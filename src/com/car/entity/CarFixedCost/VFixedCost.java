package com.car.entity.CarFixedCost;
// default package

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * VFixedCost entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "v_car_fixedcost")
public class VFixedCost implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3595501857763407763L;
	private String costId;
	private String carId;
	private String carNo;
	private double cost;
	private Date startTime;
	private Date overTime;
	private String dictIdFlag;
	private String userIdUpdate;
	private Timestamp timeUpdate;
	private String userIdCreate;
	private Timestamp timeCreate;
	private String dictCostId;
	private String dictCostName;
	private String orgIdUse;
	private String orgIdManager;
	private String modelId;
	private Date carTime;

	// Constructors

	/** default constructor */
	public VFixedCost() {
	}

	/** minimal constructor */
	public VFixedCost(String carId, String carNo, double cost, Date startTime,
			Date overTime, String dictIdFlag, String userIdCreate,
			Timestamp timeCreate, String dictCostId, String dictCostName,
			String orgIdManager, String modelId, Date carTime) {
		this.carId = carId;
		this.carNo = carNo;
		this.cost = cost;
		this.startTime = startTime;
		this.overTime = overTime;
		this.dictIdFlag = dictIdFlag;
		this.userIdCreate = userIdCreate;
		this.timeCreate = timeCreate;
		this.dictCostId = dictCostId;
		this.dictCostName = dictCostName;
		this.orgIdManager = orgIdManager;
		this.modelId = modelId;
		this.carTime = carTime;
	}

	/** full constructor */
	public VFixedCost(String carId, String carNo, double cost, Date startTime,
			Date overTime, String dictIdFlag, String userIdUpdate,
			Timestamp timeUpdate, String userIdCreate, Timestamp timeCreate,
			String dictCostId, String dictCostName, String orgIdUse,
			String orgIdManager, String modelId, Date carTime) {
		this.carId = carId;
		this.carNo = carNo;
		this.cost = cost;
		this.startTime = startTime;
		this.overTime = overTime;
		this.dictIdFlag = dictIdFlag;
		this.userIdUpdate = userIdUpdate;
		this.timeUpdate = timeUpdate;
		this.userIdCreate = userIdCreate;
		this.timeCreate = timeCreate;
		this.dictCostId = dictCostId;
		this.dictCostName = dictCostName;
		this.orgIdUse = orgIdUse;
		this.orgIdManager = orgIdManager;
		this.modelId = modelId;
		this.carTime = carTime;
	}

	// Property accessors
	@Id
	@Column(name = "costId", nullable = false, length = 64)
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

	@Column(name = "cost", nullable = false, precision = 2, scale = 0)
	public double getCost() {
		return this.cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
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

	@Column(name = "userIdUpdate", length = 64)
	public String getUserIdUpdate() {
		return this.userIdUpdate;
	}

	public void setUserIdUpdate(String userIdUpdate) {
		this.userIdUpdate = userIdUpdate;
	}

	@Column(name = "timeUpdate", length = 19)
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

	@Column(name = "dictCostId", nullable = false, length = 36)
	public String getDictCostId() {
		return this.dictCostId;
	}

	public void setDictCostId(String dictCostId) {
		this.dictCostId = dictCostId;
	}

	@Column(name = "dictCostName", nullable = false, length = 50)
	public String getDictCostName() {
		return this.dictCostName;
	}

	public void setDictCostName(String dictCostName) {
		this.dictCostName = dictCostName;
	}

	@Column(name = "orgIdUse", length = 36)
	public String getOrgIdUse() {
		return this.orgIdUse;
	}

	public void setOrgIdUse(String orgIdUse) {
		this.orgIdUse = orgIdUse;
	}

	@Column(name = "orgIdManager", nullable = false, length = 36)
	public String getOrgIdManager() {
		return this.orgIdManager;
	}

	public void setOrgIdManager(String orgIdManager) {
		this.orgIdManager = orgIdManager;
	}

	@Column(name = "modelId", nullable = false, length = 36)
	public String getModelId() {
		return this.modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "carTime", nullable = false, length = 10)
	public Date getCarTime() {
		return this.carTime;
	}

	public void setCarTime(Date carTime) {
		this.carTime = carTime;
	}

}