package com.car.service.car;

import com.car.entity.car.TCar;
import com.system.service.base.BaseServiceI;

public interface ICarservice extends BaseServiceI<TCar> {

	public void doRunCall();
	public void doRunCalls();

}
