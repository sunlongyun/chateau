package com.chisong.green.farm.app.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
/**
* <p>
* 
* </p>
* @author 孙龙云
* @date 2019-07-06
*/
@Data
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 订单
     */
    @TableId(value = "id", type = IdType.AUTO)
    private  Long id;

    /**
    * 订单号
     */
    private  String orderNo;

    /**
    * 客户姓名
     */
    private  String customerName;

    /**
    * 客户手机号
     */
    private  String mobile;

    private  Long customerId;

    /**
    * 订单状态 0-下单待支付；1-下单已支付；2-卖家已发货;3-已收货;4-订单已取消;
     */
    private  Integer status;

    /**
    * 主动取消订单原因0-未取消; 1-不想要了，2-订单过期未支付
     */
    private  Integer cancelOpt;

    /**
    * 取消订单备注
     */
    private  String cancelRemark;
    /**
     * 运费
     */
    private Long postage = 0l;

    /**
    * 物流单号
     */
    private  String logisticsNumber;

    /**
    * 支付流水号，支付成功的支付流水号
     */
    private  String payNo;

    /**
    * 订单总金额 单位：分
     */
    private  Long totalAmount;

    /**
    * 创建时间
     */
    private  Date createTime;

    /**
    * 修改时间
     */
    private  Date updateTime;

    /**
    * 是否有效 1-有效；0-无效
     */
    private  Integer validity;

}
