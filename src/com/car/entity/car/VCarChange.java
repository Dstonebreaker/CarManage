package com.car.entity.car;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="v_car_change")
public class VCarChange implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -535764465037632026L;
	private String changeId;
    private String carId;
    private String dictIdChangeType;
    private String changeCarNoOld;
    private String changeCarNoNew;
    private String changeUseOld;
    private String changeUseNew;
    private String changeManagerOld;
    private String changeManagerNew;
    private String userIdMasterOld;
    private String userIdMasterNew;
    private String userIdCreate;
    private Date   timeCreate;
    private String useOldOrgName;
    private String useNewOrgName;
    private String manageOldOrgName;
    private String manageNewOrgName;
    private String masterOldName;
    private String masterNewName;
    private String createName;
    private String dictIdFlag;
    private String carNo;
    private String dictIdCarStatus;
    @Id
	@Column(name = "changeId", unique = true, nullable = false, length = 36)
	public String getChangeId() {
		return changeId;
	}
	public void setChangeId(String changeId) {
		this.changeId = changeId;
	}
	@Column(name = "carId", nullable = false, length = 36)
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	@Column(name = "dictIdChangeType", nullable = false, length = 36)
	public String getDictIdChangeType() {
		return dictIdChangeType;
	}
	public void setDictIdChangeType(String dictIdChangeType) {
		this.dictIdChangeType = dictIdChangeType;
	}
	@Column(name = "changeCarNoOld", length = 36)
	public String getChangeCarNoOld() {
		return changeCarNoOld;
	}
	public void setChangeCarNoOld(String changeCarNoOld) {
		this.changeCarNoOld = changeCarNoOld;
	}
	@Column(name = "changeCarNoNew", length = 36)
	public String getChangeCarNoNew() {
		return changeCarNoNew;
	}
	public void setChangeCarNoNew(String changeCarNoNew) {
		this.changeCarNoNew = changeCarNoNew;
	}
	@Column(name = "changeUseOld",  length = 36)
	public String getChangeUseOld() {
		return changeUseOld;
	}
	public void setChangeUseOld(String changeUseOld) {
		this.changeUseOld = changeUseOld;
	}
	@Column(name = "changeUseNew",  length = 36)
	public String getChangeUseNew() {
		return changeUseNew;
	}
	public void setChangeUseNew(String changeUseNew) {
		this.changeUseNew = changeUseNew;
	}
	@Column(name = "changeManagerOld",  length = 36)
	public String getChangeManagerOld() {
		return changeManagerOld;
	}
	public void setChangeManagerOld(String changeManagerOld) {
		this.changeManagerOld = changeManagerOld;
	}
	@Column(name = "changeManagerNew",  length = 36)
	public String getChangeManagerNew() {
		return changeManagerNew;
	}
	public void setChangeManagerNew(String changeManagerNew) {
		this.changeManagerNew = changeManagerNew;
	}
	@Column(name = "userIdMasterOld",  length = 36)
	public String getUserIdMasterOld() {
		return userIdMasterOld;
	}
	public void setUserIdMasterOld(String userIdMasterOld) {
		this.userIdMasterOld = userIdMasterOld;
	}
	@Column(name = "userIdMasterNew",  length = 36)
	public String getUserIdMasterNew() {
		return userIdMasterNew;
	}
	public void setUserIdMasterNew(String userIdMasterNew) {
		this.userIdMasterNew = userIdMasterNew;
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
	@Column(name = "useOldOrgName",  length = 36)
	public String getUseOldOrgName() {
		return useOldOrgName;
	}
	public void setUseOldOrgName(String useOldOrgName) {
		this.useOldOrgName = useOldOrgName;
	}
	@Column(name = "useNewOrgName",  length = 36)
	public String getUseNewOrgName() {
		return useNewOrgName;
	}
	public void setUseNewOrgName(String useNewOrgName) {
		this.useNewOrgName = useNewOrgName;
	}
	@Column(name = "manageOldOrgName",  length = 36)
	public String getManageOldOrgName() {
		return manageOldOrgName;
	}
	public void setManageOldOrgName(String manageOldOrgName) {
		this.manageOldOrgName = manageOldOrgName;
	}
	@Column(name = "manageNewOrgName",  length = 36)
	public String getManageNewOrgName() {
		return manageNewOrgName;
	}
	public void setManageNewOrgName(String manageNewOrgName) {
		this.manageNewOrgName = manageNewOrgName;
	}
	@Column(name = "masterOldName",  length = 36)
	public String getMasterOldName() {
		return masterOldName;
	}
	public void setMasterOldName(String masterOldName) {
		this.masterOldName = masterOldName;
	}
	@Column(name = "masterNewName",  length = 36)
	public String getMasterNewName() {
		return masterNewName;
	}
	public void setMasterNewName(String masterNewName) {
		this.masterNewName = masterNewName;
	}
	@Column(name = "createName",  length = 36)
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	} 
	@Column(name = "dictIdFlag", nullable = false, length = 36)
	public String getDictIdFlag() {
		return dictIdFlag;
	}
	public void setDictIdFlag(String dictIdFlag) {
		this.dictIdFlag = dictIdFlag;
	}
	@Column(name = "carNo", length = 36)
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	@Column(name = "dictIdCarStatus", length = 36)
	public String getDictIdCarStatus() {
		return dictIdCarStatus;
	}
	public void setDictIdCarStatus(String dictIdCarStatus) {
		this.dictIdCarStatus = dictIdCarStatus;
	}
	
}
