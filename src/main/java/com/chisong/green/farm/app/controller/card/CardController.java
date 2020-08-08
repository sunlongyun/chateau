package com.chisong.green.farm.app.controller.card;

import com.chisong.green.farm.app.controller.response.CCResponse;
import com.chisong.green.farm.app.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * 卡券管理controller
 * @AUTHOR 孙龙云
 * @date 2020-07-18 15:02
 */
@RestController
@RequestMapping("card")
public class CardController {
	@Autowired
	private CardService cardService;

	/**
	 * 给顾客推送卡券
	 * @return
	 */
	@RequestMapping("sendCard")
	public CCResponse sendCard(@RequestBody CardSendRequest cardSendRequest){
		cardService.sendCard(cardSendRequest.getCardId(), cardSendRequest.getOpenIds());
		return CCResponse.success();
	}

	/**
	 * 给顾客推送卡券
	 * @return
	 */
	@RequestMapping("sendCardToAll")
	public CCResponse sendCardToAll(@RequestBody CardSendRequest cardSendRequest){
		cardService.sendCardToAll(cardSendRequest.getCardId());
		return CCResponse.success();
	}

	/**
	 * 给顾客推送卡券
	 * @return
	 */
	@RequestMapping("sendCardToAccountUser")
	public CCResponse sendCardToAccountUser(@RequestBody CardSendRequest cardSendRequest){
		cardService.sendCardToAccountUser(cardSendRequest.getCardId());
		return CCResponse.success();
	}
}
