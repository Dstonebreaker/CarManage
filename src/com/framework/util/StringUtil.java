package com.framework.util;

import java.util.Random;

/**
 * String工具类
 * 
 * @author 陈晓亮
 * 
 */
public class StringUtil {

	/**
	 * 格式化字符串
	 * 
	 * 例：formateString("xxx{0}bbb",1) = xxx1bbb
	 * 
	 * @param str
	 * @param params
	 * @return
	 */
	public static String formateString(String str, String... params) {
		for (int i = 0; i < params.length; i++) {
			str = str.replace("{" + i + "}", params[i] == null ? "" : params[i]);
		}
		return str;
	}

	public static String addStringWithQuo(String str1, String str2) {
		if ("".equals(str1)) {
			return str2;
		} else {
			return str1 + str2;
		}
	}

	/**
	 * 获取count个随机数
	 * 
	 * @param count
	 *            随机数位数
	 * @return
	 */
	public static String randomCode(int count) {
		StringBuffer sb = new StringBuffer();
		String str = "0123456789";
		Random random = new Random();
		for (int i = 0; i < count; i++) {
			int num = random.nextInt(str.length());
			sb.append(str.charAt(num));
			str = str.replace((str.charAt(num) + ""), "");
		}
		return sb.toString();
	}

}