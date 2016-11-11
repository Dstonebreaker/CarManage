package com.framework.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.system.entity.maintain.SessionInfo;
import com.system.entity.maintain.SysOrganization;
import com.system.entity.maintain.SysResource;
import com.system.entity.maintain.SysRole;

/**
 * 用于前台页面判断是否有权限的工具类
 * 
 * @author 陈晓亮
 * 
 */
public class SecurityUtil {
	private HttpSession session;

	public SecurityUtil(HttpSession session) {
		this.session = session;
	}

	/**
	 * 判断当前用户是否可以访问某资源
	 * 
	 * @param url
	 *            资源地址
	 * @return
	 */
	public boolean havePermission(String url) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		List<SysResource> resources = new ArrayList<SysResource>();
		for (SysRole role : sessionInfo.getUser().getSysRoles()) {
			resources.addAll(role.getSysResources());
		}
	//	for (SysOrganization organization : sessionInfo.getUser().getSysOrganization()) {
		//	resources.addAll(organization.getSysResources());
	//	}
		resources = new ArrayList<SysResource>(new HashSet<SysResource>(resources));// 去重(这里包含了当前用户可访问的所有资源)
		for (SysResource resource : resources) {
			if (StringUtils.equals(resource.getResoUrl(), url)) {// 如果有相同的，则代表当前用户可以访问这个资源
				return true;
			}
		}
		return false;
	}	
	
}