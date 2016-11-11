package com.system.entity.maintain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;



@Entity
@Table(name = "t_sys_staff", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysStaff implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4098509557227844072L;
      private String  stafId;
	  private String  orgId;
	  private String stafName;
	  private String stafPhone;
	  private String dictIdDrivingLlicenseKind;
	  private String dictIdFlag;
	  private String userIdcreate;
	  private Date timeCreate;
	  private String userIdUpdate;
	  private Date   tiemUpdate;
	  
	public SysStaff() {
		super();
	}
	public SysStaff(String stafId, String orgId, String stafName,
			String stafPhone, String dictIdDrivingLlicenseKind,
			String dictIdFlag, String userIdcreate, Date timeCreate,
			String userIdUpdate, Date tiemUpdate) {
		super();
		this.stafId = stafId;
		this.orgId = orgId;
		this.stafName = stafName;
		this.stafPhone = stafPhone;
		this.dictIdDrivingLlicenseKind = dictIdDrivingLlicenseKind;
		this.dictIdFlag = dictIdFlag;
		this.userIdcreate = userIdcreate;
		this.timeCreate = timeCreate;
		this.userIdUpdate = userIdUpdate;
		this.tiemUpdate = tiemUpdate;
	}
	@Id
	@Column(name = "stafId", unique = true, nullable = false, length = 36)
	public String getStafId() {
		return stafId;
	}
	public void setStafId(String stafId) {
		this.stafId = stafId;
	}
	@Column(name = "orgId", nullable = false, length = 36)
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	@Column(name = "stafName", nullable = false, length = 50)
	public String getStafName() {
		return stafName;
	}
	public void setStafName(String stafName) {
		this.stafName = stafName;
	}
	@Column(name = "stafPhone", nullable = false, length = 15)
	public String getStafPhone() {
		return stafPhone;
	}
	public void setStafPhone(String stafPhone) {
		this.stafPhone = stafPhone;
	}
	@Column(name = "dictIdDrivingLlicenseKind", length = 36)
	public String getDictIdDrivingLlicenseKind() {
		return dictIdDrivingLlicenseKind;
	}
	public void setDictIdDrivingLlicenseKind(String dictIdDrivingLlicenseKind) {
		this.dictIdDrivingLlicenseKind = dictIdDrivingLlicenseKind;
	}
	@Column(name = "dictIdFlag", nullable = false, length = 36)
	public String getDictIdFlag() {
		return dictIdFlag;
	}
	public void setDictIdFlag(String dictIdFlag) {
		this.dictIdFlag = dictIdFlag;
	}
	@Column(name = "userIdcreate", nullable = false, length = 36)
	public String getUserIdcreate() {
		return userIdcreate;
	}
	public void setUserIdcreate(String userIdcreate) {
		this.userIdcreate = userIdcreate;
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
	@Column(name = "tiemUpdate")
	public Date getTiemUpdate() {
		return tiemUpdate;
	}
	public void setTiemUpdate(Date tiemUpdate) {
		this.tiemUpdate = tiemUpdate;
	}
	  
}
