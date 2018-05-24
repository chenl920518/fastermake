package com.cbt.service;

import java.util.List;

import com.cbt.entity.Equipment;

public interface EquipmentService {
    /**
     * 添加企业设备清单
     * @param equipment
     */
	public void addEquipment(Equipment equipment);
    /**
     * 查询企业设备清单
     * @param factoryId
     * @return
     */
	public List<Equipment> selectFactoryEquipment(String factoryId);
	/**
	 * 删除设备清单
	 * @param id
	 */
	public void delEquipment(Integer id);
	/**
	 * 编辑企业设备清单
	 * @param equipment
	 */
	public void updateEquipment(Equipment equipment);
	
	
	
	/**
	 * 根据设备名模糊匹配
	 * @param equipmentName
	 * @return
	 */
	public int selectByEquipmentName(String equipmentName,String factoryId);
	/**
	 * 根据主键ID进行查询
	 * @param id
	 * @return
	 */
	public Equipment selectEquipmentById(Integer id);
    
}
