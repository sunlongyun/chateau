package com.chisong.green.farm.app.service;

import com.lianshang.generator.commons.IService;
import com.chisong.green.farm.app.dto.AccountFlowDto;
/**
 * <p>
 * 账户流水 服务类
 * </p>
 *
 * @author 孙龙云
 * @since 2020-05-04
 */
public interface AccountFlowService extends IService<AccountFlowDto> {

	/**
	 * 入账
	 * @param accountFlowDto
	 */
	void inCome(AccountFlowDto accountFlowDto);

	/**
	 * 提现
	 * @param accountFlowDto
	 */
	void withdraw(AccountFlowDto accountFlowDto);
}
