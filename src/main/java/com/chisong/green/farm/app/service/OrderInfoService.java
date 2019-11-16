package com.chisong.green.farm.app.service;

import com.lianshang.generator.commons.IService;
import com.chisong.green.farm.app.dto.OrderInfoDto;
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
	public String createOrder(OrderInfoDto orderInfoDto, Integer addressId);

	/**
	 * 根据订单号查询
	 * @param orderNo
	 * @return
	 */
	public OrderInfoDto getOrderByNo(String orderNo);

	/**
	 * 根据id查询订单详情
	 * @param id
	 * @return
	 */
	public OrderInfoDto getOrderById(Long id);
}
