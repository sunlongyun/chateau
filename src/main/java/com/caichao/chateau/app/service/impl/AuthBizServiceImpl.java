package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.miniProgram.request.AcccessCodeReq;
import com.caichao.chateau.app.miniProgram.response.AccessCodeResponse;
import com.caichao.chateau.app.miniProgram.service.AuthService;
import com.caichao.chateau.app.service.AuthBizService;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-07 19:37
 */
@Service
public class AuthBizServiceImpl implements AuthBizService{
	@Value("${appid}")
	private String appId;
	@Value("${secret}")
	private String secret;
	private Map<LocalDateTime,String> accessMap = new HashMap<>();
	@Autowired
	private AuthService authService;

	@Override
	public String getAccessToken() {
		String accessToken =  null;
		if(!accessMap.isEmpty() && LocalDateTime.now().isAfter(accessMap.keySet().iterator().next())){
			accessToken = accessMap.values().iterator().next();
		}else{
			AcccessCodeReq acccessCodeReq = new AcccessCodeReq();
			acccessCodeReq.setSecret(secret);
			acccessCodeReq.setAppId(appId);
			AccessCodeResponse accessCodeResponse = authService.getAccessToken(acccessCodeReq);
			LocalDateTime localDateTime = LocalDateTime.now();
			localDateTime.plusSeconds(accessCodeResponse.getExpiresIn().longValue()).minusMinutes(5);
			accessMap.put(localDateTime,accessCodeResponse.getAccessToken());
			accessToken = accessCodeResponse.getAccessToken();
		}
		return accessToken;
	}
}
