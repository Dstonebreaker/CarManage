package com.system.action.maintain;



import java.sql.Timestamp;
import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.car.entity.obd.TObdCarFaultCode;
import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Grid;
import com.system.entity.easyui.Json;
import com.system.entity.maintain.SessionInfo;
import com.system.service.maintain.ITObdCarFaultCodeService;


@Namespace("/maintain")
@Action(value = "ObdCode")
public class SysObdCodeAction  extends BaseAction<TObdCarFaultCode>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8166298783183099443L;
	
	private TObdCarFaultCode itObdCarFaultCode;
	@Autowired
	public  ITObdCarFaultCodeService itObdCarFaultCodeService;
	@Autowired
	public void setService(ITObdCarFaultCodeService service) {
		this.service = service;
	}
	public void grid() {
		Grid<TObdCarFaultCode> grid = new Grid<TObdCarFaultCode>();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		grid.setTotal(itObdCarFaultCodeService.countByFilter(hqlFilter));
		grid.setRows(itObdCarFaultCodeService.findByFilter(hqlFilter, page, rows));
		writeJson(grid);
	}
	
	
	public void update(){
		Json json = new Json();
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		if(itObdCarFaultCode!=null){
			itObdCarFaultCode.setUserIdUpdate(sessionInfo.getUser().getUserId());	
			Date date = new Date();       
			Timestamp nousedate = new Timestamp(date.getTime());
			itObdCarFaultCode.setTimeUpdate(nousedate);
			itObdCarFaultCodeService.update(itObdCarFaultCode);
			json.setSuccess(true);
			json.setMsg("更新成功");
		}
		writeJson(json);
	}
	public TObdCarFaultCode getItObdCarFaultCode() {
		return itObdCarFaultCode;
	}
	public void setItObdCarFaultCode(TObdCarFaultCode itObdCarFaultCode) {
		this.itObdCarFaultCode = itObdCarFaultCode;
	}
	
}
