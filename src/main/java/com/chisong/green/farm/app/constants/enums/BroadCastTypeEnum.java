package com.chisong.green.farm.app.constants.enums;

/**
 * 直播类型
 */
public enum BroadCastTypeEnum {

	DAILY(0,"日常直播"),
	MASTER(1, "庄主直播");

	Integer code;
	String value;
	BroadCastTypeEnum(Integer code, String value){
		this.code = code;
		this.value = value;
	}

	public Integer code(){
		return this.code;
	}

	public String value(){
		return this.value;
	}

	/**
	 *获取直播类型
	 * @param type
	 * @return
	 */
	public static BroadCastTypeEnum getBroadCastTypeEnum(Integer type){

		for(BroadCastTypeEnum broadCastTypeEnum : BroadCastTypeEnum.values()){
			if(broadCastTypeEnum.code().equals(type)){
				return broadCastTypeEnum;
			}
		}

		return null;
	}
}
