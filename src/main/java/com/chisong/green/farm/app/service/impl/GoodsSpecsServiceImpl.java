package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.entity.GoodsSpecs;
import com.chisong.green.farm.app.dto.GoodsSpecsDto;
import com.chisong.green.farm.app.mapper.GoodsSpecsMapper;
import com.chisong.green.farm.app.service.GoodsSpecsService;

import com.lianshang.generator.commons.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品规格明细 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-11-18
 */
@Service
public class GoodsSpecsServiceImpl extends ServiceImpl<GoodsSpecsMapper,GoodsSpecs, GoodsSpecsDto> implements GoodsSpecsService {

}