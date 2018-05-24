package com.cbt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cbt.entity.DialogueIds;
import com.cbt.entity.NoteMessage;

public interface NoteMessageMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(NoteMessage record);

    int insertSelective(NoteMessage record);

    NoteMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NoteMessage record);

    int updateByPrimaryKeyWithBLOBs(NoteMessage record);

    int updateByPrimaryKey(NoteMessage record);
    
    int  returnDialogueId(DialogueIds dialogueId);
    
    List<NoteMessage> queryListAdmin(@Param("factoryId")String factoryId,@Param("start")Integer start,@Param("pageSize")Integer pageSize,
    		
    		@Param("checkRead")boolean checkRead,@Param("checkSend")boolean checkSend,@Param("selectKey")String selectKey,@Param("messageType")Integer messageType);
    
    int totalOrderAdmin(@Param("factoryId")String factoryId,@Param("checkRead")boolean checkRead,@Param("checkSend")boolean checkSend,@Param("selectKey")String selectKey,@Param("messageType")Integer messageType);
    
    List<NoteMessage> selectByDialogueId(@Param("dialogueId")Integer dialogueId);
    
    List<NoteMessage> queryList(@Param("factoryId")String factoryId,@Param("start")Integer start,@Param("pageSize")Integer pageSize,
    		
    		@Param("checkRead")boolean checkRead,@Param("checkSend")boolean checkSend,@Param("selectKey")String selectKey,@Param("messageType")Integer messageType,@Param("factoryUserId")Integer factoryUserId);
    
    int totalOrder(@Param("factoryId")String factoryId,@Param("checkRead")boolean checkRead,@Param("checkSend")boolean checkSend,@Param("selectKey")String selectKey,@Param("messageType")Integer messageType,@Param("factoryUserId")Integer factoryUserId);
  
    
    /**
     * 批量更新消息状态
     * @Title updateByPrimaryKey 
     * @Description 
     * @param records
     * @return
     * @return int
     */
    int updateBatch(@Param("list")List<?> records,@Param("isRead")int isRead);
    
    
    /**
     * 查询所有消息ids
     * @Title queryIdsByFactoryId 
     * @Description 
     * @param factoryId
     * @param messageType
     * @param factoryUserId
     * @return
     * @return List<?>
     */
    List<?> queryIdsByFactoryId(@Param("factoryId")String factoryId,@Param("factoryUserId")Integer factoryUserId);
  
    /**
     * 查询所有消息ids(管理员查询)
     * @Title queryIdsByFactoryId 
     * @Description 
     * @param factoryId
     * @param messageType
     * @param factoryUserId
     * @return
     * @return List<?>
     */
    List<?> queryIdsByFactoryIdAdmin(@Param("factoryId")String factoryId);
}

