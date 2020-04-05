package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.entity.GoodsType;
import com.chisong.green.farm.app.dto.GoodsTypeDto;
import com.chisong.green.farm.app.mapper.GoodsTypeMapper;
import com.chisong.green.farm.app.service.GoodsTypeService;

import com.lianshang.generator.commons.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品分类，暂时到二级分类 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2020-03-28
 */
@Service
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeMapper,GoodsType, GoodsTypeDto> implements GoodsTypeService {

}