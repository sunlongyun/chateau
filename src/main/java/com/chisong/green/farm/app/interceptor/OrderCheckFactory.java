package com.chisong.green.farm.app.interceptor;

import com.chisong.green.farm.app.controller.order.request.CreateOrderReq;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *  订单校验工程
 * @AUTHOR 孙龙云
 * @date 2020-08-08 10:28
 */
@Component
public class OrderCheckFactory {
	@Autowired
	private List<CreateOrderReqCheck> createOrderReqCheckList;

	/**
	 * 创建订单校验
	 * @param createOrderReq
	 * @throws Exception
	 */
	public void checkCreateOrder(CreateOrderReq createOrderReq) throws RuntimeException{
		for(CreateOrderReqCheck createOrderReqCheck : createOrderReqCheckList){
			createOrderReqCheck.check(createOrderReq);
		}
	}
}
