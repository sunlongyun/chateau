package com.chisong.green.farm.app.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
/**
* <p>
* 供应商账户信息
* </p>
* @author 孙龙云
* @date 2020-07-01
*/
@Data
public class AccountInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private  Integer id;

    /**
    * 个人openId
     */
    private  String openId;

    /**
    * 手机号
     */
    private  String mobile;

    private  Integer cusotmerId;

    /**
    * 真实姓名
     */
    private  String realName;

    /**
    * 昵称
     */
    private  String nickName;

    /**
    * 账户类型 1-个人账户；2-企业账户
     */
    private  Integer type;

    /**
    * 商户id
     */
    private  String merchantId;

    /**
    * 冻结金额
     */
    private  Integer forezenAmount;

    /**
    * 可用余额
     */
    private  Integer availableAmount;

    /**
    * 粉丝数量
     */
    private  Integer friendsNum;

    /**
    * 分享累计收益
     */
    private  Integer shareAmount;

    /**
    * 待入账
     */
    private  Integer recordedAmount;

    /**
    * 总余额
     */
    private  Integer totalAmount;

    /**
    * 商户appId
     */
    private  String appId;

    /**
    * 是否有效 0-无效；1-有效
     */
    private  Integer validity;

    /**
    * 创建时间
     */
    private  Date createTime;

    /**
    * 更新时间
     */
    private  Date updateTime;

    /**
     * 所属app
     */
    private Long appInfoId;
}
