package com.car.action.car;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.car.entity.car.TCar;
import com.car.entity.car.TCarDeploy;
import com.car.entity.car.VCarDeploy;
import com.car.service.car.CarDeployService;
import com.car.service.car.ICarservice;
import com.car.service.car.IVCarDeployService;
import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.framework.util.WebMsgUtil;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Json;
import com.system.entity.maintain.SessionInfo;

@Namespace("/car")
@Action("deploy")
public class CarDeployAction extends BaseAction<TCarDeploy> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1327642662226431209L;
	private String datastr;
	private String orgIdManager;
	private String dictIdUserType;
	private String dictIdUse;
	@Autowired
	private ICarservice carservice;
	@Autowired
	private CarDeployService carDeployService;
	@Autowired
	private IVCarDeployService ivCarDeployService;

	@Autowired
	public void setService(CarDeployService service) {
		super.setService(service);
	}

	public void save() {
		Json json = new Json();
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		if (datastr != null) {
			datastr = datastr.replace("&quot;", "\"");
		}
		JSONArray jsonArray = JSON.parseArray(datastr);
		String carId = "";
		for (int index = 0; index < jsonArray.size(); index++) {
			String jstr = jsonArray.getString(index);
			JSONObject obj = JSONObject.parseObject(jstr);
			if (index == jsonArray.size() - 1) {
				carId = carId + "'" + obj.get("carId").toString() + "'";
			} else {
				carId = carId + "'" + obj.get("carId").toString() + "',";
			}
		}
		String hql1 = "";
		if (carId != "") {
			hql1 = "from TCarDeploy where orgId='" + orgIdManager
					+ "'and dictIdUse='" + dictIdUse + "'and dictIdUserType='"
					+ dictIdUserType + "' and carId not in(" + carId + ")";
		} else {
			hql1 = "from TCarDeploy where orgId='" + orgIdManager
					+ "'and dictIdUse='" + dictIdUse + "'and dictIdUserType='"
					+ dictIdUserType + "'";
		}
		List<TCarDeploy> list = carDeployService.find(hql1); // 所有将要被删除的定编车辆
		if (list != null) {
			for (TCarDeploy cars : list) {
				TCar cartemp = carservice.getById(cars.getCarId());
				cartemp.setIsDeploy(0);// 将定编状态变成0
				carservice.update(cartemp);
			}
		}
		String hql = "";
		if (carId != "") {
			hql = "delete from TCarDeploy where orgId='" + orgIdManager
					+ "'and dictIdUse='" + dictIdUse + "'and dictIdUserType='"
					+ dictIdUserType + "' and carId not in(" + carId + ")";
		} else {
			hql = "delete from TCarDeploy where orgId='" + orgIdManager
					+ "'and dictIdUse='" + dictIdUse + "'and dictIdUserType='"
					+ dictIdUserType + "'";
		}
		carDeployService.executeHql(hql);// 已结定编过，这次定编未勾选的车辆删除
		// if (jsonArray != null) {
		for (int index = 0; index < jsonArray.size(); index++) {
			String jstr = jsonArray.getString(index);
			JSONObject obj = JSONObject.parseObject(jstr);
			if (!obj.isEmpty()) {
				TCarDeploy carDeploy = new TCarDeploy();
				carDeploy.setDeployNo(Getnum());
				carDeploy.setOrgId(orgIdManager);
				carDeploy.setDeployTime(new Date());
				carDeploy.setCarId(obj.get("carId").toString());
				carDeploy.setDictIdUse(dictIdUse);
				carDeploy.setDictIdUserType(dictIdUserType);
				carDeploy.setTimeCreate(new Date());
				carDeploy.setUserIdCreate(sessionInfo.getUser().getUserId());
				HqlFilter hqlFilter = new HqlFilter();
				hqlFilter.addFilter("QUERY_t#carId_S_EQ", carDeploy.getCarId());
				TCarDeploy deploy = carDeployService.getByFilter(hqlFilter);
				if (deploy == null) {
					carDeployService.save(carDeploy);
					TCar cartemp = carservice.getById(carDeploy.getCarId());
					cartemp.setIsDeploy(1);
					carservice.update(cartemp);// 这次定编的车辆同步到tcar库
				}
			}
		}
		// }
		json.setMsg("定编成功！");
		json.setSuccess(true);
		writeJson(json);
	}

	/**
	 * 根据产权单获得车列表
	 */
	public void doNotNeedSecurity_getOrgcarList() {
		if (orgIdManager != null) {
			HqlFilter hqlFilter = new HqlFilter(getRequest());
			hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ", orgIdManager);
    		hqlFilter.addFilter("QUERY_t#dictIdCarStatus_S_NE", WebMsgUtil.CARSTATUS_SEALED);
			hqlFilter.addFilter("QUERY_t#dictIdCarStatus_S_NE", WebMsgUtil.CARSTATUS_AUCTION);
			hqlFilter.addFilter("QUERY_t#dictIdCarStatus_S_NE", WebMsgUtil.CARSTATUS_SCRAP);
			if (!(dictIdUserType == null || "".equals(dictIdUserType))) {
			hqlFilter.addFilter("QUERY_t#dictIdUserType_S_EQ", dictIdUserType);
			}
			if (!(dictIdUse == null || "".equals(dictIdUse))) {
			hqlFilter.addFilter("QUERY_t#dictIdUse_S_EQ", dictIdUse);
			}
			hqlFilter.addFilter("QUERY_t#deployId_S_NOTNULL");			
			hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
			List<VCarDeploy> list = new ArrayList<VCarDeploy>();
			List<VCarDeploy> carDeploys = ivCarDeployService
					.findByFilter(hqlFilter);
			if (carDeploys != null) {
				for (VCarDeploy vCarDeploy : carDeploys) {
//					if (dictIdUse!=null&&dictIdUse.equals(vCarDeploy.getDictIdUse())&&dictIdUserType==null) {						
//							vCarDeploy.setChecked(true);	
//					}
//					if (dictIdUse==null&&dictIdUserType!=null&&dictIdUserType.equals(vCarDeploy.getDictIdUserType())) {
//						vCarDeploy.setChecked(true);
//					}
//			 		if (dictIdUserType!=null&&dictIdUserType.equals(vCarDeploy.getDictIdUserType())) {
//			         	if (dictIdUserType!=null&&dictIdUserType.equals(vCarDeploy.getDictIdUserType())) {
//						   vCarDeploy.setChecked(true);
//			         	}
//					}
//			 		if (dictIdUse==null&&dictIdUserType==null) {
//						vCarDeploy.setChecked(true);
//					}
					vCarDeploy.setChecked(true);
					list.add(vCarDeploy);
				}
			}
			
			String hql = " from VCarDeploy where orgIdManager='" + orgIdManager
					+ "'and dictIdUserType is null and dictIdFlag='YX' and(dictIdCarStatus='" +WebMsgUtil.CARSTATUS_FREE+"' or dictIdCarStatus='" +WebMsgUtil.CARSTATUS_OUT+"' )";
			List<VCarDeploy> list1 = ivCarDeployService.find(hql);
			list.addAll(list1);
			writeJson(list);
		} else {
			HqlFilter hqlFilter = new HqlFilter(getRequest());
			hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ", id);
			hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
			hqlFilter.addFilter("QUERY_t#dictIdCarStatus_S_NE", WebMsgUtil.CARSTATUS_SEALED);
			hqlFilter.addFilter("QUERY_t#dictIdCarStatus_S_NE", WebMsgUtil.CARSTATUS_AUCTION);
			hqlFilter.addFilter("QUERY_t#dictIdCarStatus_S_NE", WebMsgUtil.CARSTATUS_SCRAP);
			List<VCarDeploy> carDeploys = ivCarDeployService
					.findByFilter(hqlFilter);
//			List<VCarDeploy> list = new ArrayList<VCarDeploy>();
//			if (carDeploys != null) {
//				for (VCarDeploy vCarDeploy : carDeploys) {
//					if (vCarDeploy.getDeployId() != null) {
//						vCarDeploy.setChecked(true);
//					}
//					list.add(vCarDeploy);
//				}
//			}
			writeJson(carDeploys);
		}
	}
	public String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 由年月日时分秒+3位随机数 生成流水号
	 * 
	 * @return
	 */
	public String Getnum() {
		String t = getStringDate();
		int x = (int) (Math.random() * 900) + 100;
		String serial = t + x;
		return serial;
	}

	public String getDatastr() {
		return datastr;
	}

	public void setDatastr(String datastr) {
		this.datastr = datastr;
	}

	public String getOrgIdManager() {
		return orgIdManager;
	}

	public void setOrgIdManager(String orgIdManager) {
		this.orgIdManager = orgIdManager;
	}

	public String getDictIdUserType() {
		return dictIdUserType;
	}

	public void setDictIdUserType(String dictIdUserType) {
		this.dictIdUserType = dictIdUserType;
	}

	public String getDictIdUse() {
		return dictIdUse;
	}

	public void setDictIdUse(String dictIdUse) {
		this.dictIdUse = dictIdUse;
	}
}
