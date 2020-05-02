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
* @date 2020-05-02
*/
@Data
public class SummaryInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private  Integer id;

    /**
    * 订单总数量
     */
    private  Long orderNums;

    /**
    * 有效商品数量
     */
    private  Integer goodsNormalNum;

    /**
    * 商品总数量
     */
    private  Integer goodsNum;

    /**
    * 今日有效订单数量
     */
    private  Long todayPayedNum;

    /**
    * 本月有效订单数量
     */
    private  Long monthPayedNum;

    /**
    * 周成交量
     */
    private  Long weekPayedNum;

    /**
    * 今日营业额
     */
    private  Long todayPayedAmount;

    /**
    * 月营业额
     */
    private  Long monthPayedAmount;

    /**
    * 周营业
     */
    private  Long weekPayedAmount;

    /**
    * 今日新增粉丝
     */
    private  Integer todayCustomerNum;

    /**
    * 本月新增粉丝
     */
    private  Integer monthCustomerNum;

    /**
    * 本周新增粉丝
     */
    private  Integer weekCustomerNum;

    /**
    * 总成交订单量
     */
    private  Long payedNums;

    /**
    * 供应商总数量
     */
    private  Integer supplierNum;

    /**
    * 平台粉丝数量
     */
    private  Long customerNum;

    /**
    * 总支付金额
     */
    private  Long payedAmount;

    /**
    * 是否有效 0-无效；1-有效
     */
    private  Integer validity;

    /**
    * 创建时间
     */
    private  Date createTime;

    /**
    * 更新时间
     */
    private  Date updateTime;

}
