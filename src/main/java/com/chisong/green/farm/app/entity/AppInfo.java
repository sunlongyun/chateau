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
* @date 2020-10-02
*/
@Data
public class AppInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private  Long id;

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

    private  Date createTime;

    private  Date updateTime;

    /**
    * 是否有效
     */
    private  Boolean validity;

}
