package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.entity.Pictures;
import com.caichao.chateau.app.dto.PicturesDto;
import com.caichao.chateau.app.mapper.PicturesMapper;
import com.caichao.chateau.app.service.PicturesService;

import com.lianshang.generator.commons.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 首页轮播图片 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-06-15
 */
@Service
public class PicturesServiceImpl extends ServiceImpl<PicturesMapper,Pictures, PicturesDto> implements PicturesService {

}