package com.chisong.green.farm.app.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
/**
* <p>
* 酒水
* </p>
* @author 孙龙云
* @date 2020-03-28
*/
@Data
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private  Long id;

    /**
    * 标题
     */
    private  String title;

    /**
    * 商品编码
     */
    private  String skuCode;

    /**
    * 二级分类id
     */
    private  Integer typeId;

    /**
    * 分类名称
     */
    private  String type;

    /**
    * 供应商公司名称
     */
    private  String supplierCompanyName;

    /**
    * 产地
     */
    private  String produceArea;

    /**
    * 1-销售中；0-已下架
     */
    private  Integer status;

    /**
    * 供应商id
     */
    private  Integer supplierId;

    /**
    * 国家名称
     */
    private  String countryName;

    private  String picUrl;

    /**
    * 缩略图地址
     */
    private  String minPicUrl;

    /**
    * 详细介绍图片
     */
    private  String detailPicUrl;

    /**
    * 说明
     */
    private  String description;

    /**
    * 统一规格；1-是；0-否
     */
    private  Integer uniformSpecs;

    /**
    * 规格，多个规则用逗号隔开
     */
    private  String specs;

    /**
    * 可选单位名称，多个用逗号隔开。1-箱；2-袋；3-千克，4-斤，5-瓶，6-升；
     */
    private  String units;

    /**
    * 进价 单位：分
     */
    private  Long originPrice;

    /**
    * 销售价格，跨境完税价  单位：分
     */
    private  Long price;

    /**
    * 促销价（只有促销活动期间有效)
     */
    private  Long promotePrice;

    /**
    * 累计销售数量
     */
    private  Integer salesNum;

    /**
    * 促销开始时间
     */
    private  Date promoteStartTime;

    /**
    * 促销结束时间
     */
    private  Date promoteEndTime;

    /**
    * 库存
     */
    private  Integer stock;

    /**
    * 创建时间
     */
    private  Date createTime;

    /**
    * 修改时间
     */
    private  Date updateTime;

    /**
    * 是否有效
     */
    private  Integer validity;

}
