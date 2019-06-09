package com.caichao.chateau.app.service;

public interface AuthBizService {

	/**
	 * 获取accessToken
	 * @return
	 */
	public String getAccessToken();

	/**
	 * 登录获取userCode
	 * @param jsCode
	 * @return
	 */
	public String login(String jsCode);
}
