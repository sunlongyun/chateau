package com.caichao.chateau.app.service;

import com.lianshang.generator.commons.IService;
import com.caichao.chateau.app.dto.OrderInfoDto;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-06-15
 */
public interface OrderInfoService extends IService<OrderInfoDto> {
	/**
	 * 创建购物订单
	 * @param orderInfoDto
	 * @return
	 */
	public String createOrder(OrderInfoDto orderInfoDto);
}
