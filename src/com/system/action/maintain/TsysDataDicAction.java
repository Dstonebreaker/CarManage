package com.system.action.maintain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.car.entity.car.VCarDictionaryRelation;
import com.framework.util.BeanUtils;
import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.framework.util.WebMsgUtil;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Grid;
import com.system.entity.easyui.Json;
import com.system.entity.easyui.Tree;
import com.system.entity.maintain.SessionInfo;
import com.system.entity.maintain.SysUser;
import com.system.entity.maintain.TsysDictionary;
import com.system.service.maintain.ITsysDicService;
import com.system.service.maintain.IVCarDictionaryRelationService;
import com.system.service.maintain.IVSysDictionaryService;

@Namespace("/maintain")
@Action(value = "dictionaryManage")
public class TsysDataDicAction extends BaseAction<TsysDictionary> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1494274309351024184L;
	private String dictClassId;
	private String dictId;

	private TsysDictionary datadic;
	private String profId;
	private String brandId;
	@Autowired
	private ITsysDicService  datadicService;
	@Autowired
	public IVCarDictionaryRelationService vCarDictionaryRelationService; 
	@Autowired
	public IVSysDictionaryService dictionaryService;
	@Autowired
	public void setService(ITsysDicService service) {
		this.service = service;
	}

	/**
	 * 获得字典数据grid
	 */
	public void grid() {
		try {

			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			Grid<?> grid = new Grid<Object>();
			HqlFilter hqlFilter = new HqlFilter(getRequest());
			String hqlQuery = "QUERY_t#dictFlag_S_EQ";
			hqlFilter.addFilter(hqlQuery, WebMsgUtil.YOUXIAO);
			if (!dictClassId.equals("")) {
				hqlFilter.addFilter("QUERY_t#dictClasId_S_EQ", dictClassId);
			}
			grid.setTotal(dictionaryService.countByFilter(hqlFilter));
			grid.setRows((List) dictionaryService.findByFilter(hqlFilter, page, rows));
			writeJson(grid);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 根据字典类型查询字典数据
	 */
	public void doNotNeedSecurity_getByClassId() {
		try {

			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			HqlFilter hqlFilter = new HqlFilter(getRequest());
			if(!dictClassId.equals("SpecialCar")){
				hqlFilter.addFilter("QUERY_t#dictClasId_S_EQ", dictClassId);
				hqlFilter.addFilter("QUERY_t#dictFlag_S_EQ", WebMsgUtil.YOUXIAO);
			}else{
				hqlFilter.addFilter("QUERY_t#dictClasId_S_EQ", dictClassId);
				hqlFilter.addFilter("QUERY_t#dictId_S_EQ_OR", WebMsgUtil.CAR_NOTSPECIAL);
				hqlFilter.addFilter("QUERY_t#dictFlag_S_EQ", WebMsgUtil.YOUXIAO);
				hqlFilter.addOrder("desc");
				hqlFilter.addSort("dictIndex");
			}
				
			List<TsysDictionary> list = ((ITsysDicService) datadicService).findByFilter(hqlFilter);
//			if(dictClassId.equals("SpecialCar")){
//				list.add(0,element);
//			}
			writeJson(list);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * 根据车辆品牌查询车系（实现联动）
	 */
	public void doNotNeedSecurity_getSeriesByBrand(){
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#dictId_S_EQ", brandId);
		hqlFilter.addFilter("QUERY_t#dictClasId_S_EQ","CarBrand");
		hqlFilter.addFilter("QUERY_t#dictCarBrandFlag_S_EQ", WebMsgUtil.YOUXIAO);
 		hqlFilter.addFilter("QUERY_t#dictCarSeriesFlag_S_EQ", WebMsgUtil.YOUXIAO);
		List<VCarDictionaryRelation> carDictionaryRelations = vCarDictionaryRelationService.findByFilter(hqlFilter);
		writeJson(carDictionaryRelations);
	}
	
	
	
	/**
	 * 查询点位和机房类型
	 */
	public void doNotNeedSecurity_getStockByClassId() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#dictClasId_S_EQ", dictClassId);
		hqlFilter.addFilter("QUERY_t#dictFlag_S_EQ", WebMsgUtil.YOUXIAO);
		hqlFilter.addFilter("QUERY_t#dictId_S_NE", "StockTypeStock");
		List<TsysDictionary> list = ((ITsysDicService) datadicService).findByFilter(hqlFilter);
		writeJson(list);
	}

	/**
	 * 逻辑删除
	 */
	public void delete() {
		Json json = new Json();
		String hql = "update TsysDictionary set dictFlag=:dictFlag where dictId=:dictId";
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("dictFlag", WebMsgUtil.WUXIAO);
		params.put("dictId", id);
		try {
			json.setSuccess(true);
			((ITsysDicService) datadicService).executeHql(hql, params);
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		writeJson(json);
	}

	public void doNotNeedSessionAndSecurity_findDataByType() {

		String hql = "select dictClasName from VsysOrgazition ";
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("dictFlag", WebMsgUtil.YOUXIAO);
		hql += " order by dictIndex asc";
		List<TsysDictionary> list = ((ITsysDicService) datadicService).find(hql, params);
		writeJson(list);

	}
	/**
	 * 无需权限查找所有对象
	 */
	/*public void doNotNeedSecurity_findAll() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		writeJson(service.findByFilter(hqlFilter));
	}*/
	/**
	 * 保存数据字典
	 */
	public void save() {
		Json json = new Json();
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());

		if (datadic != null) {
			HqlFilter hqlFilter = new HqlFilter();
			hqlFilter.addFilter("QUERY_t#dictName_S_EQ", datadic.getDictName());
			hqlFilter.addFilter("QUERY_t#dictClasId_S_EQ", datadic.getDictClasId());
			hqlFilter.addFilter("QUERY_t#dictFlag_S_EQ", WebMsgUtil.YOUXIAO);
			TsysDictionary sysDictionary = datadicService.getByFilter(hqlFilter);
			if (sysDictionary != null) {
				json.setMsg("新建字典失败，字典名已存在！");
			} else {
				HqlFilter hqlFilter2 = new HqlFilter();
				hqlFilter2.addFilter("QUERY_t#dictNo_S_EQ", datadic.getDictNo());
				hqlFilter2.addFilter("QUERY_t#dictClasId_S_EQ", datadic.getDictClasId());
				hqlFilter2.addFilter("QUERY_t#dictFlag_S_EQ", WebMsgUtil.YOUXIAO);
				TsysDictionary sysDictionary2 = datadicService.getByFilter(hqlFilter2);
				if (sysDictionary2 != null) {
					json.setMsg("新建字典失败，字典编号已存在！");
				} else {
					datadicService.saveDicData(datadic, sessionInfo.getUser().getUserId());
					json.setSuccess(true);
					json.setMsg("添加成功！");
				}
			}
			writeJson(json);
		}

	}
	/**
	 * 获得所有品牌
	 * 
	 * @return
	 */
     public  void doNotNeedSecurity_getCarBrand(){
 		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
 		HqlFilter hqlFilter = new HqlFilter();
		hqlFilter.addFilter("QUERY_t#dictClasId_S_EQ","CarBrand");
		hqlFilter.addFilter("QUERY_t#dictFlag_S_EQ", WebMsgUtil.YOUXIAO);
		List<TsysDictionary> sysDictionary =  datadicService.findByFilter(hqlFilter);
		List<Tree> tree = new ArrayList<Tree>();
		for (TsysDictionary dictionary : sysDictionary) {
			Tree node = new Tree();
			BeanUtils.copyNotNullProperties(dictionary, node);
			node.setText(dictionary.getDictName());
			node.setId(dictionary.getDictId());
			node.setIconCls("ext-icon-computer_link");
			node.setPid(null);
			tree.add(node);
		}
		writeJson(tree);
     }
     /**
 	 * 获得所有车系
 	 * 
 	 * @return
 	 */
      public  void doNotNeedSecurity_getCarSeriesById(){
    	  SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
    	  if(id == null || id.equals("")){
  		HqlFilter hqlFilter = new HqlFilter();
  		hqlFilter.addFilter("QUERY_t#dictClasId_S_EQ","CarSeries");
		hqlFilter.addFilter("QUERY_t#dictFlag_S_EQ", WebMsgUtil.YOUXIAO);
		List<TsysDictionary> sysDictionary =  datadicService.findByFilter(hqlFilter);
		List<VCarDictionaryRelation> carDictionaryRelations = new ArrayList<VCarDictionaryRelation>();
		           for(TsysDictionary dictionary:sysDictionary){
		        	   VCarDictionaryRelation  carDictionaryRelation = new VCarDictionaryRelation();  
		        	   carDictionaryRelation.setDictName(dictionary.getDictName());
		        	   carDictionaryRelation.setDictIdRelate(dictionary.getDictId());
		        	   carDictionaryRelation.setDictId("");
		        	   carDictionaryRelations.add(carDictionaryRelation);
		           }
 		Grid<VCarDictionaryRelation> grid = new Grid<VCarDictionaryRelation>();
 		grid.setTotal(datadicService.countByFilter(hqlFilter));
 		grid.setRows(carDictionaryRelations);
 			writeJson(grid );
    	  }else {
    		Grid<VCarDictionaryRelation> grid = new Grid<VCarDictionaryRelation>();
    	    HqlFilter hqlFilter = new HqlFilter(getRequest());
    		hqlFilter.addFilter("QUERY_t#dictId_S_EQ", id);
    		hqlFilter.addFilter("QUERY_t#dictClasId_S_EQ","CarBrand");
    		hqlFilter.addFilter("QUERY_t#dictCarBrandFlag_S_EQ", WebMsgUtil.YOUXIAO);
     		hqlFilter.addFilter("QUERY_t#dictCarSeriesFlag_S_EQ", WebMsgUtil.YOUXIAO);
    		grid.setTotal(vCarDictionaryRelationService.countByFilter(hqlFilter));
    		grid.setRows(vCarDictionaryRelationService.findByFilter(hqlFilter, page, rows));
    		writeJson(grid);
		}
 	
      }
	/**
	 * 更新数据字典
	 * 
	 * @return
	 */
	public void update() {

		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());

		Json json = new Json();
		json.setMsg("更新失败！");
		try {
			if (datadic != null && !StringUtils.isBlank(datadic.getDictId())) {
				HqlFilter hqlFilter = new HqlFilter();
				hqlFilter.addFilter("QUERY_t#dictId_S_NE", datadic.getDictId());
				hqlFilter.addFilter("QUERY_t#dictName_S_EQ", datadic.getDictName());
				hqlFilter.addFilter("QUERY_t#dictClasId_S_EQ", datadic.getDictClasId());
				hqlFilter.addFilter("QUERY_t#dictFlag_S_EQ", WebMsgUtil.YOUXIAO);
				TsysDictionary dictionary = datadicService.getByFilter(hqlFilter);
				if (dictionary != null) {
					json.setMsg("更新字典失败，字典名已存在！");
				} else {

					HqlFilter hqlFilter2 = new HqlFilter();
					hqlFilter2.addFilter("QUERY_t#dictId_S_NE", datadic.getDictId());
					hqlFilter2.addFilter("QUERY_t#dictClasId_S_EQ", datadic.getDictClasId());
					hqlFilter2.addFilter("QUERY_t#dictNo_S_EQ", datadic.getDictNo());
					hqlFilter2.addFilter("QUERY_t#dictFlag_S_EQ", WebMsgUtil.YOUXIAO);
					TsysDictionary sysDictionary2 = datadicService.getByFilter(hqlFilter2);
					if (sysDictionary2 != null) {
						json.setMsg("更新字典失败，字典编号已存在！");
					} else {

						SysUser sessionInfoUser = ((SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName())).getUser();
						TsysDictionary t = datadicService.getById(datadic.getDictId());
						BeanUtils.copyNotNullProperties(datadic, t, new String[] { "dictFlag", "userIdCreate", "userNameCreate",
								"dictTimeCreate" });
						t.setTimeUpdate(new Timestamp(new Date().getTime()));
						t.setUserIdUpdate(sessionInfoUser.getUserId());

						datadicService.update(t);
						json.setSuccess(true);
						json.setMsg("更新成功！");

					}
				}
			}
		} catch (Exception e) {
			json.setMsg("未知异常");
			json.setSuccess(false);
		}
		writeJson(json);

	}

	/**
	 * 获取所有项目树
	 */
	public void doNotNeedSecurity_getDictionariesTree() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		HqlFilter hqlFilter = new HqlFilter();
		hqlFilter.addFilter("QUERY_t#dictFlag_S_EQ", WebMsgUtil.YOUXIAO);
		hqlFilter.addFilter("QUERY_t#dictClasId_S_EQ", "ItemType");
		List<TsysDictionary> dictionaries = datadicService.findByFilter(hqlFilter);
		List<Tree> tree = new ArrayList<Tree>();
		for (TsysDictionary dictionary : dictionaries) {
			Tree node = new Tree();
			BeanUtils.copyNotNullProperties(dictionary, node);
			node.setText(dictionary.getDictName());
			node.setId(dictionary.getDictId());
			node.setIconCls("ext-icon-computer_link");
			node.setPid(null);
			tree.add(node);
		}
		writeJson(tree);
	}

	public String getDictClassId() {
		return dictClassId;
	}

	public void setDictClassId(String dictClassId) {
		this.dictClassId = dictClassId;
	}

	public TsysDictionary getDatadic() {
		return datadic;
	}

	public void setDatadic(TsysDictionary datadic) {
		this.datadic = datadic;
	}

	public String getProfId() {
		return profId;
	}

	public void setProfId(String profId) {
		this.profId = profId;
	}

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	
}
