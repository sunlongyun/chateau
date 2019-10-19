package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.constants.enums.Validity;
import com.caichao.chateau.app.controller.goods.request.PageQueryReq;
import com.caichao.chateau.app.dto.GoodsDto;
import com.caichao.chateau.app.dto.GoodsTailImagesDto;
import com.caichao.chateau.app.dto.GoodsTopImagesDto;
import com.caichao.chateau.app.entity.Goods;
import com.caichao.chateau.app.example.GoodsTailImagesExample;
import com.caichao.chateau.app.example.GoodsTopImagesExample;
import com.caichao.chateau.app.mapper.GoodsMapper;
import com.caichao.chateau.app.service.GoodsService;
import com.caichao.chateau.app.service.GoodsTailImagesService;
import com.caichao.chateau.app.service.GoodsTopImagesService;
import com.github.pagehelper.PageHelper;
import com.lianshang.generator.commons.PageInfo;
import com.lianshang.generator.commons.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 酒水 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-10-19
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods, GoodsDto> implements GoodsService {
    @Autowired
    private GoodsTopImagesService goodsTopImagesService;

    @Autowired
    private GoodsTailImagesService goodsTailImagesService;

    @Override
    public PageInfo<GoodsDto> getGoodsInfo(PageQueryReq pageQueryReq) {
        PageHelper.startPage(pageQueryReq.getPageNo(), pageQueryReq.getPageSize());
        List<Goods> goodsDtoList = this.baseMapper.getGoodsList(pageQueryReq);
        return PageInfo.getPageInfo(copyList(goodsDtoList, GoodsDto.class));
    }

    @Override
    public GoodsDto getDetailById(Long id) {
        GoodsDto goodsDto = getById(id);

        GoodsTopImagesExample goodsTopImagesExample = new GoodsTopImagesExample();
        goodsTopImagesExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code()).andGoodsIdEqualTo(id);
        List<String> topImgList = goodsTopImagesService.getList(goodsTopImagesExample).stream().map(GoodsTopImagesDto::getImageUrl).collect(Collectors.toList());
        goodsDto.setTopImages(topImgList);


        GoodsTailImagesExample goodsTailImagesExample = new GoodsTailImagesExample();
        goodsTailImagesExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code()).andGoodsIdEqualTo(id);
        List<String> tailImgList = goodsTailImagesService.getList(goodsTopImagesExample).stream().map(GoodsTailImagesDto::getImageUrl).collect(Collectors.toList());
        goodsDto.setTailImages(tailImgList);
        return goodsDto;
    }
}