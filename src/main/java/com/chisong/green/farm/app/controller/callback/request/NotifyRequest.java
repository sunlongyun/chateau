package com.chisong.green.farm.app.controller.callback.request;

import lombok.Data;

/**
 * 描述:
 * 回调结果通知
 * @AUTHOR 孙龙云
 * @date 2019-06-29 18:35
 */
@Data
public class NotifyRequest {

	/**
	 * 返回状态码
	 */
	private String return_code;
	/**
	 * 返回信息
	 */
	private String return_msg;
	/**
	 * appid
	 */
	private String appid;
	/**
	 * 商户号
	 */
	private String mch_id;
	/**
	 * 设备号
	 */
	private String device_info;
	/**
	 * 随机字符串
	 */
	private String nonce_str;
	/**
	 * 签名
	 */
	private String sign;
	/**
	 * 签名类型
	 */
	private String sign_type;
	/**
	 * 业务结果
	 */
	private String result_code;
	/**
	 * 错误代码
	 */
	private String err_code;
	/**
	 * 错误代码描述
	 */
	private String err_code_des;
	/**
	 * openid
	 */
	private String openid;
	/**
	 * 是否关注公众账号
	 */
	private String is_subscribe;
	/**
	 * 交易类型
	 */
	private String trade_type;
	/**
	 * 付款银行
	 */
	private String bank_type;
	/**
	 * 订单金额
	 */
	private Integer total_fee;
	/**\
	 * 应结订单总金额
	 */
	private Integer settlement_total_fee;
	/**
	 * 货币类型
	 */
	private String fee_type;
	/**
	 * 现金支付金额
	 */
	private String cash_fee;
	/**
	 * 微信支付订单号
	 */
	private String transaction_id;
	/**
	 * 商户订单号
	 */
	private String out_trade_no;
	/**
	 * 支付完成时间
	 */
	private String time_end;
}
