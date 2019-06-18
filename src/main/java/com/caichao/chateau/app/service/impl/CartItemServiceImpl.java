package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.entity.CartItem;
import com.caichao.chateau.app.dto.CartItemDto;
import com.caichao.chateau.app.mapper.CartItemMapper;
import com.caichao.chateau.app.service.CartItemService;

import com.lianshang.generator.commons.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车-酒水关联表 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-06-18
 */
@Service
public class CartItemServiceImpl extends ServiceImpl<CartItemMapper,CartItem, CartItemDto> implements CartItemService {

}