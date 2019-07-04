package com.caichao.chateau.app.controller.payment;

import com.caichao.chateau.app.controller.response.CCResponse;
import com.caichao.chateau.app.miniProgram.response.PrePayResponse;
import com.caichao.chateau.app.service.PaymentService;
import com.caichao.chateau.app.utils.IPUtil;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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
		dataMap.put("prePayId", prePayResponse.getPrepayId());
		dataMap.put("packageStr", prePayResponse.getPackageStr());
		dataMap.put("timeStamp", prePayResponse.getTimeStamp());
		dataMap.put("signType", prePayResponse.getSignType());
		dataMap.put("sign", prePayResponse.getSign());
		dataMap.put("nonceStr", prePayResponse.getNonceStr());
		dataMap.put("appId", prePayResponse.getAppId());
		return CCResponse.success(dataMap);
	}
}
