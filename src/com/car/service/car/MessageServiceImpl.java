package com.car.service.car;


import com.car.entity.car.TMessage;
import com.framework.dao.BaseDaoI;
import com.system.service.base.BaseServiceImpl;
import com.system.service.maintain.ISysSettingService;
import com.system.service.maintain.SettingManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl extends BaseServiceImpl<TMessage> implements IMessageSercice {
																			
	@Autowired
	private BaseDaoI<TMessage> messageDao;
	@Autowired
	private ISysSettingService sysSettingService;
	
	/**
	 * 添加要发送的短信到数据库
	 */
	@Override
	public boolean addSend(TMessage message) {
		// TODO Auto-generated method stub		
		try {		
		if(SettingManager.getInstance(sysSettingService).getValue("MessageBusiness")!=0){
			messageDao.save(message);	
			return true;
		  }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return false;
			
	
	}
/**
 * 添加收到的短信到数据库
 */
	@Override
	public boolean addReceive(TMessage message) {
		// TODO Auto-generated method stub		
		try {		
		if(SettingManager.getInstance(sysSettingService).getValue("MessageBusiness")!=0){
			messageDao.save(message);	
			return true;
		  }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return false;
	}
			
}
