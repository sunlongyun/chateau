package com.chisong.green.farm.app.controller.account.request;

import java.math.BigDecimal;
import lombok.Data;

/**
 * 描述:
 * 提现申请
 * @AUTHOR 孙龙云
 * @date 2020-07-01 14:48
 */
@Data
public class WithDrawApplyReq {

	/**
	 * 提现申请金额,单位:元
	 */
	private BigDecimal amount;

	/**
	 * 当前用户
	 */
	private Integer customerId;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 微信绑定手机号
	 */
	private String mobile;


}
