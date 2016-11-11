package com.system.entity.maintain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "t_sys_organization", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)

public class SysOrganization implements java.io.Serializable {	
	
	private static final long serialVersionUID = -4444384568178317422L;
	
	private String state = "open";// open,closed
	
	private String orgId; // 主键
    private String dictIdOrgType;
    private String orgIconic;
    private String orgName;
    private String orgNameAbbr;
    private String orgAddress;
    private String orgLinkman;
    private String orgLinkPhone;
    private String orgIdOrgParent;//pid
    private String orgIdManager;
    private String dictIdFlag;
    private String userIdCreate;
    private Date   timeCreate;
    private String userIdUpdate;
    private Date   timeUpdate;
    private String orgPath;
    private Integer orgLevel;
    private String  orgRealPath;
    // 父机构
    private SysOrganization sysOrganization;
    // 子机构
	private Set<SysOrganization> sysOrganizations = new HashSet<SysOrganization>(0);
	// 下辖用户
	private Set<SysUser> sysUsers = new HashSet<SysUser>(0);
	public SysOrganization() {
		super();
	}
	@Id
	@Column(name = "orgId", unique = true, nullable = false, length = 36)
	public String getOrgId() {
		if (!StringUtils.isBlank(this.orgId)) {
			return this.orgId;
		}
		else{
			this.orgId=UUID.randomUUID().toString();
		}
		return this.orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	@Column(name = "dictIdOrgType", nullable = false, length = 36)
	public String getDictIdOrgType() {
		return dictIdOrgType;
	}
	public void setDictIdOrgType(String dictIdOrgType) {
		this.dictIdOrgType = dictIdOrgType;
	}
	@Column(name = "orgIconic", nullable = false, length = 200)
	public String getOrgIconic() {
		return orgIconic;
	}
	public void setOrgIconic(String orgIconic) {
		this.orgIconic = orgIconic;
	}
	@Column(name = "orgName", nullable = false, length = 200)
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	@Column(name = "orgNameAbbr", length = 50)
	public String getOrgNameAbbr() {
		return orgNameAbbr;
	}
	public void setOrgNameAbbr(String orgNameAbbr) {
		this.orgNameAbbr = orgNameAbbr;
	}
	@Column(name = "orgAddress", length = 200)
	public String getOrgAddress() {
		return orgAddress;
	}
	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}
	@Column(name = "orgLinkman", length = 50)
	public String getOrgLinkman() {
		return orgLinkman;
	}
	public void setOrgLinkman(String orgLinkman) {
		this.orgLinkman = orgLinkman;
	}
	@Column(name = "orgLinkPhone", length = 50)
	public String getOrgLinkPhone() {
		return orgLinkPhone;
	}
	public void setOrgLinkPhone(String orgLinkPhone) {
		this.orgLinkPhone = orgLinkPhone;
	}
	@Column(name = "orgIdOrgParent", length = 36)
	public String getOrgIdOrgParent() {
		return orgIdOrgParent;
	}
	public void setOrgIdOrgParent(String orgIdOrgParent) {
		this.orgIdOrgParent = orgIdOrgParent;
	}
	@Column(name = "orgIdManager", nullable = false, length = 36)
	public String getOrgIdManager() {
		return orgIdManager;
	}
	public void setOrgIdManager(String orgIdManager) {
		this.orgIdManager = orgIdManager;
	}
	@Column(name = "dictIdFlag", nullable = false, length = 36)
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
	@Transient
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_sys_user_organization", schema = "", joinColumns = { @JoinColumn(name = "orgId",insertable=false, nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "userId",insertable=false, nullable = false, updatable = false) })
	public Set<SysUser> getSysUsers() {
		return sysUsers;
	}

	public void setSysUsers(Set<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orgIdOrgparent",insertable=false, updatable=false)
	public SysOrganization getSysOrganization() {
		return sysOrganization;
	}

	public void setSysOrganization(SysOrganization sysOrganization) {
		this.sysOrganization = sysOrganization;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysOrganization", cascade = CascadeType.ALL)
	public Set<SysOrganization> getSysOrganizations() {
		return sysOrganizations;
	}

	public void setSysOrganizations(Set<SysOrganization> sysOrganizations) {
		this.sysOrganizations = sysOrganizations;
	}
	@Column(name = "orgPath",  length = 4000)
	public String getOrgPath() {
		return orgPath;
	}
	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
	}
	@Column(name = "orgLevel",  length = 11)
	public Integer getOrgLevel() {
		return orgLevel;
	}
	public void setOrgLevel(Integer orgLevel) {
		this.orgLevel = orgLevel;
	}
	@Column(name = "orgRealPath",  length = 11)
	public String getOrgRealPath() {
		return orgRealPath;
	}
	public void setOrgRealPath(String orgRealPath) {
		this.orgRealPath = orgRealPath;
	}

}