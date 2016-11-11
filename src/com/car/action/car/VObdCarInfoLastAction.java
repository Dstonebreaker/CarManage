package com.car.action.car;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.car.entity.car.VObdCarInfoLast;
import com.car.service.car.IVObdCarInfoLastService;
import com.framework.util.HqlFilter;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Grid;
import com.system.entity.easyui.Json;
@Namespace("/car")
@Action("carInfoLast")
public class VObdCarInfoLastAction extends BaseAction<VObdCarInfoLast> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2785790580452838028L;
	
	
	private VObdCarInfoLast carInfo;
	@Autowired
	private IVObdCarInfoLastService voCarInfoLastService;

	@Autowired
	public void setService(IVObdCarInfoLastService service) {
		super.setService(service);
	}
	
	public VObdCarInfoLast getCarInfo() {
		return carInfo;
	}

	public void setCarInfo(VObdCarInfoLast carInfo) {
		this.carInfo = carInfo;
	}

	public void grid(){
		
		Grid<VObdCarInfoLast> grid = new Grid<VObdCarInfoLast>();
		HqlFilter hqlFilter = new HqlFilter(getRequest());		
		grid.setTotal(voCarInfoLastService.countByFilter(hqlFilter));
		grid.setRows(voCarInfoLastService.findByFilter(hqlFilter, page, rows));
		writeJson(grid);
		
	}
	public void getById(){
		
		if (!StringUtils.isBlank(id)) {
			HqlFilter hqlFilter = new HqlFilter(getRequest());
			hqlFilter.addFilter("QUERY_t#obdNo_S_EQ",id);
			VObdCarInfoLast  temp= voCarInfoLastService.getByFilter(hqlFilter);
			writeJson(temp);
		} else {
			Json j = new Json();
			j.setMsg("主键不可为空！");
			writeJson(j);
		}
	}
	

}
