package com.car.action.car;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.car.entity.car.TCarStatus;
import com.car.entity.car.VCarStatus;
import com.car.service.car.ICarStatuService;
import com.car.service.car.ICarStatusViewService;
import com.framework.util.BeanUtils;
import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.framework.util.WebMsgUtil;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Grid;
import com.system.entity.easyui.Json;
import com.system.entity.maintain.SessionInfo;

@Namespace("/car")
@Action("carStatusEdit")
public class CarStatusEditAction extends BaseAction<TCarStatus> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7757372107446910486L;
	private TCarStatus carStatus;
	@Autowired
	private ICarStatusViewService carStatusViewService;
	@Autowired
	private ICarStatuService carStatuService;

	@Autowired
	public void setService(ICarStatuService service) {
		// TODO Auto-generated method stub
		super.setService(service);
	}

	public TCarStatus getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(TCarStatus carStatus) {
		this.carStatus = carStatus;
	}

	public void grid() {
		Grid<VCarStatus> grid = new Grid<VCarStatus>();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ",sessionInfo.getOrganization().getOrgIdManager());
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
		grid.setTotal(carStatusViewService.countByFilter(hqlFilter));
		grid.setRows(carStatusViewService.findByFilter(hqlFilter, page, rows));
		writeJson(grid);

	}

	/**
	 * 更新
	 */
	public void update() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());

		Json json = new Json();
		json.setMsg("更新失败！");
		try {
			if (carStatus != null && !StringUtils.isBlank(carStatus.getCarId())) {

				HqlFilter hqlFilter = new HqlFilter(getRequest());
				hqlFilter.addFilter("QUERY_t#carId_S_NE", carStatus.getCarId());
				hqlFilter.addFilter("QUERY_t#keyNo_S_EQ", carStatus.getKeyNo());

				TCarStatus tempStatus = carStatuService.getByFilter(hqlFilter);
				if (tempStatus != null) {
					json.setMsg("更新失败，keyNo已存在！");
				} else {
					HqlFilter hqlFilter2 = new HqlFilter(getRequest());
					hqlFilter2.addFilter("QUERY_t#carId_S_NE",	carStatus.getCarId());
					hqlFilter2.addFilter("QUERY_t#obdNo_S_EQ",	carStatus.getObdNo());

					TCarStatus tempStatus2 = carStatuService.getByFilter(hqlFilter2);
					if (tempStatus2 != null) {
						json.setMsg("更新失败，obdNo已存在！");
					}else {
						HqlFilter hqlFilter3 = new HqlFilter(getRequest());
						hqlFilter3.addFilter("QUERY_t#carId_S_NE",	carStatus.getCarId());
						hqlFilter3.addFilter("QUERY_t#simNo_S_EQ", carStatus.getSimNo());
						TCarStatus tempStatus3 = carStatuService.getByFilter(hqlFilter3);
						if (tempStatus3 != null) {
							json.setMsg("更新失败，simNo已存在！");
						} else {
	
							TCarStatus t = carStatuService.getById(carStatus
									.getCarId());
							BeanUtils.copyNotNullProperties(carStatus, t,
									new String[] { "dictIdCarStatus", "carMileage",
											"carOil" });
							t.setUserIdUpdate(sessionInfo.getUser().getUserId());
							t.setTimeUpdate(new Date());
							carStatuService.update(t);
							json.setSuccess(true);
							json.setMsg("更新成功！");
						}
					}
				}
			}
		} catch (Exception e) {
			json.setMsg("未知异常");
			e.printStackTrace();

		}
		writeJson(json);
	}

}
