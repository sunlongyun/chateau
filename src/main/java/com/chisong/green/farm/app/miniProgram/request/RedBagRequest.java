package com.chisong.green.farm.app.miniProgram.request;

import com.chisong.green.farm.app.miniProgram.annotaion.MiniFiled;
import javax.validation.constraints.Min;
import lombok.Data;

/**
 * 描述:
 * 发送红包请求
 * @AUTHOR 孙龙云
 * @date 2019-07-28 16:32
 */
@Data
public class RedBagRequest {

	/**
	 * 商户订单号
	 */
	@MiniFiled("mch_billno")
	private String outTradeNo;

	/**
	 * 红包提供者的商户名称
	 */
	@MiniFiled("send_name")
	private String sendName;
	/**
	 * 红包类型
	 * NORMAL-普通红包；GROUP-裂变红包(可分享红包给好友，无关注公众号能力)。
	 */
	@MiniFiled("hb_type")
	private String hbType;

	/**
	 * 红包总金额
	 */
	@MiniFiled("total_amount")
	private Integer totalAmount;
	/**
	 * 红包发送人数
	 */
	@MiniFiled("total_num")
	private Integer totalNum ;
	/**
	 * 红包金额设置
	 */
	@MiniFiled("amt_type")
	private String amtType;
	/**
	 * 祝福语
	 */
	@MiniFiled("wishing")
	private String wishing;
	/**
	 * 红包名称
	 */
	@MiniFiled("act_name")
	private String actName;

	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 授权商户号
	 */
	@MiniFiled("auth_mchid")
	private String authMchid;

	/**
	 * 授权商户APPID
	 */
	@MiniFiled("auth_appid")
	private String authAppid;

	/**
	 * 风控设置
	 */
	@MiniFiled("risk_cntl")
	private String riskCntl;
}
