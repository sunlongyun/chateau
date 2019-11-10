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
* @date 2019-11-10
*/
@Data
public class ProvinceCityArea implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private  Integer id;

    private  String name;

    private  Integer pid;

    /**
    * 是否有效 1-有效；0-无效
     */
    private  Integer validity;

}
