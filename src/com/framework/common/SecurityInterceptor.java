package com.framework.common;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.framework.util.ConfigUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.system.entity.maintain.SessionInfo;
import com.system.entity.maintain.SysOrganization;
import com.system.entity.maintain.SysResource;
import com.system.entity.maintain.SysRole;

/**
 * 权限拦截器
 * 
 * @author 陈晓亮
 * 
 */
public class SecurityInterceptor extends MethodFilterInterceptor {

	private static final Logger logger = Logger.getLogger(SecurityInterceptor.class);

	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		//ActionContext actionContext = actionInvocation.getInvocationContext();
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ConfigUtil.getSessionInfoName());
		String servletPath = ServletActionContext.getRequest().getServletPath();

		servletPath = StringUtils.substringBeforeLast(servletPath, ".");// 去掉后面的后缀 *.sy *.action之类的

		logger.info("进入权限拦截器->访问的资源为：[" + servletPath + "]");

		Set<SysRole> roles = sessionInfo.getUser().getSysRoles();
		for (SysRole role : roles) {
			for (SysResource resource : role.getSysResources()) {
				if (resource != null && StringUtils.equals(resource.getResoUrl(), servletPath)) {
					return actionInvocation.invoke();
				}
			}
		}
		Set<SysOrganization> organizations = sessionInfo.getUser().getSysOrganization();
		for (SysOrganization organization : organizations) {
//			for (SysResource resource : organization.getSysResources()) {
//				if (resource != null && StringUtils.equals(resource.getResoUrl(), servletPath)) {
//					return actionInvocation.invoke();
//				}
//			}
		}

		String errMsg = "您没有访问此功能的权限！功能路径为[" + servletPath + "]请联系管理员给你赋予相应权限。";
		logger.info(errMsg);
		ServletActionContext.getRequest().setAttribute("msg", errMsg);
		return "noSecurity";
	}

}