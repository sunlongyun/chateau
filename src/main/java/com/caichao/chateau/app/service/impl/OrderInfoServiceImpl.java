package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.entity.OrderInfo;
import com.caichao.chateau.app.dto.OrderInfoDto;
import com.caichao.chateau.app.mapper.OrderInfoMapper;
import com.caichao.chateau.app.service.OrderInfoService;

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
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper,OrderInfo, OrderInfoDto> implements OrderInfoService {

}