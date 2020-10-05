package com.chisong.green.farm.app.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
/**
* <p>
* 商户支付流水
* </p>
* @author 孙龙云
* @date 2020-10-05
*/
@Data
public class MerchantPayment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private  Long id;

    /**
    * 收款账户id
     */
    private  String openId;

    /**
    * 收款方类型 0-个人;1-企业
     */
    private  Integer userType;

    /**
    * 付款类型 1-商家交易款，2-供货商费，3-分销费；4-管理费;5-平台服务费
     */
    private  Integer payType;

    /**
    * 用户名
     */
    private  String userName;

    /**
    * 0-待支付；1-付款成功;2-付款失败
     */
    private  Integer status;

    /**
    * 交易流水号
     */
    private  String tradeNo;

    /**
    * 支付记录id
     */
    private  Long payId;

    /**
    * 三方支付流水号
     */
    private  String paymentNo;

    /**
    * 交易金额
     */
    private  Integer amount;

    /**
    * 付款备注
     */
    private  String remark;

    /**
    * 创建时间
     */
    private  Date createTime;

    /**
    * 更新时间
     */
    private  Date updateTime;

    /**
    * 是否有效 1-有效;0-无效
     */
    private  Boolean validity;

}
