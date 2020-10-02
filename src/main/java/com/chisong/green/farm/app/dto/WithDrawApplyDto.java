package com.chisong.green.farm.app.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* 顾客提现申请
* </p>
* @author 孙龙云
* @date 2020-07-01
*/
@Data
public class WithDrawApplyDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
    * 账户id
     */
    private Integer accountId;

    /**
    * 申请人真实姓名
     */
    private String realName;

    /**
    * 昵称
     */
    private String nickName;

    /**
    * 手机号
     */
    private String mobile;

    /**
    * 提现金额 单位:分
     */
    private Integer amount;

    /**
    * 提现申请状态  0-申请中  1-已批准;2-已提现成功;3-申请拒绝
     */
    private Integer status;

    /**
    * 申请拒绝原因
     */
    private String rejectReson;

    /**
    * 批复人id
     */
    private Integer approveCustomerId;

    /**
    * 批复人m名称
     */
    private String approveCustomerName;

    /**
    * 批复时间
     */
    private Date approveTime;

    /**
    * 创建时间
     */
    private Date createTime;

    /**
    * 更新时间
     */
    private Date updateTime;

    /**
    * 是否有效 1-有效;0-无效
     */
    private Integer validity;

    /**
     * 所属app
     */
    private Long appInfoId;
}