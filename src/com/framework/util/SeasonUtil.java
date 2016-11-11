package com.framework.util;
/**
 * 季节转换工具
 * @author Administrator
 *
 */
public class SeasonUtil {
	
	/**
	 * 月转化成季度
	 * @param month
	 * @return
	 */
	public static String monthToSeason(String month){
		//1代表第一季度、2-第二季度、3-第三季度、4-第四季度
		String season = "";
		if(month==null||"".equals(month)){
			season = month;
		}else{
			//1-3月第一季度、4-6第二季度、7-9第三季度、10-12第四季度
			switch(Integer.parseInt(month)){
			case 1:
				season = "1";
				break;
			case 2:
				season = "1";
				break;
			case 3:
				season = "1";
				break;
			case 4:
				season = "2";
				break;
			case 5:
				season = "2";
				break;
			case 6:
				season = "2";
				break;
			case 7:
				season = "3";
				break;
			case 8:
				season = "3";
				break;
			case 9:
				season = "3";
				break;
			case 10:
				season = "4";
				break;
			case 11:
				season = "4";
				break;
			case 12:
				season = "4";
				break;
				default :season = "1";
			}
			
		}
		return season;
	}
	
	
	/**
	 * 季度转化成半年
	 * @param season
	 * @return
	 */
	public static String seasonToHalfYear(String season){
		//1-上半年、2-下半年
		String halfYear = "";
		if(season==null||"".equals(season)){
			halfYear = season;
		}else{
			//1-第一季度、2-第二季度、3-第三季度、4-第四季度
			switch(Integer.parseInt(season)){
			case 1:
				halfYear = "1";
				break;
			case 2:
				halfYear = "1";
				break;
			case 3:
				halfYear = "2";
				break;
			case 4:
				halfYear = "2";
				break;
				default :halfYear = "1";
			}
		}
		return halfYear;
	}
	
	/**
	 * 月份转化为半年
	 */
	public static String monthToHalfYear(String month){
		//1-上半年、2-下半年
		String halfYear = "";
		//1-6上半年\7-12下半年
		if(month==null||"".equals(month)){
			halfYear = month;
		}else{
			halfYear = seasonToHalfYear(monthToSeason(month));
		}
		return halfYear ;
	}
	
	
}
