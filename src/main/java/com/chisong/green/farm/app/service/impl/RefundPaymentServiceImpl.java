package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.dto.OrderInfoDto;
import com.chisong.green.farm.app.dto.PaymentDto;
import com.chisong.green.farm.app.dto.RefundOrderDto;
import com.chisong.green.farm.app.entity.RefundPayment;
import com.chisong.green.farm.app.dto.RefundPaymentDto;
import com.chisong.green.farm.app.example.RefundPaymentExample;
import com.chisong.green.farm.app.mapper.RefundPaymentMapper;
import com.chisong.green.farm.app.miniProgram.request.RefundApplyReq;
import com.chisong.green.farm.app.miniProgram.response.ParentResponse;
import com.chisong.green.farm.app.miniProgram.service.WxPayService;
import com.chisong.green.farm.app.service.OrderInfoService;
import com.chisong.green.farm.app.service.PaymentService;
import com.chisong.green.farm.app.service.RefundOrderService;
import com.chisong.green.farm.app.service.RefundPaymentService;

import com.lianshang.generator.commons.ServiceImpl;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
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
public class RefundPaymentServiceImpl extends ServiceImpl<RefundPaymentMapper, RefundPayment, RefundPaymentDto> implements
	RefundPaymentService {

	@Autowired
	private RefundOrderService refundOrderService;
	@Autowired
	private WxPayService wxPayService;


	@Autowired
	private PaymentService paymentService;
	@Autowired
	private RefundPaymentService refundPaymentService;

	@Override
	public ParentResponse refundOrder(String applyNo) {
		RefundOrderDto refundOrderDto = refundOrderService.getRefundOrderDto(applyNo);
		if(null == refundOrderDto) {
			throw new RuntimeException("未找到退款申请单");
		}
		String paymentNo = refundOrderDto.getPaymentNo();
		PaymentDto paymentDto = paymentService.getPaymentDto(paymentNo);

		//添加退款流水
		RefundPaymentDto refundPaymentDto = new RefundPaymentDto() ;
		refundPaymentDto.setStatus(1);
		refundPaymentDto.setApplyAmount(refundOrderDto.getAmount());
		refundPaymentDto.setApplyId(refundOrderDto.getId());
		refundPaymentDto.setRefundNo(applyNo);
		refundPaymentDto.setPaymentNo(paymentNo);
		refundPaymentService.save(refundPaymentDto);

		//调用微信退款接口
		RefundApplyReq refundApplyReq = new RefundApplyReq();
		String seq = String.format("%03d", (int) (Math.random() * 1000));
		String refundNo = "RF_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + seq;
		refundApplyReq.setTotalFee(paymentDto.getPayedAmount());
		refundApplyReq.setOutRefundNo(refundNo);
		refundApplyReq.setRefundFee(Integer.valueOf(refundOrderDto.getAmount() + ""));
		return wxPayService.refundOrder(refundApplyReq);
	}

	@Override
	public RefundPaymentDto getRefundPaymentDtoByApplyNo(String applyNo) {

		RefundPaymentExample refundPaymentExample = new RefundPaymentExample();
		refundPaymentExample.createCriteria().andRefundOrderNoEqualTo(applyNo);
		Optional<RefundPaymentDto> refundPaymentDtoOptional = this.getList(refundPaymentExample).stream().findFirst();
		if(refundPaymentDtoOptional.isPresent()){
			return  refundPaymentDtoOptional.get();
		}
		return null;
	}


}