package com.caichao.chateau.app.dto;

import com.caichao.chateau.app.annotation.ServiceTypeAnnotation;
import com.caichao.chateau.app.annotation.ServiceTypeAnnotation.Type;
import com.caichao.chateau.app.constants.enums.UserStatusEnum;
import com.caichao.chateau.app.constants.enums.UserTypeEnum;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* 顾客信息表
* </p>
* @author 孙龙云
* @date 2019-07-15
*/
@Data
public class CustomerInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
    * 昵称
     */
    private String nickName;

    private String password;

    private String userName;

    /**
    * 国家
     */
    private String country;
    /**
     * 推荐人id
     */
    private Long recommendId;
    /**
    * 状态 1：正常，2：冻结，
     */
    @ServiceTypeAnnotation(value = UserStatusEnum.class)
    private Integer status;

    /**
    * 用户类型 1-顾客；2-供应商；3-后台管理员
     */
    @ServiceTypeAnnotation(value = UserTypeEnum.class,type = Type.VALUE)
    private Integer type;

    /**
    * 省份
     */
    private String province;

    /**
    * 城市
     */
    private String city;

    /**
    * 头像
     */
    private String avatarUrl;

    /**
    * 手机号
     */
    private String mobile;

    /**
    * union_id
     */
    private String unionId;

    /**
    * openId
     */
    private String openId;

    /**
    * 创建时间
     */
    private Date createTime;

    /**
    * 修改时间
     */
    private Date updateTime;

    /**
    * 是否有效
     */
    private Integer validity;

}