package com.system.service.maintain;

import java.util.List;

import com.framework.util.HqlFilter;
import com.system.entity.maintain.SysResource;
import com.system.service.base.BaseServiceI;

/**
 * 资源业务逻辑
 * 
 * @author 陈晓亮
 * 
 */
public interface SysResourceServiceI extends BaseServiceI<SysResource> {

	/**
	 * 获得资源树，或者combotree(只查找菜单类型的节点)
	 * 
	 * @return
	 */
	public List<SysResource> getMainMenu(HqlFilter hqlFilter);

	/**
	 * 获得资源treeGrid
	 * 
	 * @return
	 */
	public List<SysResource> resourceTreeGrid(HqlFilter hqlFilter);

	/**
	 * 更新资源
	 */
	public void updateResource(SysResource sysResource);

	/**
	 * 保存一个资源
	 * 
	 * @param syresource
	 * @param userId
	 * @return
	 */
	public void saveResource(SysResource sysResource, String userId);

	/**
	 * 查找符合条件的资源
	 */
	public List<SysResource> findResourceByFilter(HqlFilter hqlFilter);

}