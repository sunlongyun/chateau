package com.chisong.green.farm.app.service;

import com.chisong.green.farm.app.controller.goods.request.PageQueryReq;
import com.lianshang.generator.commons.IService;
import com.chisong.green.farm.app.dto.GoodsDto;
import com.lianshang.generator.commons.PageInfo;

/**
 * <p>
 * 酒水 服务类
 * </p>
 *
 * @author 孙龙云
 * @since 2019-10-19
 */
public interface GoodsService extends IService<GoodsDto> {
    /**
     * 获取商品列表分页
     * @param pageQueryReq
     * @return
     */
    public PageInfo<GoodsDto> getGoodsInfo(PageQueryReq pageQueryReq);

    /**
     * 根据id查询商品详情
     * @param id
     * @return
     */
    public GoodsDto getDetailById(Long id);

    /**
     * 添加或者更新商品信息
     * @param goodsDto
     */
    public void saveOrUpdateGoods(GoodsDto goodsDto);
}
