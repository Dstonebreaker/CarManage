package com.sys.entity.staff;

import java.sql.Date;


/**
 * 驾驶员
 * @author Marlon
 *
 */


public class TStaff {
	
	private String stafId;		//id（GUID）	varchar(36)	PRI	NO
	private String orgId;		//单位id	varchar(36)		NO
	private String stafName;	//员工姓名	varchar(50)		NO
	private String stafPhone;	//联系手机	varchar(15)		NO
	private String dictIdDrivingLlicenseKind;	//驾驶员准驾车型	varchar(36)		YES
	private String dictIdFlag;	//标识	varchar(36)		NO
	private String userIdCreate;	//登记人id	varchar(36)		NO
	private Date timeCreate;	//登记时间	datetime		NO
	private String userIdUpdate;	//修改人id	varchar(36)		YES
	private Date timeUpdate;	//修改时间	datetime		YES
	
	
	
	
	
	
	public String getStafId() {
		return stafId;
	}
	public void setStafId(String stafId) {
		this.stafId = stafId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getStafName() {
		return stafName;
	}
	public void setStafName(String stafName) {
		this.stafName = stafName;
	}
	public String getStafPhone() {
		return stafPhone;
	}
	public void setStafPhone(String stafPhone) {
		this.stafPhone = stafPhone;
	}
	public String getDictIdDrivingLlicenseKind() {
		return dictIdDrivingLlicenseKind;
	}
	public void setDictIdDrivingLlicenseKind(String dictIdDrivingLlicenseKind) {
		this.dictIdDrivingLlicenseKind = dictIdDrivingLlicenseKind;
	}
	public String getDictIdFlag() {
		return dictIdFlag;
	}
	public void setDictIdFlag(String dictIdFlag) {
		this.dictIdFlag = dictIdFlag;
	}
	public String getUserIdCreate() {
		return userIdCreate;
	}
	public void setUserIdCreate(String userIdCreate) {
		this.userIdCreate = userIdCreate;
	}
	public Date getTimeCreate() {
		return timeCreate;
	}
	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}
	public String getUserIdUpdate() {
		return userIdUpdate;
	}
	public void setUserIdUpdate(String userIdUpdate) {
		this.userIdUpdate = userIdUpdate;
	}
	public Date getTimeUpdate() {
		return timeUpdate;
	}
	public void setTimeUpdate(Date timeUpdate) {
		this.timeUpdate = timeUpdate;
	}

}
