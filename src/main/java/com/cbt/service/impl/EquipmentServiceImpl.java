package com.cbt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbt.dao.EquipmentMapper;
import com.cbt.entity.Equipment;
import com.cbt.service.EquipmentService;

@Service
public class EquipmentServiceImpl implements EquipmentService {

	@Autowired
	private EquipmentMapper equipmentMapper;
    /**
     * 添加设备清单
     */
	@Override
	public void addEquipment(Equipment equipment) {
		equipmentMapper.insertSelective(equipment);
	}
    /**
     * 查询工厂的设备清单
     */
	@Override
	public List<Equipment> selectFactoryEquipment(String factoryId) {
		return equipmentMapper.selectFactoryEquipment(factoryId);
	}
	/**
	 * 删除设备清单
	 */
	@Override
	public void delEquipment(Integer id) {
		equipmentMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 编辑工厂设备清单
	 */
	@Override
	public void updateEquipment(Equipment equipment) {
		equipmentMapper.updateByPrimaryKeySelective(equipment);
	}
	@Override
	public int selectByEquipmentName(String equipmentName,String factoryId) {
		return equipmentMapper.selectByEquipmentName(equipmentName,factoryId);
	}
	@Override
	public Equipment selectEquipmentById(Integer id) {
		return equipmentMapper.selectByPrimaryKey(id);
	}
}
