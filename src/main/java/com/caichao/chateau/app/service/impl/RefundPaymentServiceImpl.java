package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.dto.RefundOrderDto;
import com.caichao.chateau.app.entity.RefundPayment;
import com.caichao.chateau.app.dto.RefundPaymentDto;
import com.caichao.chateau.app.mapper.RefundPaymentMapper;
import com.caichao.chateau.app.miniProgram.request.RefundApplyReq;
import com.caichao.chateau.app.miniProgram.response.ParentResponse;
import com.caichao.chateau.app.miniProgram.service.WxPayService;
import com.caichao.chateau.app.service.RefundOrderService;
import com.caichao.chateau.app.service.RefundPaymentService;

import com.lianshang.generator.commons.ServiceImpl;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 退款流水 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-07-28
 */
@Service
public class RefundPaymentServiceImpl extends ServiceImpl<RefundPaymentMapper,RefundPayment, RefundPaymentDto> implements RefundPaymentService {

	@Autowired
	private RefundOrderService refundOrderService;
	@Autowired
	private WxPayService wxPayService;
	
	@Override
	public ParentResponse refundOrder(String applyNo) {
		RefundOrderDto refundOrderDto = refundOrderService.getRefundOrderDto(applyNo);
		if(null == refundOrderDto) {
			throw new RuntimeException("未找到退款申请单");
		}
		String paymentNo = refundOrderDto.getPaymentNo();
//		OrderInfoDto orderInfoDto =  orderInfoService.getById(refundOrderDto.getOrderId());
//		paymentService.get

		RefundApplyReq refundApplyReq = new RefundApplyReq();

		String seq = String.format("%03d", (int) (Math.random() * 1000));
		String refundNo = "RF_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + seq;

		refundApplyReq.setOutRefundNo(refundNo);
		refundApplyReq.setRefundFee(Integer.valueOf(refundOrderDto.getAmount() + ""));

		wxPayService.refundOrder(refundApplyReq);
		return null;
	}
}