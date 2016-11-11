package com.car.action.insurance;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.car.entity.accident.VCarAccident;
import com.car.entity.car.TCar;
import com.car.entity.insurance.TCarClaims;
import com.car.entity.insurance.TCarInsurance;
import com.car.service.accident.IAccidentService;
import com.car.service.accident.VAccidentService;
import com.car.service.accident.VCarAccidentServiceImpl;
import com.car.service.car.ICarservice;
import com.car.service.insurance.CarClaimsServiceI;
import com.car.service.insurance.CarInsuranceServiceI;
import com.car.service.insurance.VCarClaimsServiceI;
import com.car.service.insurance.VCarInsuranceServiceI;
import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.framework.util.WebMsgUtil;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.system.action.base.BaseAction;
import com.system.entity.easyui.Json;
import com.system.entity.maintain.SessionInfo;

@Namespace("/car")
@Action("claims")
public class CarClaimsAction extends BaseAction<TCarClaims> {

	/**
     *
     */
	private static final long serialVersionUID = -4773541688346517845L;

	private TCarClaims carClaims;
	@Autowired
	private ICarservice carservice;
	@Autowired
	private CarInsuranceServiceI insuranceServiceI;
	@Autowired
	private IAccidentService accidentService;
	@Autowired
	private VAccidentService accident;
	@Autowired
	private VCarClaimsServiceI vcarClaimsService;
	@Autowired
	private VCarInsuranceServiceI carInsuranceServiceI ;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	public void setService(CarClaimsServiceI service) {
		// TODO Auto-generated method stub
		super.setService(service);
	}

	/**
	 * 保存
	 */
	public void save() {
		Json json = new Json();
		try {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			carClaims.setUserIdCreate(sessionInfo.getUser().getUserId());
			carClaims.setTimeCreate(new Date());
			TCarInsurance insurance = insuranceServiceI.getById(carClaims.getInsuId());
			String no=getNumber("claimsNo");
			if (no!=null&&!no.equals("")) {
				carClaims.setClaimsNo(no);
			}else {
				json.setSuccess(false);
				json.setMsg("保存失败");
			}
			if (insurance != null) {
				carClaims.setCarId(insurance.getCarId());
				carClaims.setCarNo(insurance.getCarNo());
			} else {
				json.setSuccess(false);
				json.setMsg("保存失败");
			}
			carClaims.setDictIdFlag(WebMsgUtil.YOUXIAO);
			service.save(carClaims);
			json.setSuccess(true);
			json.setMsg("保存成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("保存失败");
		}
		writeJson(json);
	}

	/**
	 * 更新
	 */
	public void update() {
		Json json = new Json();
		try {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			TCarInsurance insurance = insuranceServiceI.getById(carClaims.getInsuId());

			if (insurance != null) {
				carClaims.setCarId(insurance.getCarId());
				carClaims.setCarNo(insurance.getCarNo());
			} else {
				json.setSuccess(false);
				json.setMsg("保存失败");
			}
			carClaims.setDictIdFlag(WebMsgUtil.YOUXIAO);
			carClaims.setTimeUpdate(new Date());
			carClaims.setUserIdUpdate(sessionInfo.getUser().getUserId());
			service.update(carClaims);
			json.setSuccess(true);
			json.setMsg("保存成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("保存失败");
		}
		writeJson(json);

	}

	/*
	 * 删除
	 */
	public void delete() {

		Json json = new Json();
		try {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			TCarClaims carClaims = service.getById(id);
			carClaims.setDictIdFlag(WebMsgUtil.WUXIAO);
			carClaims.setUserIdUpdate(sessionInfo.getUser().getUserId());
			carClaims.setTimeUpdate(new Date());
			service.update(carClaims);
			json.setMsg("删除成功");
			json.setSuccess(true);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			json.setMsg("删除失败");
			json.setSuccess(false);
		}
		writeJson(json);
	}

	public void doNotNeedSecurity_getInsuranceList() {
		if(id == null || "".equals(id)){
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
		hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ", sessionInfo.getOrganization().getOrgIdManager());
		writeJson(carInsuranceServiceI.findByFilter(hqlFilter));
		}else{
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			HqlFilter hqlFilter = new HqlFilter(getRequest());
			hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
			hqlFilter.addFilter("QUERY_t#carId_S_EQ", id);
			hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ", sessionInfo.getOrganization().getOrgIdManager());
			writeJson(carInsuranceServiceI.findByFilter(hqlFilter));		
		}
	}
    //获取所有保险单的所有需要理赔的车辆(无法根据单位筛选)
	public void doNotNeedSecurity_getAcciList() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
     //	hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ", sessionInfo.getOrganization().getOrgIdManager());
		writeJson(accidentService.findByFilter(hqlFilter));
	}
	public TCarClaims getCarClaims() {
		return carClaims;
	}

	public void setCarClaims(TCarClaims carClaims) {
		this.carClaims = carClaims;
	}

}
