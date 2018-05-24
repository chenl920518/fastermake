package com.cbt.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





/**
 * httpclient获取图片后进行处理
 * @ClassName HttpClientDownloadUtil 
 * @Description TODO
 * @author zj
 * @date 2018年3月15日 上午9:25:35
 */
public class HttpClientDownloadUtil {

	public Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public static String getImg(String value,String csgOrderId) throws Exception {
		    String newName = "";
			value = new String(value.getBytes(), "utf-8");
			
//			String file1="http://112.64.174.34:43888/static_img/"+ value; 
			String file1=GetServerPathUtil.getInnerServerPath()+"/static_img/"+ value; 			
			
			String path = UploadAndDownloadPathUtil.getDrawingFile() + File.separator;
			File file = new File(path);
			if  (!file.exists()  && !file .isDirectory())      
			{         
			   file .mkdir();     
			}  

			try {
					URL url = new URL(file1);
					   // 打开连接
					   URLConnection con = url.openConnection();
					   //设置请求超时为5s
					   con.setConnectTimeout(120*1000);
					   // 输入流
					   InputStream is = con.getInputStream();

					   // 1K的数据缓冲
					   byte[] bs = new byte[1024];
					   // 读取到的数据长度
					   int len;
					   // 输出的文件流
					  File sf=new File(path);
					  if(!sf.exists()){
					  sf.mkdirs();
					  }
				 	  long time = System.currentTimeMillis();
			    	  Date date = new Date(time);    	
			    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					  newName = sdf.format(date) + '.' + FilenameUtils.getExtension(value);
					  int num = 1;
					  File storefile = new File(path,newName);
						 
							//LOG.warn("storefile's path: " + storefile.toString());
						 String sb3=null;
							for(int i=0;storefile.exists();i++){//如果存在同名的附件，则在后面添加数字区分
								 //获取文件名称后面的文件组后一个.的下标（后缀名）
								num++;
					            int index = newName.lastIndexOf(".");
					            //String sb = value.substring(0,index)+i;
					            sb3 = FilenameUtils.removeExtension(newName)+num;
					            newName = sb3+newName.substring(index);
								storefile = new File(path,newName);
							 }
					  
					  OutputStream os = new FileOutputStream(path+File.separator + newName);
					   // 开始读取
					   while ((len = is.read(bs)) != -1) {
					     os.write(bs, 0, len);
					   }
					   // 完毕，关闭所有链接
					   os.close();
					   is.close();
					   return newName;
					} catch (Exception e) {
	                e.printStackTrace();
	            } finally {
	                
	            }
			return newName;		
	}
	
	//获取3D视频
	public static String getVideo(String value) throws Exception {
		String newName = "";
		value = new String(value.getBytes(), "utf-8");
		
//		String file1="http://112.64.174.34:43888/static_img/"+ value; 
		String file1=GetServerPathUtil.getInnerServerPath()+"/static_img/"+ value; 
		
		
		String path = UploadAndDownloadPathUtil.getDrawingFile() + File.separator;
		File file = new File(path);
		if  (!file.exists()  && !file .isDirectory())      
		{         
			file .mkdir();     
		}  
		
		try {
			URL url = new URL(file1);
			// 打开连接
			URLConnection con = url.openConnection();
			//设置请求超时为5s
			con.setConnectTimeout(120*1000);
			// 输入流
			InputStream is = con.getInputStream();
			
			// 1K的数据缓冲
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流
			File sf=new File(path);
			if(!sf.exists()){
				sf.mkdirs();
			}
			long time = System.currentTimeMillis();
			Date date = new Date(time);    	
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			newName = sdf.format(date) + '.' + FilenameUtils.getExtension(value);
			int num = 1;
			File storefile = new File(path,newName);
			
			//LOG.warn("storefile's path: " + storefile.toString());
			String sb3=null;
			for(int i=0;storefile.exists();i++){//如果存在同名的附件，则在后面添加数字区分
				//获取文件名称后面的文件组后一个.的下标（后缀名）
				num++;
				int index = newName.lastIndexOf(".");
				//String sb = value.substring(0,index)+i;
				sb3 = FilenameUtils.removeExtension(newName)+num;
				newName = sb3+newName.substring(index);
				storefile = new File(path,newName);
			}
			
			OutputStream os = new FileOutputStream(path+File.separator + newName);
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			// 完毕，关闭所有链接
			os.close();
			is.close();
			return newName;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return newName;		
	}

	
	
	/**
	 * 下载内部报价原图
	 * @param value
	 * @param csgOrderId
	 * @return
	 * @throws Exception
	 */
	public static String getQuoteImg(String value,String csgOrderId,String orderId) throws Exception {
	    String newName = "";
//		value = new String(value.getBytes(),"utf-8");	
//		value = value.replaceAll(" ", "");
		value = URLEncoder.encode(value, "utf-8");
		System.out.println(value);
//		String file1="http://112.64.174.34:43888/static_img/"+ value; 
		String file1=GetServerPathUtil.getInnerServerPath()+"/static_img/"+ value; 
		
		String path1 = UploadAndDownloadPathUtil.getDrawingFile() + orderId + File.separator;
		File file2 = new File(path1);
		if  (!file2.exists()  && !file2 .isDirectory())      
		{         
		   file2 .mkdir();     
		}  
		String path = path1 + "o_drawing" + File.separator;
		   File file = new File(path); 
		   if  (!file.exists()  && !file.isDirectory())      
			 {         
				 file.mkdir();     
			 } 
		try {
//				   file1 = java.net.URLEncoder.encode(file1.toString(), "utf-8"); 
//				   file1 = URLDecoder.decode(file1, "utf-8");
				   URL url = new URL(file1);
				    
				   // 打开连接
				   URLConnection con = url.openConnection();
				   //设置请求超时为5s
				   con.setConnectTimeout(120*1000);
				   // 输入流
				   InputStream is = con.getInputStream();

				   // 1K的数据缓冲
				   byte[] bs = new byte[1024];
				   // 读取到的数据长度
				   int len;
				   // 输出的文件流
				  File sf=new File(path);
				  if(!sf.exists()){
				  sf.mkdirs();
				  }
				  orderId = orderId.replace(" ", "_");
				  orderId = orderId + "." + FilenameUtils.getExtension(value);
				  newName = OperationFileUtil.changeFilenameTime(orderId);
				  int num = 1;
				  File storefile = new File(path,newName);
					 
						//LOG.warn("storefile's path: " + storefile.toString());
					 String sb3=null;
					for(int i=0;storefile.exists();i++){//如果存在同名的附件，则在后面添加数字区分
						 //获取文件名称后面的文件组后一个.的下标（后缀名）
						num++;
			            int index = newName.lastIndexOf(".");
			            //String sb = value.substring(0,index)+i;
			            sb3 = FilenameUtils.removeExtension(newName)+num;
			            newName = sb3+newName.substring(index);
						storefile = new File(path,newName);
					 }
				  
				  OutputStream os = new FileOutputStream(path+File.separator + newName);
				   // 开始读取
				   while ((len = is.read(bs)) != -1) {
				     os.write(bs, 0, len);
				   }
				   // 完毕，关闭所有链接
				   os.close();
				   is.close();
				   return path+newName;
				} catch (Exception e) {
               e.printStackTrace();
           } finally {
               
           }
		return path+newName;		
}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    public static void main (String[] args){
    	try {
    		String quoteImg = getQuoteImg("20180424092717-电池外壳 5600套一年.7z","SHS19280","28006");
    		System.out.println(quoteImg);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
}
    
    
	

