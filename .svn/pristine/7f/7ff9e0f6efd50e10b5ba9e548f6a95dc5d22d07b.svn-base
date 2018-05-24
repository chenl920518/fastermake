package com.cbt.enums;
/**
 * 订单状态
 * @author Administrator
 *
 */
public enum OrderStatusEnum {
	/**
	 * 草稿
	 */
	DRAFT(0,"未发布"),
	/**
	 * 正常订单
	 */
	NORMAL_ORDER(1,"正常订单"),
	/**
	 * 报价已结束
	 */
	CONFIRM(2,"已结束"),
	/**
	 * 报价取消
	 */
	CANCEL(3,"报价取消"),
	

	EXPIRE(4,"已过期"),
	
	
	DECISION(5,"授盘中"),
	
	
	PROCESS(6,"生产中"),
	
	
	NOPASS(7,"审核未通过");


	private int code;
	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;
	
	OrderStatusEnum(int code, String value){
		this.code = code;
		this.value = value;
	}
	
	public static OrderStatusEnum getEnum(int code) {
		for(OrderStatusEnum sourceEnum:  OrderStatusEnum.values()) {
	    	if(sourceEnum.getCode() == code) return sourceEnum;
	    }
		return null;
	}
}
