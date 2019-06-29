package com.caichao.chateau.app.miniProgram.request;

import com.caichao.chateau.app.miniProgram.annotaion.MiniFiled;
import javax.validation.constraints.Min;
import lombok.Data;

/**
 * 描述:
 * 支付订单查询
 * @AUTHOR 孙龙云
 * @date 2019-06-29 17:04
 */
@Data
public class PayOrderQuery {

	/**
	 * 微信订单号
	 */
	@MiniFiled("transaction_id")
	private String transactionId;
	/**
	 * 支付流水号
	 */
	@MiniFiled("out_trade_no")
	private String outTradeNo;
}
