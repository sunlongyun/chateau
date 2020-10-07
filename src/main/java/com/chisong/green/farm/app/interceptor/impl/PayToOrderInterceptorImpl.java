package com.chisong.green.farm.app.interceptor.impl;

import com.chisong.green.farm.app.constants.enums.Validity;
import com.chisong.green.farm.app.dto.AccountFlowDto;
import com.chisong.green.farm.app.dto.AccountInfoDto;
import com.chisong.green.farm.app.dto.AppInfoDto;
import com.chisong.green.farm.app.dto.CustomerInfoDto;
import com.chisong.green.farm.app.dto.GoodsDto;
import com.chisong.green.farm.app.dto.GoodsSpecsDto;
import com.chisong.green.farm.app.dto.MerchantPaymentDto;
import com.chisong.green.farm.app.dto.OrderInfoDto;
import com.chisong.green.farm.app.dto.PaymentDto;
import com.chisong.green.farm.app.dto.RefundPaymentDto;
import com.chisong.green.farm.app.entity.AccountInfo;
import com.chisong.green.farm.app.example.AccountFlowExample;
import com.chisong.green.farm.app.example.MerchantPaymentExample;
import com.chisong.green.farm.app.example.OrderDetailExample;
import com.chisong.green.farm.app.example.RefundPaymentExample;
import com.chisong.green.farm.app.interceptor.PayToOrderInterceptor;
import com.chisong.green.farm.app.service.AccountFlowService;
import com.chisong.green.farm.app.service.AccountInfoService;
import com.chisong.green.farm.app.service.AppInfoService;
import com.chisong.green.farm.app.service.CustomerInfoService;
import com.chisong.green.farm.app.service.GoodsService;
import com.chisong.green.farm.app.service.GoodsSpecsService;
import com.chisong.green.farm.app.service.MerchantPaymentService;
import com.chisong.green.farm.app.service.OrderDetailService;
import com.chisong.green.farm.app.service.OrderInfoService;
import com.chisong.green.farm.app.service.PaymentService;
import com.chisong.green.farm.app.service.RefundPaymentService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2020-10-05 15:21
 */
@Service
@Slf4j
public class PayToOrderInterceptorImpl implements PayToOrderInterceptor {

	private static final String FIRST_RATE = "0.666";
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private GoodsSpecsService goodsSpecsService;
	@Autowired
	private AppInfoService appInfoService;

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private MerchantPaymentService merchantPaymentService;

	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private CustomerInfoService customerInfoService;

	@Autowired
	private AccountInfoService accountInfoService;

	@Autowired
	private AccountFlowService accountFlowService;

	@Value("${wei_fee_rate}")
	private String weiRate;

	@Autowired
	private RefundPaymentService refundPaymentService;

	@Override
	@Transactional
	public void handle(Long paymentId) {
		PaymentDto paymentDto = paymentService.getById(paymentId);
		if(null == paymentDto){
			throw new RuntimeException("支付记录不存在");
		}

		if(paymentDto.getStatus() != 1){
			throw new RuntimeException("支付成功的流水才可以计算费项");
		}else if (paymentDto.getStatus() == 5){
			throw new RuntimeException("已全部退款的流水无法进行结算");
		}else if(paymentDto.getSettel() == Boolean.TRUE){
			throw new RuntimeException("已结算的流水，无法重复结算");
		}

		Long appInfoId =	paymentDto.getAppInfoId();
		AppInfoDto appInfoDto = appInfoService.getById(appInfoId);
		String orderNo = paymentDto.getPayOrderNo();
		Integer baseAmount =baseAmount(orderNo, paymentDto.getAmount());
		log.info("baseAmount == {}", baseAmount);

		//1.微信平台费
		Integer weiFee = new BigDecimal(weiRate).multiply(BigDecimal.valueOf(paymentDto.getAmount())).intValue();
		if(weiFee<=0){
			weiFee=1;
		}
		log.info("paymentId == {}, weiFee =={}", paymentId, weiFee);

		//2.平台服务费
		Integer platFormDee = platFormFee(appInfoDto, baseAmount);
		savePlatFormFee(paymentId, platFormDee);
		log.info("paymentId == {}, platFormDee =={}", paymentId, platFormDee);

		//3.供货商款项
		Integer supplierFee = 0;
		if(appInfoDto.getPayManager() == Boolean.TRUE){
			supplierFee = supplierFee(orderNo);
		}
		log.info("paymentId == {}, supplierFee =={}", paymentId, supplierFee);

		if(supplierFee>0){
			saveSupplierFee(paymentId, supplierFee);
		}

		//4.游客分销费用(转发)
		Integer shareFee = 0;
		log.info("isPaySaler == {}", appInfoDto.getPaySaler());
		if(appInfoDto.getPaySaler() == Boolean.TRUE){
			 shareFee = shareFee(orderNo);
			if(shareFee>0){
				saveSharerFee(paymentId,shareFee);
			}
		}
		log.info("paymentId == {}, shareFee =={}", paymentId, shareFee);


		//5.应该给商家计算的金额
		Integer merchantFee = paymentDto.getAmount()-weiFee-platFormDee-supplierFee-shareFee;
		saveMerchantFee(paymentId, merchantFee, appInfoDto.getOpenId());


	}

	/**
	 * 商户结算
	 * @param paymentId
	 * @param machantFee
	 */
	private void saveMerchantFee(Long paymentId, Integer machantFee, String openId) {
		PaymentDto paymentDto = paymentService.getById(paymentId);
		AppInfoDto appInfoDto= appInfoService.getById(paymentDto.getAppInfoId());
		Integer transferDate = 	appInfoDto.getTransferDate();
		if(transferDate == null){
			transferDate = 15;
		}

		OrderInfoDto orderInfoDto = orderInfoService.getOrderByNo(paymentDto.getPayOrderNo());
		MerchantPaymentDto merchantPaymentDto = new MerchantPaymentDto();
		merchantPaymentDto.setStatus(0);
		merchantPaymentDto.setAmount(machantFee);
		merchantPaymentDto.setOpenId(openId);
		merchantPaymentDto.setPayId(paymentId);
		merchantPaymentDto.setPayType(1);
		merchantPaymentDto.setUserType(1);
		merchantPaymentDto.setTradeNo("sc"+System.currentTimeMillis()+"_"+paymentId);
		String remark  ="顾客["+orderInfoDto.getCustomerName()+"]购物款结算";
		merchantPaymentDto.setRemark(remark);

		merchantPaymentDto.setPreTransferTime(Date.from(LocalDateTime.now().plus(transferDate, ChronoUnit.DAYS).atZone( ZoneId
			.systemDefault()).toInstant()));
		merchantPaymentService.save(merchantPaymentDto);
	}

	/**
	 * 退还商户结算
	 * @param paymentId
	 * @param refundMachantFee
	 * @param full
	 */
	private void refundMerchantFee(Long paymentId, Integer refundMachantFee,boolean full){
		MerchantPaymentExample merchantPaymentExample = new MerchantPaymentExample();
		merchantPaymentExample.createCriteria().andPayIdEqualTo(paymentId).andPayTypeEqualTo(1);
		MerchantPaymentDto merchantPaymentDto =
			merchantPaymentService.getList(merchantPaymentExample).stream().findFirst().orElse(null);
		if(null == merchantPaymentDto){
			log.info("cannot find merchant fee , paymentId:{}",paymentId);
			throw new RuntimeException("商户交易费不存在，无法退款，paymentId=="+paymentId);
		}else if(merchantPaymentDto.getStatus() != 0){
			log.info("cannot refund merchant fee,paymentId is {} the status is {}!!",paymentId,
				merchantPaymentDto.getStatus());
			throw new RuntimeException("商户交易费当前的状态，不可以退款, paymentId="+paymentId);
		}

		if(full){
			merchantPaymentDto.setAmount(0);
			merchantPaymentDto.setStatus(2);
		}else{
			int amount =merchantPaymentDto.getAmount()
				- refundMachantFee;
			if(amount<0){
				amount=0;
			}

			merchantPaymentDto.setAmount(amount);
		}
		merchantPaymentService.update(merchantPaymentDto);
	}
	/**
	 * 退平台服务费
	 * @param paymentId
	 * @param refundFee
	  * @param full
	 */
	private void refundPlatFormFee(Long paymentId,Integer refundFee, boolean full){
		PaymentDto paymentDto =	paymentService.getById(paymentId);
		AppInfoDto appInfoDto = appInfoService.getById(paymentDto.getAppInfoId());
		Integer  transferDate = appInfoDto.getTransferDate();
		if(null == transferDate){
			transferDate = 15;
		}
		Integer reundPlatfee = refundFee;
		Integer refundRecommendFee = 0;
		if(appInfoDto.getPayRecommend() == Boolean.TRUE && null != appInfoDto.getRecommendRate()){
			refundRecommendFee = BigDecimal.valueOf(refundFee)
				.multiply(BigDecimal.valueOf(appInfoDto.getRecommendRate()))
				.divide(new BigDecimal("100"),2,BigDecimal.ROUND_HALF_DOWN).intValue();
			reundPlatfee = refundFee- refundRecommendFee;
		}

		//////////////////////退还商家介绍费/////////////////////
		if(refundRecommendFee>0){
			AccountFlowExample accountFlowExample = new AccountFlowExample();
			accountFlowExample.createCriteria().andPayNoEqualTo(paymentDto.getPayNo())
				.andSourceEqualTo(3).andStatusEqualTo(0).andValidityEqualTo(Validity.AVAIL.code());

			AccountFlowDto accountFlowDto =
				accountFlowService.getList(accountFlowExample).stream().findFirst().orElse(null);
			if(null == accountFlowDto){
				log.info("cannot find merchant recommend fee, paymentNo == {}", paymentDto.getPayNo());
				throw new RuntimeException("未查找到商家介绍费的记录,paymentNo="+paymentDto.getPayNo());
			}
			if(full){
				accountFlowDto.setAmount(0);
				accountFlowDto.setStatus(2);
			}else{
				Integer amount = accountFlowDto.getAmount() - refundRecommendFee;
				if(amount <0){
					amount = 0;
				}
				accountFlowDto.setAmount(amount);
				if(amount == 0){
					accountFlowDto.setStatus(2);
				}
			}
			accountFlowService.update(accountFlowDto);

			AccountInfoDto accountInfoDto = accountInfoService.getById(accountFlowDto.getAccountId());
			int amount = accountInfoDto.getRecordedAmount() - refundRecommendFee;
			if(amount <0){
				amount=0;
			}
			accountInfoDto.setRecordedAmount(amount);
			accountInfoService.update(accountInfoDto);
		}

		////////////////////退还平台费///////////////////////////////////////
		if(reundPlatfee >0){
			AccountInfoDto accountInfoDto = accountInfoService.getAccountInfoDtoByCustomerId(-1);
			AccountFlowExample accountFlowExample = new AccountFlowExample();
			accountFlowExample.createCriteria().andPayNoEqualTo(paymentDto.getPayNo()).andAccountIdEqualTo(accountInfoDto.getId())
				.andSourceEqualTo(2).andStatusEqualTo(0).andValidityEqualTo(Validity.AVAIL.code());
			AccountFlowDto accountFlowDto =
				accountFlowService.getList(accountFlowExample).stream().findFirst().orElse(null);
			if(null == accountFlowDto){
				log.info("cannot find platform service fee, paymentNo == {}", paymentDto.getPayNo());
				throw new RuntimeException("未查找到平台服务费的记录,paymentNo="+paymentDto.getPayNo());
			}
			if(full){
				accountFlowDto.setAmount(0);
				accountFlowDto.setStatus(2);
			}else{
				int amount =accountFlowDto.getAmount()-reundPlatfee;
				accountFlowDto.setAmount(amount);
				if(amount ==0){
					accountFlowDto.setStatus(2);
				}
			}
			accountFlowService.update(accountFlowDto);
			int recordedAmount = accountInfoDto.getRecordedAmount() - reundPlatfee;
			if(recordedAmount<0){
				recordedAmount = 0;
			}
			accountInfoDto.setRecordedAmount(recordedAmount);

			accountInfoService.update(accountInfoDto);
		}
	}

	/**
	 * 退供应商费
	 * @param paymentId
	 * @param refundFee
	 * @param full
	 */
	private void refundPSupplierFee(Long paymentId,Integer refundFee, boolean full){
		log.info("退供应商费 paymentId=={}， refundFee=={}", paymentId, refundFee);
	}
	/**
	 * 平台服务费
	 * @param paymentId
	 * @param platFormDee
	 */
	private void savePlatFormFee(Long paymentId, Integer platFormDee) {

		PaymentDto paymentDto =	paymentService.getById(paymentId);
		AppInfoDto appInfoDto = appInfoService.getById(paymentDto.getAppInfoId());
		Integer  transferDate = appInfoDto.getTransferDate();
		if(null == transferDate){
			transferDate = 15;
		}

		Integer recommendFee = 0;
		//平台推荐费
		if(appInfoDto.getPayRecommend() == Boolean.TRUE && null != appInfoDto.getRecommendRate()){
			recommendFee =
				BigDecimal.valueOf(platFormDee).multiply(BigDecimal.valueOf(appInfoDto.getRecommendRate()))
					.divide(new BigDecimal("100"),2,BigDecimal.ROUND_HALF_DOWN).intValue();
		}

		Integer platFee = platFormDee - recommendFee;

		//////////////////////平台推广费转账至推广人账户余额/////////////////////////
		if(recommendFee>0){
		  Long recommendId = appInfoDto.getRecommendCustomerid();
		  CustomerInfoDto customerInfoDto = customerInfoService.getById(recommendId);
		  AccountInfoDto accountInfoDto =
			  accountInfoService.getAccountInfoDtoByCustomerId(Integer.parseInt(recommendId+""));
		  if(null == accountInfoDto) {
			  accountInfoService.createAccountInfo(customerInfoDto);
			  accountInfoDto =
				  accountInfoService.getAccountInfoDtoByCustomerId(Integer.parseInt(recommendId+""));
		  }

			AccountFlowDto accountFlowDto = new AccountFlowDto();
			accountFlowDto.setPayNo(paymentDto.getPayNo());
			accountFlowDto.setAccountId(accountInfoDto.getId());
			accountFlowDto.setAmount(recommendFee);
			accountFlowDto.setStatus(0);
			String desc = "[商家介绍佣金]"+appInfoDto.getName()+"有一笔交易";
			accountFlowDto.setOperateName(desc);
			accountFlowDto.setSource(3);
			accountFlowDto.setType(1);
			accountFlowService.save(accountFlowDto);
			accountInfoDto.setRecordedAmount(accountInfoDto.getRecordedAmount()+recommendFee);
			accountFlowDto.setInAccountTime(Date.from(LocalDateTime.now().plus(transferDate, ChronoUnit.DAYS).atZone( ZoneId
				.systemDefault()).toInstant()));
			accountInfoService.update(accountInfoDto);
		}

		////////////////////////////平台服务费/////////////////////
		if(platFee>0){
			AccountInfoDto accountInfoDto =
				accountInfoService.getAccountInfoDtoByCustomerId(-1);

			AccountFlowDto accountFlowDto = new AccountFlowDto();
			accountFlowDto.setAccountId(accountInfoDto.getId());
			accountFlowDto.setAmount(platFee);
			accountFlowDto.setStatus(0);
			accountFlowDto.setPayNo(paymentDto.getPayNo());
			accountFlowDto.setSource(2);
			String desc = "[平台服务费]"+appInfoDto.getName()+"有一笔交易";
			accountFlowDto.setOperateName(desc);
			accountFlowDto.setType(1);
			accountFlowDto.setInAccountTime(Date.from(LocalDateTime.now().plus(transferDate, ChronoUnit.DAYS).atZone( ZoneId
				.systemDefault()).toInstant()));

			accountFlowService.save(accountFlowDto);

			accountInfoDto.setRecordedAmount(accountInfoDto.getRecordedAmount()+platFee);
			accountInfoService.update(accountInfoDto);
		}
	}

	/**
	 * 供货商结款
	 * @param paymentId
	 * @param supplierFee
	 */
	private void saveSupplierFee(Long paymentId, Integer supplierFee) {
		MerchantPaymentDto merchantPaymentDto = new MerchantPaymentDto();
		merchantPaymentDto.setStatus(0);
		merchantPaymentDto.setAmount(supplierFee);
		merchantPaymentDto.setOpenId("oqrTq4jLQt0I_9F4vQVQLQGDrBbM");
		merchantPaymentDto.setPayId(paymentId);
		merchantPaymentDto.setPayType(2);
		merchantPaymentDto.setUserType(1);
		merchantPaymentDto.setTradeNo("sp_"+System.currentTimeMillis()+paymentId);
		merchantPaymentDto.setRemark("商品结款");
		//TODO 供货商openId
		merchantPaymentService.save(merchantPaymentDto);
	}

	/**
	 * 退还分享佣金
	 * @param paymentId
	 * @param refundShareFee
	 * @param full
	 */
	private void refundShareFee(Long paymentId, Integer refundShareFee, boolean full){
		//一级代理商 66.6%，二级代理商33.3%
		Integer firstAmount =
			BigDecimal.valueOf(refundShareFee).multiply(new BigDecimal(FIRST_RATE)).setScale(2,BigDecimal.ROUND_HALF_UP).intValue();
		Integer secondAmount = refundShareFee - firstAmount;
		log.info("refund paymentId == {}, firstAmount=={}, secondAmount=={}", paymentId, firstAmount, secondAmount);

		PaymentDto paymentDto = paymentService.getById(paymentId);
		String orderNo = paymentDto.getPayOrderNo();
		OrderInfoDto orderInfoDto = orderInfoService.getOrderByNo(orderNo);
		Long customerId = orderInfoDto.getCustomerId();
		CustomerInfoDto customerInfoDto = customerInfoService.getById(customerId);
		//////////////////////////////////开始退还一级分享佣金///////////////////////////////////
		Long recommendId = customerInfoDto.getRecommendId();
		if(recommendId == null){
			recommendId = -1L;
		}
		AccountInfoDto accountInfoDto =accountInfoService.getAccountInfoDtoByCustomerId(Integer.parseInt(recommendId+""));
		AccountFlowExample accountFlowExample = new AccountFlowExample();
		accountFlowExample.createCriteria().andAccountIdEqualTo(accountInfoDto.getId())
			.andSourceEqualTo(0).andTypeEqualTo(1).andValidityEqualTo(Validity.AVAIL.code())
		.andPayNoEqualTo(paymentDto.getPayNo());

		AccountFlowDto accountFlowDto =
			accountFlowService.getList(accountFlowExample).stream().findFirst().orElse(null);
		CustomerInfoDto firstRecommend = customerInfoService.getById(recommendId);

		if(null == accountFlowDto){
			log.info("cannot find first share fee, paymentId:{}", paymentId);
			throw new RuntimeException("不存在一级分润流水,paymentId="+paymentId);
		}
		if(accountFlowDto.getStatus() != 0){
			log.info("cannot refund first share fee, paymentId:{}, the status is {}", paymentId, accountFlowDto.getStatus());
			throw new RuntimeException("当前状态不能退还一级分润费,paymentId="+paymentId);
		}

		if(full){
			accountFlowDto.setAmount(0);
			accountFlowDto.setStatus(2);
			int recordedAmount = accountInfoDto.getRecordedAmount()-accountFlowDto.getAmount();
			if(recordedAmount<0){
				recordedAmount= 0;
			}
			accountInfoDto.setRecordedAmount(recordedAmount);
		}else{
			int amount = accountFlowDto.getAmount() - firstAmount;
			if(amount<=0){
				amount = 0;
				accountFlowDto.setStatus(2);
			}
			accountFlowDto.setAmount(amount);
			int recordedAmount= accountInfoDto.getRecordedAmount()-firstAmount;
			if(recordedAmount <0){
				recordedAmount =0;
			}
			accountInfoDto.setRecordedAmount(accountInfoDto.getRecordedAmount()-firstAmount);
		}
		accountFlowService.update(accountFlowDto);
		accountInfoService.update(accountInfoDto);

		//////////////////////////////////开始退还二级分享佣金///////////////////////////////////
		Long secondRecommendId = firstRecommend.getRecommendId();
		if(null == secondRecommendId){
			secondRecommendId = -1L;
		}
		AccountInfoDto accountInfoDto2 =
			accountInfoService.getAccountInfoDtoByCustomerId(Integer.parseInt(secondRecommendId+""));

		AccountFlowExample accountFlowExample2 = new AccountFlowExample();
		accountFlowExample2.createCriteria().andAccountIdEqualTo(accountInfoDto2.getId())
			.andSourceEqualTo(0).andTypeEqualTo(1).andValidityEqualTo(Validity.AVAIL.code())
		.andPayNoEqualTo(paymentDto.getPayNo());

		AccountFlowDto accountFlowDto2 =
			accountFlowService.getList(accountFlowExample2).stream().findFirst().orElse(null);
		if(null == accountFlowDto2){
			log.info("cannot find second share fee, paymentId:{}", paymentId);
			throw new RuntimeException("不存在二级分润流水,paymentId="+paymentId);
		}
		if(accountFlowDto2.getStatus() != 0){
			log.info("cannot refund second share fee, paymentId:{}, the status is {}", paymentId,
				accountFlowDto.getStatus());
			throw new RuntimeException("当前状态不能退还二级分润费,paymentId="+paymentId);
		}

		if(full){
			accountFlowDto2.setAmount(0);
			accountFlowDto2.setStatus(2);
			accountInfoDto2.setRecordedAmount(accountInfoDto2.getRecordedAmount()-accountFlowDto2.getAmount());
		}else{
			int amount = accountFlowDto2.getAmount() - secondAmount;
			if(amount <=0){
				amount = 0;
				accountFlowDto2.setStatus(2);
			}
			accountFlowDto2.setAmount(amount);
			accountInfoDto2.setRecordedAmount(accountInfoDto2.getRecordedAmount()-secondAmount);
		}

		accountFlowService.update(accountFlowDto2);
		accountInfoService.update(accountInfoDto2);
	}
	/**
	 * 分销费用
	 * @param paymentId
	 * @param shareFee
	 */
	private void saveSharerFee(Long paymentId, Integer shareFee) {

		//一级代理商 66.6%，二级代理商33.3%
		Integer firstAmount = BigDecimal.valueOf(shareFee).multiply(new BigDecimal(FIRST_RATE)).intValue();
		Integer secondAmount = shareFee - firstAmount;
		log.info("paymentId == {}, firstAmount=={}, secondAmount=={}", paymentId, firstAmount, secondAmount);

		PaymentDto paymentDto = paymentService.getById(paymentId);
		String orderNo = paymentDto.getPayOrderNo();
		OrderInfoDto orderInfoDto = orderInfoService.getOrderByNo(orderNo);
		Long customerId = orderInfoDto.getCustomerId();
		CustomerInfoDto customerInfoDto = customerInfoService.getById(customerId);
		Long recommendId = customerInfoDto.getRecommendId();
		if(recommendId == null){
			recommendId = -1L;
		}
		AccountInfoDto accountInfoDto =accountInfoService.getAccountInfoDtoByCustomerId(Integer.parseInt(recommendId+""));
		if(null == accountInfoDto){//不存在就自动开户
			accountInfoService.createAccountInfo(customerInfoDto);
			accountInfoDto =accountInfoService.getAccountInfoDtoByCustomerId(Integer.parseInt(recommendId+""));
		}
		CustomerInfoDto firstRecommend = customerInfoService.getById(recommendId);

		accountInfoDto.setRecordedAmount(accountInfoDto.getRecordedAmount()+firstAmount);
		accountInfoService.update(accountInfoDto);

		AccountFlowDto accountFlowDto = new AccountFlowDto();
		accountFlowDto.setStatus(0);
		accountFlowDto.setAmount(firstAmount);
		accountFlowDto.setType(1);
		accountFlowDto.setSource(0);
		accountFlowDto.setAccountId(accountInfoDto.getId());
		accountFlowDto.setOperateName("[佣金]"+customerInfoDto.getNickName()+"完成了一笔购物交易.");
		accountFlowDto.setAppInfoId(paymentDto.getAppInfoId());
		accountFlowDto.setPayNo(paymentDto.getPayNo());
		accountFlowService.save(accountFlowDto);


		Long secondRecommendId = firstRecommend.getRecommendId();
		if(null == secondRecommendId){
			secondRecommendId = -1L;
		}
		CustomerInfoDto customerInfoDto2 = customerInfoService.getById(secondRecommendId);
		AccountInfoDto accountInfoDto2 =
			accountInfoService.getAccountInfoDtoByCustomerId(Integer.parseInt(secondRecommendId+""));
		if(null == accountInfoDto2){//不存在就自动开户
			accountInfoService.createAccountInfo(customerInfoDto2);
			accountInfoDto2 =accountInfoService.getAccountInfoDtoByCustomerId(Integer.parseInt(secondRecommendId+""));
		}
		accountInfoDto2.setRecordedAmount(accountInfoDto2.getRecordedAmount()+secondAmount);
		accountInfoService.update(accountInfoDto2);

		AccountFlowDto accountFlowDto2 = new AccountFlowDto();
		accountFlowDto2.setStatus(0);
		accountFlowDto2.setAmount(secondAmount);
		accountFlowDto2.setType(1);
		accountFlowDto2.setAccountId(accountInfoDto2.getId());
		accountFlowDto2.setSource(0);
		accountFlowDto2.setPayNo(paymentDto.getPayNo());
		accountFlowDto2.setAppInfoId(paymentDto.getAppInfoId());
		accountFlowDto2.setOperateName("[佣金]"+customerInfoDto.getNickName()+"完成了一笔购物交易.");
		accountFlowService.save(accountFlowDto2);

	}

	private Integer shareFee(String orderNo){
		OrderDetailExample orderDetailExample = new OrderDetailExample();
		orderDetailExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
			.andOrderNoEqualTo(orderNo);

		Integer shareFee = 	orderDetailService.getList(orderDetailExample).stream().map(orderDetailDto -> {
			Long goodsId  =	orderDetailDto.getGoodsId();
			GoodsDto goodsDto = goodsService.getById(goodsId);
			Integer profitRate = goodsDto.getProfitRate();
			if(profitRate == null){
				profitRate = 0;
			}
			log.info("shareRate=={}", profitRate);
			return BigDecimal.valueOf(orderDetailDto.getPrice())
				.multiply(BigDecimal.valueOf(orderDetailDto.getNum()))
				.multiply(BigDecimal.valueOf(profitRate)).divide(new BigDecimal("100"),2,BigDecimal.ROUND_HALF_UP).intValue();
		}).reduce(Math::addExact).get();
	   if(shareFee<2){
		   shareFee=2;
	   }
		return shareFee;
	}
	/**
	 * 平台服务费
	 * @param appInfoDto
	 * @param baseAmount
	 * @return
	 */
	private Integer   platFormFee(AppInfoDto appInfoDto, Integer baseAmount) {
		Integer platFormFeeRate =	appInfoDto.getRate();
		if(platFormFeeRate == null){
			platFormFeeRate =0;
		}
		Integer platFormFee = BigDecimal.valueOf(baseAmount).divide(new BigDecimal("100")).multiply(BigDecimal.valueOf(platFormFeeRate)).intValue();
		if(platFormFee<1){
			platFormFee=1;
		}
		return platFormFee;
	}

	/**
	 * 供货商费用
	 * @param orderNo
	 * @return
	 */
	private Integer supplierFee(String orderNo){
		OrderDetailExample orderDetailExample = new OrderDetailExample();
		orderDetailExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
			.andOrderNoEqualTo(orderNo);
	return 	orderDetailService.getList(orderDetailExample).stream().map(orderDetailDto -> {
			Integer  num = orderDetailDto.getNum();
			Long spescId = orderDetailDto.getSpecsId();
			GoodsSpecsDto goodsSpecsDto = goodsSpecsService.getById(spescId);
			Integer originPrice = goodsSpecsDto.getOriginPrice();

			return num*originPrice;
		}).reduce(Math::addExact).get();
	}
	/**
	 * 基础费项
	 * @param orderNo
	 * @param baseAmount
	 * @return
	 */
	private Integer baseAmount(String orderNo, Integer baseAmount){
		OrderDetailExample orderDetailExample = new OrderDetailExample();
		orderDetailExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
			.andOrderNoEqualTo(orderNo);

		Integer unFeeAmount = orderDetailService.getList(orderDetailExample).stream().map(orderDetailDto -> {
			Integer  num = orderDetailDto.getNum();
			Long spescId = orderDetailDto.getSpecsId();
			GoodsSpecsDto goodsSpecsDto = goodsSpecsService.getById(spescId);
			Integer unFeeItem = goodsSpecsDto.getUnFeeAmount();
			if(unFeeItem == null){
				unFeeItem = 0;
			}
			if(unFeeItem>goodsSpecsDto.getPrice()){
				unFeeItem = goodsSpecsDto.getPrice();
			}
			return num*unFeeItem;
		}).reduce(Math::addExact).get();

		return  baseAmount- unFeeAmount;
	}

	@Override
	@Transactional
	public void refund(Long paymentId, Long amount) {
		PaymentDto paymentDto = paymentService.getById(paymentId);
		if(null == paymentDto){
			throw new RuntimeException("支付记录不存在");
		}

		if(paymentDto.getStatus() != 1){
			throw new RuntimeException("支付成功的流水才可以退款");
		}else if (paymentDto.getStatus() == 5){
			throw new RuntimeException("已全部退款的流水，无法发起退款");
		}else if(paymentDto.getSettel() == Boolean.TRUE){
			throw new RuntimeException("已结算的流水，无法发起退款");
		}
		Long appInfoId =	paymentDto.getAppInfoId();
		AppInfoDto appInfoDto = appInfoService.getById(appInfoId);
		String orderNo = paymentDto.getPayOrderNo();
		Integer baseAmount =baseAmount(orderNo, paymentDto.getAmount());
		log.info("baseAmount == {}", baseAmount);

		BigDecimal refundRate = BigDecimal.ZERO;
		boolean full = false;//本次是否累计退完
		if(paymentDto.getAmount().equals(Integer.parseInt(amount+""))){
			full=true;
			refundRate = BigDecimal.ONE;
		}else{
			RefundPaymentExample refundPaymentExample = new RefundPaymentExample();
			refundPaymentExample.createCriteria().andStatusEqualTo(Validity.AVAIL.code())
				.andPaymentNoEqualTo(paymentDto.getPayNo()).andAppInfoIdEqualTo(paymentDto.getAppInfoId())
				.andStatusEqualTo(1);

			long refundAmount = refundPaymentService.getList(refundPaymentExample).stream()
				.map(RefundPaymentDto::getApplyAmount).reduce(Math::addExact).orElse(0L);
			long unRefundAmount = refundAmount+amount - paymentDto.getAmount();
			if(unRefundAmount<=1 && unRefundAmount>=-1){//剩余退款误差一分内，算错本次退完
				full = true;
			}
			refundRate = BigDecimal.valueOf(amount).divide(BigDecimal.valueOf(paymentDto.getAmount()),6,
				BigDecimal.ROUND_HALF_DOWN);

			log.info("refundRate == {}",refundRate);
		}

		//1.微信平台费
		Integer weiFee =
			new BigDecimal(weiRate).multiply(BigDecimal.valueOf(paymentDto.getAmount())).setScale(2,BigDecimal.ROUND_HALF_DOWN).intValue();
		if(weiFee<=0){
			weiFee=1;
		}
		log.info("paymentId == {}, weiFee =={}", paymentId, weiFee);


		//2.平台服务费
		Integer platFormDee = platFormFee(appInfoDto, baseAmount);
		int refundPlatFormFee =  BigDecimal.valueOf(platFormDee).multiply(refundRate).setScale(2,BigDecimal.ROUND_HALF_DOWN).intValue();
		refundPlatFormFee(paymentId, refundPlatFormFee, full);
		log.info("refund paymentId == {}, refundPlatFormFee =={}", paymentId, refundPlatFormFee);

		//3.供货商款项
		Integer supplierFee = 0;
		if(appInfoDto.getPayManager() == Boolean.TRUE){
			supplierFee = supplierFee(orderNo);
		}
		log.info("paymentId == {}, supplierFee =={}", paymentId, supplierFee);

		if(supplierFee>0){
			int refundSupplierFee = BigDecimal.valueOf(supplierFee).multiply(refundRate).setScale(2,BigDecimal.ROUND_HALF_DOWN).intValue();
			refundPSupplierFee(paymentId, refundSupplierFee, full);
		}

		//4.游客分销费用(转发)
		Integer shareFee = 0;
		log.info("isPaySaler == {}", appInfoDto.getPaySaler());
		if(appInfoDto.getPaySaler() == Boolean.TRUE){
			shareFee = shareFee(orderNo);
			int refundshareFee = BigDecimal.valueOf(shareFee).multiply(refundRate).setScale(2,BigDecimal.ROUND_HALF_DOWN).intValue();
			if(shareFee>0){
				log.info("refund paymentId == {}, refundshareFee =={}", paymentId, refundshareFee);
				refundShareFee(paymentId,refundshareFee,full);
			}
		}


		//5.应该给商家计算的金额
		Integer merchantFee = paymentDto.getAmount()-weiFee-platFormDee-supplierFee-shareFee;
		int refundMerchantFee = BigDecimal.valueOf(merchantFee).multiply(refundRate).intValue();
		refundMerchantFee(paymentId, refundMerchantFee, full);
	}
}
