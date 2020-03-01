package com.chisong.green.farm.app.miniProgram.msg;

import lombok.Data;

/**
 * 描述:
 * 图文连接
 * @AUTHOR 孙龙云
 * @date 2020-03-01 15:00
 */
@Data
public class LinkMessage extends ParentMsg {

	public LinkMessage(){
		this.setMsgtype("link");
	}
}
