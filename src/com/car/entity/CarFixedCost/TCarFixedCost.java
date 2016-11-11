package com.car.entity.CarFixedCost;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TCarFixedCost entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_car_fixed_cost")
public class TCarFixedCost implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5851170761166097742L;
	
	
	private String costId;
	private String carId;
	private String carNo;			//车牌号 
	private double cost;			//费用
	private String dictIdcosttype;  //费用类型
	private Date startTime;         //开始时间
	private Date overTime;          //过期时间
	private String dictIdFlag;
	private String userIdUpdate;
	private Timestamp timeUpdate;
	private String userIdCreate;
	private Timestamp timeCreate;

	// Constructors

	/** default constructor */
	public TCarFixedCost() {
	}

	/** minimal constructor */
	public TCarFixedCost(String costId, String carId, String carNo, double cost,
			String dictIdcosttype, Date startTime, Date overTime,
			String dictIdFlag, String userIdCreate, Timestamp timeCreate) {
		this.costId = costId;
		this.carId = carId;
		this.carNo = carNo;
		this.cost = cost;
		this.dictIdcosttype = dictIdcosttype;
		this.startTime = startTime;
		this.overTime = overTime;
		this.dictIdFlag = dictIdFlag;
		this.userIdCreate = userIdCreate;
		this.timeCreate = timeCreate;
	}

	/** full constructor */
	public TCarFixedCost(String costId, String carId, String carNo, double cost,
			String dictIdcosttype, Date startTime, Date overTime,
			String dictIdFlag, String userIdUpdate, Timestamp timeUpdate,
			String userIdCreate, Timestamp timeCreate) {
		this.costId = costId;
		this.carId = carId;
		this.carNo = carNo;
		this.cost = cost;
		this.dictIdcosttype = dictIdcosttype;
		this.startTime = startTime;
		this.overTime = overTime;
		this.dictIdFlag = dictIdFlag;
		this.userIdUpdate = userIdUpdate;
		this.timeUpdate = timeUpdate;
		this.userIdCreate = userIdCreate;
		this.timeCreate = timeCreate;
	}

	// Property accessors
	@Id
	@Column(name = "costId", unique = true, nullable = false, length = 64)
	public String getCostId() {
		return this.costId;
	}

	public void setCostId(String costId) {
		this.costId = costId;
	}

	@Column(name = "carId", nullable = false, length = 64)
	public String getCarId() {
		return this.carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	@Column(name = "carNo", nullable = false, length = 64)
	public String getCarNo() {
		return this.carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	@Column(name = "cost", columnDefinition = "decimal(10,2)", nullable = false)
	public double getCost() {
		return this.cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Column(name = "dictIdcosttype", nullable = false, length = 64)
	public String getDictIdcosttype() {
		return this.dictIdcosttype;
	}

	public void setDictIdcosttype(String dictIdcosttype) {
		this.dictIdcosttype = dictIdcosttype;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "start_time", nullable = false, length = 10)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "over_time", nullable = false, length = 10)
	public Date getOverTime() {
		return this.overTime;
	}

	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}

	@Column(name = "dictIdFlag", nullable = false, length = 45)
	public String getDictIdFlag() {
		return this.dictIdFlag;
	}

	public void setDictIdFlag(String dictIdFlag) {
		this.dictIdFlag = dictIdFlag;
	}

	@Column(name = "userIdUpdate", length = 64, nullable = true)
	public String getUserIdUpdate() {
		return this.userIdUpdate;
	}

	public void setUserIdUpdate(String userIdUpdate) {
		this.userIdUpdate = userIdUpdate;
	}

	@Column(name = "timeUpdate", length = 19, nullable = true)
	public Timestamp getTimeUpdate() {
		return this.timeUpdate;
	}

	public void setTimeUpdate(Timestamp timeUpdate) {
		this.timeUpdate = timeUpdate;
	}

	@Column(name = "userIdCreate", nullable = false, length = 64)
	public String getUserIdCreate() {
		return this.userIdCreate;
	}

	public void setUserIdCreate(String userIdCreate) {
		this.userIdCreate = userIdCreate;
	}

	@Column(name = "timeCreate", nullable = false, length = 19)
	public Timestamp getTimeCreate() {
		return this.timeCreate;
	}

	public void setTimeCreate(Timestamp timeCreate) {
		this.timeCreate = timeCreate;
	}

	@Override
	public String toString() {
		return "TCarFixedCost [costId=" + costId + ", carId=" + carId
				+ ", carNo=" + carNo + ", cost=" + cost + ", dictIdcosttype="
				+ dictIdcosttype + ", startTime=" + startTime + ", overTime="
				+ overTime + ", dictIdFlag=" + dictIdFlag + ", userIdUpdate="
				+ userIdUpdate + ", timeUpdate=" + timeUpdate
				+ ", userIdCreate=" + userIdCreate + ", timeCreate="
				+ timeCreate + "]";
	}

	
	
}