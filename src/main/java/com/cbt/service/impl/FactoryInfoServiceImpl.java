package com.cbt.service.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cbt.dao.FactoryInfoMapper;
import com.cbt.dao.FactoryUserMapper;
import com.cbt.entity.FactoryInfo;
import com.cbt.entity.FactoryUser;
import com.cbt.exception.NameOrPasswordException;
import com.cbt.service.FactoryInfoService;
import com.cbt.util.DateFormat;

/**
 * Edit 修改查询有意向报价工厂（去除当前登录公司）
 * @ClassName FactoryInfoService 
 * @Description
 * @author polo
 * @date 2018年4月8日 下午1:22:21
 */

@Service
public class FactoryInfoServiceImpl implements FactoryInfoService {
	
	@Autowired
	private FactoryInfoMapper factoryInfoMapper;
	@Autowired
	private FactoryUserMapper factoryUserMapper;
	
	private static final Integer PERMISSION = 1;       //初始注册权限为管理员
	
	@Override
	public FactoryInfo login(String loginEmail, String pwd)throws NameOrPasswordException {
		//入口参数检查
		if(loginEmail==null || loginEmail.trim().isEmpty()){
			throw new NameOrPasswordException("邮箱不能为空");
		}
		if(pwd==null || pwd.trim().isEmpty()){
			throw new NameOrPasswordException("密码不能为空");
		}
		//从业务层查询用户信息
		FactoryUser factoryUser = factoryUserMapper.selectByLoginEmail(loginEmail);
		FactoryInfo factoryInfo = new FactoryInfo();
		if(factoryUser != null){
			factoryInfo = factoryInfoMapper.selectFactoryInfo(factoryUser.getFactoryId());
		}		
		if(factoryUser==null){
			throw new NameOrPasswordException("邮箱未注册，请先注册");
		}else if(factoryInfo.getIsVip() == 105){
			throw new NameOrPasswordException("账号被屏蔽，请与管理员联系");
		}
		if(factoryUser.getPwd().equals(pwd)){
			return factoryInfo;                    //登录成功
		}else{
			throw new NameOrPasswordException("账号或者密码错误");
		}

	}

	@Override
	public int selectMaxId() {	
		return factoryInfoMapper.selectMaxId();
	}


	
	@Transactional
	@Override
	public FactoryUser insertSelective(FactoryInfo record) throws ParseException {
		FactoryUser user = factoryUserMapper.selectByLoginEmail(record.getEmail());		
		if(user != null){
			throw new RuntimeException("账号已存在");
		}
		if(record != null){
			record.setCreateTime(DateFormat.format());
			int flag = factoryInfoMapper.insertSelective(record);
			String factoryId = null;
			if(flag == 1){
			 String currentDay = DateFormat.currentDate().replace("-", "");	
			 factoryId = "f" + currentDay + record.getId();
			 record.setFactoryId(factoryId);
			 factoryInfoMapper.updateByPrimaryKeyWithBLOBs(record);
			}
			FactoryUser factoryUser = new FactoryUser();
			factoryUser.setEmail(record.getEmail());
			factoryUser.setFactoryId(factoryId);
			factoryUser.setPermission(PERMISSION);
			factoryUser.setPwd(record.getPwd());
			factoryUser.setUsername(record.getUsername());
			factoryUserMapper.insert(factoryUser);
			return factoryUser;
		}else{
			throw new RuntimeException("注册失败");
		}		
	}


	@Override
	public int updateByPrimaryKeySelective(FactoryInfo record) {
		return factoryInfoMapper.updateByPrimaryKeySelective(record);
	}


	@Override
	public FactoryInfo selectByLoginEmail(String loginEmail) {
		return factoryInfoMapper.selectByLoginEmail(loginEmail);
	}
    /**
     * 查询工厂供应商基本信息
     */
	@Override
	public FactoryInfo selectFactoryInfo(String factoryId) {
		return factoryInfoMapper.selectFactoryInfo(factoryId);
	}

	@Override
	public FactoryInfo selectFactoryInfoAndEquipment(String factoryId) {
		// TODO Auto-generated method stub
		return factoryInfoMapper.selectFactoryInfoAndEquipment(factoryId);
	}

	@Override
	public int insertFactoryPreference(String factoryId, String buyerId,String factoryName,
			String createDate) {
	
		return factoryInfoMapper.insertFactoryPreference(factoryId, buyerId,factoryName ,createDate);
	}


	@Override
	public int deleteFactoryPreference(String factoryId, String buyerId) {
	
		return factoryInfoMapper.deleteFactoryPreference(factoryId, buyerId);
	}

	@Override
	public int selectFactoryPreference(String factoryId, String buyerId) {

		return factoryInfoMapper.selectFactoryPreference(factoryId, buyerId);
	}

	@Override
	public List<FactoryInfo> queryFactoryList() {
		return factoryInfoMapper.queryFactoryList();
	}

	@Override
	public List selectFactoryListByBuyerId(String buyerId) {
	
		return factoryInfoMapper.selectFactoryListByBuyerId(buyerId);
	}

	@Override
	public List<FactoryInfo> selectByCondition(Map map) {
		
		return factoryInfoMapper.selectByCondition(map);
	}

	@Override
	public int totalOrder(Map map) {
		return factoryInfoMapper.totalOrder(map);
	}
    /**
     * 删除工厂运行视频
     */
	@Override
	public void delProductionVideo(FactoryInfo factoryInfo) {
		factoryInfoMapper.delProductionVideo(factoryInfo);
	}

	@Override
	public FactoryInfo selectByPrimaryKey(Integer id) {
		return factoryInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<FactoryInfo> queryByOrderIdAndQuoteMessage(Integer orderId,String factoryId) {
		return factoryInfoMapper.queryByOrderIdAndQuoteMessage(orderId,factoryId);
	}

	@Override
	public List<Map<String, Object>> queryByMainProcess(String process) {
		return factoryInfoMapper.queryByMainProcess(process);
	}

	@Transactional
	@Override
	public int updateByPrimaryKeySelective(FactoryInfo record,
			FactoryUser factoryUser) {
		factoryInfoMapper.updateByPrimaryKeySelective(record);
		int state = factoryUserMapper.updateByPrimaryKeySelective(factoryUser);
		return state;
	}
		
}
