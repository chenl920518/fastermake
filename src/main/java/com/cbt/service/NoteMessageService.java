package com.cbt.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cbt.entity.NoteMessage;


public interface NoteMessageService {
	
	
	/**
	 * 保存工业大众点评消息
	 * @param record
	 * @return
	 */
   public int insert(NoteMessage record);
	
   
   
   
   public int insertSelective(NoteMessage record);
   
   /*
    * 
    * 新建一个新会话
    */
   
   public int insertNewDialogue(NoteMessage record);
	
   
   
   
   
   
     /**
      * 根据ID查询
      * @param id
      * @return
      */
   public NoteMessage selectByPrimaryKey(Integer id);
   
   /*
    * 查询列表(管理员)
    */
   public List<NoteMessage> queryListAdmin(String factoryId,Integer start,Integer pageSize,boolean checkRead,boolean checkSend,String selectKey,Integer messageType);
   
   /*
    * 列表数(管理员)
    */
   
   public int totalOrderAdmin(String factoryId,boolean checkRead,boolean checkSend,String selectKey,Integer messageType);
  
   /*
    * 查询列表
    */
   public List<NoteMessage> queryList(String factoryId,Integer start,Integer pageSize,boolean checkRead,boolean checkSend,String selectKey,Integer messageType,Integer factoryUserId);
   
   /*
    * 列表数
    */
   
   public int totalOrder(String factoryId,boolean checkRead,boolean checkSend,String selectKey,Integer messageType,Integer factoryUserId);
   
   
   /*
    * 查询详情
    */
   public List<NoteMessage> queryDetail(Integer dialogueId);
   
   
   /*
    * 更新数据
    */
   public int updateMessageData(NoteMessage noteMessage);
   
   
   
   
   /**
    * 批量更新消息状态
    * @Title updateByPrimaryKey 
    * @Description 
    * @param records
    * @return
    * @return int
    */
   int updateBatch(List<?> records,int permission,String factoryId,Integer factoryUserId,int isRead);
}
