package com.system.service.maintain;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.dao.BaseDaoI;
import com.framework.util.ConfigUtil;
import com.framework.util.IpUtil;
import com.system.entity.maintain.SessionInfo;
import com.system.entity.maintain.TsysLog;
import com.system.service.base.BaseServiceImpl;

@Service
public class SystemLogServiceImpl extends BaseServiceImpl<TsysLog> implements ISystemLogService {

	@Autowired
	private BaseDaoI<TsysLog> systemLogDao;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.zhpmomdm.service.sm.ISystemLogService#save(java.lang.String,
	 *      java.lang.String, javax.servlet.http.HttpSession)
	 * @param method
	 *            是功能 比如查询、增加
	 * @param description
	 *            功能得具体描述
	 * @param session
	 *            直接给getSession()
	 */
	@Override
	public void save(String method, String description, HttpSession session,
			HttpServletRequest request) {

		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil
				.getSessionInfoName());

		TsysLog systemLog = new TsysLog();
		systemLog.setLogIp(IpUtil.getIpAddr(request));
		systemLog.setUserId(sessionInfo.getUser().getUserId());
		systemLog.setLogOperateTime(new Date());
		systemLog.setLogOperateDescription(description);
		systemLog.setLogOperateFunction(method);		

		try {
			this.systemLogDao.save(systemLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveMoblie(String method, String userid, String description) {
		// TODO Auto-generated method stub
		TsysLog systemLog = new TsysLog();

		systemLog.setLogIp("移动端");
		systemLog.setUserId(userid);
		systemLog.setLogOperateTime(new Date());
		systemLog.setLogOperateDescription(description);
		systemLog.setLogOperateFunction(method);

		try {
			this.systemLogDao.save(systemLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
