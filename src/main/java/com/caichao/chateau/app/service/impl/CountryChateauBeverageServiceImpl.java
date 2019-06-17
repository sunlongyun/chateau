package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.controller.beverage.request.PageQueryReq;
import com.caichao.chateau.app.dto.CountryChateauBeverageDto;
import com.caichao.chateau.app.entity.CountryChateauBeverage;
import com.caichao.chateau.app.mapper.CountryChateauBeverageMapper;
import com.caichao.chateau.app.service.CountryChateauBeverageService;
import com.github.pagehelper.PageHelper;
import com.lianshang.generator.commons.PageInfo;
import com.lianshang.generator.commons.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 酒水 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-06-15
 */
@Service
public class CountryChateauBeverageServiceImpl extends
	ServiceImpl<CountryChateauBeverageMapper, CountryChateauBeverage, CountryChateauBeverageDto> implements
	CountryChateauBeverageService {

	@Override
	public PageInfo<CountryChateauBeverageDto> getCountryChateauBeverageDtoPageInfo(PageQueryReq pageQueryReq) {
		PageHelper.startPage(pageQueryReq.getPageNo(), pageQueryReq.getPageSize());
		List<CountryChateauBeverage> list = this.baseMapper.getCountryChateauBeverageList(pageQueryReq);
		PageInfo pageInfo = PageInfo.getPageInfo(list);
		pageInfo.setDataList(copyList(list,CountryChateauBeverageDto.class));
		;
		return pageInfo;
	}
}