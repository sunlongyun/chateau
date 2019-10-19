package com.caichao.chateau.app.service.impl;

import com.caichao.chateau.app.controller.goods.request.PageQueryReq;
import com.caichao.chateau.app.entity.Goods;
import com.caichao.chateau.app.dto.GoodsDto;
import com.caichao.chateau.app.mapper.GoodsMapper;
import com.caichao.chateau.app.service.GoodsService;

import com.github.pagehelper.PageHelper;
import com.lianshang.generator.commons.PageInfo;
import com.lianshang.generator.commons.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 酒水 服务实现类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-10-19
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper,Goods, GoodsDto> implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public PageInfo<GoodsDto> getGoodsInfo(PageQueryReq pageQueryReq) {
        PageHelper.startPage(pageQueryReq.getPageNo(), pageQueryReq.getPageSize());
        List<Goods> goodsDtoList = goodsMapper.getGoodsList(pageQueryReq);
        return PageInfo.getPageInfo(copyList(goodsDtoList, GoodsDto.class));
    }
}