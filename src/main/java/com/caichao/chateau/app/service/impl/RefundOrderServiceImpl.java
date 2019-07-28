package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.entity.RefundOrder;
import com.caichao.chateau.app.dto.RefundOrderDto;
import com.caichao.chateau.app.mapper.RefundOrderMapper;
import com.caichao.chateau.app.service.RefundOrderService;

import com.lianshang.generator.commons.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 退款申请单 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-07-28
 */
@Service
public class RefundOrderServiceImpl extends ServiceImpl<RefundOrderMapper,RefundOrder, RefundOrderDto> implements RefundOrderService {

}