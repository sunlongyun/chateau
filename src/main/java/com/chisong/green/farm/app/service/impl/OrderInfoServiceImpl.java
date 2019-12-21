package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.constants.enums.Validity;
import com.chisong.green.farm.app.controller.order.request.OrderDetailReq;
import com.chisong.green.farm.app.controller.response.CCResponse;
import com.chisong.green.farm.app.dto.CartItemDto;
import com.chisong.green.farm.app.dto.CustomerDeliveryAddressDto;
import com.chisong.green.farm.app.dto.GoodsDto;
import com.chisong.green.farm.app.dto.OrderDeliveryAddressMappingDto;
import com.chisong.green.farm.app.dto.OrderDetailDto;
import com.chisong.green.farm.app.dto.OrderInfoDto;
import com.chisong.green.farm.app.dto.PostageTemplateDto;
import com.chisong.green.farm.app.entity.OrderInfo;
import com.chisong.green.farm.app.example.CartItemExample;
import com.chisong.green.farm.app.example.OrderDeliveryAddressMappingExample;
import com.chisong.green.farm.app.example.OrderDetailExample;
import com.chisong.green.farm.app.example.OrderInfoExample;
import com.chisong.green.farm.app.example.PostageTemplateExample;
import com.chisong.green.farm.app.mapper.OrderInfoMapper;
import com.chisong.green.farm.app.service.CartItemService;
import com.chisong.green.farm.app.service.CustomerDeliveryAddressService;
import com.chisong.green.farm.app.service.GoodsService;
import com.chisong.green.farm.app.service.OrderDeliveryAddressMappingService;
import com.chisong.green.farm.app.service.OrderDetailService;
import com.chisong.green.farm.app.service.OrderInfoService;
import com.chisong.green.farm.app.service.PostageTemplateService;
import com.chisong.green.farm.exception.BizException;
import com.lianshang.generator.commons.ServiceImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

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
	@Autowired
	private PostageTemplateService postageTemplateService;
	@Override
	@Transactional
	public String createOrder(OrderInfoDto orderInfoDto, Integer addressId, List<OrderDetailReq> orderDetailReqList) {
		//1.添加订单基本信息
		save(orderInfoDto);
		//2.添加订单明细信息
		List<OrderDetailDto> orderDetailDtoList = orderInfoDto.getOrderDetailDtoList();
		if(CollectionUtils.isEmpty(orderDetailDtoList)) {
			throw new RuntimeException("订单明细不能为空");
		}
		long total = 0l;

		//每个庄园，Postage = new HashMap<>();
		for(OrderDetailDto orderDetailDto : orderDetailDtoList) {
			log.info("orderDetailDto:{}", orderDetailDto);
			if(null == orderDetailDto) {
				continue;
			}

			orderDetailDto.setOrderId(orderInfoDto.getId());

			GoodsDto goodsDto = goodsService.getById(orderDetailDto.getGoodsId());
			orderDetailDto.setTitle(goodsDto.getTitle());
			orderDetailDto.setPrice(goodsDto.getPrice());
			orderDetailDto.setTotalPrice(orderDetailDto.getPrice() * orderDetailDto.getNum());
			total += orderDetailDto.getTotalPrice();
			goodsService.decreaseStock(orderDetailDto.getNum(), goodsDto.getId());

			orderDetailService.save(orderDetailDto);

			//如果购物项存在，则删除
			if(null != orderDetailDto.getCartItemId()) {
				cartItemService.deleteById(orderDetailDto.getCartItemId());
			} else {
				CartItemExample cartItemExample = new CartItemExample();
				cartItemExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code()).andBeverageIdEqualTo
					(orderDetailDto.getGoodsId());
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
		long postage = computePostage(orderDetailReqList);
		log.info("postage==={}", postage);
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

	private long getTotalPostFee(List<OrderDetailReq> orderDetailReqList) {
		log.info("orderDetailReqList:{}", orderDetailReqList);
		return (long) orderDetailReqList.stream().map(orderDetailReq->{
			Long goodsId = orderDetailReq.getGoodsId();
			//查询运费模板
			PostageTemplateExample postageTemplateExample = new PostageTemplateExample();
			postageTemplateExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
				.andGoodsIdEqualTo(goodsId).andProvincesLike("%"+orderDetailReq.getProvince().trim()+"%");
			Optional<PostageTemplateDto> postageTemplateDtoOptional =
				postageTemplateService.getList(postageTemplateExample).stream().findFirst();
			log.info("postageTemplateDtoOptional:{}", postageTemplateDtoOptional.get());
			if(!postageTemplateDtoOptional.isPresent()){
				throw new RuntimeException("没有找到合适的运费模板");
			}

			PostageTemplateDto postageTemplateDto = 	postageTemplateDtoOptional.get();
			if((null != postageTemplateDto.getFreeNum() && orderDetailReq.getNum() > postageTemplateDto.getFreeNum())
				|| (null !=postageTemplateDto.getFreeTotalAmount()
					&& orderDetailReq.getPrice() * orderDetailReq.getNum() > postageTemplateDto.getFreeTotalAmount())){
				log.info("包邮--------------");
				return  0;
			}
			return orderDetailReq.getNum() * postageTemplateDtoOptional.get().getAmount();
		}).reduce(0, (a, b) -> a+b);
	}
	@Override
	public long computePostage(List<OrderDetailReq> orderDetailReqList) {
		return getTotalPostFee(orderDetailReqList);
	}


}