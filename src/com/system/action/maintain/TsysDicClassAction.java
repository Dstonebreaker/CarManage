package com.system.action.maintain;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.framework.util.WebMsgUtil;
import com.system.action.base.BaseAction;
import com.system.entity.maintain.SessionInfo;
import com.system.entity.maintain.TsysDictionaryClass;
import com.system.service.maintain.ITsysDicClassService;

@Namespace("/maintain")
@Action(value = "dictionaryClasssManage")
public class TsysDicClassAction extends BaseAction<TsysDictionaryClass> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1343179639964231812L;
	private String dictClassId;
	private TsysDictionaryClass dictionaryClas;
	@Autowired
	private ITsysDicClassService dicClassService;

	@Autowired
	public void setService(ITsysDicClassService service) {
		this.service = service;
	}
	/**
	 * 无需权限查找所有对象
	 */
	public void doNotNeedSecurity_findAll() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		writeJson(service.findByFilter(hqlFilter));
	}
	/**
	 * 根据字典类型查询字典数据
	 */
	public void doNotNeedSecurity_getClassNameByClassId() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#dictClasId_S_EQ", dictClassId);
		hqlFilter.addFilter("QUERY_t#dictClasFlag_S_EQ", WebMsgUtil.YOUXIAO);
		List<TsysDictionaryClass> list = dicClassService.findByFilter(hqlFilter);
		writeJson(list);
	}

	/**
	 * 自用字典类型
	 */
	public void doNotNeedSecurity_getSelfClassName() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_t#dictClasFlag_S_EQ", WebMsgUtil.YOUXIAO);
		hqlFilter.addFilter("QUERY_t#dictClasIsSelf_I_EQ", "1");
		List<TsysDictionaryClass> list = dicClassService.findByFilter(hqlFilter);
		writeJson(list);
	}

	public String getDictClassId() {
		return dictClassId;
	}

	public void setDictClassId(String dictClassId) {
		this.dictClassId = dictClassId;
	}

	public TsysDictionaryClass getDictionaryClas() {
		return dictionaryClas;
	}

	public void setDictionaryClas(TsysDictionaryClass dictionaryClas) {
		this.dictionaryClas = dictionaryClas;
	}

}
