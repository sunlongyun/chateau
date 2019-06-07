package com.caichao.chateau.app.miniProgram.response;

import com.caichao.chateau.app.miniProgram.annotaion.MiniFiled;
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
	@MiniFiled("openid")
	private String openid;
	@MiniFiled("session_key")
	private String sessionKey;
	/**
	 * 用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。
	 */
	@MiniFiled("unionid")
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
	@MiniFiled("errcode")
	private Number errCode;
	/**
	 * 错误信息
	 */
	@MiniFiled("errmsg")
	private String errMsg;
}
