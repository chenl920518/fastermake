package com.cbt.enums;
/**
 * 订单状态
 * @author Administrator
 *
 */
public enum OrderStatusSelfEnum {

	/**
	 * 投标中
	 */
	NORMAL(1,"投标中"),
	/**
	 * 投标结束 / 选标中
	 */
	DECISION(2,"投标结束 / 选标中"),
	/**
	 * 投标结束 / 客户跟进中
	 */
	FOLLOW(3,"投标结束 / 客户跟进中"),
	
    /**
     * 投标结束 / 客户现有的供应商价格更便宜
     */
	REFUSE(4,"投标结束 / 客户现有的供应商价格更便宜"),
	
	/**
	 * 投标结束 / 客户不能给出合理理由
	 */
	CANCEL(5,"投标结束 / 客户不能给出合理理由");


	private int code;
	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;
	
	OrderStatusSelfEnum(int code, String value){
		this.code = code;
		this.value = value;
	}
	
	public static OrderStatusSelfEnum getEnum(int code) {
		for(OrderStatusSelfEnum sourceEnum:  OrderStatusSelfEnum.values()) {
	    	if(sourceEnum.getCode() == code) return sourceEnum;
	    }
		return null;
	}
}
