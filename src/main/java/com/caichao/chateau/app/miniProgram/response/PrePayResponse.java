package com.caichao.chateau.app.miniProgram.response;

import com.caichao.chateau.app.miniProgram.annotaion.MiniFiled;
import javax.validation.constraints.Min;
import lombok.Data;

/**
 * 描述:
 * 预支付返回结果
 * @AUTHOR 孙龙云
 * @date 2019-06-28 22:28
 */
@Data
public class PrePayResponse extends ParentResponse {

	/**
	 * 预支付流水
	 */
	@MiniFiled("prepay_id")
	private String prepayId;
	/**
	 * 支付二维码连接  trade_type=NATIVE时有返回，此url用于生成支付二维码，然后提供给用户进行扫码支付
	 */
	@MiniFiled("code_url")
	private String codeUrl;
	/////////下面支付参数//////////
	/**
	 * package
	 */
	private String packageStr;
	/**
	 * 时间戳
	 */
	private String timeStamp;
	/**
	 * appid
	 */
	private String appId;
	/**
	 * 签名方式
	 */
	private String signType;

}
