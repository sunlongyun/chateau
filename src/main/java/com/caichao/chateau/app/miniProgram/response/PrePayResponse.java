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
	 * 返回状态码SUCCESS/FAIL
	 */
	@MiniFiled("return_code")
	private String returnCode;
	/**
	 * 返回信息
	 */
	@MiniFiled("return_msg")
	private String returnMsg;
	/**
	 * appId
	 */
	@MiniFiled("appid")
	private String appid;
	/**
	 * 商户id
	 */
	@MiniFiled("mch_id")
	private String mchId;
	/**
	 * 设备号
	 */
	@MiniFiled("device_info")
	private String deviceInfo;
	/**
	 * 随机字符串
	 */
	@MiniFiled("nonce_str")
	private String nonceStr;
	/**
	 * 签名
	 */
	private String sign;
	/**
	 * 业务结果
	 */
	@MiniFiled("result_code")
	private String resultCode;

	/**
	 * 错误描述信息
	 */
	@MiniFiled("err_code_des")
	private String errCodeDes;
	/**
	 * 交易类型
	 */
	@MiniFiled("trade_type")
	private String tradeType;
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
}
