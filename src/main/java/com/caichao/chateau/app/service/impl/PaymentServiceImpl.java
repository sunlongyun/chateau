package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.dto.OrderDeliveryAddressMappingDto;
import com.caichao.chateau.app.dto.OrderInfoDto;
import com.caichao.chateau.app.entity.OrderDeliveryAddressMapping;
import com.caichao.chateau.app.entity.Payment;
import com.caichao.chateau.app.dto.PaymentDto;
import com.caichao.chateau.app.mapper.PaymentMapper;
import com.caichao.chateau.app.miniProgram.request.PrePayRequest;
import com.caichao.chateau.app.miniProgram.response.LoginResponse;
import com.caichao.chateau.app.miniProgram.response.PrePayResponse;
import com.caichao.chateau.app.miniProgram.service.WxPayService;
import com.caichao.chateau.app.service.OrderDeliveryAddressMappingService;
import com.caichao.chateau.app.service.OrderInfoService;
import com.caichao.chateau.app.service.PaymentService;

import com.caichao.chateau.app.utils.CurrentUserUtils;
import com.lianshang.generator.commons.ServiceImpl;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-06-15
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper,Payment, PaymentDto> implements PaymentService {

	public static final String TRADE_TYPE = "JSAPI";
	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private WxPayService wxPayService;
	@Value("${notifyUrl}")
	private String notifyUrl;
	@Autowired
	private OrderDeliveryAddressMappingService orderDeliveryAddressMappingService;
	@Override
	public String createPayOrder(String clientIP, String orderNo, Long orderId) {
		OrderInfoDto orderInfoDto = null;
		if(null != orderId){
			orderInfoDto = orderInfoService.getById(orderId);
		}else if(StringUtils.isEmpty(orderNo)){
			orderInfoDto = orderInfoService.getOrderByNo(orderNo);
		}

		if(null == orderInfoDto){
			throw  new RuntimeException("未查询到业务订单");
		}
		String seq = String.format("%03d", (int) (Math.random() * 1000));
		String payNo = "PY_"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))+seq;
		//1.发起支付
		PrePayResponse prePayResponse =	getPrePatResponse(payNo, clientIP, orderInfoDto);
		//2.保存支付流水
		savePayment(orderInfoDto, payNo, prePayResponse);


		return prePayResponse.getPrepayId();
	}

	private void savePayment(OrderInfoDto orderInfoDto, String payNo, PrePayResponse prePayResponse) {
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setPayNo(payNo);
		paymentDto.setPayOrderNo(orderInfoDto.getOrderNo());
		paymentDto.setPrePayId(prePayResponse.getPrepayId());

		this.save(paymentDto);
	}

	/**
	 * 获取预支付结果
	 * @param clientIP
	 * @param orderInfoDto
	 * @return
	 */
	private PrePayResponse getPrePatResponse(String payNo, String clientIP, OrderInfoDto orderInfoDto) {
		PrePayRequest prePayRequest = new PrePayRequest();

		prePayRequest.setOutTradeNo(payNo);
		long totalAmount = orderInfoDto.getTotalAmount();
		//获取当前用户的openId
		LoginResponse loginResponse = CurrentUserUtils.get();
		prePayRequest.setOpenid(loginResponse.getOpenid());
		prePayRequest.setSpbillCreateIp(clientIP);
		String title = orderInfoDto.getCustomerName()+"支付"+(totalAmount/10)+"元";
		prePayRequest.setBody(title);
		prePayRequest.setTradeType(TRADE_TYPE);
		prePayRequest.setTotalFee(totalAmount+"");
		prePayRequest.setNotifyUrl(notifyUrl);

		PrePayResponse prePayResponse = wxPayService.prePay(prePayRequest);
		return prePayResponse;
	}
}