package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.entity.CustomerInfo;
import com.caichao.chateau.app.dto.CustomerInfoDto;
import com.caichao.chateau.app.mapper.CustomerInfoMapper;
import com.caichao.chateau.app.service.CustomerInfoService;

import com.lianshang.generator.commons.ServiceImpl;
import org.springframework.stereotype.Service;

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

}