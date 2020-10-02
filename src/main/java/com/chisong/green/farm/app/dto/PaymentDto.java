package com.chisong.green.farm.app.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* 
* </p>
* @author 孙龙云
* @date 2020-05-04
*/
@Data
public class PaymentDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 支付流水号
     */
    private Long id;

    /**
    * 支付订单号
     */
    private String payOrderNo;

    /**
    * 支付流水号
     */
    private String payNo;

    /**
    * 预支付流水
     */
    private String prePayId;

    /**
    * 成功支付金额
     */
    private Integer payedAmount;

    /**
    * 申请支付金额
     */
    private Integer amount;

    /**
    * 第三方支付流水号，微信/支付宝流水号
     */
    private String thirdPayNo;

    /**
    * 支付状态 0-提交；1-成功；2-失败；3-超时未支付，4-未支付关闭
     */
    private Integer status;

    /**
    * 支付时间
     */
    private Date payTime;

    /**
    * 支付成功时间
     */
    private Date paySuccessTime;

    /**
    * 拿到最终结果之前查询次数。超过一定的次数还不能查询到成功的结果，则认为是失败
     */
    private Integer times;

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