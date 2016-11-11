package com.car.entity.car;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.omg.CORBA.PRIVATE_MEMBER;

@Entity
@Table(name="t_car")
public class TCar  implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7076663346013156584L;
	private String carId;//id（GUID）
	private String orgIdManager;//产权单位Id
	private String orgIdUse;//固定使用单位Id
	private String carNo;//车牌号
	private String carIdentifyNo;//车辆识别号
	private String carEngineNo;//发动机号
	private String dictIdColor;//车身颜色
	private String modelId;//型号id
	private String dictIdCarType;//车辆种类
	private String dictIdEnvironmentProtection;//环保标志
	private Date carRegisterDate;//初次登记日期
	private String carFileNo;//内部档案号 
	private Date carScrap;//强制报废日期
	private Date carTime;//出厂日期
	private String dictIdUsingKind;//用途分类
	private String dictIdSpecialCar;//特种车
	private String carAssets;//已记固定资产
	private String carAssetsNo;//车辆资产编码
	private Double carAssetsMoney;//资产金额
	private String carFinance;//财政在编
	private String carMemo;//备注
	private String userIdCreate;//登记人id
	private Date timeCreate;//登记时间
	private String userIdUpdate;//修改人id
	private Date timeUpdate;//修改时间
	private String dictIdFlag;//标识
	private String dictBrand;
	private String carPoliceUsed;//警用
	private String dictIdFunds;//资金来源id
	private String userIdMaster;//保管人id
	private String carIsSettle;//是否落户
	private TCarStatus carStatus;
	private String cardId;//卡的id以及二维码数据
    private Integer isDeploy; //是否定编标记
    private String carGarage;//库号
    private Integer mileageCalibration;//里程校准
    private Double oilCalibration;//油量校准
	@Id
	@Column(name = "carId", unique = true, nullable = false, length = 36)
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	@Column(name = "orgIdManager", nullable = false, length = 36)
	public String getOrgIdManager() {
		return orgIdManager;
	}
	public void setOrgIdManager(String orgIdManager) {
		this.orgIdManager = orgIdManager;
	}
	@Column(name = "carNo",nullable = false, length = 12)
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	@Column(name = "modelId",nullable = false, length = 36)
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	@Column(name = "orgIdUse", length = 36)
	public String getOrgIdUse() {
		return orgIdUse;
	}
	public void setOrgIdUse(String orgIdUse) {
		this.orgIdUse = orgIdUse;
	}
	@Column(name = "carTime",nullable = false, length = 11)
	public Date getCarTime() {
		return carTime;
	}
	public void setCarTime(Date carTime) {
		this.carTime = carTime;
	}
	
	@Column(name = "carMemo", length = 400)
	public String getCarMemo() {
		return carMemo;
	}
	public void setCarMemo(String carMemo) {
		this.carMemo = carMemo;
	}
	@Column(name = "userIdCreate",nullable = false, length = 36)
	public String getUserIdCreate() {
		return userIdCreate;
	}
	public void setUserIdCreate(String userIdCreate) {
		this.userIdCreate = userIdCreate;
	}
	@Column(name = "timeCreate",nullable = false, length = 11)
	public Date getTimeCreate() {
		return timeCreate;
	}
	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}
	@Column(name = "userIdUpdate",nullable = true, length = 36)
	public String getUserIdUpdate() {
		return userIdUpdate;
	}
	public void setUserIdUpdate(String userIdUpdate) {
		this.userIdUpdate = userIdUpdate;
	}
	@Column(name = "timeUpdate", length = 11)
	public Date getTimeUpdate() {
		return timeUpdate;
	}
	public void setTimeUpdate(Date timeUpdate) {
		this.timeUpdate = timeUpdate;
	}
	
	@Column(name = "dictIdFlag",nullable = true, length = 36)
	public String getDictIdFlag() {
		return dictIdFlag;
	}
	public void setDictIdFlag(String dictIdFlag) {
		this.dictIdFlag = dictIdFlag;
	}
	@Column(name = "carIdentifyNo",nullable = true, length = 36)
	public String getCarIdentifyNo() {
		return carIdentifyNo;
	}
	public void setCarIdentifyNo(String carIdentifyNo) {
		this.carIdentifyNo = carIdentifyNo;
	}
	@Column(name = "carEngineNo",nullable = true, length = 36)
	public String getCarEngineNo() {
		return carEngineNo;
	}
	public void setCarEngineNo(String carEngineNo) {
		this.carEngineNo = carEngineNo;
	}
	@Column(name = "dictIdColor",nullable = true, length = 36)
	public String getDictIdColor() {
		return dictIdColor;
	}
	public void setDictIdColor(String dictIdColor) {
		this.dictIdColor = dictIdColor;
	}
	@Column(name = "dictIdCarType",nullable = true, length = 36)
	public String getDictIdCarType() {
		return dictIdCarType;
	}
	public void setDictIdCarType(String dictIdCarType) {
		this.dictIdCarType = dictIdCarType;
	}
	@Column(name = "dictIdEnvironmentProtection",nullable = true, length = 36)
	public String getDictIdEnvironmentProtection() {
		return dictIdEnvironmentProtection;
	}
	public void setDictIdEnvironmentProtection(String dictIdEnvironmentProtection) {
		this.dictIdEnvironmentProtection = dictIdEnvironmentProtection;
	}
	@Column(name = "carRegisterDate",nullable = true, length = 36)
	public Date getCarRegisterDate() {
		return carRegisterDate;
	}
	public void setCarRegisterDate(Date carRegisterDate) {
		this.carRegisterDate = carRegisterDate;
	}
	@Column(name = "carFileNo",nullable = true, length = 36)
	public String getCarFileNo() {
		return carFileNo;
	}
	public void setCarFileNo(String carFileNo) {
		this.carFileNo = carFileNo;
	}
	@Column(name = "carScrap",nullable = true, length = 36)
	public Date getCarScrap() {
		return carScrap;
	}
	public void setCarScrap(Date carScrap) {
		this.carScrap = carScrap;
	}
	@Column(name = "dictIdUsingKind",nullable = true, length = 36)
	public String getDictIdUsingKind() {
		return dictIdUsingKind;
	}
	public void setDictIdUsingKind(String dictIdUsingKind) {
		this.dictIdUsingKind = dictIdUsingKind;
	}
	@Column(name = "dictIdSpecialCar",nullable = true, length = 36)
	public String getDictIdSpecialCar() {
		return dictIdSpecialCar;
	}
	public void setDictIdSpecialCar(String dictIdSpecialCar) {
		this.dictIdSpecialCar = dictIdSpecialCar;
	}
	@Column(name = "carAssets",nullable = true, length = 36)
	public String getCarAssets() {
		return carAssets;
	}
	public void setCarAssets(String carAssets) {
		this.carAssets = carAssets;
	}
	@Column(name = "carAssetsNo",nullable = true, length = 36)
	public String getCarAssetsNo() {
		return carAssetsNo;
	}
	public void setCarAssetsNo(String carAssetsNo) {
		this.carAssetsNo = carAssetsNo;
	}
	@Column(name = "carAssetsMoney",nullable = true, length = 36)
	public Double getCarAssetsMoney() {
		return carAssetsMoney;
	}
	public void setCarAssetsMoney(Double carAssetsMoney) {
		this.carAssetsMoney = carAssetsMoney;
	}	
	@Column(name = "carFinance",nullable = true, length = 36)
	public String getCarFinance() {
		return carFinance;
	}

	public void setCarFinance(String carFinance) {
		this.carFinance = carFinance;
	}
	@Transient
	public String getDictBrand() {
		return dictBrand;
	}
	public void setDictBrand(String dictBrand) {
		this.dictBrand = dictBrand;
	}
	@Column(name = "carPoliceUsed",nullable = true, length = 36)
	public String getCarPoliceUsed() {
		return carPoliceUsed;
	}
	public void setCarPoliceUsed(String carPoliceUsed) {
		this.carPoliceUsed = carPoliceUsed;
	}
	@Column(name = "dictIdFunds",nullable = true, length = 36)
	public String getDictIdFunds() {
		return dictIdFunds;
	}
	public void setDictIdFunds(String dictIdFunds) {
		this.dictIdFunds = dictIdFunds;
	}
	@Column(name = "userIdMaster",nullable = true, length = 36)
	public String getUserIdMaster() {
		return userIdMaster;
	}
	public void setUserIdMaster(String userIdMaster) {
		this.userIdMaster = userIdMaster;
	}
	@Column(name = "carIsSettle",nullable = true, length = 36)
	public String getCarIsSettle() {
		return carIsSettle;
	}
	public void setCarIsSettle(String carIsSettle) {
		this.carIsSettle = carIsSettle;
	}
	
	@OneToOne(cascade=CascadeType.ALL)  
    @JoinColumn(name="carId", insertable = false, updatable = false) 
	public TCarStatus getCarStatus() {
		return carStatus;
	}
	public void setCarStatus(TCarStatus carStatus) {
		this.carStatus = carStatus;
	}

	@Column(name = "cardId",length = 64)
	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	@Column(name = "isDeploy",nullable = false, length = 2)
	public Integer getIsDeploy() {
		return isDeploy;
	}
	public void setIsDeploy(Integer isDeploy) {
		this.isDeploy = isDeploy;
	}
	@Column(name = "carGarage",nullable = false, length = 10)
	public String getCarGarage() {
		return carGarage;
	}
	public void setCarGarage(String carGarage) {
		this.carGarage = carGarage;
	}
	@Column(name = "mileageCalibration",nullable = false, length = 10)
	public Integer getMileageCalibration() {
		return mileageCalibration;
	}
	public void setMileageCalibration(Integer mileageCalibration) {
		this.mileageCalibration = mileageCalibration;
	}
	@Column(name = "oilCalibration",nullable = false, length = 10)
	public Double getOilCalibration() {
		return oilCalibration;
	}
	public void setOilCalibration(Double oilCalibration) {
		this.oilCalibration = oilCalibration;
	}
	
	
}
