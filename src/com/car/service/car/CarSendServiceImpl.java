package com.car.service.car;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.entity.car.TCarApplySendStatus;
import com.car.entity.car.TCarSend;
import com.framework.dao.BaseDaoI;
import com.framework.util.WebMsgUtil;
import com.system.service.base.BaseServiceImpl;

@Service
public class CarSendServiceImpl extends BaseServiceImpl<TCarSend> implements ICarSendService {

	@Autowired
	private BaseDaoI<TCarSend> sendDao;

	@Autowired
	private BaseDaoI<TCarApplySendStatus> sendStatusDao;

	@Override
	public void doSend(TCarSend send) {
		//sendDao.save(send);

		String hql = "update TCarStatus set dictIdCarStatus=:carStatus where carId=:carId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("carStatus", WebMsgUtil.CARSTATUS_OUT);
		params.put("carId", send.getCarId());
		sendDao.executeHql(hql, params);

		hql = "update TCarApplySendStatus set dictIdApplySendStatus=:applySendStatus,sendId=:sendId where applyId=:applyId";
		params = new HashMap<String, Object>();
		params.put("applySendStatus", WebMsgUtil.APPLYSENDSTATUS_DHC);
		params.put("applyId", send.getApplyId());
		params.put("sendId", send.getSendId());
		int res = sendDao.executeHql(hql, params);
		if (res <= 0) {
			// 紧急派车改状态
			hql = "update TCarApplySendStatus set dictIdApplySendStatus=:applySendStatus where sendId=:sendId";
			params = new HashMap<String, Object>();
			params.put("applySendStatus", WebMsgUtil.APPLYSENDSTATUS_DHC);
			params.put("sendId", send.getSendId());
			sendDao.executeHql(hql, params);
		}
	}

	@Override
	public void saveSend(TCarSend send) {
		sendDao.saveOrUpdate(send);
		
		String hql = "update TCarApplySendStatus set dictIdApplySendStatus=:applySendStatus,sendId=:sendId where applyId=:applyId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("applySendStatus", WebMsgUtil.APPLYSENDSTATUS_YSCPCD);
		params.put("applyId", send.getApplyId());
		params.put("sendId", send.getSendId());
		int res = sendDao.executeHql(hql, params);
		if (res <= 0) {
			// 紧急派车改状态
			TCarApplySendStatus sendStatus = new TCarApplySendStatus();
			sendStatus.setDictIdApplySendStatus(WebMsgUtil.APPLYSENDSTATUS_YSCPCD);
			sendStatus.setSendId(send.getSendId());
			sendStatus.setId(UUID.randomUUID().toString());
			sendStatusDao.save(sendStatus);
		}
	}

}
