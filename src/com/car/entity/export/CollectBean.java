package com.car.entity.export;


import java.sql.Timestamp;

public class CollectBean implements java.io.Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int index;
	private String collectId;
	private String orgName;
	private String collectType;
	private String collectSubType;
	private int collectValue;
	private String collectDate;
	private String userId;
	private Timestamp timeCreate;
	private String dictIdCarType;
	private String userIdPilot;
	private String modelName;
	private String userName;
	private String carMileage;
	private String carNo;
	


	
	
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getCollectId() {
		return collectId;
	}
	public void setCollectId(String collectId) {
		this.collectId = collectId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getCollectType() {
		return collectType;
	}
	public void setCollectType(String collectType) {
		this.collectType = collectType;
	}
	public String getCollectSubType() {
		return collectSubType;
	}
	public void setCollectSubType(String collectSubType) {
		this.collectSubType = collectSubType;
	}
	public int getCollectValue() {
		return collectValue;
	}
	public void setCollectValue(int collectValue) {
		this.collectValue = collectValue;
	}
	public String getCollectDate() {
		return collectDate;
	}
	public void setCollectDate(String collectDate) {
		this.collectDate = collectDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Timestamp getTimeCreate() {
		return timeCreate;
	}
	public void setTimeCreate(Timestamp timeCreate) {
		this.timeCreate = timeCreate;
	}
	public String getDictIdCarType() {
		return dictIdCarType;
	}
	public void setDictIdCarType(String dictIdCarType) {
		this.dictIdCarType = dictIdCarType;
	}
	public String getUserIdPilot() {
		return userIdPilot;
	}
	public void setUserIdPilot(String userIdPilot) {
		this.userIdPilot = userIdPilot;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCarMileage() {
		return carMileage;
	}
	public void setCarMileage(String carMileage) {
		this.carMileage = carMileage;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	
	
}
