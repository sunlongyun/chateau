package com.caichao.chateau.app.miniProgram.response;

import com.caichao.chateau.app.miniProgram.annotaion.MiniFiled;
import lombok.Data;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-07 18:33
 */
@Data
public class AccessCodeResponse extends ParentResponse{

	/**
	 * 凭证
	 */
	@MiniFiled("access_token")
	private String accessToken;
	/**
	 * 凭证有效时间
	 */
	@MiniFiled("expires_in")
	private Number expiresIn;
}
