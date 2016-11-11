package com.car.service.export;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.entity.export.TCarCollectDeploy;
import com.framework.dao.BaseDaoI;
import com.system.service.base.BaseServiceImpl;
@Service
public class CarCollectDeployServiceImpl extends BaseServiceImpl<TCarCollectDeploy>implements ICarCollectDeployService {
	@Autowired
	private BaseDaoI<TCarCollectDeploy> collectDeployDao;

	@Override
	public int doRunCall(String orgId, String userId, String queryType,
			String flag) {
		String sql = "{ call p_car_collect_deploy('"+orgId+"','" + userId + "','"+queryType+"',"+flag+")}";
		return (collectDeployDao.executeSql(sql));
	}

	@Override
	public int doRunCalls(String orgId, String userId, String queryType,
			String flag) {
		String sql = "{  call p_car_collect_special('"+orgId+"','" + userId + "','"+queryType+"',"+flag+")}";
		return (collectDeployDao.executeSql(sql));
	}
}
