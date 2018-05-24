package com.cbt.enums;
/**
 * 订单状态
 * @author Administrator
 *
 */
public enum BigProductStatusEnum {
	/**
	 * 未接受
	 */
	NOT_ACCEPT(0,"未接受"),
	/**
	 * 已接受
	 */
	ACCEPT(1,"已接受"),
	/**
	 * 已拒绝
	 */
	REFUSE(2,"已拒绝"),
	/**
	 * 已付款待确认
	 */
	NEED_CONFIRM(3,"付款待确认"),
	

	CONFIRM(4,"确认到账");


	private int code;
	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;
	
	BigProductStatusEnum(int code, String value){
		this.code = code;
		this.value = value;
	}
	
	public static BigProductStatusEnum getEnum(int code) {
		for(BigProductStatusEnum sourceEnum:  BigProductStatusEnum.values()) {
	    	if(sourceEnum.getCode() == code) return sourceEnum;
	    }
		return null;
	}
}
