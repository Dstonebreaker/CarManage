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

import com.alibaba.fastjson.annotation.JSONField;



@Entity
@Table(name = "t_sys_user", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysUser implements java.io.Serializable {
	
	private static final long serialVersionUID = 4953324493185534207L;
	private String ip;// 此属性不存数据库，虚拟属性
	
	private String userId;
	private String userLogin;
	private String userPwd;
	private String userName;
	private String userPhone;
	private String userMac;
	private byte[]   userSign;
	private String dictIdDrivingLlicenseKind;
	private String dictIdFlag;
	private String userIdCreate;
	private Date timeCreate;
	private String userIdUpdate;
	private Date  timeUpdate;
	private String dictIdUserType;
	 // 所属机构
    private Set<SysOrganization> sysOrganization = new HashSet<SysOrganization>(0);
    // 所属角色
	private Set<SysRole> sysRoles = new HashSet<SysRole>(0);
	public SysUser() {
		super();
	}
	
	public SysUser(String userId, String userLogin, String userPwd,
			String userName, String userPhone, String userMac,
			String dictIdFlag, String userIdCreate, Date timeCreate,
			String userIdUpdate, Date timeUpdate) {
		super();
		this.userId = userId;
		this.userLogin = userLogin;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userPhone = userPhone;
		this.userMac = userMac;
		this.dictIdFlag = dictIdFlag;
		this.userIdCreate = userIdCreate;
		this.timeCreate = timeCreate;
		this.userIdUpdate = userIdUpdate;
		this.timeUpdate = timeUpdate;
	}

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
	@JSONField(serialize = false)
	@Column(name = "userSign")
	public byte[] getUserSign() {
		return userSign;
	}
	public void setUserSign(byte[] userSign) {
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
	@Column(name ="dictIdUserType",  length = 36)
	public String getDictIdUserType() {
		return dictIdUserType;
	}

	public void setDictIdUserType(String dictIdUserType) {
		this.dictIdUserType = dictIdUserType;
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