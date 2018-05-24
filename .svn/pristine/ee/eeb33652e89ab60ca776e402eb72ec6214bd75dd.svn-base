package com.cbt.listener;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

import com.cbt.entity.Progress;

@Component
public class FileUploadProgressListener implements ProgressListener { 
	private HttpSession session;  
    public FileUploadProgressListener() {  
    }  
    public FileUploadProgressListener(HttpSession _session) {  
        session=_session;  
        Progress ps = new Progress();  
        session.setAttribute("status", ps);  
    }  
    public void update(long pBytesRead, long pContentLength, int pItems) {  
    	Progress ps = (Progress) session.getAttribute("status");  
        ps.setpBytesRead(pBytesRead);  
        ps.setpContentLength(pContentLength);  
        ps.setpItems(pItems);  
        float tmp = (float)pBytesRead;  
        float result = tmp/pContentLength*100;  
        ps.setPercentage(result);
        //更新  
        session.setAttribute("status", ps);  
    }  
}