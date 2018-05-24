package com.cbt.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @Description 操作文件工具类
 * @author LJ
 * @Date 2016年6月8日 上午11:42:44
 * @Version v1.0
 */
public final class OperationFileUtil {
    private static final String ENCODING = "utf-8";
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());  

    /**
     * 文件下载
     * 
     * @param filePath
     *            文件路径
     * @return
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public static ResponseEntity<byte[]> download(String filePath) throws UnsupportedEncodingException, IOException {
        String fileName = FilenameUtils.getName(filePath);
        return downloadAssist(filePath, fileName);
    }

    /**
     * 文件下载
     * 
     * @param filePath
     *            文件路径
     * @param fileName
     *            文件名
     * @return
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public static ResponseEntity<byte[]> download(String filePath, String fileName) throws UnsupportedEncodingException, IOException {
        return downloadAssist(filePath, fileName);
    }

    /**
     * 文件下载辅助
     * 
     * @param filePath
     *            文件路径
     * @param fileName
     *            文件名
     * @return
     * @throws IOException
     */
    private static ResponseEntity<byte[]> downloadAssist(String filePath, String fileName) throws IOException{
    	
        File file = new File(filePath);
        if (!file.isFile() || !file.exists()) {
            throw new IllegalArgumentException("filePath 参数必须是真实存在的文件路径:" + filePath);

        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        System.out.println(fileName);
        headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName, ENCODING));
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
    }

    
    /**
     * 下载文件，原文件名显示
     * @Title download 
     * @Description TODO
     * @param fileName
     * @param path
     * @param response
     * @return
     * @return HttpServletResponse
     */
    public static HttpServletResponse download(String fileName, String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" +  new String( fileName.getBytes("gb2312"), "ISO8859-1" )); 
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    } 
    
    
    
    
    /**
     * 下载文件
     * @Title download 
     * @Description TODO
     * @param fileName
     * @param path
     * @param response
     * @return
     * @return HttpServletResponse
     */
    public static HttpServletResponse download(String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String( filename.getBytes("gb2312"), "ISO8859-1" )); 
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    } 
    
    
    
    
    
    
    /**
     * 多文件上传
     * 
     * @param request
     *            当前上传的请求
     * @param basePath
     *            保存文件的路径
     * @throws IOException
     * @throws IllegalStateException
     * @return Map<String, String> 返回上传文件的保存路径 以文件名做map的key;文件保存路径作为map的value
     */
    public static Map<String, String> multiFileUpload(HttpServletRequest request, String basePath) throws IllegalStateException, IOException {
        if (!(new File(basePath).isDirectory())) {
            throw new IllegalArgumentException("basePath 参数必须是文件夹路径");
        }
        return multifileUploadAssist1(request, basePath, null);
    }
    
    /**
     * 不改变文件后缀
     * @param request
     * @param basePath
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    public static Map<String, String> multiFileUpload1(HttpServletRequest request, String basePath) throws IllegalStateException, IOException {
    	if (!(new File(basePath).isDirectory())) {
    		throw new IllegalArgumentException("basePath 参数必须是文件夹路径");
    	}
    	return multifileUploadAssist(request, basePath, null);
    }

    /**
     * 多文件上传
     * 
     * @param request
     *            当前上传的请求
     * @param basePath
     *            保存文件的路径
     * @param exclude
     *            排除文件名字符串,以逗号分隔的,默认无可传null
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    public static Map<String, String> multiFileUpload(HttpServletRequest request, String basePath, String exclude) throws IllegalStateException, IOException {
        if (!(new File(basePath).isDirectory())) {
            throw new IllegalArgumentException("basePath 参数必须是文件夹路径");
        }

        return multifileUploadAssist1(request, basePath, exclude);
    }
    
    
    /**
     * 多文件上传
     * 
     * @param request
     *            当前上传的请求
     * @param basePath
     *            保存文件的路径
     * @param exclude
     *            排除文件名字符串,以逗号分隔的,默认无可传null
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    public static Map<String, String> multiFileUpload_changename(HttpServletRequest request, String basePath) throws IllegalStateException, IOException {
        if (!(new File(basePath).isDirectory())) {
            throw new IllegalArgumentException("basePath 参数必须是文件夹路径");
        }

        return multifileUploadAssist1_changename(request, basePath);
    }
    
    
    
    
    
    
    /**
     * 多文件上传（返回全路径）
     * 
     * @param request
     *            当前上传的请求
     * @param basePath
     *            保存文件的路径
     * @param exclude
     *            排除文件名字符串,以逗号分隔的,默认无可传null
     * @return
     * @throws Exception 
     */
    public static Map<String, Map<String, String>> multiFileUpload2(HttpServletRequest request, String basePath,String compressPath, String exclude) throws Exception {
        if (!(new File(basePath).isDirectory())) {
            throw new IllegalArgumentException("basePath 参数必须是文件夹路径");
        }
        if (!(new File(compressPath).isDirectory())) {
            throw new IllegalArgumentException("compressPath 参数必须是文件夹路径");
        }

        return multifileUploadAssist2(request, basePath,compressPath, exclude);
    }

    
    
    /**
     * 多文件上传(处理文件名时间节点显示  数据库保留原始文件名)
     * 
     * @param request
     *            当前上传的请求
     * @param basePath
     *            保存文件的路径
     * @param exclude
     *            排除文件名字符串,以逗号分隔的,默认无可传null
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    public static Map<String, Map<String, String>> multiFileUpload2_changename(HttpServletRequest request, String basePath,String compressPath, String exclude) throws Exception {
        if (!(new File(basePath).isDirectory())) {
            throw new IllegalArgumentException("basePath 参数必须是文件夹路径");
        }
        if (!(new File(compressPath).isDirectory())) {
            throw new IllegalArgumentException("compressPath 参数必须是文件夹路径");
        }

        return multifileUploadAssist2_changename(request, basePath,compressPath, exclude);
    }
    
    
    
    
    /**
     * 多文件上传辅助
     * 
     * @param request
     *            当前上传的请求
     * @param basePath
     *            保存文件的路径
     * @param exclude
     *            排除文件名字符串,以逗号分隔的,默认无可传null
     * @return
     * @throws IOException
     */
    private static Map<String, String> multifileUploadAssist(HttpServletRequest request, String basePath, String exclude) throws IOException {
        exclude = exclude == null ? "" : exclude;

        Map<String, String> filePaths = new HashMap<String, String>();
        File file = null;
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        // 判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // get the parameter names of the MULTIPART files contained in this request
            Iterator<String> iter = multiRequest.getFileNames();
           
            while (iter.hasNext()) {
                // 取得上传文件
                List<MultipartFile> multipartFiles = multiRequest.getFiles(iter.next());
                for (MultipartFile multipartFile : multipartFiles) {
                    String fileName = multipartFile.getOriginalFilename();
                    if (StringUtils.isNotEmpty(fileName) && (!exclude.contains(fileName))) {
                    	
                        file = new File(basePath + changeFilenameTime(fileName));
                        filePaths.put(fileName, file.getPath());
                        multipartFile.transferTo(file);
                    }
                }
            }
        }
        return filePaths;
    }
    
    private static Map<String, String> multifileUploadAssist1(HttpServletRequest request, String basePath, String exclude) throws IOException {
    	exclude = exclude == null ? "" : exclude;
    	
    	Map<String, String> filePaths = new HashMap<String, String>();
    	File file = null;
    	// 创建一个通用的多部分解析器
    	CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
    	// 判断 request 是否有文件上传,即多部分请求
    	if (multipartResolver.isMultipart(request)) {
    		// 转换成多部分request
    		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
    		// get the parameter names of the MULTIPART files contained in this request
    		Iterator<String> iter = multiRequest.getFileNames();
    		
    		while (iter.hasNext()) {
    			// 取得上传文件
    			List<MultipartFile> multipartFiles = multiRequest.getFiles(iter.next());
    			for (MultipartFile multipartFile : multipartFiles) {
    				String fileName = multipartFile.getOriginalFilename();
    				if (StringUtils.isNotEmpty(fileName) && (!exclude.contains(fileName))) {
    					String fileNameToTime = changeFilenameTime(fileName);
    					file = new File(basePath + fileNameToTime);
    					filePaths.put(fileName, fileNameToTime);
    					multipartFile.transferTo(file);
    				}
    			}
    		}
    	}
    	return filePaths;
    }

    
    /**
     * 将文件改变名称   数据库保存原始文件名
     * @Title multifileUploadAssist1_changename 
     * @Description 
     * @param request
     * @param basePath
     * @param exclude
     * @return
     * @throws IOException
     * @return Map<String,String>
     * @throws IllegalStateException 
     */
    private  static Map<String, String> multifileUploadAssist1_changename(HttpServletRequest request, String basePath) throws IllegalStateException, IOException{
    	
    	Map<String, String> filePaths = new HashMap<String, String>();
    	// 创建一个通用的多部分解析器
    	CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
    	// 判断 request 是否有文件上传,即多部分请求
    	if (multipartResolver.isMultipart(request)) {
    		// 转换成多部分request
    		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
    		// get the parameter names of the MULTIPART files contained in this request
    		Iterator<String> iter = multiRequest.getFileNames();
    		
    		while (iter.hasNext()) {
    			// 取得上传文件
    			List<MultipartFile> multipartFiles = multiRequest.getFiles(iter.next());
    			for (MultipartFile multipartFile : multipartFiles) {
    				String fileName = multipartFile.getOriginalFilename();
    				if (StringUtils.isNotEmpty(fileName)) {
    					  String newName = "";
    					  long time = System.currentTimeMillis();
    			    	  Date date = new Date(time);    	
    			    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    			    	  
    			    	  /**
    			    	   * 获取5位随机数
    			    	   */
//    			    	  int n = (int)(Math.random()*90000 + 10000);   			    	  
//    					  newName = sdf.format(date) + n + '.' + FilenameUtils.getExtension(fileName);
    					
    			    	  /**
    			    	   * 使用UUID重命名
    			    	   */
    					  newName = changeFilenameUUID(fileName);
    					  
					    /*
					     * 进度报告   /100012/progress/
					     */	
    					  
    					File storefile = new File(basePath,newName); 					
    					filePaths.put(fileName, newName);
						multipartFile.transferTo(storefile);
						
    				}
    			}
    		}
    	}
    	return filePaths;
    }
    
    
    
    
    
    
    //存入系统压缩并返回文件路径
    private static Map<String,Map<String, String>> multifileUploadAssist2(HttpServletRequest request, String basePath,String compressPath ,String exclude) throws Exception {
    	exclude = exclude == null ? "" : exclude;
    	Map<String,Map<String, String>> result = new HashMap<String,Map<String, String>>();
    	Map<String, String> filePaths = new HashMap<String, String>();
    	Map<String, String> compressFilePaths = new HashMap<String, String>();
    	
    	File file = null;
    	// 创建一个通用的多部分解析器
    	CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
    	// 判断 request 是否有文件上传,即多部分请求
    	if (multipartResolver.isMultipart(request)) {
    		// 转换成多部分request
    		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
    		// get the parameter names of the MULTIPART files contained in this request
    		Iterator<String> iter = multiRequest.getFileNames();
    		
    		while (iter.hasNext()) {
    			// 取得上传文件
    			List<MultipartFile> multipartFiles = multiRequest.getFiles(iter.next());
    			for (MultipartFile multipartFile : multipartFiles) {
    				String fileName = multipartFile.getOriginalFilename();
    				if (StringUtils.isNotEmpty(fileName) && (!exclude.contains(fileName))) {
    					String fileNameToTime = changeFilenameTime(fileName);
    					String filePath = basePath + fileNameToTime;
    					String compressFilePath = compressPath + fileNameToTime;
    					
    					file = new File(filePath);
    					filePaths.put(fileName, filePath);
    				    compressFilePaths.put(fileName, compressFilePath);
    					multipartFile.transferTo(file);
    					ImageCompressUtil.saveMinPhoto(filePath,compressFilePath, 200, 0.9d);
    					
    					
    				}
    			}
    		}
    		result.put("filePaths", filePaths);
    		result.put("compressFilePaths", compressFilePaths);
    	}
    	return result;
    }
    
    
    
    
    
    
    //存入系统压缩并返回文件路径
    private static Map<String,Map<String, String>> multifileUploadAssist2_changename(HttpServletRequest request, String basePath,String compressPath ,String exclude) throws Exception {
    	exclude = exclude == null ? "" : exclude;
    	Map<String,Map<String, String>> result = new HashMap<String,Map<String, String>>();
    	Map<String, String> filePaths = new HashMap<String, String>();
    	Map<String, String> compressFilePaths = new HashMap<String, String>();
    	
    	File file = null;
    	// 创建一个通用的多部分解析器
    	CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
    	// 判断 request 是否有文件上传,即多部分请求
    	if (multipartResolver.isMultipart(request)) {
    		// 转换成多部分request
    		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
    		// get the parameter names of the MULTIPART files contained in this request
    		Iterator<String> iter = multiRequest.getFileNames();
    		
    		while (iter.hasNext()) {
    			// 取得上传文件
    			List<MultipartFile> multipartFiles = multiRequest.getFiles(iter.next());
    			for (MultipartFile multipartFile : multipartFiles) {
    				String fileName = multipartFile.getOriginalFilename();
    				if (StringUtils.isNotEmpty(fileName) && (!exclude.contains(fileName))) {
    					
    					
    					  String newName = "";
      					  long time = System.currentTimeMillis();
      			    	  Date date = new Date(time);    	
      			    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
      					  newName = sdf.format(date) + '.' + FilenameUtils.getExtension(fileName);
      					  int num = 1;
    					    /*
    					     * 进度报告   /100012/photo/
    					     */	
      					  
  					      File storefile = new File(basePath,newName);
  						 
  				          String sb3=null; 				         
						  for(int i=0;storefile.exists();i++){//如果存在同名的附件，则在后面添加数字区分
							 //获取文件名称后面的文件组后一个.的下标（后缀名）
							num++;
				            int index = newName.lastIndexOf(".");
				            //String sb = value.substring(0,index)+i;
				            sb3 = FilenameUtils.removeExtension(newName)+num;
				            newName = sb3+newName.substring(index);
							storefile = new File(basePath,newName);
//							 System.out.println(num);
						  }    
    					
    					
    					

    					String filePath = basePath + newName;
    					String compressFilePath = compressPath + changeFilenameZip(newName);
    					
    					file = new File(filePath);
    					filePaths.put(fileName, newName);
    					multipartFile.transferTo(file);
    					ImageCompressUtil.saveMinPhoto(filePath,compressFilePath, 400, 1d);    					
    					
    				}
    			}
    		}
    		result.put("filePaths", filePaths);
    	}
    	return result;
    }
    
    
    
    
    
    /**
     * 将文件名转变加时间节点命名的 ,保留文件后缀(时间：20161108103931)
     * 
     * @param filename
     * @return
     */
    public static String changeFilenameTime(String filename) {
    	long time = System.currentTimeMillis();
    	Date date = new Date(time);    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return FilenameUtils.removeExtension(filename)+"&"+sdf.format(date) + "." + FilenameUtils.getExtension(filename);
    }
    /**
     * 将文件名转变加时间节点命名的 ,保留文件后缀(时间：20161108103931)
     * 
     * @param filename
     * @return
     */
    public static String changeFilenameUUID(String filename) {
    	String random = UUID.randomUUID().toString().replaceAll("-", "");
    	return  random + "." + FilenameUtils.getExtension(filename);
    }

    
    /**
     * 将文件名转变为_compress,保留文件后缀(时间：123_compress.img)
     * 
     * @param filename
     * @return
     */
    public static String changeFilenameZip(String filename) {
    	return FilenameUtils.removeExtension(filename)+"_compress" + "." + FilenameUtils.getExtension(filename);
    }

    
    /**
     * 将文件名转变加时间节点命名的 ,保留文件后缀(时间：20161108103931)
     * 
     * @param filename
     * @return
     */
    public static String changeFilenameTime(Integer orderId,String filename) {
    	long time = System.currentTimeMillis();
    	Date date = new Date(time);    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return orderId+"-"+sdf.format(date) + "." + FilenameUtils.getExtension(filename);
    }
    
    
    /**
     * 删除文件
     * 
     * @param filePath
     */
    public static void deleteFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists() && file.isFile()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}