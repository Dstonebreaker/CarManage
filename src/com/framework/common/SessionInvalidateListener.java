package com.framework.common;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.struts2.ServletActionContext;

import com.framework.util.ConfigUtil;
import com.system.entity.maintain.SessionInfo;

public class SessionInvalidateListener implements HttpSessionBindingListener,
		HttpSessionActivationListener,HttpSessionListener {

	@Override
	public void sessionDidActivate(HttpSessionEvent arg0) {
		System.out.println("sessionDidActivate");
	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent arg0) {
		System.out.println("sessionWillPassivate");

	}

	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		System.out.println("valueBound");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		//单session注销或过期的时候触发
		System.out.println("valueUnbound");
		SessionInfo sessionInfo = (SessionInfo) arg0.getSession().getAttribute(ConfigUtil.getSessionInfoName());
		String orgid = sessionInfo.getOrganization().getOrgId();
		Map<String,HttpSession> map = (Map<String, HttpSession>) ServletActionContext.getServletContext().getAttribute(orgid);
		if(map!=null){
			map.remove(orgid);
		}
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("sessionCreated");
		System.out.println("sessionid is:"+arg0.getSession().getId());
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("sessionDestroyed");
		//单session注销或过期的时候触发
		System.out.println("valueUnbound");
		SessionInfo sessionInfo = (SessionInfo) arg0.getSession().getAttribute(ConfigUtil.getSessionInfoName());
		String orgid = sessionInfo.getOrganization().getOrgId();
		//Map<String,HttpSession> map = (Map<String, HttpSession>) ServletActionContext.getServletContext().getAttribute(orgid);
		/*Map<String,HttpSession> map = (Map<String, HttpSession>) LoginContainer.getContainer().get(orgid);
		if(map!=null){
			map.remove(orgid);
		}*/
		LoginContainer.getContainer().remove(orgid);
	}

}
