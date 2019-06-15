package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.entity.OrderDeliveryAddressMapping;
import com.caichao.chateau.app.dto.OrderDeliveryAddressMappingDto;
import com.caichao.chateau.app.mapper.OrderDeliveryAddressMappingMapper;
import com.caichao.chateau.app.service.OrderDeliveryAddressMappingService;

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
public class OrderDeliveryAddressMappingServiceImpl extends ServiceImpl<OrderDeliveryAddressMappingMapper,OrderDeliveryAddressMapping, OrderDeliveryAddressMappingDto> implements OrderDeliveryAddressMappingService {

}