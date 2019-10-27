package com.caichao.chateau.app.controller.customer.request;

import lombok.Data;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-09 17:21
 */
@Data
public class FreshCustomerReq {

	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 国家
	 */
	private String country;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 头像
	 */
	private String avatarUrl;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 推荐人id
	 */
	private String recommendId;
}
