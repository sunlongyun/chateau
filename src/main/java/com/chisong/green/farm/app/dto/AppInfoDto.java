package com.chisong.green.farm.app.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* APP名称
* </p>
* @author 孙龙云
* @date 2020-10-02
*/
@Data
public class AppInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

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

    private Date createTime;

    private Date updateTime;

    /**
    * 是否有效
     */
    private Boolean validity;

    /**
     * 所属app
     */
    private Long appInfoId;
}