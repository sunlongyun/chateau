package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.entity.CountryChateauBeverage;
import com.caichao.chateau.app.dto.CountryChateauBeverageDto;
import com.caichao.chateau.app.mapper.CountryChateauBeverageMapper;
import com.caichao.chateau.app.service.CountryChateauBeverageService;

import com.lianshang.generator.commons.ServiceImpl;
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
public class CountryChateauBeverageServiceImpl extends ServiceImpl<CountryChateauBeverageMapper,CountryChateauBeverage, CountryChateauBeverageDto> implements CountryChateauBeverageService {

}