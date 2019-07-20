package com.caichao.chateau.app.service;

import com.caichao.chateau.app.dto.CountryChateauDto;
import com.lianshang.generator.commons.IService;

/**
 * <p>
 * 国家--酒庄 服务类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-06-15
 */
public interface CountryChateauService extends IService<CountryChateauDto> {

	/**
	 * 获取日常直播地址
	 * @param id
	 * @return
	 */
	public String getDailyPusherUlr(Integer id);

	/**
	 * 获取庄主直播地址
	 * @param id
	 * @return
	 */
	public String getMasterPusherUlr(Integer id);

	/**
	 * 根据code查询庄园信息
	 * @param code
	 * @return
	 */
	public CountryChateauDto getByCode(String code);
}
