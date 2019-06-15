package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.entity.CustomerDeliveryAddress;
import com.caichao.chateau.app.dto.CustomerDeliveryAddressDto;
import com.caichao.chateau.app.mapper.CustomerDeliveryAddressMapper;
import com.caichao.chateau.app.service.CustomerDeliveryAddressService;

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
public class CustomerDeliveryAddressServiceImpl extends ServiceImpl<CustomerDeliveryAddressMapper,CustomerDeliveryAddress, CustomerDeliveryAddressDto> implements CustomerDeliveryAddressService {

}