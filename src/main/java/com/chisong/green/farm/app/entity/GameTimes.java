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
* @date 2019-12-07
*/
@Data
public class GameTimes implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private  Integer id;

    /**
    * 游戏id
     */
    private  Integer gamesId;

    /**
    * 创建时间
     */
    private  Date createTime;

    /**
    * ip地址
     */
    private  String ip;

    /**
    * 是否有效 1-有效；0-无效
     */
    private  Integer validity;

}
