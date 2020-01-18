package com.chisong.green.farm.app.controller.order;

import com.chisong.green.farm.app.constants.enums.OrderStatusEnum;
import com.chisong.green.farm.app.constants.enums.Validity;
import com.chisong.green.farm.app.controller.order.request.CreateOrderReq;
import com.chisong.green.farm.app.controller.order.request.OrderDetailReq;
import com.chisong.green.farm.app.controller.response.CCResponse;
import com.chisong.green.farm.app.dto.CustomerDeliveryAddressDto;
import com.chisong.green.farm.app.dto.GoodsSpecsDto;
import com.chisong.green.farm.app.example.OrderDeliveryAddressMappingExample;
import com.chisong.green.farm.app.example.OrderDetailExample;
import com.chisong.green.farm.app.example.OrderInfoExample;
import com.chisong.green.farm.app.example.OrderInfoExample.Criteria;
import com.chisong.green.farm.app.miniProgram.response.LoginResponse;
import com.chisong.green.farm.app.miniProgram.response.PrePayResponse;
import com.chisong.green.farm.app.service.GoodsSpecsService;
import com.chisong.green.farm.app.utils.CurrentUserUtils;
import com.chisong.green.farm.app.utils.IPUtil;
import com.chisong.green.farm.app.dto.CustomerInfoDto;
import com.chisong.green.farm.app.dto.GoodsDto;
import com.chisong.green.farm.app.dto.OrderDeliveryAddressMappingDto;
import com.chisong.green.farm.app.dto.OrderDetailDto;
import com.chisong.green.farm.app.dto.OrderInfoDto;
import com.chisong.green.farm.app.dto.SupplierDto;
import com.chisong.green.farm.app.service.CustomerDeliveryAddressService;
import com.chisong.green.farm.app.service.CustomerInfoService;
import com.chisong.green.farm.app.service.GoodsService;
import com.chisong.green.farm.app.service.OrderDeliveryAddressMappingService;
import com.chisong.green.farm.app.service.OrderDetailService;
import com.chisong.green.farm.app.service.OrderInfoService;
import com.chisong.green.farm.app.service.PaymentService;
import com.chisong.green.farm.app.service.SupplierService;
import com.lianshang.generator.commons.PageInfo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 订单管理controller
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-23 10:31
 */
@RequestMapping("order")
@RestController
@Slf4j
public class OrderController {

	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private CustomerInfoService customerInfoService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private OrderDeliveryAddressMappingService orderDeliveryAddressMappingService;
    @Autowired
	private GoodsService goodsService;

	@Autowired
	private SupplierService supplierService;
	@Autowired
	private GoodsSpecsService goodsSpecsService;
	@Autowired
	private CustomerDeliveryAddressService customerDeliveryAddressService;
	/**
	 * 查询订单状态
	 */
	@RequestMapping("getStatus")
	public CCResponse getStatus(Long orderId, String orderNo) {
		OrderInfoDto orderInfoDto = getOrderInfoDto(orderId, orderNo);
		if(null == orderInfoDto) {
			return CCResponse.fail("未查询到符合条件的订单");
		}
		Integer status = orderInfoDto.getStatus();
		String statusName = OrderStatusEnum.getValue(status);
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("status", status);
		dataMap.put("statusName", statusName);

		return CCResponse.success(dataMap);
	}

	/**
	 * 历史订单列表
	 */
	@RequestMapping("list")
	public CCResponse list(Integer pageNo, Integer pageSize, Integer status) {
		LoginResponse loginResponse = CurrentUserUtils.get();
		CustomerInfoDto customerInfoDto = customerInfoService.getCustomerInfoDtoByOpenId(loginResponse.getOpenid());

		OrderInfoExample orderInfoExample = new OrderInfoExample();
		Criteria criteria = orderInfoExample.createCriteria();
		criteria.andValidityEqualTo(Validity.AVAIL.code()).andCustomerIdEqualTo(customerInfoDto.getId())
		.andStatusNotEqualTo(OrderStatusEnum.DELETED.code());
		if(null != status) {
			criteria.andStatusEqualTo(status);
		}
		orderInfoExample.setOrderByClause("id desc");
		PageInfo<OrderInfoDto> pageInfo = orderInfoService.getPageInfo(pageNo, pageSize, orderInfoExample);
		pageInfo.getDataList().forEach(orderInfoDto -> {
			buildOrdderDetail(orderInfoDto);

		});

		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("pageInfo", pageInfo);
		return CCResponse.success(dataMap);
	}

	private void buildOrdderDetail(OrderInfoDto orderInfoDto) {
		OrderDetailExample orderDetailExample = new OrderDetailExample();
		orderDetailExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code()).andOrderIdEqualTo
			(orderInfoDto.getId());
		List<OrderDetailDto> orderDetailDtoList = orderDetailService.getList(orderDetailExample);
		if(null != orderDetailDtoList) {
			for(OrderDetailDto orderDetailDto : orderDetailDtoList) {
				Long beverageId = orderDetailDto.getGoodsId();
				GoodsDto goodsDto = goodsService.getById
					(beverageId);
				Integer supplierId = goodsDto.getSupplierId();

				SupplierDto supplierDto = supplierService.getById(supplierId);
				log.info("supplierDto:{}", supplierDto);
				orderDetailDto.setSupplierId(supplierId);
				orderDetailDto.setSupplierAddress(supplierDto.getAddress());

				orderDetailDto.setMinPicUrl(goodsDto.getMinPicUrl());
			}
		}
		orderInfoDto.setOrderDetailDtoList(orderDetailDtoList);
	}

	/**
	 * 订单详情
	 */
	@RequestMapping("detail")
	public CCResponse orderDetail(Long orderId, String orderNo) {

		OrderInfoDto orderInfoDto = getOrderInfoDto(orderId, orderNo);
		if(null == orderInfoDto) {
			return CCResponse.fail("未查询到符合条件的订单");
		}

		buildOrdderDetail(orderInfoDto);
		//收货地址
		OrderDeliveryAddressMappingExample orderDeliveryAddressMappingExample = new
			OrderDeliveryAddressMappingExample();
		orderDeliveryAddressMappingExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
			.andOrderIdEqualTo(orderInfoDto.getId());
		orderDeliveryAddressMappingExample.setOrderByClause("id desc");

		List<OrderDeliveryAddressMappingDto> orderDeliveryAddressMappingDtoList =
			orderDeliveryAddressMappingService.getList
				(orderDeliveryAddressMappingExample);

		log.info("orderDeliveryAddressMappingDtoList:{}", orderDeliveryAddressMappingDtoList);


		if(!CollectionUtils.isEmpty(orderDeliveryAddressMappingDtoList)) {
			OrderDeliveryAddressMappingDto orderDeliveryAddressMappingDto = orderDeliveryAddressMappingDtoList
				.get(0);

			orderInfoDto.setOrderDeliveryAddressMappingDto(orderDeliveryAddressMappingDto);

			CustomerDeliveryAddressDto customerDeliveryAddressDto =
				customerDeliveryAddressService.getById(orderDeliveryAddressMappingDto.getAddressId());

			orderInfoDto.setAddress(customerDeliveryAddressDto);
		}

		return CCResponse.success(orderInfoDto);
	}

	/**
	 * 查询订单基本信息
	 */
	private OrderInfoDto getOrderInfoDto(Long orderId, String orderNo) {
		OrderInfoDto orderInfoDto = null;
		if(null != orderId) {
			orderInfoDto = orderInfoService.getById(orderId);
		} else if(!StringUtils.isEmpty(orderNo)) {
			OrderInfoExample orderInfoExample = new OrderInfoExample();
			orderInfoExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code()).andOrderNoEqualTo(orderNo);
			List<OrderInfoDto> orderInfoDtoList = orderInfoService.getList(orderInfoExample);
			if(!CollectionUtils.isEmpty(orderInfoDtoList)) {
				orderInfoDto = orderInfoDtoList.get(0);
			}
		}
		return orderInfoDto;
	}

	/**
	 * 取消订单
	 */
	@RequestMapping("cancelOrder")
	public CCResponse cancelOrder(Long orderId) {
		OrderInfoDto orderInfoDto = orderInfoService.getById(orderId);
		if(null == orderInfoDto) {
			throw new RuntimeException("订单不存在");
		}
		if(!OrderStatusEnum.UN_PAY.code().equals(orderInfoDto.getStatus())) {
			throw new RuntimeException("只有未支付订单才可以取消");
		}
		orderInfoDto.setStatus(OrderStatusEnum.CANCELED.code());
		orderInfoService.update(orderInfoDto);
		return CCResponse.success();
	}

	/**
	 * 删除订单
	 */
	@RequestMapping("/deleteOrder")
	public CCResponse deleteOrder(Long orderId) {
		log.info("要删除的订单号:{}", orderId);
		OrderInfoDto orderInfoDto = orderInfoService.getById(orderId);
		if(null == orderInfoDto) {
			throw new RuntimeException("订单不存在");
		}
		if(OrderStatusEnum.DELIVERY.code().equals(orderInfoDto.getStatus())) {
			throw new RuntimeException("付款未收货的订单不可以删除");
		}
		orderInfoDto.setStatus(OrderStatusEnum.DELETED.code());
		orderInfoService.update(orderInfoDto);

		return CCResponse.success();
	}

	/**
	 * 确认收货
	 */
	@RequestMapping("confirmReceiveOrder")
	public CCResponse confirmReceiveOrder(Long orderId) {
		OrderInfoDto orderInfoDto = orderInfoService.getById(orderId);
		if(null == orderInfoDto) {
			throw new RuntimeException("订单不存在");
		}
		if(!OrderStatusEnum.DELIVERY.code().equals(orderInfoDto.getStatus())) {
			throw new RuntimeException("只有待收货的订单才可以确认收货");
		}
		orderInfoDto.setStatus(OrderStatusEnum.RECEIVED.code());
		orderInfoService.update(orderInfoDto);
		return CCResponse.success();
	}

	/**
	 * 计算运费
	 */
	@RequestMapping("computePostage")
	public CCResponse computePostage(@RequestBody List<OrderDetailDto> orderDetailReqList) {

		long total = orderInfoService.computePostage(orderDetailReqList);

		Map<String, Object> postageMap = new HashMap<>();
		postageMap.put("postage", total);
		postageMap.put("deliveryList", "[]");

		return CCResponse.success(postageMap);
	}

	/**
	 * 创建订单
	 */
	@RequestMapping("createOrder")
	public CCResponse createOrder(@RequestBody CreateOrderReq createOrderReq) {
		if(null == createOrderReq || CollectionUtils.isEmpty(createOrderReq.getOrderDetailReqList())
			|| null == createOrderReq.getAddressId()) {
			throw new RuntimeException("订单明细以及收货地址都不能为空");
		}

		//如果购物清单的商品来自不同的供应商，则返回提示分别下单
		if(checkSupplier(createOrderReq)) {
			return CCResponse.fail("您购买的商品来自不同的供应商，建议分别下单购买");
		}

		LoginResponse loginResponse = CurrentUserUtils.get();
		CustomerInfoDto customerInfoDto = customerInfoService.getCustomerInfoDtoByOpenId(loginResponse.getOpenid());
		if(CollectionUtils.isEmpty(createOrderReq.getOrderDetailReqList())) {
			throw new RuntimeException("购物明细不能为空");
		}
		OrderInfoDto orderInfoDto = buildOrderInfo(customerInfoDto);
		String orderNo = createOrder(orderInfoDto, createOrderReq.getOrderDetailReqList(),
			createOrderReq.getAddressId());


		String clientIp = IPUtil.getIpAddr();
		PrePayResponse prePayResponse = paymentService.createPayOrder(clientIp, orderNo, orderInfoDto.getId());

		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("orderNo", orderNo);
		dataMap.put("prePayId", prePayResponse.getPrepayId());
		dataMap.put("orderId", orderInfoDto.getId());

		return CCResponse.success(dataMap);
	}

	private boolean checkSupplier(@RequestBody CreateOrderReq createOrderReq) {
		long supplierCount =	createOrderReq.getOrderDetailReqList().stream().map(orderDetailReq -> {
			return goodsService.getById(orderDetailReq.getGoodsId()).getSupplierId();
		}).distinct().count();
		if(supplierCount >1){
			return true;
		}
		return false;
	}

	/**
	 * 创建订单
	 */
	private String createOrder(OrderInfoDto orderInfoDto, List<OrderDetailReq> orderDetailReqList, Integer addressId) {


		List<OrderDetailDto> orderDetailDtoList = getOrderDetailDtos(orderInfoDto, orderDetailReqList);

		orderInfoDto.setOrderDetailDtoList(orderDetailDtoList);
		String orderNo = orderInfoService.createOrder(orderInfoDto, addressId);
		return orderNo;
	}

	/**
	 * 构建订单明细
	 * @param orderInfoDto
	 * @param orderDetailReqList
	 * @return
	 */
	private List<OrderDetailDto> getOrderDetailDtos(OrderInfoDto orderInfoDto,
		List<OrderDetailReq> orderDetailReqList) {
		return orderDetailReqList.stream().map(orderDetailReq -> {
				OrderDetailDto orderDetailDto = new OrderDetailDto();
				orderDetailDto.setGoodsId(orderDetailReq.getGoodsId());
				orderDetailDto.setOrderNo(orderInfoDto.getOrderNo());
				orderDetailDto.setNum(orderDetailReq.getNum());
				orderDetailDto.setCartItemId(orderDetailReq.getCartItemId());
				orderDetailDto.setSpecsId(orderDetailReq.getSpecsId());
				orderDetailDto.setSpecsName(orderDetailDto.getSpecsName());

				GoodsDto goodsDto = goodsService.getById(orderDetailReq
					.getGoodsId());

			if(null != goodsDto.getPromoteStartTime()
				&& null != goodsDto.getPromoteEndTime()
				&& goodsDto.getPromoteStartTime().before(new Date())
				&& goodsDto.getPromoteEndTime().after(new Date())){
				goodsDto.setPromote(true);
			}


				orderDetailDto.setTitle(goodsDto.getTitle());
				if(goodsDto.getUniformSpecs() == 1 && null == orderDetailDto.getSpecsId()) {//统一规格商品
					orderDetailDto.setPrice(goodsDto.getPrice());
					if(goodsDto.isPromote() && null != goodsDto.getPromotePrice()){
						orderDetailDto.setPrice(goodsDto.getPromotePrice());
					}
					orderDetailDto.setSpecsName(goodsDto.getSpecs());
				}else{
					GoodsSpecsDto goodsSpecsDto = goodsSpecsService.getById(orderDetailDto.getSpecsId());
					log.info("goodsId:{},goodsSpecsDto:{}",goodsDto.getId(), goodsSpecsDto);
					orderDetailDto.setPrice(Long.valueOf(goodsSpecsDto.getPrice()+""));
					if(goodsDto.isPromote() && null  != goodsSpecsDto.getPromotionPrice()){
						orderDetailDto.setPrice(Long.valueOf(goodsSpecsDto.getPromotionPrice()+""));
					}
					orderDetailDto.setSpecsName(goodsSpecsDto.getName());
				}
			orderDetailDto.setTotalPrice(orderDetailDto.getPrice() * orderDetailDto.getNum());
				return orderDetailDto;
			}).collect(Collectors.toList());
	}

	/**
	 * 构建订单基本信息
	 */
	private OrderInfoDto buildOrderInfo(CustomerInfoDto customerInfoDto) {
		String seq = String.format("%04d", (int) (Math.random() * 10000));
		String customerId = String.format("%04d", customerInfoDto.getId());
		String orderNo = "CC" + customerId + "_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern
			("yyyyMMddHHmmss")) + seq;

		OrderInfoDto orderInfoDto = new OrderInfoDto();
		orderInfoDto.setOrderNo(orderNo);
		orderInfoDto.setCustomerId(customerInfoDto.getId());
		orderInfoDto.setCustomerName(customerInfoDto.getNickName());
		orderInfoDto.setMobile(customerInfoDto.getMobile());

		return orderInfoDto;
	}
}
