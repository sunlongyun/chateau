package com.chisong.green.farm.app.controller.card;

import java.util.List;
import lombok.Data;

/**
 * 描述:
 * 发送购物券请求入参
 * @AUTHOR 孙龙云
 * @date 2020-07-18 15:39
 */
@Data
public class CardSendRequest {

	/**
	 * 优惠券id
	 */
	private String cardId;
	/**
	 * openId列表
	 */
	private List<String> openIds;
}
