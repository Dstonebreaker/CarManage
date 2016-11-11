package com.car.action.insurance;

import com.car.entity.car.VCarManage;
import com.car.entity.insurance.VCarClaims;
import com.car.entity.insurance.VCarInsurance;
import com.car.service.insurance.VCarClaimsServiceI;
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

@Namespace("/car")
@Action("vclaims")
public class VCarClaimsAction extends BaseAction<VCarClaims> {

	/**
     *
     */
	private static final long serialVersionUID = -4773541688346517845L;

	@Autowired
	public void setService(VCarClaimsServiceI service) {
		// TODO Auto-generated method stub
		super.setService(service);
	}
	public void grid() {
		Grid<VCarClaims> grid = new Grid<VCarClaims>();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());	
		hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ", sessionInfo.getOrganization().getOrgIdManager());
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
		grid.setTotal(service.countByFilter(hqlFilter));
		grid.setRows(service.findByFilter(hqlFilter, page, rows));
		writeJson(grid);

	}

}
