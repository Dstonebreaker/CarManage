package com.car.entity.car;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "v_car_returninfo")
public class VCarReturnInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7759106864494430814L;
     private String returnId;
     private String sendId;
     private String sendNo;
     private Date sendTime;
     private Date returnTime;
     private String returnFlag;
     private String sendFlag;
     private String carNo;
     private String orgIdManager;
     private String carId;
	public VCarReturnInfo() {
		super();
	}
	@Id
	@Column(name = "returnId", unique = true, nullable = false, length = 36)
	public String getReturnId() {
		return returnId;
	}
	public void setReturnId(String returnId) {
		this.returnId = returnId;
	}
	@Column(name = "sendId", nullable = false, length = 36)
	public String getSendId() {
		return sendId;
	}
	public void setSendId(String sendId) {
		this.sendId = sendId;
	}
	@Column(name = "sendNo", nullable = false, length = 36)
	public String getSendNo() {
		return sendNo;
	}
	public void setSendNo(String sendNo) {
		this.sendNo = sendNo;
	}
	@Column(name = "sendTime", nullable = false, length = 36)
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	@Column(name = "returnTime", nullable = false, length = 36)
	public Date getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
	@Column(name = "returnFlag", nullable = false, length = 36)
	public String getReturnFlag() {
		return returnFlag;
	}
	public void setReturnFlag(String returnFlag) {
		this.returnFlag = returnFlag;
	}
	@Column(name = "sendFlag", nullable = false, length = 36)
	public String getSendFlag() {
		return sendFlag;
	}
	public void setSendFlag(String sendFlag) {
		this.sendFlag = sendFlag;
	}
	@Column(name = "carNo", nullable = false, length = 36)
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	@Column(name = "orgIdManager",  length = 36)
	public String getOrgIdManager() {
		return orgIdManager;
	}
	public void setOrgIdManager(String orgIdManager) {
		this.orgIdManager = orgIdManager;
	}
	@Column(name = "carId", nullable = false, length = 36)
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
     
}
