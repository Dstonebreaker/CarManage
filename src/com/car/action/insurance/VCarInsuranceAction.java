package com.car.action.insurance;

import com.car.entity.insurance.VCarInsurance;
import com.car.service.insurance.VCarInsuranceServiceI;
import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.framework.util.WebMsgUtil;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.system.action.base.BaseAction;
import com.system.entity.easyui.Grid;
import com.system.entity.maintain.SessionInfo;
import com.system.service.maintain.ISysSettingService;
import com.system.service.maintain.SettingManager;

@Namespace("/car")
@Action("vinsurance")
public class VCarInsuranceAction extends BaseAction<VCarInsurance> {

	/**
     *
     */
	private static final long serialVersionUID = -4773541688346517845L;

	@Autowired
	public void setService(VCarInsuranceServiceI service) {
		// TODO Auto-generated method stub
		super.setService(service);
	}
	@Autowired
	private VCarInsuranceServiceI carInsuranceServiceI;
	@Autowired
	private ISysSettingService iSysSettingService;
	//保养提醒列表
	public  void grid(){
    	Grid<VCarInsurance> grid = new Grid<VCarInsurance>();
		HqlFilter hqlFilter = new HqlFilter(getRequest());//
	    int a= SettingManager.getInstance(iSysSettingService).getValue("inSurtTimeId");
		hqlFilter.addFilter("QUERY_t#day_I_GE", "0");
		hqlFilter.addFilter("QUERY_t#day_I_LE", String.valueOf(a));
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
		grid.setTotal(carInsuranceServiceI.countByFilter(hqlFilter));
		grid.setRows(carInsuranceServiceI.findByFilter(hqlFilter, page, rows));
		writeJson(grid);		
    }
	//保险记录列表
	public  void grids(){
    	Grid<VCarInsurance> grid = new Grid<VCarInsurance>();
		HqlFilter hqlFilter = new HqlFilter(getRequest());//
	 // int a= SettingManager.getInstance(iSysSettingService).getValue("inSurtTimeId");
	 // hqlFilter.addFilter("QUERY_t#day_I_GE", "0");
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());	
		hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ", sessionInfo.getOrganization().getOrgIdManager());
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
		grid.setTotal(carInsuranceServiceI.countByFilter(hqlFilter));
		grid.setRows(carInsuranceServiceI.findByFilter(hqlFilter, page, rows));
		writeJson(grid);		
    }
}
