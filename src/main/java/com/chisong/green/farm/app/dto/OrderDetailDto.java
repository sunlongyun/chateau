package com.chisong.green.farm.app.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* 订单详情
* </p>
* @author 孙龙云
* @date 2019-12-21
*/
@Data
public class OrderDetailDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
    * 商品规格id，非统一规格商品，必须存在商品规格id
     */
    private Long specsId;

    /**
    * 商品规格名称
     */
    private String specsName;
    /**
     * 供应商id
     */
    private Integer supplierId;
    /**
     * 供应商地址
     */
    private String supplierAddress;
    /**
     * 购物车项
     */
    private Integer cartItemId;
    /**
    * 酒水id
     */
    private Long goodsId;

    /**
    * 标题
     */
    private String title;

    /**
    * 数量
     */
    private Integer num;

    /**
    * 缩略图
     */
    private String minPicUrl;

    /**
    * 单价  单位:分
     */
    private Long price;
    /**
     * 省份
     */
    private String province;

    /**
    * 总价格  单位:分
     */
    private Long totalPrice;

    /**
    * 所属订单号
     */
    private String orderNo;

    /**
    * 订单id
     */
    private Long orderId;

    /**
    * 创建时间
     */
    private Date createTime;

    /**
    * 更新时间
     */
    private Date updateTime;

    /**
    * 是否有效 1-有效；0-无效
     */
    private Integer validity;

    /**
     * 所属app
     */
    private Long appInfoId;
}