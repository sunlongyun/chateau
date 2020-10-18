package com.chisong.green.farm.app.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* APP名称
* </p>
* @author 孙龙云
* @date 2020-10-17
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
    * appId
     */
    private String appId;

    /**
    * APP秘钥
     */
    private String appSecret;

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
    private Boolean paySupplier;

    /**
    * 商家推荐费，分享平台佣金 0-否，1-是
     */
    private Boolean payRecommend;

    /**
    * 分享平台佣金比例(%),不超过100
     */
    private Integer recommendRate;

    /**
    * 推荐人id
     */
    private Long recommendCustomerid;

    /**
    * 是否需要设置管理费 0-不需要;1-需要
     */
    private Boolean payManager;

    /**
    * 是否需要设置分销费用 0-不需要;1-需要
     */
    private Boolean paySaler;

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

    /**
    * 是否同城  1-同城；0-非同城
     */
    private Boolean sameCity;

    /**
    * 同城公里数 
     */
    private Integer rangeLimit;

    /**
    * 到账时间 ，单位天
     */
    private Integer transferDate;

}