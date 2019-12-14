package com.chisong.green.farm.app.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
/**
* <p>
* 游戏服务推广表
* </p>
* @author 孙龙云
* @date 2019-12-01
*/
@Data
public class Games implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private  Integer id;

    /**
    * 服务器名
     */
    private  String title;

    /**
    * 服务器ip
     */
    private  String ip;

    /**
    * 开机时间
     */
    private  String openTimes;

    /**
    * 线路类别 1-双线机房；0-单线机房
     */
    private  Integer route;

    /**
    * 详细介绍
     */
    private  String detail;

    private  String qq;

    /**
    * 创建时间
     */
    private  Date createTime;

    /**
    * 更新时间
     */
    private  Date updateTime;

    /**
    * 有效截止期
     */
    private  Date expireTimes;

    /**
    * 权重 1-100
     */
    private  Integer weight;

    /**
    * 下载地址
     */
    private  String downloadUrl;

    /**
    * 是否有效 1-有效；0-无效
     */
    private  Integer validity;

}
