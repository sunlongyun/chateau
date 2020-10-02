package com.chisong.green.farm.app.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* 退款申请单
* </p>
* @author 孙龙云
* @date 2020-05-04
*/
@Data
public class RefundOrderDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键
     */
    private Long id;

    /**
    * 订单号
     */
    private String orderNo;

    /**
    * 订单id
     */
    private Long orderId;

    /**
    * 申请人姓名
     */
    private String applyName;

    /**
    * 申请时间
     */
    private Date applyTime;

    /**
    * 批准时间
     */
    private Date approveTime;

    /**
    * 批复人id
     */
    private Integer approveId;

    /**
    * 批准人
     */
    private String approveName;

    /**
    * 支付流水号
     */
    private String paymentNo;

    /**
    * 申请备注
     */
    private String refundRemark;

    /**
    * 退款申请流水号
     */
    private String refundNo;

    /**
    * 申请金额
     */
    private Long amount;

    /**
    * 状态 0-申请待审批，1-已批准通过；2-驳回；3-退款成功
     */
    private Integer status;

    /**
    * 创建时间
     */
    private Date createTime;

    /**
    * 更新时间
     */
    private Date updateTime;

    /**
    * 是否有效 0-无效；1-有效
     */
    private Integer validity;

    /**
     * 所属app
     */
    private Long appInfoId;
}