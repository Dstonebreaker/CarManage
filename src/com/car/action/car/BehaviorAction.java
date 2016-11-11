package com.car.action.car;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.car.entity.obd.VDriverBehavior;
import com.car.service.car.IVDriverBehaviorService;
import com.framework.util.HqlFilter;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Grid;

@Namespace("/analysis")
@Action("behavior")
public class BehaviorAction extends BaseAction<VDriverBehavior> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5489163643533431191L;

	@Autowired
	public void setService(IVDriverBehaviorService service) {
		// TODO Auto-generated method stub
		super.setService(service);
	}


	

	public void grid() {
		Grid<VDriverBehavior> grid = new Grid<VDriverBehavior>();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addSort("obdTime");
		hqlFilter.addOrder("ASC");
		//grid.setTotal(((IVDriverBehaviorService) service).countByFilter(hqlFilter));
		grid.setRows(((IVDriverBehaviorService) service).findByFilter(hqlFilter));
		writeJson(grid);

	}

}
