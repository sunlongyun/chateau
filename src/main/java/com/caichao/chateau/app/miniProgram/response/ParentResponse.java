package com.caichao.chateau.app.miniProgram.response;

import com.caichao.chateau.app.miniProgram.annotaion.MiniFiled;
import lombok.Data;

/**
 * 描述:
 *
 * @AUTHOR 孙龙云
 * @date 2019-06-07 18:18
 */
@Data
public class ParentResponse {
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
	 * 错误码
	 * errcode 的合法值
	 * 值	说明	最低版本
	 * -1	系统繁忙，此时请开发者稍候再试
	 * 0	请求成功
	 * 40029	code 无效
	 * 45011	频率限制，每个用户每分钟100次
	 */
	@MiniFiled("errcode")
	public Number errCode;
	/**
	 * 错误信息
	 */
	@MiniFiled("errmsg")
	public String errMsg;
	/**
	 * 交易类型
	 */
	@MiniFiled("trade_type")
	private String tradeType;
}
