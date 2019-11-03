package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.constants.enums.Validity;
import com.chisong.green.farm.app.controller.goods.request.PageQueryReq;
import com.chisong.green.farm.app.dto.GoodsDto;
import com.chisong.green.farm.app.dto.GoodsTailImagesDto;
import com.chisong.green.farm.app.dto.GoodsTopImagesDto;
import com.chisong.green.farm.app.entity.Goods;
import com.chisong.green.farm.app.example.GoodsTailImagesExample;
import com.chisong.green.farm.app.example.GoodsTopImagesExample;
import com.chisong.green.farm.app.mapper.GoodsMapper;
import com.chisong.green.farm.app.service.GoodsService;
import com.chisong.green.farm.app.service.GoodsTailImagesService;
import com.chisong.green.farm.app.service.GoodsTopImagesService;
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
        if(pageQueryReq.getHot()==1){
            pageQueryReq.setPageSize(6);
        }
        PageHelper.startPage(pageQueryReq.getPageNo(), pageQueryReq.getPageSize());
        List<Goods> goodsDtoList = this.baseMapper.getGoodsList(pageQueryReq);
        PageInfo pageInfo = PageInfo.getPageInfo(goodsDtoList);

        pageInfo.setDataList(copyList(goodsDtoList,GoodsDto.class));
        return pageInfo;
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
        List<String> tailImgList = goodsTailImagesService.getList(goodsTopImagesExample).stream().map(
	        GoodsTailImagesDto::getImageUrl).collect(Collectors.toList());
        goodsDto.setTailImages(tailImgList);
        return goodsDto;
    }
}