package com.car.action.car;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.car.entity.car.TCar;
import com.car.entity.car.TCarTransform;
import com.car.entity.car.VCarTransform;
import com.car.service.car.ICarTransformService;
import com.car.service.car.ICarservice;
import com.car.service.car.IVCarTransformService;
import com.framework.util.BeanUtils;
import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.framework.util.WebMsgUtil;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Grid;
import com.system.entity.easyui.Json;
import com.system.entity.maintain.SessionInfo;

@Namespace("/car")
@Action("carTransform")
public class CarTransformAction extends BaseAction<TCarTransform> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4787189588465184782L;
	@Autowired
	public void setService(ICarTransformService service) {
		// TODO Auto-generated method stub
		super.setService(service);
	}
	@Autowired
	private ICarTransformService carTransformService;
	@Autowired
	private IVCarTransformService vCarTransformService;
	@Autowired
	private ICarservice carservice;
	private TCarTransform carTransform;
	
	public TCarTransform getCarTransform() {
		return carTransform;
	}


	public void setCarTransform(TCarTransform carTransform) {
		this.carTransform = carTransform;
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

	public void grid() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		Grid<VCarTransform> grid = new Grid<VCarTransform>();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
	
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
		grid.setTotal(vCarTransformService.countByFilter(hqlFilter));
		grid.setRows(vCarTransformService.findByFilter(hqlFilter, page, rows));
		writeJson(grid);

	}
	
	
	/**
	 * 保存记录
	 */
	public void save() {
		Json json = new Json();
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());

		try {
			if (carTransform != null) {
				HqlFilter hqlFilter = new HqlFilter(getRequest());
				hqlFilter.addFilter("QUERY_t#carId_S_EQ", carTransform.getCarId());
				hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ",
						WebMsgUtil.YOUXIAO);
				TCarTransform carTransformTemp = carTransformService.getByFilter(hqlFilter);
				if (carTransformTemp != null) {
					json.setMsg("登记失败，该车牌号已登记！");
				} else {
					TCar car = carservice.getById(carTransform.getCarId());
					carTransform.setCarNo(car.getCarNo());
					carTransform.setTransformNo(Getnum());//编号
					carTransform.setTransformId(UUID.randomUUID().toString());//ID
					carTransform.setTimeCreate(new Date() );//创建时间
					carTransform.setUserIdCreate(sessionInfo.getUser().getUserId());//创建人
					carTransform.setDictIdFlag(WebMsgUtil.YOUXIAO);//Flag标志位
					carTransformService.save(carTransform);
					json.setSuccess(true);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("未知异常");
			json.setSuccess(false);

		}

		writeJson(json);
	}
	
	/**
	 * 更新
	 */
	public void update() {
		Json json = new Json();
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());
		json.setMsg("更新失败！");
		try {
			if (carTransform != null && !StringUtils.isBlank(carTransform.getTransformId())) {

				HqlFilter hqlFilter = new HqlFilter(getRequest());
				hqlFilter.addFilter("QUERY_t#transformId_S_NE",
						carTransform.getTransformId());
				hqlFilter.addFilter("QUERY_t#carId_S_EQ", carTransform.getCarId());
				hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ",
						WebMsgUtil.YOUXIAO);
				TCarTransform  carTransformTemp = carTransformService.getByFilter(hqlFilter);
				if (carTransformTemp != null) {
					json.setMsg("更新失败，车牌号已存在！");
				} else {

					TCarTransform t = carTransformService.getById(carTransform.getTransformId());
					BeanUtils.copyNotNullProperties(carTransform, t, new String[] {
							 "dictIdFlag", "timeCreate", "userIdCreate","transformNo" });
					TCar car = carservice.getById(carTransform.getCarId());
					t.setTimeUpdate(new Date());//修改时间
					t.setUserIdUpdate(sessionInfo.getUser().getUserId());//修改人
					t.setCarNo(car.getCarNo());
					carTransformService.update(t);
					json.setSuccess(true);
					json.setMsg("更新成功！");

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("未知异常");
			json.setSuccess(false);

		}
		writeJson(json);
	}


	/**
	 * 删除
	 * 
	 * @return
	 */
	public void delete() {
		Json json = new Json();
		try {
			TCarTransform  carTransform = service.getById(id);
			carTransform.setDictIdFlag(WebMsgUtil.WUXIAO);
			carTransformService.update(carTransform);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		writeJson(json);
	}
}
