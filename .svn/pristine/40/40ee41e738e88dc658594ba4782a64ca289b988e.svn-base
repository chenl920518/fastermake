package com.cbt.util;

import java.io.IOException;
import java.util.Properties;

public class UploadAndDownloadPathUtil {  
    private static Properties p = new Properties();  
  
    /** 
     * 读取properties配置文件信息 
     */  
    static{  
        try {  
            p.load(UploadAndDownloadPathUtil.class.getClassLoader().getResourceAsStream("upLoadAndDownLoadPath.properties"));  
        } catch (IOException e) {  
            e.printStackTrace();   
        }  
    }  
    /** 
     * 根据key得到value的值 
     */  
    public static String getQuoteFile()  
    {  
        return p.getProperty("quote_file");  
    }  
    
    //产品库添加图片地址
    public static String getProductPic()  
    {  
        return p.getProperty("product_pic");  
    }  
    
    public static String getProductPicStatic()  
    {  
        return p.getProperty("product_pic_static");  
    } 
    
    //采购商发起询盘地址
    public static String getQuoteProductPath(){
    	
    	return p.getProperty("quote_product");  
    	
    }
    //工厂营业执照
    public static String getFactoryLicense(){
    	return p.getProperty("factory_license");  	
    }
    //工厂企业Logo
    public static String getFactoryLogo(){
		return p.getProperty("factory_logo");
    }
    
    //工厂厂门照面
    public static String getFactoryGate(){
    	return p.getProperty("factory_gate");  	
    }
    
   //工厂生产环境
    public static String getProductionEnvironment(){
    	return p.getProperty("production_environment");  	
    }
    //工厂设备生产视频
    public static String getProductionVideo(){
    	return p.getProperty("production_video");  	
    }
    //工厂资质证明
    public static String getQualificationFile(){
    	return p.getProperty("qualification_file");  	
    }
    //图纸缩略图
    public static String getDrawingFile(){
    	return p.getProperty("drawing_file");  	
    }
    public static String getDrawingFileStatic(){
    	return p.getProperty("drawing_file_static");  	
    }
    public static String getFactoryDocument(){
    	return p.getProperty("factory_document");  	
    }
    public static String getQuoteFileStatic(){
    	return p.getProperty("quote_static");  	
    }
    public static String getFactoryEquipment(){
    	return p.getProperty("factory_equipment");  	
    }
   
}  
	
	



