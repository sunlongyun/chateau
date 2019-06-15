package com.caichao.chateau.app.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
/**
* <p>
* 购物车
* </p>
* @author 孙龙云
* @date 2019-06-15
*/
@Data
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private  Integer id;

    /**
    * 客户信息表id
     */
    private  Integer customerInfoId;

    /**
    * 创建时间
     */
    private  Date createTime;

    /**
    * 更新时间
     */
    private  Date updateTime;

    /**
    * 是否有效 1-有效；0-无效
     */
    private  Integer validity;

}
