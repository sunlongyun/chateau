package com.chisong.green.farm.app.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
/**
* <p>
* 供应商
* </p>
* @author 孙龙云
* @date 2019-11-10
*/
@Data
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private  Integer id;

    /**
    * 公司名称
     */
    private  String companyName;

    /**
    * 供应商logo
     */
    private  String logo;

    /**
    * 地址
     */
    private  String address;

    /**
    * 电话
     */
    private  String telephone;

    private  String linker;

    /**
    * 联系人手机号
     */
    private  String mobile;

    private  Integer areaId;

    private  Integer cityId;

    private  Integer provinceId;

    private  String area;

    private  String city;

    private  String province;

    /**
    * 创建人姓名
     */
    private  String creatorName;

    /**
    * 创建人
     */
    private  Long creatorId;

    /**
    * 公司大图
     */
    private  String pic;

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

    /**
     * 所属app
     */
    private Long appInfoId;
}
