package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.constants.enums.Validity;
import com.chisong.green.farm.app.dto.CustomerInfoDto;
import com.chisong.green.farm.app.dto.ShoppingCartDto;
import com.chisong.green.farm.app.example.CustomerInfoExample;
import com.chisong.green.farm.app.example.ShoppingCartExample;
import com.chisong.green.farm.app.miniProgram.request.AcccessCodeReq;
import com.chisong.green.farm.app.miniProgram.request.LoginReq;
import com.chisong.green.farm.app.miniProgram.response.AccessCodeResponse;
import com.chisong.green.farm.app.miniProgram.response.LoginResponse;
import com.chisong.green.farm.app.miniProgram.service.AuthService;
import com.chisong.green.farm.app.service.AuthBizService;
import com.chisong.green.farm.app.service.CustomerInfoService;
import com.chisong.green.farm.app.service.ShoppingCartService;
import com.chisong.green.farm.app.utils.AppUtils;
import com.chisong.green.farm.app.utils.LoginUserInfoUtil;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
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

//	public Map<LocalDateTime, String> accessMap = new HashMap<>();
	private Map<String, Map<LocalDateTime, String>> appMap = new HashMap<>();
	@Autowired
	private AuthService authService;
	@Autowired
	private CustomerInfoService customerInfoService;
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Override
	public String getAccessToken() {
		String accessToken = null;
		Map<LocalDateTime, String> accessMap = appMap.computeIfAbsent(AppUtils.getName(), (key) -> new HashMap<>());
		if(!accessMap.isEmpty() && LocalDateTime.now().isBefore(accessMap.keySet().iterator().next())) {
			accessToken = accessMap.values().iterator().next();
		} else {
			accessMap.clear();
			AcccessCodeReq acccessCodeReq = new AcccessCodeReq();
			acccessCodeReq.setSecret(AppUtils.getSecret());
			acccessCodeReq.setAppId(AppUtils.getName());
			AccessCodeResponse accessCodeResponse = authService.getAccessToken(acccessCodeReq);
			LocalDateTime localDateTime = LocalDateTime.now();
			localDateTime  =
				localDateTime.plusSeconds(accessCodeResponse.getExpiresIn().longValue()).minusMinutes(15);
			accessMap.put(localDateTime, accessCodeResponse.getAccessToken());
			appMap.put(AppUtils.getName(), accessMap);
			accessToken = accessCodeResponse.getAccessToken();
		}
		return accessToken;
	}

	@Override
	public String login(String jsCode) {
		LoginReq loginReq = new LoginReq();
		loginReq.setAppId(AppUtils.getName());
		loginReq.setSecret(AppUtils.getSecret());
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
		CustomerInfoDto customerInfoDto = new CustomerInfoDto();
		if(CollectionUtils.isEmpty(customerInfoDtoList)){

			customerInfoDto.setUnionId(loginResponse.getUnionId());
			customerInfoDto.setOpenId(loginResponse.getOpenid());
//			BeanUtils.copyProperties(loginResponse,customerInfoDto);
			Long apId = AppUtils.get();
			customerInfoDto.setAppInfoId(apId);
			customerInfoService.save(customerInfoDto);
		}else{
			 customerInfoDto =	customerInfoDtoList.get(0);
			customerInfoDto.setValidity(Validity.AVAIL.code());
			customerInfoDto.setUnionId(loginResponse.getUnionId());
			customerInfoService.update(customerInfoDto);
		}

		//购物车如果不存在，则创建购物车
		ShoppingCartExample shoppingCartExample = new ShoppingCartExample();
		shoppingCartExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code()).andCustomerInfoIdEqualTo
			(Integer.valueOf(customerInfoDto.getId()+"") );
		if(CollectionUtils.isEmpty(shoppingCartService.getList(shoppingCartExample))){
			ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
			shoppingCartDto.setCustomerInfoId(Integer.valueOf(customerInfoDto.getId()+""));
			Long apId = AppUtils.get();
			shoppingCartDto.setAppInfoId(apId);
			shoppingCartService.save(shoppingCartDto);
		}

	}
}
