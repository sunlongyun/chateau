package com.chisong.green.farm.app.constants.enums;

public enum OrderStatusEnum implements BaseServiceEnum{
	UN_PAY(0,"下单未支付"),
	PAYED(1,"下单已支付"),
	DELIVERY(2, "卖家已发货"),
	RECEIVED(3,"买家已收货"),
	CANCELED(4, "订单已取消"),
	DELETED(5, "订单已删除");

	private Integer code;
	private String value;

	OrderStatusEnum(Integer code, String value){
		this.code =code;
		this.value = value;
	}
	public Integer code(){
		return this.code;
	}
	public String value(){
		return this.value;
	}

	/**
	 * 根据code查询value
	 * @param code
	 * @return
	 */
	public static String getValue(Integer code){
		for(OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()){
			if(orderStatusEnum.code().equals(code)){
				return orderStatusEnum.value();
			}
		}
		return "未知";
	}
}
