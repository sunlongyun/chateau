package com.chisong.green.farm.app.dto;

import com.chisong.green.farm.app.annotation.AmountUnitChange;
import com.chisong.green.farm.app.annotation.ServiceTypeAnnotation;
import com.chisong.green.farm.app.constants.enums.OrderStatusEnum;
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
    private Integer postage =0;

    /**
    * 物流单号
     */
    private String logisticsNumber;

    /**
    * 支付流水号，支付成功的支付流水号
     */
    private String payNo;
    /**
     * 顾客已支付金额
     */
    @AmountUnitChange(showUnit=true)
    private  Long payedAmount;

    /**
     * 收益
     */
    @AmountUnitChange(showUnit=true)
    private  Long income;

    /**
     * 1 是否已结算分润
     */
    private  Integer shared;

    /**
     * 供应商发货邮费
     */
    @AmountUnitChange(showUnit=true)
    private  Integer supplierPostage;

    /**
     * 给供应商结款额度
     */
    @AmountUnitChange(showUnit=true)
    private  Long supplierAmount;

    /**
     * 给供应商结款状态  0-未结款;1-部分结算；2-已结算
     */
    private  Integer supplierStatus;

    /**
     * 该订单成本(不含邮费)
     */
    @AmountUnitChange(showUnit=true)
    private  Long costAmount;

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
    private OrderDeliveryAddressMappingDto orderDeliveryAddressMappingDto;
    /**
     * 地址
     */
    private CustomerDeliveryAddressDto address ;
    /**
     * 收货地址
     */
    private String userAddress;

    /**
     * 管理员id
     */
    private Integer managerId;
}