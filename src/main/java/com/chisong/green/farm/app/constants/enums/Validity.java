package com.chisong.green.farm.app.constants.enums;

/**
 * 有效性
 */
public enum Validity {
	AVAIL(1,"有效"),
	IN_VALID(0,"无效");

	int code;
	String value;
	Validity(int code, String value){
		this.value = value;
		this.code = code;
	}

	public int code(){
		return this.code;
	}

	public String value(){
		return this.value;
	}

}
