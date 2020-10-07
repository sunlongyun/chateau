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

	/**
	 * 退款，在结算之前
	 * @param paymentId
	 * @param amount
	 */
	void refund(Long paymentId, Long amount);
}
