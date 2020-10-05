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
import com.chisong.green.farm.app.example.OrderDetailExample;
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
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlEnumValue;
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
public class PayToOrderImpl implements PayToOrderInterceptor {
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

	@Override
	@Transactional
	public void handle(Long paymentId) {
		PaymentDto paymentDto = paymentService.getById(paymentId);
		if(null == paymentDto){
			throw new RuntimeException("支付记录不存在");
		}

		if(paymentDto.getStatus() != 1){
			throw new RuntimeException("支付成功的流水才可以计算费项");
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

		//5.管理费
		if(appInfoDto.getPayManager() ==  Boolean.TRUE){
			//TODO 平台管理费暂时不需要
		}


		//6.应该给商家计算的金额
		Integer merchantFee = paymentDto.getAmount()-weiFee-platFormDee-supplierFee-shareFee;
		saveMerchantFee(paymentId, merchantFee, appInfoDto.getOpenId());
	}

	/**
	 * 商户结算
	 * @param paymentId
	 * @param machantFee
	 */
	private void saveMerchantFee(Long paymentId, Integer machantFee, String openId) {
		MerchantPaymentDto merchantPaymentDto = new MerchantPaymentDto();
		merchantPaymentDto.setStatus(0);
		merchantPaymentDto.setAmount(machantFee);
		merchantPaymentDto.setOpenId(openId);
		merchantPaymentDto.setPayId(paymentId);
		merchantPaymentDto.setPayType(1);
		merchantPaymentDto.setUserType(1);
		merchantPaymentDto.setTradeNo("sc_"+System.currentTimeMillis()+"_"+paymentId);
		merchantPaymentDto.setRemark("顾客付款结算");
		merchantPaymentService.save(merchantPaymentDto);
	}

	/**
	 * 平台服务费
	 * @param paymentId
	 * @param platFormDee
	 */
	private void savePlatFormFee(Long paymentId, Integer platFormDee) {
		MerchantPaymentDto merchantPaymentDto = new MerchantPaymentDto();
		merchantPaymentDto.setStatus(0);
		merchantPaymentDto.setUserType(1);
		merchantPaymentDto.setTradeNo("pf_"+System.currentTimeMillis()+"_"+paymentId);
		merchantPaymentDto.setAmount(platFormDee);
		merchantPaymentDto.setOpenId("oqrTq4jLQt0I_9F4vQVQLQGDrBbM");
		merchantPaymentDto.setPayId(paymentId);
		merchantPaymentDto.setPayType(5);
		merchantPaymentDto.setRemark("平台服务费");
		merchantPaymentService.save(merchantPaymentDto);
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
		merchantPaymentDto.setTradeNo("sp_"+System.currentTimeMillis()+"_"+paymentId);
		merchantPaymentDto.setRemark("商品结款");
		//TODO 供货商openId
		merchantPaymentService.save(merchantPaymentDto);
	}
	/**
	 * 分销费用
	 * @param paymentId
	 * @param shareFee
	 */
	private void saveSharerFee(Long paymentId, Integer shareFee) {

		//一级代理商 66.6%，二级代理商33.3%
		Integer firstAmount = BigDecimal.valueOf(shareFee).multiply(new BigDecimal("0.666")).intValue();
		Integer secondAmount = shareFee - firstAmount;
		log.info("paymentId == {}, firstAmount=={}, secondAmount=={}", paymentId, firstAmount, secondAmount);

		PaymentDto paymentDto = paymentService.getById(paymentId);
		String orderNo = paymentDto.getPayOrderNo();
		OrderInfoDto orderInfoDto = orderInfoService.getOrderByNo(orderNo);
		Long customerId = orderInfoDto.getCustomerId();
		CustomerInfoDto customerInfoDto = customerInfoService.getById(customerId);
		Long recommendId = customerInfoDto.getRecommendId();
		if(recommendId == -1L || recommendId == null){
			return;
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
		//如果不存在二级代理商，则费用全部给一级代理商
		if(firstRecommend.getRecommendId() == null || firstRecommend.getRecommendId()==-1){
			firstAmount= shareFee;
		}
		accountFlowDto.setAmount(firstAmount);
		accountFlowDto.setType(1);
		accountFlowDto.setSource(0);
		accountFlowDto.setAccountId(accountInfoDto.getId());
		accountFlowDto.setOperateName(customerInfoDto.getNickName()+"完成了一笔购物交易.");
		accountFlowService.save(accountFlowDto);






		Long secondRecommendId = firstRecommend.getRecommendId();
		if(null == secondRecommendId || secondRecommendId==-1){
			return;
		}
		CustomerInfoDto customerInfoDto2 = customerInfoService.getById(secondRecommendId);
		AccountInfoDto accountInfoDto2 =
			accountInfoService.getAccountInfoDtoByCustomerId(Integer.parseInt(secondRecommendId+""));
		if(null == accountInfoDto2){//不存在就自动开户
			accountInfoService.createAccountInfo(customerInfoDto2);
			accountInfoDto2 =accountInfoService.getAccountInfoDtoByCustomerId(Integer.parseInt(secondRecommendId+""));
		}
		accountInfoDto2.setRecordedAmount(accountInfoDto.getRecordedAmount()+secondAmount);
		accountInfoService.update(accountInfoDto2);

		AccountFlowDto accountFlowDto2 = new AccountFlowDto();
		accountFlowDto2.setStatus(0);
		accountFlowDto2.setAmount(secondAmount);
		accountFlowDto2.setType(1);
		accountFlowDto2.setAccountId(accountInfoDto2.getId());
		accountFlowDto2.setSource(0);
		accountFlowDto2.setOperateName(customerInfoDto.getNickName()+"完成了一笔购物交易.");
		accountFlowService.save(accountFlowDto2);

	}

	private Integer shareFee(String orderNo){
		OrderDetailExample orderDetailExample = new OrderDetailExample();
		orderDetailExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
			.andOrderNoEqualTo(orderNo);

		return 	orderDetailService.getList(orderDetailExample).stream().map(orderDetailDto -> {
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
}
