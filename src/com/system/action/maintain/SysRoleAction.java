package com.system.action.maintain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.car.service.car.ICarservice;
import com.framework.util.BeanUtils;
import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Grid;
import com.system.entity.easyui.Json;
import com.system.entity.easyui.Tree;
import com.system.entity.maintain.SessionInfo;
import com.system.entity.maintain.SysRole;
import com.system.entity.maintain.SysUser;
import com.system.service.maintain.SysRoleServiceI;
import com.system.service.maintain.SysUserServiceI;



/**
 * 角色
 * 
 * @author 陈晓亮
 * 
 */
@Namespace("/maintain")
@Action(value = "sysRole")
public class SysRoleAction extends BaseAction<SysRole> {

	private static final long serialVersionUID = -601950350292994338L;
	
	
	@Autowired
	private SysUserServiceI userService;
	@Autowired
	public  ICarservice carservice;
	private SysRole sysRole;

	public SysRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(SysRoleServiceI service) {
		this.service = service;
	}

	/**
	 * 角色grid
	 */
//	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void grid() {
		Grid grid = new Grid();
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		hqlFilter.addFilter("QUERY_user#userId_S_EQ", sessionInfo.getUser().getUserId());
		grid.setTotal(((SysRoleServiceI) service).countRoleByFilter(hqlFilter));
		grid.setRows(((SysRoleServiceI) service).findRoleByFilter(hqlFilter, page, rows));
		writeJson(grid);
	}
	/**
	 * 无需权限查找所有对象
	 */
	public void doNotNeedSecurity_findAll() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		writeJson(service.findByFilter(hqlFilter));
	}
	/**
	 * 保存一个角色
	 */
	public void save() {
		Json json = new Json();
		if (sysRole != null) {
			HqlFilter hqlFilter = new HqlFilter();
			hqlFilter.addFilter("QUERY_t#roleName_S_EQ", sysRole.getRoleName());
			SysRole role = service.getByFilter(hqlFilter);
			if (role != null) {
				json.setMsg("新建角色失败，角色名已存在！");
			} else{
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			((SysRoleServiceI) service).saveRole(sysRole, sessionInfo.getUser().getUserId());
			json.setSuccess(true);
			 json.setMsg("添加成功");
			}
		}
		writeJson(json);
	}
	
	/**
	 * 更新一个对象
	 */
	public void update() {
		Json json = new Json();
		String reflectId = null;
		try {
			if (sysRole != null) {
				reflectId = (String) FieldUtils.readField(sysRole, "roleId", true);
			                     }
			HqlFilter hqlFilter = new HqlFilter();
			hqlFilter.addFilter("QUERY_t#roleId_S_NE", sysRole.getRoleId());
			hqlFilter.addFilter("QUERY_t#roleName_S_EQ", sysRole.getRoleName());
			SysRole role = service.getByFilter(hqlFilter);
			if (role != null) {
				json.setMsg("新建用户失败，角色名已存在！");
			} else{
		if (!StringUtils.isBlank(reflectId)) {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			SysUser user = userService.getById(sessionInfo.getUser().getUserId());
			SysRole t = service.getById(reflectId);
			BeanUtils.copyNotNullProperties(sysRole, t, new String[] { "userIdUpdate",  "timeUpdate" });
			t.setUserIdUpdate(user.getUserId());
			t.setTimeUpdate(new Date());
			service.update(t);
			carservice.doRunCall();
			json.setSuccess(true);
			json.setMsg("更新成功！");
			
	     	}
		}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		writeJson(json);
	}
	
	/**
	 * 角色授权
	 */
	public void grant() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		Json json = new Json();
		((SysRoleServiceI) service).grant(id, ids);
		carservice.doRunCall();//rucall 方法
		json.setSuccess(true);
		writeJson(json);
	}

	/**
	 * 获得当前用户能看到的所有角色树
	 */
	public void doNotNeedSecurity_getRolesTree() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		HqlFilter hqlFilter = new HqlFilter();
	//  hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", "YX");
		List<SysRole> list = ((SysRoleServiceI) service)
				.findByFilter(hqlFilter);
		List<Tree> tree = new ArrayList<Tree>();
		for (SysRole role : list) {
			Tree node = new Tree();
			BeanUtils.copyNotNullProperties(role, node);
			node.setText(role.getRoleName());
			node.setId(role.getRoleId());
			node.setPid(null);
			tree.add(node);
		}
		writeJson(tree);
	}

	/**
	 * 获得当前用户的角色
	 */
	public void doNotNeedSecurity_getRoleByUserId() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		hqlFilter.addFilter("QUERY_user#userId_S_EQ", id);
		List<SysRole> roles = ((SysRoleServiceI) service).findRoleByFilter(hqlFilter);
		writeJson(roles);
	}
	/**
	 * 角色的删除
	 */
	public void delete() {
		Json json = new Json();
		if (!StringUtils.isBlank(id)) {
			SysRole role= service.getById(id);
			service.delete(role);
			carservice.doRunCall();
			json.setSuccess(true);
			json.setMsg("删除成功！");
		}
		writeJson(json);
	}
	/**
	 * 用户角色分布报表
	 */
	public void doNotNeedSecurity_userRoleChart() {
		List<SysRole> roles = service.find();
		List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
		for (SysRole role : roles) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("name", role.getRoleName());
//			m.put("y", userService.countUserByRoleId(role.getId()));
			m.put("sliced", false);
			m.put("selected", false);
			l.add(m);
		}
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("name", "无");
		m.put("y", userService.countUserByNotRoleId());
		m.put("sliced", true);
		m.put("selected", true);
		l.add(m);
		writeJson(l);
	}
}