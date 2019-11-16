package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.constants.enums.Validity;
import com.chisong.green.farm.app.dto.CartItemDto;
import com.chisong.green.farm.app.dto.CustomerDeliveryAddressDto;
import com.chisong.green.farm.app.dto.GoodsDto;
import com.chisong.green.farm.app.dto.OrderDeliveryAddressMappingDto;
import com.chisong.green.farm.app.dto.OrderDetailDto;
import com.chisong.green.farm.app.dto.OrderInfoDto;
import com.chisong.green.farm.app.entity.OrderInfo;
import com.chisong.green.farm.app.example.CartItemExample;
import com.chisong.green.farm.app.example.OrderDeliveryAddressMappingExample;
import com.chisong.green.farm.app.example.OrderDetailExample;
import com.chisong.green.farm.app.example.OrderInfoExample;
import com.chisong.green.farm.app.mapper.OrderInfoMapper;
import com.chisong.green.farm.app.service.CartItemService;
import com.chisong.green.farm.app.service.CustomerDeliveryAddressService;
import com.chisong.green.farm.app.service.GoodsService;
import com.chisong.green.farm.app.service.OrderDeliveryAddressMappingService;
import com.chisong.green.farm.app.service.OrderDetailService;
import com.chisong.green.farm.app.service.OrderInfoService;
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
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private CustomerDeliveryAddressService customerDeliveryAddressService;

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

			GoodsDto goodsDto = goodsService.getById(orderDetailDto.getBeverageId());
			orderDetailDto.setTitle(goodsDto.getTitle());
			orderDetailDto.setPrice(goodsDto.getPrice());
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
		CustomerDeliveryAddressDto customerDeliveryAddressDto = customerDeliveryAddressService.getById(addressId);
		OrderDeliveryAddressMappingDto orderDeliveryAddressMapping = new OrderDeliveryAddressMappingDto();
		orderDeliveryAddressMapping.setAddressId(addressId);
		orderDeliveryAddressMapping.setOrderId(orderInfoDto.getId());
		orderDeliveryAddressMapping.setOrderNo(orderInfoDto.getOrderNo());
		orderDeliveryAddressMapping.setAddress(customerDeliveryAddressDto.getDetaiAddress());
		orderDeliveryAddressMappingService.save(orderDeliveryAddressMapping);
		return orderInfoDto.getOrderNo();
	}

	@Override
	public OrderInfoDto getOrderByNo(String orderNo) {
		OrderInfoDto orderInfoDto = getOrderInfoDto(orderNo);
		//构建订单详情列表
		buildOrderDetails(orderInfoDto);
	    //构建地址
		buildAddress(orderInfoDto);

		return orderInfoDto;
	}

	/**
	 * 订单基本信息
	 * @param orderNo
	 * @return
	 */
	private OrderInfoDto getOrderInfoDto(String orderNo) {
		OrderInfoDto orderInfoDto = null;
		OrderInfoExample orderInfoExample = new OrderInfoExample();
		orderInfoExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code()).andOrderNoEqualTo(orderNo);
		List<OrderInfoDto> orderInfoDtoList = getList(orderInfoExample);
		if(!CollectionUtils.isEmpty(orderInfoDtoList)) {
			orderInfoDto = orderInfoDtoList.get(0);
		}
		return orderInfoDto;
	}

	private void buildOrderDetails(OrderInfoDto orderInfoDto) {
		//查询订单详情
		OrderDetailExample orderDetailExample = new OrderDetailExample();
		orderDetailExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
			.andOrderNoEqualTo(orderInfoDto.getOrderNo()).andOrderIdEqualTo(orderInfoDto.getId());

		List<OrderDetailDto> orderDetailDtos = orderDetailService.getList(orderDetailExample);
		orderInfoDto.setOrderDetailDtoList(orderDetailDtos);
	}

	private void buildAddress(OrderInfoDto orderInfoDto) {
		//查询收货地址
		OrderDeliveryAddressMappingExample orderDeliveryAddressMappingExample =
			new OrderDeliveryAddressMappingExample();
		orderDeliveryAddressMappingExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
			.andOrderIdEqualTo(orderInfoDto.getId());
		OrderDeliveryAddressMappingDto orderDeliveryAddressMappingDto =
			orderDeliveryAddressMappingService.getList(orderDeliveryAddressMappingExample).stream().findFirst().get();
		orderInfoDto.setOrderDeliveryAddressMappingDto(orderDeliveryAddressMappingDto);
		orderInfoDto.setAddress(orderDeliveryAddressMappingDto.getAddress());
	}

	@Override
	public OrderInfoDto getOrderById(Long id) {
		OrderInfoDto orderInfoDto = getById(id);
		return getOrderByNo(orderInfoDto.getOrderNo());
	}


}