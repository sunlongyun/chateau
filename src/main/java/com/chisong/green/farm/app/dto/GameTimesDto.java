package com.chisong.green.farm.app.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* 
* </p>
* @author 孙龙云
* @date 2019-12-07
*/
@Data
public class GameTimesDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
    * 游戏id
     */
    private Integer gamesId;

    /**
    * 创建时间
     */
    private Date createTime;

    /**
    * ip地址
     */
    private String ip;

    /**
    * 是否有效 1-有效；0-无效
     */
    private Integer validity;

}