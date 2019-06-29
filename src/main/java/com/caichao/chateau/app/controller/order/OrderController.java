package com.caichao.chateau.app.controller.order;

import com.caichao.chateau.app.constants.enums.Validity;
import com.caichao.chateau.app.controller.order.request.OrderDetailReq;
import com.caichao.chateau.app.controller.response.CCResponse;
import com.caichao.chateau.app.dto.CustomerInfoDto;
import com.caichao.chateau.app.dto.OrderDetailDto;
import com.caichao.chateau.app.dto.OrderInfoDto;
import com.caichao.chateau.app.example.OrderDetailExample;
import com.caichao.chateau.app.example.OrderInfoExample;
import com.caichao.chateau.app.miniProgram.response.LoginResponse;
import com.caichao.chateau.app.service.CustomerInfoService;
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
import javax.servlet.http.HttpServletRequest;
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

	/**
	 * 历史订单列表
	 */
	@RequestMapping("list")
	public CCResponse list(Integer pageNo, Integer pageSize) {
		OrderInfoExample orderInfoExample = new OrderInfoExample();
		orderInfoExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code());

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
	public CCResponse orderDetail(Long orderId, String orderNo, HttpServletRequest httpServletRequest) {
		String ip = IPUtil.getIpAddr(httpServletRequest);
		log.info("客户端IP:{}", ip);
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
		if(null == orderInfoDto) {
			return CCResponse.fail("未查询到符合条件的订单");
		}

		buildOrdderDetail(orderInfoDto);
		return CCResponse.success(orderInfoDto);
	}


	/**
	 * 创建订单
	 */
	@RequestMapping("createOrder")
	public CCResponse createOrder(@RequestBody List<OrderDetailReq> orderDetailReqList,
		HttpServletRequest httpServletRequest) {
		LoginResponse loginResponse = CurrentUserUtils.get();
		CustomerInfoDto customerInfoDto = customerInfoService.getCustomerInfoDtoByOpenId(loginResponse.getOpenid());
		if(CollectionUtils.isEmpty(orderDetailReqList)) {
			throw new RuntimeException("购物明细不能为空");
		}
		OrderInfoDto orderInfoDto = buildOdrerInfo(loginResponse, customerInfoDto);
		String orderNo = createOrder(orderInfoDto, orderDetailReqList, loginResponse, customerInfoDto);
		String clientIp = IPUtil.getIpAddr(httpServletRequest);
		String prePayId = paymentService.createPayOrder(clientIp, orderNo, orderInfoDto.getId());

		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("orderNo", orderNo);
		dataMap.put("prePayId", prePayId);
		dataMap.put("orderId", orderInfoDto.getId());

		return CCResponse.success(dataMap);
	}

	/**
	 * 创建订单
	 */
	private String createOrder(OrderInfoDto orderInfoDto, List<OrderDetailReq> orderDetailReqList, LoginResponse
		loginResponse,
		CustomerInfoDto customerInfoDto) {

		List<OrderDetailDto> orderDetailDtoList = orderDetailReqList.stream().map(orderDetailReq -> {
			OrderDetailDto orderDetailDto = new OrderDetailDto();
			orderDetailDto.setBeverageId(orderDetailReq.getBeverageId());
			orderDetailDto.setOrderNo(orderInfoDto.getOrderNo());
			orderDetailDto.setNum(orderDetailReq.getNum());
			orderDetailDto.setCartItemId(orderDetailReq.getCartItemId());
			return orderDetailDto;
		}).collect(Collectors.toList());

		orderInfoDto.setOrderDetailDtoList(orderDetailDtoList);

		String orderNo = orderInfoService.createOrder(orderInfoDto);
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
