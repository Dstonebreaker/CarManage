package com.car.action.pgis;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.car.entity.car.TCar;
import com.car.entity.car.TCarSend;
import com.car.entity.pgis.VObdCarGpsForobddb;
import com.car.entity.pgis.VObdGpsLast;
import com.car.service.car.ICarSendService;
import com.car.service.car.ICarservice;
import com.car.service.pgis.VObdCarGpsForobddbServiceImpl;
import com.car.service.pgis.VObdGpsLastService;
import com.framework.util.HqlFilter;
import com.framework.util.WebMsgUtil;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Json;
import com.system.entity.maintain.SysOrganization;
import com.system.service.base.BaseServiceI;
import com.system.service.maintain.SysOrganizationServiceI;


@Namespace("/pgis")
@Action("carManage")
public class pgis_CarAction extends BaseAction<VObdGpsLast> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1719681500488350493L;

	@Autowired
	ICarservice carservice;
	@Autowired
	VObdGpsLastService obdLastService;
	@Autowired
	SysOrganizationServiceI orgService;
	@Autowired
	BaseServiceI<VObdGpsLast> baseService;
	
	//用于查询出车时间，以查询轨迹
	@Autowired
	ICarSendService sendService;
	@Autowired
	VObdCarGpsForobddbServiceImpl trailService;
	
	private String orgId;
	private String carId;
	
	private Double ALong;
	private Double ALat;
	private Double BLong;
	private Double BLat;
	
	private String startTime = null;		//yyyy-MM-dd，用于轨迹
	private String endTime = null;
	
	private Integer zoom;
	
	
	
	public void doNotNeedSecurity_getcarList() {
		

		HqlFilter hqlFilter = new HqlFilter(getRequest());
		//hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
		//writeJson(carservice.findByFilter(hqlFilter));
		
		if(orgId != null && orgId.length() != 0){
			SysOrganization organization = orgService.getById(orgId);
			
			hqlFilter.addFilter("QUERY_t#orgRealPath_S_RLK", organization.getOrgPath());
		}
		//车辆状态是否是出车状态，暂时注释掉，
		hqlFilter.addFilter("QUERY_t#dictIdCarStatus_S_EQ", WebMsgUtil.CARSTATUS_OUT);
		//添加是否保密
		hqlFilter.addFilter("QUERY_t#dictIdIsSecret_S_NE", "IsSecretYes");
		//根据经纬度加载范围内的车辆
		//加0.01的作用是加载比当前窗口范围跟大的地图，原因是当小幅度滑动地图时，可以看到刚刚加载进来的地图上的车
		if(ALong != null){
			hqlFilter.addFilter("QUERY_t#obdGpsLongitude_DB_GT", (ALong-0.01)+"");
			hqlFilter.addFilter("QUERY_t#obdGpsLongitude_DB_LT", (BLong+0.01)+"");
			hqlFilter.addFilter("QUERY_t#obdGpsLatitude_DB_GT", (ALat-0.01)+"");
			hqlFilter.addFilter("QUERY_t#obdGpsLatitude_DB_LT", (BLat+0.01)+"");
		}
		
		if(zoom != null && zoom <= 13 && zoom >= 9){
//			select s.carLong as carLong, s.carLat as carLat, count(*) as amount from (select * from t_gis_polymerize where isStandard=1) s,t_gis_polymerize d,
//			(select polyId from t_gis_polymerize_standard t where t.polyScale>'10' #屏幕上的zoom值替换10
//			order by polyScale LIMIT 1) f
//			where s.polyId=f.polyId and s.polyId = d.polyId and s.groupIds=d.groupIds and 
//			d.carLong>'0'  #屏幕上A角经度
//			and d.carLong<'180' #屏幕上B角经度
//			and d.carLat>'0'	#屏幕上A角纬度 
//			and d.carLat <'180' #屏幕上B角纬度 
//			and s.orgRealPath like '%'	#session中orgPath路径值填充进去
//			group by s.carId, s.carLong, s.carLat
			
			
			 String sql = "select s.carLong as carLong, s.carLat as carLat, count(*) as amount ";
					sql += "from (select * from t_gis_polymerize where isStandard=1) s,t_gis_polymerize d,";
					sql += "(select polyId from t_gis_polymerize_standard t where t.polyScale>:zoom order by polyScale LIMIT 1) f";
					sql += "where s.polyId=f.polyId and s.polyId = d.polyId and s.groupIds=d.groupIds ";
					sql += "and d.carLong>:aLong";
					sql += "and d.carLong<:bLong";
					sql += "and d.carLat>:aLat";
					sql += "and d.carLat<:bLat";
					sql += "and s.orgRealPath like :orgRealPath";
					sql += "group by s.carId, s.carLong, s.carLat";
					
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("zoom", zoom);
			params.put("aLong", ALong);
			params.put("bLong", BLong);
			params.put("aLat", ALat);
			params.put("bLat", BLat);
			params.put("orgRealPath", orgService.getById(orgId).getOrgPath() + "%%");
			
			writeJson(baseService.findBySql(sql, params));
			
			return;
		}
		
		writeJson(obdLastService.findByFilter(hqlFilter));
		
	}
	
	public void doNotNeedSecurity_getTrail(){
		Json json = new Json();

		if (null == startTime || startTime.length() == 0) {
			String hql = "select max(sendTime) where carId = :carId";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("carId", carId);
			List<String> list = sendService.findBySql(hql, params);
			startTime = list.get(0);
		}
		
		HqlFilter hql = new HqlFilter();
		hql.addFilter("QUERY_t#carId_S_EQ", carId);
		hql.addFilter("QUERY_t#obdTime_D_GT",startTime);
		if (null != endTime && endTime.length() > 0) {
			hql.addFilter("QUERY_t#obdTime_D_LT",endTime);
		}
		
		List<VObdCarGpsForobddb> list = trailService.findByFilter(hql);
		
		String result = null;
		
		//这个值要在数据库获取，目前先模拟
		int distancedot = 1;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getGpsIndex()%distancedot==0) {
				result+=list.get(i).getGpsLon()+","+list.get(i).getGpsLat()+",";
			}
		}
		
		json.setObj(result);
		json.setSuccess(true);
		
		writeJson(json);
	}
	
	
	
	public void doNotNeedSecurity_getcarInfo() {
		Json json = new Json();
		if(carId != null && carId.length() != 0){
			try {
				json.setObj(obdLastService.getById(carId));
				json.setSuccess(true);
			} catch (Exception e) {
				json.setMsg("服务端出错,请重试");
			}
		}else{
			json.setMsg("carId不能为空");
		}
		writeJson(json);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Double getALong() {
		return ALong;
	}
	public void setALong(Double aLong) {
		ALong = aLong;
	}
	public Double getALat() {
		return ALat;
	}
	public void setALat(Double aLat) {
		ALat = aLat;
	}
	public Double getBLong() {
		return BLong;
	}
	public void setBLong(Double bLong) {
		BLong = bLong;
	}
	public Double getBLat() {
		return BLat;
	}
	public void setBLat(Double bLat) {
		BLat = bLat;
	}


	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}
