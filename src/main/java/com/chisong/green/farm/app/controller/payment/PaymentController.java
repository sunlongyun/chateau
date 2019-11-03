package com.chisong.green.farm.app.controller.payment;

import com.chisong.green.farm.app.controller.response.CCResponse;
import com.chisong.green.farm.app.miniProgram.response.PrePayResponse;
import com.chisong.green.farm.app.service.PaymentService;
import com.chisong.green.farm.app.utils.IPUtil;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 支付管理controller
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-23 15:55
 */
@RequestMapping("payment")
@RestController
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	/**
	 * 支付
	 */
	@RequestMapping("payOrder")
	public CCResponse payOrder(String orderNo, Long orderId) {
		String clientIp = IPUtil.getIpAddr();
		PrePayResponse prePayResponse  = paymentService.createPayOrder(clientIp, orderNo, orderId);
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("orderNo", orderNo);
		dataMap.put("prePayId", prePayResponse.getPrepayId());
		return CCResponse.success(dataMap);
	}
}
