package com.chisong.green.farm.app.miniProgram.msg;

import lombok.Data;

/**
 * 描述:
 * 文本消息
 * @AUTHOR 孙龙云
 * @date 2020-02-29 17:26
 */
@Data
public class TextMsg extends ParentMsg {

	/**
	 * 文本内容
	 */
	private Content text;
	/**
	 * 内容
	 */
	@Data
	public 	static class Content{
		private String content;
	}

	public TextMsg() {
		this.setMsgtype("text");
	}


}


