package com.cbt.controller;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbt.entity.FactoryInfo;
import com.cbt.service.FactoryInfoService;
import com.cbt.service.QuoteInfoService;
import com.cbt.util.ImageCompressUtil;
import com.cbt.util.OperationFileUtil;
import com.cbt.util.UploadAndDownloadPathUtil;
import com.cbt.util.WebCookie;

@RequestMapping("/upload")
@Controller
public class UploadAttachmentController {
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	 @Autowired
	 private QuoteInfoService quoteInfoService;  
	 @Autowired
	 private FactoryInfoService factoryInfoService;  
	 
	 
 
 	/**
 	 * 上传文件返回文件名（加时间节点显示）
 	 * 上传消息附件
 	 * @param request
 	 * @param response
 	 * @return
 	 */
    @ResponseBody
 	@RequestMapping("/uploadAttachmentAndChangeName.do")
 	public JsonResult<String> uploadAttachmentAndChangeName(HttpServletRequest request,HttpServletResponse response){
 		
 	 try {
 		 Integer orderId = Integer.parseInt(request.getParameter("orderId"));
 		 String drawingName = request.getParameter("selectDrawingName");
 		 String path1 = UploadAndDownloadPathUtil.getQuoteFile() +  orderId + File.separator;
		 File file1 = new File(path1);
		 if  (!file1.exists()  && !file1 .isDirectory())      
		 {         
			 file1 .mkdir();     
		 }  
 		 String path = path1 + "message" + File.separator;
		 File file = new File(path);
		 if  (!file.exists()  && !file .isDirectory())      
		 {         
			 file .mkdir();     
		 }  	    	  
// 		Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload1(request, path);
// 		String fileName = "";
// 		if(!(multiFileUpload == null || multiFileUpload.size() == 0)){
// 			fileName = multiFileUpload.get(drawingName);
// 		} 
           
         //根据文件名获取上传文件的位置  数据库保存原始文件名称
 		Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload_changename(request, path);
 		String fileName = "";
 		if(!(multiFileUpload == null || multiFileUpload.size() == 0)){
 			fileName = multiFileUpload.get(drawingName);
 			fileName = path + fileName;
 		} 				
 		
 		return new JsonResult<String>(0,"success",fileName);	
	 	} catch (IllegalStateException e) {
	 		logger.error("=====upload uploadAttachmentAndChangeName======",e);
	 		return new JsonResult<String>(1,"failed");	
	 	} catch (IOException e) {
	 		logger.error("=====upload uploadAttachmentAndChangeName======",e);
	 		return new JsonResult<String>(1,"failed");	
	 	} 	 			
	 	}

    
    /**
 	 * 上传文件返回文件名（加时间节点   工厂报价附件）
 	 * @param request
 	 * @param response
 	 * @return
 	 */
    @ResponseBody
 	@RequestMapping("/attachmentAndChangeName.do")
 	public JsonResult<String> attachmentAndChangeName(HttpServletRequest request,HttpServletResponse response){
 		
 	 try {
 		 Integer orderId = Integer.parseInt(request.getParameter("orderId"));
 		 String drawingName = request.getParameter("selectDrawingName");
 		 String path1 = UploadAndDownloadPathUtil.getQuoteFile() +  orderId + File.separator;
		 File file1 = new File(path1);
		 if  (!file1.exists()  && !file1 .isDirectory())      
		 {         
			 file1 .mkdir();     
		 }  
 		 String path = path1 + "quote" + File.separator;
 		
		 File file = new File(path);
		 if  (!file.exists()  && !file .isDirectory())      
		 {         
			 file .mkdir();     
		 }  	    
	    //根据文件名获取上传文件的位置  数据库保存原始文件名称
 		Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload_changename(request, path);
 		String fileName = "";
 		if(!(multiFileUpload == null || multiFileUpload.size() == 0)){
 			fileName = multiFileUpload.get(drawingName);
 			fileName = path + fileName;
 		} 				
 		
 		return new JsonResult<String>(0,"success",fileName);	
	 	} catch (IllegalStateException e) {
	 		e.printStackTrace();
	 		return new JsonResult<String>(1,"failed");	
	 	} catch (IOException e) {
	 		e.printStackTrace();
	 		return new JsonResult<String>(1,"failed");	
	 	} 
	 			
	 	}

    /**
     * 图纸重新上传获取(用于采购商询盘图纸上传、修改)
     * @Title drawingAndChangeName 
     * @Description TODO
     * @param request
     * @param response
     * @return
     * @return JsonResult<String>
     */
    @ResponseBody
  	@RequestMapping("/drawingAndChangeName.do")
  	public JsonResult<String> drawingAndChangeName(HttpServletRequest request,HttpServletResponse response){
  		
  	 try {
  		 String orderId = request.getParameter("orderId");
  		 String drawingName = request.getParameter("selectDrawingName");
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
	    //根据文件名获取上传文件的位置  数据库保存原始文件名称
 		Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload_changename(request, path);
 		String fileName = "";
 		if(!(multiFileUpload == null || multiFileUpload.size() == 0)){
 			fileName = multiFileUpload.get(drawingName);
 			fileName = path + fileName;
 		} 				
  		
  		return new JsonResult<String>(0,"success",fileName);	
 	 	} catch (IllegalStateException e) {
 	 		e.printStackTrace();
 	 		return new JsonResult<String>(1,"failed");	
 	 	} catch (IOException e) {
 	 		e.printStackTrace();
 	 		return new JsonResult<String>(1,"failed");	
 	 	} 
 	 			
 	 	}
    
    
    
    
    
    
    
    
    

	/**
	 * 上传文件返回实际路径文件名 add by sang
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/uploadFileAndChangeName.do")
	public JsonResult<String> uploadFileAndChangeName(
			HttpServletRequest request, HttpServletResponse response) {

		try {

			String drawingName = request.getParameter("fileName");
			String path = UploadAndDownloadPathUtil.getQuoteProductPath()
					+ File.separator;
			File file = new File(path);
			if (!file.exists() && !file.isDirectory()) {
				file.mkdir();
			}
			Map<String, String> multiFileUpload = OperationFileUtil
					.multiFileUpload1(request, path);
			String fileName = "";
			if (multiFileUpload != null && multiFileUpload.size() != 0) {
				fileName = multiFileUpload.get(drawingName);
			}

			return new JsonResult<String>(0, "success", fileName);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return new JsonResult<String>(1, "failed");
		} catch (IOException e) {
			e.printStackTrace();
			return new JsonResult<String>(1, "failed");
		}

	}

	/**
	 * 上传单个产品图片返回文件名（加时间节点显示）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/uploadProductPicChangeName.do")
	public JsonResult<String> uploadProductPicAndChangeName(
			HttpServletRequest request, HttpServletResponse response) {

		String result = "";
		Map<String, String> fileNameMap = null;
		Map<String, String> compressNameMap = null;
		String fileName = "";
		String compressfileName = "";

		try {
			String factoryId = WebCookie.getFactoryId(request);
			String drawingName = request.getParameter("fileName");

			String path = UploadAndDownloadPathUtil.getProductPic()
					+ File.separator + factoryId + File.separator;
			String compressPath = path + "compressImg" + File.separator;

			File file = new File(path);
			File compressImg = new File(compressPath);
			if (!file.exists() && !file.isDirectory()) {
				file.mkdirs();
			}
			if (!compressImg.exists() && !compressImg.isDirectory()) {
				compressImg.mkdirs();
			}

			Map<String, Map<String, String>> multiFileUpload = OperationFileUtil
					.multiFileUpload2(request, path, compressPath, null);

			String compressName = "";
			if (!(multiFileUpload == null || multiFileUpload.size() == 0)) {
				fileNameMap = multiFileUpload.get("filePaths");
				compressNameMap = multiFileUpload.get("compressFilePaths");
			}
			
			
			boolean isWindow = System.getProperty("os.name").startsWith("Windows");
			if (fileNameMap != null&&isWindow) {
				
				fileName = (UploadAndDownloadPathUtil.getProductPicStatic() + fileNameMap
						.get(drawingName).substring(
								UploadAndDownloadPathUtil.getProductPic()
										.length())).replaceAll("\\\\", "/");

			}else{
				fileName = (UploadAndDownloadPathUtil.getProductPicStatic() + fileNameMap
						.get(drawingName).substring(
								UploadAndDownloadPathUtil.getProductPic()
										.length()));
			}
			if (compressNameMap != null&&isWindow) {
				compressfileName = (UploadAndDownloadPathUtil
						.getProductPicStatic() + compressNameMap.get(
						drawingName).substring(
						UploadAndDownloadPathUtil.getProductPic().length()))
						.replaceAll("\\\\", "/");
			}else{
				compressfileName = (UploadAndDownloadPathUtil
						.getProductPicStatic() + compressNameMap.get(
						drawingName).substring(
						UploadAndDownloadPathUtil.getProductPic().length()));
			}
			result = fileName + ";" + compressfileName;

			return new JsonResult<String>(0, "success", result);
		} catch (Exception e) {
			logger.error("=========error=========", e);
			return new JsonResult<String>(1, "failed");
		}

	}
	/**
	 * 更新企业Logo
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateFactoryLogo.do")
	public JsonResult<String> updateFactoryLogo(HttpServletRequest request, HttpServletResponse response){

		try {
			String factoryId = WebCookie.getFactoryId(request);
			if (StringUtils.isBlank(factoryId)) {
				response.sendRedirect("/zh/login.html");
				return new JsonResult<String>(1,"未获取到登录信息");
			}
			String factoryLogo = request.getParameter("factoryLogoFileName");
			String path = UploadAndDownloadPathUtil.getFactoryLogo()+factoryId+ File.separator;
			File file = new File(path);
			if (!file.exists() && !file.isDirectory()) {
				file.mkdir();
			}
			Map<String, String> multiFileUpload = OperationFileUtil
					.multiFileUpload1(request, path);
			String fileName = "";
			if (multiFileUpload != null && multiFileUpload.size() != 0) {
				fileName = multiFileUpload.get(factoryLogo);
			}
			boolean isWindow = System.getProperty("os.name").startsWith("Windows");
			if (fileName != null&&isWindow) {
				fileName = fileName.substring(UploadAndDownloadPathUtil.getFactoryLogo().length()+factoryId.length()+1).replaceAll("\\\\", "/");
			}else{
				fileName = fileName.substring(UploadAndDownloadPathUtil.getFactoryLogo().length()+factoryId.length()+1);
			}
			
			return new JsonResult<String>(0, "success", fileName);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return new JsonResult<String>(1, "failed");
		} catch (IOException e) {
			e.printStackTrace();
			return new JsonResult<String>(1, "failed");
		}
	}
	
	
	/**
	 * 上传多个工厂营业执照照片返回文件名（加时间节点显示）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/uploadFactoryLicense.do")
	public JsonResult<String> uploadProductPicAndChangeNames(HttpServletRequest request, HttpServletResponse response) {
		try {
			String factoryId = WebCookie.getFactoryId(request);
			if (StringUtils.isBlank(factoryId)) {
				response.sendRedirect("/zh/login.html");
				return new JsonResult<String>(1,"未获取到登录信息");
			}
			FactoryInfo factoryInfo = factoryInfoService.selectFactoryInfo(factoryId);
			String factoryLicense = request.getParameter("factoryLicenseFileName");
			String path = UploadAndDownloadPathUtil.getFactoryLicense()+factoryId+ File.separator;
			File file = new File(path);
			if (!file.exists() && !file.isDirectory()) {
				file.mkdir();
			}
			Map<String, String> multiFileUpload = OperationFileUtil
					.multiFileUpload1(request, path);
			String fileName = "";
			if (multiFileUpload != null && multiFileUpload.size() != 0) {
				fileName = multiFileUpload.get(factoryLicense);
			}
			boolean isWindow = System.getProperty("os.name").startsWith("Windows");
			if (fileName != null&&isWindow) {
				fileName = fileName.substring(UploadAndDownloadPathUtil.getFactoryLicense().length()+factoryId.length()+1).replaceAll("\\\\", "/");
			}else{
				fileName = fileName.substring(UploadAndDownloadPathUtil.getFactoryLicense().length()+factoryId.length()+1);
			}
			
			String factoryLicenses = null;
			if(StringUtils.isNotBlank(factoryInfo.getFactoryLicense())){
				factoryLicenses = factoryInfo.getFactoryLicense()+","+fileName;
			}else{
				factoryLicenses = fileName;
			}
			factoryInfo.setFactoryLicense(factoryLicenses);
			factoryInfoService.updateByPrimaryKeySelective(factoryInfo);
			
			
			return new JsonResult<String>(0, "success", fileName);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return new JsonResult<String>(1, "failed");
		} catch (IOException e) {
			e.printStackTrace();
			return new JsonResult<String>(1, "failed");
		}

	}
	
	/**
	 * 上传多个工厂厂门照片返回文件名（加时间节点显示）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/uploadFactoryGateFile.do")
	public JsonResult<String> uploadFactoryGateFile(HttpServletRequest request, HttpServletResponse response) {
		try {
			String factoryId = WebCookie.getFactoryId(request);
			if (StringUtils.isBlank(factoryId)) {
				response.sendRedirect("/zh/login.html");
				return new JsonResult<String>(1,"未获取到登录信息");
			}
			FactoryInfo factoryInfo = factoryInfoService.selectFactoryInfo(factoryId);
			String factoryGate = request.getParameter("factoryGateFileName");
			String path = UploadAndDownloadPathUtil.getFactoryGate()+factoryId+ File.separator;
			File file = new File(path);
			if (!file.exists() && !file.isDirectory()) {
				file.mkdir();
			}
			Map<String, String> multiFileUpload = OperationFileUtil
					.multiFileUpload1(request, path);
			String fileName = "";
			if (multiFileUpload != null && multiFileUpload.size() != 0) {
				fileName = multiFileUpload.get(factoryGate);
			}
			boolean isWindow = System.getProperty("os.name").startsWith("Windows");
			if (fileName != null&&isWindow) {
				fileName = fileName.substring(UploadAndDownloadPathUtil.getFactoryGate().length()+factoryId.length()+1).replaceAll("\\\\", "/");
			}else{
				fileName = fileName.substring(UploadAndDownloadPathUtil.getFactoryGate().length()+factoryId.length()+1);
			}
			
			String factoryGates = null;
			if(StringUtils.isNotBlank(factoryInfo.getFactoryGate())){
				factoryGates = factoryInfo.getFactoryGate()+","+fileName;
			}else{
				factoryGates = fileName;
			}
			factoryInfo.setFactoryGate(factoryGates);
			factoryInfoService.updateByPrimaryKeySelective(factoryInfo);
			return new JsonResult<String>(0, "success", fileName);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return new JsonResult<String>(1, "failed");
		} catch (IOException e) {
			e.printStackTrace();
			return new JsonResult<String>(1, "failed");
		}

	}
	
	
	/**
	 * 上传工厂生产环境照片返回文件名（加时间节点显示）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/uploadProductionEnvironmentFile.do")
	public JsonResult<Map<String, Object>> uploadProductionEnvironmentFile(HttpServletRequest request, HttpServletResponse response) {
		try {
			String factoryId = WebCookie.getFactoryId(request);
			if (StringUtils.isBlank(factoryId)) {
				response.sendRedirect("/zh/login.html");
				return new JsonResult<Map<String,Object>>(1,"未获取到登录信息");
			}
			FactoryInfo factoryInfo = factoryInfoService.selectFactoryInfo(factoryId);
			String path = UploadAndDownloadPathUtil.getProductionEnvironment()+ factoryId+File.separator;
			String compressPath = path + "compressImg" + File.separator;

			File file = new File(path);
			File compressImg = new File(compressPath);
			if (!file.exists() && !file.isDirectory()) {
				file.mkdirs();
			}
			if (!compressImg.exists() && !compressImg.isDirectory()) {
				compressImg.mkdir();
			}

			Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload1(request, path);
		    String picNames = "";
		    String fileNames="";
 			if(!(multiFileUpload == null || multiFileUpload.size() == 0)){
 				 Set<String> keySet = multiFileUpload.keySet();
 				   for (String key : keySet) {	
 					   String pic = multiFileUpload.get(key);  
 					   String photoCompressName = "";
 				        if(pic != null && !"".equals(pic)){
 				        	photoCompressName = OperationFileUtil.changeFilenameZip(pic);
 				        }		
 				        String photoPath = pic;
 	                    String photoPathCompress = photoCompressName;
 				        ImageCompressUtil.saveMinPhoto(photoPath, photoPathCompress, 215, 0.9d);
 				       picNames += pic + ",";
 				   }
 				   if(picNames.endsWith(",")){
 					  picNames = picNames.substring(0,picNames.length()-1);
 				   }	
 				  //截取出文件名
 				   if(StringUtils.isNotBlank(picNames)){
 					    String[] picNameArray=picNames.split(","); 
 		 				for (int i = 0; i < picNameArray.length; i++) {
 		 				   File tempFile =new File(picNameArray[i].trim());  
 		 			       String fileName = tempFile.getName();
 		 			       fileNames += fileName + ",";
 						}
 		           }
 				   if(fileNames.endsWith(",")){
 					  fileNames = fileNames.substring(0,fileNames.length()-1);
 				   }
 			}
 			String productionEnvironments = null;
			if(StringUtils.isNotBlank(factoryInfo.getProductionEnvironment())){
				productionEnvironments = factoryInfo.getProductionEnvironment()+","+fileNames;
			}else{
				productionEnvironments = fileNames;
			}
			
			//只取最新6张
			if(StringUtils.isNotBlank(productionEnvironments)){
				String[] split = productionEnvironments.split(",");
				if(split.length > 6){
					int index = productionEnvironments.indexOf(",", split.length-6);
					productionEnvironments = productionEnvironments.substring((index+1),productionEnvironments.length());
				}
			}
			factoryInfo.setProductionEnvironment(productionEnvironments);
			factoryInfoService.updateByPrimaryKeySelective(factoryInfo);
			
			
 			Map<String,Object> map=new HashMap<String,Object>();
 			map.put("fileNames", fileNames);
 			map.put("factoryId", factoryId);
 			return new JsonResult<Map<String, Object>>(map);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return new JsonResult<Map<String,Object>>(1,"failed");
		} catch (IOException e) {
			e.printStackTrace();
			return new JsonResult<Map<String,Object>>(1,"failed");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Map<String,Object>>(1,"failed");
		}

	}
	
	/**
	 * 上传工厂设备生产视频（加时间节点显示）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/uploadProductionVideo.do")
	public JsonResult<Map<String, Object>> uploadProductionVideo(HttpServletRequest request, HttpServletResponse response) {
		try {
			String factoryId = WebCookie.getFactoryId(request);
			if (StringUtils.isBlank(factoryId)) {
				response.sendRedirect("/zh/login.html");
				return new JsonResult<Map<String,Object>>(1,"未获取到登录信息");
			}
			String path = UploadAndDownloadPathUtil.getProductionVideo()+ factoryId+File.separator;
			String compressPath = path + "compressImg" + File.separator;
            String productionVideo=request.getParameter("productionVideo");
			File file = new File(path);
			File compressImg = new File(compressPath);
			if (!file.exists() && !file.isDirectory()) {
				file.mkdirs();
			}
			if (!compressImg.exists() && !compressImg.isDirectory()) {
				compressImg.mkdir();
			}

			Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload_changename(request, path);
			String fileName=multiFileUpload.get(productionVideo);
//		    File tempFile =new File(fileUrl);  
//	        String fileName = tempFile.getName();		      
	        Map<String,Object> map=new HashMap<String,Object>();
 			map.put("fileName", fileName);
 			map.put("factoryId", factoryId);
 			return new JsonResult<Map<String, Object>>(map);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return new JsonResult<Map<String,Object>>(1,"failed");
		} catch (IOException e) {
			e.printStackTrace();
			return new JsonResult<Map<String,Object>>(1,"failed");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Map<String,Object>>(1,"failed");
		}

	}
	
	/**
	 * 上传资质证明附件（加时间节点显示）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/uploadQualificationFile.do")
	public JsonResult<Map<String, Object>> uploadQualificationFile(HttpServletRequest request, HttpServletResponse response) {
		try {
			String factoryId = WebCookie.getFactoryId(request);
			if (StringUtils.isBlank(factoryId)) {
				response.sendRedirect("/zh/login.html");
				return new JsonResult<Map<String,Object>>(1,"未获取到登录信息");
			}
			String path = UploadAndDownloadPathUtil.getQualificationFile()+ factoryId+File.separator;
			String compressPath = path + "compressImg" + File.separator;
            String qualificationFile=request.getParameter("qualificationFile");
            String modifyQualificationFile=request.getParameter("modifyQualificationFile");
			File file = new File(path);
			File compressImg = new File(compressPath);
			if (!file.exists() && !file.isDirectory()) {
				file.mkdirs();
			}
			if (!compressImg.exists() && !compressImg.isDirectory()) {
				compressImg.mkdir();
			}

			Map<String, String> multiFileUpload = OperationFileUtil.multiFileUpload1(request, path);
			String fileUrl="";
			if(StringUtils.isNotBlank(qualificationFile)){//上传附件
				fileUrl=multiFileUpload.get(qualificationFile);
			}
			if(StringUtils.isNotBlank(modifyQualificationFile)){//修改附件
				fileUrl=multiFileUpload.get(modifyQualificationFile);
			}
		    File tempFile =new File(fileUrl);  
	        String fileName = tempFile.getName();		      
	        Map<String,Object> map=new HashMap<String,Object>();
 			map.put("fileName", fileName);
 			map.put("factoryId", factoryId);
 			return new JsonResult<Map<String, Object>>(map);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return new JsonResult<Map<String,Object>>(1,"failed");
		} catch (IOException e) {
			e.printStackTrace();
			return new JsonResult<Map<String,Object>>(1,"failed");
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Map<String,Object>>(1,"failed");
		}

	}

	@ResponseBody
	@RequestMapping("/deleteFile.do")
	public JsonResult<String> deleteFile(HttpServletRequest request,
			HttpServletResponse response) {

		String filePath = request.getParameter("filePath");

		OperationFileUtil.deleteFile(filePath);

		return new JsonResult<String>(0, "success");

	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/deletePic.do")
	public JsonResult<String> deletePic(HttpServletRequest request) {

		String url = request.getParameter("url");
		String compressUrl = request.getParameter("compressUrl");
		
		boolean isWindow = System.getProperty("os.name").startsWith("Windows");
		String filePath1 ="";
		String filePath2 ="";
		if(isWindow){
		 filePath1 = UploadAndDownloadPathUtil.getProductPic()+url.substring(UploadAndDownloadPathUtil.getProductPicStatic().length()).replaceAll("/", "\\\\");
		
		 filePath2 = UploadAndDownloadPathUtil.getProductPic()+compressUrl.substring(UploadAndDownloadPathUtil.getProductPicStatic().length()).replaceAll("/", "\\\\");
		}else{
			 filePath1 = UploadAndDownloadPathUtil.getProductPic()+url.substring(UploadAndDownloadPathUtil.getProductPicStatic().length());
				
			 filePath2 = UploadAndDownloadPathUtil.getProductPic()+compressUrl.substring(UploadAndDownloadPathUtil.getProductPicStatic().length());
			
		}
		OperationFileUtil.deleteFile(filePath1);
		OperationFileUtil.deleteFile(filePath2);
		return new JsonResult<String>(0, "success");

	}
	

	public static void main(String[] args) {
//		String temp = UploadAndDownloadPathUtil.getProductPic();
//
//		System.out.println(temp.substring(UploadAndDownloadPathUtil
//				.getProductPic().length())
//				+ UploadAndDownloadPathUtil.getProductPicStatic());
		
		String temp = "/productimg/f2017092614/419353274614435183&20171031183734.jpg";
//		
//		System.out.println(UploadAndDownloadPathUtil.getProductPic()+temp.substring(UploadAndDownloadPathUtil.getProductPicStatic().length()));
//		
//		System.out.println(temp.substring(UploadAndDownloadPathUtil.getProductPicStatic().length())
//				+ UploadAndDownloadPathUtil.getProductPic());
		
		
//		String path = UploadAndDownloadPathUtil.getProductPic()+"/sadasdjjh/sadasd/ff.jpg" ;
//				
//		String compressPath = path + "compressImg" ;
//		
//		System.out.println(path);
//		System.out.println(compressPath);		
//		
//		
//		String fileName = (UploadAndDownloadPathUtil.getProductPicStatic() + path.substring(
//						UploadAndDownloadPathUtil.getProductPic()
//								.length()));
//
//	  System.out.println(fileName);

	}
}
