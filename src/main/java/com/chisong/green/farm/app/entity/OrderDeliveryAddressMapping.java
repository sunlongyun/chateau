package com.chisong.green.farm.app.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
/**
* <p>
* 订单--收货地址映射表
* </p>
* @author 孙龙云
* @date 2019-11-16
*/
@Data
public class OrderDeliveryAddressMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private  Long id;

    /**
    * 订单id
     */
    private  Long orderId;

    private  String address;

    /**
    * 订单号
     */
    private  String orderNo;

    /**
    * 收货地址
     */
    private  Integer addressId;

    /**
    * 物流单号
     */
    private  String logisticsNumber;

    /**
    * 创建时间
     */
    private  Date createTime;

    /**
    * 更新时间
     */
    private  Date updateTime;

    /**
    * 是否有效
     */
    private  Integer validity;

}
