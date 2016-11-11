package com.system.action.maintain;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Grid;
import com.system.entity.maintain.SessionInfo;
import com.system.entity.maintain.VsysLog;
import com.system.service.maintain.VISystemLogService;

@Namespace("/sm")
@Action(value = "systemLogView")
public class SystemLogViewAction extends BaseAction<VsysLog> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4251949993792679885L;

	@Autowired
	public VISystemLogService systemLogService;
	@Autowired
	public void setService(VISystemLogService service) {
		this.service = service;

	}

	@Override
	public void grid() {
		Grid<VsysLog> grid = new Grid<VsysLog>();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		grid.setTotal(service.countByFilter(hqlFilter));
		grid.setRows(service.findByFilter(hqlFilter, page, rows));
		writeJson(grid);
	}

	public void doNotNeedSessionAndSecurity_function() {

		List systemIndustries = service.findBySql("select v_sys_log.logOperateFunction from v_sys_log  group by logOperateFunction");

		writeJson(systemIndustries);
	}
}
