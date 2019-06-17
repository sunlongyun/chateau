package com.caichao.chateau.app.mapper;

import com.caichao.chateau.app.controller.beverage.request.PageQueryReq;
import com.caichao.chateau.app.entity.CountryChateauBeverage;
import com.lianshang.generator.commons.LsBaseMapper;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 酒水 Mapper 接口
 * </p>
 *
 * @author 孙龙云
 * @since 2019-06-15
 */
public interface CountryChateauBeverageMapper extends LsBaseMapper<CountryChateauBeverage> {

	/**
	 * 查询列表
	 * @param pageQueryReq
	 * @return
	 */
	public List<CountryChateauBeverage> getCountryChateauBeverageList(PageQueryReq pageQueryReq);
}
