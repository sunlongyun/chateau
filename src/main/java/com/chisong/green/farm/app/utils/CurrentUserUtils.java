package com.chisong.green.farm.app.utils;

import com.chisong.green.farm.app.dto.CustomerInfoDto;
import com.chisong.green.farm.app.miniProgram.response.LoginResponse;
import com.chisong.green.farm.app.service.CustomerInfoService;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-09 15:47
 */
@Slf4j
@Component
public class CurrentUserUtils implements ApplicationContextAware {

	private static CustomerInfoService customerInfoService;

	/**
	 * 获取当前顾客信息
	 * @return
	 */
	public static CustomerInfoDto getCurrentCustomerInfoDto(){
		LoginResponse loginResponse = get();
		log.info("customerInfoService=="+customerInfoService);
		CustomerInfoDto customerInfoDto = customerInfoService.getCustomerInfoDtoByOpenId(loginResponse.getOpenid());
		log.info("customerInfoDto:{}", customerInfoDto);
		return customerInfoDto;
	}
	/**
	 * 获取当前登录用户
	 * @return
	 */
	public static LoginResponse get(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
			.getRequest();
		String userCode = request.getHeader("userCode");
		log.info("userCode==={}", userCode);
		log.info("userInfoMap=={}",LoginUserInfoUtil.userInfoMap);
		LoginResponse loginResponse = LoginUserInfoUtil.getLoginResponse(userCode);
		if(null == loginResponse){
			throw new RuntimeException("会话已过期，请重新登录");
		}
		log.info("loginResponse==={}", loginResponse);
		return loginResponse;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		customerInfoService = applicationContext.getBean(CustomerInfoService.class);
	}
}
