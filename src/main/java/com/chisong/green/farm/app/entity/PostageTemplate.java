package com.chisong.green.farm.app.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
/**
* <p>
* 运费模板
* </p>
* @author 孙龙云
* @date 2019-11-10
*/
@Data
public class PostageTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private  Long id;

    /**
    * 商品id
     */
    private  Long goodsId;

    /**
    * 省份名称，逗号隔开
     */
    private  String provinces;

    /**
    * 运费单位 1-件；2-kg; 3-箱
     */
    private  Integer unit;

    /**
    * 单位价格，  金额单位：分
     */
    private  Integer amount;

    /**
    * 包邮的总金额
     */
    private  Integer freeTotalAmount;

    /**
    * 包邮的起始数量
     */
    private  Integer freeNum;

    /**
    * 1-有效；0-无效
     */
    private  Integer validity;

}
