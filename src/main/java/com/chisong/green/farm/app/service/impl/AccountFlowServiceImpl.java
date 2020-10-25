package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.dto.AccountInfoDto;
import com.chisong.green.farm.app.dto.MerchantPaymentDto;
import com.chisong.green.farm.app.entity.AccountFlow;
import com.chisong.green.farm.app.dto.AccountFlowDto;
import com.chisong.green.farm.app.mapper.AccountFlowMapper;
import com.chisong.green.farm.app.service.AccountFlowService;

import com.chisong.green.farm.app.service.AccountInfoService;
import com.chisong.green.farm.app.service.MerchantPaymentService;
import com.lianshang.generator.commons.ServiceImpl;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 账户流水 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2020-05-04
 */
@Service
@Slf4j
public class AccountFlowServiceImpl extends ServiceImpl<AccountFlowMapper,AccountFlow, AccountFlowDto> implements AccountFlowService {
	@Autowired
	private AccountInfoService accountInfoService;

	@Autowired
	private MerchantPaymentService merchantPaymentService;

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public void inCome(AccountFlowDto accountFlowDto) {
		if(accountFlowDto.getStatus() != 0){
			log.info("入账失败, flowId={}",accountFlowDto.getId());
			//throw new RuntimeException("入账失败, flowId="+accountFlowDto.getId())
			return;
		}

		this.update(accountFlowDto);
		accountFlowDto = getById(accountFlowDto.getId());
		if(accountFlowDto.getStatus() == 0){
			accountFlowDto.setStatus(1);
			this.update(accountFlowDto);

			AccountInfoDto accountInfoDto = accountInfoService.getById(accountFlowDto.getAccountId());
			accountInfoDto.setTotalAmount(accountInfoDto.getTotalAmount()+accountFlowDto.getAmount());
			accountInfoDto.setAvailableAmount(accountInfoDto.getAvailableAmount()+accountFlowDto.getAmount());
			int recordedAmount = accountInfoDto.getRecordedAmount()-accountFlowDto.getAmount();
			if(recordedAmount <0){
				log.info("入账失败, flowId={},待入账金额更新为负数{}",accountFlowDto.getId(),recordedAmount );
				throw new RuntimeException("入账失败, flowId="+accountFlowDto.getId()+",待入账金额更新为负数"+recordedAmount);
			}
			accountInfoDto.setRecordedAmount(recordedAmount);

			accountInfoService.update(accountInfoDto);
		}



	}

	@Override
	@Transactional
	public void withdraw(AccountFlowDto accountFlowDto) {
		if(accountFlowDto.getStatus() != 0 || accountFlowDto.getSource() !=1 ){
			log.info("提现失败, flowId={}",accountFlowDto.getId());
			return;
		}

		accountFlowDto.setStatus(1);
		this.update(accountFlowDto);
		AccountInfoDto accountInfoDto = accountInfoService.getById(accountFlowDto.getAccountId());
		accountInfoDto.setForezenAmount(accountInfoDto.getForezenAmount()-accountFlowDto.getAmount());
		accountInfoDto.setTotalAmount(accountInfoDto.getTotalAmount() - accountFlowDto.getAmount());
		if(accountInfoDto.getForezenAmount() <0 || accountInfoDto.getTotalAmount() <0){
			log.info("提现失败，冻结余额出现负值, accountId={}", accountInfoDto.getId());
			throw new RuntimeException("提现失败，冻结余额出现负值, accountId="+accountInfoDto.getId());
		}
		accountInfoService.update(accountInfoDto);


		MerchantPaymentDto merchantPaymentDto = new MerchantPaymentDto();
		merchantPaymentDto.setStatus(0);
		merchantPaymentDto.setPreTransferTime(new Date());
		merchantPaymentDto.setAmount(accountFlowDto.getAmount());
		merchantPaymentDto.setTradeNo("ws"+System.currentTimeMillis()+accountFlowDto.getId());
		merchantPaymentDto.setOpenId(accountInfoDto.getOpenId());
		merchantPaymentDto.setUserType(0);
		merchantPaymentDto.setPayType(6);
		merchantPaymentDto.setUserName(accountInfoDto.getRealName());
		merchantPaymentDto.setRemark("余额提现");
		merchantPaymentDto.setAppInfoId(accountInfoDto.getAppInfoId());

		merchantPaymentService.save(merchantPaymentDto);
	}
}