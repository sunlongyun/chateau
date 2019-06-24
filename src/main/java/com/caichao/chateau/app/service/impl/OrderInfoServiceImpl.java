package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.dto.CountryChateauBeverageDto;
import com.caichao.chateau.app.dto.OrderDetailDto;
import com.caichao.chateau.app.entity.OrderInfo;
import com.caichao.chateau.app.dto.OrderInfoDto;
import com.caichao.chateau.app.mapper.OrderInfoMapper;
import com.caichao.chateau.app.service.CartItemService;
import com.caichao.chateau.app.service.CountryChateauBeverageService;
import com.caichao.chateau.app.service.OrderDetailService;
import com.caichao.chateau.app.service.OrderInfoService;

import com.lianshang.generator.commons.ServiceImpl;
import java.util.List;
import javax.xml.ws.soap.Addressing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
	@Autowired
	private CountryChateauBeverageService countryChateauBeverageService;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private CartItemService cartItemService;
	@Override
	@Transactional
	public String createOrder(OrderInfoDto orderInfoDto) {
		//1.添加订单基本信息
		save(orderInfoDto);
		//2.添加订单明细信息
		List<OrderDetailDto> orderDetailDtoList = orderInfoDto.getOrderDetailDtoList();
		if(CollectionUtils.isEmpty(orderDetailDtoList)){
			throw  new RuntimeException("订单明细不能为空");
		}
		long total = 0l;
		for(OrderDetailDto orderDetailDto : orderDetailDtoList){

			orderDetailDto.setOrderId(orderInfoDto.getId());
			CountryChateauBeverageDto countryChateauBeverageDto =  countryChateauBeverageService.getById(orderDetailDto
				.getBeverageId());
			orderDetailDto.setMinPicUrl(countryChateauBeverageDto.getMinPicUrl());
			orderDetailDto.setTitle(countryChateauBeverageDto.getTitle());
			orderDetailDto.setPrice(countryChateauBeverageDto.getPrice());
			orderDetailDto.setEnTitle(countryChateauBeverageDto.getEnTitle());
			orderDetailDto.setTotalPrice(orderDetailDto.getPrice() * orderDetailDto.getNum());
			total += orderDetailDto.getPrice() * orderDetailDto.getNum();

			orderDetailService.save(orderDetailDto);

			//如果购物项存在，则删除
			if(null != orderDetailDto.getCartItemId()){
				cartItemService.deleteById( orderDetailDto.getCartItemId());
			}
		}

		orderInfoDto.setTotalAmount(total);
		//3.更新订单信息
		this.update(orderInfoDto);
		//4.发起支付 TODO 调用支付接口
		return orderInfoDto.getOrderNo();
	}
}