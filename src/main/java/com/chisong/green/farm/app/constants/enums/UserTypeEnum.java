package com.chisong.green.farm.app.constants.enums;

/**
 * 用户类型
 * @author 孙龙云
 */
public enum UserTypeEnum implements BaseServiceEnum {
	ADMIN(3, "平台管理员"),
	CUSTOMER(1, "顾客"),
	SUPER_ADMIN(4, "超级管理员"),
	SUPPLIER(2, "供应商"),
	SALER_AND_CUSTOMER(5, "销售经理兼平台管理员");
	private int code;
	private String value;

	UserTypeEnum(int code, String value) {
		this.code = code;
		this.value = value;
	}

	public int code() {
		return this.code;
	}

	public String value() {
		return this.value;
	}

	/**
	 * 根据code获取value
	 * @param code
	 * @return
	 */
	public static String getValue(int code) {
		UserTypeEnum[] values = UserTypeEnum.values();
		for(UserTypeEnum e : values) {
			if(e.code == code) {
				return e.value;
			}
		}
		return "";
	}
}
