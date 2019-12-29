package com.chisong.green.farm.app.dto;

import com.chisong.green.farm.app.annotation.AmountUnitChange;
import com.chisong.green.farm.app.annotation.ServiceTypeAnnotation;
import com.chisong.green.farm.app.annotation.ServiceTypeAnnotation.Type;
import com.chisong.green.farm.app.constants.enums.GoodsStatusEnum;
import com.chisong.green.farm.app.constants.enums.UniformSpecsEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
* <p>
* 酒水
* </p>
* @author 孙龙云
* @date 2019-12-22
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
    * 类别
     */
    private String type;

    /**
    * 供应商公司名称
     */
    private String supplierCompanyName;

    /**
    * 产地
     */
    private String produceArea;

    /**
    * 1-销售中；0-已下架
     */
    @ServiceTypeAnnotation(value = GoodsStatusEnum.class,type = Type.ALL)
    private Integer status;

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
    * 统一规格；1-是；0-否
     */
    @ServiceTypeAnnotation(value = UniformSpecsEnum.class,type = Type.ALL)
    private Integer uniformSpecs;

    /**
    * 规格，多个规则用逗号隔开
     */
    private String specs;

    /**
    * 可选单位名称，多个用逗号隔开。1-箱；2-袋；3-千克，4-斤，5-瓶，6-升；
     */
    private String units;

    /**
    * 进价 单位：分
     */
    private Long originPrice;

    /**
    * 销售价格，跨境完税价  单位：分
     */
    @AmountUnitChange(showUnit=true)
    private Long price;
    /**
     * 选中规格的价格
     */
    private Long specsPrice;
    /**
     * 选中的规格id
     */
    private Long specsId;
    /**
    * 促销价（只有促销活动期间有效)
     */
    @AmountUnitChange(showUnit=true)
    private Long promotePrice;

    /**
    * 累计销售数量
     */
    private Integer salesNum;

    /**
    * 促销开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date promoteStartTime;

    /**
    * 促销结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date promoteEndTime;

    /**
    * 库存
     */
    private Integer stock;

    /**
    * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
    * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    /**
    * 是否有效
     */
    private Integer validity;

    /**
     * 商品尾部图片
     */
    private List<String> tailImages = new ArrayList<>();
    /**
     * 商品头部图片
     */
    private List<String> topImages = new ArrayList<>();
    /**
     * 商品规格列表，非统一规格，才显示规格列表
     */
    private List<GoodsSpecsDto> specsDtoList = new ArrayList<>();
}