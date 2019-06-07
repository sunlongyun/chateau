package com.caichao.chateau.app.miniProgram.request;

import com.caichao.chateau.app.miniProgram.annotaion.MiniFiled;
import lombok.Data;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-07 16:20
 */
@Data
public class LoginReq {

	/**
	 * 小程序 appId
	 */
	@MiniFiled("appid")
	private String appId;
	/**
	 * 小程序 appSecret
	 */
	private String secret;
	/**
	 * 登录时获取的 code
	 */
	@MiniFiled("js_code")
	private String jsCode;
	/**
	 * 授权类型，此处只需填写 authorization_code
	 返回值
	 */
	@MiniFiled("grant_type")
	private String grantType ="authorization_code";
}
