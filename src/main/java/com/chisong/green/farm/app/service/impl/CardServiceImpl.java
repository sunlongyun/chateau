package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.constants.enums.Validity;
import com.chisong.green.farm.app.dto.CustomerInfoDto;
import com.chisong.green.farm.app.example.CustomerInfoExample;
import com.chisong.green.farm.app.miniProgram.request.SendCardRequest;
import com.chisong.green.farm.app.miniProgram.service.WxPayService;
import com.chisong.green.farm.app.service.AuthBizService;
import com.chisong.green.farm.app.service.CardService;
import com.chisong.green.farm.app.service.CustomerInfoService;
import com.chisong.green.farm.app.utils.JsonUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

/**
 * 描述:
 * 卡券管理service
 * @AUTHOR 孙龙云
 * @date 2020-07-18 15:18
 */
@Service
@Slf4j
public class CardServiceImpl implements CardService {

	@Autowired
	private WxPayService wxPayService;

	@Autowired
	private CustomerInfoService customerInfoService;

	@Override
	public void sendCard(String cardId, List<String> openIdList) {
		if(CollectionUtils.isEmpty(openIdList)){
			throw  new RuntimeException("顾客不能为空");
		}

		for(String openId : openIdList){
			SendCardRequest sendCardRequest = new SendCardRequest();
			sendCardRequest.setCouponStockId(cardId);
			sendCardRequest.setOpenid(openId);
			sendCardRequest.setOpenidCount(1);
			wxPayService.sendCard(sendCardRequest);
		}
	}

	@Override
	public void sendCardToAll(String cardId) {
		CustomerInfoExample customerInfoExample = new CustomerInfoExample();
		customerInfoExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code());
		List<String> openIds =
			customerInfoService.getList(customerInfoExample).stream().map(CustomerInfoDto::getOpenId).collect(Collectors.toList());
		sendCard(cardId, openIds);
	}

	@Override
	public void sendCardToAccountUser(String cardId) {
		CustomerInfoExample customerInfoExample = new CustomerInfoExample();
		customerInfoExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code()).andNickNameIsNotNull();
		List<String> openIds =
			customerInfoService.getList(customerInfoExample).stream().map(CustomerInfoDto::getOpenId).collect(Collectors.toList());
		sendCard(cardId, openIds);
	}
}
