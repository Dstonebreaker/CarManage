package com.car.action.car;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.car.entity.car.TCarApply;
import com.car.entity.car.VCarApply;
import com.car.service.car.ICarApplyService;
import com.car.service.car.ICarApplyViewService;
import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.framework.util.WebMsgUtil;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Grid;
import com.system.entity.easyui.Json;
import com.system.entity.maintain.SessionInfo;
import com.system.entity.maintain.SysUser;

@Namespace("/car")
@Action("apply")
public class CarApplyAction extends BaseAction<TCarApply> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4773541688346517845L;

	private TCarApply apply;

	@Autowired
	private ICarApplyViewService viewService;

	@Autowired
	public void setService(ICarApplyService service) {
		super.setService(service);
	}

	@Override
	public void grid() {
		Grid<VCarApply> grid = new Grid<VCarApply>();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		grid.setTotal(viewService.countByFilter(hqlFilter));
		grid.setRows(viewService.findByFilter(hqlFilter, page, rows));
		writeJson(grid);
	}

	public void save() {
		Json json = new Json();
		try {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			SysUser user = sessionInfo.getUser();
			apply.setApplyId(UUID.randomUUID().toString());
			apply.setOrgId(sessionInfo.getOrganization().getOrgId());
			apply.setApplyNo(getNumber("applyNo"));
			apply.setUserIdCreate(user.getUserId());
			apply.setTimeCreate(new Date());
			apply.setDictIdCheckStatus(WebMsgUtil.CHECK_STATUS_START);
			apply.setApplyType(WebMsgUtil.CARSENDTYPE_YD);
			((ICarApplyService) service).save(apply, WebMsgUtil.APPLYSENDSTATUS_SQZ);
			json.setMsg("保存成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("保存异常！");
			json.setSuccess(false);
			e.printStackTrace();
		}
		writeJson(json);
	}

	public void saveWD() {
		Json json = new Json();
		try {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			SysUser user = sessionInfo.getUser();
			apply.setApplyId(UUID.randomUUID().toString());
			//apply.setOrgId(sessionInfo.getOrganization().getOrgId());
			apply.setApplyNo(getNumber("applyNo"));
			apply.setUserIdCreate(user.getUserId());
			apply.setTimeCreate(new Date());
			apply.setDictIdCheckStatus(WebMsgUtil.CHECK_STATUS_START);
			apply.setApplyType(WebMsgUtil.CARSENDTYPE_WD);
			((ICarApplyService) service).save(apply, WebMsgUtil.APPLYSENDSTATUS_DPC);
			json.setMsg("保存成功！您的单号为：" + apply.getApplyNo() + "，请在下方列表中查找并进行派车！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("保存异常！");
			json.setSuccess(false);
			e.printStackTrace();
		}
		writeJson(json);
	}

	public void infoCollection() {
		Json json = new Json();
		try {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			SysUser user = sessionInfo.getUser();
			apply.setApplyId(UUID.randomUUID().toString());
			apply.setOrgId(sessionInfo.getOrganization().getOrgId());
			apply.setApplyNo(getNumber("applyNo"));
			apply.setUserIdCreate(user.getUserId());
			apply.setTimeCreate(new Date());
			apply.setDictIdCheckStatus(WebMsgUtil.CHECK_STATUS_START);
			apply.setApplyType(WebMsgUtil.CARSENDTYPE_JJ);
			((ICarApplyService) service).doInfoCollection(apply, id);
			json.setMsg("信息补录成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("信息补录异常！");
			json.setSuccess(false);
			e.printStackTrace();
		}
		writeJson(json);
	}

	public void update() {
		Json json = new Json();
		try {
			service.update(apply);
			json.setMsg("保存成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("保存异常！");
			json.setSuccess(false);
			e.printStackTrace();
		}
		writeJson(json);
	}

	public void delete() {
		Json json = new Json();
		try {
			((ICarApplyService) service).delete(id);
			json.setMsg("已删除！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("保存异常！");
			json.setSuccess(false);
			e.printStackTrace();
		}
		writeJson(json);
	}

	public void getByViewId() {
		try {
			if (!StringUtils.isBlank(id)) {
				writeJson(viewService.getById(id));
			} else {
				Json j = new Json();
				j.setMsg("主键不可为空！");
				writeJson(j);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void approval() {
		Json json = new Json();
		try {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			((ICarApplyService) service).doApproval(apply, sessionInfo);
			json.setMsg("审批成功！");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("审批异常！");
			json.setSuccess(false);
			e.printStackTrace();
		}
		writeJson(json);
	}

	public TCarApply getApply() {
		return apply;
	}

	public void setApply(TCarApply apply) {
		this.apply = apply;
	}

}
