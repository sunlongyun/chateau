package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.entity.Payment;
import com.caichao.chateau.app.dto.PaymentDto;
import com.caichao.chateau.app.mapper.PaymentMapper;
import com.caichao.chateau.app.service.PaymentService;

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
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper,Payment, PaymentDto> implements PaymentService {

}