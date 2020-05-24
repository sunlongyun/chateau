package com.chisong.green.farm.app.controller.account;

import lombok.Data;

/**
 * 描述:
 *账户流水分页查询条件
 * @AUTHOR 孙龙云
 * @date 2020-05-23 14:48
 */
@Data
public class AccountFlowRequest {

	/**
	 * 当前页码
	 */
	private Integer pageNo=1;
	/**
	 * 每页显示数量
	 */
	private Integer pageSize = 10;
}
