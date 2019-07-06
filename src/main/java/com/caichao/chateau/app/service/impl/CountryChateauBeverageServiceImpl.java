package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.constants.enums.Validity;
import com.caichao.chateau.app.controller.beverage.request.PageQueryReq;
import com.caichao.chateau.app.dto.BeverageTailImagesDto;
import com.caichao.chateau.app.dto.BeverageTopImagesDto;
import com.caichao.chateau.app.dto.CountryChateauBeverageDto;
import com.caichao.chateau.app.entity.CountryChateauBeverage;
import com.caichao.chateau.app.example.BeverageTailImagesExample;
import com.caichao.chateau.app.example.BeverageTopImagesExample;
import com.caichao.chateau.app.mapper.CountryChateauBeverageMapper;
import com.caichao.chateau.app.service.BeverageTailImagesService;
import com.caichao.chateau.app.service.BeverageTopImagesService;
import com.caichao.chateau.app.service.CountryChateauBeverageService;
import com.github.pagehelper.PageHelper;
import com.lianshang.generator.commons.PageInfo;
import com.lianshang.generator.commons.ServiceImpl;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private BeverageTopImagesService beverageTopImagesService;

	@Autowired
	private BeverageTailImagesService beverageTailImagesService;

	@Override
	public PageInfo<CountryChateauBeverageDto> getCountryChateauBeverageDtoPageInfo(PageQueryReq pageQueryReq) {
		PageHelper.startPage(pageQueryReq.getPageNo(), pageQueryReq.getPageSize());
		List<CountryChateauBeverage> list = this.baseMapper.getCountryChateauBeverageList(pageQueryReq);
		PageInfo pageInfo = PageInfo.getPageInfo(list);
		pageInfo.setDataList(copyList(list,CountryChateauBeverageDto.class));
		;
		return pageInfo;
	}

	@Override
	public CountryChateauBeverageDto getDetail(Long id) {
		CountryChateauBeverageDto countryChateauBeverageDto = getById(id);
		if(null == countryChateauBeverageDto){
			throw new RuntimeException("商品不存在");
		}

		BeverageTopImagesExample beverageTopImagesExample = new BeverageTopImagesExample();
		beverageTopImagesExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
			.andBeverageIdEqualTo(id);

		BeverageTailImagesExample beverageTailImagesExample = new BeverageTailImagesExample();
		beverageTailImagesExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
			.andBeverageIdEqualTo(id);

		countryChateauBeverageDto.setTopImages(beverageTopImagesService.getList(beverageTopImagesExample).stream()
			.map(BeverageTopImagesDto::getImageUrl).collect(Collectors.toList()));

		countryChateauBeverageDto.setTailImages(beverageTailImagesService.getList(beverageTailImagesExample).stream()
			.map(BeverageTailImagesDto::getImageUrl).collect(Collectors.toList()));


		return countryChateauBeverageDto;
	}
}