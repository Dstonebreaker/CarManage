package com.system.action.maintain;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.car.entity.car.VCarDictionaryRelation;
import com.framework.util.HqlFilter;
import com.framework.util.WebMsgUtil;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Json;
import com.system.entity.maintain.SysDictionaryRelation;
import com.system.service.maintain.ISysDictionaryRelationService;
import com.system.service.maintain.IVCarDictionaryRelationService;

@Namespace("/maintain")
@Action(value = "dictionaryRelation")
public class SysDictionaryRelationAction extends BaseAction<SysDictionaryRelation>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1063975880066455651L;
	private   SysDictionaryRelation  sysDictionaryRelation;
	private   String dictId;
	private   String dictIdRelate;
	public SysDictionaryRelation getSysDictionaryRelation() {
		return sysDictionaryRelation;
	}
	public void setSysDictionaryRelation(SysDictionaryRelation sysDictionaryRelation) {
		this.sysDictionaryRelation = sysDictionaryRelation;
	}
	
	public String getDictId() {
		return dictId;
	}
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	public String getDictIdRelate() {
		return dictIdRelate;
	}
	public void setDictIdRelate(String dictIdRelate) {
		this.dictIdRelate = dictIdRelate;
	}

	@Autowired
	public IVCarDictionaryRelationService iVCarDictionaryRelationService;
	@Autowired
	public ISysDictionaryRelationService  iSysDictionaryRelationService;
	
	public void getById(){
		if((id!=null||("").equals(id))&&(ids!=null||("").equals(ids))){
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#dictIdRelate_S_EQ", ids);
		hqlFilter.addFilter("QUERY_t#dictId_S_EQ", id);
		VCarDictionaryRelation  vCarDictionaryRelation  =	iVCarDictionaryRelationService.getByFilter(hqlFilter);
		writeJson(vCarDictionaryRelation);
		}
	}
	/**
	 * 无需权限查找所有对象
	 */
	public void doNotNeedSecurity_findAll() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		writeJson(service.findByFilter(hqlFilter));
	}
   public  void save(){
	   Json json = new Json();
	   if(sysDictionaryRelation!=null){
		   HqlFilter hqlFilter = new HqlFilter(getRequest());
			hqlFilter.addFilter("QUERY_t#dictIdRelate_S_EQ", sysDictionaryRelation.getDictIdRelate());
			hqlFilter.addFilter("QUERY_t#dictId_S_EQ", sysDictionaryRelation.getDictId());
			VCarDictionaryRelation  vCarDictionaryRelation  =	iVCarDictionaryRelationService.getByFilter(hqlFilter);
		  if(vCarDictionaryRelation!=null){
				json.setMsg("品牌车系关系已存在！");    
		  }else{
	         sysDictionaryRelation.setDictClasId("CarBrand");
	         sysDictionaryRelation.setDictClasIdRelate("CarSeries");
	         iSysDictionaryRelationService.save(sysDictionaryRelation);
	          json.setSuccess(true);
	          json.setMsg("添加成功");
		  }
     }
	   writeJson(json);	
   }
   public  void update(){
	   Json json = new Json(); 
	   try {
	   if(sysDictionaryRelation!=null){ 
		   HqlFilter hqlFilter = new HqlFilter(getRequest());
			hqlFilter.addFilter("QUERY_t#dictIdRelate_S_EQ", sysDictionaryRelation.getDictIdRelate());
			hqlFilter.addFilter("QUERY_t#dictId_S_EQ", sysDictionaryRelation.getDictId());
			VCarDictionaryRelation  vCarDictionaryRelation  =	iVCarDictionaryRelationService.getByFilter(hqlFilter);
		  if(vCarDictionaryRelation!=null){
				json.setMsg("品牌车系关系已存在！");    
		     }else{
			  String sql ="update t_sys_dictionary_relation set dictId = '"+sysDictionaryRelation.getDictId()+"',"
			  		+ "dictIdRelate='"+sysDictionaryRelation.getDictIdRelate()+"' "
			  		+ "where dictId ='"+dictId+"'and dictIdRelate='"+dictIdRelate+"'";
			        iSysDictionaryRelationService.executeSql(sql);
		      json.setSuccess(true);
		      json.setMsg("更新成功");
		     } 
	     }
	       } catch (Exception e) {
			e.printStackTrace();
		}
	   writeJson(json);	 
   }
   public  void  delete(){
	   Json json = new Json(); 
	   if(id!=null&&!("").equals(id)&&ids!=null&&!("").equals(ids)){
		   String sql ="delete from t_sys_dictionary_relation where dictId ='"+id+"'and dictIdRelate='"+ids+"'";
			        iSysDictionaryRelationService.executeSql(sql);
		      json.setSuccess(true);
		      json.setMsg("删除成功");
	   }
	   writeJson(json);	   
   }
}
