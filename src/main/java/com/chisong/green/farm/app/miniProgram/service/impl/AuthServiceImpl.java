package com.chisong.green.farm.app.miniProgram.service.impl;

import com.chisong.green.farm.app.miniProgram.ReqUtil;
import com.chisong.green.farm.app.miniProgram.ResUtil;
import com.chisong.green.farm.app.miniProgram.request.AcccessCodeReq;
import com.chisong.green.farm.app.miniProgram.request.LoginReq;
import com.chisong.green.farm.app.miniProgram.response.AccessCodeResponse;
import com.chisong.green.farm.app.miniProgram.response.LoginResponse;
import com.chisong.green.farm.app.miniProgram.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-07 16:13
 */
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

	/**
	 * code转sesson
	 */
	private String JS_CODE_2_SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session";
	/**
	 * 获取访问token
	 */
	private String ACCESS_TOKEN_URL ="https://api.weixin.qq.com/cgi-bin/token";
	@Autowired
	private RestTemplate restTemplate;
	private static String access_token = null;
	private static long endTime = 1l;
	@Override
	public LoginResponse codeToSession(LoginReq loginReq) {
		log.info("loginReq:{}",loginReq);
		String url = JS_CODE_2_SESSION_URL + ReqUtil.getStr(loginReq);
		log.info("url:{}",url);
		String forObject = restTemplate.getForObject(url, String.class);
		LoginResponse loginResponse = ResUtil.getObj(LoginResponse.class, forObject);
		return loginResponse;
	}

	@Override
	public AccessCodeResponse getAccessToken(AcccessCodeReq acccessCodeReq) {
		String url = ACCESS_TOKEN_URL+ReqUtil.getStr(acccessCodeReq);
		String forObject = restTemplate.getForObject(url, String.class);
		AccessCodeResponse accessCodeResponse = ResUtil.getObj(AccessCodeResponse.class,forObject);
		return accessCodeResponse;
	}
}
