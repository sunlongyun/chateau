package com.chisong.green.farm.app.interceptor;

import com.chisong.green.farm.app.controller.order.request.CreateOrderReq;

/**
 * 创建订单校验
 */
public interface CreateOrderReqCheck {

	/**
	 * 创建订单校验
	 * @param pageQueryReq
	 */
	 void check(CreateOrderReq pageQueryReq) throws RuntimeException;
}
