package com.system.service.maintain;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.dao.BaseDaoI;
import com.framework.util.HqlFilter;
import com.system.entity.maintain.SysResource;
import com.system.entity.maintain.SysRole;
import com.system.entity.maintain.SysUser;
import com.system.service.base.BaseServiceImpl;

/**
 * 角色业务逻辑
 * 
 * @author 陈晓亮
 * 
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements SysRoleServiceI {

	@Autowired
	private BaseDaoI<SysUser> userDao;
	@Autowired
	private BaseDaoI<SysResource> resourceDao;

	@Override
	public List<SysRole> findRoleByFilter(HqlFilter hqlFilter, int page, int rows) {
		String hql = "select distinct t from SysRole t join t.sysUsers user";
		return find(hql + hqlFilter.getWhereAndOrderHql(), hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<SysRole> findRoleByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from SysRole t join t.sysUsers user";
		return find(hql + hqlFilter.getWhereAndOrderHql(), hqlFilter.getParams());
	}

	@Override
	public Long countRoleByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from SysRole t join t.sysUsers user";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public void saveRole(SysRole sysRole, String userId) {
		SysUser user = userDao.getById(SysUser.class, userId);	
		sysRole.setTimeCreate(new Date());
		sysRole.setUserIdCreate(user.getUserId());	
		save(sysRole);		
		user.getSysRoles().add(sysRole);// 把新添加的角色与当前用户关联
	}

	@Override
	public void grant(String id, String resourceIds) {
		SysRole role = getById(id);
		if (role != null) {
			role.setSysResources(new HashSet<SysResource>());
			for (String resourceId : resourceIds.split(",")) {
				if (!StringUtils.isBlank(resourceId)) {
					SysResource resource = resourceDao.getById(SysResource.class, resourceId);
					if (resource != null) {
						role.getSysResources().add(resource);
					}
				}
			}
		}
	}

}