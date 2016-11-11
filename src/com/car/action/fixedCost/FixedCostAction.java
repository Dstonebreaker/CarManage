package com.car.action.fixedCost;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.WebUtils;

import com.car.entity.CarFixedCost.TCarFixedCost;
import com.car.entity.CarFixedCost.VFixedCost;
import com.car.entity.car.VCarApply;
import com.car.service.accident.IAccidentService;
import com.car.service.car.IVCarService;
import com.car.service.fixedCost.IFixedCostService;
import com.car.service.fixedCost.VFixedCostService;
import com.framework.util.BeanUtils;
import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.framework.util.WebMsgUtil;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Grid;
import com.system.entity.easyui.Json;
import com.system.entity.maintain.SessionInfo;
import com.system.service.base.BaseServiceI;

//功能描述：对车辆维保费、年检费、保险费等年度定期费用进行管理。
//页面功能：包括各项费用的录入、修改和删除、查看明细操作。
//费用记录包括：维保费、年检费、保险费。
//1.10.2.	费用查询
//功能描述：对费用进行查询。
//页面功能：包括查询操作。
//费用查询包括： 按单位、日期范围、费用类型等组合条件进行查询统计。


@Namespace("/")
@Action("fixedcost")
public class FixedCostAction extends BaseAction<TCarFixedCost> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8426443870136316415L;

	
	@Autowired
	public void setService(IFixedCostService service){
		this.service = service;
	}
	@Autowired
	private VFixedCostService VFixedConstService;
	@Autowired
	private IVCarService carservice;
	
	private TCarFixedCost fixedCost;


	
	
	@Override
	public void grid() {
		Grid<VFixedCost> grid = new Grid<VFixedCost>();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
		grid.setTotal(VFixedConstService.countByFilter(hqlFilter));
		grid.setRows(VFixedConstService.findByFilter(hqlFilter, page, rows));
		writeJson(grid);
	}
	
	
	public void save(){
		Json json = new Json();
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		
		try {
			fixedCost.setCostId(UUID.randomUUID().toString());
			fixedCost.setUserIdCreate(sessionInfo.getUser().getUserId());
			fixedCost.setTimeCreate(new Timestamp(new Date().getTime()));
			fixedCost.setDictIdFlag(WebMsgUtil.YOUXIAO);
			
			service.save(fixedCost);
			json.setSuccess(true);
			
		} catch (Exception e) {
			json.setMsg("未知错误！");
		}
		writeJson(json);
	}
	
	
	public void getByCarNo(){
		HqlFilter hqlF = new HqlFilter();
		hqlF.addFilter("QUERY_t#carNo_s_EQ", id);
		hqlF.addFilter("QUERY_t#dictIdFlag_s_EQ", WebMsgUtil.YOUXIAO);
		
		writeJson(VFixedConstService.findByFilter(hqlF));
	}
	public void getById(){
		writeJson(VFixedConstService.getById(id));
	}
	
	public void update(){
		Json json = new Json();
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		
		try {
			fixedCost.setUserIdUpdate(sessionInfo.getUser().getUserId());
			fixedCost.setTimeUpdate(new Timestamp(new Date().getTime()));
			fixedCost.setDictIdFlag(WebMsgUtil.YOUXIAO);
			
			HqlFilter hqlF = new HqlFilter();
			hqlF.addFilter("QUERY_t#costId_S_EQ", fixedCost.getCostId());
			hqlF.addFilter("QUERY_t#dictIdFlag_S_EQ",WebMsgUtil.YOUXIAO);
			TCarFixedCost temp = service.getByFilter(hqlF);
			
			BeanUtils.copyNotNullProperties(fixedCost, temp, new String[]{
					"dictIdFlag","userIdCreate","timeCreate"
			});
			
			service.update(temp);
			json.setSuccess(true);
			
		} catch (Exception e) {
			json.setMsg("未知错误！");
		}
		writeJson(json);
	}

	public void delete() {
		Json json = new Json();
		if (!StringUtils.isBlank(id)) {
			TCarFixedCost t = service.getById(id);
			t.setDictIdFlag("WX");
			service.update(t);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}
	
	
	public void doNotNeedSecurity_getcarList(){
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		HqlFilter hqlF = new HqlFilter(getRequest());
		hqlF.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
		hqlF.addFilter("QUERY_t#orgIdManager_S_EQ",sessionInfo.getOrganization().getOrgIdManager());
		//去掉报废等车
		hqlF.addFilter("QUERY_t#dictIdCarStatus_S_NE", WebMsgUtil.CARSTATUS_SEALED);
		hqlF.addFilter("QUERY_t#dictIdCarStatus_S_NE", WebMsgUtil.CARSTATUS_AUCTION);
		hqlF.addFilter("QUERY_t#dictIdCarStatus_S_NE", WebMsgUtil.CARSTATUS_SCRAP);
		
		String[] includes={"carId","carNo"};
		writeJsonByIncludesProperties(carservice.findByFilter(hqlF),includes);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public TCarFixedCost getFixedCost() {
		return fixedCost;
	}

	public void setFixedCost(TCarFixedCost fixedCost) {
		this.fixedCost = fixedCost;
	}
	
}
