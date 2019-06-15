package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.entity.Country;
import com.caichao.chateau.app.dto.CountryDto;
import com.caichao.chateau.app.mapper.CountryMapper;
import com.caichao.chateau.app.service.CountryService;

import com.lianshang.generator.commons.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 国家 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-06-15
 */
@Service
public class CountryServiceImpl extends ServiceImpl<CountryMapper,Country, CountryDto> implements CountryService {

}