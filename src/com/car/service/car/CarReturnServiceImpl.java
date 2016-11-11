/**
 * 
 */
package com.car.service.car;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.entity.car.TCarReturn;
import com.car.entity.car.TCarSend;
import com.framework.dao.BaseDaoI;
import com.framework.util.WebMsgUtil;
import com.system.service.base.BaseServiceImpl;

/**
 * @author Marlon
 * 
 */
@Service
public class CarReturnServiceImpl extends BaseServiceImpl<TCarReturn> implements ICarReturnService {

	@Autowired
	private BaseDaoI<TCarReturn> returnDao;
	
	@Autowired
	private BaseDaoI<TCarSend> sendDao;

	@Override
	public void doReturn(TCarReturn cr) {
		returnDao.save(cr);
		
		String hql = "update TCarApplySendStatus set dictIdApplySendStatus=:applySendStatus,returnId=:returnId where sendId=:sendId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("applySendStatus", WebMsgUtil.APPLYSENDSTATUS_YHC);
		params.put("returnId", cr.getReturnId());
		params.put("sendId", cr.getSendId());
		returnDao.executeHql(hql, params);
		
		TCarSend s = sendDao.getById(TCarSend.class, cr.getSendId());
		hql = "update TCarStatus set dictIdCarStatus=:carStatus where carId=:carId";
		params = new HashMap<String, Object>();
		params.put("carStatus", WebMsgUtil.CARSTATUS_FREE);
		params.put("carId", s.getCarId());
		sendDao.executeHql(hql, params);
	}

}
