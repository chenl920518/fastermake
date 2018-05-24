package com.cbt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cbt.entity.QuoteMessage;

public interface QuoteMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuoteMessage record);

    int insertSelective(QuoteMessage record);

    QuoteMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuoteMessage record);

    int updateByPrimaryKey(QuoteMessage record);
    
    
    
    
    /**
     * 根据供应商ID查询消息
     */
    List<QuoteMessage> queryMessageByFactoryId(@Param("factoryId")String factoryId,
    		@Param("orderId")Integer orderId);
    
    
    
    
    
}