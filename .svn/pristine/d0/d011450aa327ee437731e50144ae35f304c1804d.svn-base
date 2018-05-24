package com.cbt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cbt.entity.Equipment;

/**
 * 供应商关键设备清单
 * @author chenlun
 *
 */
public interface EquipmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Equipment record);

    int insertSelective(Equipment record);

    Equipment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Equipment record);

    int updateByPrimaryKey(Equipment record);
    /**
     * 查询工厂设备清单
     * @param factoryId
     * @return
     */
	List<Equipment> selectFactoryEquipment(String factoryId);
	
	
	/**
	 * 根据设备名模糊匹配
	 * @param equipmentName
	 * @return
	 */
	int selectByEquipmentName(@Param("equipmentName")String equipmentName,@Param("factoryId")String factoryId);
    
}