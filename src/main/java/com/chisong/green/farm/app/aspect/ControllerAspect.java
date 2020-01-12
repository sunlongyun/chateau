package com.chisong.green.farm.app.aspect;

import com.chisong.green.farm.app.controller.response.CCResponse;
import com.chisong.green.farm.app.dto.CustomerInfoDto;
import com.chisong.green.farm.app.miniProgram.response.LoginResponse;
import com.chisong.green.farm.app.service.CustomerInfoService;
import com.chisong.green.farm.app.utils.JsonUtils;
import com.chisong.green.farm.app.utils.LoginUserInfoUtil;
import com.chisong.green.farm.exception.BizException;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private CustomerInfoService customerInfoService;

	@Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping) "
		+ "|| @annotation(org.springframework.web.bind.annotation.PostMapping) "
		+ "|| @annotation(org.springframework.web.bind.annotation.RequestMapping)")
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
				//已经登录的，尝试设置推荐人
				trySetRecommend(request, loginResponse);
			}
			result = proceedingJoinPoint.proceed();
			return  result;
		} catch(Exception ex) {
			ex.printStackTrace();
			log.error("【请求异常】", ex);
			if(ex  instanceof  Exception) {
				if(ex instanceof BizException) {
					BizException bizException = (BizException) ex;
					return CCResponse.fail(bizException.getCode(), bizException.getMsg());
				}else{
					return CCResponse.fail(ex.getMessage());
				}
			}

			String errorMsg = ex.getMessage();
			if(StringUtils.isEmpty(errorMsg)) {
				errorMsg = "请求服务异常";
			}
			return  CCResponse.fail(errorMsg);
		} finally {
			long end = System.currentTimeMillis();
			log.info("【响应参数】耗时:[{}]，出参:[{}]", end - start, JsonUtils.object2JsonString(result));
		}

	}

	private void trySetRecommend(HttpServletRequest request, LoginResponse loginResponse) {
		String recommendId =  (String) request.getParameter("recommendId");
		log.info("recommendId:{}", recommendId);
		//之前推荐人为空，并且账号是半个内创建，当前推荐人不为空，那么设置推荐人
		if(!StringUtils.isEmpty(recommendId)){
            CustomerInfoDto customerInfoDto =  customerInfoService.getCustomerInfoDtoByOpenId(loginResponse.getOpenid());
            if((null == customerInfoDto.getRecommendId() || -1 == customerInfoDto.getRecommendId())
            && (System.currentTimeMillis() -  customerInfoDto.getCreateTime().getTime()) <(30*60*1000L) ){
                customerInfoDto.setRecommendId(Long.parseLong(recommendId));
                customerInfoService.update(customerInfoDto);
            }
        }
	}
}
