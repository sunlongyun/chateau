package com.caichao.chateau.app.miniProgram.service.impl;

import com.caichao.chateau.app.miniProgram.annotaion.ReqUtil;
import com.caichao.chateau.app.miniProgram.annotaion.ResUtil;
import com.caichao.chateau.app.miniProgram.request.LoginReq;
import com.caichao.chateau.app.miniProgram.response.LoginResponse;
import com.caichao.chateau.app.miniProgram.service.LoginService;
import java.util.Map;
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
public class LoginServiceImpl implements LoginService {

	private String jsCode2SessionUrl = "https://api.weixin.qq.com/sns/jscode2session";
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public LoginResponse codeToSession(LoginReq loginReq) {
		String url = jsCode2SessionUrl + ReqUtil.getStr(loginReq);
		String forObject = restTemplate.getForObject(url, String.class);
		LoginResponse loginResponse = ResUtil.getObj(LoginResponse.class, forObject);
		return loginResponse;
	}
}
