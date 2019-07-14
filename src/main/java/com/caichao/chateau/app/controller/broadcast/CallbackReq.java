package com.caichao.chateau.app.controller.broadcast;

import lombok.Data;

/**
 * 描述:
 * 直播回调入参
 * @AUTHOR 孙龙云
 * @date 2019-07-13 22:35
 */
@Data
public class CallbackReq{
	private String app;
	private String appid;
	private String appname;
	private String channel_id;
	private String errcode;
	private String errmsg;
	private String event_time;
	private String stream_id;
}
