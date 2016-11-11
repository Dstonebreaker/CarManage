package com.system.action.maintain;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.car.service.car.IVCarExamineService;
import com.car.service.car.IVCarMaintenanceService;
import com.car.service.insurance.VCarInsuranceServiceI;
import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.framework.util.WebMsgUtil;
import com.system.action.base.BaseAction;
import com.system.entity.maintain.SysRemind;
import com.system.service.maintain.ISysSettingService;
import com.system.service.maintain.SettingManager;

@Namespace("/maintain")
@Action(value = "sysRemind")
public class SysRemindAction extends BaseAction<SysRemind>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9171225097673521681L;
	@Autowired
	private IVCarExamineService vCarExamineService;
	@Autowired
	private VCarInsuranceServiceI vCarInsuranceServiceI; 
	@Autowired
	private IVCarMaintenanceService vCarMaintenanceService;
    @Autowired
		private ISysSettingService iSysSettingService;
    private SysRemind sysRemind;
	
	public void doNotNeedSecurity_getCount() {
		try {
			HqlFilter hqlFilter = new HqlFilter();
		    int time1= SettingManager.getInstance(iSysSettingService).getValue("examTimeId");
			hqlFilter.addFilter("QUERY_t#day_I_GE", "0");
			hqlFilter.addFilter("QUERY_t#day_I_LE", String.valueOf(time1));
			hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
			long count1 = vCarExamineService.countByFilter(hqlFilter);
			
			HqlFilter hqlFilters = new HqlFilter();
			 int time2= SettingManager.getInstance(iSysSettingService).getValue("inSurtTimeId");
			hqlFilters.addFilter("QUERY_t#day_I_GE", "0");
			hqlFilters.addFilter("QUERY_t#day_I_LE", String.valueOf(time2));
			hqlFilters.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
			long count2 = vCarInsuranceServiceI.countByFilter(hqlFilters);
			
			HqlFilter hqlFilteres = new HqlFilter();
		 int time= SettingManager.getInstance(iSysSettingService).getValue("mainTimeId");
		 int mile= SettingManager.getInstance(iSysSettingService).getValue("mainMileId");
			hqlFilteres.addFilter("QUERY_t#day_I_GE", "0");
			hqlFilteres.addFilter("QUERY_t#day_I_LE", String.valueOf(time));
			hqlFilteres.addFilter("QUERY_t#mileage_I_GE", "0");
			hqlFilteres.addFilter("QUERY_t#mileage_I_LE", String.valueOf(mile));
			hqlFilteres.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
			long count3 = vCarMaintenanceService.countByFilter(hqlFilteres);
			SysRemind sysReminds = new  SysRemind();
			sysReminds.setCountExamine((count1+""));
			sysReminds.setCountInsurance(count2+"");
			sysReminds.setCountMaintenance(count3+"");
			writeJson(sysReminds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SysRemind getSysRemind() {
		return sysRemind;
	}

	public void setSysRemind(SysRemind sysRemind) {
		this.sysRemind = sysRemind;
	}

}
