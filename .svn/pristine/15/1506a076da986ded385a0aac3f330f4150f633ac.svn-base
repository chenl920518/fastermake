package com.cbt.enums;
/**
 * 增加用户权限等级枚举
 * @author Polo
 *
 */
public enum PermissionEnum {
	/**
	 * 管理员
	 */
	ADMIN(1,"管理员"),
	/**
	 * 报价员
	 */
	QUOTER(10,"报价员"),
	/**
	 * 报价助理
	 */
	ASSISTANT(20,"报价助理");


	private int code;
	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;
	
	PermissionEnum(int code, String value){
		this.code = code;
		this.value = value;
	}
	
	public static PermissionEnum getEnum(int code) {
		for(PermissionEnum sourceEnum:  PermissionEnum.values()) {
	    	if(sourceEnum.getCode() == code) return sourceEnum;
	    }
		return null;
	}
	public static PermissionEnum getCode(String val) {
		for(PermissionEnum sourceEnum:  PermissionEnum.values()) {
			if(val.equals(sourceEnum.getValue())) return sourceEnum;
		}
		return null;
	}
}
