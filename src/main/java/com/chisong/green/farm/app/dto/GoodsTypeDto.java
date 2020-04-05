package com.chisong.green.farm.app.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* 商品分类，暂时到二级分类
* </p>
* @author 孙龙云
* @date 2020-03-29
*/
@Data
public class GoodsTypeDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
    * 类目名称
     */
    private String name;

    /**
    * 父类
     */
    private Integer parentId;

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

    /**
    * 权重
     */
    private Integer weight;

}