package com.caichao.chateau.app.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* 顾客信息表
* </p>
* @author 孙龙云
* @date 2019-06-07
*/
@Data
public class CustomerInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
    * 真实姓名
     */
    private String realName;

    /**
    * 账号
     */
    private String userName;

    /**
    * 密码
     */
    private String passWord;

    /**
    * 手机号
     */
    private String mobile;

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
    private Boolean validity;

}