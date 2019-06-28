package com.caichao.chateau.app.miniProgram.service;

import com.caichao.chateau.app.miniProgram.request.PrePayRequest;
import com.caichao.chateau.app.miniProgram.response.PrePayResponse;

/**
 * 描述:
 * 微信支付接口
 * @AUTHOR 孙龙云
 * @date 2019-06-26 7:31
 */
public interface WxPayService {

	/**
	 * 生成预支付流水
	 * @param prePayRequest
	 * @return
	 */
	public PrePayResponse prePay(PrePayRequest prePayRequest);
}
