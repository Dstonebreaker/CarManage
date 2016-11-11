package com.car.entity.export;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_car_collect")
public class TCarCollect implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5616130906858322173L;
	private String collectId;
	private String orgName;
	private String collectType;
	private String collectSubType;
	private String collectValue;
	private String collectDate;
	private String userId;
	private Date timeCreate;
	private String dictIdCarType;
	private String userIdPilot;
	private String modelName;
	private String userName;
	private String carMileage;
	private String carNo;

	@Id
	@Column(name = "collectId", unique = true, nullable = false, columnDefinition = "nvarchar(36)")
	public String getCollectId() {
		return collectId;
	}

	public void setCollectId(String collectId) {
		this.collectId = collectId;
	}

	@Column(name = "orgName", columnDefinition = "nvarchar(200)")
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "collectType", columnDefinition = "nvarchar(50)")
	public String getCollectType() {
		return collectType;
	}

	public void setCollectType(String collectType) {
		this.collectType = collectType;
	}

	@Column(name = "collectSubType", columnDefinition = "nvarchar(50)")
	public String getCollectSubType() {
		return collectSubType;
	}

	public void setCollectSubType(String collectSubType) {
		this.collectSubType = collectSubType;
	}

	@Column(name = "collectValue", columnDefinition = "nvarchar(50)")
	public String getCollectValue() {
		return collectValue;
	}

	public void setCollectValue(String collectValue) {
		this.collectValue = collectValue;
	}

	@Column(name = "collectDate", columnDefinition = "nvarchar(12)")
	public String getCollectDate() {
		return collectDate;
	}

	public void setCollectDate(String collectDate) {
		this.collectDate = collectDate;
	}

	@Column(name = "userId", columnDefinition = "nvarchar(50)")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "timeCreate",length=20)
	public Date getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}
	@Column(name = "dictIdCarType", columnDefinition = "nvarchar(36)")
	public String getDictIdCarType() {
		return dictIdCarType;
	}

	public void setDictIdCarType(String dictIdCarType) {
		this.dictIdCarType = dictIdCarType;
	}
	@Column(name = "userIdPilot", columnDefinition = "nvarchar(36)")
	public String getUserIdPilot() {
		return userIdPilot;
	}

	public void setUserIdPilot(String userIdPilot) {
		this.userIdPilot = userIdPilot;
	}
	@Column(name = "modelName", columnDefinition = "nvarchar(50)")
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	@Column(name = "userName", columnDefinition = "nvarchar(50)")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name = "carMileage", columnDefinition = "nvarchar(50)")
	public String getCarMileage() {
		return carMileage;
	}
	public void setCarMileage(String carMileage) {
		this.carMileage = carMileage;
	}
	@Column(name = "carNo", columnDefinition = "nvarchar(12)")
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

}
