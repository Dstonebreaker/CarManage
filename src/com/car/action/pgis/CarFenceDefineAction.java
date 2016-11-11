package com.car.action.pgis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.car.entity.pgis.TCarFenceDefine;
import com.car.entity.pgis.VCarFenceDefine;
import com.car.service.pgis.ICarFenceDefineService;
import com.car.service.pgis.IVCarFenceDefineService;
import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.framework.util.WebMsgUtil;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Grid;
import com.system.entity.easyui.Json;
import com.system.entity.maintain.TsysDictionary;
import com.system.service.maintain.ITsysDicService;
@Namespace("/pgis")
@Action("carFenceDefine")
public class CarFenceDefineAction extends BaseAction<TCarFenceDefine> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8991406873908001911L;
	
	private TCarFenceDefine fenceDefine;
	private String dictIdRegion;
	private String points;
	private int fenceKind;
	private String fenceCenter;
	private int type;
	@Autowired
	private IVCarFenceDefineService vCarFenceDefineService;
	@Autowired
	private ITsysDicService dicService;
	@Autowired
	public void setService(ICarFenceDefineService service) {
		super.setService(service);
	}
	
	@Override
	public void grid() {
		// TODO Auto-generated method stub
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		grid.setTotal(vCarFenceDefineService.countByFilter(hqlFilter));
		grid.setRows(vCarFenceDefineService.findByFilter(hqlFilter, page, rows));
		writeJson(grid);
	}
	
	public void doNotNeedSecurity_getRegion() {
		HqlFilter dichqlFilter=new HqlFilter();
		dichqlFilter.addFilter("QUERY_t#dictClasId_S_EQ", WebMsgUtil.CLASS_REGION);
		dichqlFilter.addFilter("QUERY_t#dictFlag_S_EQ", WebMsgUtil.YOUXIAO);
		List<TsysDictionary> dictionaries=dicService.findByFilter(dichqlFilter);
		HqlFilter hqlFilter=new HqlFilter();
		List<VCarFenceDefine> fenceDefines= vCarFenceDefineService.findByFilter(hqlFilter);
		List<TsysDictionary> list=new ArrayList<TsysDictionary>();
		for (int i = 0; i < dictionaries.size(); i++) {
			if (fenceDefines.size()>0) {
			for (int j = 0; j < fenceDefines.size(); j++) {
				if (dictionaries.get(i).getDictId().equals(fenceDefines.get(j).getDictIdRegion())) {
					break;
				}
				if (!dictionaries.get(i).getDictId().equals(fenceDefines.get(j).getDictIdRegion())&&j==fenceDefines.size()-1) {
					list.add(dictionaries.get(i));
				}
			}
			}else {
				list.add(dictionaries.get(i));
			}
		}
		writeJson(list);
	}
	
	/**
	 * 保存
	 */
	public void save() {
		Json json = new Json();
		try {
			fenceDefine=new TCarFenceDefine();
			fenceDefine.setDictIdRegion(dictIdRegion);
			fenceDefine.setFenceKind(fenceKind);
			if (fenceKind==1) {
				String[] result=points.split(",");
				String[] newStrings;
				if (result.length==4) {
					newStrings=new String[]{result[0],result[1],result[2],result[1],result[2],result[3],result[0],result[3],result[0],result[1]};
					result=newStrings;
				}
				if (result.length==2) {
					json.setSuccess(false);
					json.setMsg("保存失败,请画多边形!");
				}
				String po="";
				for (int i = 0; i < result.length; i++) {
					
					if (i%2==1) {
						po+=result[i]+",";
					}else {
						po+=result[i]+" ";
					}
				}
				if (po.length()>0) {
					points=po.substring(0, po.length()-1);
				}else {
					points=null;
				}
				fenceDefine.setPoints(points);
			}else{
				String[] result=points.split(",");
				fenceDefine.setFenceCenter(result[0]+" "+result[1]);
				fenceDefine.setFenceRadius(Double.parseDouble(result[2])*100);
			}
			if (type==0) {
				((ICarFenceDefineService)service).save(fenceDefine);
			}else {
				((ICarFenceDefineService)service).update(fenceDefine);
			}
			
			json.setSuccess(true);
			json.setMsg("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("保存失败");
		}
		writeJson(json);
	}
	
	/**
	 * 更新
	 */
	public void update() {
		Json json = new Json();
		try {
			service.update(fenceDefine);
			json.setSuccess(true);
			json.setMsg("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("保存失败");
		}
		writeJson(json);
	}
	
	/** 
	 * 删除
	 */
	public void delete() {
		Json json = new Json();
		try {
			fenceDefine=service.getById(id);
			service.delete(fenceDefine);
			json.setSuccess(true);
			json.setMsg("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("删除失败");
		}
		writeJson(json);
	}

	public TCarFenceDefine getFenceDefine() {
		return fenceDefine;
	}

	public void setFenceDefine(TCarFenceDefine fenceDefine) {
		this.fenceDefine = fenceDefine;
	}

	public String getDictIdRegion() {
		return dictIdRegion;
	}

	public void setDictIdRegion(String dictIdRegion) {
		this.dictIdRegion = dictIdRegion;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public int getFenceKind() {
		return fenceKind;
	}

	public void setFenceKind(int fenceKind) {
		this.fenceKind = fenceKind;
	}

	public String getFenceCenter() {
		return fenceCenter;
	}

	public void setFenceCenter(String fenceCenter) {
		this.fenceCenter = fenceCenter;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
