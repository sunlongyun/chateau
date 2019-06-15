package com.caichao.chateau.app.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* 国家
* </p>
* @author 孙龙云
* @date 2019-06-15
*/
@Data
public class CountryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键
     */
    private Integer id;

    /**
    * 编码
     */
    private String code;

    /**
    * 国家名称
     */
    private String name;

    /**
    * 图片地址
     */
    private String picUrl;

    /**
    * 缩略图地址
     */
    private String minPicUrl;

    /**
    * 标题
     */
    private String title;

    /**
    * 介绍
     */
    private String description;

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