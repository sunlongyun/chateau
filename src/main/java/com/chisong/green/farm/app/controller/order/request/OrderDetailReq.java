package com.chisong.green.farm.app.controller.order.request;

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
	 * 商品id
	 */
	private Long goodsId;
	/**
	 * 商品规格id，非统一规格商品，必须存在商品规格id
	 */
	private Long specsId;
	/**
	 * 商品规格名称
	 */
	private String specsName;
	/**
	 * 数量
	 */
	private Integer num;
	/**
	 * 购物车项id
	 */
	private Integer cartItemId;
	/**
	 * 收货地址省份
	 */
	private String province;
}
