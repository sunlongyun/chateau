package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.entity.CartBeverageMapping;
import com.caichao.chateau.app.dto.CartBeverageMappingDto;
import com.caichao.chateau.app.mapper.CartBeverageMappingMapper;
import com.caichao.chateau.app.service.CartBeverageMappingService;

import com.lianshang.generator.commons.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车-酒水关联表 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-06-15
 */
@Service
public class CartBeverageMappingServiceImpl extends ServiceImpl<CartBeverageMappingMapper,CartBeverageMapping, CartBeverageMappingDto> implements CartBeverageMappingService {

}