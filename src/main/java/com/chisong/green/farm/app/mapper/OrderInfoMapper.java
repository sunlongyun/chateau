package com.chisong.green.farm.app.mapper;

import com.chisong.green.farm.app.entity.OrderInfo;
import com.lianshang.generator.commons.LsBaseMapper;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 孙龙云
 * @since 2019-06-15
 */
public interface OrderInfoMapper extends LsBaseMapper<OrderInfo> {


	/**
	 * 查询订单列表
	 * @param params
	 * @return
	 */
	public List<OrderInfo> getOrderList(Map<String,Object> params);
}
