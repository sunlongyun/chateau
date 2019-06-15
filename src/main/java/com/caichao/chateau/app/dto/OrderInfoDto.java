package com.caichao.chateau.app.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* 
* </p>
* @author 孙龙云
* @date 2019-06-15
*/
@Data
public class OrderInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 订单
     */
    private Long id;

    /**
    * 订单号
     */
    private String orderNo;

    /**
    * 客户姓名
     */
    private String customerName;

    /**
    * 客户手机号
     */
    private String mobile;

    private String customerId;

    /**
    * 订单状态 0-下单待支付；1-下单已支付；2-卖家已发货;3-已收货;4-订单已取消;
     */
    private Integer status;

    /**
    * 物流单号
     */
    private String logisticsNumber;

    /**
    * 支付流水号，支付成功的支付流水号
     */
    private String payNo;

    /**
    * 订单总金额 单位：分
     */
    private Long totalAmount;

    /**
    * 创建时间
     */
    private Date createTime;

    /**
    * 修改时间
     */
    private Date updateTime;

    /**
    * 是否有效 1-有效；0-无效
     */
    private Integer validity;

}