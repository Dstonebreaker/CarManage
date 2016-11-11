package com.system.entity.maintain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "t_sys_user_role", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysUserRole implements java.io.Serializable{

	private static final long serialVersionUID = -6280971913829227224L;
	
	private String userId;
	private String roleId;
	
	public SysUserRole() {
		super();
	}
	
	public SysUserRole(String userId, String roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
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
	@Column(name = "roleId", unique = true, nullable = false, length = 36)
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	

}
