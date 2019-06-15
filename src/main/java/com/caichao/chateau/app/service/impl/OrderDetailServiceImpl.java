package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.entity.OrderDetail;
import com.caichao.chateau.app.dto.OrderDetailDto;
import com.caichao.chateau.app.mapper.OrderDetailMapper;
import com.caichao.chateau.app.service.OrderDetailService;

import com.lianshang.generator.commons.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单详情 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-06-15
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper,OrderDetail, OrderDetailDto> implements OrderDetailService {

}