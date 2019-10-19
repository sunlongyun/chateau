package com.caichao.chateau.app.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* 酒水
* </p>
* @author 孙龙云
* @date 2019-10-19
*/
@Data
public class GoodsDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
     */
    private Long id;

    /**
    * 标题
     */
    private String title;

    /**
    * 商品编码
     */
    private String skuCode;

    /**
    * 英文标题
     */
    private String enTitle;

    /**
    * 供应商公司名称
     */
    private String supplierCompanyName;

    /**
    * 供应商id
     */
    private Integer supplierId;

    /**
    * 国家名称
     */
    private String countryName;

    private String picUrl;

    /**
    * 缩略图地址
     */
    private String minPicUrl;

    /**
    * 详细介绍图片
     */
    private String detailPicUrl;

    /**
    * 说明
     */
    private String description;

    /**
    * 规格
     */
    private String specs;

    /**
    * 进价 单位：分
     */
    private Long originPrice;

    /**
    * 销售价格，跨境完税价  单位：分
     */
    private Long price;

    /**
    * 年份
     */
    private String year;

    /**
    * 库存
     */
    private Integer stock;

    /**
    * 酒庄id
     */
    private Integer chateauId;

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