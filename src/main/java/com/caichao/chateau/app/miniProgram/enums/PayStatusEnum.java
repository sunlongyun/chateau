package com.caichao.chateau.app.miniProgram.enums;

/**
 * 支付状态枚举
 */
public enum PayStatusEnum {
	SUCCESS("SUCCESS","支付成功"),
	REFUND("REFUND","转入退款"),
	NOTPAY("NOTPAY","未支付"),
	CLOSED("CLOSED","已关闭"),
	REVOKED("REVOKED","已撤销"),
	USERPAYING("USERPAYING","用户支付中"),
	PAYERROR("PAYERROR","支付失败");

	private String code;
	private String value;
	PayStatusEnum(String code, String value){
		this.code = code;
		this.value = value;
	}

	public String code(){
		return this.code;
	}
	public String value(){
		return this.value;
	}
}
