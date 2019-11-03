package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.entity.OrderDeliveryAddressMapping;
import com.chisong.green.farm.app.dto.OrderDeliveryAddressMappingDto;
import com.chisong.green.farm.app.mapper.OrderDeliveryAddressMappingMapper;
import com.chisong.green.farm.app.service.OrderDeliveryAddressMappingService;

import com.lianshang.generator.commons.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单--收货地址映射表 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-06-15
 */
@Service
public class OrderDeliveryAddressMappingServiceImpl extends ServiceImpl<OrderDeliveryAddressMappingMapper, OrderDeliveryAddressMapping, OrderDeliveryAddressMappingDto> implements
	OrderDeliveryAddressMappingService {

}