package com.cbt.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cbt.entity.Progress;



@Controller
@SessionAttributes("status")  
public class FileUploadController {

	
	
	@RequestMapping(value = "/progressStatus", method = RequestMethod.POST )
	@ResponseBody
	public Progress initCreateInfo(Map<String, Object> model) {
		Progress status = (Progress) model.get("status");
		if(status==null){
			return status;
		}
		return status;
	}
}
