package com.caichao.chateau.app.service;

import com.caichao.chateau.app.dto.OrderInfoDto;
import com.lianshang.generator.commons.IService;
import com.caichao.chateau.app.dto.CustomerInfoDto;
/**
 * <p>
 * 顾客信息表 服务类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-06-07
 */
public interface CustomerInfoService extends IService<CustomerInfoDto> {

	/**
	 * 更新顾客状态
	 * @param id
	 * @param status
	 */
	public void changeStatus(Long id, Integer status);

	/**
	 * 根据openId查询
	 * @param openId
	 * @return
	 */
	public CustomerInfoDto getCustomerInfoDtoByOpenId(String openId);

	/**
	 * 根据用户名称查询
	 * @param userName
	 * @return
	 */
	public CustomerInfoDto getAdminUserByUserName(String userName);
}
