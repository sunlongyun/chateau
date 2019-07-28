package com.caichao.chateau.app.miniProgram.request;

import com.caichao.chateau.app.miniProgram.annotaion.MiniFiled;
import javax.validation.constraints.Min;
import lombok.Data;

/**
 * 描述:
 * 退款申请
 * @AUTHOR 孙龙云
 * @date 2019-07-28 16:32
 */
@Data
public class RefundApplyReq {

	/**\
	 * 微信订单号
	 */
	@MiniFiled("transaction_id")
	private String transactionId;

	/**
	 * 商户订单号
	 */
	@MiniFiled("out_trade_no")
	private String outTradeNo;

	/**
	 * 退款申请单号
	 */
	@MiniFiled("out_refund_no")
	private String outRefundNo;

	/**
	 * 订单金额
	 */
	@MiniFiled("total_fee")
	private Integer totalFee = 0;
	/**
	 * 退款申请金额
	 */
	@MiniFiled("refund_fee")
	private Integer refundFee;

	/**
	 * 退款结果通知
	 */
	@MiniFiled("notify_url")
	private String notifyUrl;
}
