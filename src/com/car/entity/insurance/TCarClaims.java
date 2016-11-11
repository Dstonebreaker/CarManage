package com.car.entity.insurance;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

/**
 * TCarClaims entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_car_claims")
public class TCarClaims implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5703924858884614918L;
	private String claimsId;
	private String insuId;
	private String carId;
	private String carNo;
	private String accidentId;
	private String dictIdResultStatus;
	private String dictIdFlag;
	private String userIdCreate;
	private Date timeCreate;
	private String userIdUpdate;
	private Date timeUpdate;
	private String claimsNo;
	// Constructors

	/** default constructor */
	public TCarClaims() {
	}

	/** minimal constructor */
	public TCarClaims(String claimsId, String insuId, String carId, String carNo, Date accidentTime, String accidentId,
			String dictIdResultStatus, String dictIdFlag, String userIdCreate, Date timeCreate) {
		this.claimsId = claimsId;
		this.insuId = insuId;
		this.carId = carId;
		this.carNo = carNo;
		this.accidentId = accidentId;
		this.dictIdResultStatus = dictIdResultStatus;
		this.dictIdFlag = dictIdFlag;
		this.userIdCreate = userIdCreate;
		this.timeCreate = timeCreate;
	}

	/** full constructor */
	public TCarClaims(String claimsId, String insuId, String carId, String carNo, Timestamp accidentTime, String accidentId,
			String dictIdResultStatus, String dictIdFlag, String userIdCreate, Timestamp timeCreate, String userIdUpdate,
			Timestamp timeUpdate) {
		this.claimsId = claimsId;
		this.insuId = insuId;
		this.carId = carId;
		this.carNo = carNo;
		this.accidentId = accidentId;
		this.dictIdResultStatus = dictIdResultStatus;
		this.dictIdFlag = dictIdFlag;
		this.userIdCreate = userIdCreate;
		this.timeCreate = timeCreate;
		this.userIdUpdate = userIdUpdate;
		this.timeUpdate = timeUpdate;
	}

	// Property accessors
	@Id
	@Column(name = "claimsId", unique = true, nullable = false, length = 36)
	public String getClaimsId() {
		if (!StringUtils.isBlank(this.claimsId)) {
			return claimsId;
		}
		claimsId = UUID.randomUUID().toString();
		return this.claimsId;
	}

	public void setClaimsId(String claimsId) {
		this.claimsId = claimsId;
	}

	@Column(name = "insuId", nullable = false, length = 50)
	public String getInsuId() {
		return this.insuId;
	}

	public void setInsuId(String insuId) {
		this.insuId = insuId;
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


	@Column(name = "accidentId", nullable = false, length = 100)
	public String getAccidentId() {
		return this.accidentId;
	}

	public void setAccidentId(String accidentId) {
		this.accidentId = accidentId;
	}

	@Column(name = "dictIdResultStatus", nullable = false, length = 36)
	public String getDictIdResultStatus() {
		return this.dictIdResultStatus;
	}

	public void setDictIdResultStatus(String dictIdResultStatus) {
		this.dictIdResultStatus = dictIdResultStatus;
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
	@Column(name = "claimsNo", nullable = false, length = 64)
	public String getClaimsNo() {
		return claimsNo;
	}

	public void setClaimsNo(String claimsNo) {
		this.claimsNo = claimsNo;
	}

}