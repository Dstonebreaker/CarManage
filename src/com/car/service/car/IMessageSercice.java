package com.car.service.car;


import com.car.entity.car.TMessage;
import com.system.service.base.BaseServiceI;

public interface IMessageSercice extends BaseServiceI<TMessage>{
	
	public boolean addSend(TMessage message);
	public boolean addReceive(TMessage message); 
}
