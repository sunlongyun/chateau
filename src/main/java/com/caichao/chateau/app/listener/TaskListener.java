package com.caichao.chateau.app.listener;

import com.caichao.chateau.app.constants.enums.BroadCastStatusEnum;
import com.caichao.chateau.app.constants.enums.Validity;
import com.caichao.chateau.app.example.CountryChateauExample;
import com.caichao.chateau.app.example.CountryChateauExample.Criteria;
import com.caichao.chateau.app.service.CountryChateauService;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 描述:
 * 容器初始化完成事件
 *
 * @AUTHOR 孙龙云
 * @date 2019-07-09 21:25
 */
@Component
@Slf4j
public class TaskListener implements ApplicationListener {

	@Autowired
	private CountryChateauService countryChateauService;

	private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
		//更新时间在十分钟以上，状态在直播中的记录，状态更新为结束直播
		executorService.schedule(new Runnable() {
			@Override
			public void run() {
				CountryChateauExample countryChateauExample = new CountryChateauExample();
				countryChateauExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
					.andUpdateTimeLessThan(new Date(LocalDateTime.now().minus(10, ChronoUnit.MINUTES).getNano()))
					.andDailyBroadcastIngEqualTo(BroadCastStatusEnum.ING.code());


				Criteria criteria2 = countryChateauExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code());
				criteria2.andUpdateTimeLessThan(new Date(LocalDateTime.now().minus(10, ChronoUnit.MINUTES).getNano()));
				criteria2.andMasterBroadcastIngEqualTo(BroadCastStatusEnum.ING.code());
				countryChateauExample.or(criteria2);

				countryChateauService.getList(countryChateauExample).forEach(countryChateauDto -> {
					log.info("countryChateauDto：{} has outline", countryChateauDto);
					countryChateauDto.setMasterBroadcastIng(0);
					countryChateauDto.setDailyBroadcastIng(0);

					countryChateauService.update(countryChateauDto);
				});

			}
		}, 5l, TimeUnit.MINUTES);
	}
}
