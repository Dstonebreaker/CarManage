package com.car.action.car;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.car.entity.car.TCarModel;
import com.car.entity.car.VCarModel;
import com.car.service.car.ICarModelService;
import com.car.service.car.IVCarModelService;
import com.framework.util.BeanUtils;
import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.framework.util.WebMsgUtil;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Grid;
import com.system.entity.easyui.Json;
import com.system.entity.maintain.SessionInfo;

@Namespace("/car")
@Action("Model")
public class CarModelAction extends BaseAction<TCarModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6885146273959031819L;
	private TCarModel carModel;
	private String brandId;
	@Autowired
	public ICarModelService carModelService;
	@Autowired
	public IVCarModelService vCarModelService; 

	@Autowired
	public void setService(ICarModelService service) {
		// TODO Auto-generated method stub
		super.setService(service);
	}

	public void grid() {
		Grid<VCarModel> grid = new Grid<VCarModel>();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ",WebMsgUtil.YOUXIAO);
		grid.setTotal(vCarModelService.countByFilter(hqlFilter));
		grid.setRows(vCarModelService.findByFilter(hqlFilter, page, rows));
		writeJson(grid);

	}

	/**
	 * 新建车型库
	 * 
	 */
	public void save() {
		Json json = new Json();
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());

		try {
			if (carModel != null) {
				HqlFilter hqlFilter = new HqlFilter(getRequest());
				hqlFilter.addFilter("QUERY_t#modelName_S_EQ",
						carModel.getModelName());
				hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
				TCarModel tCarModel = carModelService.getByFilter(hqlFilter);
				if (tCarModel != null) {
					json.setMsg("新建车型库失败，名字已存在！");
				} else {
					carModel.setModelId(UUID.randomUUID().toString());
					carModel.setTimeCreate(new Date());
					carModel.setDictIdFlag(WebMsgUtil.YOUXIAO);
					carModel.setUserIdCreate(sessionInfo.getUser().getUserId());
					carModelService.save(carModel);
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
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(
				ConfigUtil.getSessionInfoName());

		Json json = new Json();
		json.setMsg("更新失败！");
		try {
			if (carModel != null && !StringUtils.isBlank(carModel.getModelId())) {

				HqlFilter hqlFilter = new HqlFilter(getRequest());
				hqlFilter.addFilter("QUERY_t#modelId_S_NE",
						carModel.getModelId());
				hqlFilter.addFilter("QUERY_t#modelName_S_EQ",
						carModel.getModelName());
			
				TCarModel model = carModelService.getByFilter(hqlFilter);
				if (model != null) {
					json.setMsg("更新车型失败，车型已存在！");
				} else {
					TCarModel t = carModelService.getById(carModel.getModelId());
					BeanUtils.copyNotNullProperties(carModel, t, new String[] {	"userIdCreate", "timeCreate", "dictIdFlag", });
					t.setUserIdUpdate(sessionInfo.getUser().getUserId());
					t.setTimeUpdate(new Date());
					carModelService.update(t);
					json.setSuccess(true);
					json.setMsg("更新成功！");

				}
			}
		} catch (Exception e) {
			json.setMsg("未知异常");
			e.printStackTrace();

		}
		writeJson(json);
	}


	/**
	 * 逻辑删除车型
	 */
	public void delete() {
		Json json = new Json();
		try {
			TCarModel model = service.getById(id);
			model.setDictIdFlag(WebMsgUtil.WUXIAO);
			carModelService.update(model);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		writeJson(json);
	}
	
	public  void doNotNeedSecurity_getCarMode(){
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);
	    List<TCarModel>	 model = carModelService.findByFilter(hqlFilter);
		writeJson(model);
	}
	
	
	public void doNotNeedSessionAndSecurity_getAllModel() {		
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);		
		writeJson(carModelService.findByFilter(hqlFilter));

	}
	
	public void doNotNeedSessionAndSecurity_getModelByBrandId() {		
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#dictIdBrand_S_EQ",brandId);		
		hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", WebMsgUtil.YOUXIAO);		
		writeJson(carModelService.findByFilter(hqlFilter));

	}

	public TCarModel getCarModel() {
		return carModel;
	}

	public void setCarModel(TCarModel carModel) {
		this.carModel = carModel;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	
}
