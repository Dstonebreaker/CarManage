package com.car.dao.accident;

import org.springframework.stereotype.Repository;

import com.car.entity.accident.TAccident;
import com.framework.dao.BaseDaoI;
import com.framework.dao.BaseDaoImpl;


@Repository
public class AccidentDaoImpl extends BaseDaoImpl<TAccident> implements BaseDaoI<TAccident> {
	
}
