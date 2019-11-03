package com.chisong.green.farm.app.constants.enums;

/**
 * 直播状态
 */
public enum BroadCastStatusEnum {

	ING(0,"直播中"), UN(1,"尚未直播");

	Integer code;
	String value;

	BroadCastStatusEnum(Integer code, String value){
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
	 * 获取直播状态
	 * @param status
	 * @return
	 */
	public static BroadCastStatusEnum getBroadCastStatusEnum(Integer status){
		for(BroadCastStatusEnum broadCastStatusEnum : BroadCastStatusEnum.values()){
			if(broadCastStatusEnum.code().equals(status)){
				return broadCastStatusEnum;
			}
		}
		return null;
	}
}
