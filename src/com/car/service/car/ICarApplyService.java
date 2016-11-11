package com.car.service.car;

import java.io.Serializable;

import com.car.entity.car.TCarApply;
import com.system.entity.maintain.SessionInfo;
import com.system.service.base.BaseServiceI;

public interface ICarApplyService extends BaseServiceI<TCarApply> {

	/**
	 * 提交申请单
	 * 
	 * @param TCarApply
	 * @param dictIdApplySendStatus
	 * @return
	 */
	public Serializable save(TCarApply o, String dictIdApplySendStatus);
	
	/**
	 * 审批
	 * 
	 * @param TCarApply
	 * @param sessionInfo
	 */
	public void doApproval(TCarApply apply, SessionInfo sessionInfo);
	
	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(String id);
	
	/**
	 * 紧急派车，还车后信息补录
	 * 
	 * @param o
	 * @param sendStatusId
	 * @return
	 */
	public Serializable doInfoCollection(TCarApply o, String sendStatusId);
	
}
