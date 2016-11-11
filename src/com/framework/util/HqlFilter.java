package com.framework.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * HQL过滤器，用于添加where条件和排序，过滤结果集
 * 
 * 添加规则使用addFilter方法
 * 
 * 举例：QUERY_t#id_S_EQ = 0 //最终连接出的HQL是 and t.id = :id id的值是0通过参数传递给Dao
 * 
 * 格式说明QUERY前缀就说明要添加过滤条件
 * 
 * t#id 就是t.id
 * 
 * S:String L:Long I:Integer D:Date ST:Short BD:BigDecimal FT:Float DB:Double
 * T:Timestamp
 * 
 * EQ 是操作符
 * 
 * // EQ 相等 // NE 不等 // LT 小于 // GT 大于 // LE 小于等于 // GE 大于等于 // LK 模糊 // RLK 右模糊
 * // LLK 左模糊// ISNULL 为空// NOTNULL 不为空//IN 集合查询//NOTIN 数据串不加引号整型和字符串都不需要加
 * a,b,c这样既可
 * 
 * @author 陈晓亮
 * 
 */
public class HqlFilter {

	private HttpServletRequest request;// 为了获取request里面传过来的动态参数
	private Map<String, Object> params = new HashMap<String, Object>();// 条件参数
	private StringBuffer hql = new StringBuffer();
	private List<String> sort = new ArrayList<String>();// 排序字段
	private List<String> order = new ArrayList<String>();// asc/desc

	/**
	 * 默认构造
	 */
	public HqlFilter() {

	}

	/**
	 * 带参构造
	 * 
	 * @param request
	 */
	public HqlFilter(HttpServletRequest request) {
		this.request = request;
		addFilter(request);

	}
	/**
	 * 带参构造,区分提交条件为空串
	 * 
	 * @param request  xuzhong
	 */
	public HqlFilter(HttpServletRequest request,String b) {
		this.request = request;
		init(request);

	}
    public void init(HttpServletRequest request){
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String value = request.getParameter(name);
			if(!("".equals(value))){		
				if (name != null && (name.endsWith("NULL") || name.endsWith("NULL_OR"))) {
					addFilter(name);
				} else {
					addFilter(name, value);
				}	
			}
		}
	}
	/**
	 * 添加排序字段
	 * 
	 * @param sort
	 */
	public void addSort(String sort) {
		this.sort.add(sort);
	}

	/**
	 * 添加排序方法，默认asc升序
	 * 
	 * @param order
	 */
	public void addOrder(String order) {
		this.order.add(order);
	}

	/**
	 * 转换SQL操作符
	 * 
	 * @param operator
	 * @return
	 */
	private String getSqlOperator(String operator) {
		if (StringUtils.equalsIgnoreCase(operator, "EQ")) {
			return " = ";
		}
		if (StringUtils.equalsIgnoreCase(operator, "NE")) {
			return " != ";
		}
		if (StringUtils.equalsIgnoreCase(operator, "LT")) {
			return " < ";
		}
		if (StringUtils.equalsIgnoreCase(operator, "GT")) {
			return " > ";
		}
		if (StringUtils.equalsIgnoreCase(operator, "LE")) {
			return " <= ";
		}
		if (StringUtils.equalsIgnoreCase(operator, "GE")) {
			return " >= ";
		}
		if (StringUtils.equalsIgnoreCase(operator, "ISNULL")) {
			return " is null ";
		}
		if (StringUtils.equalsIgnoreCase(operator, "NOTNULL")) {
			return " is not null ";
		}
		if (StringUtils.equalsIgnoreCase(operator, "IN")) {
			return " in ";
		}
		if (StringUtils.equalsIgnoreCase(operator, "NOTIN")) {
			return " not in ";
		}
		if (StringUtils.equalsIgnoreCase(operator, "LK") || StringUtils.equalsIgnoreCase(operator, "RLK")
				|| StringUtils.equalsIgnoreCase(operator, "LLK")) {
			return " like ";
		}
		if (StringUtils.equalsIgnoreCase(operator, "NOTLK")) {
			return " not like ";
		}
		return "";
	}

	/**
	 * 获得添加过滤字段后的HQL
	 * 
	 * @return
	 */
	public String getWhereHql() {
		return hql.toString();
	}

	/**
	 * 获得添加过滤字段后加上排序字段的HQL
	 * 
	 * @return
	 */
	public String getWhereAndOrderHql() {

		if (request != null) {
			String s = request.getParameter("sort");
			String o = request.getParameter("order");
			if (!StringUtils.isBlank(s)) {
				sort.add(s);
			}
			if (!StringUtils.isBlank(o)) {
				order.add(o);
			}
		}

		String hqlstr = hql.toString();
		for (int i = 0; i < sort.size(); i++) {
			String temsort = "";
			if (!StringUtils.isBlank(sort.get(i))) {
				if (sort.get(i).indexOf(".") < 1) {
					temsort = "t." + sort.get(i);
				}

				if (i == 0) {
					hqlstr += " order by " + temsort + " " + (order.size() == 0 ? "asc" : order.get(i)) + ",";// 添加排序信息
				} else {
					hqlstr += temsort + " " + (order.size() == 0 ? "asc" : order.get(i)) + ",";// 添加排序信息
				}

			}
		}
		if (sort.size() > 0) {
			hqlstr = hqlstr.substring(0, hqlstr.length() - 1);
		}
		// else {
		// if (request != null) {
		// String s = request.getParameter("sort");
		// String o = request.getParameter("order");
		// if (!StringUtils.isBlank(s)) {
		// sort.add(s) ;
		// }
		// if (!StringUtils.isBlank(o)) {
		// order.add(s);
		// }
		// if (!StringUtils.isBlank(sort.get(i)) &&
		// !StringUtils.isBlank(order.get(i))) {
		// if (sort.indexOf(".") < 1) {
		// temsort = "t." + sort.get(i);
		// }
		//
		// hql.append(" order by " + temsort + " " + order.get(i) + ", ");//
		// 添加排序信息
		// }
		// }
		// }

		return hqlstr;
	}

	/**
	 * 获得过滤字段参数和值
	 * 
	 * @return
	 */
	public Map<String, Object> getParams() {
		return params;
	}

	/**
	 * 添加过滤
	 * 
	 * @param request
	 */
	public void addFilter(HttpServletRequest request) {
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String value = request.getParameter(name);

			// if (name != null && name.endsWith("NULL") && (value == null ||
			// "".equals(value))) {
			if (name != null && (name.endsWith("NULL") || name.endsWith("NULL_OR"))) {
				addFilter(name);
			} else {
				addFilter(name, value);
			}
		}
	}

	/**
	 * 添加过滤（是否为null）
	 * 
	 * @param name
	 */
	public void addFilter(String name) {
		if (name != null) {
			if (name.startsWith("QUERY_") && (name.endsWith("NULL") || name.endsWith("NULL_OR"))) {// 如果有需要过滤的字段
				String[] filterParams = StringUtils.split(name, "_");
				if (filterParams.length == 4) {
					String columnName = filterParams[1].replaceAll("#", ".");// 要过滤的字段名称
					String columnType = filterParams[2];// 字段类型
					String operator = filterParams[3];// SQL操作符
					String placeholder = UUID.randomUUID().toString().replace("-", "");// 生成一个随机的参数名称

					if (hql.toString().indexOf(" where 1=1") < 0) {
						hql.append("  where 1=1 ");
					}

					hql.append(" and " + columnName + " " + getSqlOperator(operator));// 拼HQL
				} else {
					String columnName = filterParams[1].replaceAll("#", ".");// 要过滤的字段名称
					String columnType = filterParams[2];// 字段类型
					String operator = filterParams[3];// SQL操作符
					String conjunction = filterParams[4];// SQL连接
					String placeholder = UUID.randomUUID().toString().replace("-", "");// 生成一个随机的参数名称

					if (hql.toString().indexOf(" where 1=1") < 0) {
						hql.append("  where 1=1 ");
					}

					hql.append(conjunction + " " + columnName + " " + getSqlOperator(operator));// 拼HQL
				}
			}
		}
	}

	/**
	 * 添加过滤
	 * 
	 * 举例，name传递：QUERY_t#id_S_EQ 或者 QUERY_t#id_S_EQ_OR
	 * 
	 * 举例，value传递：0
	 * 
	 * @param
	 */
	public void addFilter(String name, String value) {
		if (name != null && value != null) {
			if (name.startsWith("QUERY_")) {// 如果有需要过滤的字段
				String[] filterParams = StringUtils.split(name, "_");
				if (filterParams.length == 4) {
					String columnName = filterParams[1].replaceAll("#", ".");// 要过滤的字段名称
					String columnType = filterParams[2];// 字段类型
					String operator = filterParams[3];// SQL操作符
					String placeholder = UUID.randomUUID().toString().replace("-", "");// 生成一个随机的参数名称

					if (hql.toString().indexOf(" where 1=1") < 0) {
						hql.append("  where 1=1 ");
					}

					Object objs = getObjValue(columnType, operator, value);
					if (objs instanceof String[]){
						String[] strs =(String[])objs;
						hql.append(" and ( ");
						for (int i=0;i<strs.length;i++) {
							if (i==0){
								hql.append(columnName + " " + getSqlOperator(operator) + " :param" + placeholder + " ");// 拼HQL
								params.put("param" + placeholder, strs[i]);
							}
							else{
								hql.append(" or " + columnName + " " + getSqlOperator(operator) + " :param" + placeholder + " ");// 拼HQL
								params.put("param" + placeholder, strs[i]);
							}
							placeholder = UUID.randomUUID().toString().replace("-", "");
						}
						hql.append(" ) ");
					}
					else{
						hql.append(" and " + columnName + " " + getSqlOperator(operator) + " :param" + placeholder + " ");// 拼HQL
						
						params.put("param" + placeholder, getObjValue(columnType, operator, value));// 添加参数
					}
				} else {
					String columnName = filterParams[1].replaceAll("#", ".");// 要过滤的字段名称
					String columnType = filterParams[2];// 字段类型
					String operator = filterParams[3];// SQL操作符
					String conjunction = filterParams[4];// SQL连接
					String placeholder = UUID.randomUUID().toString().replace("-", "");// 生成一个随机的参数名称

					if (hql.toString().indexOf(" where 1=1") < 0) {
						hql.append("  where 1=1 ");
					}

					hql.append(conjunction + " " + columnName + " " + getSqlOperator(operator) + " :param" + placeholder + " ");// 拼HQL
					params.put("param" + placeholder, getObjValue(columnType, operator, value));// 添加参数
				}
			}
		}
	}

	/**
	 * 将String值转换成Object，用于拼写HQL，替换操作符和值
	 * 
	 * S:String L:Long I:Integer D:Date ST:Short BD:BigDecimal FT:Float
	 * 
	 * @param columnType
	 * @param operator
	 * @param value
	 * @return
	 */
	private Object getObjValue(String columnType, String operator, String value) {
		if (StringUtils.equalsIgnoreCase(columnType, "S")) {
			if (StringUtils.equalsIgnoreCase(operator, "LK") || StringUtils.equalsIgnoreCase(operator, "NOTLK")) {
				value = "%%" + value + "%%";
			} else if (StringUtils.equalsIgnoreCase(operator, "RLK")) {
				value = value + "%%";
			} else if (StringUtils.equalsIgnoreCase(operator, "LLK")) {
				value = "%%" + value;
			} else if (StringUtils.equalsIgnoreCase(operator, "IN") || StringUtils.equalsIgnoreCase(operator, "NOTIN")) {
				String vals[] = value.split(",");
				/*
				String[] res = new String[vals.length];
				for (int i = 0; i < vals.length; i++) {
					if (i==0){
						value = vals[0]+"',";
					}
					else{
						value = value + vals[i]+"',";
					}
				}
				if(value.length()>0){
					value = value.substring(0, value.length()-1);
				}
				*/
				return vals;
				/*
				 * List<String> res = new ArrayList<String>(); for (int i = 0; i
				 * < vals.length; i++) { res.add(vals[i]); }
				 */
//				return vals;
			}
			return value;
		}
		if (StringUtils.equalsIgnoreCase(columnType, "L")) {
			return Long.parseLong(value);
		}
		if (StringUtils.equalsIgnoreCase(columnType, "I")) {
			if (StringUtils.equalsIgnoreCase(operator, "IN") || StringUtils.equalsIgnoreCase(operator, "NOTIN")) {
				String vals[] = value.split(",");

				Integer[] res = new Integer[vals.length];
				for (int i = 0; i < vals.length; i++) {
					res[i] = Integer.parseInt(vals[i]);
				}

				return res;
			}

			return Integer.parseInt(value);
		}
		if (StringUtils.equalsIgnoreCase(columnType, "D")) {
			try {
				return DateUtils.parseDate(value, new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd", "yyyy/MM/dd" });
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.equalsIgnoreCase(columnType, "T")) {
			return Timestamp.valueOf(value);
		}
		if (StringUtils.equalsIgnoreCase(columnType, "ST")) {
			return Short.parseShort(value);
		}
		if (StringUtils.equalsIgnoreCase(columnType, "BD")) {
			return BigDecimal.valueOf(Long.parseLong(value));
		}
		if (StringUtils.equalsIgnoreCase(columnType, "FT")) {
			return Float.parseFloat(value);
		}
		if (StringUtils.equalsIgnoreCase(columnType, "DB")) {
			return Double.valueOf(value);
		}
		return null;
	}
}