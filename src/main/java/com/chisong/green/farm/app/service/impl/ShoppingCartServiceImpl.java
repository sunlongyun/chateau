package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.entity.ShoppingCart;
import com.chisong.green.farm.app.dto.ShoppingCartDto;
import com.chisong.green.farm.app.mapper.ShoppingCartMapper;
import com.chisong.green.farm.app.service.ShoppingCartService;

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
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart, ShoppingCartDto> implements
	ShoppingCartService {

}