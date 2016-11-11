package com.system.entity.maintain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "t_sys_user_organization", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysUserOrganization implements java.io.Serializable{

	private static final long serialVersionUID = 1667268396254137086L;
	
	private String userId;
	private String orgId;
	
	public SysUserOrganization() {
		super();
	}
	public SysUserOrganization(String userId, String orgId) {
		super();
		this.userId = userId;
		this.orgId = orgId;
	}
	@Id
	@Column(name = "userId", unique = true, nullable = false, length = 36)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Id
	@Column(name = "orgId", unique = true, nullable = false, length = 36)
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	

}
