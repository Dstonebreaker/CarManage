package com.car.entity.car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="v_car_master")
public class VCarMaster implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1056270458810995117L;
	
	private  String userId;
	private  String orgName;
	private  String userName;
	private  String orgIdManager;
	
	
	@Id
	@Column(name = "userId", unique = true, nullable = false, length = 36)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "orgName", length = 36)
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	@Column(name = "userName", length = 36)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name = "orgIdManager", length = 36)
	public String getOrgIdManager() {
		return orgIdManager;
	}
	public void setOrgIdManager(String orgIdManager) {
		this.orgIdManager = orgIdManager;
	}
	
	

}
