package com.caichao.chateau.app.miniProgram.request;

import com.caichao.chateau.app.miniProgram.annotaion.MiniFiled;
import lombok.Data;

/**
 * 描述:
 * 登录请求参数
 * @AUTHOR 孙龙云
 * @date 2019-06-07 18:31
 */
@Data
public class AcccessCodeReq {
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
	 * 授权类型，此处只需填写 client_credential
	 返回值
	 */
	@MiniFiled("grant_type")
	private String grantType ="client_credential";
}
