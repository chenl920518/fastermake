package com.cbt.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbt.entity.FactoryInfo;
import com.cbt.entity.ProductLibrary;
import com.cbt.service.FactoryInfoService;
import com.cbt.service.ProductLibraryService;
import com.cbt.util.DateFormat;
import com.cbt.util.OperationFileUtil;
import com.cbt.util.UploadAndDownloadPathUtil;
import com.cbt.util.WebCookie;

@RequestMapping("/productManage")
@Controller
public class ProductManageController {

   private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductLibraryService productLibraryService;

	@Autowired
	private FactoryInfoService factoryInfoService;

	/**
	 * 查询登录用户产品列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryList.do")
	public JsonResult<Map<String, Object>> getList(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			Map<String, Object> map = null;
			String factoryId = WebCookie.getFactoryId(request);
			
			if (StringUtils.isBlank(factoryId)) {
//			    response.sendRedirect("/fastermake/login1.html");
				map= new HashMap<String, Object>();
				map.put("url", "/zh/login.html");
				return new JsonResult<Map<String, Object>>(1, "未获取到登录信息",map);
			} else {

				String process = request.getParameter("process");

				List<ProductLibrary> pllist = productLibraryService
						.queryProductGroupFactoryId(factoryId, process);
				int number = productLibraryService.selectCountByFactoryId(
						factoryId, process);
				FactoryInfo factoryInfo = factoryInfoService.selectFactoryInfo(factoryId);
				map= new HashMap<String, Object>();

				map.put("productList", pllist);
				map.put("number", number);
				map.put("factoryInfo", factoryInfo);

				return new JsonResult<Map<String, Object>>(0, "success", map);

			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Map<String, Object>>(1, "查询失败");
		}

	}
	
	
	
//
//	@RequestMapping("/productManageQuoteList.do")
//	public String details(HttpServletRequest request,
//			HttpServletResponse response) {
//
//
//		String factoryId = WebCookie.getFactoryId(request);
//
//		String url = request.getRequestURI();
//		 
//		 System.out.println(url);
//		if (StringUtils.isBlank(factoryId)) {
//
//			Cookie cookie = new Cookie("quoteDetailUrl", url);
//			cookie.setPath("/");
//			cookie.setMaxAge(60 * 60 * 24 * 365);
//			response.addCookie(cookie);
//			return "redirect:/zh/login.html";
//
//		} else {
//			
//			
//			  Cookie urlCookie = new Cookie("quoteDetailUrl","");
//			  urlCookie.setPath("/");
//			  urlCookie.setMaxAge(0);
//			  response.addCookie(urlCookie); 
//			
//			return "redirect:/zh/product_totalpage.html";
//
//		}
//
//	}

	
	

	/**
	 * 查询单个产品列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectOneProduct.do")
	public JsonResult<Object> getOneProduct(HttpServletRequest request,
			HttpServletResponse response) {

		ProductLibrary productLibrary = null;

		try {

			String factoryId = WebCookie.getFactoryId(request);
			if (StringUtils.isBlank(factoryId)) {
				response.sendRedirect("/login.html");
				return new JsonResult<Object>(1, "未获取到登录信息");
			} else {

				String picId = request.getParameter("picId");

				int id = Integer.parseInt(picId);

				productLibrary = productLibraryService.selectByPrimaryKey(id);

				if (productLibrary != null) {
					return new JsonResult<Object>(0, "success", productLibrary);
				} else {
					return new JsonResult<Object>(1, "查询失败");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<Object>(1, "查询失败");
		}

	}

	/**
	 * 查询产品详情
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectProductDetail.do")
	public JsonResult<Map<String, Object>> selectProductDetail(
			HttpServletRequest request) {

		ProductLibrary productLibrary = new ProductLibrary();

		FactoryInfo factoryInfo = new FactoryInfo();

		Map<String, Object> map = null;
		
	

		try {
			String buyerId = WebCookie.getFactoryId(request);
			
			String picId = request.getParameter("picId");
			
			int id = Integer.parseInt(picId);
			productLibrary = productLibraryService.selectByPrimaryKey(id);
			

			if (productLibrary != null && productLibrary.getFactoryId() != null) {
				factoryInfo = factoryInfoService
						.selectFactoryInfoAndEquipment(productLibrary
								.getFactoryId());
			}
			logger.info(factoryInfo.toString());
			
			map = new HashMap<String, Object>();
		
			map.put("productLibrary", productLibrary);
			map.put("factoryInfo", factoryInfo);
			map.put("buyerId", buyerId);
		

			return new JsonResult<Map<String, Object>>(0, "success", map);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("=============查询产品详情失败============");
			return new JsonResult<Map<String, Object>>(1, "查询失败");
		}

	}

	/**
	 * 更新产品
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/updateProduct.do")
	public JsonResult<String> updateProduct(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String factoryId = WebCookie.getFactoryId(request);
			String state = WebCookie.getStateName(request);
			String pageFactoryId = request.getParameter("factoryId");

			if (StringUtils.isBlank(factoryId)) {
//				response.sendRedirect("http://192.168.1.54:8099/fastermake/login.html");
				return new JsonResult<String>(1, "未获取到登录信息");

			} else if (!factoryId.equals(pageFactoryId)) {

				return new JsonResult<String>(1, "没有权限修改");

			} else {

				ProductLibrary pl = new ProductLibrary();
				Enumeration<String> en = request.getParameterNames();

				while (en.hasMoreElements()) {

					String name = en.nextElement();
					String value = request.getParameter(name);
					if (value != null && !value.equals("_empty")) {
						BeanUtils.setProperty(pl, name, value);
					}
				}

				String mainCategory = request.getParameter("mainCategory");
				String subCategory = request.getParameter("subCategory");
				// String pictureCover = request.getParameter("pictureCover");
				String pictureCover = "1";
				// 图片路径
				String filePath1 = request.getParameter("filePath1");
				String comprocessPath1 = request
						.getParameter("comprocessPath1");
				String filePath2 = request.getParameter("filePath2");
				String comprocessPath2 = request
						.getParameter("comprocessPath2");
				String filePath3 = request.getParameter("filePath3");
				String comprocessPath3 = request
						.getParameter("comprocessPath3");
				String filePath4 = request.getParameter("filePath4");
				String comprocessPath4 = request
						.getParameter("comprocessPath4");

				String drawingPathGroup = null;
				String compressDrawingPathGroup = null;
				String drawingPath = null;
				String compressDrawingPath = null;

				if (StringUtils.isNotBlank(filePath1)) {
					drawingPathGroup = filePath1;
					compressDrawingPathGroup = comprocessPath1;
				}
				if (StringUtils.isNotBlank(filePath2)) {
					drawingPathGroup += ";" + filePath2;
					compressDrawingPathGroup += ";" + comprocessPath2;
				}
				if (StringUtils.isNotBlank(filePath3)) {
					drawingPathGroup += ";" + filePath3;
					compressDrawingPathGroup += ";" + comprocessPath3;
				}
				
				if (StringUtils.isNotBlank(filePath4)) {
					drawingPathGroup += ";" + filePath4;
					compressDrawingPathGroup += ";" + comprocessPath4;
				}

				if (StringUtils.isNotBlank(pictureCover)) {
					switch (pictureCover) {
					case "1":
						drawingPath = filePath1;
						compressDrawingPath = comprocessPath1;

						break;
					case "2":
						drawingPath = filePath2;
						compressDrawingPath = comprocessPath2;

						break;

					case "3":
						drawingPath = filePath3;
						compressDrawingPath = comprocessPath3;

						break;

					}

				}

				String process = null;
				String mainProcess = null;
				if (StringUtils.isNotBlank(mainCategory)
						&& StringUtils.isNotBlank(subCategory)) {
					process = mainCategory + ";" + subCategory;
					mainProcess = mainCategory;
				} else {
					process = mainCategory;
					mainProcess = mainCategory;
				}
				pl.setMainProcess(mainProcess);
				pl.setProcess(process);
				pl.setDrawingPath(drawingPath);
				pl.setCompressDrawingPath(compressDrawingPath);
				pl.setDrawingPathGroup(drawingPathGroup);
				pl.setCompressDrawingPathGroup(compressDrawingPathGroup);
				if(state!=null){
					pl.setRegion(state);
				}

				productLibraryService.updateProductMessage(pl);
				response.sendRedirect("/zh/product_totalpage.html");
				return new JsonResult<String>(0, "保存成功");

			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<String>(1, "保存失败");
		}

	}

	/**
	 * 删除产品
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/deleteProduct.do")
	public JsonResult<String> deleteProduct(HttpServletRequest request,
			HttpServletResponse response) {

		String picId = request.getParameter("picId");

		if (picId != null) {

			int id = Integer.parseInt(picId);
			ProductLibrary productLibrary =  productLibraryService.selectByPrimaryKey(id);
			
			String[] compressDrawingPathGroup = productLibrary.getCompressDrawingPathGroup().split(";");
			String[] drawingPathGroup= productLibrary.getDrawingPathGroup().split(";");
			
			if(compressDrawingPathGroup!=null&&compressDrawingPathGroup.length>0){
				if(System.getProperty("os.name").startsWith("Windows")){
				for(int i=0;i<compressDrawingPathGroup.length;i++){
				     String path1 =  UploadAndDownloadPathUtil.getProductPic() + compressDrawingPathGroup[i].substring(UploadAndDownloadPathUtil.getProductPicStatic().length()).replaceAll("/", "\\\\");
				     String path2 =  UploadAndDownloadPathUtil.getProductPic() + drawingPathGroup[i].substring(UploadAndDownloadPathUtil.getProductPicStatic().length()).replaceAll("/", "\\\\");	 
				     OperationFileUtil.deleteFile(path1);
					 OperationFileUtil.deleteFile(path2);
				     
				}
				}else{
					
					for(int i=0;i<compressDrawingPathGroup.length;i++){
					     String path1 =  UploadAndDownloadPathUtil.getProductPic() + compressDrawingPathGroup[i].substring(UploadAndDownloadPathUtil.getProductPicStatic().length());
					     String path2 =  UploadAndDownloadPathUtil.getProductPic() + drawingPathGroup[i].substring(UploadAndDownloadPathUtil.getProductPicStatic().length());	 
					     OperationFileUtil.deleteFile(path1);
						 OperationFileUtil.deleteFile(path2);
					     
					}
					
					
				}
				
			}
			
			
			productLibraryService.deleteByPrimaryKey(id);
			return new JsonResult<String>(0, "删除成功");
		} else {
			return new JsonResult<String>(1, "没有数据可供删除");
		}
		
		
		

	}

	/**
	 * 新增产品
	 * 
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/createNewProduct.do")
	public JsonResult<String> createNewProduct(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String factoryId = WebCookie.getFactoryId(request);

			 if (StringUtils.isBlank(factoryId)) {
//			    response.sendRedirect("/login.html");
			 return new JsonResult<String>(1, "未获取到登录信息");
			
			 } else {
            String state = WebCookie.getStateName(request);
            
        	String pageFactoryId = request.getParameter("factoryId");
        	
        	String id = request.getParameter("id");
        	
        	
             if (pageFactoryId!=null&&!"".equals(pageFactoryId) &&!factoryId.equals(pageFactoryId)) {
				return new JsonResult<String>(1, "没有权限修改");
			} 
            
             
            
     
			
			ProductLibrary pl = new ProductLibrary();
			Enumeration<String> en = request.getParameterNames();

			while (en.hasMoreElements()) {

				String name = en.nextElement();
				String value = request.getParameter(name);
				if (value != null && !value.equals("_empty")) {
					BeanUtils.setProperty(pl, name, value);
				}
			}

			String mainCategory = request.getParameter("mainCategory");
			String subCategory = request.getParameter("subCategory");
			// String pictureCover = request.getParameter("pictureCover");
			String pictureCover = "1";
			// 图片路径
			String filePath1 = request.getParameter("filePath1");
			String comprocessPath1 = request.getParameter("comprocessPath1");
			String filePath2 = request.getParameter("filePath2");
			String comprocessPath2 = request.getParameter("comprocessPath2");
			String filePath3 = request.getParameter("filePath3");
			String comprocessPath3 = request.getParameter("comprocessPath3");
			String filePath4 = request.getParameter("filePath4");
			String comprocessPath4 = request.getParameter("comprocessPath4");

			String drawingPathGroup = null;
			String compressDrawingPathGroup = null;
			String drawingPath = null;
			String compressDrawingPath = null;

			if (StringUtils.isNotBlank(filePath1)) {
				drawingPathGroup = filePath1;
				compressDrawingPathGroup = comprocessPath1;
			}
			if (StringUtils.isNotBlank(filePath2)) {
				drawingPathGroup += ";" + filePath2;
				compressDrawingPathGroup += ";" + comprocessPath2;
			}
			if (StringUtils.isNotBlank(filePath3)) {
				drawingPathGroup += ";" + filePath3;
				compressDrawingPathGroup += ";" + comprocessPath3;
			}
			if (StringUtils.isNotBlank(filePath4)) {
				drawingPathGroup += ";" + filePath4;
				compressDrawingPathGroup += ";" + comprocessPath4;
			}

			if (StringUtils.isNotBlank(pictureCover)) {
				switch (pictureCover) {
				case "1":
					drawingPath = filePath1;
					compressDrawingPath = comprocessPath1;
					break;
				case "2":
					drawingPath = filePath2;
					compressDrawingPath = comprocessPath2;
					break;
				case "3":
					drawingPath = filePath3;
					compressDrawingPath = comprocessPath3;
					break;
				}

			}

			String process = null;
			String mainProcess = null;
			if (StringUtils.isNotBlank(mainCategory)
					&& StringUtils.isNotBlank(subCategory)) {
				process = mainCategory + ";" + subCategory;
				mainProcess = mainCategory;

			} else {
				process = mainCategory;
				mainProcess = mainCategory;
			}

			pl.setProcess(process);
			pl.setMainProcess(mainProcess);
			pl.setDrawingPath(drawingPath);
			pl.setCompressDrawingPath(compressDrawingPath);
			pl.setDrawingPathGroup(drawingPathGroup);
			pl.setCompressDrawingPathGroup(compressDrawingPathGroup);
			pl.setFactoryId(factoryId);
			pl.setCreateDate(DateFormat.format());
			if(state!=null){
				pl.setRegion(state);
			}
			
            if(StringUtils.isBlank(id)){
            	productLibraryService.insertNewProduct(pl);
            }else{
            	pl.setId(Integer.parseInt(id));
            	productLibraryService.updateProductMessage(pl);
            }
			
			
			
			response.sendRedirect("/zh/product_totalpage.html");
			return new JsonResult<String>(0, "保存成功");

			 }
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<String>(1, "保存失败");
		}

	}
	
	
	

}
