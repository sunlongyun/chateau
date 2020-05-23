package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.constants.enums.Validity;
import com.chisong.green.farm.app.dto.OrderInfoDto;
import com.chisong.green.farm.app.dto.PaymentDto;
import com.chisong.green.farm.app.dto.RefundOrderDto;
import com.chisong.green.farm.app.entity.RefundOrder;
import com.chisong.green.farm.app.example.RefundOrderExample;
import com.chisong.green.farm.app.mapper.RefundOrderMapper;
import com.chisong.green.farm.app.miniProgram.response.ParentResponse;
import com.chisong.green.farm.app.service.OrderInfoService;
import com.chisong.green.farm.app.service.PaymentService;
import com.chisong.green.farm.app.service.RefundOrderService;
import com.chisong.green.farm.app.service.RefundPaymentService;
import com.chisong.green.farm.app.utils.CurrentUserUtils;
import com.lianshang.generator.commons.ServiceImpl;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 退款申请单 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-07-28
 */
@Service
@Slf4j
public class RefundOrderServiceImpl extends ServiceImpl<RefundOrderMapper, RefundOrder, RefundOrderDto> implements
	RefundOrderService {

	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private RefundOrderService refundOrderService;
	@Autowired
	private RefundPaymentService refundPaymentService;

	@Override
	public void applyRefund(String orderNo, Integer amount) {
		OrderInfoDto orderInfoDto = orderInfoService.getOrderByNo(orderNo);
		if(null == orderInfoDto) {
			throw new RuntimeException("未找到对应的订单");
		} else if(StringUtils.isEmpty(orderInfoDto.getPayNo())) {
			throw new RuntimeException("订单未支付，不能发起退款");
		}
		String paymentNo = orderInfoDto.getPayNo();
		PaymentDto paymentDto = paymentService.getPaymentDto(paymentNo);
		if(null == paymentDto) {
			throw new RuntimeException("未找到对应支付流水");
		}
		RefundOrderExample refundOrderExample = new RefundOrderExample();
		refundOrderExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
			.andOrderNoEqualTo(orderNo).andStatusNotIn(Arrays.asList(2,4));

		long totalRefund = refundOrderService.getList(refundOrderExample).stream()
			.mapToLong(RefundOrderDto::getAmount).reduce(Math::addExact).orElse(0L);

		log.info("orderNO==>{}, totalRefund==>{}",orderNo, totalRefund);

		if(totalRefund >paymentDto.getAmount()){
			throw new RuntimeException("退款申请失败，退款申请总金额不能大于付款金额!");
		}

		//创建退款申请单
		RefundOrderDto refundOrderDto = new RefundOrderDto();
		refundOrderDto.setOrderId(orderInfoDto.getId());
		refundOrderDto.setOrderNo(orderNo);
		refundOrderDto.setAmount(Long.parseLong(amount+""));
		refundOrderDto.setStatus(1);
		refundOrderDto.setPaymentNo(paymentNo);
		String seq = String.format("%03d", (int) (Math.random() * 1000));
		String refundNo = "RF_APPLY_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + seq;

		refundOrderDto.setRefundNo(refundNo);
		refundOrderDto.setRefundRemark("退款");

		refundOrderService.save(refundOrderDto);

		refundPaymentService.refundOrder(refundNo);
	}

	@Override
	public RefundOrderDto getRefundOrderDto(String applyNo) {
		RefundOrderExample refundOrderExample = new RefundOrderExample();
		refundOrderExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
			.andRefundNoEqualTo(applyNo);

		List<RefundOrderDto> refundOrderDtoList = getList(refundOrderExample);
		if(!CollectionUtils.isEmpty(refundOrderDtoList)) {
			return refundOrderDtoList.get(0);
		}
		return null;
	}
}