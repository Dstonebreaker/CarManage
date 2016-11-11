package com.car.service.export;

import com.car.entity.export.TCarCollectDeploy;
import com.system.service.base.BaseServiceI;

public interface ICarCollectDeployService extends BaseServiceI<TCarCollectDeploy>{
	//IN `orgId` varchar(36),IN `userId` varchar(36),IN `queryType` int,OUT `flag` int
	public int doRunCall(String orgId, String userId,  String queryType, String flag);
	public int doRunCalls(String orgId, String userId,  String queryType, String flag);
}
