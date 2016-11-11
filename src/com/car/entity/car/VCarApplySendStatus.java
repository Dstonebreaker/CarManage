package com.car.entity.car;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * VCarApplySendStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "v_car_apply_send_status")
public class VCarApplySendStatus implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7577041332700586345L;
	// Fields

	private String id;
	private String sendId;
	private String applyId;
	private String returnId;
	private String dictIdApplySendStatus;
	private String applyNo;
	private String carPoliceUsed;
	private String orgId;
	private String orgPath;
	private String userIdLinkman;
	private String stafPhoneLinkman;
	private Integer applyUsedPeople;
	private String dictIdUseCarReson;
	private Timestamp applyUsingTime;
	private Timestamp applyRemandTime;
	private String dictIdRegion;
	private String userIdPilot;
	private String stafPhonePilot;
	private String dictIdDrivingLlicenseKind;
	private String dictIdIsSecret;
	private String applyMemo;
	private String applyUserIdCreate;
	private Timestamp applyTimeCreate;
	private String dictIdCheckStatus;
	private String userIdCheck1;
	private Timestamp timeCheck1;
	private String applyCheck1;
	private String userIdCheck2;
	private Timestamp timeCheck2;
	private String applyCheck2;
	private String userIdCheck3;
	private Timestamp timeCheck3;
	private String applyCheck3;
	private String applyType;
	private String useCarReson;
	private String orgName;
	private String orgIdManager;
	private String isSecret;
	private String applyUserNameCreate;
	private String linkManName;
	private String drivingLlicenseKind;
	private String pilotName;
	private String region;
	private String userNameCheck1;
	private String userCheckSign1; // 审批人签名图片地址
	private String userNameCheck2;
	private String checkStatus;
	private String dictIdCarType;
	private String carType;
	private String sendStatus;
	private String applyCarNo;
	private String applyTypeName;
	private String orgIdCheck;
	private String sendNo;
	private String dictIdCarSendType;
	private String userIdHandler;
	private String sendHandlerPicture;
	private String sendFile;
	private Timestamp sendTime;
	private String carId;
	private String carNo;
	private String simNo;
	private Double sendOil;
	private Double sendMileage;
	private String sendMemo;
	private String sendDictIdFlag;
	private String sendUserIdCreate;
	private Timestamp sendTimeCreate;
	private String sendUserIdUpdate;
	private Timestamp sendTimeUpdate;
	private String carSendType;
	private String userNameHandler;
	private String sendUserNameCreate;
	private String sendUserNameUpdate;
	private String sendDictIdCarType;
	private String sendCarTypeName;
	private Timestamp returnTime;
	private Double returnMileage;
	private Double returnOil;
	private String retDictIdFlag;
	private String retUserIdCreate;
	private Timestamp retTimeCreate;
	private String retUserIdUpdate;
	private Timestamp retTimeUpdate;
	private String retUserNameCreate;
	private String retuserNameUpdate;
	private String carGarage;
	private String modelName;

	// Constructors

	/** default constructor */
	public VCarApplySendStatus() {
	}

	// Property accessors
	@Id
	@Column(name = "id", nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "sendId", length = 36)
	public String getSendId() {
		return this.sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	@Column(name = "applyId", length = 36)
	public String getApplyId() {
		return this.applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	@Column(name = "returnId", length = 36)
	public String getReturnId() {
		return this.returnId;
	}

	public void setReturnId(String returnId) {
		this.returnId = returnId;
	}

	@Column(name = "dictIdApplySendStatus", nullable = false, length = 36)
	public String getDictIdApplySendStatus() {
		return this.dictIdApplySendStatus;
	}

	public void setDictIdApplySendStatus(String dictIdApplySendStatus) {
		this.dictIdApplySendStatus = dictIdApplySendStatus;
	}

	@Column(name = "applyNo", length = 20)
	public String getApplyNo() {
		return this.applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	@Column(name = "carPoliceUsed", length = 20)
	public String getCarPoliceUsed() {
		return carPoliceUsed;
	}

	public void setCarPoliceUsed(String carPoliceUsed) {
		this.carPoliceUsed = carPoliceUsed;
	}

	@Column(name = "orgId", length = 36)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "orgPath", length = 4000)
	public String getOrgPath() {
		return orgPath;
	}

	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
	}

	@Column(name = "userIdLinkman", length = 36)
	public String getUserIdLinkman() {
		return this.userIdLinkman;
	}

	public void setUserIdLinkman(String userIdLinkman) {
		this.userIdLinkman = userIdLinkman;
	}

	@Column(name = "stafPhoneLinkman", length = 15)
	public String getStafPhoneLinkman() {
		return this.stafPhoneLinkman;
	}

	public void setStafPhoneLinkman(String stafPhoneLinkman) {
		this.stafPhoneLinkman = stafPhoneLinkman;
	}

	@Column(name = "applyUsedPeople")
	public Integer getApplyUsedPeople() {
		return this.applyUsedPeople;
	}

	public void setApplyUsedPeople(Integer applyUsedPeople) {
		this.applyUsedPeople = applyUsedPeople;
	}

	@Column(name = "dictIdUseCarReson", length = 36)
	public String getDictIdUseCarReson() {
		return dictIdUseCarReson;
	}

	public void setDictIdUseCarReson(String dictIdUseCarReson) {
		this.dictIdUseCarReson = dictIdUseCarReson;
	}

	@Column(name = "useCarReson", length = 50)
	public String getUseCarReson() {
		return useCarReson;
	}

	public void setUseCarReson(String useCarReson) {
		this.useCarReson = useCarReson;
	}

	@Column(name = "applyUsingTime", length = 19)
	public Timestamp getApplyUsingTime() {
		return this.applyUsingTime;
	}

	public void setApplyUsingTime(Timestamp applyUsingTime) {
		this.applyUsingTime = applyUsingTime;
	}

	@Column(name = "applyRemandTime", length = 19)
	public Timestamp getApplyRemandTime() {
		return this.applyRemandTime;
	}

	public void setApplyRemandTime(Timestamp applyRemandTime) {
		this.applyRemandTime = applyRemandTime;
	}

	@Column(name = "dictIdRegion", length = 36)
	public String getDictIdRegion() {
		return this.dictIdRegion;
	}

	public void setDictIdRegion(String dictIdRegion) {
		this.dictIdRegion = dictIdRegion;
	}

	@Column(name = "userIdPilot", length = 36)
	public String getUserIdPilot() {
		return this.userIdPilot;
	}

	public void setUserIdPilot(String userIdPilot) {
		this.userIdPilot = userIdPilot;
	}

	@Column(name = "stafPhonePilot", length = 15)
	public String getStafPhonePilot() {
		return this.stafPhonePilot;
	}

	public void setStafPhonePilot(String stafPhonePilot) {
		this.stafPhonePilot = stafPhonePilot;
	}

	@Column(name = "dictIdDrivingLlicenseKind", length = 36)
	public String getDictIdDrivingLlicenseKind() {
		return this.dictIdDrivingLlicenseKind;
	}

	public void setDictIdDrivingLlicenseKind(String dictIdDrivingLlicenseKind) {
		this.dictIdDrivingLlicenseKind = dictIdDrivingLlicenseKind;
	}

	@Column(name = "dictIdIsSecret", length = 36)
	public String getDictIdIsSecret() {
		return this.dictIdIsSecret;
	}

	public void setDictIdIsSecret(String dictIdIsSecret) {
		this.dictIdIsSecret = dictIdIsSecret;
	}

	@Column(name = "applyMemo", length = 400)
	public String getApplyMemo() {
		return this.applyMemo;
	}

	public void setApplyMemo(String applyMemo) {
		this.applyMemo = applyMemo;
	}

	@Column(name = "applyUserIdCreate", length = 36)
	public String getApplyUserIdCreate() {
		return this.applyUserIdCreate;
	}

	public void setApplyUserIdCreate(String applyUserIdCreate) {
		this.applyUserIdCreate = applyUserIdCreate;
	}

	@Column(name = "applyTimeCreate", length = 19)
	public Timestamp getApplyTimeCreate() {
		return this.applyTimeCreate;
	}

	public void setApplyTimeCreate(Timestamp applyTimeCreate) {
		this.applyTimeCreate = applyTimeCreate;
	}

	@Column(name = "dictIdCheckStatus", length = 36)
	public String getDictIdCheckStatus() {
		return this.dictIdCheckStatus;
	}

	public void setDictIdCheckStatus(String dictIdCheckStatus) {
		this.dictIdCheckStatus = dictIdCheckStatus;
	}

	@Column(name = "userIdCheck1", length = 36)
	public String getUserIdCheck1() {
		return this.userIdCheck1;
	}

	public void setUserIdCheck1(String userIdCheck1) {
		this.userIdCheck1 = userIdCheck1;
	}

	@Column(name = "timeCheck1", length = 19)
	public Timestamp getTimeCheck1() {
		return this.timeCheck1;
	}

	public void setTimeCheck1(Timestamp timeCheck1) {
		this.timeCheck1 = timeCheck1;
	}

	@Column(name = "applyCheck1", length = 100)
	public String getApplyCheck1() {
		return this.applyCheck1;
	}

	public void setApplyCheck1(String applyCheck1) {
		this.applyCheck1 = applyCheck1;
	}

	@Column(name = "userIdCheck2", length = 36)
	public String getUserIdCheck2() {
		return this.userIdCheck2;
	}

	public void setUserIdCheck2(String userIdCheck2) {
		this.userIdCheck2 = userIdCheck2;
	}

	@Column(name = "timeCheck2", length = 19)
	public Timestamp getTimeCheck2() {
		return this.timeCheck2;
	}

	public void setTimeCheck2(Timestamp timeCheck2) {
		this.timeCheck2 = timeCheck2;
	}

	@Column(name = "applyCheck2", length = 100)
	public String getApplyCheck2() {
		return this.applyCheck2;
	}

	public void setApplyCheck2(String applyCheck2) {
		this.applyCheck2 = applyCheck2;
	}

	@Column(name = "userIdCheck3", length = 36)
	public String getUserIdCheck3() {
		return this.userIdCheck3;
	}

	public void setUserIdCheck3(String userIdCheck3) {
		this.userIdCheck3 = userIdCheck3;
	}

	@Column(name = "timeCheck3", length = 19)
	public Timestamp getTimeCheck3() {
		return this.timeCheck3;
	}

	public void setTimeCheck3(Timestamp timeCheck3) {
		this.timeCheck3 = timeCheck3;
	}

	@Column(name = "applyCheck3", length = 100)
	public String getApplyCheck3() {
		return this.applyCheck3;
	}

	public void setApplyCheck3(String applyCheck3) {
		this.applyCheck3 = applyCheck3;
	}

	@Column(name = "applyType", length = 45)
	public String getApplyType() {
		return this.applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	@Column(name = "orgName", length = 200)
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "orgIdManager", length = 36)
	public String getOrgIdManager() {
		return this.orgIdManager;
	}

	public void setOrgIdManager(String orgIdManager) {
		this.orgIdManager = orgIdManager;
	}

	@Column(name = "isSecret", length = 50)
	public String getIsSecret() {
		return this.isSecret;
	}

	public void setIsSecret(String isSecret) {
		this.isSecret = isSecret;
	}

	@Column(name = "applyUserNameCreate", length = 50)
	public String getApplyUserNameCreate() {
		return this.applyUserNameCreate;
	}

	public void setApplyUserNameCreate(String applyUserNameCreate) {
		this.applyUserNameCreate = applyUserNameCreate;
	}

	@Column(name = "linkManName", length = 50)
	public String getLinkManName() {
		return this.linkManName;
	}

	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}

	@Column(name = "drivingLlicenseKind", length = 50)
	public String getDrivingLlicenseKind() {
		return this.drivingLlicenseKind;
	}

	public void setDrivingLlicenseKind(String drivingLlicenseKind) {
		this.drivingLlicenseKind = drivingLlicenseKind;
	}

	@Column(name = "pilotName", length = 50)
	public String getPilotName() {
		return this.pilotName;
	}

	public void setPilotName(String pilotName) {
		this.pilotName = pilotName;
	}

	@Column(name = "region", length = 50)
	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Column(name = "userNameCheck1", length = 50)
	public String getUserNameCheck1() {
		return this.userNameCheck1;
	}

	public void setUserNameCheck1(String userNameCheck1) {
		this.userNameCheck1 = userNameCheck1;
	}

	@JSONField(serialize = false)
	@Column(name = "userCheckSign1", length = 400)
	public String getUserCheckSign1() {
		return userCheckSign1;
	}

	public void setUserCheckSign1(String userCheckSign1) {
		this.userCheckSign1 = userCheckSign1;
	}

	@Column(name = "userNameCheck2", length = 50)
	public String getUserNameCheck2() {
		return this.userNameCheck2;
	}

	public void setUserNameCheck2(String userNameCheck2) {
		this.userNameCheck2 = userNameCheck2;
	}

	@Column(name = "checkStatus", length = 50)
	public String getCheckStatus() {
		return this.checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	@Column(name = "dictIdCarType", length = 36)
	public String getDictIdCarType() {
		return this.dictIdCarType;
	}

	public void setDictIdCarType(String dictIdCarType) {
		this.dictIdCarType = dictIdCarType;
	}

	@Column(name = "carType", length = 50)
	public String getCarType() {
		return this.carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	@Column(name = "sendStatus", length = 50)
	public String getSendStatus() {
		return this.sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	@Column(name = "applyCarNo", length = 12)
	public String getApplyCarNo() {
		return this.applyCarNo;
	}

	public void setApplyCarNo(String applyCarNo) {
		this.applyCarNo = applyCarNo;
	}

	@Column(name = "applyTypeName", length = 50)
	public String getApplyTypeName() {
		return this.applyTypeName;
	}

	public void setApplyTypeName(String applyTypeName) {
		this.applyTypeName = applyTypeName;
	}

	@Column(name = "orgIdCheck", length = 36)
	public String getOrgIdCheck() {
		return this.orgIdCheck;
	}

	public void setOrgIdCheck(String orgIdCheck) {
		this.orgIdCheck = orgIdCheck;
	}

	@Column(name = "sendNo", length = 20)
	public String getSendNo() {
		return this.sendNo;
	}

	public void setSendNo(String sendNo) {
		this.sendNo = sendNo;
	}

	@Column(name = "dictIdCarSendType", length = 36)
	public String getDictIdCarSendType() {
		return this.dictIdCarSendType;
	}

	public void setDictIdCarSendType(String dictIdCarSendType) {
		this.dictIdCarSendType = dictIdCarSendType;
	}

	@Column(name = "userIdHandler", length = 36)
	public String getUserIdHandler() {
		return this.userIdHandler;
	}

	public void setUserIdHandler(String userIdHandler) {
		this.userIdHandler = userIdHandler;
	}

	@Column(name = "sendHandlerPicture", length = 400)
	public String getSendHandlerPicture() {
		return this.sendHandlerPicture;
	}

	public void setSendHandlerPicture(String sendHandlerPicture) {
		this.sendHandlerPicture = sendHandlerPicture;
	}

	@Column(name = "sendFile", length = 400)
	public String getSendFile() {
		return this.sendFile;
	}

	public void setSendFile(String sendFile) {
		this.sendFile = sendFile;
	}

	@Column(name = "sendTime", length = 19)
	public Timestamp getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}

	@Column(name = "carId", length = 36)
	public String getCarId() {
		return this.carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	@Column(name = "carNo", length = 12)
	public String getCarNo() {
		return this.carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	@Column(name = "simNo", length = 15)
	public String getSimNo() {
		return this.simNo;
	}

	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}

	@Column(name = "sendOil", precision = 10)
	public Double getSendOil() {
		return this.sendOil;
	}

	public void setSendOil(Double sendOil) {
		this.sendOil = sendOil;
	}

	@Column(name = "sendMileage", precision = 10)
	public Double getSendMileage() {
		return this.sendMileage;
	}

	public void setSendMileage(Double sendMileage) {
		this.sendMileage = sendMileage;
	}

	@Column(name = "sendMemo", length = 400)
	public String getSendMemo() {
		return this.sendMemo;
	}

	public void setSendMemo(String sendMemo) {
		this.sendMemo = sendMemo;
	}

	@Column(name = "sendDictIdFlag", length = 36)
	public String getSendDictIdFlag() {
		return this.sendDictIdFlag;
	}

	public void setSendDictIdFlag(String sendDictIdFlag) {
		this.sendDictIdFlag = sendDictIdFlag;
	}

	@Column(name = "sendUserIdCreate", length = 36)
	public String getSendUserIdCreate() {
		return this.sendUserIdCreate;
	}

	public void setSendUserIdCreate(String sendUserIdCreate) {
		this.sendUserIdCreate = sendUserIdCreate;
	}

	@Column(name = "sendTimeCreate", length = 19)
	public Timestamp getSendTimeCreate() {
		return this.sendTimeCreate;
	}

	public void setSendTimeCreate(Timestamp sendTimeCreate) {
		this.sendTimeCreate = sendTimeCreate;
	}

	@Column(name = "sendUserIdUpdate", length = 36)
	public String getSendUserIdUpdate() {
		return this.sendUserIdUpdate;
	}

	public void setSendUserIdUpdate(String sendUserIdUpdate) {
		this.sendUserIdUpdate = sendUserIdUpdate;
	}

	@Column(name = "sendTimeUpdate", length = 19)
	public Timestamp getSendTimeUpdate() {
		return this.sendTimeUpdate;
	}

	public void setSendTimeUpdate(Timestamp sendTimeUpdate) {
		this.sendTimeUpdate = sendTimeUpdate;
	}

	@Column(name = "carSendType", length = 50)
	public String getCarSendType() {
		return this.carSendType;
	}

	public void setCarSendType(String carSendType) {
		this.carSendType = carSendType;
	}

	@Column(name = "userNameHandler", length = 50)
	public String getUserNameHandler() {
		return this.userNameHandler;
	}

	public void setUserNameHandler(String userNameHandler) {
		this.userNameHandler = userNameHandler;
	}

	@Column(name = "sendUserNameCreate", length = 50)
	public String getSendUserNameCreate() {
		return this.sendUserNameCreate;
	}

	public void setSendUserNameCreate(String sendUserNameCreate) {
		this.sendUserNameCreate = sendUserNameCreate;
	}

	@Column(name = "sendUserNameUpdate", length = 50)
	public String getSendUserNameUpdate() {
		return this.sendUserNameUpdate;
	}

	public void setSendUserNameUpdate(String sendUserNameUpdate) {
		this.sendUserNameUpdate = sendUserNameUpdate;
	}

	@Column(name = "sendDictIdCarType", length = 36)
	public String getSendDictIdCarType() {
		return this.sendDictIdCarType;
	}

	public void setSendDictIdCarType(String sendDictIdCarType) {
		this.sendDictIdCarType = sendDictIdCarType;
	}

	@Column(name = "sendCarTypeName", length = 50)
	public String getSendCarTypeName() {
		return this.sendCarTypeName;
	}

	public void setSendCarTypeName(String sendCarTypeName) {
		this.sendCarTypeName = sendCarTypeName;
	}

	@Column(name = "returnTime", length = 19)
	public Timestamp getReturnTime() {
		return this.returnTime;
	}

	public void setReturnTime(Timestamp returnTime) {
		this.returnTime = returnTime;
	}

	@Column(name = "returnMileage", precision = 10)
	public Double getReturnMileage() {
		return this.returnMileage;
	}

	public void setReturnMileage(Double returnMileage) {
		this.returnMileage = returnMileage;
	}

	@Column(name = "returnOil", precision = 10)
	public Double getReturnOil() {
		return this.returnOil;
	}

	public void setReturnOil(Double returnOil) {
		this.returnOil = returnOil;
	}

	@Column(name = "retDictIdFlag", length = 36)
	public String getRetDictIdFlag() {
		return this.retDictIdFlag;
	}

	public void setRetDictIdFlag(String retDictIdFlag) {
		this.retDictIdFlag = retDictIdFlag;
	}

	@Column(name = "retUserIdCreate", length = 36)
	public String getRetUserIdCreate() {
		return this.retUserIdCreate;
	}

	public void setRetUserIdCreate(String retUserIdCreate) {
		this.retUserIdCreate = retUserIdCreate;
	}

	@Column(name = "retTimeCreate", length = 19)
	public Timestamp getRetTimeCreate() {
		return this.retTimeCreate;
	}

	public void setRetTimeCreate(Timestamp retTimeCreate) {
		this.retTimeCreate = retTimeCreate;
	}

	@Column(name = "retUserIdUpdate", length = 36)
	public String getRetUserIdUpdate() {
		return this.retUserIdUpdate;
	}

	public void setRetUserIdUpdate(String retUserIdUpdate) {
		this.retUserIdUpdate = retUserIdUpdate;
	}

	@Column(name = "retTimeUpdate", length = 19)
	public Timestamp getRetTimeUpdate() {
		return this.retTimeUpdate;
	}

	public void setRetTimeUpdate(Timestamp retTimeUpdate) {
		this.retTimeUpdate = retTimeUpdate;
	}

	@Column(name = "retUserNameCreate", length = 50)
	public String getRetUserNameCreate() {
		return this.retUserNameCreate;
	}

	public void setRetUserNameCreate(String retUserNameCreate) {
		this.retUserNameCreate = retUserNameCreate;
	}

	@Column(name = "retuserNameUpdate", length = 50)
	public String getRetuserNameUpdate() {
		return this.retuserNameUpdate;
	}

	public void setRetuserNameUpdate(String retuserNameUpdate) {
		this.retuserNameUpdate = retuserNameUpdate;
	}
	
	@Column(name = "carGarage", length = 10)
	public String getCarGarage() {
		return carGarage;
	}

	public void setCarGarage(String carGarage) {
		this.carGarage = carGarage;
	}
	@Column(name = "modelName", length = 50)
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

}