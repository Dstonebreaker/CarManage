package com.system.entity.maintain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Table(name = "t_sys_role_resource", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysRoleResource  implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1311033388617980973L;
	
	private String roleId;
	private String resoId;
	
	public SysRoleResource() {
		super();
	}
	public SysRoleResource(String roleId, String resoId) {
		super();
		this.roleId = roleId;
		this.resoId = resoId;
	}
	@Id
	@Column(name = "roleId", unique = true, nullable = false, length = 36)
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	@Id
	@Column(name = "roleId", unique = true, nullable = false, length = 36)
	public String getResoId() {
		return resoId;
	}
	public void setResoId(String resoId) {
		this.resoId = resoId;
	}
	
	

}
