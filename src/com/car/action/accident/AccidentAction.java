package com.car.action.accident;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.h2.engine.SysProperties;
import org.springframework.beans.factory.annotation.Autowired;

import com.car.entity.accident.TAccident;
import com.car.entity.accident.VCarAccident;
import com.car.entity.car.TCar;
import com.car.entity.car.TCarReturn;
import com.car.entity.car.TCarSend;
import com.car.entity.car.VCarApply;
import com.car.service.accident.IAccidentService;
import com.car.service.accident.VAccidentService;
import com.car.service.car.ICarReturnService;
import com.car.service.car.ICarSendService;
import com.car.service.car.ICarservice;
import com.framework.util.BeanUtils;
import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.framework.util.WebMsgUtil;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Grid;
import com.system.entity.easyui.Json;
import com.system.entity.maintain.SessionInfo;
import com.system.service.maintain.SysOrganizationServiceI;

/**
 * 事故
 * @author Marlon
 *
 */
@Namespace("/")
@Action(value = "accident")
public class AccidentAction extends BaseAction<TAccident> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7222401602187116845L;
	
	private TAccident accident;

	//获取所有车辆
	@Autowired
	private ICarservice carService;
	@Autowired
	private ICarSendService carSendService;
	@Autowired
	private ICarReturnService carRetuenService;
	@Autowired
	private SysOrganizationServiceI orService;
	@Autowired
	private VAccidentService VAccidentService;
	@Autowired
	private ICarservice carservice;
	
	
	
	@Autowired
	public void setService(IAccidentService service){
		this.service = service;
	}
	@Override
	public void getById() {
		if (!StringUtils.isBlank(id)) {
			writeJson(VAccidentService.getById(id));
		} else {
			Json j = new Json();
			j.setMsg("主键不可为空！");
			writeJson(j);
		}
	};
	
	
	
	
	@Override
	public void grid() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		Grid<VCarAccident> grid = new Grid<VCarAccident>();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#ownerCompany_s_EQ",orService.getById( sessionInfo.getOrganization().getOrgIdManager()).getOrgName());
		grid.setTotal(VAccidentService.countByFilter(hqlFilter));
		grid.setRows(VAccidentService.findByFilter(hqlFilter, page, rows));
		writeJson(grid);
	}
	
	public void save(){
		Json json = new Json();
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		
		try {
			accident.setAcciId(UUID.randomUUID().toString());
			accident.setAcciNo(getNumber("acciNo"));
			
			//设置车辆信息
			HqlFilter hqlF = new HqlFilter();
			hqlF.addFilter("QUERY_t#carId_s_EQ", accident.getCarId());
			hqlF.addFilter("QUERY_t#dictIdFlag_s_EQ", WebMsgUtil.YOUXIAO);
			TCar car = carService.getByFilter(hqlF);
			if (car == null) {
				json.setMsg("车牌号错误,没有查到该车辆!");
			}else{
				accident.setCarNo(car.getCarNo());
				//通过catId设置查找产权单位
				accident.setOwnerCompany(orService.getById(car.getOrgIdManager()).getOrgName());
				//设置单号
				//查询距离当前最近的一条记录
				String hql = "from TCarSend where sendTime in (select max(sendTime) from TCarSend)";
				TCarSend carSend = carSendService.getByHql(hql);
				//查看carreturn表中是否有当前用车申请单，如果没有，则代表当前申请单为当前事故的申请单，
				HqlFilter hqlF2 = new HqlFilter();
				hqlF2.addFilter("QUERY_t#sendId_s_EQ",carSend.getSendId());
				hqlF2.addFilter("QUERY_t#dictIdFlag_s_EQ", WebMsgUtil.YOUXIAO);
				TCarReturn carReturn = carRetuenService.getByFilter(hqlF2);
				if (carReturn != null) {
					accident.setSendId(carSend.getSendId());
				}
				
				accident.setTimeCreate(new Date());
				accident.setUserIdCreate(sessionInfo.getUser().getUserId());
				accident.setDictIdFlag(WebMsgUtil.YOUXIAO);
				
				service.save(accident);
				json.setSuccess(true);
			}
		} catch (Exception e) {
			
			json.setMsg("未知错误！");
		}
		writeJson(json);
	}
	
	
	public void update() {
		Json json = new Json();
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		try {
			
			TAccident temp = service.getById(accident.getAcciId());
			if (temp.getSendId() != null) {
				accident.setSendId(temp.getSendId());
			}
			
			//设置车辆信息
			HqlFilter hqlF = new HqlFilter();
			hqlF.addFilter("QUERY_t#carId_s_EQ", accident.getCarId());
			hqlF.addFilter("QUERY_t#dictIdFlag_s_EQ", WebMsgUtil.YOUXIAO);
			TCar car = carService.getByFilter(hqlF);
			if (car == null) {
				json.setMsg("车牌号错误,没有查到该车辆!");
			}else{
				accident.setCarNo(car.getCarNo());
				
				accident.setTimeCreate(temp.getTimeCreate());
				accident.setUserIdCreate(temp.getUserIdCreate());
				accident.setUserIdUpdate(sessionInfo.getUser().getUserId());
				accident.setTimeUpdate(new Date());
				accident.setDictIdFlag(WebMsgUtil.YOUXIAO);
				accident.setOwnerCompany(temp.getOwnerCompany());
				accident.setAcciNo(temp.getAcciNo());
				
				BeanUtils.copyNotNullProperties(accident, temp);
				
				service.update(temp);
				json.setMsg("保存成功！");
				json.setSuccess(true);
			}
		} catch (Exception e) {
			json.setMsg("保存异常！");
			json.setSuccess(false);
			
			e.printStackTrace();
		}
		writeJson(json);
	}
	
	
	
	
	/**
	 * 获取所有车辆
	 */
	public void doNotNeedSecurity_getCars() {
		HqlFilter hqlFilter = new HqlFilter();
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
		List<TCar> list =carService.findByFilter(hqlFilter);
		writeJson(list);
	}
	
	public void doNotNeedSecurity_getUsercarList() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		HqlFilter hqlFilter = new HqlFilter();
		hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ", sessionInfo.getOrganization().getOrgIdManager());
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
		writeJson(carservice.findByFilter(hqlFilter));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public TAccident getAccident() {
		return accident;
	}
	public void setAccident(TAccident accident) {
		this.accident = accident;
	}
}
