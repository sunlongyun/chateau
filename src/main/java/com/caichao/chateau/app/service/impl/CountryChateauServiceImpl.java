package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.entity.CountryChateau;
import com.caichao.chateau.app.dto.CountryChateauDto;
import com.caichao.chateau.app.mapper.CountryChateauMapper;
import com.caichao.chateau.app.service.CountryChateauService;

import com.lianshang.generator.commons.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 国家--酒庄 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-06-15
 */
@Service
public class CountryChateauServiceImpl extends ServiceImpl<CountryChateauMapper,CountryChateau, CountryChateauDto> implements CountryChateauService {

}