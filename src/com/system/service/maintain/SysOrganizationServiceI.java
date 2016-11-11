package com.system.service.maintain;

import java.util.List;

import com.framework.util.HqlFilter;
import com.system.entity.maintain.SysOrganization;
import com.system.service.base.BaseServiceI;

/**
 * 机构业务
 * 
 * @author 陈晓亮
 * 
 */
public interface SysOrganizationServiceI extends BaseServiceI<SysOrganization> {

	/**
	 * 保存机构
	 * 
	 * @param data
	 *            机构信息
	 * @param id
	 *            用户ID
	 */
	public void saveOrganization(SysOrganization sysOrganization, String userId);
	
	/**
	 * 更新机构
	 */
	public void updateOrganization(SysOrganization sysOrganization);
	
	/**
	 * 查找机构
	 */
	public List<SysOrganization> findOrganizationByFilter(HqlFilter hqlFilter);

	/**
	 * 机构授权
	 * 
	 * @param id
	 *            机构ID
	 * @param resourceIds
	 *            资源IDS
	 */
	public void grant(String id, String resourceIds);	
	
	/**
	 * 获得资源treeGrid
	 * 
	 * @return
	 */
	public List<SysOrganization> organizationTreeGrid(HqlFilter hqlFilter, String id);
	
	/**
	 * 获得资源treeGrid
	 * 
	 * @return
	 */
	public List<SysOrganization> organizationTreeGrid(HqlFilter hqlFilter, String id, String orgIdManager);
	
	/**
	 * 获得本机构及子机构
	 * @param id
	 * @return
	 */
	public List<SysOrganization> selfAndSubOrganizationTreeGrid(String id);

}