package com.chisong.green.farm.app.service;

import com.chisong.green.farm.app.miniProgram.response.PrePayResponse;
import com.lianshang.generator.commons.IService;
import com.chisong.green.farm.app.dto.PaymentDto;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-06-15
 */
public interface PaymentService extends IService<PaymentDto> {

	/**
	 * 根据订单号或者订单id创建预支付流水
	 * @param orderNo
	 * @param orderId
	 */
	public PrePayResponse createPayOrder(String clientIP, String orderNo, Long orderId);

	/**
	 * 根据流水号查询
	 * @param paymentNo
	 * @return
	 */
	public PaymentDto getPaymentDto(String paymentNo);

}
