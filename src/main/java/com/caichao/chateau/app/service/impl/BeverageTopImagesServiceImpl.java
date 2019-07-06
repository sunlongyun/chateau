package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.entity.BeverageTopImages;
import com.caichao.chateau.app.dto.BeverageTopImagesDto;
import com.caichao.chateau.app.mapper.BeverageTopImagesMapper;
import com.caichao.chateau.app.service.BeverageTopImagesService;

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
public class BeverageTopImagesServiceImpl extends ServiceImpl<BeverageTopImagesMapper,BeverageTopImages, BeverageTopImagesDto> implements BeverageTopImagesService {

}