package com.chisong.green.farm.app.service;

import com.chisong.green.farm.app.controller.order.request.OrderDetailReq;
import com.chisong.green.farm.app.dto.OrderDetailDto;
import com.chisong.green.farm.app.entity.OrderInfo;
import com.chisong.green.farm.app.example.OrderInfoExample;
import com.lianshang.generator.commons.IService;
import com.chisong.green.farm.app.dto.OrderInfoDto;
import com.lianshang.generator.commons.PageInfo;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;

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

	/**
	 * 邮费计算
	 * @param orderDetailReqList
	 * @return
	 */
	public Integer computePostage( List<OrderDetailDto> orderDetailReqList);

	/**
	 * 查询订单列表
	 * @param params
	 * @return
	 */
	public List<OrderInfoDto> getOrderList(Map<String,Object> params);

	/**
	 * 分页查询
	 * @param pageNo
	 * @param pageSize
	 * @param params
	 * @return
	 */
	public PageInfo<?> getOrderInfoPageInfo(int pageNo, int pageSize, Map<String,Object> params);
}
