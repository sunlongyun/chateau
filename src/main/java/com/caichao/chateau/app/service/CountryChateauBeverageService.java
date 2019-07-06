package com.caichao.chateau.app.service;

import com.caichao.chateau.app.controller.beverage.request.PageQueryReq;
import com.lianshang.generator.commons.IService;
import com.caichao.chateau.app.dto.CountryChateauBeverageDto;
import com.lianshang.generator.commons.PageInfo;

/**
 * <p>
 * 酒水 服务类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-06-15
 */
public interface CountryChateauBeverageService extends IService<CountryChateauBeverageDto> {

	/**
	 * 分页查询
	 * @param pageQueryReq
	 * @return
	 */
	public PageInfo<CountryChateauBeverageDto> getCountryChateauBeverageDtoPageInfo(PageQueryReq pageQueryReq);

	/**
	 * 获取商品详情
	 * @param id
	 * @return
	 */
	public CountryChateauBeverageDto getDetail(Long id);
}
