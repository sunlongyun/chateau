package com.chisong.green.farm.app.miniProgram.response;

import com.chisong.green.farm.app.miniProgram.annotaion.MiniFiled;
import lombok.Data;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2020-05-03 10:48
 */
@Data
public class PayToPersonResponse extends ParentResponse {

	/**
	 * 商户订单号
	 */
	@MiniFiled("partner_trade_no")
	private String partnerTradeNo;
	/**
	 * 微信付款单号
	 */
	@MiniFiled("payment_no")
	private String paymentNo;
	/**
	 * 付款成功时间
	 */
	@MiniFiled("payment_time")
	private  String paymentTime;
}
