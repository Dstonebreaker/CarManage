package com.car.service.export;

import com.car.entity.export.TCarCollect;
import com.system.service.base.BaseServiceI;

public interface ICarCollectService extends BaseServiceI<TCarCollect> {
	public int doRunCall(String startTime, String endTime, String orgId,String userId, String collectType,String queryType, String flag);

	public int doMonthRunCall(String startTime, String endTime, String orgId, String userId, String queryType, String flag);

	public int doDriverRunCall(String startTime, String endTime, String orgId,String userId, String queryType, String flag);
	public int doSituationRunCall(String startTime, String endTime, String orgId,String userId, String queryType, String flag);
}
