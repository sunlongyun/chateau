package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.constants.enums.UserStatusEnum;
import com.chisong.green.farm.app.constants.enums.Validity;
import com.chisong.green.farm.app.entity.CustomerInfo;
import com.chisong.green.farm.app.dto.CustomerInfoDto;
import com.chisong.green.farm.app.example.CustomerInfoExample;
import com.chisong.green.farm.app.mapper.CustomerInfoMapper;
import com.chisong.green.farm.app.service.CustomerInfoService;

import com.lianshang.generator.commons.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
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
public class CustomerInfoServiceImpl extends ServiceImpl<CustomerInfoMapper, CustomerInfo, CustomerInfoDto> implements
	CustomerInfoService {

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
	public void setRecommendId(Long id, Long recommendId) {
		CustomerInfoDto customerInfoDto = getById(id);
		customerInfoDto.setRecommendId(recommendId);
		long times = System.currentTimeMillis() - customerInfoDto.getCreateTime().getTime();

		if(times > 30 * 60 *1000L || (customerInfoDto.getRecommendId() != null
		&& customerInfoDto.getRecommendId() != -1)){//大于30分钟的不更新,已经有推荐人的 不更新
			return ;
		}
		this.update(customerInfoDto);
	}

	@Override
	public CustomerInfoDto getCustomerInfoDtoByOpenId(String openId) {
		CustomerInfoExample customerInfoExample = new CustomerInfoExample();
		customerInfoExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code()).andOpenIdEqualTo(openId).andIdGreaterThan(0L);
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