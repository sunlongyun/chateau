package com.chisong.green.farm.app.utils;

import com.chisong.green.farm.app.miniProgram.response.LoginResponse;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-09 15:47
 */
@Slf4j
public class CurrentUserUtils {

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

		log.info("loginResponse==={}", loginResponse);
		return loginResponse;
	}

}
