package com.car.service.export;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.entity.export.TCarCollectDeployType;
import com.framework.dao.BaseDaoI;
import com.system.service.base.BaseServiceImpl;
@Service
public class CarCollDeployTypeServiceImpl extends BaseServiceImpl<TCarCollectDeployType>implements ICarCollectDeployTypeService{
	@Autowired
	private BaseDaoI<TCarCollectDeployType> collectDeployTypeDao;
	@Override
	public int doRunCall(String orgId, String userId, String queryType,
			String flag) {
		String sql = "{ call p_org_collect_deploy('"+orgId+"','" + userId + "','"+queryType+"',"+flag+")}";
		return (collectDeployTypeDao.executeSql(sql));
	}

}
