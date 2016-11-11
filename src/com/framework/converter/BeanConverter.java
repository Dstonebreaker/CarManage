package com.framework.converter;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BeanConverter {	
	
	public Object[][] getObjects (List<T> list) {
		Object[][] os = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			Object object = list.get(i);
			Class<?> c = object.getClass();
			// 取出bean里的所以方法
			Method[] methods = c.getDeclaredMethods();
			Field[] fields = c.getDeclaredFields();
			int j = 0;
			for (Field field : fields) {
				try {
					String fieldGetName = parGetName(field.getName());
					if (!checkGetMet(methods, fieldGetName)) {
						continue;
					}
					Method fieldGetMet = c.getMethod(fieldGetName, new Class[]{});
					os[i][j] = fieldGetMet.invoke(object, new Object[]{});
				} catch (Exception e) {
					e.printStackTrace();
				}
				j++;				
			}
		}
		return os;		
	}
	
	/**
	 * 
	 * 拼接属性的get方法
	 *
	 * @author 陈晓亮
	 * @date 2014-8-22
	 * @param fieldName
	 * @return String
	 */
	public static String parGetName (String fieldName) {
		if (null == fieldName || "".equals(fieldName)) {
			return null;
		}
		return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}
	
	/**
	 * 
	 * 判断是否存在属性的get方法
	 *
	 * @author 陈晓亮
	 * @date 2014-8-22
	 * @param methods
	 * @param fieldGetMet
	 * @return boolean
	 */
	public static boolean checkGetMet(Method[] methods, String fieldGetMet) {
		for (Method method : methods) {
			if (fieldGetMet.equals(method.getName())) {
				return true;
			}
		}
		return false;
	}

}
