package com.chisong.green.farm.app.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
/**
* <p>
* APP名称
* </p>
* @author 孙龙云
* @date 2020-10-05
*/
@Data
public class AppInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private  Long id;

    /**
    * 收款openId
     */
    private  String openId;

    /**
    * app名称
     */
    private  String name;

    /**
    * 联系人
     */
    private  String contactUser;

    /**
    * 联系电话
     */
    private  String contactPhone;

    /**
    * 是否需要给供应商结款 0-不需要;1-需要
     */
    private  Boolean paySupplier;

    /**
    * 是否需要设置管理费 0-不需要;1-需要
     */
    private  Boolean payManager;

    /**
    * 是否需要设置分销费用 0-不需要;1-需要
     */
    private  Boolean paySaler;

    private  Date createTime;

    private  Date updateTime;

    /**
    * 是否有效
     */
    private  Boolean validity;

    /**
    * 平台服务费百分比，最大100
     */
    private  Integer rate;

    /**
    * 是否同城  1-同城；0-非同城
     */
    private  Boolean sameCity;

    /**
    * 同城公里数 
     */
    private  Integer range;

    /**
    * 到账时间 ，单位天
     */
    private  Integer transferDate;

}
