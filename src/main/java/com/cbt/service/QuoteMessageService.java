package com.cbt.service;

import java.util.List;

import com.cbt.entity.NoteMessage;
import com.cbt.entity.QuoteMessage;

public interface QuoteMessageService {
	
	
	/**
	 * 保存询盘消息
	 * @param record
	 * @return
	 */
   public int insert(QuoteMessage record,NoteMessage note);
	
	 /**
     * 根据供应商ID查询消息
     */
   public List<QuoteMessage> queryMessageByFactoryId(String factoryId,Integer orderId);
   
     /**
      * 根据ID查询
      * @param id
      * @return
      */
   public QuoteMessage selectByPrimaryKey(Integer id);
}
