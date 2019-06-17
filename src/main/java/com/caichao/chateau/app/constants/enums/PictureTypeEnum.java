package com.caichao.chateau.app.constants.enums;

/**
 * 图片类型
 */
public enum PictureTypeEnum {
	FIRST_PAGE_ROLL(1,"首页轮播图片"),
	COUNTRY_NAVIGATION(2,"国家葡萄酒引导图片"),
	COUNTRY_DETAIL_IMAGES(3,"国家葡萄酒详情图片");

	int code;
	String value;
	PictureTypeEnum(int code, String value){
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
