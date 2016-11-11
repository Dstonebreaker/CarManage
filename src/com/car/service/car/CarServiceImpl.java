package com.car.service.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.entity.car.TCar;
import com.car.entity.car.TCarApply;
import com.framework.dao.BaseDaoI;
import com.system.service.base.BaseServiceImpl;
@Service
public class CarServiceImpl extends BaseServiceImpl<TCar> implements ICarservice {

	@Autowired
	private BaseDaoI<TCar> carDao;
	@Override
	public void doRunCall(){
		
		 String sql = "{call p_sys_organization_ext()}";
		 carDao.executeSql(sql);
		 
	}
	public void doRunCalls() {
		String  sql = "{call p_sys_organization()}";
		 carDao.executeSql(sql);
	}

}
