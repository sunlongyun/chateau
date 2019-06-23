package com.caichao.chateau.app.controller.order.request;

import lombok.Data;

/**
 * 描述:
 * 创建订单的明细详情
 * @AUTHOR 孙龙云
 * @date 2019-06-23 15:10
 */
@Data
public class OrderDetailReq {

	/**
	 * 酒水id
	 */
	private Long beverageId;
	/**
	 * 数量
	 */
	private Integer num;
	/**
	 * 购物车项id
	 */
	private Integer cartItemId;
}
