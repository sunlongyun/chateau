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
public class LoginResponse extends ParentResponse {

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

	@Override
	public String toString() {
		return "LoginResponse{" +
			"openid='" + openid + '\'' +
			", sessionKey='" + sessionKey + '\'' +
			", unionId='" + unionId + '\'' +
			", errCode=" + errCode +
			", errMsg='" + errMsg + '\'' +
			'}';
	}
}
