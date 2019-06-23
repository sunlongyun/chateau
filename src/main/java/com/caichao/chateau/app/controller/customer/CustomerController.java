package com.caichao.chateau.app.controller.customer;

import com.caichao.chateau.app.controller.customer.request.FreshCustomerReq;
import com.caichao.chateau.app.controller.response.CCResponse;
import com.caichao.chateau.app.dto.CustomerInfoDto;
import com.caichao.chateau.app.miniProgram.response.LoginResponse;
import com.caichao.chateau.app.service.CustomerInfoService;
import com.caichao.chateau.app.utils.CurrentUserUtils;
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
	/**
	 * 刷新客户信息
	 * @param freshCustomerReq
	 * @return
	 */
	@RequestMapping("freshCustomerInfo")
	public CCResponse freshCustomerInfo(FreshCustomerReq freshCustomerReq){
		LoginResponse loginResponse = CurrentUserUtils.get();

		CustomerInfoDto customerInfoDto = customerInfoService.getCustomerInfoDtoByOpenId(loginResponse.getOpenid());
		if(!StringUtils.isEmpty(freshCustomerReq.getNickName())){
			customerInfoDto.setNickName(freshCustomerReq.getNickName());
		}
		if(!StringUtils.isEmpty(freshCustomerReq.getCountry())){
			customerInfoDto.setCountry(freshCustomerReq.getCountry());
		}
		if(!StringUtils.isEmpty(freshCustomerReq.getProvince())){
			customerInfoDto.setProvince(freshCustomerReq.getProvince());
		}
		if(!StringUtils.isEmpty(freshCustomerReq.getCity())){
			customerInfoDto.setCity(freshCustomerReq.getCity());
		}
		if(!StringUtils.isEmpty(freshCustomerReq.getAvatarUrl())){
			customerInfoDto.setAvatarUrl(freshCustomerReq.getAvatarUrl());
		}
		if(!StringUtils.isEmpty(freshCustomerReq.getMobile())){
			customerInfoDto.setMobile(freshCustomerReq.getMobile());
		}
		customerInfoService.update(customerInfoDto);

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
