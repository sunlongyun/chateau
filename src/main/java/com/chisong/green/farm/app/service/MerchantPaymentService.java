package com.chisong.green.farm.app.service;

import com.lianshang.generator.commons.IService;
import com.chisong.green.farm.app.dto.MerchantPaymentDto;
/**
 * <p>
 * 商户支付流水 服务类
 * </p>
 *
 * @author 孙龙云
 * @since 2020-10-05
 */
public interface MerchantPaymentService extends IService<MerchantPaymentDto> {

	/**
	 * 付款
	 * @param id
	 */
	void pay(Long id);
}
