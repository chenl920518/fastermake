package com.cbt.enums;
/**
 * 内部报价/NBMail同步的项目状态
 * @author polo
 *
 */
public enum FollowStatusQuotationEnum {

	/**
	 * 正常沟通中
	 */
	NORMAL_COMMUNICATION(1,"正常沟通中"),
	/**
	 * 深入沟通
	 */
	DEEP_COMMUNICATION(2,"深入沟通"),
	/**
	 * 生产
	 */
	PRODUCTION(3,"生产"),
	/**
	 * 报价太高或者错误
	 */
	QUOTE_ERROR(4,"报价太高或者错误"),
	/**
	 * 暂停
	 */
	QUOTE_PAUSE(5,"暂停"),
	/**
	 * 完成
	 */
	QUOTE_COMPLETE(6,"完成"),
	/**
	 * 此项目死亡(价格太高)
	 */
	HIGH_PRICE_DEATH(7,"此项目死亡(价格太高)"),
	/**
	 * 此项目死亡(其他原因)
	 */
	OTHER_DEATH(8,"此项目死亡(其他原因)");
	


	private int code;
	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;
	
	FollowStatusQuotationEnum(int code, String value){
		this.code = code;
		this.value = value;
	}
	
	public static FollowStatusQuotationEnum getEnum(int code) {
		for(FollowStatusQuotationEnum sourceEnum:  FollowStatusQuotationEnum.values()) {
	    	if(sourceEnum.getCode() == code) return sourceEnum;
	    }
		return null;
	}
}
