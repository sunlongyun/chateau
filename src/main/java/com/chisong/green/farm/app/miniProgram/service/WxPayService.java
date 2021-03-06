package com.chisong.green.farm.app.miniProgram.service;

import com.chisong.green.farm.app.miniProgram.request.PayOrderQuery;
import com.chisong.green.farm.app.miniProgram.request.PayToPersonRequest;
import com.chisong.green.farm.app.miniProgram.request.PrePayRequest;
import com.chisong.green.farm.app.miniProgram.request.RedBagRequest;
import com.chisong.green.farm.app.miniProgram.request.RefundApplyReq;
import com.chisong.green.farm.app.miniProgram.response.ParentResponse;
import com.chisong.green.farm.app.miniProgram.response.PayOrderQueryResultResponse;
import com.chisong.green.farm.app.miniProgram.response.PayToPersonResponse;
import com.chisong.green.farm.app.miniProgram.response.PrePayResponse;
import com.chisong.green.farm.app.miniProgram.response.RedBagResponse;

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

	/**
	 * 支付订单查询
	 * @param payOrderQuery
	 * @return
	 */
	public PayOrderQueryResultResponse queryPayOrder(PayOrderQuery payOrderQuery);

	/**
	 * 退款申请
	 * @param refundApplyReq
	 * @return
	 */
	public ParentResponse refundOrder(RefundApplyReq refundApplyReq);

	/**
	 * 发送红包
	 */
	public RedBagResponse RedBagResponse(RedBagRequest redBagRequest);

	/**
	 * 企业付款给个人
	 */
	public PayToPersonResponse payToPerson(PayToPersonRequest payToPersonRequest);
}
