package com.car.entity.car;



import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name = "t_car_change")
public class TCarChange implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6891968667584136443L;
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
   
    @Id
	@Column(name = "changeId", unique = true, nullable = false, length = 36)
	public String getChangeId() {
    	if (!StringUtils.isBlank(this.changeId)) {
			return this.changeId;
		}
		return UUID.randomUUID().toString();
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
	@Column(name = "timeCreate", nullable = false, length = 36)
	public Date getTimeCreate() {
		return timeCreate;
	}
	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}
    
   
}
