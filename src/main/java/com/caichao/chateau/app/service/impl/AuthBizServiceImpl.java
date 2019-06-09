package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.constants.enums.Validity;
import com.caichao.chateau.app.dto.CustomerInfoDto;
import com.caichao.chateau.app.example.CustomerInfoExample;
import com.caichao.chateau.app.miniProgram.request.AcccessCodeReq;
import com.caichao.chateau.app.miniProgram.request.LoginReq;
import com.caichao.chateau.app.miniProgram.response.AccessCodeResponse;
import com.caichao.chateau.app.miniProgram.response.LoginResponse;
import com.caichao.chateau.app.miniProgram.service.AuthService;
import com.caichao.chateau.app.service.AuthBizService;
import com.caichao.chateau.app.service.CustomerInfoService;
import com.caichao.chateau.app.utils.LoginUserInfoUtil;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-07 19:37
 */
@Service
@Slf4j
public class AuthBizServiceImpl implements AuthBizService {

	@Value("${appid}")
	private String appId;
	@Value("${secret}")
	private String secret;
	private Map<LocalDateTime, String> accessMap = new HashMap<>();
	@Autowired
	private AuthService authService;
	@Autowired
	private CustomerInfoService customerInfoService;
	@Override
	public String getAccessToken() {
		String accessToken = null;
		if(!accessMap.isEmpty() && LocalDateTime.now().isAfter(accessMap.keySet().iterator().next())) {
			accessToken = accessMap.values().iterator().next();
		} else {
			AcccessCodeReq acccessCodeReq = new AcccessCodeReq();
			acccessCodeReq.setSecret(secret);
			acccessCodeReq.setAppId(appId);
			AccessCodeResponse accessCodeResponse = authService.getAccessToken(acccessCodeReq);
			LocalDateTime localDateTime = LocalDateTime.now();
			localDateTime.plusSeconds(accessCodeResponse.getExpiresIn().longValue()).minusMinutes(5);
			accessMap.put(localDateTime, accessCodeResponse.getAccessToken());
			accessToken = accessCodeResponse.getAccessToken();
		}
		return accessToken;
	}

	@Override
	public String login(String jsCode) {
		LoginReq loginReq = new LoginReq();
		loginReq.setAppId(appId);
		loginReq.setSecret(secret);
		loginReq.setJsCode(jsCode);
		LoginResponse loginResponse = authService.codeToSession(loginReq);
		log.info("loginResponse:{}",loginResponse);
		insertIfNecessary(loginResponse);

		String userCode = LoginUserInfoUtil.put(loginResponse);
		return userCode;
	}

	//如果openid没有落库，则添加，否则更新
	private void insertIfNecessary(LoginResponse loginResponse){
		CustomerInfoExample customerInfoExample = new CustomerInfoExample();
		customerInfoExample.createCriteria().andOpenIdEqualTo(loginResponse.getOpenid());
		List<CustomerInfoDto> customerInfoDtoList = customerInfoService.getList(customerInfoExample);
		if(CollectionUtils.isEmpty(customerInfoDtoList)){
			CustomerInfoDto customerInfoDto = new CustomerInfoDto();
			BeanUtils.copyProperties(loginResponse,customerInfoDto);
			customerInfoService.save(customerInfoDto);
		}else{
			CustomerInfoDto customerInfoDto =	customerInfoDtoList.get(0);
			customerInfoDto.setValidity(Validity.AVAIL.code());
			customerInfoDto.setUnionId(loginResponse.getUnionId());
			customerInfoService.update(customerInfoDto);
		}
	}
}
