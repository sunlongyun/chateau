package com.chisong.green.farm.app.mapper;

import com.chisong.green.farm.app.entity.GoodsSpecs;
import com.lianshang.generator.commons.LsBaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品规格明细 Mapper 接口
 * </p>
 *
 * @author 孙龙云
 * @since 2019-11-18
 */
public interface GoodsSpecsMapper extends LsBaseMapper<GoodsSpecs> {

	/**
	 * 减少规格库存
	 * @param stock
	 * @param id
	 * @return
	 */
	public int decreaseStock(@Param("stock") Integer stock, @Param("id") Long id);
}
