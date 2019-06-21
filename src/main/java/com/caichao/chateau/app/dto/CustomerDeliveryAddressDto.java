package com.caichao.chateau.app.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* 
* </p>
* @author 孙龙云
* @date 2019-06-15
*/
@Data
public class CustomerDeliveryAddressDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键
     */
    private Integer id;

    /**
    * 客户
     */
    private Integer customerId;

    /**
    * 联系人
     */
    private String contact;

    /**
    * 座机
     */
    private String telephone;

    /**
    * 联系人手机号
     */
    private String mobile;

    /**
    * 国家
     */
    private String country;

    /**
    * 省份
     */
    private String province;

    /**
    * 城市
     */
    private String city;

    /**
    * 区/县
     */
    private String area;

    /**
    * 街道地址
     */
    private String address;

    /**
    * 默认地址
     */
    private Boolean tacitly;
    /**
     * 地址明细
     */
    private String detaiAddress;

    /**
    * 创建时间
     */
    private Date createTime;

    /**
    * 更新时间
     */
    private Date updateTime;

    /**
    * 是否有效 1-有效；0-无效
     */
    private Integer validity;

}