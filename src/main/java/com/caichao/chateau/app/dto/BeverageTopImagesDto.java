package com.caichao.chateau.app.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* 酒水头部图片，用于商品详情展示
* </p>
* @author 孙龙云
* @date 2019-07-06
*/
@Data
public class BeverageTopImagesDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
    * 酒水id
     */
    private Long beverageId;

    /**
    * 图片地址
     */
    private String imageUrl;

    /**
    * 创建时间
     */
    private Date createTime;

    /**
    * 修改时间
     */
    private Date updateTime;

    /**
    * 是否有效  1-有效；0-无效
     */
    private Integer validity;

}