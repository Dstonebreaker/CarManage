package com.system.service.maintain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;




import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

import sun.print.resources.serviceui;

import com.framework.util.HqlFilter;
import com.system.entity.maintain.SysSetting;
import com.system.service.base.BaseServiceI;

public class SettingManager {
	private static BaseServiceI service; 
	private static SettingManager values;
	private Map<String, Integer> valueMap = new HashMap<String, Integer>();

	//private SettingManager() {
	//}

	public static SettingManager getInstance(BaseServiceI s) {
		if (values == null) {
			service = s;
			values = new SettingManager();
		}
		return values;
	}
	public int getValue(String key) {
		if (valueMap==null||valueMap.size()==0) {
			valueMap = values.findByFilters();
		}
		return valueMap.get(key);
	}
	public void setValue(String key, int value) {
		valueMap.put(key, value);
	}
	private Map<String, Integer> findByFilters() {
		HqlFilter hqlFilter = new HqlFilter();
		List<SysSetting> list = service.findByFilter(hqlFilter);		
		Map<String, Integer> valueMaps = new HashMap<String, Integer>();
		for (SysSetting setting : list) {
			valueMaps.put(setting.getSettId(), setting.getSettValue());
		}
		return valueMaps;
	}
}
