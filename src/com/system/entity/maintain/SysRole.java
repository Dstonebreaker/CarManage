package com.system.entity.maintain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "t_sys_role", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysRole implements java.io.Serializable {

	private static final long serialVersionUID = -1185421585372080937L;
	
	private String roleId; // 主键
	private String roleName; // 角色名称
	private String roleMemo; // 角色描述
	private String userIdCreate;
	private Date   timeCreate;
	private String userIdUpdate;
	private Date timeUpdate;
	
	 // 下属用户
    private Set<SysUser> sysUsers = new HashSet<SysUser>(0);
    // 下属资源
	private Set<SysResource> sysResources = new HashSet<SysResource>(0);
	public SysRole() {
		super();
	}
	public SysRole(String roleId, String roleName, String roleMemo,
			String userIdCreate, Date timeCreate, String userIdUpdate,
			Date timeUpdate) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleMemo = roleMemo;
		this.userIdCreate = userIdCreate;
		this.timeCreate = timeCreate;
		this.userIdUpdate = userIdUpdate;
		this.timeUpdate = timeUpdate;
	}
	@Id
	@Column(name = "roleId", unique = true, nullable = false, length = 36)
	public String getRoleId() {
		if (!StringUtils.isBlank(this.roleId)) {
			return this.roleId;
		}
		return UUID.randomUUID().toString();
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	@Column(name = "roleName",  nullable = false, length = 50)
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Column(name = "roleMemo",  nullable = false, length =200)
	public String getRoleMemo() {
		return roleMemo;
	}
	public void setRoleMemo(String roleMemo) {
		this.roleMemo = roleMemo;
	}
	@Column(name = "userIdCreate",  nullable = false, length =36)
	public String getUserIdCreate() {
		return userIdCreate;
	}
	public void setUserIdCreate(String userIdCreate) {
		this.userIdCreate = userIdCreate;
	}
	@Column(name = "timeCreate",  nullable = false)
	public Date getTimeCreate() {
		return timeCreate;
	}
	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}
	@Column(name = "userIdUpdate",  length =36)
	public String getUserIdUpdate() {
		return userIdUpdate;
	}
	public void setUserIdUpdate(String userIdUpdate) {
		this.userIdUpdate = userIdUpdate;
	}
	@Column(name = "timeUpdate")
	public Date getTimeUpdate() {
		return timeUpdate;
	}
	public void setTimeUpdate(Date timeUpdate) {
		this.timeUpdate = timeUpdate;
	}
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_sys_user_role", schema = "", joinColumns = { @JoinColumn(name = "roleId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "userId", nullable = false, updatable = false) })
	public Set<SysUser> getSysUsers() {
		return sysUsers;
	}

	public void setSysUsers(Set<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_sys_role_resource", schema = "", joinColumns = { @JoinColumn(name = "roleId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "resoId", nullable = false, updatable = false) })
	public Set<SysResource> getSysResources() {
		return sysResources;
	}

	public void setSysResources(Set<SysResource> sysResources) {
		this.sysResources = sysResources;
	}
	
}