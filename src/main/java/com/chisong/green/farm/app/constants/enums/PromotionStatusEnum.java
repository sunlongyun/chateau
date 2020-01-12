package com.chisong.green.farm.app.constants.enums;

/**
 * 商品促销状态
 */
public enum PromotionStatusEnum implements BaseServiceEnum{

	PROMOTION_ING(1,"促销中"), UN_PROMOTION(0,"未促销");

	Integer code;
	String value;

	PromotionStatusEnum(Integer code, String value){
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
	public static PromotionStatusEnum getGoodsStatusEnum(Integer status){
		for(PromotionStatusEnum promotionStatusEnum : PromotionStatusEnum.values()){
			if(promotionStatusEnum.code().equals(status)){
				return promotionStatusEnum;
			}
		}
		return null;
	}
}
