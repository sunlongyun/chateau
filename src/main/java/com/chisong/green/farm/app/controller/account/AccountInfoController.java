package com.chisong.green.farm.app.controller.account;

import com.chisong.green.farm.app.constants.enums.Validity;
import com.chisong.green.farm.app.controller.response.CCResponse;
import com.chisong.green.farm.app.dto.AccountInfoDto;
import com.chisong.green.farm.app.dto.CustomerInfoDto;
import com.chisong.green.farm.app.example.AccountFlowExample;
import com.chisong.green.farm.app.miniProgram.response.LoginResponse;
import com.chisong.green.farm.app.service.AccountFlowService;
import com.chisong.green.farm.app.service.AccountInfoService;
import com.chisong.green.farm.app.service.CustomerInfoService;
import com.chisong.green.farm.app.utils.CurrentUserUtils;
import com.lianshang.generator.commons.PageInfo;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2020-05-23 14:28
 */
@RestController
@RequestMapping("/account")
@Slf4j
public class AccountInfoController {

	@Autowired
	private AccountInfoService accountInfoService;

	@Autowired
	private AccountFlowService accountFlowService;

	@Autowired
	private CustomerInfoService customerInfoService;

	/**
	 * 统计天周月业绩汇总
	 * @return
	 */
	@RequestMapping("/dayWeekMonthSummaryInfo")
	public CCResponse getDayWeekMonthSummaryInfo(){
		LoginResponse loginResponse = CurrentUserUtils.get();
		CustomerInfoDto customerInfoDto = customerInfoService.getCustomerInfoDtoByOpenId(loginResponse.getOpenid());

		return  CCResponse.success(accountInfoService.getDayWeekMonthSummaryInfo(customerInfoDto.getId()));
	}

	/**
	 * 申请提现
	 * @param amount
	 * @return
	 */
	@RequestMapping("/withdraw")
	public CCResponse widthDraw(Integer amount){
		log.info("申请提现{}元", amount);
		return CCResponse.fail("该功能暂未开启，敬请期待");
	}
	/**
	 * 收入明细
	 * @return
	 */
	@RequestMapping("details")
	public CCResponse getDetails(AccountFlowRequest accountFlowRequest){
		LoginResponse loginResponse = CurrentUserUtils.get();
		if(null == loginResponse){
			loginResponse = new LoginResponse();
			loginResponse.setOpenid("oqrTq4jLQt0I_9F4vQVQLQGDrBbM");
		}
		CustomerInfoDto customerInfoDto = customerInfoService.getCustomerInfoDtoByOpenId(loginResponse.getOpenid());
		AccountInfoDto accountInfoDto =
			accountInfoService.getAccountInfoDtoByCustomerId(Integer.parseInt(customerInfoDto.getId()+
			"") );
		if(null == accountInfoDto){
			return CCResponse.fail("账户不存在,请先进行登录授权");
		}

		AccountFlowExample accountFlowExample = new AccountFlowExample();
		accountFlowExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
			.andSourceEqualTo(0).andTypeEqualTo(1).andAccountIdEqualTo(accountInfoDto.getId());
		accountFlowExample.setOrderByClause("id desc");

	 PageInfo pageInfo =	accountFlowService.getPageInfo(accountFlowRequest.getPageNo(),
		 accountFlowRequest.getPageSize(),
		    accountFlowExample);
		return CCResponse.success(pageInfo);
	}

	/**
	 * 收益汇总
	 * @return
	 */
	@RequestMapping("profitSummary")
	public CCResponse profitSummary(){
		LoginResponse loginResponse = CurrentUserUtils.get();

		CustomerInfoDto customerInfoDto = customerInfoService.getCustomerInfoDtoByOpenId(loginResponse.getOpenid());

		AccountInfoDto accountInfoDto =
			accountInfoService.getAccountInfoDtoByCustomerId(Integer.parseInt(customerInfoDto.getId()+
			"") );
		if(null == accountInfoDto){
			return CCResponse.fail("账户不存在，登录授权后自动开户");
		}
		Map<String,Object> summaryInfo = new HashMap<>();
		summaryInfo.put("accountAmount", accountInfoDto.getTotalAmount()/100);
		summaryInfo.put("shareAmount", accountInfoDto.getShareAmount()/100);
		summaryInfo.put("availableAmount", accountInfoDto.getAvailableAmount()/100);
		summaryInfo.put("recordedAmount", accountInfoDto.getRecordedAmount()/100);
		summaryInfo.put("friendsNum", accountInfoDto.getFriendsNum());

		return CCResponse.success(summaryInfo);
	}
}
