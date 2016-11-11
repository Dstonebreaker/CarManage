package com.car.entity.car;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * VCarStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "v_car_status")
public class VCarStatus implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1704492166888024503L;
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
	private String dictIdFlag;
	private String userIdCreate;
	private Date timeCreate;
	private String userIdUpdate;
	private Date timeUpdate;
	private String simNo;
	private String dictIdCarStatus;
	private String keyNo;// 车辆钥匙号
	private String obdNo;// obd编号
	private Integer carMileage; // 里程
	private Integer carOil; // 油量
	private String color;
	private String environmentProtection;
	private String usingKind;
	private String specialCar;
	private String carStatus;
	private String carType;
	private String modelName;
	private String cardId; // 卡的id以及二维码数据

	// Constructors

	/** default constructor */
	public VCarStatus() {
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

	public void setDictIdEnvironmentProtection(String dictIdEnvironmentProtection) {
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
		return carPoliceUsed;
	}

	public void setCarPoliceUsed(String carPoliceUsed) {
		this.carPoliceUsed = carPoliceUsed;
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

	@Column(name = "simNo", nullable = false, length = 15)
	public String getSimNo() {
		return this.simNo;
	}

	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}

	@Column(name = "dictIdCarStatus", nullable = false, length = 36)
	public String getDictIdCarStatus() {
		return this.dictIdCarStatus;
	}

	public void setDictIdCarStatus(String dictIdCarStatus) {
		this.dictIdCarStatus = dictIdCarStatus;
	}

	@Column(name = "keyNo", length = 30)
	public String getKeyNo() {
		return keyNo;
	}

	public void setKeyNo(String keyNo) {
		this.keyNo = keyNo;
	}

	@Column(name = "obdNo", length = 50)
	public String getObdNo() {
		return obdNo;
	}

	public void setObdNo(String obdNo) {
		this.obdNo = obdNo;
	}

	@Column(name = "carMileage", length = 10)
	public Integer getCarMileage() {
		return carMileage;
	}

	public void setCarMileage(Integer carMileage) {
		this.carMileage = carMileage;
	}

	@Column(name = "carOil", length = 10)
	public Integer getCarOil() {
		return carOil;
	}

	public void setCarOil(Integer carOil) {
		this.carOil = carOil;
	}

	@Column(name = "color", nullable = false, length = 50)
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Column(name = "environmentProtection", nullable = false, length = 50)
	public String getEnvironmentProtection() {
		return environmentProtection;
	}

	public void setEnvironmentProtection(String environmentProtection) {
		this.environmentProtection = environmentProtection;
	}

	@Column(name = "usingKind", nullable = false, length = 50)
	public String getUsingKind() {
		return usingKind;
	}

	public void setUsingKind(String usingKind) {
		this.usingKind = usingKind;
	}

	@Column(name = "specialCar", nullable = false, length = 50)
	public String getSpecialCar() {
		return specialCar;
	}

	public void setSpecialCar(String specialCar) {
		this.specialCar = specialCar;
	}

	@Column(name = "carStatus", nullable = false, length = 50)
	public String getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(String carStatus) {
		this.carStatus = carStatus;
	}

	@Column(name = "carType", nullable = false, length = 50)
	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	@Column(name = "modelName", nullable = false, length = 50)
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
}