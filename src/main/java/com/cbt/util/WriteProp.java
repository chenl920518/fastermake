package com.cbt.util;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

public class WriteProp {

	private static Resource resource = new DefaultResourceLoader()
			.getResource("classpath:wechat.properties");

	// 写入配置文件
	public static void write(String key, String value) {
		try {
			Properties prop = new Properties();
			InputStream fis = resource.getInputStream();
			// 从输入流中读取属性列表（键和元素对）
			prop.load(fis);
			OutputStream fos = new FileOutputStream(resource.getFile());
			prop.setProperty(key, value);
			prop.store(fos,
					"# Output pattern : date [thread] priority category - message");
			fis.close();
			fos.close();// 关闭流
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 通过key获取配置文件
	public static String get(String key) {
		Properties properties = new Properties();
		InputStream fis;
		try {
			fis = resource.getInputStream();
			properties.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String value = properties.getProperty(key);
		return value;
	}
	
	// 通过key获取配置文件
	public static double getDouble(String key) {
		Properties properties = new Properties();
		InputStream fis;
		try {
			fis = resource.getInputStream();
			properties.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String value = properties.getProperty(key);
		if(null == value || "".equals(value)){
			return 0.0d;
		}else{
			return Double.parseDouble(value);
		}
	}
}
