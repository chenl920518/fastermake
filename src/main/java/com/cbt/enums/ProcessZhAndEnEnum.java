package com.cbt.enums;
/**
 * 地区
 * @author Administrator
 *
 */
public enum ProcessZhAndEnEnum {

	Injection("注塑","Injection molding"),
	Vacuum("吸塑","Vacuum forming"),
	Rotational("滚塑","Rotational molding"),
	Blow("吹塑","Blow molding"),
	Plastic("挤塑","Plastic extrusion"),
	Others_Plastic("其他塑料工艺","Others(Plastic)"),
	
	Stainless("不锈钢铸造(硅溶胶、水玻璃)","Stainless steel casting (silicon glue, water glass)"),
	Die_casting("压铸(铝、锌、镁、铜等)","Die casting (aluminum, zinc, magnesium, copper, etc.)"),
	Sand_casting("砂铸(钢、铁、铜、铝等)","Sand casting (steel, iron, copper, aluminum, etc.)"),
	Gravity_casting("重力铸造（铝、铜等)","Gravity casting (aluminum, copper, etc.)"),
	Others_casting("其他铸造","Others(casting)"),
	Hot_forging("热锻","Hot forging"),
	Cold_forging("冷锻","Cold forging"),
	Raw_material("原料采购粗加工","Raw material procurement"),
	Sheet_metal("钣金加工(切割，折弯，焊接，组装)","Sheet metal machining (cutting, bending, welding, assembly)"),
	Stamping("冲压拉伸","Stamping and stretching"),
	Spinning("旋压","Spinning"),
	Aluminum_extrusion("铝挤压","Raw material procurement"),
	CNC_machining("3轴以上精密加工中心","CNC machining center (3-axes or more)"),
	Lathe_machine("高速车床","High-speed lathe machine"),
	Milling_machine("高速铣床","High-speed milling machine"),
	Ordinary_lathe("普通车铣钻磨","Ordinary lathe & millilng machine"),
	Others("其他","Others"),
	Prompt_goods ("现成商品采购","Prompt Goods"),
	Other_purchases("其他采购","Other purchases");


	private String str;
	public String getStr() {
		return str;
	}

	public String getValue() {
		return value;
	}

	private String value;
	
	ProcessZhAndEnEnum(String str, String value){
		this.str = str;
		this.value = value;
	}
	
	public static ProcessZhAndEnEnum getEnum(String str) {
		for(ProcessZhAndEnEnum sourceEnum:  ProcessZhAndEnEnum.values()) {
	    	if(str.equals(sourceEnum.getStr())) return sourceEnum;
	    	if(str.equals(sourceEnum.getValue())) return sourceEnum;
	    }
		return null;
	}

}
