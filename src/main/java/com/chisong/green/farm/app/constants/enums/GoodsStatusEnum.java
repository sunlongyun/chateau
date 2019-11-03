package com.chisong.green.farm.app.constants.enums;

/**
 * 商品状态
 */
public enum GoodsStatusEnum implements BaseServiceEnum{

	NORMAL(1,"销售中"), DOWNLOAD(0,"已下架");

	Integer code;
	String value;

	GoodsStatusEnum(Integer code, String value){
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
	public static GoodsStatusEnum getGoodsStatusEnum(Integer status){
		for(GoodsStatusEnum goodsStatusEnum : GoodsStatusEnum.values()){
			if(goodsStatusEnum.code().equals(status)){
				return goodsStatusEnum;
			}
		}
		return null;
	}
}
