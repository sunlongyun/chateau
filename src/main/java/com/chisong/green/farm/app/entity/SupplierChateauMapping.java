package com.chisong.green.farm.app.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
/**
* <p>
* 供应商庄园映射关系
* </p>
* @author 孙龙云
* @date 2019-07-02
*/
@Data
public class SupplierChateauMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private  Long id;

    /**
    * 供应商id
     */
    private  Integer supplierId;

    /**
    * 庄园id
     */
    private  Integer chateauId;

    /**
    * 是否有效 1-有效；0-无效
     */
    private  Integer validity;

    /**
    * 创建时间
     */
    private  Date createTime;

    /**
    * 更新时间
     */
    private  Date updateTime;

}
