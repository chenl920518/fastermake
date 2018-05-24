package com.cbt.enums;
/**
 * 客户类型
 * @author Administrator
 *
 */
public enum CustomerTypeEnum {
	/**
	 * 实单老客户
	 */
	DRAFT(1,"实单老客户"),
	/**
	 * 新客户-大中公司
	 */
	NORMAL_ORDER(2,"新客户-大中公司"),
	/**
	 * 新客户-小公司
	 */
	CONFIRM(3,"新客户-小公司");
	private int code;
	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;
	
	CustomerTypeEnum(int code, String value){
		this.code = code;
		this.value = value;
	}
	
	public static CustomerTypeEnum getEnum(int code) {
		for(CustomerTypeEnum sourceEnum:  CustomerTypeEnum.values()) {
	    	if(sourceEnum.getCode() == code) return sourceEnum;
	    }
		return null;
	}
	
	public static CustomerTypeEnum getEnum(String value) {
		for(CustomerTypeEnum sourceEnum:  CustomerTypeEnum.values()) {
	    	if(value.equals(sourceEnum.getValue())) return sourceEnum;
	    }
		return null;
	}
}
