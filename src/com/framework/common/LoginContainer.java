package com.framework.common;

import java.util.HashMap;
import java.util.Map;

public class LoginContainer {

	private static Map<String,Object> container = new HashMap<String,Object>();
	
	public static void setContainer(Map<String,Object> container){
		LoginContainer.container = container;
	}
	
	public static Map<String,Object> getContainer(){
		return container;
	}
}
