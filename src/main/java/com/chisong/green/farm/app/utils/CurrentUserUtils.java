package com.chisong.green.farm.app.utils;

import com.chisong.green.farm.app.miniProgram.response.LoginResponse;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-09 15:47
 */
public class CurrentUserUtils {
	private static ThreadLocal<LoginResponse> currentUser = new ThreadLocal<>();

	/**
	 * 设置当前登录用户
	 * @param loginResponse
	 */
	public static void set(LoginResponse loginResponse){
		currentUser.set(loginResponse);
	}

	/**
	 * 获取当前登录用户
	 * @return
	 */
	public static LoginResponse get(){
		LoginResponse loginResponse = currentUser.get();
		if(null == loginResponse){
			loginResponse = new LoginResponse();
			loginResponse.setOpenid("oqrTq4jLQt0I_9F4vQVQLQGDrBbM");
		}
		return loginResponse;
	}

	public static void remove(){
		currentUser.remove();;
	}
}
