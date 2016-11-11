package com.system.action.maintain;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Grid;
import com.system.entity.easyui.Json;
import com.system.entity.maintain.SessionInfo;
import com.system.entity.maintain.SysSetting;
import com.system.service.maintain.ISysSettingService;
import com.system.service.maintain.SettingManager;



@Namespace("/maintain")
@Action(value = "sysSetting")
public class SysSettingAction extends BaseAction<SysSetting>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4071696568482536571L;
	private   SysSetting  sysSetting;
	@Autowired
	public  ISysSettingService iSysSettingService;
	@Autowired
	public void setService(ISysSettingService service) {
		this.service = service;
	}
	public void grid() {
		Grid<SysSetting> grid = new Grid<SysSetting>();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		grid.setTotal(iSysSettingService.countByFilter(hqlFilter));
		grid.setRows(iSysSettingService.findByFilter(hqlFilter, page, rows));
		writeJson(grid);
	}
	public void update(){
		Json json = new Json();
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		if(sysSetting!=null){
			sysSetting.setUserIdUpdate(sessionInfo.getUser().getUserId());
			sysSetting.setTimeUpdate(new Date());
			iSysSettingService.update(sysSetting);
		 SettingManager.getInstance(iSysSettingService).setValue(sysSetting.getSettId(), sysSetting.getSettValue());
			json.setSuccess(true);
			json.setMsg("更新成功");
		}
		writeJson(json);
	}
	/**
	 * 无需权限查找所有对象
	 */
	public void doNotNeedSecurity_findAll() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		writeJson(service.findByFilter(hqlFilter));
	}
	public SysSetting getSysSetting() {
		return sysSetting;
	}
	public void setSysSetting(SysSetting sysSetting) {
		this.sysSetting = sysSetting;
	}


}
