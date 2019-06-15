package com.caichao.chateau.app.entity;

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
* @date 2019-06-15
*/
@Data
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 支付流水号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private  Long id;

    /**
    * 支付订单号
     */
    private  String payOrderNo;

    /**
    * 支付流水号
     */
    private  String payNo;

    /**
    * 第三方支付流水号，微信/支付宝流水号
     */
    private  String thirdPayNo;

    /**
    * 支付状态 0-提交；1-成功；2-失败；
     */
    private  Integer status;

    /**
    * 支付时间
     */
    private  Date payTime;

    /**
    * 支付成功时间
     */
    private  Date paySuccessTime;

    /**
    * 创建时间
     */
    private  Date createTime;

    /**
    * 更新时间
     */
    private  Date updateTime;

    /**
    * 是否有效 1-有效；0-无效
     */
    private  Integer validity;

}
