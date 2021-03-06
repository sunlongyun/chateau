package com.chisong.green.farm.app.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* 运费模板
* </p>
* @author 孙龙云
* @date 2019-12-28
*/
@Data
public class PostageTemplateDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
     */
    private Long id;

    /**
    * 商品id
     */
    private Long goodsId;

    /**
    * 省份名称，逗号隔开
     */
    private String provinces;

    /**
    * 每几个数量增加一次单位邮费，-1永远不增加
     */
    private Integer incUnitNum;

    /**
    * 运费单位件；1-件；2-袋；3-千克，4-斤，5-瓶，6-升；7-箱
     */
    private Integer unit;

    /**
    * 单位价格，  金额单位：分
     */
    private Integer amount;

    /**
    * 包邮的总金额
     */
    private Integer freeTotalAmount;

    /**
    * 权重 ，序号越大，优先级越高
     */
    private Integer weight;

    /**
    * 包邮的起始数量
     */
    private Integer freeNum;

    /**
    * 1-有效；0-无效
     */
    private Integer validity;

}