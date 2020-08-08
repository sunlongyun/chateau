package com.chisong.green.farm.app.interceptor.impl;

import com.chisong.green.farm.app.controller.goods.request.PageQueryReq;
import com.chisong.green.farm.app.controller.order.request.CreateOrderReq;
import com.chisong.green.farm.app.controller.response.CCResponse;
import com.chisong.green.farm.app.interceptor.CreateOrderReqCheck;
import com.chisong.green.farm.app.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 描述:
 * 创建订单校验(常规校验)
 * @AUTHOR 孙龙云
 * @date 2020-08-08 10:22
 */
@Service
@Order(1)
public class NormalCheckImpl implements CreateOrderReqCheck {
	@Autowired
	private GoodsService goodsService;

	@Override
	public void check(CreateOrderReq createOrderReq) throws RuntimeException {
		if(null == createOrderReq || CollectionUtils.isEmpty(createOrderReq.getOrderDetailReqList())
			|| null == createOrderReq.getAddressId()) {
			throw new RuntimeException("订单明细以及收货地址都不能为空");
		}

		//如果购物清单的商品来自不同的供应商，则返回提示分别下单
		if(checkSupplier(createOrderReq)) {
			throw new RuntimeException("您购买的商品来自不同的供应商，建议分别下单购买");
		}

		if(CollectionUtils.isEmpty(createOrderReq.getOrderDetailReqList())) {
			throw new RuntimeException("购物明细不能为空");
		}
	}

	private boolean checkSupplier(@RequestBody CreateOrderReq createOrderReq) {
		long supplierCount = createOrderReq.getOrderDetailReqList().stream().map(orderDetailReq -> {
			return goodsService.getById(orderDetailReq.getGoodsId()).getSupplierId();
		}).distinct().count();
		if(supplierCount > 1) {
			return true;
		}
		return false;
	}
}
