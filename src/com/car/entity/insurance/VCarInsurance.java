package com.car.entity.insurance;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * VCarInsurance entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "v_car_insurance")
public class VCarInsurance implements java.io.Serializable {

	// Fields

	private String insuId;
	private String insuNo;
	private String carId;
	private String carNo;
	private String dictIdInsuranceType;
	private String dictIdInsuranceCorp;
	private Date insuStartDate;
	private Date insuOverDate;
	private Double insuMoney;
	private String dictIdFlag;
	private String userIdCreate;
	private Date timeCreate;
	private String userIdUpdate;
	private Date timeUpdate;
	private String typedictName;
	private String corpdictName;
	private int day;
	private Date lastTime;
	private String orgIdManager;
	// Constructors

	/** default constructor */
	public VCarInsurance() {
	}

	/** minimal constructor */
	public VCarInsurance(String insuId, String insuNo, String carId, String carNo, String dictIdInsuranceType, String dictIdInsuranceCorp,
			Date insuStartDate, Date insuOverDate, Double insuMoney, String dictIdFlag, String userIdCreate, Timestamp timeCreate) {
		this.insuId = insuId;
		this.insuNo = insuNo;
		this.carId = carId;
		this.carNo = carNo;
		this.dictIdInsuranceType = dictIdInsuranceType;
		this.dictIdInsuranceCorp = dictIdInsuranceCorp;
		this.insuStartDate = insuStartDate;
		this.insuOverDate = insuOverDate;
		this.insuMoney = insuMoney;
		this.dictIdFlag = dictIdFlag;
		this.userIdCreate = userIdCreate;
		this.timeCreate = timeCreate;
	}

	/** full constructor */
	public VCarInsurance(String insuId, String insuNo, String carId, String carNo, String dictIdInsuranceType, String dictIdInsuranceCorp,
			Date insuStartDate, Date insuOverDate, Double insuMoney, String dictIdFlag, String userIdCreate, Timestamp timeCreate,
			String userIdUpdate, Timestamp timeUpdate, String typedictName, String corpdictName) {
		this.insuId = insuId;
		this.insuNo = insuNo;
		this.carId = carId;
		this.carNo = carNo;
		this.dictIdInsuranceType = dictIdInsuranceType;
		this.dictIdInsuranceCorp = dictIdInsuranceCorp;
		this.insuStartDate = insuStartDate;
		this.insuOverDate = insuOverDate;
		this.insuMoney = insuMoney;
		this.dictIdFlag = dictIdFlag;
		this.userIdCreate = userIdCreate;
		this.timeCreate = timeCreate;
		this.userIdUpdate = userIdUpdate;
		this.timeUpdate = timeUpdate;
		this.typedictName = typedictName;
		this.corpdictName = corpdictName;
	}

	// Property accessors
	@Id
	@Column(name = "insuId", nullable = false, length = 36)
	public String getInsuId() {
		return this.insuId;
	}

	public void setInsuId(String insuId) {
		this.insuId = insuId;
	}

	@Column(name = "insuNo", nullable = false, length = 50)
	public String getInsuNo() {
		return this.insuNo;
	}

	public void setInsuNo(String insuNo) {
		this.insuNo = insuNo;
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

	@Column(name = "dictIdInsuranceType", nullable = false, length = 36)
	public String getDictIdInsuranceType() {
		return this.dictIdInsuranceType;
	}

	public void setDictIdInsuranceType(String dictIdInsuranceType) {
		this.dictIdInsuranceType = dictIdInsuranceType;
	}

	@Column(name = "dictIdInsuranceCorp", nullable = false, length = 36)
	public String getDictIdInsuranceCorp() {
		return this.dictIdInsuranceCorp;
	}

	public void setDictIdInsuranceCorp(String dictIdInsuranceCorp) {
		this.dictIdInsuranceCorp = dictIdInsuranceCorp;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "insuStartDate", nullable = false, length = 10)
	public Date getInsuStartDate() {
		return this.insuStartDate;
	}

	public void setInsuStartDate(Date insuStartDate) {
		this.insuStartDate = insuStartDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "insuOverDate", nullable = false, length = 10)
	public Date getInsuOverDate() {
		return this.insuOverDate;
	}

	public void setInsuOverDate(Date insuOverDate) {
		this.insuOverDate = insuOverDate;
	}

	@Column(name = "insuMoney", nullable = false, precision = 10)
	public Double getInsuMoney() {
		return this.insuMoney;
	}

	public void setInsuMoney(Double insuMoney) {
		this.insuMoney = insuMoney;
	}

	@Column(name = "dictIdFlag", nullable = false, length = 36)
	public String getDictIdFlag() {
		return this.dictIdFlag;
	}

	public void setDictIdFlag(String dictIdFlag) {
		this.dictIdFlag = dictIdFlag;
	}

	@Column(name = "userIdCreate", nullable = false, length = 36)
	public String getUserIdCreate() {
		return this.userIdCreate;
	}

	public void setUserIdCreate(String userIdCreate) {
		this.userIdCreate = userIdCreate;
	}

	@Column(name = "timeCreate", nullable = false, length = 19)
	public Date getTimeCreate() {
		return this.timeCreate;
	}

	public void setTimeCreate(Date timeCreate) {
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
	public Date getTimeUpdate() {
		return this.timeUpdate;
	}

	public void setTimeUpdate(Date timeUpdate) {
		this.timeUpdate = timeUpdate;
	}

	@Column(name = "typedictName", length = 50)
	public String getTypedictName() {
		return this.typedictName;
	}

	public void setTypedictName(String typedictName) {
		this.typedictName = typedictName;
	}

	@Column(name = "corpdictName", length = 50)
	public String getCorpdictName() {
		return this.corpdictName;
	}

	public void setCorpdictName(String corpdictName) {
		this.corpdictName = corpdictName;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
	@Column(name = "orgIdManager")
	public String getOrgIdManager() {
		return orgIdManager;
	}

	public void setOrgIdManager(String orgIdManager) {
		this.orgIdManager = orgIdManager;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
}