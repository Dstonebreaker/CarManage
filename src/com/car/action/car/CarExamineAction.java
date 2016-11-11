package com.car.action.car;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.car.entity.car.TCar;
import com.car.entity.car.TCarExamine;
import com.car.entity.car.VCarExamine;
import com.car.service.car.ICarExamineService;
import com.car.service.car.ICarservice;
import com.car.service.car.IVCarExamineService;
import com.framework.util.BeanUtils;
import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.framework.util.WebMsgUtil;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Grid;
import com.system.entity.easyui.Json;
import com.system.entity.maintain.SessionInfo;
import com.system.entity.maintain.SysUser;
import com.system.service.maintain.ISysSettingService;
import com.system.service.maintain.SysUserServiceI;

@Namespace("/car")
@Action("Examine")
public class CarExamineAction extends BaseAction<TCarExamine> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6590787352768445125L;

	private String startTime;
	private String endTime;
	private TCarExamine carExamine;
	@Autowired
	private ICarservice carservice;
	@Autowired
	private ICarExamineService carExamineService;
	@Autowired
	private SysUserServiceI userService;
	@Autowired
	private IVCarExamineService vCarExamineService;
	@Autowired
	private ISysSettingService settingService;

	@Autowired
	public void setService(ICarExamineService service) {
		// TODO Auto-generated method stub
		super.setService(service);
	}

	public TCarExamine getCarExamine() {
		return carExamine;
	}

	public void setCarExamine(TCarExamine carExamine) {
		this.carExamine = carExamine;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * 车辆年审列表
	 */
	public void grid() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		Grid<VCarExamine> grid = new Grid<VCarExamine>();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		if (startTime != null) {
			hqlFilter.addFilter("QUERY_t#examValidStartTime_D_GE", startTime);
		}
		if (endTime != null) {
			hqlFilter.addFilter("QUERY_t#examValidOverTime_D_LE", endTime);
		}
		hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ", sessionInfo.getOrganization().getOrgIdManager());
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
		//hqlFilter.addFilter("QUERY_t#carFlag_S_EQ", WebMsgUtil.YOUXIAO);
		grid.setTotal(vCarExamineService.countByFilter(hqlFilter));
		grid.setRows(vCarExamineService.findByFilter(hqlFilter, page, rows));
		writeJson(grid);
	}

	/**
	 * 车辆年审预警列表
	 */
	public void alertGrid() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		Grid<VCarExamine> grid = new Grid<VCarExamine>();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		Calendar cal = Calendar.getInstance();
	
	
		if (startTime != null) {
			hqlFilter.addFilter("QUERY_t#examValidStartTime_D_GE", startTime);
		}
		if (endTime != null) {
			hqlFilter.addFilter("QUERY_t#examValidOverTime_D_LE", endTime);
		}
		hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ", sessionInfo.getOrganization().getOrgIdManager());
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);	
		//hqlFilter.addFilter("QUERY_t#carFlag_S_EQ", WebMsgUtil.YOUXIAO);
		hqlFilter.addFilter("QUERY_t#day_I_GE", "0");
		hqlFilter.addFilter("QUERY_t#day_I_LE", settingService.getById("examTimeId").getSettValue().toString());
		grid.setTotal(vCarExamineService.countByFilter(hqlFilter));
		List<VCarExamine> vCarExaminesLists = vCarExamineService.findByFilter(hqlFilter, page, rows);
		for (VCarExamine vCarExamine : vCarExaminesLists) {	
			cal.setTime(vCarExamine.getExamValidOverTime());
			cal.add(5, 1);
			vCarExamine.setNextEaxmTime(cal.getTime());//下次年审日期
		}
		grid.setRows(vCarExaminesLists);
		writeJson(grid);
	}

	/**
	 * 保存年审记录
	 */
	public void save() {
		Json json = new Json();
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());

		try {
			if (carExamine != null) {
				HqlFilter hqlFilter = new HqlFilter(getRequest());
				hqlFilter
						.addFilter("QUERY_t#carId_S_EQ", carExamine.getCarId());
				hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ",
						WebMsgUtil.YOUXIAO);
				TCarExamine carExamineTemp = carExamineService
						.getByFilter(hqlFilter);
				if (carExamineTemp != null) {
					json.setMsg("登记年审失败，该车牌号已登记！");
				} else {
					TCar car = carservice.getById(carExamine.getCarId());
					carExamine.setCarNo(car.getCarNo());
					carExamine.setExamId(UUID.randomUUID().toString());
					carExamine.setTimeCreate(new Date());

					Calendar cal = Calendar.getInstance();
					cal.setTime(carExamine.getExamValidStartTime());
					// SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");				
					if (carExamine.getExamCycle() == 0.5) {

						cal.add(2, 6);
					}else{
						cal.add(1, (int) carExamine.getExamCycle().doubleValue());
					}
					carExamine.setExamValidOverTime(cal.getTime());
					carExamine.setDictIdFlag(WebMsgUtil.YOUXIAO);
					carExamine.setUserIdCreate(sessionInfo.getUser()
							.getUserId());
					carExamineService.save(carExamine);
					json.setSuccess(true);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("未知异常");
			json.setSuccess(false);

		}

		writeJson(json);
	}

	/**
	 * 更新年审登记
	 */
	public void update() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());

		Json json = new Json();
		json.setMsg("更新失败！");
		try {
			if (carExamine != null
					&& !StringUtils.isBlank(carExamine.getCarId())) {

				HqlFilter hqlFilter = new HqlFilter(getRequest());
				hqlFilter.addFilter("QUERY_t#examId_S_NE",
						carExamine.getExamId());
				hqlFilter
						.addFilter("QUERY_t#carId_S_EQ", carExamine.getCarId());
				hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ",
						WebMsgUtil.YOUXIAO);
				TCarExamine examineTemp = carExamineService
						.getByFilter(hqlFilter);
				if (examineTemp != null) {
					json.setMsg("更新年审失败，该车牌号已登记！");
				} else {
					TCar car = carservice.getById(carExamine.getCarId());
					TCarExamine t = carExamineService.getById(carExamine
							.getExamId());
					BeanUtils.copyNotNullProperties(carExamine, t,
							new String[] { "userIdCreate", "timeCreate",
									"dictIdFlag", });
					t.setUserIdUpdate(sessionInfo.getUser().getUserId());
					t.setTimeUpdate(new Date());
					t.setCarNo(car.getCarNo());
					
					Calendar cal = Calendar.getInstance();
					cal.setTime(t.getExamValidStartTime());
					// SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					
					if (t.getExamCycle() == 0.5) {
						cal.add(2, 6);
					}else{
						cal.add(1, (int) t.getExamCycle().doubleValue());
					}					
					
					t.setExamValidOverTime(cal.getTime());
					carExamineService.update(t);
					json.setSuccess(true);
					json.setMsg("更新成功！");

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		writeJson(json);
	}

	/**
	 * 删除年审登记
	 */
	public void delete() {
		Json json = new Json();
		try {
			TCarExamine examineTemp = carExamineService.getById(id);
			examineTemp.setDictIdFlag(WebMsgUtil.WUXIAO);
			carExamineService.update(examineTemp);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		writeJson(json);
	}

	/**
	 * 获得所有用户
	 */
	public void doNotNeedSecurity_getAllUserList() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
		List<SysUser> userList = userService.findByFilter(hqlFilter);
		writeJson(userList);
	}
	
	/**
	 * 获取预警详情
	 */
	public void getAlertById(){
		Calendar cal = Calendar.getInstance();		
		if (!StringUtils.isBlank(id)) {
			VCarExamine vCarExamine = vCarExamineService.getById(id);
			cal.setTime(vCarExamine.getExamValidOverTime());
			cal.add(5, 1);
			vCarExamine.setNextEaxmTime(cal.getTime());
			writeJson(vCarExamine);
		} else {
			Json j = new Json();
			j.setMsg("主键不可为空！");
			writeJson(j);
		}
		
	}

}
