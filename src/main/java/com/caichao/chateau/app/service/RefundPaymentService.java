package com.caichao.chateau.app.service;

import com.caichao.chateau.app.miniProgram.response.ParentResponse;
import com.lianshang.generator.commons.IService;
import com.caichao.chateau.app.dto.RefundPaymentDto;
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
	 * 退款申请
	 * @param applyNo
	 * @return
	 */
	public ParentResponse refundOrder(String applyNo);
}
