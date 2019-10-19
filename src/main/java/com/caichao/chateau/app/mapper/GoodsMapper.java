package com.caichao.chateau.app.mapper;

import com.caichao.chateau.app.controller.goods.request.PageQueryReq;
import com.caichao.chateau.app.entity.Goods;
import com.lianshang.generator.commons.LsBaseMapper;

import java.util.List;

/**
 * <p>
 * 酒水 Mapper 接口
 * </p>
 *
 * @author 孙龙云
 * @since 2019-10-19
 */
public interface GoodsMapper extends LsBaseMapper<Goods> {
    /**
     * 查询商品列表
     * @param pageQueryReq
     * @return
     */
    public List<Goods> getGoodsList(PageQueryReq pageQueryReq);
}
