package com.cbt.enums;
/**
 * 订单状态
 * @author Administrator
 *
 */
public enum QuoteOrderStatusEnum {
	/**
	 * 草稿
	 */
	DRAFT(0,"草稿"),
	/**
	 * 正常订单
	 */
	NORMAL_ORDER(1,"报价中"),
	/**
	 * 已授盘
	 */
	CONFIRM_ORDER(2,"已授盘"),
	
	PROCESS_ORDER(3,"生产中"),
	
	CANCEL_ORDER(4,"已撤销"),
	
	FINISH_ORDER(5,"已完成"),
	
	EXPIRE_ORDER(6,"已过期"),
	
	REFUSE_ORDER(7,"被拒绝");

	private int code;
	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;
	
	QuoteOrderStatusEnum(int code, String value){
		this.code = code;
		this.value = value;
	}
	
	public static QuoteOrderStatusEnum getEnum(int code) {
		for(QuoteOrderStatusEnum sourceEnum:  QuoteOrderStatusEnum.values()) {
	    	if(sourceEnum.getCode() == code) return sourceEnum;
	    }
		return null;
	}
}
