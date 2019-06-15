package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.entity.ShoppingCart;
import com.caichao.chateau.app.dto.ShoppingCartDto;
import com.caichao.chateau.app.mapper.ShoppingCartMapper;
import com.caichao.chateau.app.service.ShoppingCartService;

import com.lianshang.generator.commons.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-06-15
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper,ShoppingCart, ShoppingCartDto> implements ShoppingCartService {

}