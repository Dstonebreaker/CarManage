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
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "v_sys_organization", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class VSysOrganization implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1792931613038470306L;
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
    private String orgManagerName;
    private String iconCls;
    // 父机构
    private VSysOrganization vsysOrganization;
    // 子机构
	private Set<VSysOrganization> vsysOrganizations = new HashSet<VSysOrganization>(0);
		
	
	@Id
	@Column(name = "orgId", unique = true, nullable = false, length = 36)
	public String getOrgId() {
		if (!StringUtils.isBlank(this.orgId)) {
			return this.orgId;
		}
		return UUID.randomUUID().toString();
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
	@Column(name = "orgManagerName", nullable = false, length = 36)
	public String getOrgManagerName() {
		return orgManagerName;
	}
	public void setOrgManagerName(String orgManagerName) {
		this.orgManagerName = orgManagerName;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orgIdOrgparent",insertable=false, updatable=false)
	public VSysOrganization getVsysOrganization() {
		return vsysOrganization;
	}
	public void setVsysOrganization(VSysOrganization vsysOrganization) {
		this.vsysOrganization = vsysOrganization;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "vsysOrganization", cascade = CascadeType.ALL)
	public Set<VSysOrganization> getVsysOrganizations() {
		return vsysOrganizations;
	}
	public void setVsysOrganizations(Set<VSysOrganization> vsysOrganizations) {
		this.vsysOrganizations = vsysOrganizations;
	}
	@Transient
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
}
