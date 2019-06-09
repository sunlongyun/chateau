package com.caichao.chateau.app.service;

public interface AuthBizService {

	/**
	 * 获取accessToken
	 */
	public String getAccessToken();

	/**
	 * 登录获取userCode
	 */
	public String login(String jsCode);
}
