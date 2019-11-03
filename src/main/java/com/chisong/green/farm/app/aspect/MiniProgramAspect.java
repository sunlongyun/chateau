package com.chisong.green.farm.app.aspect;

import com.chisong.green.farm.app.miniProgram.response.ParentResponse;
import com.chisong.green.farm.app.utils.JsonUtils;
import com.chisong.green.farm.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 描述:
 * 小程序网关调用失败，调用异常或者返回code不是0
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-09 16:54
 */
@Component
@Aspect
@Slf4j
public class MiniProgramAspect {

	@Pointcut("execution(* com.caichao.chateau.app.miniProgram.service..*.*(..))")
	private void aroundMethod() {

	}

	@Around(value = "aroundMethod()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object[] args = proceedingJoinPoint.getArgs();
		Signature signature = proceedingJoinPoint.getSignature();
		String className = proceedingJoinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		try {
			log.info("【小程序请求入参】className:{},methodName:{},args:{}", className, methodName,
				JsonUtils.object2JsonString(args));

			ParentResponse parentResponse = (ParentResponse) proceedingJoinPoint.proceed();

			log.info("parentResponse:{}",parentResponse);
			if(!StringUtils.isEmpty(parentResponse.getErrCode()) && 0 !=parentResponse.getErrCode().doubleValue() ){
				throw new BizException(parentResponse.getErrCode()+"", parentResponse.getErrMsg());
			}
			log.info("【小程序响应】出参:{}", JsonUtils.object2JsonString(parentResponse));
			return parentResponse;
		} catch(Exception ex) {
			log.error("【小程序请求异常】", ex);

			String errorMsg = ex.getCause()+"";
			if(StringUtils.isEmpty(errorMsg)) {
				errorMsg = "小程序请求服务异常";
			}
			throw new BizException("9999", errorMsg);
		} finally {
			long end = System.currentTimeMillis();
			log.info("【小程序请求耗时】耗时:{}", end - start);
		}

	}
}
