package com.chisong.green.farm.app.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* APP名称
* </p>
* @author 孙龙云
* @date 2020-10-05
*/
@Data
public class AppInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
    * 收款openId
     */
    private String openId;

    /**
    * app名称
     */
    private String name;

    /**
    * 联系人
     */
    private String contactUser;

    /**
    * 联系电话
     */
    private String contactPhone;

    /**
    * 是否需要给供应商结款 0-不需要;1-需要
     */
    private Integer paySupplier;

    /**
    * 是否需要设置管理费 0-不需要;1-需要
     */
    private Integer payManager;

    /**
    * 是否需要设置分销费用 0-不需要;1-需要
     */
    private Integer paySaler;

    private Date createTime;

    private Date updateTime;

    /**
    * 是否有效
     */
    private Boolean validity;

    /**
    * 平台服务费百分比，最大100
     */
    private Integer rate;

}