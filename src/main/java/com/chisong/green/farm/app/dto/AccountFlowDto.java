package com.chisong.green.farm.app.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* 账户流水
* </p>
* @author 孙龙云
* @date 2020-10-07
*/
@Data
public class AccountFlowDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
    * 账户id
     */
    private Integer accountId;

    /**
    * 0-流出，1-流入
     */
    private Integer type;

    /**
    * 状态 0-进行中；1-已完成；2-失败
     */
    private Integer status;

    /**
    * 收入来源  0-分享赚钱，1-余额提现
     */
    private Integer source;

    /**
    * 流水金额 单位:分
     */
    private Integer amount;

    /**
    * 操作说明
     */
    private String operateName;

    /**
    * 是否有效 1-有效；0-无效
     */
    private Integer validity;

    /**
    * 创建时间
     */
    private Date createTime;

    /**
    * 所属app
     */
    private Long appInfoId;

    /**
    * 支付流水号
     */
    private String payNo;

    /**
    * 待入账时间；到时间后，待入账流水自动入账
     */
    private Date inAccountTime;

}