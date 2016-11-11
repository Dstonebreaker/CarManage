package com.system.entity.maintain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="v_car_manager_organization")
public class VCarManagerOrganization implements java.io.Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3562194925098787791L;
	private String orgIdManager;
	private String OrgName;
	@Id
	@Column(name = "orgIdManager",unique = true, nullable = false)
	public String getOrgIdManager() {
		return orgIdManager;
	}
	public void setOrgIdManager(String orgIdManager) {
		this.orgIdManager = orgIdManager;
	}
	@Column(name="OrgName")
	public String getOrgName() {
		return OrgName;
	}
	public void setOrgName(String orgName) {
		OrgName = orgName;
	}
	
	
}
