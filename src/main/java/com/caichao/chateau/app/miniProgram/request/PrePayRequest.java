package com.caichao.chateau.app.miniProgram.request;

import com.caichao.chateau.app.miniProgram.annotaion.MiniFiled;
import lombok.Data;

/**
 * 描述:
 * 预支付请求参数
 * @AUTHOR 孙龙云
 * @date 2019-06-26 7:32
 */
@Data
public class PrePayRequest {

	/**
	 * 商品描述，必填
	 */
	private String body;
	/**
	 * 商品详情
	 */
	private String detail;
	/**
	 * 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
	 */
	private String attach;
	/**
	 * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一。详见商户订单号
	 */
	@MiniFiled("out_trade_no")
	private String outTradeNo;
	/**
	 * 货币类型
	 */
	@MiniFiled("fee_type")
	private String feeType;
	/**
	 * 标价金额，必填
	 */
	@MiniFiled("total_fee")
	private String totalFee;
	/**
	 * 终端ip，必填
	 */
	@MiniFiled("spbill_create_ip")
	private String spbillCreateIp;
	/**
	 * 异步回调地址，支付结果通知地址，必填
	 */
	@MiniFiled("notify_url")
	private String notifyUrl;
	/**
	 * 交易类型，必填
	 */
	@MiniFiled("trade_type")
	private String tradeType;
	/**
	 * openId，trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。openid如何获取，可参考【获取openid】。
	 */
	private String openid;
}
