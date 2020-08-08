package com.chisong.green.farm.app.miniProgram.response;

import com.chisong.green.farm.app.miniProgram.annotaion.MiniFiled;
import lombok.Data;

/**
 * 描述:
 * 发送购物券返回值
 * @AUTHOR 孙龙云
 * @date 2020-07-18 17:23
 */
@Data
public class SendCardResponse extends  ParentResponse  {
	private String openid;

	/**
	 * 代金券id
	 */
	@MiniFiled("coupon_id")
	private String couponId;
}
