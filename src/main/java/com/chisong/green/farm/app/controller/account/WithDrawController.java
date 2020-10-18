package com.chisong.green.farm.app.controller.account;

import com.chisong.green.farm.app.controller.account.request.WithDrawApplyReq;
import com.chisong.green.farm.app.controller.response.CCResponse;
import com.chisong.green.farm.app.dto.CustomerInfoDto;
import com.chisong.green.farm.app.miniProgram.response.LoginResponse;
import com.chisong.green.farm.app.service.AccountInfoService;
import com.chisong.green.farm.app.service.CustomerInfoService;
import com.chisong.green.farm.app.utils.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 提现管理controller
 * @AUTHOR 孙龙云
 * @date 2020-10-07 16:39
 */
@RestController
@RequestMapping("widthdraw")
public class WithDrawController {
	@Autowired
	private AccountInfoService accountInfoService;
	@Autowired
	private CustomerInfoService customerInfoService;

	/**
	 * 提现申请
	 * @param withDrawReq
	 * @return
	 */
	@RequestMapping("/apply")
	public CCResponse apply(@RequestBody WithDrawApplyReq withDrawReq){
		if(null == withDrawReq.getAmount() || withDrawReq.getAmount() <5){
			return CCResponse.fail("提现金额至少5元");
		}
		if(StringUtils.isEmpty(withDrawReq.getRealName()) || StringUtils.isEmpty(withDrawReq.getMobile())){
			return CCResponse.fail("请填写真实姓名和手机号");
		}
		LoginResponse loginResponse = CurrentUserUtils.get();

		if(null == loginResponse){
			return CCResponse.success();
		}
		CustomerInfoDto customerInfoDto = customerInfoService.getCustomerInfoDtoByOpenId(loginResponse.getOpenid());

		//提现申请
		withDrawReq.setCustomerId(Integer.parseInt(customerInfoDto.getId()+"") );
		accountInfoService.applyAmount(withDrawReq);
		return CCResponse.success();
	}
}
