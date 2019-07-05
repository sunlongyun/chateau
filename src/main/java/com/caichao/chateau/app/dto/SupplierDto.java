package com.caichao.chateau.app.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* 供应商
* </p>
* @author 孙龙云
* @date 2019-07-05
*/
@Data
public class SupplierDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
    * 公司名称
     */
    private String companyName;

    /**
    * 供应商logo
     */
    private String logo;

    /**
    * 地址
     */
    private String address;

    /**
    * 电话
     */
    private String telephone;

    private String linker;

    /**
    * 联系人手机号
     */
    private String mobile;

    /**
    * 公司大图
     */
    private String pic;

    /**
    * 是否有效 1-有效；0-无效
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