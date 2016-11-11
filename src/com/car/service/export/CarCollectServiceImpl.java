package com.car.service.export;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.entity.export.TCarCollect;
import com.framework.dao.BaseDaoI;
import com.system.service.base.BaseServiceImpl;

@Service
public class CarCollectServiceImpl extends BaseServiceImpl<TCarCollect>
		implements ICarCollectService {
	@Autowired
	private BaseDaoI<TCarCollect> collectDao;

	public int doRunCall(String startTime, String endTime, String orgId,String userId, String collectType,String queryType, String flag) {		
		String sql = "{call p_car_collect('" + startTime + "','" + endTime	+ "','"+orgId+"','" + userId + "','"+collectType+"','"+queryType+"',"+flag+")}";
		return (collectDao.executeSql(sql));

	}

	@Override
	public int doMonthRunCall(String startTime, String endTime, String orgId,String userId, String queryType, String flag) {
		String sql = "{call p_car_collect_month('" + startTime + "','" + endTime	+ "','"+orgId+"','" + userId + "','"+queryType+"',"+flag+")}";
		return (collectDao.executeSql(sql));
	}

	@Override
	public int doDriverRunCall(String startTime, String endTime, String orgId,String userId, String queryType, String flag) {
		String sql = "{call p_car_drivercollect('" + startTime + "','" + endTime	+ "','"+orgId+"','" + userId + "','"+queryType+"',"+flag+")}";
		return (collectDao.executeSql(sql));
	}

	@Override
	public int doSituationRunCall(String startTime, String endTime,	String orgId, String userId, String queryType, String flag) {
		String sql = "{call p_car_collect_use('" + startTime + "','" + endTime	+ "','"+orgId+"','" + userId + "','"+queryType+"',"+flag+")}";
		return (collectDao.executeSql(sql));
	}
}
