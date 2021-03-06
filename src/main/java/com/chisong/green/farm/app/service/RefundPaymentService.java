package com.chisong.green.farm.app.service;

import com.chisong.green.farm.app.miniProgram.response.ParentResponse;
import com.lianshang.generator.commons.IService;
import com.chisong.green.farm.app.dto.RefundPaymentDto;
/**
 * <p>
 * 退款流水 服务类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-07-28
 */
public interface RefundPaymentService extends IService<RefundPaymentDto> {


	/**
	 * 退款
	 * @param applyNo
	 * @return
	 */
	public ParentResponse refundOrder(String applyNo);

	public RefundPaymentDto getRefundPaymentDtoByApplyNo(String applyNo);

	/**
	 * 根据微信申请退款流水号查询
	 * @param refundNo
	 * @return
	 */
	public RefundPaymentDto getRefundPaymentDtoByRefundNo(String refundNo);
}
