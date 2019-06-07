package com.caichao.chateau.app.miniProgram.response;

import lombok.Data;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-07 16:23
 */
@Data
public class LoginResponse {

	/**
	 * 用户唯一标识
	 */
	private String openid;
	private String sessionKey;
	/**
	 * 用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。
	 */
	private String unionId;
	/**
	 * 错误码
	 * errcode 的合法值
	 值	说明	最低版本
	 -1	系统繁忙，此时请开发者稍候再试
	 0	请求成功
	 40029	code 无效
	 45011	频率限制，每个用户每分钟100次
	 */
	private String errCode;
	/**
	 * 错误信息
	 */
	private String errMsg;
}
