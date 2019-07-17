package com.caichao.chateau.app.dto;

import com.caichao.chateau.app.annotation.AmountUnitChange;
import com.caichao.chateau.app.annotation.ServiceTypeAnnotation;
import com.caichao.chateau.app.constants.enums.OrderStatusEnum;
import com.caichao.chateau.app.constants.enums.UserStatusEnum;
import com.caichao.chateau.app.entity.CustomerDeliveryAddress;
import java.io.Serializable;
import java.util.Date;

import java.util.List;
import lombok.Data;
/**
* <p>
* 
* </p>
* @author 孙龙云
* @date 2019-07-06
*/
@Data
public class OrderInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 订单
     */
    private Long id;

    /**
    * 订单号
     */
    private String orderNo;

    /**
    * 客户姓名
     */
    private String customerName;

    /**
    * 客户手机号
     */
    private String mobile;

    private Long customerId;

    /**
    * 订单状态 0-下单待支付；1-下单已支付；2-卖家已发货;3-已收货;4-订单已取消;
     */
    @ServiceTypeAnnotation(value = OrderStatusEnum.class)
    private Integer status;

    /**
    * 主动取消订单原因0-未取消; 1-不想要了，2-订单过期未支付
     */
    private Integer cancelOpt;

    /**
    * 取消订单备注
     */
    private String cancelRemark;

    /**
    * 邮费
     */
    @AmountUnitChange(showUnit=true)
    private Integer postage = 0;

    /**
    * 物流单号
     */
    private String logisticsNumber;

    /**
    * 支付流水号，支付成功的支付流水号
     */
    private String payNo;

    /**
    * 订单总金额 单位：分
     */
    @AmountUnitChange(showUnit=true)
    private Long totalAmount;

    /**
    * 创建时间
     */
    private Date createTime;

    /**
    * 修改时间
     */
    private Date updateTime;

    /**
    * 是否有效 1-有效；0-无效
     */
    private Integer validity;
    /**
     * 订单详情
     */
    private List<OrderDetailDto> orderDetailDtoList;

    /**
     * 收货地址
     *
     */
    private CustomerDeliveryAddressDto address;
}