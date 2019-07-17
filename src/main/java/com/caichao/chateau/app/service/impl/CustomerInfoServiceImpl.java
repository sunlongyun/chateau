package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.constants.enums.UserStatusEnum;
import com.caichao.chateau.app.constants.enums.Validity;
import com.caichao.chateau.app.dto.OrderInfoDto;
import com.caichao.chateau.app.entity.CustomerInfo;
import com.caichao.chateau.app.dto.CustomerInfoDto;
import com.caichao.chateau.app.example.CustomerInfoExample;
import com.caichao.chateau.app.mapper.CustomerInfoMapper;
import com.caichao.chateau.app.service.CustomerInfoService;

import com.lianshang.generator.commons.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * <p>
 * 顾客信息表 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-06-07
 */
@Service
public class CustomerInfoServiceImpl extends ServiceImpl<CustomerInfoMapper,CustomerInfo, CustomerInfoDto> implements CustomerInfoService {

	@Override
	public void changeStatus(Long id, Integer status) {
		CustomerInfoDto customerInfoDto = getById(id);
		if(null != customerInfoDto){
			UserStatusEnum userStatusEnum = UserStatusEnum.getUserStatusEnum(status);
			if(null == userStatusEnum){
				throw new RuntimeException("状态错误");
			}
			customerInfoDto.setStatus(status);
			this.update(customerInfoDto);
		}
	}

	@Override
	public CustomerInfoDto getCustomerInfoDtoByOpenId(String openId) {
		CustomerInfoExample customerInfoExample = new CustomerInfoExample();
		customerInfoExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code()).andOpenIdEqualTo(openId);
		List<CustomerInfoDto> list = getList(customerInfoExample);
		if(!CollectionUtils.isEmpty(list)){
			return list.get(0);
		}
		return null;
	}

	@Override
	public CustomerInfoDto getAdminUserByUserName(String userName) {
		CustomerInfoExample customerInfoExample = new CustomerInfoExample();
		customerInfoExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code()).andUserNameEqualTo(userName);
		List<CustomerInfoDto> customerInfoDtoList = getList(customerInfoExample);
		if(!CollectionUtils.isEmpty(customerInfoDtoList)){
			return customerInfoDtoList.get(0);
		}
		return null;
	}
}