package com.caichao.chateau.app.service;

import com.lianshang.generator.commons.IService;
import com.caichao.chateau.app.dto.RefundOrderDto;
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
	 * 查询退款申请记录
	 * @param applyNo
	 * @return
	 */
	public RefundOrderDto getRefundOrderDto(String applyNo);
}
