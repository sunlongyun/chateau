package com.caichao.chateau.app.miniProgram.response;

import com.caichao.chateau.app.miniProgram.annotaion.MiniFiled;
import lombok.Data;

/**
 * 描述:
 * 支付结果查询
 * @AUTHOR 孙龙云
 * @date 2019-06-28 22:28
 */
@Data
public class PayOrderQueryResultResponse extends ParentResponse {

	/**
	 * 预支付流水
	 */
	@MiniFiled("prepay_id")
	private String prepayId;
	/**
	 * 支付二维码连接  trade_type=NATIVE时有返回，此url用于生成支付二维码，然后提供给用户进行扫码支付
	 */
	@MiniFiled("code_url")
	private String codeUrl;
	/**
	 * 交易状态
	 */
	@MiniFiled("trade_state")
	private String tradeState;
	/**
	 * 付款银行
	 */
	@MiniFiled("bank_type")
	private String bankType;
	/**
	 * 付费金额
	 */
	@MiniFiled("total_fee")
	private Integer totalFee;
	/**
	 * 微信支付订单号
	 */
	@MiniFiled("transaction_id")
	private String transactionId;
	/**
	 * 商户订单号
	 */
	@MiniFiled("out_trade_no")
	private String outTradeNo;
	/**
	 * 支付完成时间
	 */
	@MiniFiled("time_end")
	private String timeEnd;
	/**
	 * 交易状态描述
	 */
	private String tradeStateDesc;

}
