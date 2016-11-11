package com.car.action.car;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.car.entity.car.TCarModel;
import com.car.entity.car.TCarModelMaintenance;
import com.car.entity.car.VCarModelMaintenance;
import com.car.service.car.ICarModelMaintenanceService;
import com.car.service.car.IVCarModelMaintenanceService;
import com.framework.util.BeanUtils;
import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.framework.util.WebMsgUtil;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Grid;
import com.system.entity.easyui.Json;
import com.system.entity.maintain.SessionInfo;

@Namespace("/car")
@Action("ModelMaintenance")
public class CarModelMaintenanceAction extends BaseAction<TCarModelMaintenance> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5528199332073156081L;
	private TCarModelMaintenance carModelMaintenance;

	public TCarModelMaintenance getCarModelMaintenance() {
		return carModelMaintenance;
	}

	public void setCarModelMaintenance(TCarModelMaintenance carModelMaintenance) {
		this.carModelMaintenance = carModelMaintenance;
	}

	@Autowired
	public ICarModelMaintenanceService carModelMaintenanceService;
	@Autowired
	public IVCarModelMaintenanceService vCarModelMaintenanceService;

	@Autowired
	public void setService(ICarModelMaintenanceService service) {
		// TODO Auto-generated method stub
		super.setService(service);
	}

	/**
	 * 获取
	 */
	public void grid() {
		Grid<VCarModelMaintenance> grid = new Grid<VCarModelMaintenance>();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
		grid.setTotal(vCarModelMaintenanceService.countByFilter(hqlFilter));
		grid.setRows(vCarModelMaintenanceService.findByFilter(hqlFilter, page,
				rows));
		writeJson(grid);

	}

	public void update() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		Json json = new Json();
		json.setMsg("更新失败！");
		try {
			if (carModelMaintenance != null
					&& !StringUtils.isBlank(carModelMaintenance.getMmainId())) {
				String id = carModelMaintenance.getMmainId();
				TCarModelMaintenance t = carModelMaintenanceService.getById(id);
				BeanUtils.copyNotNullProperties(carModelMaintenance, t,
						new String[] { "userIdCreate", "timeCreate",
								"dictIdFlag", });
				t.setTimeUpdate(new Date());
				t.setUserIdUpdate(sessionInfo.getUser().getUserId());
				carModelMaintenanceService.update(t);
				json.setSuccess(true);
				json.setMsg("更新成功！");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		writeJson(json);

	}

	public void delete() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		Json json = new Json();
		json.setMsg("删除失败！");
		try {
			TCarModelMaintenance t = service.getById(id);
			t.setDictIdFlag(WebMsgUtil.WUXIAO);
			carModelMaintenanceService.update(t);
			json.setSuccess(true);
			json.setMsg("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeJson(json);

	}

	public void save() {
		Json json = new Json();
		try {

			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
					ConfigUtil.getSessionInfoName());
			carModelMaintenance.setTimeCreate(new Date());
			carModelMaintenance.setDictIdFlag(WebMsgUtil.YOUXIAO);
			carModelMaintenance.setUserIdCreate(sessionInfo.getUser()
					.getUserId());
			carModelMaintenanceService.save(carModelMaintenance);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeJson(json);

	}
}
