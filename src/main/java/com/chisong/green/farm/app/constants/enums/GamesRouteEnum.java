package com.chisong.green.farm.app.constants.enums;

/**
 * 游戏路由
 */
public enum GamesRouteEnum implements BaseServiceEnum{

	DOUBLE(1,"双线机房"), SINGLE(0,"单线机房");

	Integer code;
	String value;

	GamesRouteEnum(Integer code, String value){
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
	 * 获取商品状态
	 * @param status
	 * @return
	 */
	public static GamesRouteEnum getGoodsStatusEnum(Integer status){
		for(GamesRouteEnum goodsStatusEnum : GamesRouteEnum.values()){
			if(goodsStatusEnum.code().equals(status)){
				return goodsStatusEnum;
			}
		}
		return null;
	}
}
