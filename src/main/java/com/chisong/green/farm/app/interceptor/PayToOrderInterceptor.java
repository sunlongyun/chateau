package com.chisong.green.farm.app.interceptor;

/**
 * 订单付款拦截器
 */
public interface PayToOrderInterceptor {

	/**
	 * 订单付款拦截处理
	 * @param paymentId
	 */
	void handle(Long paymentId);
}
