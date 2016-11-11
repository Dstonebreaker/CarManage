package com.system.service.maintain;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.framework.util.HqlFilter;
import com.system.entity.maintain.TsysDictionary;
import com.system.service.base.BaseServiceI;

public interface ITsysDicService extends BaseServiceI<TsysDictionary> {
	public List<Map> treeData(HttpServletRequest request);

	public void saveDicData(TsysDictionary dictionary, String userId);

}
