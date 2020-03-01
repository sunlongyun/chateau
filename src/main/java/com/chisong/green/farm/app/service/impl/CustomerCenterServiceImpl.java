package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.miniProgram.annotaion.ResUtil;
import com.chisong.green.farm.app.miniProgram.msg.ParentMsg;
import com.chisong.green.farm.app.miniProgram.msg.TextMsg;
import com.chisong.green.farm.app.service.AuthBizService;
import com.chisong.green.farm.app.service.CustomerCenterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2020-02-29 17:36
 */
@Service
@Slf4j
public class CustomerCenterServiceImpl implements CustomerCenterService {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private AuthBizService authBizService;


	@Override
	public void sendToCustomer(ParentMsg parentMsg) {
		parentMsg.setAccessToken(authBizService.getAccessToken());

		String json = ResUtil.getJson(parentMsg);
		log.info("回复消息:{}", json);
		String url ="https://api.weixin.qq.com/cgi-bin/message/custom/send"
			+ "?access_token=";

		String accessToken = authBizService.getAccessToken();
		log.info("accessToken:{}", accessToken);
		url+= accessToken;
		log.info("url=={}", url);

		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
		ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
		log.info("响应:{}", exchange.getBody());
	}
}
