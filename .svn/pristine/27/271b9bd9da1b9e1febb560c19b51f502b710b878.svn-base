package com.cbt.controller;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cbt.entity.ReadExcelVO;
import com.cbt.util.ReadExcelUtils;



@RequestMapping("/excel")
@Controller
public class AnalysisQuoteFileController {

	
	
	  /**
	   * 解析报价表(塑料件)
	   * @Title analysisPlastic 
	   * @Description
	   * @param request
	   * @param response
	   * @return
	   * @return String
	   */
	  @ResponseBody
	  @RequestMapping(value = "/plastic")  
	  public JsonResult<List<ReadExcelVO>> analysisPlastic(String path) {  
	    
		  try {
			  ReadExcelUtils excelReader = new ReadExcelUtils(path);
			  //读取excel（产品单价、模具价格）
			  List<ReadExcelVO> excelVO = excelReader.readExcelPlasticContent();		
			  return new JsonResult<List<ReadExcelVO>>(excelVO);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult<List<ReadExcelVO>>(1,"解析失败");
		}
		  
		  
	  }  

}
