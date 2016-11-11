package com.car.service.export;

import com.car.entity.export.TCarCollectDeployType;
import com.system.service.base.BaseServiceI;

public interface ICarCollectDeployTypeService  extends BaseServiceI<TCarCollectDeployType>{
	public int doRunCall(String orgId, String userId,  String queryType, String flag);
}
