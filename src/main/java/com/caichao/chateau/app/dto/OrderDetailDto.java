package com.caichao.chateau.app.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* 订单详情
* </p>
* @author 孙龙云
* @date 2019-06-16
*/
@Data
public class OrderDetailDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
    * 酒水id
     */
    private Long beverageId;

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

}