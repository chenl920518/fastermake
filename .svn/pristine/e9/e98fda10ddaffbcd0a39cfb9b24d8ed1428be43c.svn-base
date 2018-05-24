package com.cbt.enums;
/**
 * 订单状态
 * @author Administrator
 *
 */
public enum FileTypeEnum {
	/**
	 * 图片
	 */
	IMG(0,"图片"),
	/**
	 * 进度报告
	 */
	PROGRESS_REPORT(1,"进度报告"),
	/**
	 * 材质证明
	 */
	MATERIAL_REPORT(2,"材质证明"),
	/**
	 * 已付款待确认
	 */
	QUANLITY_REPORT(3,"付款待确认");


	private int code;
	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private String value;
	
	FileTypeEnum(int code, String value){
		this.code = code;
		this.value = value;
	}
	
	public static FileTypeEnum getEnum(int code) {
		for(FileTypeEnum sourceEnum:  FileTypeEnum.values()) {
	    	if(sourceEnum.getCode() == code) return sourceEnum;
	    }
		return null;
	}
}
