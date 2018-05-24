package com.cbt.enums;
/**
 * 订单状态
 * @author Administrator
 *
 */
public enum FollowStatusSelfEnum {

	/**
	 * 未拿到订单
	 */
	NO_ORDER(1,"未拿到订单"),
	/**
	 * 拿到订单
	 */
	GET_ORDER(2,"拿到订单"),
	/**
	 * 报订单丢失
	 */
	LOSE_ORDER(3,"订单丢失");
	


	private int code;
	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;
	
	FollowStatusSelfEnum(int code, String value){
		this.code = code;
		this.value = value;
	}
	
	public static FollowStatusSelfEnum getEnum(int code) {
		for(FollowStatusSelfEnum sourceEnum:  FollowStatusSelfEnum.values()) {
	    	if(sourceEnum.getCode() == code) return sourceEnum;
	    }
		return null;
	}
}
