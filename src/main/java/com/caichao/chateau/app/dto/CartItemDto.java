package com.caichao.chateau.app.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* 购物车-酒水关联表
* </p>
* @author 孙龙云
* @date 2019-06-18
*/
@Data
public class CartItemDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键
     */
    private Integer id;

    /**
    * 购物车id
     */
    private Integer cartId;

    /**
    * 酒水名称
     */
    private String title;

    /**
    * 酒水id
     */
    private Long beverageId;

    /**
    * 数量
     */
    private Integer num;

    /**
    * 单价
     */
    private Long price;

    /**
    * 总价
     */
    private Long totalPrice;

    /**
    * 缩略图
     */
    private String minPicUrl;

    /**
    * 创建时间
     */
    private Date createTime;

    /**
    * 更新时间
     */
    private Date updateTime;

    /**
    * 是否有效
     */
    private Integer validity;

}