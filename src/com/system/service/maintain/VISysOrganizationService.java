package com.system.service.maintain;

import java.util.List;

import com.framework.util.HqlFilter;
import com.system.entity.maintain.VSysOrganization;
import com.system.service.base.BaseServiceI;

public interface VISysOrganizationService extends BaseServiceI<VSysOrganization> {
	public List<VSysOrganization> organizationTreeGrid(HqlFilter hqlFilter, String id);
}
