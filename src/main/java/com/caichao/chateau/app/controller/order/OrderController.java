package com.caichao.chateau.app.controller.order;

import com.caichao.chateau.app.constants.enums.OrderStatusEnum;
import com.caichao.chateau.app.constants.enums.Validity;
import com.caichao.chateau.app.controller.order.request.CreateOrderReq;
import com.caichao.chateau.app.controller.order.request.OrderDetailReq;
import com.caichao.chateau.app.controller.response.CCResponse;
import com.caichao.chateau.app.dto.CustomerDeliveryAddressDto;
import com.caichao.chateau.app.dto.CustomerInfoDto;
import com.caichao.chateau.app.dto.OrderDeliveryAddressMappingDto;
import com.caichao.chateau.app.dto.OrderDetailDto;
import com.caichao.chateau.app.dto.OrderInfoDto;
import com.caichao.chateau.app.example.OrderDeliveryAddressMappingExample;
import com.caichao.chateau.app.example.OrderDetailExample;
import com.caichao.chateau.app.example.OrderInfoExample;
import com.caichao.chateau.app.example.OrderInfoExample.Criteria;
import com.caichao.chateau.app.miniProgram.response.LoginResponse;
import com.caichao.chateau.app.miniProgram.response.PrePayResponse;
import com.caichao.chateau.app.service.CustomerDeliveryAddressService;
import com.caichao.chateau.app.service.CustomerInfoService;
import com.caichao.chateau.app.service.OrderDeliveryAddressMappingService;
import com.caichao.chateau.app.service.OrderDetailService;
import com.caichao.chateau.app.service.OrderInfoService;
import com.caichao.chateau.app.service.PaymentService;
import com.caichao.chateau.app.utils.CurrentUserUtils;
import com.caichao.chateau.app.utils.IPUtil;
import com.lianshang.generator.commons.PageInfo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
		OrderInfoExample orderInfoExample = new OrderInfoExample();
		Criteria criteria = orderInfoExample.createCriteria();
		criteria.andValidityEqualTo(Validity.AVAIL.code());
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
		orderInfoDto.setOrderDetailDtoList(orderDetailService.getList(orderDetailExample));
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

		List<OrderDeliveryAddressMappingDto> orderDeliveryAddressMappingDtoList =
			orderDeliveryAddressMappingService.getList
				(orderDeliveryAddressMappingExample);

		log.info("orderDeliveryAddressMappingDtoList:{}", orderDeliveryAddressMappingDtoList);

		if(!CollectionUtils.isEmpty(orderDeliveryAddressMappingDtoList)) {
			OrderDeliveryAddressMappingDto orderDeliveryAddressMappingDto = orderDeliveryAddressMappingDtoList
				.get(0);
			CustomerDeliveryAddressDto customerDeliveryAddressDto = customerDeliveryAddressService.getById
				(orderDeliveryAddressMappingDto
					.getAddressId());

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
		if(null == orderInfoDto){
			throw new RuntimeException("订单不存在");
		}
		if(!OrderStatusEnum.UN_PAY.code().equals(orderInfoDto.getStatus())){
			throw new RuntimeException("只有未支付订单才可以取消");
		}
		orderInfoDto.setStatus(OrderStatusEnum.CANCELED.code());
		orderInfoService.update(orderInfoDto);
		return CCResponse.success();
	}

	/**
	 * 删除订单
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/deleteOrder")
	public CCResponse deleteOrder(Long orderId){
		OrderInfoDto orderInfoDto = orderInfoService.getById(orderId);
		if(null == orderInfoDto){
			throw new RuntimeException("订单不存在");
		}
		if(OrderStatusEnum.DELIVERY.code().equals(orderInfoDto.getStatus())){
			throw new RuntimeException("付款未收货的订单不可以删除");
		}
		orderInfoService.deleteById(orderId);
		return CCResponse.success();
	}

	/**
	 * 确认收货
	 * @param orderId
	 * @return
	 */
	@RequestMapping("confirmReceiveOrder")
	public CCResponse confirmReceiveOrder(Long orderId){
		OrderInfoDto orderInfoDto = orderInfoService.getById(orderId);
		if(null == orderInfoDto){
			throw new RuntimeException("订单不存在");
		}
		if(!OrderStatusEnum.DELIVERY.code().equals(orderInfoDto.getStatus())){
			throw new RuntimeException("只有待收货的订单才可以确认收货");
		}
		orderInfoDto.setStatus(OrderStatusEnum.RECEIVED.code());
		orderInfoService.update(orderInfoDto);
		return CCResponse.success();
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
		LoginResponse loginResponse = CurrentUserUtils.get();
		CustomerInfoDto customerInfoDto = customerInfoService.getCustomerInfoDtoByOpenId(loginResponse.getOpenid());
		if(CollectionUtils.isEmpty(createOrderReq.getOrderDetailReqList())) {
			throw new RuntimeException("购物明细不能为空");
		}
		OrderInfoDto orderInfoDto = buildOdrerInfo(loginResponse, customerInfoDto);
		String orderNo = createOrder(orderInfoDto, createOrderReq.getOrderDetailReqList(),
			createOrderReq.getAddressId());
		String clientIp = IPUtil.getIpAddr();
		;
		PrePayResponse prePayResponse = paymentService.createPayOrder(clientIp, orderNo, orderInfoDto.getId());

		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("orderNo", orderNo);
		dataMap.put("prePayId", prePayResponse.getPrepayId());
		dataMap.put("orderId", orderInfoDto.getId());

		return CCResponse.success(dataMap);
	}

	/**
	 * 创建订单
	 */
	private String createOrder(OrderInfoDto orderInfoDto, List<OrderDetailReq> orderDetailReqList, Integer addressId) {

		List<OrderDetailDto> orderDetailDtoList = orderDetailReqList.stream().map(orderDetailReq -> {
			OrderDetailDto orderDetailDto = new OrderDetailDto();
			orderDetailDto.setBeverageId(orderDetailReq.getBeverageId());
			orderDetailDto.setOrderNo(orderInfoDto.getOrderNo());
			orderDetailDto.setNum(orderDetailReq.getNum());
			orderDetailDto.setCartItemId(orderDetailReq.getCartItemId());
			return orderDetailDto;
		}).collect(Collectors.toList());

		orderInfoDto.setOrderDetailDtoList(orderDetailDtoList);

		String orderNo = orderInfoService.createOrder(orderInfoDto, addressId);
		return orderNo;
	}

	/**
	 * 构建订单基本信息
	 */
	private OrderInfoDto buildOdrerInfo(LoginResponse loginResponse, CustomerInfoDto customerInfoDto) {
		String seq = String.format("%03d", (int) (Math.random() * 1000));
		String orderNo = loginResponse.getOpenid() + "+" + LocalDateTime.now().format(DateTimeFormatter.ofPattern
			("yyyyMMddHHmmss")) + seq;

		OrderInfoDto orderInfoDto = new OrderInfoDto();
		orderInfoDto.setOrderNo(orderNo);
		orderInfoDto.setCustomerId(customerInfoDto.getId());
		orderInfoDto.setCustomerName(customerInfoDto.getNickName());
		orderInfoDto.setMobile(customerInfoDto.getMobile());

		return orderInfoDto;
	}
}
