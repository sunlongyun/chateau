package com.chisong.green.farm.app.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* 
* </p>
* @author 孙龙云
* @date 2019-11-10
*/
@Data
public class ProvinceCityAreaDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private Integer pid;

    /**
    * 是否有效 1-有效；0-无效
     */
    private Integer validity;

    /**
     * 所属app
     */
    private Long appInfoId;
}