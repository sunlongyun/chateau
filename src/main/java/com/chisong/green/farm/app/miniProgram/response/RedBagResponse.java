package com.chisong.green.farm.app.miniProgram.response;

import com.chisong.green.farm.app.miniProgram.annotaion.MiniFiled;
import javax.validation.constraints.Min;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2020-05-03 10:18
 */
public class RedBagResponse extends  ParentResponse {

	/**
	 * 商户订单号
	 */
	@MiniFiled("mch_billno")
	private String outTradeNo;
	/**
	 * 商户号
	 */
	@MiniFiled("mch_id")
	private String mchId;
	/**
	 * 公众账号appid
	 */
	@MiniFiled("wxappid")
	private String wxappId;
	/**
	 * 总金额
	 */
	@MiniFiled("total_amount")
	private Integer totalAmount;

	/**
	 * 凭证
	 */
	@MiniFiled("sp_ticket")
	private String ticket;

	/**
	 * 红包单号
	 */
	@MiniFiled("detail_id")
	private String detailId;

	/**
	 * 红包发送时间
	 */
	@MiniFiled("send_time")
	private String sendTime;
}
