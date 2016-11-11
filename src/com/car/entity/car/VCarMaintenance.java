package com.car.entity.car;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * VCarMaintenance entity 保养记录
 */
@Entity
@Table(name = "v_car_maintenance")
public class VCarMaintenance implements java.io.Serializable {

	// Fields

	private String mainId;
	private String carId;
	private String carNo;
	private Double mainInMileage;
	private Double mainMileage;
	private Double mainNextMileage;
	private Date mainTime;
	private Date mainNextTime;
	private Double mainMoney;
	private String mainCondition;
	private String userIdHandler;
	private String dictIdMaintenanceFactory;
	private String userIdCreate;
	private Timestamp timeCreate;
	private String userIdUpdate;
	private Timestamp timeUpdate;
	private String dictIdFlag;
	private String factory;
	private String handlerName;
	private String carModelName;
	private String carmodelId;
	private String mmainId;
	private Integer day;
   // private BigDecimal  mileage;
	private  Integer mileage;
	private String orgIdManager;
	// Constructors

	/** default constructor */
	public VCarMaintenance() {
	}

	/** minimal constructor */
	public VCarMaintenance(String mainId, String carId, String carNo,
			Double mainInMileage, Double mainMileage, Date mainTime,
			Date mainNextTime, Double mainMoney, String mainCondition,
			String userIdHandler, String dictIdMaintenanceFactory,
			String userIdCreate, Timestamp timeCreate, String dictIdFlag) {
		this.mainId = mainId;
		this.carId = carId;
		this.carNo = carNo;
		this.mainInMileage = mainInMileage;
		this.mainMileage = mainMileage;
		this.mainTime = mainTime;
		this.mainNextTime = mainNextTime;
		this.mainMoney = mainMoney;
		this.mainCondition = mainCondition;
		this.userIdHandler = userIdHandler;
		this.dictIdMaintenanceFactory = dictIdMaintenanceFactory;
		this.userIdCreate = userIdCreate;
		this.timeCreate = timeCreate;
		this.dictIdFlag = dictIdFlag;
	}

	/** full constructor */
	public VCarMaintenance(String mainId, String carId, String carNo,
			Double mainInMileage, Double mainMileage, Double mainNextMileage,
			Date mainTime, Date mainNextTime, Double mainMoney,
			String mainCondition, String userIdHandler,
			String dictIdMaintenanceFactory, String userIdCreate,
			Timestamp timeCreate, String userIdUpdate, Timestamp timeUpdate,
			String dictIdFlag, String factory, String handlerName,
			String carModelName, String carmodelId, Integer day) {
		this.mainId = mainId;
		this.carId = carId;
		this.carNo = carNo;
		this.mainInMileage = mainInMileage;
		this.mainMileage = mainMileage;
		this.mainNextMileage = mainNextMileage;
		this.mainTime = mainTime;
		this.mainNextTime = mainNextTime;
		this.mainMoney = mainMoney;
		this.mainCondition = mainCondition;
		this.userIdHandler = userIdHandler;
		this.dictIdMaintenanceFactory = dictIdMaintenanceFactory;
		this.userIdCreate = userIdCreate;
		this.timeCreate = timeCreate;
		this.userIdUpdate = userIdUpdate;
		this.timeUpdate = timeUpdate;
		this.dictIdFlag = dictIdFlag;
		this.factory = factory;
		this.handlerName = handlerName;
		this.carModelName = carModelName;
		this.carmodelId = carmodelId;
		this.day = day;
	}

	// Property accessors
	@Id
	@Column(name = "mainId", nullable = false, length = 36)
	public String getMainId() {
		return this.mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	@Column(name = "carId", nullable = false, length = 36)
	public String getCarId() {
		return this.carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	@Column(name = "carNo", nullable = false, length = 12)
	public String getCarNo() {
		return this.carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	@Column(name = "mainInMileage", nullable = false, precision = 10)
	public Double getMainInMileage() {
		return this.mainInMileage;
	}

	public void setMainInMileage(Double mainInMileage) {
		this.mainInMileage = mainInMileage;
	}

	@Column(name = "mainMileage", nullable = false, precision = 10)
	public Double getMainMileage() {
		return this.mainMileage;
	}

	public void setMainMileage(Double mainMileage) {
		this.mainMileage = mainMileage;
	}

	@Column(name = "mainNextMileage", precision = 10)
	public Double getMainNextMileage() {
		return this.mainNextMileage;
	}

	public void setMainNextMileage(Double mainNextMileage) {
		this.mainNextMileage = mainNextMileage;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "mainTime", nullable = false, length = 10)
	public Date getMainTime() {
		return this.mainTime;
	}

	public void setMainTime(Date mainTime) {
		this.mainTime = mainTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "mainNextTime", nullable = false, length = 10)
	public Date getMainNextTime() {
		return this.mainNextTime;
	}

	public void setMainNextTime(Date mainNextTime) {
		this.mainNextTime = mainNextTime;
	}

	@Column(name = "mainMoney", nullable = false, precision = 10)
	public Double getMainMoney() {
		return this.mainMoney;
	}

	public void setMainMoney(Double mainMoney) {
		this.mainMoney = mainMoney;
	}

	@Column(name = "mainCondition", nullable = false, length = 400)
	public String getMainCondition() {
		return this.mainCondition;
	}

	public void setMainCondition(String mainCondition) {
		this.mainCondition = mainCondition;
	}

	@Column(name = "userIdHandler", nullable = false, length = 36)
	public String getUserIdHandler() {
		return this.userIdHandler;
	}

	public void setUserIdHandler(String userIdHandler) {
		this.userIdHandler = userIdHandler;
	}

	@Column(name = "dictIdMaintenanceFactory", nullable = false, length = 36)
	public String getDictIdMaintenanceFactory() {
		return this.dictIdMaintenanceFactory;
	}

	public void setDictIdMaintenanceFactory(String dictIdMaintenanceFactory) {
		this.dictIdMaintenanceFactory = dictIdMaintenanceFactory;
	}

	@Column(name = "userIdCreate", nullable = false, length = 36)
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

	@Column(name = "userIdUpdate", length = 36)
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

	@Column(name = "dictIdFlag", nullable = false, length = 45)
	public String getDictIdFlag() {
		return this.dictIdFlag;
	}

	public void setDictIdFlag(String dictIdFlag) {
		this.dictIdFlag = dictIdFlag;
	}

	@Column(name = "factory", length = 50)
	public String getFactory() {
		return this.factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	@Column(name = "handlerName", length = 50)
	public String getHandlerName() {
		return this.handlerName;
	}

	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}

	@Column(name = "carModelName", length = 50)
	public String getCarModelName() {
		return this.carModelName;
	}

	public void setCarModelName(String carModelName) {
		this.carModelName = carModelName;
	}

	@Column(name = "carmodelId", length = 36)
	public String getCarmodelId() {
		return this.carmodelId;
	}

	public void setCarmodelId(String carmodelId) {
		this.carmodelId = carmodelId;
	}

	@Column(name = "day")
	public Integer getDay() {
		return this.day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}
	@Column(name = "mileage")
	public Integer getMileage() {
		return mileage;
	}
	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}
	@Column(name = "orgIdManager")
	public String getOrgIdManager() {
		return orgIdManager;
	}

	public void setOrgIdManager(String orgIdManager) {
		this.orgIdManager = orgIdManager;
	}

	public String getMmainId() {
		return mmainId;
	}
	public void setMmainId(String mmainId) {
		this.mmainId = mmainId;
	}
}