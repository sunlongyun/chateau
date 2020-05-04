package com.chisong.green.farm.app.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* 退款流水
* </p>
* @author 孙龙云
* @date 2020-05-04
*/
@Data
public class RefundPaymentDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键
     */
    private Long id;

    /**
    * 申请退款金额
     */
    private Long applyAmount;

    /**
    * 订单申请id
     */
    private Long applyId;

    /**
    * 退款申请号
     */
    private String refundNo;

    /**
    * 微信退款单号
     */
    private String outRefundNo;

    /**
    * 退款失败原因
     */
    private String failedRemark;

    /**
    * 退款状态 0-申请，1-退款成功；2-退款失败
     */
    private Integer status;

    /**
    * 支付流水号
     */
    private String paymentNo;

    /**
    * 退款申请单号
     */
    private String refundOrderNo;

    /**
    * 退款成功时间
     */
    private String refundSuccessTime;

    /**
    * 创建时间
     */
    private Date createTime;

    /**
    * 更新时间
     */
    private Date updateTime;

}