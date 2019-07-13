package com.caichao.chateau.app.listener;

import com.caichao.chateau.app.constants.enums.Validity;
import com.caichao.chateau.app.example.CountryChateauExample;
import com.caichao.chateau.app.service.CountryChateauService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 描述:
 * 容器初始化完成事件
 *
 * @AUTHOR 孙龙云
 * @date 2019-07-09 21:25
 */
//@Component
@Slf4j
public class FreshTask {

	@Autowired
	private CountryChateauService countryChateauService;


	@Scheduled(cron = "0 0/10 * * * ?")
	public void onApplicationEvent() {
		//更新时间在十分钟以上，状态在直播中的记录，状态更新为结束直播
		CountryChateauExample countryChateauExample = new CountryChateauExample();
		countryChateauExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code());

		countryChateauService.getList(countryChateauExample).forEach(countryChateauDto -> {
			if(countryChateauDto.getUpdateTime().getTime() < (System.currentTimeMillis() - 10 * 60 * 1000)
				&& (countryChateauDto.getDailyBroadcastIng() == 1
				|| countryChateauDto.getMasterBroadcastIng() == 1)) {
				countryChateauDto.setMasterBroadcastIng(0);
				countryChateauDto.setDailyBroadcastIng(0);
				countryChateauService.update(countryChateauDto);
			}

		});

	}
}
