package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.entity.CartItem;
import com.chisong.green.farm.app.dto.CartItemDto;
import com.chisong.green.farm.app.mapper.CartItemMapper;
import com.chisong.green.farm.app.service.CartItemService;

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
public class CartItemServiceImpl extends ServiceImpl<CartItemMapper, CartItem, CartItemDto> implements CartItemService {

}