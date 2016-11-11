package com.system.service.maintain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.dao.BaseDaoI;
import com.system.service.base.BaseServiceImpl;
import com.system.entity.maintain.SysOrganization;
import com.system.entity.maintain.SysResource;
import com.system.entity.maintain.SysRole;
import com.system.entity.maintain.SysUser;
import com.framework.util.BeanUtils;
import com.framework.util.HqlFilter;

/**
 * 资源业务逻辑
 * 
 * @author 陈晓亮
 * 
 */
@Service
public class SysResourceServiceImpl extends BaseServiceImpl<SysResource> implements SysResourceServiceI {

	@Autowired
	private BaseDaoI<SysUser> userDao;

	/**
	 * 由于角色与资源关联，机构也与资源关联，所以查询用户能看到的资源菜单应该查询两次，最后合并到一起。
	 */
	@Override
	public List<SysResource> getMainMenu(HqlFilter hqlFilter) {
		List<SysResource> l = new ArrayList<SysResource>();
		String hql = "select distinct t from SysResource t join t.sysRoles role join role.sysUsers user";
		List<SysResource> resource_role = find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
		l.addAll(resource_role);
//		hql = "select distinct t from SysResource t join t.sysOrganizations organization join organization.sysUsers user";
//		List<SysResource> resource_organization = find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
//		l.addAll(resource_organization);
		l = new ArrayList<SysResource>(new HashSet<SysResource>(l));// 去重
		Collections.sort(l, new Comparator<SysResource>() {// 排序
			@Override
			public int compare(SysResource o1, SysResource o2) {
				if (o1.getResoIndex() == null) {
					o1.setResoIndex(1000);
				}
				if (o2.getResoIndex() == null) {
					o2.setResoIndex(1000);
				}
				return o1.getResoIndex().compareTo(o2.getResoIndex());
			}
		});
		return l;
	}

	@Override
	public List<SysResource> resourceTreeGrid(HqlFilter hqlFilter) {
		List<SysResource> l = new ArrayList<SysResource>();
		String hql = "select distinct t from SysResource t join t.sysRoles role join role.sysUsers user";
		List<SysResource> resource_role = find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
		l.addAll(resource_role);
//		hql = "select distinct t from SysResource t join t.sysOrganizations organization join organization.sysUsers user";
//		List<SysResource> resource_organization = find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
//		l.addAll(resource_organization);
		l = new ArrayList<SysResource>(new HashSet<SysResource>(l));// 去重
		Collections.sort(l, new Comparator<SysResource>() {// 排序
					@Override
					public int compare(SysResource o1, SysResource o2) {
						if (o1.getResoIndex() == null) {
							o1.setResoIndex(1000);
						}
						if (o2.getResoIndex() == null) {
							o2.setResoIndex(1000);
						}
						return o1.getResoIndex().compareTo(o2.getResoIndex());
					}
				});
		return l;
		
	}

	@Override
	public void updateResource(SysResource sysResource) {
		if (!StringUtils.isBlank(sysResource.getResoId())) {
			SysResource t = getById(sysResource.getResoId());
			SysResource oldParent = t.getSysResource();
			BeanUtils.copyNotNullProperties(sysResource, t, new String[] { "createdatetime" });
			if (sysResource.getSysResource() != null) {// 说明要修改的节点选中了上级节点
				SysResource pt = getById(sysResource.getSysResource().getResoId());// 上级节点
				isParentToChild(t, pt, oldParent);// 说明要将当前节点修改到当前节点的子或者孙子下
				t.setSysResource(pt);
			} else {
				t.setSysResource(null);
			}
		}
	}

	/**
	 * 判断是否是将当前节点修改到当前节点的子节点下
	 * 
	 * @param t
	 *            当前节点
	 * @param pt
	 *            要修改到的节点
	 * 
	 * @param oldParent
	 *            原上级节点
	 * @return
	 */
	private boolean isParentToChild(SysResource t, SysResource pt, SysResource oldParent) {
//		if (pt != null && pt.getSysResource() != null) {
//			if (StringUtils.equals(pt.getSysResource().getId(), t.getId())) {
//				pt.setSysResource(oldParent);
//				return true;
//			} else {
//				return isParentToChild(t, pt.getSysResource(), oldParent);
//			}
//		}
		return false;
	}

	/**
	 * 由于新添加的资源，当前用户的角色或者机构并没有访问此资源的权限，所以这个地方重写save方法，将新添加的资源放到用户的第一个角色里面或者第一个机构里面
	 */
	@Override
	public void saveResource(SysResource sysResource, String userId) {
		SysUser user = userDao.getById(SysUser.class, userId);	
		 sysResource.setDictIdFlag("YX");
		 sysResource.setTimeCreate(new Timestamp(new Date().getTime()));
		save(sysResource);
		Set<SysRole> roles = user.getSysRoles();
		if (roles != null && !roles.isEmpty()) {// 如果用户有角色，就将新资源放到用户的第一个角色里面
			List<SysRole> l = new ArrayList<SysRole>();
			l.addAll(roles);
			Collections.sort(l, new Comparator<SysRole>() {
				@Override
				public int compare(SysRole o1, SysRole o2) {
					if (o1.getTimeCreate().getTime() > o2.getTimeCreate().getTime()) {
						return 1;
					}
					if (o1.getTimeCreate().getTime() < o2.getTimeCreate().getTime()) {
						return -1;
					}
					return 0;
				}
			});
			l.get(0).getSysResources().add(sysResource);
		} 
	}

	@Override
	public List<SysResource> findResourceByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from SysResource t join t.sysRoles role join role.sysUsers user";
		return find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

}