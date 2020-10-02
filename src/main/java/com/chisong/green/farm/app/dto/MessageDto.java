package com.chisong.green.farm.app.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
* <p>
* 客服中心消息
* </p>
* @author 孙龙云
* @date 2020-03-01
*/
@Data
public class MessageDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
    * 发送者openId
     */
    private String from;

    /**
    * 接收者openId
     */
    private String to;

    /**
    * 消息类型 text-文本，image-图片，link-图文连接
     */
    private String msgType;

    /**
    * 商品id
     */
    private Long goodsId;

    /**
    * 订单id
     */
    private Long orderId;

    /**
    * 顾客id
     */
    private Long customerId;

    /**
    * 1--已读；0--未读
     */
    private Integer read;

    /**
    * 消息内容
     */
    private String content;

    /**
    * 消息id
     */
    private Long msgId;

    /**
    * 创建时间
     */
    private Date createTime;

    /**
    * 更新时间
     */
    private Date updateTime;

    /**
    * 是否有效 1-有效；0-无效
     */
    private Integer validity;

    /**
     * 所属app
     */
    private Long appInfoId;
}