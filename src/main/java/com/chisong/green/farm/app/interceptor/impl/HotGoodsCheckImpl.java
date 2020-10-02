package com.chisong.green.farm.app.interceptor.impl;

import com.chisong.green.farm.app.constants.enums.OrderStatusEnum;
import com.chisong.green.farm.app.constants.enums.Validity;
import com.chisong.green.farm.app.controller.order.request.CreateOrderReq;
import com.chisong.green.farm.app.controller.order.request.OrderDetailReq;
import com.chisong.green.farm.app.dto.CustomerInfoDto;
import com.chisong.green.farm.app.dto.GoodsDto;
import com.chisong.green.farm.app.dto.OrderDetailDto;
import com.chisong.green.farm.app.dto.OrderInfoDto;
import com.chisong.green.farm.app.example.OrderDetailExample;
import com.chisong.green.farm.app.example.OrderInfoExample;
import com.chisong.green.farm.app.interceptor.CreateOrderReqCheck;
import com.chisong.green.farm.app.miniProgram.response.LoginResponse;
import com.chisong.green.farm.app.service.CustomerInfoService;
import com.chisong.green.farm.app.service.GoodsService;
import com.chisong.green.farm.app.service.OrderDetailService;
import com.chisong.green.farm.app.service.OrderInfoService;
import com.chisong.green.farm.app.utils.CurrentUserUtils;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述:
 *  特价商品校验
 * @AUTHOR 孙龙云
 * @date 2020-08-08 10:38
 */
@Service
public class HotGoodsCheckImpl implements CreateOrderReqCheck {

	@Autowired
	private CustomerInfoService customerInfoService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private OrderDetailService orderDetailService;

	@Override
	public void check(CreateOrderReq pageQueryReq) throws RuntimeException {
		LoginResponse loginResponse = CurrentUserUtils.get();

		CustomerInfoDto customerInfoDto = customerInfoService.getCustomerInfoDtoByOpenId(loginResponse.getOpenid());

		/**
		 * 一周内顾客只能购买一单特价商品
		 */

		//本次购买，存在热门商品
		long count = pageQueryReq.getOrderDetailReqList().stream().filter(orderDetailReq -> {
			Long goodsId = orderDetailReq.getGoodsId();
			GoodsDto goodsDto = goodsService.getById(goodsId);
			return goodsDto.isHot();
		}).map(OrderDetailReq::getGoodsId).distinct().count();
		if(count > 1) {
			throw new RuntimeException("特价商品每次只能购买一款商品！！");
		}
		count = pageQueryReq.getOrderDetailReqList().stream().filter(orderDetailReq -> {
			Long goodsId = orderDetailReq.getGoodsId();
			GoodsDto goodsDto = goodsService.getById(goodsId);
			return goodsDto.isHot();
		}).map(orderDetailReq -> orderDetailReq.getNum()).reduce((a, b) -> a + b).orElse(0);

		if(count > 1) {
			throw new RuntimeException("特价商品每次只能购买一件！！");
		}
		if(count == 1) {//只购买了一件特价商品，校验本周是否购买了其他特价商品
			OrderInfoExample orderInfoExample = new OrderInfoExample();
			orderInfoExample.createCriteria().andCustomerIdEqualTo(customerInfoDto.getId())
				.andValidityEqualTo(Validity.AVAIL.code())
				.andStatusNotIn(Arrays.asList(OrderStatusEnum.DELETED.code(), OrderStatusEnum.CANCELED.code()))
				.andCreateTimeGreaterThan(localDateTime2Date(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
				.andCreateTimeLessThan(localDateTime2Date(LocalDateTime.now()));
			List<OrderInfoDto> orderInfoDtoList = orderInfoService.getList(orderInfoExample);
			boolean exits = orderInfoDtoList.stream().anyMatch(orderInfoDto -> {
				Long orderId = orderInfoDto.getId();
				OrderDetailExample orderDetailExample = new OrderDetailExample();
				orderDetailExample.createCriteria().andOrderIdEqualTo(orderId)
					.andValidityEqualTo(Validity.AVAIL.code());
				List<OrderDetailDto> orderDetailDtos = orderDetailService.getList(orderDetailExample);

				return orderDetailDtos.stream().anyMatch(orderDetailDto -> {
					Long goodsId = orderDetailDto.getGoodsId();
					GoodsDto goodsDto = goodsService.getById(goodsId);
					return goodsDto.isHot();
				});

			});

			if(exits) {
				throw new RuntimeException("一周内只能购买一单特价商品");
			}
		}

	}

	/**
	 * LocalDateTime转换为Date
	 *
	 * @param localDateTime
	 */
	private Date localDateTime2Date(LocalDateTime localDateTime) {
		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zdt = localDateTime
			.atZone(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
		Date date = Date.from(zdt.toInstant());
		return date;
	}
}
