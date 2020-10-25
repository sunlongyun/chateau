package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.dto.AppInfoDto;
import com.chisong.green.farm.app.dto.PaymentDto;
import com.chisong.green.farm.app.entity.MerchantPayment;
import com.chisong.green.farm.app.dto.MerchantPaymentDto;
import com.chisong.green.farm.app.mapper.MerchantPaymentMapper;
import com.chisong.green.farm.app.miniProgram.request.PayToPersonRequest;
import com.chisong.green.farm.app.miniProgram.response.PayToPersonResponse;
import com.chisong.green.farm.app.miniProgram.service.WxPayService;
import com.chisong.green.farm.app.service.AppInfoService;
import com.chisong.green.farm.app.service.MerchantPaymentService;

import com.chisong.green.farm.app.service.PaymentService;
import com.lianshang.generator.commons.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 商户支付流水 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2020-10-05
 */
@Service
@Slf4j
public class MerchantPaymentServiceImpl extends ServiceImpl<MerchantPaymentMapper,MerchantPayment, MerchantPaymentDto> implements MerchantPaymentService {
	@Autowired
	private WxPayService wxPayService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private AppInfoService appInfoService;
	@Override
	public void pay(Long id) {
		MerchantPaymentDto merchantPaymentDto = getById(id);
		if(null == merchantPaymentDto){
			throw new RuntimeException("付款申请不存在");
		}else if(merchantPaymentDto.getStatus() ==2){
			throw new RuntimeException("付款记录已设置‘失败’，请重新申请付款");
		}else if(merchantPaymentDto.getStatus() ==1){
			return;
		}

		PayToPersonRequest payToPersonRequest = new PayToPersonRequest();
		payToPersonRequest.setPartnerTradeNo(merchantPaymentDto.getTradeNo());
		payToPersonRequest.setDesc(merchantPaymentDto.getRemark());
		payToPersonRequest.setAmount(merchantPaymentDto.getAmount());
		payToPersonRequest.setOpenid(merchantPaymentDto.getOpenId());
		AppInfoDto appInfoDto =null;
		if(merchantPaymentDto.getAppInfoId() == null){
			PaymentDto paymentDto = paymentService.getById(merchantPaymentDto.getPayId());
			 appInfoDto = appInfoService.getById(paymentDto.getAppInfoId());
			merchantPaymentDto.setAppInfoId(appInfoDto.getId());
			this.update(merchantPaymentDto);
		}else {
			appInfoDto = appInfoService.getById(merchantPaymentDto.getAppInfoId());
		}

	   payToPersonRequest.setMerchantAppid(appInfoDto.getAppId());

		PayToPersonResponse payToPersonResponse = wxPayService.payToPerson(payToPersonRequest);

		if(payToPersonResponse != null){
			if("SUCCESS".equals(payToPersonResponse.getResultCode())){
				merchantPaymentDto.setPaymentNo(payToPersonResponse.getPaymentNo());
				merchantPaymentDto.setStatus(1);
				update(merchantPaymentDto);
			}else{
				log.info("付款失败, id:{}",id);
				throw new RuntimeException("付款失败:"+id);
			}
		}
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void tranfer(Long id) {
		MerchantPaymentDto merchantPaymentDto = getById(id);
		if(merchantPaymentDto.getStatus()==0){
			update(merchantPaymentDto);
			merchantPaymentDto = getById(id);
			if(merchantPaymentDto.getStatus()==0){
				pay(merchantPaymentDto.getId());
			}
		}
	}
}