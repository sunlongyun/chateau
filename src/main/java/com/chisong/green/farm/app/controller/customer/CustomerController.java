package com.chisong.green.farm.app.controller.customer;

import com.chisong.green.farm.app.controller.customer.request.FreshCustomerReq;
import com.chisong.green.farm.app.controller.response.CCResponse;
import com.chisong.green.farm.app.dto.AccountInfoDto;
import com.chisong.green.farm.app.dto.CustomerInfoDto;
import com.chisong.green.farm.app.miniProgram.response.LoginResponse;
import com.chisong.green.farm.app.service.AccountInfoService;
import com.chisong.green.farm.app.service.CustomerInfoService;
import com.chisong.green.farm.app.utils.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 *顾客信息管理
 * @AUTHOR 孙龙云
 * @date 2019-06-09 15:44
 */
@RestController
@RequestMapping("customer")
public class CustomerController {
	@Autowired
	private CustomerInfoService customerInfoService;
	@Autowired
	private AccountInfoService accountInfoService;
	/**
	 * 刷新客户信息
	 * @param freshCustomerReq
	 * @return
	 */
	@RequestMapping("freshCustomerInfo")
	public CCResponse freshCustomerInfo(FreshCustomerReq freshCustomerReq){
		LoginResponse loginResponse = CurrentUserUtils.get();

		if(null == loginResponse){
			return CCResponse.success();
		}
		CustomerInfoDto customerInfoDto = customerInfoService.getCustomerInfoDtoByOpenId(loginResponse.getOpenid());

		if(null != freshCustomerReq.getRecommendId()
		&& !"undefined".equals(freshCustomerReq.getRecommendId())
		&& (customerInfoDto.getRecommendId() == null || customerInfoDto.getRecommendId()==-1)){
			customerInfoService.setRecommendId(customerInfoDto.getId(), Long.valueOf(freshCustomerReq.getRecommendId()));
		}

		if(!StringUtils.isEmpty(freshCustomerReq.getNickName()) && !"undefined".equals(freshCustomerReq.getNickName())){
			customerInfoDto.setNickName(freshCustomerReq.getNickName());
		}
		if(!StringUtils.isEmpty(freshCustomerReq.getCountry()) && !"undefined".equals(freshCustomerReq.getNickName())){
			customerInfoDto.setCountry(freshCustomerReq.getCountry());
		}
		if(!StringUtils.isEmpty(freshCustomerReq.getProvince()) && !"undefined".equals(freshCustomerReq.getCity())){
			customerInfoDto.setProvince(freshCustomerReq.getCity());
		}
		if(!StringUtils.isEmpty(freshCustomerReq.getCity()) && !"undefined".equals(freshCustomerReq.getCity())){
			customerInfoDto.setCity(freshCustomerReq.getCity());
		}
		if(!StringUtils.isEmpty(freshCustomerReq.getAvatarUrl()) && !"undefined".equals(freshCustomerReq.getAvatarUrl())){
			customerInfoDto.setAvatarUrl(freshCustomerReq.getAvatarUrl());
		}
		if(!StringUtils.isEmpty(freshCustomerReq.getMobile()) && !"undefined".equals(freshCustomerReq.getMobile())){
			customerInfoDto.setMobile(freshCustomerReq.getMobile());
		}


		customerInfoService.update(customerInfoDto);
		//符合开户条件,并且尚未开户的，则自动开户
		accountInfoService.createAccountInfo(customerInfoDto);
		return CCResponse.success();
	}

	/**
	 * 获取当前用户信息
	 * @return
	 */
	@RequestMapping("getCustomerInfo")
	public CCResponse getCustomerInfo(){
		LoginResponse loginResponse = CurrentUserUtils.get();
		CustomerInfoDto customerInfoDto = customerInfoService.getCustomerInfoDtoByOpenId(loginResponse.getOpenid());
		return CCResponse.success(customerInfoDto);
	}
}
