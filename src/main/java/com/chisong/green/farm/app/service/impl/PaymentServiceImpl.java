package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.constants.enums.Validity;
import com.chisong.green.farm.app.dto.OrderInfoDto;
import com.chisong.green.farm.app.dto.PaymentDto;
import com.chisong.green.farm.app.entity.Payment;
import com.chisong.green.farm.app.example.PaymentExample;
import com.chisong.green.farm.app.mapper.PaymentMapper;
import com.chisong.green.farm.app.miniProgram.request.PrePayRequest;
import com.chisong.green.farm.app.miniProgram.response.LoginResponse;
import com.chisong.green.farm.app.miniProgram.response.PrePayResponse;
import com.chisong.green.farm.app.miniProgram.service.WxPayService;
import com.chisong.green.farm.app.service.OrderInfoService;
import com.chisong.green.farm.app.service.PaymentService;
import com.chisong.green.farm.app.utils.AppUtils;
import com.chisong.green.farm.app.utils.CurrentUserUtils;
import com.lianshang.generator.commons.ServiceImpl;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment, PaymentDto> implements PaymentService {

	public static final String TRADE_TYPE = "JSAPI";
	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private WxPayService wxPayService;
	@Value("${notifyUrl}")
	private String notifyUrl;


	@Override
	public PrePayResponse createPayOrder(String clientIP, String orderNo, Long orderId) {

		OrderInfoDto orderInfoDto = null;
		if(null != orderId) {
			orderInfoDto = orderInfoService.getById(orderId);
		} else if(!StringUtils.isEmpty(orderNo)) {
			orderInfoDto = orderInfoService.getOrderByNo(orderNo);
		}

		if(null == orderInfoDto) {
			throw new RuntimeException("未查询到业务订单");
		}
		String seq = String.format("%03d", (int) (Math.random() * 1000));
		String payNo = "PY_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + seq;
		//1.发起支付
		PrePayResponse prePayResponse = getPrePatResponse(payNo, clientIP, orderInfoDto);
		//2.保存支付流水
		savePayment(orderInfoDto, payNo, prePayResponse);

		return prePayResponse;
	}

	@Override
	public PaymentDto getPaymentDto(String paymentNo) {
		PaymentExample paymentExample = new PaymentExample();
		paymentExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
		.andPayNoEqualTo(paymentNo);

		List<PaymentDto> paymentDtoList = getList(paymentExample);
		if(!CollectionUtils.isEmpty(paymentDtoList)){
			return paymentDtoList.get(0);
		}
		return null;
	}

	private void savePayment(OrderInfoDto orderInfoDto, String payNo, PrePayResponse prePayResponse) {
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setPayNo(payNo);
		paymentDto.setPayOrderNo(orderInfoDto.getOrderNo());
		paymentDto.setPrePayId(prePayResponse.getPrepayId());
		paymentDto.setAmount(Integer.parseInt( (orderInfoDto.getTotalAmount()+orderInfoDto.getPostage())+""));
		Long appInfoId = AppUtils.get();
		paymentDto.setAppInfoId(appInfoId);
		this.save(paymentDto);
	}

	/**
	 * 获取预支付结果
	 */
	private PrePayResponse getPrePatResponse(String payNo, String clientIP, OrderInfoDto orderInfoDto) {
		PrePayRequest prePayRequest = new PrePayRequest();

		prePayRequest.setOutTradeNo(payNo);
		long totalAmount = orderInfoDto.getTotalAmount() + orderInfoDto.getPostage();
		//获取当前用户的openId
		LoginResponse loginResponse = CurrentUserUtils.get();
		prePayRequest.setOpenid(loginResponse.getOpenid());
		prePayRequest.setSpbillCreateIp(clientIP);
		String title = "松松付款";
		prePayRequest.setBody(title);
		prePayRequest.setTradeType(TRADE_TYPE);
		prePayRequest.setTotalFee(totalAmount + "");
		prePayRequest.setNotifyUrl(notifyUrl);

		PrePayResponse prePayResponse = wxPayService.prePay(prePayRequest);
		return prePayResponse;
	}


}