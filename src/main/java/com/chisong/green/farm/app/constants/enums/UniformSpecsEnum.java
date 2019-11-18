package com.chisong.green.farm.app.constants.enums;

/**
 * 是否统一规格，
 */
public enum UniformSpecsEnum implements BaseServiceEnum{

	YES(1,"统一规格"), NO(0,"非统一规格");

	Integer code;
	String value;

	UniformSpecsEnum(Integer code, String value){
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
	public static UniformSpecsEnum getGoodsStatusEnum(Integer status){
		for(UniformSpecsEnum goodsStatusEnum : UniformSpecsEnum.values()){
			if(goodsStatusEnum.code().equals(status)){
				return goodsStatusEnum;
			}
		}
		return null;
	}
}
