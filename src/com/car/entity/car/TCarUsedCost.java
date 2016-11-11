package com.car.entity.car;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name = "t_car_used_cost")
public class TCarUsedCost implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7323809427179942935L;
    private String usedCostId;
    private String sendId;
    private String carId;
    private String carNo;
    private BigDecimal usedCost;
    private String dictIduseCostType;
    private String userIdCreate;
    private Date   timeCreate;
    
    
	public TCarUsedCost() {
		super();
	}
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
	public BigDecimal getUsedCost() {
		return usedCost;
	}
	public void setUsedCost(BigDecimal usedCost) {
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
    
}
