package com.car.action.pgis;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.framework.util.BeanUtils;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Tree;
import com.system.entity.maintain.SysOrganization;
import com.system.service.maintain.SysOrganizationServiceI;


@Namespace("/pgis")
@Action(value = "pgis_SysOrganizationAction")
public class pgis_SysOrganizationAction extends BaseAction<SysOrganization> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2108726085158457213L;
	
	@Autowired
	SysOrganizationServiceI sysOrgservice;

	
	public void doNotNeedSecurity_getSelfAndSubSyorganizationsTreeByMapOrgList(){
		List<SysOrganization> organizations = sysOrgservice.selfAndSubOrganizationTreeGrid(id);
		
		List<Tree> tree = new ArrayList<Tree>();
		
		for (SysOrganization organization : organizations) {
			
			Tree node = new Tree();
			BeanUtils.copyNotNullProperties(organization, node);
			node.setText(organization.getOrgName());
			node.setPid(organization.getOrgIdOrgParent());
			node.setId(organization.getOrgId());
			/*HqlFilter hqlFilter1 = new HqlFilter(getRequest());
			hqlFilter1.addFilter("QUERY_t#userId_S_EQ", ids);
			hqlFilter1.addFilter("QUERY_t#orgId_S_EQ", organization.getOrgId());
			SysUserOrganization  sysUserOrganization =  iSysUserOrganizationService.getByFilter(hqlFilter1);
			if(sysUserOrganization!=null){
			   node.setChecked(true);
			}*/
			tree.add(node);
		}
		
		writeJson(tree);
	}
}
