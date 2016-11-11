package com.system.action.maintain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.framework.util.BeanUtils;
import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Json;
import com.system.entity.easyui.Tree;
import com.system.entity.maintain.SessionInfo;
import com.system.entity.maintain.SysResource;
import com.system.entity.maintain.SysRole;
import com.system.service.maintain.SysResourceServiceI;




/**
 * 资源
 * 
 * @author 陈晓亮
 * 
 */
@Namespace("/maintain")
@Action(value = "sysResource")
public class SysResourceAction extends BaseAction<SysResource> {

	private static final long serialVersionUID = 2403820041954973287L;
	@Autowired
	private SysResourceServiceI resourceService;
	private SysResource sysResource;

	public SysResource getSysResource() {
		return sysResource;
	}

	public void setSysResource(SysResource sysResource) {
		this.sysResource = sysResource;
	}

	/**
	 * 注入业务逻辑，使当前action调用service.xxx的时候，直接是调用基础业务逻辑
	 * 
	 * 如果想调用自己特有的服务方法时，请使用((TServiceI) service).methodName()这种形式强转类型调用
	 * 
	 * @param service
	 */
	@Autowired
	public void setService(SysResourceServiceI service) {
		this.service = service;
	}

	/**
	 * 更新资源
	 */
	public void update() {
		Json json = new Json();
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		if (!StringUtils.isBlank(sysResource.getResoId())) {
			if (sysResource.getSysResource() != null && StringUtils.equals(sysResource.getResoId(), sysResource.getResoIdParent())) {
				json.setMsg("父资源不可以是自己！");
			} else {
				sysResource.setTimeUpdate(new Timestamp(new Date().getTime()));
				sysResource.setUserIdUpdate(sessionInfo.getUser().getUserId());
				if(sysResource.getResoUrl()==null){
				   sysResource.setResoUrl("");
				}
			//	sysResource.setDictIdFlag("YX");
			//	sysResource.setSysRoot(sessionInfo.getRoot());
				((SysResourceServiceI) service).updateResource(sysResource);
				json.setSuccess(true);
				 json.setMsg("更新成功");
			}
		}
		writeJson(json);
	}
	/**
	 * 无需权限查找所有对象
	 */
	public void doNotNeedSecurity_findAll() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		writeJson(service.findByFilter(hqlFilter));
	}
	/**
	 * 逻辑删除资源
	 */
	public void delete() {
		Json json = new Json();
		try {
			SysResource resource = service.getById(id);
			resource.setDictIdFlag("WX");
			resourceService.update(resource);
			json.setSuccess(true);
			 json.setMsg("删除成功");
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
		}
		writeJson(json);
	}
	/**
	 * 获得主菜单tree，也用于获得上级资源菜单combotree
	 */
	public void doNotNeedSecurity_getMainMenu() {
		HqlFilter hqlFilter = new HqlFilter(getRequest());
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		hqlFilter.addFilter("QUERY_user#userId_S_EQ", sessionInfo.getUser().getUserId());
	    hqlFilter.addFilter("QUERY_t#dictIdResoType_S_EQ", "0");// 0就是只查菜单
	    hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", "YX");//
		List<SysResource> resources = (resourceService.getMainMenu(hqlFilter));
		List<Tree> tree = new ArrayList<Tree>();
		for (SysResource resource : resources) {
			Tree node = new Tree();
			BeanUtils.copyNotNullProperties(resource, node);
			node.setText(resource.getResoName());
			node.setPid(resource.getResoIdParent());
			node.setId(resource.getResoId());
			Map<String, String> attributes = new HashMap<String, String>();
			attributes.put("url", resource.getResoUrl());
			attributes.put("target", null);//数据库无此属性，前台页面有
			node.setAttributes(attributes);
			tree.add(node);
		}
		writeJson(tree);
	}

	/**
	 * 获得资源treeGrid
	 */
	public void treeGrid() {        
    	try {
    		HqlFilter hqlFilter = new HqlFilter(getRequest());
    	    SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
    	    hqlFilter.addFilter("QUERY_user#userId_S_EQ", sessionInfo.getUser().getUserId());
    	    hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", "YX");
    	    List<SysResource> resources = resourceService.resourceTreeGrid(hqlFilter);
    	    writeJson(resources);    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得角色的资源列表
	 */
	public void doNotNeedSecurity_getRoleResources() {
		SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
		HqlFilter hqlFilter = new HqlFilter();
		hqlFilter.addFilter("QUERY_role#roleId_S_EQ", id);
		  List<SysResource>  resources = resourceService.findResourceByFilter(hqlFilter);
		writeJson(resources);
	}


	/**
	 * 获得资源树
	 */
	public void doNotNeedSecurity_getResourcesTree() {
		try {
    		HqlFilter hqlFilter = new HqlFilter(getRequest());
    	    SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
    	    hqlFilter.addFilter("QUERY_user#userId_S_EQ", sessionInfo.getUser().getUserId());
    	    hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", "YX");
    	    List<SysResource>  resources = resourceService.resourceTreeGrid(hqlFilter); 
    	    List<Tree> tree = new ArrayList<Tree>();
    		for (SysResource resource : resources) {
    			Tree node = new Tree();
    			BeanUtils.copyNotNullProperties(resource, node);
    			node.setText(resource.getResoName());
    			node.setId(resource.getResoId());
    			node.setPid(resource.getResoIdParent());
    			tree.add(node);
    		}
    		writeJson(tree);
		   } catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存一个资源
	 */
	public void save() {
		Json json = new Json();
		if (sysResource != null) {
			SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
			sysResource.setResoId(UUID.randomUUID().toString());
			if(sysResource.getResoUrl()==null){
				sysResource.setResoUrl("");
			}
			sysResource.setDictIdFlag("YX");
			sysResource.setTimeCreate(new Timestamp(new Date().getTime()));
			sysResource.setUserIdCreate(sessionInfo.getUser().getUserId());
			((SysResourceServiceI) service).saveResource(sysResource, sessionInfo.getUser().getUserId());
			json.setSuccess(true);
			 json.setMsg("添加成功");
		}
		writeJson(json);
	}

}