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
@Table(name = "t_sys_resource", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysResource implements java.io.Serializable {
	
	private static final long serialVersionUID = 4656906405343088203L;
		
	private String resoId;
	private String resoNo;
	private String dictIdResoType;
	private Integer resoIndex;
	private String resoName;
	private String resoUrl;
	private String dictIdFlag;
	private String resoIdParent;
	private String userIdCreate;
	private Date   timeCreate;
	private String userIdUpdate;
	private Date   timeUpdate;
	    // 资源类型
		private TsysDictionary sysDictionary;
		// 有效标识
		private TsysDictionary sysDictionarys;
		// 归属角色
		private Set<SysRole> sysRoles = new HashSet<SysRole>(0);
	    // 上级资源
		private SysResource sysResource;
		// 下级资源
		private Set<SysResource> sysResources = new HashSet<SysResource>(0);
	public SysResource() {
		super();
	}
	public SysResource(String resoId, String resoNo, String dictIdResoType,
			Integer resoIndex, String resoName, String resoUrl,
			String dictIdFlag, String resoIdParent, String userIdCreate,
			Date timeCreate, String userIdUpdate, Date timeUpdate) {
		super();
		this.resoId = resoId;
		this.resoNo = resoNo;
		this.dictIdResoType = dictIdResoType;
		this.resoIndex = resoIndex;
		this.resoName = resoName;
		this.resoUrl = resoUrl;
		this.dictIdFlag = dictIdFlag;
		this.resoIdParent = resoIdParent;
		this.userIdCreate = userIdCreate;
		this.timeCreate = timeCreate;
		this.userIdUpdate = userIdUpdate;
		this.timeUpdate = timeUpdate;
	}
	@Id
	@Column(name = "resoId", unique = true, nullable = false, length = 36)
	public String getResoId() {
		if (!StringUtils.isBlank(this.resoId)) {
			return this.resoId;
		}
		return UUID.randomUUID().toString();
	}
	public void setResoId(String resoId) {
		this.resoId = resoId;
	}
	@Column(name = "resoNo", length = 50)
	public String getResoNo() {
		return resoNo;
	}
	public void setResoNo(String resoNo) {
		this.resoNo = resoNo;
	}
	@Column(name = "dictIdResoType", nullable = false, length = 36)
	public String getDictIdResoType() {
		return dictIdResoType;
	}
	public void setDictIdResoType(String dictIdResoType) {
		this.dictIdResoType = dictIdResoType;
	}
	@Column(name = "resoIndex", nullable = false,precision = 11)
	public Integer getResoIndex() {
		return resoIndex;
	}
	public void setResoIndex(Integer resoIndex) {
		this.resoIndex = resoIndex;
	}
	@Column(name = "resoName", nullable = false,length = 50)
	public String getResoName() {
		return resoName;
	}
	public void setResoName(String resoName) {
		this.resoName = resoName;
	}
	@Column(name = "resoUrl", nullable = false,length = 400)
	public String getResoUrl() {
		return resoUrl;
	}
	public void setResoUrl(String resoUrl) {
		this.resoUrl = resoUrl;
	}
	@Column(name = "dictIdFlag", nullable = false,length = 36)
	public String getDictIdFlag() {
		return dictIdFlag;
	}
	public void setDictIdFlag(String dictIdFlag) {
		this.dictIdFlag = dictIdFlag;
	}
	@Column(name = "resoIdParent", length = 36)
	public String getResoIdParent() {
		return resoIdParent;
	}
	public void setResoIdParent(String resoIdParent) {
		this.resoIdParent = resoIdParent;
	}
	@Column(name = "userIdCreate", nullable = false,length = 36)
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
	@Column(name = "userIdUpdate", length = 36)
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
	@JoinTable(name = "t_sys_role_resource", schema = "", joinColumns = { @JoinColumn(name = "resoId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "roleId", nullable = false, updatable = false) })
	public Set<SysRole> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(Set<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resoIdParent",insertable=false, updatable=false)
	public SysResource getSysResource() {
		return sysResource;
	}

	public void setSysResource(SysResource sysResource) {
		this.sysResource = sysResource;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sysResource", cascade = CascadeType.ALL)
	public Set<SysResource> getSysResources() {
		return sysResources;
	}

	public void setSysResources(Set<SysResource> sysResources) {
		this.sysResources = sysResources;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dictIdResoType", insertable = false, updatable = false)
	public TsysDictionary getSysDictionary() {
		return sysDictionary;
	}
	public void setSysDictionary(TsysDictionary sysDictionary) {
		this.sysDictionary = sysDictionary;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dictIdFlag", insertable = false, updatable = false)
	public TsysDictionary getSysDictionarys() {
		return sysDictionarys;
	}
	public void setSysDictionarys(TsysDictionary sysDictionarys) {
		this.sysDictionarys = sysDictionarys;
	}
		
}