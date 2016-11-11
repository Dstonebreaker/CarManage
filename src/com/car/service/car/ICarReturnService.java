package com.car.service.car;

import com.car.entity.car.TCarReturn;
import com.system.service.base.BaseServiceI;

public interface ICarReturnService extends BaseServiceI<TCarReturn> {
	
	public void doReturn(TCarReturn cr);

}
