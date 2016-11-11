package com.framework.util;


/**
 * @author 官京
 *  isno   表示短信开关0为开1为关
 */
public class OpenMsUtils { 
	
	private Integer isno;

	public Integer getIsno() {
		return Integer.parseInt(ConfigUtil.get("message"));
	}

 
}
