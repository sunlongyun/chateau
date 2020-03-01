package com.chisong.green.farm.app.service;

import com.chisong.green.farm.app.miniProgram.msg.ParentMsg;
import com.chisong.green.farm.app.miniProgram.msg.TextMsg;

/**
 * 客服中心
 */
public interface CustomerCenterService {

	/**
	 * 回复顾客信息
	 * @param parentMsg
	 */
	public void sendToCustomer(ParentMsg parentMsg);

}
