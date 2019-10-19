package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.constants.enums.Validity;
import com.caichao.chateau.app.dto.CartItemDto;
import com.caichao.chateau.app.dto.OrderDeliveryAddressMappingDto;
import com.caichao.chateau.app.dto.OrderDetailDto;
import com.caichao.chateau.app.dto.OrderInfoDto;
import com.caichao.chateau.app.entity.OrderInfo;
import com.caichao.chateau.app.example.CartItemExample;
import com.caichao.chateau.app.example.OrderInfoExample;
import com.caichao.chateau.app.mapper.OrderInfoMapper;
import com.caichao.chateau.app.service.CartItemService;
import com.caichao.chateau.app.service.OrderDeliveryAddressMappingService;
import com.caichao.chateau.app.service.OrderDetailService;
import com.caichao.chateau.app.service.OrderInfoService;
import com.lianshang.generator.commons.ServiceImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-06-15
 */
@Service
@Slf4j
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo, OrderInfoDto> implements
	OrderInfoService {

	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private OrderDeliveryAddressMappingService orderDeliveryAddressMappingService;

	@Override
	@Transactional
	public String createOrder(OrderInfoDto orderInfoDto, Integer addressId) {
		//1.添加订单基本信息
		save(orderInfoDto);
		//2.添加订单明细信息
		List<OrderDetailDto> orderDetailDtoList = orderInfoDto.getOrderDetailDtoList();
		if(CollectionUtils.isEmpty(orderDetailDtoList)) {
			throw new RuntimeException("订单明细不能为空");
		}
		long total = 0l;

		//每个庄园，算一次运费
		Map<Integer, Integer> chateauPostage = new HashMap<>();
		for(OrderDetailDto orderDetailDto : orderDetailDtoList) {
			log.info("orderDetailDto:{}", orderDetailDto);
			if(null == orderDetailDto) {
				continue;
			}

			total += orderDetailDto.getPrice() * orderDetailDto.getNum();

			orderDetailDto.setOrderId(orderInfoDto.getId());

//			CountryChateauBeverageDto countryChateauBeverageDto = countryChateauBeverageService.getById(orderDetailDto
//				.getBeverageId());
//			//查询庄园，查询默认运费
//			CountryChateauDto countryChateauDto = countryChateauService
//				.getById(countryChateauBeverageDto.getChateauId());
//			if(null == countryChateauDto.getPostage()) {
//				countryChateauDto.setPostage(0);
//			}
//			chateauPostage.put(countryChateauDto.getId(), countryChateauDto.getPostage());
//
//			orderDetailDto.setMinPicUrl(countryChateauBeverageDto.getMinPicUrl());
//			orderDetailDto.setTitle(countryChateauBeverageDto.getTitle());
//			orderDetailDto.setPrice(countryChateauBeverageDto.getPrice());
//			orderDetailDto.setEnTitle(countryChateauBeverageDto.getEnTitle());
			orderDetailDto.setTotalPrice(orderDetailDto.getPrice() * orderDetailDto.getNum());

			orderDetailService.save(orderDetailDto);

			//如果购物项存在，则删除
			if(null != orderDetailDto.getCartItemId()) {
				cartItemService.deleteById(orderDetailDto.getCartItemId());
			} else {
				CartItemExample cartItemExample = new CartItemExample();
				cartItemExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code()).andBeverageIdEqualTo
					(orderDetailDto.getBeverageId());
				List<CartItemDto> cartItemDtoList = cartItemService.getList(cartItemExample);
				if(!CollectionUtils.isEmpty(cartItemDtoList)) {
					cartItemDtoList.forEach(cartItemDto -> {
						cartItemService.deleteById(cartItemDto.getId());
					});
				}
			}
		}
		/**
		 * 订单运费
		 */
		Integer postage = chateauPostage.values().stream().reduce((a, b) -> a + b).orElse(0);
		orderInfoDto.setTotalAmount(total);
		orderInfoDto.setPostage(postage);
		//3.更新订单信息
		this.update(orderInfoDto);
		//3.保存收货地址
		OrderDeliveryAddressMappingDto orderDeliveryAddressMapping = new OrderDeliveryAddressMappingDto();
		orderDeliveryAddressMapping.setAddressId(addressId);
		orderDeliveryAddressMapping.setOrderId(orderInfoDto.getId());
		orderDeliveryAddressMapping.setOrderNo(orderInfoDto.getOrderNo());

		orderDeliveryAddressMappingService.save(orderDeliveryAddressMapping);
		return orderInfoDto.getOrderNo();
	}

	@Override
	public OrderInfoDto getOrderByNo(String orderNo) {
		OrderInfoExample orderInfoExample = new OrderInfoExample();
		orderInfoExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code()).andOrderNoEqualTo(orderNo);
		List<OrderInfoDto> orderInfoDtoList = getList(orderInfoExample);
		if(!CollectionUtils.isEmpty(orderInfoDtoList)) {
			return orderInfoDtoList.get(0);
		}
		return null;
	}
}