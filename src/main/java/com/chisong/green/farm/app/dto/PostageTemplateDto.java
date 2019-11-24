package com.chisong.green.farm.app.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* 运费模板
* </p>
* @author 孙龙云
* @date 2019-11-24
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
    * 运费单位件；1-箱；2-袋；3-千克，4-斤，5-瓶，6-升；
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

    @Override
    public String toString() {
        return "PostageTemplateDto{" +
            "goodsId=" + goodsId +
            ", provinces='" + provinces + '\'' +
            ", unit=" + unit +
            ", amount=" + amount +
            ", freeTotalAmount=" + freeTotalAmount +
            ", weight=" + weight +
            ", freeNum=" + freeNum +
            '}';
    }
}