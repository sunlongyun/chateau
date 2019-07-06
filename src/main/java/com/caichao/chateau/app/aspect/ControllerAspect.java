package com.caichao.chateau.app.aspect;

import com.caichao.chateau.app.controller.response.CCResponse;
import com.caichao.chateau.app.miniProgram.response.LoginResponse;
import com.caichao.chateau.app.utils.CurrentUserUtils;
import com.caichao.chateau.app.utils.JsonUtils;
import com.caichao.chateau.app.utils.LoginUserInfoUtil;
import com.caichao.chateau.exception.BizException;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-09 16:09
 */
@Component
@Aspect
@Slf4j
public class ControllerAspect {

	@Pointcut("execution(* com.caichao..*Controller.*(..))")
	private void aroundMethod() {

	}

	@Around(value = "aroundMethod()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
			.getRequest();
		String userCode = request.getHeader("userCode");

		long start = System.currentTimeMillis();
		Object[] args = proceedingJoinPoint.getArgs();
		Signature signature = proceedingJoinPoint.getSignature();
		String className = proceedingJoinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		Object result = null;
		try {
			log.info("【请求入参】className:[{}],methodName:[{}],args:[{}]", className, methodName,
				JsonUtils.object2JsonString(args));
			LoginResponse loginResponse = LoginUserInfoUtil.getLoginResponse(userCode);
			if(null != loginResponse){
				CurrentUserUtils.set(loginResponse);
			}
			result = proceedingJoinPoint.proceed();
			return  result;
		} catch(Exception ex) {
			ex.printStackTrace();
			log.error("【请求异常】", ex);
			if(ex instanceof BizException) {
				BizException bizException = (BizException) ex;
				return CCResponse.fail(bizException.getCode(), bizException.getMsg());
			}
			String errorMsg = ex.getCause().getMessage();
			if(StringUtils.isEmpty(errorMsg)) {
				errorMsg = "请求服务异常";
			}
			return  CCResponse.fail(errorMsg);
		} finally {
			long end = System.currentTimeMillis();
			log.info("【响应参数】耗时:[{}]，出参:[{}]", end - start, JsonUtils.object2JsonString(result));
			CurrentUserUtils.remove();
		}

	}
}
