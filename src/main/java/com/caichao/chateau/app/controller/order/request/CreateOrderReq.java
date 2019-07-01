package com.caichao.chateau.app.controller.order.request;

import java.util.List;
import lombok.Data;

/**
 * 描述:
 * 创建订单请求
 * @AUTHOR 孙龙云
 * @date 2019-07-01 22:44
 */
@Data
public class CreateOrderReq {

	/**
	 * 订单明细
	 */
	private List<OrderDetailReq> orderDetailReqList;
	/**
	 * 收货地址id
	 */
	private Integer addressId;
}
