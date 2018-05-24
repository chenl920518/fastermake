package com.cbt.enums;
/**
 * 地区
 * @author Administrator
 *
 */
public enum MessageTypeEnum {
	
	/**
	 * 工业大众点评消息
	 */
	FACTORYCOMMENT(0,"工业大众点评"),

	/**
	 * 产品库消息
	 */
	PRODUCTLIBRARY(1,"产品库"),
	
	/**
	 * 报价沟通消息
	 */
	QUOTE_MESSAGE(2,"报价沟通消息"),
	
	/**
	 * 大货消息
	 */
	BIG_PRODUCT_MESSAGE(3,"大货消息"),
	
	/**
	 * 报价提示消息
	 */
	OFFER_MESSAGE(4,"报价提示消息"),
	
	
	/**
	 * 报价内部消息
	 */
	QUOTE_INFO(5,"报价内部消息"),
	
	
	/**
	 * 周报消息
	 */
	WEEKLY_REPORT(6,"周报消息"),
	
	
	/**
	 * 其他提示消息
	 */
	OTHER_MESSAGE(6,"其他提示消息");
	
	
	


	private int code;
	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;
	
	MessageTypeEnum(int code, String value){
		this.code = code;
		this.value = value;
	}
	
	public static MessageTypeEnum getEnum(int code) {
		for(MessageTypeEnum sourceEnum:  MessageTypeEnum.values()) {
	    	if(sourceEnum.getCode() == code) return sourceEnum;
	    }
		return null;
	}
	public static MessageTypeEnum getCode(String val) {
		for(MessageTypeEnum sourceEnum:  MessageTypeEnum.values()) {
			if(val.equals(sourceEnum.getValue())) return sourceEnum;
		}
		return null;
	}
}
