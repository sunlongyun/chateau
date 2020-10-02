package com.chisong.green.farm.app.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
/**
* <p>
* 商品规格明细
* </p>
* @author 孙龙云
* @date 2020-05-23
*/
@Data
public class GoodsSpecs implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private  Long id;

    /**
    * 商品id
     */
    private  Long goodsId;

    /**
    * 规格名称
     */
    private  String name;

    /**
    * 售价
     */
    private  Integer price;

    /**
    * 进货价格是否包含邮费
     */
    private  Integer postage;

    /**
    * 进价
     */
    private  Integer originPrice;

    /**
    * 规格图片
     */
    private  String imgUrl;

    /**
    * 促销价
     */
    private  Integer promotionPrice;

    /**
    * 是否参加促销活动 1-参加;0-不参加
     */
    private  Integer promote;

    /**
    * 销售数量
     */
    private  Integer salesNum;

    /**
    * 库存
     */
    private  Integer stock;
    /**
     * 是否作为展示商品
     */
    private Integer isShow;

    /**
    * 创建时间
     */
    private  Date createTime;

    /**
    * 修改时间
     */
    private  Date updateTime;

    /**
    * 是否有效 1-有效;0-无效
     */
    private  Integer validity;

    /**
     * 所属app
     */
    private Long appInfoId;
}
