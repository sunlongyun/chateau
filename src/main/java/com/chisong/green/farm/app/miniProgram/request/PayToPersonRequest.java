package com.chisong.green.farm.app.miniProgram.request;

import com.chisong.green.farm.app.miniProgram.annotaion.MiniFiled;
import lombok.Data;

/**
 * 描述:
 * 企业付款给个人
 * @AUTHOR 孙龙云
 * @date 2020-05-03 10:43
 */
@Data
public class PayToPersonRequest {

	/**
	 * 商户订单号
	 */
	@MiniFiled("partner_trade_no")
	private String partnerTradeNo;
	@MiniFiled("mch_appid")
	private String merchantAppid;
	/**
	 * 商户号
	 */
	@MiniFiled("mchid")
	private String merchantId;
	/**
	 * 用户openId
	 */
	private String openid;

	/**
	 * 校验用户候选名字
	 * NO_CHECK：不校验真实姓名
	 * FORCE_CHECK：强校验真实姓名
	 */
	@MiniFiled("check_name")
	private String checkName ="NO_CHECK";

	/**
	 * 收款人姓名
	 */
	@MiniFiled("re_user_name")
	private String reUserName;

	/**
	 * 金额 ，单位：分
	 */
	private Integer amount;
	/**
	 * 企业付款备注
	 */
	private String desc;

	/**
	 * ip
	 */
	@MiniFiled("spbill_create_ip")
	private String ip;
}
