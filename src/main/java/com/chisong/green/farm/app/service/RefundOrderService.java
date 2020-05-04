package com.chisong.green.farm.app.service;

import com.lianshang.generator.commons.IService;
import com.chisong.green.farm.app.dto.RefundOrderDto;
/**
 * <p>
 * 退款申请单 服务类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-07-28
 */
public interface RefundOrderService extends IService<RefundOrderDto> {

	/**
	 * 申请退款
	 * @param orderNo
	 * @param amount
	 */
	public void applyRefund(String orderNo, Integer amount);

	/**
	 * 查询退款申请记录
	 * @param applyNo
	 * @return
	 */
	public RefundOrderDto getRefundOrderDto(String applyNo);
}
