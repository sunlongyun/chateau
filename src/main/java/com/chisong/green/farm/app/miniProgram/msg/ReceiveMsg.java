package com.chisong.green.farm.app.miniProgram.msg;

import com.chisong.green.farm.app.miniProgram.annotaion.MiniFiled;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2020-02-29 18:00
 */
@Data
public class ReceiveMsg {

	/**
	 * 小程序原始id
	 */
	@MiniFiled("ToUserName")
	private String toUserName;

	/**
	 * 发送者的openId
	 */
	@MiniFiled("FromUserName")
	private String FromUserName;


	/**
	 * 消息类型
	 */
	@MiniFiled("MsgType")
	private String msgType;

	/**
	 * 消息内容
	 */
	@MiniFiled("Content")
	private String content;

	/**
	 * 消息id
	 */
	@MiniFiled("MsgId")
	private Double msgId;

	public Long getMsgId(){
		return msgId.longValue();
	}
	/**
	 * 创建时间
	 */
	@MiniFiled("CreateTime")
	private Double createTime;

	public Long getCreateTime(){
		return createTime.longValue();
	}
}
