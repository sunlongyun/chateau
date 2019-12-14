package com.chisong.green.farm.app.dto;

import com.chisong.green.farm.app.annotation.ServiceTypeAnnotation;
import com.chisong.green.farm.app.annotation.ServiceTypeAnnotation.Type;
import com.chisong.green.farm.app.constants.enums.GamesRouteEnum;
import com.chisong.green.farm.app.constants.enums.UserTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
* <p>
* 游戏服务推广表
* </p>
* @author 孙龙云
* @date 2019-12-01
*/
@Data
public class GamesDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
     */
    private Integer id;

    /**
    * 服务器名
     */
    private String title;

    /**
    * 服务器ip
     */
    private String ip;

    /**
    * 开机时间
     */
    private String openTimes;

    /**
    * 线路类别 1-双线机房；0-单线机房
     */
    @ServiceTypeAnnotation(value = GamesRouteEnum.class,type= Type.VALUE)
    private Integer route;

    /**
    * 详细介绍
     */
    private String detail;

    private String qq;

    /**
    * 创建时间
     */
    private Date createTime;

    /**
    * 更新时间
     */
    private Date updateTime;

    /**
    * 有效截止期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expireTimes;

    /**
    * 权重 1-100
     */
    private Integer weight;

    /**
    * 下载地址
     */
    private String downloadUrl;

    /**
    * 是否有效 1-有效；0-无效
     */
    private Integer validity;
    /**
     * 访问次数
     */
    private Integer times =0;
}