package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.annotation.AmountUnitChange;
import com.chisong.green.farm.app.constants.enums.Validity;
import com.chisong.green.farm.app.dto.CustomerInfoDto;
import com.chisong.green.farm.app.entity.AccountInfo;
import com.chisong.green.farm.app.dto.AccountInfoDto;
import com.chisong.green.farm.app.example.AccountInfoExample;
import com.chisong.green.farm.app.mapper.AccountInfoMapper;
import com.chisong.green.farm.app.service.AccountInfoService;

import com.lianshang.generator.commons.ServiceImpl;
import java.util.Map;
import java.util.Optional;
import javax.xml.bind.ValidationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
			accountInfoDto.setRealName(customerInfoDto.getUserName());
			accountInfoDto.setNickName(customerInfoDto.getNickName());
			accountInfoDto.setType(1);
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
}