package com.car.action.car;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.car.entity.car.VCarApplySendStatus;
import com.car.service.car.ICarApplySendStatusViewService;
import com.framework.util.HqlFilter;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Json;

@Namespace("/car")
@Action("sendStatus")
public class SendStatusAction extends BaseAction<VCarApplySendStatus> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8024730486773133141L;

	@Autowired
	public void setService(ICarApplySendStatusViewService service) {
		super.setService(service);
	}
	
	public void getBySendId() {
		if (!StringUtils.isBlank(id)) {
			HqlFilter hqlFilter = new HqlFilter();
			hqlFilter.addFilter("QUERY_t#sendId_S_EQ", id);
			writeJson(service.getByFilter(hqlFilter));
		} else {
			Json j = new Json();
			j.setMsg("主键不可为空！");
			writeJson(j);
		}
	}
	
	public void getByApplyId() {
		if (!StringUtils.isBlank(id)) {
			HqlFilter hqlFilter = new HqlFilter();
			hqlFilter.addFilter("QUERY_t#applyId_S_EQ", id);
			writeJson(service.getByFilter(hqlFilter));
		} else {
			Json j = new Json();
			j.setMsg("主键不可为空！");
			writeJson(j);
		}
	}

}
