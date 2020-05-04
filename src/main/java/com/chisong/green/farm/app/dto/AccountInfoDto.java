package com.chisong.green.farm.app.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* 供应商账户信息
* </p>
* @author 孙龙云
* @date 2020-05-04
*/
@Data
public class AccountInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
    * 个人openId
     */
    private String openId;

    private Integer cusotmerId;

    /**
    * 真实姓名
     */
    private String realName;

    /**
    * 昵称
     */
    private String nickName;

    /**
    * 账户类型 1-个人账户；2-企业账户
     */
    private Integer type;

    /**
    * 商户id
     */
    private String merchantId;

    /**
    * 冻结金额
     */
    private Integer forezenAmount;

    /**
    * 可用余额
     */
    private Integer availableAmount;

    /**
    * 总余额
     */
    private Integer totalAmount;

    /**
    * 商户appId
     */
    private String appId;

    /**
    * 是否有效 0-无效；1-有效
     */
    private Integer validity;

    /**
    * 创建时间
     */
    private Date createTime;

    /**
    * 更新时间
     */
    private Date updateTime;

}