package com.car.entity.car;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.dbutils.wrappers.StringTrimmedResultSet;
import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name = "v_car_used_cost")
public class VCarUsedCost implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5421567882921745585L;
	private String usedCostId;
    private String sendId;
    private String carId;
    private String carNo;
    private double usedCost;
    private String dictIduseCostType;
    private String userIdCreate;
    private Date   timeCreate;
    private String orgName;
    private String  dictName;
    private Date sendTime;
    private Date returnTime;
    private String dictIdFlag;
    private String orgIdManager;
    private String sendNo;
    @Id
	@Column(name = "usedCostId", unique = true, nullable = false, length = 36)
	public String getUsedCostId() {
		if (!StringUtils.isBlank(this.usedCostId)) {
			return this.usedCostId;
		}
		return UUID.randomUUID().toString();
	}
	public void setUsedCostId(String usedCostId) {
		this.usedCostId = usedCostId;
	}
	@Column(name = "sendId", nullable = false, length = 36)
	public String getSendId() {
		return sendId;
	}
	public void setSendId(String sendId) {
		this.sendId = sendId;
	}
	@Column(name = "carId", nullable = false, length = 36)
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	@Column(name = "carNo", nullable = false, length = 36)
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	@Column(name = "usedCost", nullable = false, length = 10)
	public double getUsedCost() {
		return usedCost;
	}
	public void setUsedCost(double usedCost) {
		this.usedCost = usedCost;
	}
	@Column(name = "dictIduseCostType", nullable = false, length = 36)
	public String getDictIduseCostType() {
		return dictIduseCostType;
	}
	public void setDictIduseCostType(String dictIduseCostType) {
		this.dictIduseCostType = dictIduseCostType;
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
	@Column(name = "orgName", nullable = false, length = 36)
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	@Column(name = "dictName", nullable = false, length = 36)
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	@Column(name = "sendTime", nullable = false)
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	@Column(name = "returnTime", nullable = false)
	public Date getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
	@Column(name = "dictIdFlag", nullable = false)
	public String getDictIdFlag() {
		return dictIdFlag;
	}
	public void setDictIdFlag(String dictIdFlag) {
		this.dictIdFlag = dictIdFlag;
	}
	@Column(name = "orgIdManager",length = 36)
	public String getOrgIdManager() {
		return orgIdManager;
	}
	public void setOrgIdManager(String orgIdManager) {
		this.orgIdManager = orgIdManager;
	}
	@Column(name = "sendNo",length = 36)
	public String getSendNo() {
		return sendNo;
	}
	public void setSendNo(String sendNo) {
		this.sendNo = sendNo;
	}
    

}
