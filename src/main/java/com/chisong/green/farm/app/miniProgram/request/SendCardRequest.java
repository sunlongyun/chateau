package com.chisong.green.farm.app.miniProgram.request;

import com.chisong.green.farm.app.miniProgram.annotaion.MiniFiled;
import lombok.Data;

/**
 * 描述:
 * 发送代金券请求
 * @AUTHOR 孙龙云
 * @date 2020-07-18 17:18
 */
@Data
public class SendCardRequest {

	/**
	 * 代金券批次
	 */
	@MiniFiled("coupon_stock_id")
	private String couponStockId;

	/**
	 * 流水号
	 */
	@MiniFiled("partner_trade_no")
	private  String partnerTradeNo;
	/**
	 * openId记录数
	 */
	@MiniFiled("openid_count")
	private Integer openidCount =1;

	/**
	 * Openid信息，用户在appid下的唯一标识
	 */
	private String openid;
}
