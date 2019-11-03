package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.entity.CustomerDeliveryAddress;
import com.chisong.green.farm.app.dto.CustomerDeliveryAddressDto;
import com.chisong.green.farm.app.mapper.CustomerDeliveryAddressMapper;
import com.chisong.green.farm.app.service.CustomerDeliveryAddressService;

import com.lianshang.generator.commons.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-06-15
 */
@Service
public class CustomerDeliveryAddressServiceImpl extends ServiceImpl<CustomerDeliveryAddressMapper, CustomerDeliveryAddress, CustomerDeliveryAddressDto> implements
	CustomerDeliveryAddressService {

}