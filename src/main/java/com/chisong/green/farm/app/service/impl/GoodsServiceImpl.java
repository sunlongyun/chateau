package com.chisong.green.farm.app.service.impl;

import com.chisong.green.farm.app.constants.enums.Validity;
import com.chisong.green.farm.app.controller.goods.request.PageQueryReq;
import com.chisong.green.farm.app.dto.GoodsDto;
import com.chisong.green.farm.app.dto.GoodsTailImagesDto;
import com.chisong.green.farm.app.dto.GoodsTopImagesDto;
import com.chisong.green.farm.app.entity.Goods;
import com.chisong.green.farm.app.entity.GoodsTopImages;
import com.chisong.green.farm.app.example.GoodsTailImagesExample;
import com.chisong.green.farm.app.example.GoodsTopImagesExample;
import com.chisong.green.farm.app.mapper.GoodsMapper;
import com.chisong.green.farm.app.service.GoodsService;
import com.chisong.green.farm.app.service.GoodsTailImagesService;
import com.chisong.green.farm.app.service.GoodsTopImagesService;
import com.github.pagehelper.PageHelper;
import com.lianshang.generator.commons.PageInfo;
import com.lianshang.generator.commons.ServiceImpl;
import java.util.ArrayList;
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

    @Override
    public void saveOrUpdateGoods(GoodsDto goodsDto) {
        if(null == goodsDto.getId()){
           save(goodsDto);
        }else{
            update(goodsDto);
        }
        handleGoodsTopImages(goodsDto);

        handleGoodsTailImages(goodsDto);
    }

    /**
     * 商品头部滚动图片
     * @param goodsDto
     */
    private void handleGoodsTopImages(GoodsDto goodsDto) {
        final List<String> goodsTops =  goodsDto.getTopImages();

        GoodsTopImagesExample goodsTopImagesExample = new GoodsTopImagesExample();
        goodsTopImagesExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
            .andGoodsIdEqualTo(goodsDto.getId());
        List<GoodsTopImagesDto> goodsTopImagesDtos =  goodsTopImagesService.getList(goodsTopImagesExample);

        goodsTopImagesDtos.stream().filter(goodsTopImagesDto -> {
            return !goodsTops.contains(goodsTopImagesDto.getImageUrl());
        }).forEach(goodsTopImagesDto -> {
            goodsTopImagesService.deleteById(goodsTopImagesDto.getId());
        });

        //goodsTops 删除已经存在的
        goodsTopImagesDtos.stream().forEach(goodsTopImagesDto -> {
            if(goodsTops.contains(goodsTopImagesDto.getImageUrl())){
                goodsTops.remove(goodsTopImagesDto.getImageUrl());
            }
        });
        //goodsTops 剩余的是需要添加的
        goodsTops.stream().forEach(imageUrl->{
            GoodsTopImagesDto goodsTopImagesDto = new GoodsTopImagesDto();
            goodsTopImagesDto.setGoodsId(goodsDto.getId());
            goodsTopImagesDto.setImageUrl(imageUrl);
            goodsTopImagesService.save(goodsTopImagesDto);
        });
    }


    /**
     * 处理商品介绍图片
     * @param goodsDto
     */
    private void handleGoodsTailImages(GoodsDto goodsDto) {
        final List<String> goodsTails =  goodsDto.getTailImages();

        GoodsTailImagesExample goodsTailImagesExample = new GoodsTailImagesExample();
        goodsTailImagesExample.createCriteria().andValidityEqualTo(Validity.AVAIL.code())
            .andGoodsIdEqualTo(goodsDto.getId());
        List<GoodsTailImagesDto> goodsTailImagesDtos =  goodsTailImagesService.getList(goodsTailImagesExample);

        goodsTailImagesDtos.stream().filter(goodsTailImagesDto -> {
            return !goodsTails.contains(goodsTailImagesDto.getImageUrl());
        }).forEach(goodsTailImagesDto -> {
            goodsTailImagesService.deleteById(goodsTailImagesDto.getId());
        });

        //goodsTails 删除已经存在的
        goodsTailImagesDtos.stream().forEach(goodsTailImagesDto -> {
            if(goodsTails.contains(goodsTailImagesDto.getImageUrl())){
                goodsTails.remove(goodsTailImagesDto.getImageUrl());
            }
        });
        //goodsTails 剩余的是需要添加的
        goodsTails.stream().forEach(imageUrl->{
            GoodsTailImagesDto goodsTailImagesDto = new GoodsTailImagesDto();
            goodsTailImagesDto.setGoodsId(goodsDto.getId());
            goodsTailImagesDto.setImageUrl(imageUrl);
            goodsTailImagesService.save(goodsTailImagesDto);
        });
    }

}