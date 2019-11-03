package com.chisong.green.farm.app.miniProgram.service;

import com.chisong.green.farm.app.miniProgram.request.AcccessCodeReq;
import com.chisong.green.farm.app.miniProgram.request.LoginReq;
import com.chisong.green.farm.app.miniProgram.response.AccessCodeResponse;
import com.chisong.green.farm.app.miniProgram.response.LoginResponse;

/**
 * 小程序登录
 */
public interface AuthService {

	/**
	 * 登录凭证 code转session
	 * @param loginReq
	 * @return
	 */
	public LoginResponse codeToSession(LoginReq loginReq);

	/**
	 * 获取凭证
	 * @param acccessCodeReq
	 * @return
	 */
	public AccessCodeResponse getAccessToken(AcccessCodeReq acccessCodeReq);
}
