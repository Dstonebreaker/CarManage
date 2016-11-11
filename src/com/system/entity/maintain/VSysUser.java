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
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
@Entity
@Table(name = "v_sys_user", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class VSysUser implements java.io.Serializable {
	
	private static final long serialVersionUID = 4953324493185534207L;
	private String ip;// 此属性不存数据库，虚拟属性
	
	private String userId;
	private String userLogin;
	private String userPwd;
	private String userName;
	private String userPhone;
	private String userMac;
	private String userSign;
	private String dictIdDrivingLlicenseKind;
	private String dictIdFlag;
	private String userIdCreate;
	private Date timeCreate;
	private String userIdUpdate;
	private Date  timeUpdate;
	private String dictName;
	private String orgId;
	private String orgIdManager;
	 // 所属机构
    private Set<SysOrganization> sysOrganization = new HashSet<SysOrganization>(0);
    // 所属角色
	private Set<SysRole> sysRoles = new HashSet<SysRole>(0);
	

	@Id
	@Column(name = "userId", unique = true, nullable = false, length = 36)
	public String getUserId() {
		if (!StringUtils.isBlank(this.userId)) {
			return this.userId;
		}
		return UUID.randomUUID().toString();
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "userLogin", nullable = false, length = 50)
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	@Column(name = "userPwd", nullable = false, length = 50)
	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	@Column(name = "userName", nullable = false, length = 50)
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name = "userPhone",  length = 50)
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	@Column(name = "userMac",  length = 50)
	public String getUserMac() {
		return userMac;
	}
	public void setUserMac(String userMac) {
		this.userMac = userMac;
	}
	@Column(name = "userSign",  length = 36)
	public String getUserSign() {
		return userSign;
	}
	
	public void setUserSign(String userSign) {
		this.userSign = userSign;
	}
	@Column(name = "dictIdDrivingLlicenseKind",  length = 36)
   	public String getDictIdDrivingLlicenseKind() {
		return dictIdDrivingLlicenseKind;
	}

	public void setDictIdDrivingLlicenseKind(String dictIdDrivingLlicenseKind) {
		this.dictIdDrivingLlicenseKind = dictIdDrivingLlicenseKind;
	}

	@Column(name = "dictIdFlag", nullable = false, length = 50)
	public String getDictIdFlag() {
		return dictIdFlag;
	}
	public void setDictIdFlag(String dictIdFlag) {
		this.dictIdFlag = dictIdFlag;
	}
	@Column(name = "userIdCreate", nullable = false, length = 36)
	public String getUserIdCreate() {
		return userIdCreate;
	}
	public void setUserIdCreate(String userIdCreate) {
		this.userIdCreate = userIdCreate;
	}
	@Column(name = "timeCreate", nullable = false)
	public Date getTimeCreate() {
		return timeCreate;
	}
	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}
	@Column(name = "userIdUpdate",  length = 36)
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
	@Column(name = " dictName", nullable = false)
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	@Column(name = "orgId", nullable = false)
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	@Column(name = "orgIdManager")
	public String getOrgIdManager() {
		return orgIdManager;
	}
	public void setOrgIdManager(String orgIdManager) {
		this.orgIdManager = orgIdManager;
	}
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_sys_user_organization", schema = "", joinColumns = { @JoinColumn(name = "userId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "orgId", nullable = false, updatable = false) })
	public Set<SysOrganization> getSysOrganization() {
		return sysOrganization;
	}

	public void setSysOrganization(Set<SysOrganization> sysOrganization) {
		this.sysOrganization = sysOrganization;
	}
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_sys_user_role", schema = "", joinColumns = { @JoinColumn(name = "userId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "roleId", nullable = false, updatable = false) })
	public Set<SysRole> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(Set<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}
	
	@Transient
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}