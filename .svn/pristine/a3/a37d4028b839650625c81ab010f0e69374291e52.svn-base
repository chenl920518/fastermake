package com.cbt.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbt.dao.NoteMessageMapper;
import com.cbt.dao.QuoteInfoMapper;
import com.cbt.dao.QuoteWeeklyReportMapper;
import com.cbt.entity.DialogueIds;
import com.cbt.entity.NoteMessage;
import com.cbt.entity.QuoteInfo;
import com.cbt.entity.QuoteWeeklyReport;
import com.cbt.enums.FileTypeEnum;
import com.cbt.enums.MessageTypeEnum;
import com.cbt.service.QuoteWeeklyReportService;
import com.cbt.util.DateFormat;
import com.cbt.util.GetServerPathUtil;
import com.cbt.util.WebCookie;

@Service
public class QuoteWeeklyReportServiceImpl implements QuoteWeeklyReportService {
 
	@Autowired
	private QuoteWeeklyReportMapper quoteWeeklyReportMapper;
	
	@Autowired
	private QuoteInfoMapper quoteInfoMapper;
	
	@Autowired
	private NoteMessageMapper noteMessageMapper;
	
	@Override
	public List<QuoteWeeklyReport> queryByOrderId(Integer orderId) {
		return quoteWeeklyReportMapper.queryByOrderId(orderId);
	}

	@Override
	public QuoteWeeklyReport selectByPrimaryKey(Integer id) {
		return quoteWeeklyReportMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(QuoteWeeklyReport record) {
		return quoteWeeklyReportMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<QuoteWeeklyReport> queryByOrderIdAndDate(Integer orderId,
			String uploadDate) {
		return quoteWeeklyReportMapper.queryByOrderIdAndDate(orderId, uploadDate);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return quoteWeeklyReportMapper.deleteByPrimaryKey(id);
	}

	
	@Transactional
	@Override
	public int insertSelective(HttpServletRequest request,QuoteWeeklyReport record) {
		  QuoteInfo quoteInfo = quoteInfoMapper.queryByOrderId(record.getOrderId());
		  NoteMessage note = new NoteMessage();
		  note.setReceiverId(quoteInfo.getCustomerId());
		  note.setSendId(WebCookie.getFactoryId(request)); 
		  note.setOrderId(quoteInfo.getOrderId());
		  if(record.getFileType() == FileTypeEnum.MATERIAL_REPORT.getCode()){
			  note.setMessageTitle(record.getOrderId()+(quoteInfo.getCsgOrderId() == null ? "" : "("+quoteInfo.getCsgOrderId()+")")+"询盘有新的材质报告上传");
			  note.setMessageDetails(record.getOrderId()+"询盘有新的材质报告上传"); 
		  }else if(record.getFileType() == FileTypeEnum.PROGRESS_REPORT.getCode()){
			  note.setMessageTitle(record.getOrderId()+(quoteInfo.getCsgOrderId() == null ? "" : "("+quoteInfo.getCsgOrderId()+")")+"询盘有新的进度报告上传");
			  note.setMessageDetails(record.getOrderId()+"询盘有新的进度报告上传"); 
		  }else if(record.getFileType() == FileTypeEnum.QUANLITY_REPORT.getCode()){
			  note.setMessageTitle(record.getOrderId()+(quoteInfo.getCsgOrderId() == null ? "" : "("+quoteInfo.getCsgOrderId()+")")+"询盘有新的质量报告上传");
			  note.setMessageDetails(record.getOrderId()+"询盘有新的质量报告上传"); 
		  }

		  note.setMessageType(MessageTypeEnum.WEEKLY_REPORT.getCode());
		  note.setUrl(GetServerPathUtil.getServerPath()+"/zh/purchase_big_goods_report.html?factoryId="+WebCookie.getFactoryId(request)+"&orderId="+record.getOrderId());    
		  note.setCreateTime(DateFormat.format());
		  
		  DialogueIds did = new DialogueIds();
		  noteMessageMapper.returnDialogueId(did);
		  int dialogueId = did.getId();
		  note.setDialogueId(dialogueId);
		  noteMessageMapper.insertSelective(note);
		  
		return quoteWeeklyReportMapper.insertSelective(record);
	}

	
	@Transactional
	@Override
	public void insertPhotosBatch(HttpServletRequest request,List<QuoteWeeklyReport> reports) {
		 Integer orderId = null;
		  for (QuoteWeeklyReport quoteWeeklyReport : reports) {
		 	orderId = quoteWeeklyReport.getOrderId();
		 }
		  QuoteInfo quoteInfo = quoteInfoMapper.queryByOrderId(orderId);
	      NoteMessage note = new NoteMessage();
		  note.setReceiverId(quoteInfo.getCustomerId());
		  note.setSendId(WebCookie.getFactoryId(request));  
		  note.setMessageTitle(orderId+(quoteInfo.getCsgOrderId() == null ? "" : "("+quoteInfo.getCsgOrderId()+")")+"询盘有新的周报上传");
		  note.setMessageDetails(orderId+(quoteInfo.getCsgOrderId() == null ? "" : "("+quoteInfo.getCsgOrderId()+")")+"询盘有新的周报上传"); 
		  note.setOrderId(quoteInfo.getOrderId());
		  note.setMessageType(MessageTypeEnum.WEEKLY_REPORT.getCode());
		  note.setUrl(GetServerPathUtil.getServerPath()+"/zh/purchase_big_goods_report.html?factoryId="+WebCookie.getFactoryId(request)+"&orderId="+orderId);   
		  note.setCreateTime(DateFormat.format());
		  
		  DialogueIds did = new DialogueIds();
		  noteMessageMapper.returnDialogueId(did);
		  int dialogueId = did.getId();
		  note.setDialogueId(dialogueId);
		  noteMessageMapper.insertSelective(note);
		  
		quoteWeeklyReportMapper.insertPhotosBatch(reports);
		
	}

	@Override
	public List<QuoteWeeklyReport> queryByOrderIdAndSupplier(Integer orderId) {
		return quoteWeeklyReportMapper.queryByOrderIdAndSupplier(orderId);
	}

}
