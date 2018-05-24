package com.cbt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cbt.entity.QuoteWeeklyReport;

public interface QuoteWeeklyReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuoteWeeklyReport record);

    int insertSelective(QuoteWeeklyReport record);

    QuoteWeeklyReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuoteWeeklyReport record);

    int updateByPrimaryKey(QuoteWeeklyReport record);
    
    
    /**
     * 查询图片类型  根据日期显示
     * @Title queryByOrderId 
     * @Description TODO
     * @return
     * @return List<QuoteWeeklyReport>
     */
    List<QuoteWeeklyReport> queryByOrderId(@Param("orderId")Integer orderId);
    
    
    /**
     * 供应商查询所有图片 文档 
     * @Title queryByOrderIdAndSupplier 
     * @Description TODO
     * @param orderId
     * @return
     * @return List<QuoteWeeklyReport>
     */
    List<QuoteWeeklyReport> queryByOrderIdAndSupplier(@Param("orderId")Integer orderId);
    
    
    /**
     * 根据类型查询
     * @Title queryByOrderIdAndType 
     * @Description TODO
     * @param orderId
     * @param type
     * @return
     * @return List<QuoteWeeklyReport>
     */
    List<QuoteWeeklyReport> queryByOrderIdAndType(@Param("orderId")Integer orderId,@Param("type")Integer type);
    
    
    
    
    
    
    /**
     * 根据询盘号和上传时间查询图片
     * @Title queryByOrderIdAndDate 
     * @Description TODO
     * @param orderId
     * @param uploadDate
     * @return
     * @return List<QuoteWeeklyReport>
     */
    List<QuoteWeeklyReport> queryByOrderIdAndDate(@Param("orderId")Integer orderId,@Param("uploadDate")String uploadDate);
    
    
    
    /**
     * 批量插入周报图片
     * @Title insertPhotosBatch 
     * @Description TODO
     * @param reports
     * @return void
     */
    void insertPhotosBatch(List<QuoteWeeklyReport> reports);
    
}