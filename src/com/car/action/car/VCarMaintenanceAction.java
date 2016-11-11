package com.car.action.car;

import com.car.entity.car.TCarMaintenance;
import com.car.entity.car.VCarMaintenance;
import com.car.entity.insurance.VCarInsurance;
import com.car.service.car.ICarMaintenanceService;
import com.car.service.car.ICarModelMaintenanceService;
import com.car.service.car.IVCarMaintenanceService;
import com.car.service.car.IVCarModelMaintenanceService;
import com.car.service.car.IVCarService;
import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.framework.util.WebMsgUtil;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Grid;
import com.system.entity.easyui.Json;
import com.system.entity.maintain.SessionInfo;
import com.system.service.maintain.ISysSettingService;
import com.system.service.maintain.SettingManager;
import com.system.service.maintain.SysUserServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Namespace("/car")
@Action("vcarmaintenance")
public class VCarMaintenanceAction extends BaseAction<VCarMaintenance> {

    /**
     *
     */



    @Autowired
    public void setService(IVCarMaintenanceService service) {
        // TODO Auto-generated method stub
        super.setService(service);
    }
    @Autowired
	private IVCarMaintenanceService ivCarMaintenanceService;
    @Autowired
	private ISysSettingService iSysSettingService;
    @Autowired
	private IVCarService carservice;

    public  void grid(){
    	SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
    	Grid<VCarMaintenance> grid = new Grid<VCarMaintenance>();
		HqlFilter hqlFilter = new HqlFilter(getRequest());//
		hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ", sessionInfo.getOrganization().getOrgIdManager());
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
		grid.setTotal(ivCarMaintenanceService.countByFilter(hqlFilter));
		grid.setRows(ivCarMaintenanceService.findByFilter(hqlFilter, page, rows));
		writeJson(grid);
    		
    }
	public  void grids(){
    	Grid<VCarMaintenance> grid = new Grid<VCarMaintenance>();
		HqlFilter hqlFilter = new HqlFilter(getRequest());//
	    int time= SettingManager.getInstance(iSysSettingService).getValue("mainTimeId");
	    int mile= SettingManager.getInstance(iSysSettingService).getValue("mainMileId");
		hqlFilter.addFilter("QUERY_t#day_I_GE", "0");
		hqlFilter.addFilter("QUERY_t#day_I_LE", String.valueOf(time));
		hqlFilter.addFilter("QUERY_t#mileage_I_GE", "0");
		hqlFilter.addFilter("QUERY_t#mileage_I_LE", String.valueOf(mile));
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
		grid.setTotal(ivCarMaintenanceService.countByFilter(hqlFilter));
		grid.setRows(ivCarMaintenanceService.findByFilter(hqlFilter, page, rows));
		writeJson(grid);
    		
    }
}
