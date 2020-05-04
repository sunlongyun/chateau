package com.chisong.green.farm.app.service;

import com.lianshang.generator.commons.IService;
import com.chisong.green.farm.app.dto.AccountInfoDto;
/**
 * <p>
 * 账户信息 服务类
 * </p>
 *
 * @author 孙龙云
 * @since 2020-05-04
 */
public interface AccountInfoService extends IService<AccountInfoDto> {

	/**
	 * 根据openId查询
	 * @param openId
	 * @return
	 */
	public AccountInfoDto getAccountInfoDtoByOpenId(String openId);

	/**
	 * 根据顾客id查询账户信息
	 * @param customerId
	 * @return
	 */
	public AccountInfoDto getAccountInfoDtoByCustomerId(Integer customerId);

	/**
	 * 获取叮当农场的账号
	 * @return
	 */
	public AccountInfoDto getDingdangApp();
}
