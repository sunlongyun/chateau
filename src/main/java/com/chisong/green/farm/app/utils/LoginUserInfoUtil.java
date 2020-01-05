package com.chisong.green.farm.app.utils;

import com.chisong.green.farm.app.miniProgram.response.LoginResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListMap;
import org.springframework.util.StringUtils;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-09 10:12
 */
public class LoginUserInfoUtil {

	public static Map<String, LoginResponse> userInfoMap = new HashMap<>();

	/**
	 * 根据userCode查询登录返回信息
	 * @param userCode
	 * @return
	 */
	public static LoginResponse getLoginResponse(String userCode){
		return userInfoMap.get(userCode);
	}

	/**
	 * 添加并返回userCode
	 */
	public static String put(LoginResponse loginResponse) {
		String userCode =  LocalDateTime.now().format(DateTimeFormatter.ofPattern
			("yyyyMMddHHmmss"))+"_"+UUID.randomUUID().toString();
		userInfoMap.put(userCode,loginResponse);
		return userCode;
	}
}
