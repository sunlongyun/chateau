package com.chisong.green.farm.app.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
/**
* <p>
* 顾客信息表
* </p>
* @author 孙龙云
* @date 2019-11-10
*/
@Data
public class CustomerInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private  Long id;

    /**
    * 昵称
     */
    private  String nickName;

    private  String password;

    private  String userName;

    /**
    * 国家
     */
    private  String country;

    /**
    * 推荐人id
     */
    private  Long recommendId;

    /**
    * 状态 1：正常，2：冻结，
     */
    private  Integer status;

    /**
    * 供应商公司
     */
    private  String companyName;

    /**
    * 供应商id，如果顾客是供应商，请填写供应商id
     */
    private  Integer supplierId;

    /**
    * 用户类型 1-顾客；2-供应商；3-后台管理员
     */
    private  Integer type;

    /**
    * 省份
     */
    private  String province;

    /**
    * 城市
     */
    private  String city;

    /**
    * 头像
     */
    private  String avatarUrl;

    /**
    * 手机号
     */
    private  String mobile;

    /**
    * union_id
     */
    private  String unionId;

    /**
    * openId
     */
    private  String openId;

    /**
    * 创建时间
     */
    private  Date createTime;

    /**
    * 修改时间
     */
    private  Date updateTime;

    /**
    * 是否有效
     */
    private  Integer validity;

}
