package com.car.action.car;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.car.entity.car.TCar;
import com.car.entity.car.TCarChange;
import com.car.entity.car.VCarChange;
import com.car.service.car.ICarChangeService;
import com.car.service.car.ICarservice;
import com.car.service.car.IVCarChangeService;
import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.framework.util.WebMsgUtil;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Grid;
import com.system.entity.easyui.Json;
import com.system.entity.maintain.SessionInfo;
import com.system.service.maintain.ISysSettingService;
import com.system.service.maintain.SettingManager;

@Namespace("/car")
@Action("change")
public class CarChangeAction extends BaseAction<VCarChange>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4039590762053444769L;	
	private String changeManagerNew;
	private TCarChange carChange;
	private String dictId;
	@Autowired
	private ICarservice carservice;
	@Autowired
	private  ISysSettingService iSysSettingService;
	@Autowired
	private IVCarChangeService carChangeService;
	@Autowired
	private ICarChangeService changeService;
	@Autowired
	public void setService(IVCarChangeService service) {
		super.setService(service);
	}

	public void grid() {
		Grid<VCarChange> grid = new Grid<VCarChange>();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		if (!(changeManagerNew == null || "".equals(changeManagerNew))) {
			hqlFilter.addFilter("QUERY_t#changeManagerNew_S_EQ", changeManagerNew);
		}
		if (!(dictId == null || "".equals(dictId))) {
			hqlFilter.addFilter("QUERY_t#dictIdChangeType_S_EQ", dictId);
		}
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
		hqlFilter.addFilter("QUERY_t#dictIdCarStatus_S_EQ", WebMsgUtil.CARSTATUS_FREE);
		grid.setTotal(carChangeService.countByFilter(hqlFilter));
		grid.setRows(carChangeService.findByFilter(hqlFilter, page, rows));
		writeJson(grid);
	}
	public void save(){	
		Json json = new Json();
		if(carChange!=null){
			if(carChange.getChangeCarNoNew()!=null){
				 HqlFilter hqlFilters = new HqlFilter(getRequest());
				 hqlFilters.addFilter("QUERY_t#carNo_S_EQ", carChange.getChangeCarNoNew());
				 hqlFilters.addFilter("QUERY_t#dictIdFlag_S_EQ",WebMsgUtil.YOUXIAO);
				 TCar  cars  =  carservice.getByFilter(hqlFilters);
				 if(cars !=null){
					 json.setSuccess(false);
					 json.setMsg("该车牌已存在！");		
					 writeJson(json);
					 return;
				 }
			}
	     SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		 carChange.setDictIdChangeType(dictId);
		 carChange.setUserIdCreate(sessionInfo.getUser().getUserId());
		 carChange.setTimeCreate(new Date());
		 HqlFilter hqlFilter = new HqlFilter(getRequest());
		 hqlFilter.addFilter("QUERY_t#carId_S_EQ", carChange.getCarId());
		 hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ",WebMsgUtil.YOUXIAO);
		 TCar  car  =  carservice.getByFilter(hqlFilter);
		 car.setUserIdMaster(carChange.getUserIdMasterNew());//更新车管员信息
		 if("CHA0001".equals(dictId)){
			 car.setOrgIdManager(carChange.getChangeManagerNew());//更新产权单位信息
		 }
          else if("CHA0002".equals(dictId)){
			 car.setOrgIdUse(carChange.getChangeUseNew());//更新使用单位信息
		 }else if("CHA0003".equals(dictId)){
			 carChange.setChangeCarNoOld(car.getCarNo());
			 car.setCarNo(carChange.getChangeCarNoNew());//更新车牌号信息
		 }
		 changeService.save(carChange);
		 carservice.update(car);//更新car信息表数据
		 json.setSuccess(true);
		 json.setMsg("添加成功");
			
		}
		writeJson(json);
	}
	
	public String getChangeManagerNew() {
		return changeManagerNew;
	}

	public void setChangeManagerNew(String changeManagerNew) {
		this.changeManagerNew = changeManagerNew;
	}
	public TCarChange getCarChange() {
		return carChange;
	}
	public void setCarChange(TCarChange carChange) {
		this.carChange = carChange;
	}

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

}
