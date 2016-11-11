package com.system.service.maintain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.framework.util.BeanUtils;
import com.framework.util.HqlFilter;
import com.system.entity.maintain.VSysOrganization;
import com.system.service.base.BaseServiceImpl;

@Service
public class VSysOrganizationServiceImpl extends BaseServiceImpl<VSysOrganization> implements VISysOrganizationService{

	@Override
	public List<VSysOrganization> organizationTreeGrid(HqlFilter hqlFilter,
			String id) {
		List<VSysOrganization> l = new ArrayList<VSysOrganization>();
		String hql = null;
		if (id == null || id.equals("")) {
			hql = "select distinct t from VSysOrganization t where t.orgIdOrgParent = Null and t.dictIdFlag ='YX'";
		} else {
			hql = "select distinct t from VSysOrganization t";
			hqlFilter.addFilter("QUERY_t#orgIdOrgParent_S_EQ", id);
			hqlFilter.addFilter("QUERY_t#dictIdFlag_S_EQ", "YX");
		}
		List<VSysOrganization> resource_role = find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());		
		if (resource_role != null && resource_role.size() > 0) {
			for(VSysOrganization o : resource_role) {
				o.setIconCls(o.getOrgIconic());
				VSysOrganization organization = new VSysOrganization();
				BeanUtils.copyProperties(o, organization);
				Set<VSysOrganization> set = o.getVsysOrganizations();
				if (set != null && !set.isEmpty()) {
					for(VSysOrganization vo : set){
						if(vo.getDictIdFlag().equals("YX")){
							organization.setState("closed");
							break;
						}
					}
				}
				l.add(organization);
			}
		}
		return l;
	}

}
