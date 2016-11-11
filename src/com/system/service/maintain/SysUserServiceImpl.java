package com.system.service.maintain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.dao.BaseDaoI;
import com.system.entity.maintain.SysOrganization;
import com.system.entity.maintain.SysRole;
import com.system.entity.maintain.SysUser;
import com.system.service.base.BaseServiceImpl;

/**
 * 用户业务逻辑
 * 
 * @author 陈晓亮
 * 
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements SysUserServiceI {

	@Autowired
	private BaseDaoI<SysRole> roleDao;

	@Autowired
	private BaseDaoI<SysOrganization> organizationDao;

	@Override
	public void grantRole(String id, String roleIds) {
		SysUser user = getById(id);
		if (user != null) {
			user.setSysRoles(new HashSet<SysRole>());
			for (String roleId : roleIds.split(",")) {
				if (!StringUtils.isBlank(roleId)) {
					SysRole role = roleDao.getById(SysRole.class, roleId);
					if (role != null) {
						user.getSysRoles().add(role);
					}
				}
			}
		}
	}

	@Override
	public void grantOrganization(String id, String organizationIds) {
		SysUser user = getById(id);
		if (user != null) {
			user.setSysOrganization(new HashSet<SysOrganization>());
			for (String organizationId : organizationIds.split(",")) {
				if (!StringUtils.isBlank(organizationId)) {
					SysOrganization organization = organizationDao.getById(SysOrganization.class, organizationId);
					if (organization != null) {
						user.getSysOrganization().add(organization);
					}
				}
			}
		}
	}

	@Override
	public List<Long> userCreateDatetimeChart() {
		List<Long> l = new ArrayList<Long>();
		int k = 0;
		for (int i = 0; i < 12; i++) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("a", k);
			params.put("b", k + 2);
			k = k + 2;
			l.add(count("select count(*) from SysUser t where to_number(to_char(t.createDate,'HH24'))>=:a and to_number(to_char(t.createDate,'HH24'))<:b", params));
		}
		return l;
	}

	@Override
	public Long countUserByRoleId(String roleId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		String hql = "select count(*) from SysUser t join t.sysRoles role where role.id = :roleId";
		return count(hql, params);
	}

	@Override
	public Long countUserByNotRoleId() {
		String hql = "select count(*) from SysUser t left join t.sysRoles role where role.id is null";
		return count(hql);
	}

}