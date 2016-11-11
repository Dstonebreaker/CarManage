package com.car.action.car;

import java.util.Date;
import java.util.UUID;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.car.entity.car.TCarReturn;
import com.car.service.car.ICarReturnService;
import com.framework.util.ConfigUtil;
import com.framework.util.WebMsgUtil;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Json;
import com.system.entity.maintain.SessionInfo;

@Namespace("/car")
@Action("ret")
public class CarReturnAction extends BaseAction<TCarReturn> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2193948486641893041L;
	
	private TCarReturn carRet;

	@Autowired
	public void setService(ICarReturnService service) {
		super.setService(service);
	}

	public void ret() {
		Json json = new Json();
		try {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			carRet.setDictIdFlag(WebMsgUtil.YOUXIAO);
			carRet.setReturnId(UUID.randomUUID().toString());
			carRet.setReturnTime(new Date());
			carRet.setTimeCreate(carRet.getReturnTime());
			carRet.setUserIdCreate(sessionInfo.getUser().getUserId());
			((ICarReturnService)service).doReturn(carRet);
			json.setMsg("车辆已成功归还！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("还车异常！");
			json.setSuccess(false);
			e.printStackTrace();
		}
		writeJson(json);
	}

	public TCarReturn getCarRet() {
		return carRet;
	}

	public void setCarRet(TCarReturn carRet) {
		this.carRet = carRet;
	}

}
