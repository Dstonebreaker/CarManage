package com.system.service.maintain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.system.entity.maintain.TsysLog;
import com.system.service.base.BaseServiceI;


public interface ISystemLogService extends BaseServiceI<TsysLog> {
	
	public void save(String method, String description, HttpSession session,
			HttpServletRequest request);

	public void saveMoblie(String method, String userid, String description);
	
}
