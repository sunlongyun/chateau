package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.dto.GoodsTopImagesDto;
import com.caichao.chateau.app.entity.GoodsTopImages;
import com.caichao.chateau.app.mapper.GoodsTopImagesMapper;
import com.caichao.chateau.app.service.GoodsTopImagesService;

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
public class GoodsTopImagesServiceImpl extends ServiceImpl<GoodsTopImagesMapper,GoodsTopImages, GoodsTopImagesDto> implements GoodsTopImagesService {

}