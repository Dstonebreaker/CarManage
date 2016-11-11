package com.car.action.car;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.car.entity.car.TCarSend;
import com.car.entity.car.VCarStatus;
import com.car.service.car.ICarSendService;
import com.car.service.car.ICarStatusViewService;
import com.framework.util.WebMsgUtil;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Grid;
import com.system.entity.maintain.SysOrganization;
import com.system.service.maintain.SysOrganizationServiceI;

@Namespace("/car")
@Action("vstatus")
public class CarStatusAction extends BaseAction<VCarStatus> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5192529835739841752L;

	private String orgId;
	private String dictIdCarType;
	private String dictIdCarStatus;
	private String carId;
	private String anyNo;
	private String carPoliceUsed;

	@Autowired
	private SysOrganizationServiceI orgService;
	
	@Autowired
	private ICarSendService sendService;

	@Autowired
	public void setService(ICarStatusViewService service) {
		super.setService(service);
	}

	public void gridByManager() {
		Grid grid = new Grid();
		SysOrganization org = orgService.getById(orgId);
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = "from VCarStatus t where ((orgIdManager=:orgId and orgIdUse is null) or orgIdUse=:orgIdUse) and dictIdFlag=:flag and dictIdCarStatus=:status and (carNo like :carNo or cardId=:cardId)";
		params.put("orgId", org.getOrgIdManager());
		params.put("orgIdUse", orgId);
		params.put("flag", WebMsgUtil.YOUXIAO);
		params.put("status", WebMsgUtil.CARSTATUS_FREE);
		if (!StringUtils.isBlank(dictIdCarType)) {
			hql += " and dictIdCarType=:type";
			params.put("type", dictIdCarType);
		}
		if (!StringUtils.isBlank(carPoliceUsed)) {
			hql += " and carPoliceUsed=:carPoliceUsed";
			params.put("carPoliceUsed", carPoliceUsed);
		}
		params.put("carNo", "%" + (anyNo == null ? "" : anyNo) + "%");
		params.put("cardId", anyNo == null ? "" : anyNo);
		grid.setTotal(service.count("select count(distinct t) " + hql, params));
		grid.setRows(service.find(hql, params, page, rows));
		writeJson(grid);
	}

	/*
	 * if (result.carId == undefined || result.carId == null || result.carId ==
	 * '') { url = sys.contextPath + '/car/vstatus!gridByManager.cxl'; params =
	 * { 'orgId' : result.orgId, 'dictIdCarType' : result.dictIdCarType }; }
	 * else { url = sys.contextPath + '/car/vstatus!grid.cxl'; params = {
	 * 'QUERY_t#dictIdCarStatus_S_EQ' : '<%=WebMsgUtil.CARSTATUS_FREE %>',
	 * 'QUERY_t#carId_S_EQ' : result.carId }; } 'QUERY_t#carNo_S_LK' :
	 * $('#anyNo').textbox('getValue'), 'QUERY_t#keyNo_S_EQ_OR' :
	 * $('#anyNo').textbox('getValue')
	 */

	public void gridForSend() {
		Grid grid = new Grid();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = "from VCarStatus t where 1=1 and dictIdFlag=:dictIdFlag and (carNo like :carNo or keyNo=:keyNo)";

		params.put("dictIdFlag", WebMsgUtil.YOUXIAO);
		params.put("carNo", "%" + (anyNo == null ? "" : anyNo) + "%");
		params.put("keyNo", anyNo == null ? "" : anyNo);
		if (!StringUtils.isBlank(dictIdCarStatus)) {
			hql += " and dictIdCarStatus=:dictIdCarStatus";
			params.put("dictIdCarStatus", dictIdCarStatus);
		}
		if (!StringUtils.isBlank(carId)) {
			hql += " and carId=:carId";
			params.put("carId", carId);
		}
		if (!StringUtils.isBlank(carPoliceUsed)) {
			hql += " and carPoliceUsed=:carPoliceUsed";
			params.put("carPoliceUsed", carPoliceUsed);
		}

		grid.setTotal(service.count("select count(distinct t) " + hql, params));
		grid.setRows(service.find(hql, params, page, rows));
		writeJson(grid);
	}

	public void getBySendId() {
		try {
			TCarSend send = sendService.getById(id);
			VCarStatus car = service.getById(send.getCarId());
			writeJson(car);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getDictIdCarType() {
		return dictIdCarType;
	}

	public void setDictIdCarType(String dictIdCarType) {
		this.dictIdCarType = dictIdCarType;
	}

	public String getDictIdCarStatus() {
		return dictIdCarStatus;
	}

	public void setDictIdCarStatus(String dictIdCarStatus) {
		this.dictIdCarStatus = dictIdCarStatus;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getAnyNo() {
		return anyNo;
	}

	public void setAnyNo(String anyNo) {
		this.anyNo = anyNo;
	}

	public String getCarPoliceUsed() {
		return carPoliceUsed;
	}

	public void setCarPoliceUsed(String carPoliceUsed) {
		this.carPoliceUsed = carPoliceUsed;
	}

}
