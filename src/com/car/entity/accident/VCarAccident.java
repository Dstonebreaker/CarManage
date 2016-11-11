package com.car.entity.accident;
// default package

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * VCarAccident entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "v_car_accident")
public class VCarAccident implements java.io.Serializable {

	// Fields

	private String acciId;
	private String acciState;
	private String carNo;
	private String acciType;
	private Timestamp acciTime;
	private String carId;
	private String sendId;
	private String acciExplain;
	private Integer acciDeductMark;
	private Integer acciRiskProportion;
	private Double acciInsuranceMoney;
	private Double acciSelfMoney;
	private String userIdCreate;
	private Timestamp timeCreate;
	private String userIdUpdate;
	private Timestamp timeUpdate;
	private String acciAddress;
	private String acciNo;
	private String dictIdFlag;
	private String ownerCompany;
	private String stafName;
	private String acciRisk;
	private String acciStateId;
	private String acciTypeId;
	private String acciRiskId;
	private String orgIdManager;
	
	
	
	// Constructors

	/** default constructor */
	public VCarAccident() {
	}

	/** minimal constructor */
	public VCarAccident(String acciId, String acciState, String carNo,
			String acciType, Timestamp acciTime, String acciExplain,
			Integer acciDeductMark, Integer acciRiskProportion,
			Double acciInsuranceMoney, Double acciSelfMoney,
			String userIdCreate, Timestamp timeCreate, String acciAddress,
			String acciNo, String dictIdFlag, String ownerCompany,
			String stafName, String acciRisk, String acciStateId,
			String acciTypeId, String acciRiskId, String carId,
			String orgIdManager, String dictIdCarStatus) {
		this.acciId = acciId;
		this.acciState = acciState;
		this.carNo = carNo;
		this.acciType = acciType;
		this.acciTime = acciTime;
		this.acciExplain = acciExplain;
		this.acciDeductMark = acciDeductMark;
		this.acciRiskProportion = acciRiskProportion;
		this.acciInsuranceMoney = acciInsuranceMoney;
		this.acciSelfMoney = acciSelfMoney;
		this.userIdCreate = userIdCreate;
		this.timeCreate = timeCreate;
		this.acciAddress = acciAddress;
		this.acciNo = acciNo;
		this.dictIdFlag = dictIdFlag;
		this.ownerCompany = ownerCompany;
		this.stafName = stafName;
		this.acciRisk = acciRisk;
		this.acciStateId = acciStateId;
		this.acciTypeId = acciTypeId;
		this.acciRiskId = acciRiskId;
		this.carId = carId;
		this.orgIdManager = orgIdManager;
		
	}

	/** full constructor */
	public VCarAccident(String acciId, String acciState, String carNo,
			String acciType, Timestamp acciTime, String sendId,
			String acciExplain, Integer acciDeductMark,
			Integer acciRiskProportion, Double acciInsuranceMoney,
			Double acciSelfMoney, String userIdCreate, Timestamp timeCreate,
			String userIdUpdate, Timestamp timeUpdate, String acciAddress,
			String acciNo, String dictIdFlag, String ownerCompany,
			String stafName, String acciRisk, String acciStateId,
			String acciTypeId, String acciRiskId, String carId,
			String orgIdManager, String dictIdCarStatus) {
		this.acciId = acciId;
		this.acciState = acciState;
		this.carNo = carNo;
		this.acciType = acciType;
		this.acciTime = acciTime;
		this.sendId = sendId;
		this.acciExplain = acciExplain;
		this.acciDeductMark = acciDeductMark;
		this.acciRiskProportion = acciRiskProportion;
		this.acciInsuranceMoney = acciInsuranceMoney;
		this.acciSelfMoney = acciSelfMoney;
		this.userIdCreate = userIdCreate;
		this.timeCreate = timeCreate;
		this.userIdUpdate = userIdUpdate;
		this.timeUpdate = timeUpdate;
		this.acciAddress = acciAddress;
		this.acciNo = acciNo;
		this.dictIdFlag = dictIdFlag;
		this.ownerCompany = ownerCompany;
		this.stafName = stafName;
		this.acciRisk = acciRisk;
		this.acciStateId = acciStateId;
		this.acciTypeId = acciTypeId;
		this.acciRiskId = acciRiskId;
		this.carId = carId;
		this.orgIdManager = orgIdManager;
	
	}

	// Property accessors
	@Id
	@Column(name = "acciId", nullable = false, length = 36)
	public String getAcciId() {
		return this.acciId;
	}

	public void setAcciId(String acciId) {
		this.acciId = acciId;
	}

	@Column(name = "acciState", nullable = false, length = 50)
	public String getAcciState() {
		return this.acciState;
	}

	public void setAcciState(String acciState) {
		this.acciState = acciState;
	}

	@Column(name = "carNo", nullable = false, length = 12)
	public String getCarNo() {
		return this.carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	@Column(name = "acciType", nullable = false, length = 50)
	public String getAcciType() {
		return this.acciType;
	}

	public void setAcciType(String acciType) {
		this.acciType = acciType;
	}

	@Column(name = "acciTime", nullable = false, length = 19)
	public Timestamp getAcciTime() {
		return this.acciTime;
	}

	public void setAcciTime(Timestamp acciTime) {
		this.acciTime = acciTime;
	}

	@Column(name = "carId", nullable = false, length = 36)
	public String getCarId() {
		return this.carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	@Column(name = "sendId", length = 36)
	public String getSendId() {
		return this.sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	@Column(name = "acciExplain", nullable = false, length = 400)
	public String getAcciExplain() {
		return this.acciExplain;
	}

	public void setAcciExplain(String acciExplain) {
		this.acciExplain = acciExplain;
	}

	@Column(name = "acciDeductMark", nullable = false)
	public Integer getAcciDeductMark() {
		return this.acciDeductMark;
	}

	public void setAcciDeductMark(Integer acciDeductMark) {
		this.acciDeductMark = acciDeductMark;
	}

	@Column(name = "acciRiskProportion", nullable = false)
	public Integer getAcciRiskProportion() {
		return this.acciRiskProportion;
	}

	public void setAcciRiskProportion(Integer acciRiskProportion) {
		this.acciRiskProportion = acciRiskProportion;
	}

	@Column(name = "acciInsuranceMoney", nullable = false, precision = 10)
	public Double getAcciInsuranceMoney() {
		return this.acciInsuranceMoney;
	}

	public void setAcciInsuranceMoney(Double acciInsuranceMoney) {
		this.acciInsuranceMoney = acciInsuranceMoney;
	}

	@Column(name = "acciSelfMoney", nullable = false, precision = 10)
	public Double getAcciSelfMoney() {
		return this.acciSelfMoney;
	}

	public void setAcciSelfMoney(Double acciSelfMoney) {
		this.acciSelfMoney = acciSelfMoney;
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

	@Column(name = "acciAddress", nullable = false, length = 100)
	public String getAcciAddress() {
		return this.acciAddress;
	}

	public void setAcciAddress(String acciAddress) {
		this.acciAddress = acciAddress;
	}

	@Column(name = "acciNo", nullable = false, length = 45)
	public String getAcciNo() {
		return this.acciNo;
	}

	public void setAcciNo(String acciNo) {
		this.acciNo = acciNo;
	}

	@Column(name = "dictIdFlag", nullable = false, length = 45)
	public String getDictIdFlag() {
		return this.dictIdFlag;
	}

	public void setDictIdFlag(String dictIdFlag) {
		this.dictIdFlag = dictIdFlag;
	}

	@Column(name = "ownerCompany", nullable = false, length = 45)
	public String getOwnerCompany() {
		return this.ownerCompany;
	}

	public void setOwnerCompany(String ownerCompany) {
		this.ownerCompany = ownerCompany;
	}

	@Column(name = "stafName", nullable = false, length = 45)
	public String getStafName() {
		return this.stafName;
	}

	public void setStafName(String stafName) {
		this.stafName = stafName;
	}

	@Column(name = "acciRisk", nullable = false, length = 50)
	public String getAcciRisk() {
		return this.acciRisk;
	}

	public void setAcciRisk(String acciRisk) {
		this.acciRisk = acciRisk;
	}

	@Column(name = "acciStateId", nullable = false, length = 36)
	public String getAcciStateId() {
		return this.acciStateId;
	}

	public void setAcciStateId(String acciStateId) {
		this.acciStateId = acciStateId;
	}

	@Column(name = "acciTypeId", nullable = false, length = 36)
	public String getAcciTypeId() {
		return this.acciTypeId;
	}

	public void setAcciTypeId(String acciTypeId) {
		this.acciTypeId = acciTypeId;
	}

	@Column(name = "acciRiskId", nullable = false, length = 36)
	public String getAcciRiskId() {
		return this.acciRiskId;
	}

	public void setAcciRiskId(String acciRiskId) {
		this.acciRiskId = acciRiskId;
	}
	@Column(name = "orgIdManager",  length = 36)
	public String getOrgIdManager() {
		return orgIdManager;
	}

	public void setOrgIdManager(String orgIdManager) {
		this.orgIdManager = orgIdManager;
	}
	
	

}