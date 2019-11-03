package com.chisong.green.farm.app.constants.enums;
/**
 * 用户状态
 * @author 孙龙云
 */
public enum UserStatusEnum implements BaseServiceEnum{
	NORMAL(1,"正常"),
	FORZEN(2, "冻结");
	private int code;
	private String value;

	/**
	 * 获取枚举类型
	 * @param code
	 * @return
	 */
	public static UserStatusEnum getUserStatusEnum(Integer code){
		if(null == code){
			return null;
		}
		for(UserStatusEnum userStatusEnum : UserStatusEnum.values()){
			if(userStatusEnum.code == code){
				return userStatusEnum;
			}
		}
		return null;
	}
	UserStatusEnum(int code, String value){
		this.code = code;
		this.value = value;
	}
	
	public int code(){
		return this.code;
	}
	public String value(){
		return this.value;
	}
	
}
