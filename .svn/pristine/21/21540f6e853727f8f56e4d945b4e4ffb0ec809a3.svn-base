package com.cbt.enums;
/**
 * 地区
 * @author Administrator
 *
 */
public enum StateEnum {
	/**
	 * 任何地区
	 */
	ALL_STATE(0,"任何地区"),
	/**
	 * 上海
	 */
	SHANGHAI(1,"上海市"),
	/**
	 * 浙江
	 */
	ZHEJIANG(1,"浙江"),
	/**
	 * 江苏
	 */
	JIANGSUN(1,"江苏"),
	/**
	 * 深圳
	 */
	SEHNZHEN(2,"深圳"),
	/**
	 * 广州
	 */
	GUANGZHOU(2,"广州"),
	/**
	 * 福建
	 */
	FUJIAN(2,"福建");


	private int code;
	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;
	
	StateEnum(int code, String value){
		this.code = code;
		this.value = value;
	}
	
	public static StateEnum getEnum(int code) {
		for(StateEnum sourceEnum:  StateEnum.values()) {
	    	if(sourceEnum.getCode() == code) return sourceEnum;
	    }
		return null;
	}
	public static StateEnum getCode(String val) {
		for(StateEnum sourceEnum:  StateEnum.values()) {
			if(val.equals(sourceEnum.getValue())) return sourceEnum;
		}
		return ALL_STATE;
	}
}
