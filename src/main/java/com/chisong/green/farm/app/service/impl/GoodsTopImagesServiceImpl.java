package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.dto.GoodsTopImagesDto;
import com.chisong.green.farm.app.entity.GoodsTopImages;
import com.chisong.green.farm.app.mapper.GoodsTopImagesMapper;
import com.chisong.green.farm.app.service.GoodsTopImagesService;

import com.lianshang.generator.commons.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 酒水头部图片，用于商品详情展示 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-07-06
 */
@Service
public class GoodsTopImagesServiceImpl extends ServiceImpl<GoodsTopImagesMapper, GoodsTopImages, GoodsTopImagesDto> implements
	GoodsTopImagesService {

}