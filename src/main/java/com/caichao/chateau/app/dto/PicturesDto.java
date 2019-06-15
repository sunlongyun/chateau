package com.caichao.chateau.app.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* 首页轮播图片
* </p>
* @author 孙龙云
* @date 2019-06-15
*/
@Data
public class PicturesDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键
     */
    private Integer id;

    /**
    * 图片地址
     */
    private String picUrl;

    /**
    * 连接跳转地址
     */
    private String href;

    /**
    * 图片类型 1-首页轮播图，2-国家葡萄酒介绍引导图片，3-国家葡萄酒介绍详细图片，由2引导出来
     */
    private Integer type;

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