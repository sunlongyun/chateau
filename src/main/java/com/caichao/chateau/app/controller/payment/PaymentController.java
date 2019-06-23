package com.caichao.chateau.app.controller.payment;

import com.caichao.chateau.app.controller.response.CCResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 支付管理controller
 * @AUTHOR 孙龙云
 * @date 2019-06-23 15:55
 */
@RequestMapping("payment")
@RestController
public class PaymentController {


	/**
	 * 支付
	 * @return
	 */
	@RequestMapping
	public CCResponse payOrder(String orderNo, Long orderId){
		return CCResponse.success();
	}
}
