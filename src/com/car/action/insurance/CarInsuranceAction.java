package com.car.action.insurance;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.car.entity.message.MsgAlarm;
import com.car.entity.insurance.TCarInsurance;
import com.car.service.car.ICarservice;
import com.car.service.car.IVCarService;
import com.car.service.insurance.CarInsuranceServiceI;
import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.framework.util.WebMsgUtil;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.system.action.base.BaseAction;
import com.system.entity.easyui.Json;
import com.system.entity.maintain.SessionInfo;

/**
 * 保险action
 */
@Namespace("/car")
@Action("insurance")
public class CarInsuranceAction extends BaseAction<TCarInsurance> {

	/**
     *
     */
	private static final long serialVersionUID = -4773541688346517845L;

	private TCarInsurance insurance;
	@Autowired
	private IVCarService carservice;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private String carId;
	private String typeId;
	@Autowired
	public void setService(CarInsuranceServiceI service) {
		// TODO Auto-generated method stub
		super.setService(service);
	}

	/**
	 * 保存
	 */
	public void save() {
		Json json = new Json();
		try {
			HqlFilter hqlFilter = new HqlFilter(getRequest());
			hqlFilter.addFilter("QUERY_t#insuNo_S_EQ",insurance.getInsuNo());
			hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
			hqlFilter.addFilter("QUERY_t#dictIdInsuranceCorp_S_EQ",insurance.getDictIdInsuranceCorp());
			TCarInsurance insurances = service.getByFilter(hqlFilter);
			if(insurances!=null){
				json.setSuccess(false);
				json.setMsg("保险单号已存在");
			}else{
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			insurance.setUserIdCreate(sessionInfo.getUser().getUserId());
			insurance.setTimeCreate(new Date());
			insurance.setDictIdFlag(WebMsgUtil.YOUXIAO);
			service.save(insurance);
			json.setSuccess(true);
			json.setMsg("保存成功");
			}
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
			insurance.setUserIdUpdate(sessionInfo.getUser().getUserId());
			insurance.setTimeUpdate(new Date());
			insurance.setDictIdFlag(WebMsgUtil.YOUXIAO);
			service.update(insurance);
			json.setMsg("保存成功");
			json.setSuccess(true);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			json.setMsg("保存失败");
			json.setSuccess(false);
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
			TCarInsurance insurance = service.getById(id);
			insurance.setDictIdFlag(WebMsgUtil.WUXIAO);
			insurance.setUserIdUpdate(sessionInfo.getUser().getUserId());
			insurance.setTimeUpdate(new Date());
			service.update(insurance);
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

	public void doNotNeedSecurity_getcarList() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
		hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ",sessionInfo.getOrganization().getOrgIdManager());
		hqlFilter.addFilter("QUERY_t#dictIdCarStatus_S_NE", WebMsgUtil.CARSTATUS_SEALED);
		hqlFilter.addFilter("QUERY_t#dictIdCarStatus_S_NE", WebMsgUtil.CARSTATUS_AUCTION);
		hqlFilter.addFilter("QUERY_t#dictIdCarStatus_S_NE", WebMsgUtil.CARSTATUS_SCRAP);
		String[] includes={"carId","carNo"};
		writeJsonByIncludesProperties(carservice.findByFilter(hqlFilter),includes);
	}
	public void doNotNeedSecurity_getMaxTime() {
		writeText(((CarInsuranceServiceI)service).getMaxTime(carId,typeId,id));
	}
	public TCarInsurance getInsurance() {
		return insurance;
	}

	public void setInsurance(TCarInsurance insurance) {
		this.insurance = insurance;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
}
