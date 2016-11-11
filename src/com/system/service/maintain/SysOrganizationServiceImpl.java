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
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.dao.BaseDaoI;
import com.system.service.base.BaseServiceImpl;
import com.system.entity.maintain.SessionInfo;
import com.system.entity.maintain.SysOrganization;
import com.system.entity.maintain.SysResource;
import com.system.entity.maintain.SysUser;
import com.framework.util.BeanUtils;
import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;

/**
 * 机构业务
 * 
 * @author 陈晓亮
 * 
 */
@Service
public class SysOrganizationServiceImpl extends BaseServiceImpl<SysOrganization> implements SysOrganizationServiceI {

	@Autowired
	private BaseDaoI<SysResource> resourceDao;
	@Autowired
	private BaseDaoI<SysOrganization> organizationDao;

	@Autowired
	private BaseDaoI<SysUser> userDao;

	@Override
	public void saveOrganization(SysOrganization sysOrganization, String userId) {
		SysUser user = userDao.getById(SysUser.class, userId);
		sysOrganization.setTimeCreate(new Date());
		sysOrganization.setUserIdCreate(user.getUserId());
		sysOrganization.setDictIdFlag("YX");
		sysOrganization.setOrgIdManager(sysOrganization.getOrgId());
		save(sysOrganization);

	}

	@Override
	public void updateOrganization(SysOrganization sysOrganization) {
		if (!StringUtils.isBlank(sysOrganization.getOrgId())) {
			update(sysOrganization);
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
	private boolean isParentToChild(SysOrganization t, SysOrganization pt, SysOrganization oldParent) {
		// if (pt != null && pt.getSysOrganization() != null) {
		// if (StringUtils.equals(pt.getSysOrganization().getId(), t.getId())) {
		// pt.setSysOrganization(oldParent);
		// return true;
		// } else {
		// return isParentToChild(t, pt.getSysOrganization(), oldParent);
		// }
		// }
		return false;
	}

	@Override
	public List<SysOrganization> findOrganizationByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from SysOrganization t join t.sysUsers user";
		return find(hql + hqlFilter.getWhereAndOrderHql(), hqlFilter.getParams());
	}

	@Override
	public void grant(String id, String resourceIds) {
		// SysOrganization organization = getById(id);
		// if (organization != null) {
		// organization.setSysResources(new HashSet<SysResource>());
		// for (String resourceId : resourceIds.split(",")) {
		// if (!StringUtils.isBlank(resourceId)) {
		// SysResource resource = resourceDao.getById(SysResource.class,
		// resourceId);
		// if (resource != null) {
		// organization.getSysResources().add(resource);
		// }
		// }
		// }
		// }
	}

	@Override
	public List<SysOrganization> organizationTreeGrid(HqlFilter hqlFilter, String id) {
		List<SysOrganization> l = new ArrayList<SysOrganization>();
		String hql = null;
		if (id == null || id.equals("")) {
			hql = "select distinct t from SysOrganization t where t.orgIdOrgParent = Null and t.dictIdFlag ='YX'";
		} else {
			hql = "select distinct t from SysOrganization t";
			hqlFilter.addFilter("QUERY_t#orgIdOrgParent_S_EQ", id);
			hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", "YX");
		}
		List<SysOrganization> resource_role = find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
		if (resource_role != null && resource_role.size() > 0) {
			for (SysOrganization o : resource_role) {
				SysOrganization organization = new SysOrganization();
				BeanUtils.copyProperties(o, organization);
				Set<SysOrganization> set = o.getSysOrganizations();
				if (set != null && !set.isEmpty()) {
					organization.setState("closed");
				}
				l.add(organization);
			}
		}
		return l;
	}

	@Override
	public List<SysOrganization> organizationTreeGrid(HqlFilter hqlFilter, String id, String orgIdManager) {
		List<SysOrganization> l = new ArrayList<SysOrganization>();
		String hql = null;
		if (id == null || id.equals("")) {
			hql = "select distinct t from SysOrganization t where t.orgIdManager='" + orgIdManager
					+ "' and t.dictIdFlag ='YX'";
		} else {
			hql = "select distinct t from SysOrganization t";
			hqlFilter.addFilter("QUERY_t#orgIdOrgParent_S_EQ", id);
			hqlFilter.addFilter("QUERY_t#orgIdManager_S_EQ", orgIdManager);
			hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", "YX");
		}
		List<SysOrganization> resource_role = find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
		if (resource_role != null && resource_role.size() > 0) {
			for (SysOrganization o : resource_role) {
				SysOrganization organization = new SysOrganization();
				BeanUtils.copyProperties(o, organization);
				Set<SysOrganization> set = o.getSysOrganizations();
				if (set != null && !set.isEmpty()) {
					organization.setState("closed");
				}
				l.add(organization);
			}
		}
		return l;
	}

	@Override
	public List<SysOrganization> selfAndSubOrganizationTreeGrid(String id) {
		List<SysOrganization> l = new ArrayList<SysOrganization>();
		SysOrganization organization = null;
		if (id == null || "".equals(id)) {
			SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession()
					.getAttribute(ConfigUtil.getSessionInfoName());
			id = sessionInfo.getUser().getSysOrganization().iterator().next().getOrgId();
			organization = organizationDao.getById(SysOrganization.class, id);
			l.add(organization);
		} else {
			organization = organizationDao.getById(SysOrganization.class, id);
		}
		Set<SysOrganization> resource_role = organization.getSysOrganizations();

		if (resource_role != null && resource_role.size() > 0) {
			for (SysOrganization o : resource_role) {
				SysOrganization sub_organization = new SysOrganization();
				BeanUtils.copyProperties(o, sub_organization);
				Set<SysOrganization> set = o.getSysOrganizations();
				if (set != null && !set.isEmpty()) {
					sub_organization.setState("closed");
				}
				l.add(sub_organization);
			}
		}
		return l;
	}

}