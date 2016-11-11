package com.car.entity.insurance;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * VCarClaims entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "v_car_claims")
public class VCarClaims implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -395521910694674164L;
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
	private Date acciTime;
	private String orgIdManager;
	/**
	 * 理赔状态
	 */
	private String insuranceStatus;
	/**
	 * 保险单号
	 */
	private String insuNo;
	/**
	 * 事故地址
	 */
	private String acciAddress;
	/**
	 * 事故性质
	 */
	private String acciType;

	// Constructors

	/** default constructor */
	public VCarClaims() {
	}

	/** minimal constructor */
	public VCarClaims(String claimsId, String insuId, String carId, String carNo, Date accidentTime, String accidentId,
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
	public VCarClaims(String claimsId, String insuId, String carId, String carNo, Date accidentTime, String accidentId,
			String dictIdResultStatus, String dictIdFlag, String userIdCreate, Date timeCreate, String userIdUpdate,
			Date timeUpdate, String insuranceStatus, String insuNo, String acciAddress, String acciType) {
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
		this.insuranceStatus = insuranceStatus;
		this.insuNo = insuNo;
		this.acciAddress = acciAddress;
		this.acciType = acciType;
	}

	// Property accessors
	@Id
	@Column(name = "claimsId", nullable = false, length = 36)
	public String getClaimsId() {
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

	@Column(name = "insuranceStatus", length = 50)
	public String getInsuranceStatus() {
		return this.insuranceStatus;
	}

	public void setInsuranceStatus(String insuranceStatus) {
		this.insuranceStatus = insuranceStatus;
	}

	@Column(name = "insuNo", length = 50)
	public String getInsuNo() {
		return this.insuNo;
	}

	public void setInsuNo(String insuNo) {
		this.insuNo = insuNo;
	}

	@Column(name = "acciAddress", length = 100)
	public String getAcciAddress() {
		return this.acciAddress;
	}

	public void setAcciAddress(String acciAddress) {
		this.acciAddress = acciAddress;
	}

	@Column(name = "acciType", length = 50)
	public String getAcciType() {
		return this.acciType;
	}

	public void setAcciType(String acciType) {
		this.acciType = acciType;
	}

	public String getClaimsNo() {
		return claimsNo;
	}

	public void setClaimsNo(String claimsNo) {
		this.claimsNo = claimsNo;
	}

	public Date getAcciTime() {
		return acciTime;
	}

	public void setAcciTime(Date acciTime) {
		this.acciTime = acciTime;
	}

	public String getOrgIdManager() {
		return orgIdManager;
	}
	public void setOrgIdManager(String orgIdManager) {
		this.orgIdManager = orgIdManager;
	}
     
}