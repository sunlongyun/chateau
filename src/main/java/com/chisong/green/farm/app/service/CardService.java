package com.chisong.green.farm.app.service;

import java.util.List;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2020-07-18 15:16
 */
public interface CardService {

	/**
	 * 发送卡券到制定顾客
	 * @param cardId
	 * @param openIdList
	 */
	void sendCard(String cardId, List<String> openIdList);

	/**
	 * 给所有用户发送卡券
	 * @param cardId
	 */
	void sendCardToAll(String cardId);

	/**
	 * 给开通账户的用户发送卡券
	 * @param cardId
	 */
	void sendCardToAccountUser(String cardId);
}
