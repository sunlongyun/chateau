package com.chisong.green.farm.app.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
/**
* <p>
* 购物车-酒水关联表
* </p>
* @author 孙龙云
* @date 2019-12-22
*/
@Data
public class CartItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private  Integer id;

    /**
    * 购物车id
     */
    private  Integer cartId;

    /**
    * 酒水名称
     */
    private  String title;

    /**
    * 商品id
     */
    private  Long goodsId;

    /**
    * 规格id
     */
    private  Long specsId;

    /**
    * 规格名称
     */
    private  String specsName;

    /**
    * 数量
     */
    private  Integer num;

    /**
    * 单价
     */
    private  Long price;

    /**
    * 总价
     */
    private  Long totalPrice;

    /**
    * 缩略图
     */
    private  String minPicUrl;

    /**
    * 创建时间
     */
    private  Date createTime;

    /**
    * 更新时间
     */
    private  Date updateTime;

    /**
    * 是否有效
     */
    private  Integer validity;

    /**
     * 所属app
     */
    private Long appInfoId;
}
