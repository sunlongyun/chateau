package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.annotation.AmountUnitChange;
import com.chisong.green.farm.app.constants.enums.Validity;
import com.chisong.green.farm.app.controller.account.request.WithDrawApplyReq;
import com.chisong.green.farm.app.dto.AccountFlowDto;
import com.chisong.green.farm.app.dto.CustomerInfoDto;
import com.chisong.green.farm.app.entity.AccountFlow;
import com.chisong.green.farm.app.entity.AccountInfo;
import com.chisong.green.farm.app.dto.AccountInfoDto;
import com.chisong.green.farm.app.entity.CustomerInfo;
import com.chisong.green.farm.app.entity.WithDrawApply;
import com.chisong.green.farm.app.example.AccountInfoExample;
import com.chisong.green.farm.app.example.WithDrawApplyExample;
import com.chisong.green.farm.app.mapper.AccountFlowMapper;
import com.chisong.green.farm.app.mapper.AccountInfoMapper;
import com.chisong.green.farm.app.mapper.CustomerInfoMapper;
import com.chisong.green.farm.app.mapper.WithDrawApplyMapper;
import com.chisong.green.farm.app.service.AccountInfoService;

import com.chisong.green.farm.app.utils.AppUtils;
import com.lianshang.generator.commons.ServiceImpl;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import javax.xml.bind.ValidationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 供应商账户信息 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2020-05-04
 */
@Service
public class AccountInfoServiceImpl extends ServiceImpl<AccountInfoMapper,AccountInfo, AccountInfoDto> implements AccountInfoService {

	@Value("${appid}")
	private String dingdangAppId;

	@Autowired
	private AccountInfoMapper accountInfoMapper;

	@Autowired
	private AccountFlowMapper accountFlowMapper;

	@Autowired
	private WithDrawApplyMapper withDrawApplyMapper;

	@Autowired
	private CustomerInfoMapper customerInfoMapper;

	@Override
	public AccountInfoDto getAccountInfoDtoByOpenId(String openId) {
		AccountInfoExample accountInfoExample = new AccountInfoExample();
		accountInfoExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
			.andOpenIdEqualTo(openId);
		Optional<AccountInfoDto> accountInfoDtoOptional =  this.getList(accountInfoExample).stream().findFirst();
		if(accountInfoDtoOptional.isPresent()){
			return accountInfoDtoOptional.get();
		}
		return null;
	}

	@Override
	public void createAccountInfo(CustomerInfoDto customerInfoDto) {
		AccountInfoDto accountInfoDto = getAccountInfoDtoByCustomerId(Integer.parseInt(customerInfoDto.getId()+""));
		if(null == accountInfoDto){
			accountInfoDto = new AccountInfoDto();
			accountInfoDto.setCusotmerId(Integer.parseInt(customerInfoDto.getId()+""));
			accountInfoDto.setOpenId(customerInfoDto.getOpenId());
			String userName = customerInfoDto.getUserName();
			if(StringUtils.isEmpty(userName)){
				userName = customerInfoDto.getNickName();
			}
			accountInfoDto.setRealName(userName);
			accountInfoDto.setNickName(customerInfoDto.getNickName());
			accountInfoDto.setMobile(customerInfoDto.getMobile());
			accountInfoDto.setType(1);
			Long apId = AppUtils.get();
			accountInfoDto.setAppInfoId(apId);
			save(accountInfoDto);
		}
	}


	@Override
	public AccountInfoDto getAccountInfoDtoByCustomerId(Integer customerId) {
		AccountInfoExample accountInfoExample = new AccountInfoExample();
		accountInfoExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
			.andCusotmerIdEqualTo(customerId);
		Optional<AccountInfoDto> accountInfoDtoOptional =  this.getList(accountInfoExample).stream().findFirst();
		if(accountInfoDtoOptional.isPresent()){
			return accountInfoDtoOptional.get();
		}
		return null;
	}

	@Override
	public AccountInfoDto getDingdangApp() {
		return getAccountInfoDtoByOpenId(dingdangAppId);
	}

	@Override
	public Map<String, Object> getDayWeekMonthSummaryInfo(Long customerId) {
		return accountInfoMapper.getDayWeekMonthSummaryInfo(customerId);
	}

	@Override
	@Transactional
	public void applyAmount(WithDrawApplyReq withDrawApplyReq) {
		AccountInfoDto accountInfoDto= getAccountInfoDtoByCustomerId(withDrawApplyReq.getCustomerId());
		if(null == accountInfoDto){
			throw new RuntimeException("账户不存在，请先申请开户");
		}
		WithDrawApplyExample withDrawApplyExample = new WithDrawApplyExample();
		withDrawApplyExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
			.andAccountIdEqualTo(accountInfoDto.getId()).andStatusEqualTo(0);

	    boolean exists = withDrawApplyMapper.selectByExample(withDrawApplyExample).stream().findAny().isPresent();
		if(exists){
			throw new RuntimeException("有一笔提现申请正在处理中，请到账后再发起新的请求!");
		}
	  //1.冻结申请金额
		int amount =
			BigDecimal.valueOf(withDrawApplyReq.getAmount()).multiply(BigDecimal.valueOf(100))
				.setScale(2,BigDecimal.ROUND_HALF_EVEN).intValue();

		int r =	accountInfoMapper.frozenAmount(amount, accountInfoDto.getId());
		if(r <=0){
			throw new RuntimeException("提现申请失败,可能账户余额不足!");
		}
		accountInfoDto = getById( accountInfoDto.getId());

		//2.添加提现流水明细
		saveAccountFlow(accountInfoDto, amount);

		//3. 添加提现申请
		saveApply(withDrawApplyReq, accountInfoDto, amount);

		//4. 如果必要，更新用户姓名和手机号
		updateBaseInfo(withDrawApplyReq, accountInfoDto);
	}

	private void updateBaseInfo(WithDrawApplyReq withDrawApplyReq, AccountInfoDto accountInfoDto) {
		if(!withDrawApplyReq.getMobile().equals(accountInfoDto.getMobile()) ||
			withDrawApplyReq.getRealName().equals(accountInfoDto.getRealName())){
			accountInfoDto.setRealName(withDrawApplyReq.getRealName());
			accountInfoDto.setMobile(withDrawApplyReq.getMobile());
			this.update(accountInfoDto);

			CustomerInfo customerInfo = new CustomerInfo();
			customerInfo.setId(Long.parseLong(accountInfoDto.getCusotmerId()+""));
			customerInfo.setMobile(withDrawApplyReq.getMobile());
			customerInfoMapper.updateById(customerInfo);
		}
	}

	private void saveApply(WithDrawApplyReq withDrawApplyReq, AccountInfoDto accountInfoDto, int amount) {
		WithDrawApply withDrawApply = new WithDrawApply();
		withDrawApply.setAccountId(accountInfoDto.getId());
		withDrawApply.setAmount(amount);
		withDrawApply.setStatus(0);
		withDrawApply.setNickName(accountInfoDto.getNickName());
		withDrawApply.setRealName(withDrawApplyReq.getRealName());
		withDrawApply.setMobile(withDrawApplyReq.getMobile());
		withDrawApply.setAppInfoId(AppUtils.get());
		withDrawApplyMapper.insert(withDrawApply);
	}

	private void saveAccountFlow(AccountInfoDto accountInfoDto, int amount) {
		AccountFlow accountFlow = new AccountFlow();
		accountFlow.setAccountId(accountInfoDto.getId());
		accountFlow.setAmount(amount);
		String desc="[余额提现]";
		accountFlow.setOperateName(desc);
		accountFlow.setSource(1);
		accountFlow.setStatus(0);
		accountFlow.setType(0);
		accountFlow.setAppInfoId(AppUtils.get());

		accountFlowMapper.insert(accountFlow);
	}

}