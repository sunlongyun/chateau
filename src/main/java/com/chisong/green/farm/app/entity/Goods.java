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
* @date 2020-04-05
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
    * 商品一级类目id
     */
    private  Integer firstTypeId;

    /**
    * 二级分类id
     */
    private  Integer typeId;

    /**
    * 分类名称
     */
    private  String typeName;

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
    * 商品展示价格(规格明细的最低价格)
     */
    private  Long price;

    /**
    * 展示促销价(规格明细的最低促销价)
     */
    private  Long promotePrice;
    /**
     * 是否促销
     */
    private boolean promote;

    /**
    * 累计销售数量
     */
    private  Integer salesNum;

    /**
    * 促销开始时间  只对参与促销的规格生效
     */
    private  Date promoteStartTime;

    /**
    * 促销结束时间
     */
    private  Date promoteEndTime;

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
