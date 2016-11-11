package com.car.entity.car;
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
 * V_Car entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "v_car")
public class V_Car implements java.io.Serializable {

	// Fields

	private String carId;
	private String orgIdManager;
	private String orgIdUse;
	private String carNo;
	private String carIdentifyNo;
	private String carEngineNo;
	private String dictIdColor;
	private String modelId;
	private String dictIdCarType;
	private String dictIdEnvironmentProtection;
	private Date carRegisterDate;
	private String carFileNo;
	private Date carScrap;
	private Date carTime;
	private String dictIdUsingKind;
	private String dictIdSpecialCar;
	private String carAssets;
	private String carAssetsNo;
	private Double carAssetsMoney;
	private String carFinance;
	private String carMemo;
	private String carPoliceUsed;
	private String dictIdFunds;
	private String carIsSettle;
	private String userIdMaster;
	private String dictIdFlag;
	private String userIdCreate;
	private Timestamp timeCreate;
	private String userIdUpdate;
	private Timestamp timeUpdate;
    private String  dictIdCarStatus;
	// Constructors

	/** default constructor */
	public V_Car() {
	}

	/** minimal constructor */
	public V_Car(String orgIdManager, String carNo, String carIdentifyNo,
			String carEngineNo, String dictIdColor, String modelId,
			String dictIdCarType, String dictIdEnvironmentProtection,
			Date carRegisterDate, String carFileNo, Date carTime,
			String dictIdUsingKind, String dictIdSpecialCar, String carAssets,
			String userIdCreate, Timestamp timeCreate) {
		this.orgIdManager = orgIdManager;
		this.carNo = carNo;
		this.carIdentifyNo = carIdentifyNo;
		this.carEngineNo = carEngineNo;
		this.dictIdColor = dictIdColor;
		this.modelId = modelId;
		this.dictIdCarType = dictIdCarType;
		this.dictIdEnvironmentProtection = dictIdEnvironmentProtection;
		this.carRegisterDate = carRegisterDate;
		this.carFileNo = carFileNo;
		this.carTime = carTime;
		this.dictIdUsingKind = dictIdUsingKind;
		this.dictIdSpecialCar = dictIdSpecialCar;
		this.carAssets = carAssets;
		this.userIdCreate = userIdCreate;
		this.timeCreate = timeCreate;
	}

	/** full constructor */
	public V_Car(String orgIdManager, String orgIdUse, String carNo,
			String carIdentifyNo, String carEngineNo, String dictIdColor,
			String modelId, String dictIdCarType,
			String dictIdEnvironmentProtection, Date carRegisterDate,
			String carFileNo, Date carScrap, Date carTime,
			String dictIdUsingKind, String dictIdSpecialCar, String carAssets,
			String carAssetsNo, Double carAssetsMoney, String carFinance,
			String carMemo, String carPoliceUsed, String dictIdFunds,
			String carIsSettle, String userIdMaster, String dictIdFlag,
			String userIdCreate, Timestamp timeCreate, String userIdUpdate,
			Timestamp timeUpdate) {
		this.orgIdManager = orgIdManager;
		this.orgIdUse = orgIdUse;
		this.carNo = carNo;
		this.carIdentifyNo = carIdentifyNo;
		this.carEngineNo = carEngineNo;
		this.dictIdColor = dictIdColor;
		this.modelId = modelId;
		this.dictIdCarType = dictIdCarType;
		this.dictIdEnvironmentProtection = dictIdEnvironmentProtection;
		this.carRegisterDate = carRegisterDate;
		this.carFileNo = carFileNo;
		this.carScrap = carScrap;
		this.carTime = carTime;
		this.dictIdUsingKind = dictIdUsingKind;
		this.dictIdSpecialCar = dictIdSpecialCar;
		this.carAssets = carAssets;
		this.carAssetsNo = carAssetsNo;
		this.carAssetsMoney = carAssetsMoney;
		this.carFinance = carFinance;
		this.carMemo = carMemo;
		this.carPoliceUsed = carPoliceUsed;
		this.dictIdFunds = dictIdFunds;
		this.carIsSettle = carIsSettle;
		this.userIdMaster = userIdMaster;
		this.dictIdFlag = dictIdFlag;
		this.userIdCreate = userIdCreate;
		this.timeCreate = timeCreate;
		this.userIdUpdate = userIdUpdate;
		this.timeUpdate = timeUpdate;
	}

	// Property accessors
	@Id
	@Column(name = "carId", nullable = false, length = 36)
	public String getCarId() {
		return this.carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	@Column(name = "orgIdManager", nullable = false, length = 36)
	public String getOrgIdManager() {
		return this.orgIdManager;
	}

	public void setOrgIdManager(String orgIdManager) {
		this.orgIdManager = orgIdManager;
	}

	@Column(name = "orgIdUse", length = 36)
	public String getOrgIdUse() {
		return this.orgIdUse;
	}

	public void setOrgIdUse(String orgIdUse) {
		this.orgIdUse = orgIdUse;
	}

	@Column(name = "carNo", nullable = false, length = 12)
	public String getCarNo() {
		return this.carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	@Column(name = "carIdentifyNo", nullable = false, length = 20)
	public String getCarIdentifyNo() {
		return this.carIdentifyNo;
	}

	public void setCarIdentifyNo(String carIdentifyNo) {
		this.carIdentifyNo = carIdentifyNo;
	}

	@Column(name = "carEngineNo", nullable = false, length = 20)
	public String getCarEngineNo() {
		return this.carEngineNo;
	}

	public void setCarEngineNo(String carEngineNo) {
		this.carEngineNo = carEngineNo;
	}

	@Column(name = "dictIdColor", nullable = false, length = 36)
	public String getDictIdColor() {
		return this.dictIdColor;
	}

	public void setDictIdColor(String dictIdColor) {
		this.dictIdColor = dictIdColor;
	}

	@Column(name = "modelId", nullable = false, length = 36)
	public String getModelId() {
		return this.modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	@Column(name = "dictIdCarType", nullable = false, length = 36)
	public String getDictIdCarType() {
		return this.dictIdCarType;
	}

	public void setDictIdCarType(String dictIdCarType) {
		this.dictIdCarType = dictIdCarType;
	}

	@Column(name = "dictIdEnvironmentProtection", nullable = false, length = 36)
	public String getDictIdEnvironmentProtection() {
		return this.dictIdEnvironmentProtection;
	}

	public void setDictIdEnvironmentProtection(
			String dictIdEnvironmentProtection) {
		this.dictIdEnvironmentProtection = dictIdEnvironmentProtection;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "carRegisterDate", nullable = false, length = 10)
	public Date getCarRegisterDate() {
		return this.carRegisterDate;
	}

	public void setCarRegisterDate(Date carRegisterDate) {
		this.carRegisterDate = carRegisterDate;
	}

	@Column(name = "carFileNo", nullable = false, length = 50)
	public String getCarFileNo() {
		return this.carFileNo;
	}

	public void setCarFileNo(String carFileNo) {
		this.carFileNo = carFileNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "carScrap", length = 10)
	public Date getCarScrap() {
		return this.carScrap;
	}

	public void setCarScrap(Date carScrap) {
		this.carScrap = carScrap;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "carTime", nullable = false, length = 10)
	public Date getCarTime() {
		return this.carTime;
	}

	public void setCarTime(Date carTime) {
		this.carTime = carTime;
	}

	@Column(name = "dictIdUsingKind", nullable = false, length = 36)
	public String getDictIdUsingKind() {
		return this.dictIdUsingKind;
	}

	public void setDictIdUsingKind(String dictIdUsingKind) {
		this.dictIdUsingKind = dictIdUsingKind;
	}

	@Column(name = "dictIdSpecialCar", nullable = false, length = 36)
	public String getDictIdSpecialCar() {
		return this.dictIdSpecialCar;
	}

	public void setDictIdSpecialCar(String dictIdSpecialCar) {
		this.dictIdSpecialCar = dictIdSpecialCar;
	}

	@Column(name = "carAssets", nullable = false, length = 20)
	public String getCarAssets() {
		return this.carAssets;
	}

	public void setCarAssets(String carAssets) {
		this.carAssets = carAssets;
	}

	@Column(name = "carAssetsNo", length = 50)
	public String getCarAssetsNo() {
		return this.carAssetsNo;
	}

	public void setCarAssetsNo(String carAssetsNo) {
		this.carAssetsNo = carAssetsNo;
	}

	@Column(name = "carAssetsMoney", precision = 10)
	public Double getCarAssetsMoney() {
		return this.carAssetsMoney;
	}

	public void setCarAssetsMoney(Double carAssetsMoney) {
		this.carAssetsMoney = carAssetsMoney;
	}

	@Column(name = "carFinance", length = 20)
	public String getCarFinance() {
		return this.carFinance;
	}

	public void setCarFinance(String carFinance) {
		this.carFinance = carFinance;
	}

	@Column(name = "carMemo", length = 400)
	public String getCarMemo() {
		return this.carMemo;
	}

	public void setCarMemo(String carMemo) {
		this.carMemo = carMemo;
	}

	@Column(name = "carPoliceUsed", length = 20)
	public String getCarPoliceUsed() {
		return this.carPoliceUsed;
	}

	public void setCarPoliceUsed(String carPoliceUsed) {
		this.carPoliceUsed = carPoliceUsed;
	}

	@Column(name = "dictIdFunds", length = 36)
	public String getDictIdFunds() {
		return this.dictIdFunds;
	}

	public void setDictIdFunds(String dictIdFunds) {
		this.dictIdFunds = dictIdFunds;
	}

	@Column(name = "carIsSettle", length = 10)
	public String getCarIsSettle() {
		return this.carIsSettle;
	}

	public void setCarIsSettle(String carIsSettle) {
		this.carIsSettle = carIsSettle;
	}

	@Column(name = "userIdMaster", length = 36)
	public String getUserIdMaster() {
		return this.userIdMaster;
	}

	public void setUserIdMaster(String userIdMaster) {
		this.userIdMaster = userIdMaster;
	}

	@Column(name = "dictIdFlag", length = 36)
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
	@Column(name = "dictIdCarStatus", length = 19)
	public String getDictIdCarStatus() {
		return dictIdCarStatus;
	}

	public void setDictIdCarStatus(String dictIdCarStatus) {
		this.dictIdCarStatus = dictIdCarStatus;
	}

}