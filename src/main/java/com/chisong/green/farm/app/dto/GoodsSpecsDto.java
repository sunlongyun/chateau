package com.chisong.green.farm.app.dto;

import java.io.Serializable;
import java.util.Date;

import java.util.Objects;
import lombok.Data;
/**
* <p>
* 商品规格明细
* </p>
* @author 孙龙云
* @date 2019-11-18
*/
@Data
public class GoodsSpecsDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
    * 商品id
     */
    private Long goodsId;

    /**
    * 规格名称
     */
    private String name;

    /**
    * 进价
     */
    private Integer price;

    /**
    * 进价
     */
    private Integer originPrice;

    /**
    * 库存
     */
    private Integer stock;

    /**
    * 创建时间
     */
    private Date createTime;

    /**
    * 修改时间
     */
    private Date updateTime;

    /**
    * 是否有效 1-有效;0-无效
     */
    private Integer validity;
    /**
     * 选中
     */
    private Integer selected=0;

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        GoodsSpecsDto that = (GoodsSpecsDto) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}