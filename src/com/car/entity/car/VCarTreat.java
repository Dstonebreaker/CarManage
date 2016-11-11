package com.car.entity.car;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="v_car_treat")
public class VCarTreat implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -601887647494823697L;
	private String treatId;// ID
	private String treatNo;// 申请单编号
	private String treatUnit;// 申请单位
	private String carId;// 车辆id
	private String dictIdTreatMode;// 处置方式
	private String dictIdTreatResult;// 处置结果
	private String treatReason;// 处置原因
	private String userIdCreate;// 申请人
	private Date timeCreate;// 申请日期
	private String treatUserId;// 处置人
	private String treatTime;// 处置时间
	private String treatVerifyUserId;// 审批人
	private Date treatVerifyTime;// 审批时间
	private String treatVerifyOpinion;// 审批意见
	private String dictIdFlag;
	
	private String orgIdManager;// 产权单位Id
	private String orgIdUse;// 固定使用单位Id
	private String carNo;// 车牌号
	private String carIdentifyNo;// 车辆识别号
	private String carEngineNo;// 发动机号
	private String dictIdColor;// 车身颜色
	private String modelId;// 型号id
	private String dictIdCarType;// 车辆种类
	private String dictIdEnvironmentProtection;// 环保标志
	private Date carRegisterDate;// 初次登记日期
	private String carFileNo;// 内部档案号
	private Date carScrap;// 强制报废日期
	private Date carTime;// 出厂日期
	private String dictIdUsingKind;// 用途分类
	private String dictIdSpecialCar;// 特种车
	private String carAssets;// 已记固定资产
	private String carAssetsNo;// 车辆资产编码
	private Double carAssetsMoney;// 资产金额
	private String carFinance;// 财政在编
	private String carMemo;// 备注
	//private String dictIdFlag;// 标识
	private String dictBrand;
	private String carPoliceUsed;// 警用
	private String dictIdFunds;// 资金来源id
	private String userIdMaster;// 保管人id
	private String carIsSettle;// 是否落户

	private String orgName;// 申请单位
	private String modeName;// 处置方式
	private String resultName;// 处置结果
	private String treatUserName;// 处置人

	private String verifyUserName;// 审批人
	private String creatUserName;// 申请人
	private String modelName; 
	@Id
	@Column(name = "treatId", unique = true, nullable = false, length = 36)
	public String getTreatId() {
		return treatId;
	}

	public void setTreatId(String treatId) {
		this.treatId = treatId;
	}

	@Column(name = "treatNo", nullable = false, length = 36)
	public String getTreatNo() {
		return treatNo;
	}

	public void setTreatNo(String treatNo) {
		this.treatNo = treatNo;
	}

	@Column(name = "treatUnit", nullable = false, length = 20)
	public String getTreatUnit() {
		return treatUnit;
	}

	public void setTreatUnit(String treatUnit) {
		this.treatUnit = treatUnit;
	}

	@Column(name = "dictIdTreatMode", nullable = false, length = 36)
	public String getDictIdTreatMode() {
		return dictIdTreatMode;
	}

	public void setDictIdTreatMode(String dictIdTreatMode) {
		this.dictIdTreatMode = dictIdTreatMode;
	}

	@Column(name = "dictIdTreatResult", nullable = false, length = 36)
	public String getDictIdTreatResult() {
		return dictIdTreatResult;
	}

	public void setDictIdTreatResult(String dictIdTreatResult) {
		this.dictIdTreatResult = dictIdTreatResult;
	}

	@Column(name = "treatReason", length = 200)
	public String getTreatReason() {
		return treatReason;
	}

	public void setTreatReason(String treatReason) {
		this.treatReason = treatReason;
	}

	@Column(name = "userIdCreate", nullable = false, length = 11)
	public String getUserIdCreate() {
		return userIdCreate;
	}

	public void setUserIdCreate(String userIdCreate) {
		this.userIdCreate = userIdCreate;
	}

	@Column(name = "timeCreate", nullable = false, length = 19)
	public Date getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}

	@Column(name = "treatUserId", length = 36)
	public String getTreatUserId() {
		return treatUserId;
	}

	public void setTreatUserId(String treatUserId) {
		this.treatUserId = treatUserId;
	}

	@Column(name = "treatTime", length = 19)
	public String getTreatTime() {
		return treatTime;
	}

	public void setTreatTime(String treatTime) {
		this.treatTime = treatTime;
	}

	@Column(name = "treatVerifyUserId", length = 36)
	public String getTreatVerifyUserId() {
		return treatVerifyUserId;
	}

	public void setTreatVerifyUserId(String treatVerifyUserId) {
		this.treatVerifyUserId = treatVerifyUserId;
	}

	@Column(name = "treatVerifyTime", length = 19)
	public Date getTreatVerifyTime() {
		return treatVerifyTime;
	}

	public void setTreatVerifyTime(Date treatVerifyTime) {
		this.treatVerifyTime = treatVerifyTime;
	}

	@Column(name = "treatVerifyOpinion", length = 200)
	public String getTreatVerifyOpinion() {
		return treatVerifyOpinion;
	}

	public void setTreatVerifyOpinion(String treatVerifyOpinion) {
		this.treatVerifyOpinion = treatVerifyOpinion;
	}

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

	@Column(name = "carNo", nullable = false, length = 12)
	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	@Column(name = "modelId", nullable = false, length = 36)
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

	@Column(name = "carTime", nullable = false, length = 11)
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

	@Column(name = "dictIdFlag", nullable = true, length = 36)
	public String getDictIdFlag() {
		return dictIdFlag;
	}

	public void setDictIdFlag(String dictIdFlag) {
		this.dictIdFlag = dictIdFlag;
	}

	@Column(name = "carIdentifyNo", nullable = true, length = 36)
	public String getCarIdentifyNo() {
		return carIdentifyNo;
	}

	public void setCarIdentifyNo(String carIdentifyNo) {
		this.carIdentifyNo = carIdentifyNo;
	}

	@Column(name = "carEngineNo", nullable = true, length = 36)
	public String getCarEngineNo() {
		return carEngineNo;
	}

	public void setCarEngineNo(String carEngineNo) {
		this.carEngineNo = carEngineNo;
	}

	@Column(name = "dictIdColor", nullable = true, length = 36)
	public String getDictIdColor() {
		return dictIdColor;
	}

	public void setDictIdColor(String dictIdColor) {
		this.dictIdColor = dictIdColor;
	}

	@Column(name = "dictIdCarType", nullable = true, length = 36)
	public String getDictIdCarType() {
		return dictIdCarType;
	}

	public void setDictIdCarType(String dictIdCarType) {
		this.dictIdCarType = dictIdCarType;
	}

	@Column(name = "dictIdEnvironmentProtection", nullable = true, length = 36)
	public String getDictIdEnvironmentProtection() {
		return dictIdEnvironmentProtection;
	}

	public void setDictIdEnvironmentProtection(
			String dictIdEnvironmentProtection) {
		this.dictIdEnvironmentProtection = dictIdEnvironmentProtection;
	}

	@Column(name = "carRegisterDate", nullable = true, length = 36)
	public Date getCarRegisterDate() {
		return carRegisterDate;
	}

	public void setCarRegisterDate(Date carRegisterDate) {
		this.carRegisterDate = carRegisterDate;
	}

	@Column(name = "carFileNo", nullable = true, length = 36)
	public String getCarFileNo() {
		return carFileNo;
	}

	public void setCarFileNo(String carFileNo) {
		this.carFileNo = carFileNo;
	}

	@Column(name = "carScrap", nullable = true, length = 36)
	public Date getCarScrap() {
		return carScrap;
	}

	public void setCarScrap(Date carScrap) {
		this.carScrap = carScrap;
	}

	@Column(name = "dictIdUsingKind", nullable = true, length = 36)
	public String getDictIdUsingKind() {
		return dictIdUsingKind;
	}

	public void setDictIdUsingKind(String dictIdUsingKind) {
		this.dictIdUsingKind = dictIdUsingKind;
	}

	@Column(name = "dictIdSpecialCar", nullable = true, length = 36)
	public String getDictIdSpecialCar() {
		return dictIdSpecialCar;
	}

	public void setDictIdSpecialCar(String dictIdSpecialCar) {
		this.dictIdSpecialCar = dictIdSpecialCar;
	}

	@Column(name = "carAssets", nullable = true, length = 36)
	public String getCarAssets() {
		return carAssets;
	}

	public void setCarAssets(String carAssets) {
		this.carAssets = carAssets;
	}

	@Column(name = "carAssetsNo", nullable = true, length = 36)
	public String getCarAssetsNo() {
		return carAssetsNo;
	}

	public void setCarAssetsNo(String carAssetsNo) {
		this.carAssetsNo = carAssetsNo;
	}

	@Column(name = "carAssetsMoney", nullable = true, length = 36)
	public Double getCarAssetsMoney() {
		return carAssetsMoney;
	}

	public void setCarAssetsMoney(Double carAssetsMoney) {
		this.carAssetsMoney = carAssetsMoney;
	}

	@Column(name = "carFinance", nullable = true, length = 36)
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

	@Column(name = "carPoliceUsed", nullable = true, length = 36)
	public String getCarPoliceUsed() {
		return carPoliceUsed;
	}

	public void setCarPoliceUsed(String carPoliceUsed) {
		this.carPoliceUsed = carPoliceUsed;
	}

	@Column(name = "dictIdFunds", nullable = true, length = 36)
	public String getDictIdFunds() {
		return dictIdFunds;
	}

	public void setDictIdFunds(String dictIdFunds) {
		this.dictIdFunds = dictIdFunds;
	}

	@Column(name = "userIdMaster", nullable = true, length = 36)
	public String getUserIdMaster() {
		return userIdMaster;
	}

	public void setUserIdMaster(String userIdMaster) {
		this.userIdMaster = userIdMaster;
	}

	@Column(name = "carIsSettle", nullable = true, length = 36)
	public String getCarIsSettle() {
		return carIsSettle;
	}

	public void setCarIsSettle(String carIsSettle) {
		this.carIsSettle = carIsSettle;
	}

	@Column(name = "orgName", length = 36)
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "modeName", length = 36)
	public String getModeName() {
		return modeName;
	}

	public void setModeName(String modeName) {
		this.modeName = modeName;
	}

	@Column(name = "resultName", length = 36)
	public String getResultName() {
		return resultName;
	}

	public void setResultName(String resultName) {
		this.resultName = resultName;
	}

	@Column(name = "treatUserName", length = 36)
	public String getTreatUserName() {
		return treatUserName;
	}

	public void setTreatUserName(String treatUserName) {
		this.treatUserName = treatUserName;
	}

	@Column(name = "verifyUserName", length = 36)
	public String getVerifyUserName() {
		return verifyUserName;
	}

	public void setVerifyUserName(String verifyUserName) {
		this.verifyUserName = verifyUserName;
	}

	@Column(name = "creatUserName", length = 36)
	public String getCreatUserName() {
		return creatUserName;
	}

	public void setCreatUserName(String creatUserName) {
		this.creatUserName = creatUserName;
	}
	@Column(name = "modelName", length = 36)
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
}
