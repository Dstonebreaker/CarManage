package com.car.entity.car;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name="t_car_deploy")
public class TCarDeploy implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7508295323968615681L;
	private String deployId;
	private String deployNo;
	private String orgId;
	private String carId;
	private String dictIdUserType;
	private String dictIdUse;
    private Date   deployTime;
    private String userIdCreate;
    private Date   timeCreate;
    
    
    @Id
	@Column(name = "deployId", unique = true, nullable = false, length = 36)
	public String getDeployId() {
    	if (!StringUtils.isBlank(this.deployId)) {
			return this.deployId;
		}
		return UUID.randomUUID().toString();
	}
	public void setDeployId(String deployId) {
		this.deployId = deployId;
	}
	@Column(name = "deployNo", nullable = false, length = 36)
	public String getDeployNo() {
		return deployNo;
	}
	public void setDeployNo(String deployNo) {
		this.deployNo = deployNo;
	}
	@Column(name = "orgId", nullable = false, length = 36)
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	@Column(name = "carId", nullable = false, length = 36)
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	@Column(name = "dictIdUserType", nullable = false, length = 36)
	public String getDictIdUserType() {
		return dictIdUserType;
	}
	public void setDictIdUserType(String dictIdUserType) {
		this.dictIdUserType = dictIdUserType;
	}
	@Column(name = "dictIdUse", nullable = false, length = 36)
	public String getDictIdUse() {
		return dictIdUse;
	}
	public void setDictIdUse(String dictIdUse) {
		this.dictIdUse = dictIdUse;
	}
	@Column(name = "deployTime", nullable = false)
	public Date getDeployTime() {
		return deployTime;
	}
	public void setDeployTime(Date deployTime) {
		this.deployTime = deployTime;
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
    
    
    
    
}
