package com.car.service.car;

import com.car.entity.car.TCarSend;
import com.system.service.base.BaseServiceI;

public interface ICarSendService extends BaseServiceI<TCarSend> {

	/**
	 * 派车
	 * 
	 * @param send
	 * @return
	 */
	public void doSend(TCarSend send);
	
	public void saveSend(TCarSend send);

}
