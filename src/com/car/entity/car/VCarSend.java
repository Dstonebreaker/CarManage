package com.car.entity.car;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * VCarSend entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "v_car_send")
public class VCarSend implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 167441606121988217L;
	// Fields

	private String sendId; // 主键id
	private String sendNo; // 派车单号
	private String dictIdCarSendType; // 派车方式（有单派车/无单派车/紧急派车）
	private String carSendType;
//	private String applyId; // 申请id（暂留）
	private String orgId; // 申请车辆的单位
	private String orgIdManager;
	private String orgName;
	private String userIdHandler; // 领车人id
	private String userNameHandler;
	private String sendHandlerPicture; // 领车人拍照
	private String sendFile; // 派车文件上传
	private Date sendTime; // 派车时间：派车时间自动读取服务器时间
	private String carId; // 车辆id
	private String carNo; // 车牌号
	private String simNo; // sim卡号
	private Double sendOil; // 出车油量
	private Double sendMileage; // 出车里程
	private String sendMemo; // 备注
	private String userIdPilot; // 驾驶员ID
	private String pilotName;
	private String stafPhonePilot; // 驾驶员手机
	private String dictIdDrivingLlicenseKind; // 驾驶员准驾车型
	private String drivingLlicenseKind;
	private String dictIdFlag; // 标识
	private String userIdCreate; // 登记人id
	private String userNameCreate;
	private Date timeCreate; // 登记时间
	private String userIdUpdate; // 修改人id
	private String userNameUpdate;
	private Date timeUpdate; // 修改时间
	private String applyId; // 申请id
	private String returnId; // 还车单id
	private String dictIdApplySendStatus; // 单据状态
	private String sendStatusName;
	private String dictIdCarType; // 车辆种类
	private String carTypeName;

	// Constructors

	/** default constructor */
	public VCarSend() {
	}

	// Property accessors
	@Id
	@Column(name = "sendId", nullable = false, length = 36)
	public String getSendId() {
		return this.sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	@Column(name = "sendNo", nullable = false, length = 20)
	public String getSendNo() {
		return this.sendNo;
	}

	public void setSendNo(String sendNo) {
		this.sendNo = sendNo;
	}

	@Column(name = "dictIdCarSendType", nullable = false, length = 36)
	public String getDictIdCarSendType() {
		return this.dictIdCarSendType;
	}

	public void setDictIdCarSendType(String dictIdCarSendType) {
		this.dictIdCarSendType = dictIdCarSendType;
	}

	@Column(name = "applyId", length = 36)
	public String getApplyId() {
		return this.applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	@Column(name = "orgId", nullable = false, length = 36)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "orgIdManager", length = 36)
	public String getOrgIdManager() {
		return orgIdManager;
	}

	public void setOrgIdManager(String orgIdManager) {
		this.orgIdManager = orgIdManager;
	}

	@Column(name = "orgName", nullable = false, length = 200)
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "userIdHandler", nullable = false, length = 36)
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

	@Column(name = "sendTime", nullable = false, length = 19)
	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
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

	@Column(name = "simNo", length = 15)
	public String getSimNo() {
		return this.simNo;
	}

	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}

	@Column(name = "sendOil", nullable = false, precision = 10)
	public Double getSendOil() {
		return this.sendOil;
	}

	public void setSendOil(Double sendOil) {
		this.sendOil = sendOil;
	}

	@Column(name = "sendMileage", nullable = false, precision = 10)
	public Double getSendMileage() {
		return this.sendMileage;
	}

	public void setSendMileage(Double sendMileage) {
		this.sendMileage = sendMileage;
	}

	@Column(name = "sendMemo", nullable = false, length = 400)
	public String getSendMemo() {
		return this.sendMemo;
	}

	public void setSendMemo(String sendMemo) {
		this.sendMemo = sendMemo;
	}

	@Column(name = "userIdPilot", nullable = false, length = 36)
	public String getUserIdPilot() {
		return userIdPilot;
	}

	public void setUserIdPilot(String userIdPilot) {
		this.userIdPilot = userIdPilot;
	}

	@Column(name = "pilotName", nullable = false, length = 50)
	public String getPilotName() {
		return pilotName;
	}

	public void setPilotName(String pilotName) {
		this.pilotName = pilotName;
	}

	@Column(name = "stafPhonePilot", nullable = false, length = 15)
	public String getStafPhonePilot() {
		return stafPhonePilot;
	}

	public void setStafPhonePilot(String stafPhonePilot) {
		this.stafPhonePilot = stafPhonePilot;
	}

	@Column(name = "dictIdDrivingLlicenseKind", nullable = false, length = 36)
	public String getDictIdDrivingLlicenseKind() {
		return dictIdDrivingLlicenseKind;
	}

	public void setDictIdDrivingLlicenseKind(String dictIdDrivingLlicenseKind) {
		this.dictIdDrivingLlicenseKind = dictIdDrivingLlicenseKind;
	}

	@Column(name = "drivingLlicenseKind", nullable = false, length = 50)
	public String getDrivingLlicenseKind() {
		return drivingLlicenseKind;
	}

	public void setDrivingLlicenseKind(String drivingLlicenseKind) {
		this.drivingLlicenseKind = drivingLlicenseKind;
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

	@Column(name = "carSendType", nullable = false, length = 50)
	public String getCarSendType() {
		return this.carSendType;
	}

	public void setCarSendType(String carSendType) {
		this.carSendType = carSendType;
	}

	@Column(name = "userNameHandler", nullable = false, length = 50)
	public String getUserNameHandler() {
		return this.userNameHandler;
	}

	public void setUserNameHandler(String userNameHandler) {
		this.userNameHandler = userNameHandler;
	}

	@Column(name = "userNameCreate", nullable = false, length = 50)
	public String getUserNameCreate() {
		return this.userNameCreate;
	}

	public void setUserNameCreate(String userNameCreate) {
		this.userNameCreate = userNameCreate;
	}

	@Column(name = "userNameUpdate", nullable = false, length = 50)
	public String getUserNameUpdate() {
		return this.userNameUpdate;
	}

	public void setUserNameUpdate(String userNameUpdate) {
		this.userNameUpdate = userNameUpdate;
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

	@Column(name = "sendStatusName", nullable = false, length = 50)
	public String getSendStatusName() {
		return this.sendStatusName;
	}

	public void setSendStatusName(String sendStatusName) {
		this.sendStatusName = sendStatusName;
	}

	@Column(name = "dictIdCarType", nullable = false, length = 36)
	public String getDictIdCarType() {
		return dictIdCarType;
	}

	public void setDictIdCarType(String dictIdCarType) {
		this.dictIdCarType = dictIdCarType;
	}

	@Column(name = "carTypeName", nullable = false, length = 50)
	public String getCarTypeName() {
		return carTypeName;
	}

	public void setCarTypeName(String carTypeName) {
		this.carTypeName = carTypeName;
	}

}