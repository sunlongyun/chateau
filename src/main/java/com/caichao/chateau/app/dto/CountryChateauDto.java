package com.caichao.chateau.app.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* 国家--酒庄
* </p>
* @author 孙龙云
* @date 2019-07-09
*/
@Data
public class CountryChateauDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键
     */
    private Integer id;

    /**
    * 酒庄名称
     */
    private String title;

    /**
    * 酒庄编码
     */
    private String code;

    /**
    * 图片地址
     */
    private String picUrl;

    /**
    * 缩略图地址
     */
    private String minPicUrl;

    /**
    * 酒庄视频地址
     */
    private String video;

    /**
    * 是否正在直播  0-否；1-是
     */
    private Integer dailyBroadcastIng;

    /**
    * 是否正在直播 1-是；0-否
     */
    private Integer masterBroadcastIng;

    /**
    * 酒庄日常直播地址
     */
    private String dailyBroadcast;

    /**
    * 庄主日常直播地址
     */
    private String masterBroadcast;

    /**
    * 国家id
     */
    private Integer countryId;

    /**
    * 邮费
     */
    private Integer postage;

    /**
    * 酒庄介绍
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