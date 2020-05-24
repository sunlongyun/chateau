package com.chisong.green.farm.app.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
/**
* <p>
* 账户流水
* </p>
* @author 孙龙云
* @date 2020-05-23
*/
@Data
public class AccountFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private  Long id;

    /**
    * 账户id
     */
    private  Integer accountId;

    /**
    * 0-流出，1-流入
     */
    private  Integer type;

    /**
    * 状态 0-进行中；1-已完成
     */
    private  Integer status;

    /**
    * 收入来源  0-分享赚钱
     */
    private  Integer source;

    /**
    * 流水金额
     */
    private  Integer amount;

    /**
    * 操作说明
     */
    private  String operateName;

    /**
    * 是否有效 1-有效；0-无效
     */
    private  Integer validity;

    /**
    * 创建时间
     */
    private  Date createTime;

}
