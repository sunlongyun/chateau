package com.chisong.green.farm.app.miniProgram.msg;

import com.chisong.green.farm.app.miniProgram.annotaion.MiniFiled;
import lombok.Data;

/**
 * 描述:
 * 客服中心消息
 * @AUTHOR 孙龙云
 * @date 2020-02-29 17:07
 */
@Data
public abstract class ParentMsg {

	@MiniFiled("access_token")
	private String accessToken;
	/**
	 * 发送者openId
	 */
	private String touser;
	/**
	 * 消息类型
	 */
	private String msgtype;

}
