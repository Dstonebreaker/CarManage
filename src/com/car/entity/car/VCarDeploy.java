package com.car.entity.car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="v_car_deploy")
public class VCarDeploy  implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1241031095897655843L;
    private String carId;
    private String orgIdManager;
    private String carNo;
    private String carEngineNo;
    private String carIdentifyNo;
    private String dictIdFlag;
    private String deployId;
    private String dictIdUserType;
    private String dictIdUse;
    private String orgId;
    private  String dictIdCarStatus;
    private boolean checked;
    
    @Id
	@Column(name = "carId", unique = true, nullable = false, length = 36)
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	@Column(name = "orgIdManager", nullable = false, length = 36)
	public String getOrgIdManager() {
		return orgIdManager;
	}
	public void setOrgIdManager(String orgIdManager) {
		this.orgIdManager = orgIdManager;
	}
	@Column(name = "carNo", nullable = false, length = 36)
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	@Column(name = "carEngineNo", nullable = false, length = 36)
	public String getCarEngineNo() {
		return carEngineNo;
	}
	public void setCarEngineNo(String carEngineNo) {
		this.carEngineNo = carEngineNo;
	}
	@Column(name = "carIdentifyNo", nullable = false, length = 36)
	public String getCarIdentifyNo() {
		return carIdentifyNo;
	}
	public void setCarIdentifyNo(String carIdentifyNo) {
		this.carIdentifyNo = carIdentifyNo;
	}
	@Column(name = "dictIdFlag", nullable = false, length = 36)
	public String getDictIdFlag() {
		return dictIdFlag;
	}
	public void setDictIdFlag(String dictIdFlag) {
		this.dictIdFlag = dictIdFlag;
	}
	@Column(name = "deployId", length = 36)
	public String getDeployId() {
		return deployId;
	}
	public void setDeployId(String deployId) {
		this.deployId = deployId;
	}
	@Column(name = "dictIdUserType", length = 36)
	public String getDictIdUserType() {
		return dictIdUserType;
	}
	public void setDictIdUserType(String dictIdUserType) {
		this.dictIdUserType = dictIdUserType;
	}
	@Column(name = "dictIdUse", length = 36)
	public String getDictIdUse() {
		return dictIdUse;
	}
	public void setDictIdUse(String dictIdUse) {
		this.dictIdUse = dictIdUse;
	}
	@Column(name = "orgId", length = 36)
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	@Transient
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	@Column(name = "dictIdCarStatus")
	public String getDictIdCarStatus() {
		return dictIdCarStatus;
	}
	public void setDictIdCarStatus(String dictIdCarStatus) {
		this.dictIdCarStatus = dictIdCarStatus;
	}
	
}
