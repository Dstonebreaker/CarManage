package com.car.entity.car;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * VCarApply entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "v_car_apply")
public class VCarApply implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5016956693843413475L;
	// Fields
	private String applyId; // 主键（GUID）
	private String applyNo; // 申请编号（YYYYMMDD+三位流水号）
	private String dictIdCarType; // 车型ID
	private String carType;
	private String orgId; // 组织ID
	private String carId; // 固定车辆id
	private String carPoliceUsed;
	private String carNo;
	private String orgName;
	private String orgIdManager;
	private String userIdLinkman; // 联系人ID
	private String linkManName;
	private String stafPhoneLinkman; // 联系手机
	private Integer applyUsedPeople; // 乘车人数
	private String dictIdUseCarReson; // 用车理由
	private Date applyUsingTime; // 预计用车时间
	private Date applyRemandTime; // 预计还车时间
	private String dictIdRegion; // 用车区域
	private String region;
	private String dictIdIsSecret; // 是否保密
	private String isSecret;
	private String applyMemo; // 备注
	private String userIdCreate; // 登记人ID
	private String userNameCreate;
	private Date timeCreate; // 登记时间
	private String dictIdCheckStatus; // 审批状态id
	private String checkStatus;
	private String userIdCheck1; // 科室审批人id
	private String userNameCheck1;
	private String userCheckSign1; // 审批人签名图片地址
	private Date timeCheck1; // 科室审批时间
	private String applyCheck1; // 科室审批意见
	private String userIdCheck2; // 处审批人id
	private String userNameCheck2;
	private Date timeCheck2; // 处审批时间
	private String applyCheck2; // 处审批意见
	private String userIdCheck3; // 第三级审批人id（预留）
	private Date timeCheck3; // 第三级审批时间（预留）
	private String applyCheck3; // 第三级审批意见（预留）
	private String applyType; // 申请类型（有单申请/无单申请）
	private String applyTypeName;
	private String dictIdApplySendStatus; // 用车状态
	private String sendStatus; // 用车状态
	private String preApplyId;
	private String newApplyId;
	private String orgIdCheck; // 审批单位id
	private String useCarReson;

	// Constructors

	/** default constructor */
	public VCarApply() {
	}

	// Property accessors
	@Column(name = "applyNo", nullable = false, length = 20)
	public String getApplyNo() {
		return this.applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	@Column(name = "dictIdCarType", length = 36)
	public String getDictIdCarType() {
		return dictIdCarType;
	}

	public void setDictIdCarType(String dictIdCarType) {
		this.dictIdCarType = dictIdCarType;
	}

	@Column(name = "orgId", nullable = false, length = 36)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "carId", length = 36)
	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	@Column(name = "carPoliceUsed", length = 20)
	public String getCarPoliceUsed() {
		return carPoliceUsed;
	}

	public void setCarPoliceUsed(String carPoliceUsed) {
		this.carPoliceUsed = carPoliceUsed;
	}

	@Column(name = "carNo", length = 12)
	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	@Column(name = "userIdLinkman", nullable = false, length = 36)
	public String getUserIdLinkman() {
		return this.userIdLinkman;
	}

	public void setUserIdLinkman(String userIdLinkman) {
		this.userIdLinkman = userIdLinkman;
	}

	@Column(name = "stafPhoneLinkman", nullable = false, length = 15)
	public String getStafPhoneLinkman() {
		return this.stafPhoneLinkman;
	}

	public void setStafPhoneLinkman(String stafPhoneLinkman) {
		this.stafPhoneLinkman = stafPhoneLinkman;
	}

	@Column(name = "applyUsedPeople", nullable = false)
	public Integer getApplyUsedPeople() {
		return this.applyUsedPeople;
	}

	public void setApplyUsedPeople(Integer applyUsedPeople) {
		this.applyUsedPeople = applyUsedPeople;
	}

	@Column(name = "dictIdUseCarReson", nullable = false, length = 36)
	public String getDictIdUseCarReson() {
		return dictIdUseCarReson;
	}

	public void setDictIdUseCarReson(String dictIdUseCarReson) {
		this.dictIdUseCarReson = dictIdUseCarReson;
	}

	@Column(name = "applyUsingTime", nullable = false, length = 19)
	public Date getApplyUsingTime() {
		return this.applyUsingTime;
	}

	public void setApplyUsingTime(Date applyUsingTime) {
		this.applyUsingTime = applyUsingTime;
	}

	@Column(name = "applyRemandTime", nullable = false, length = 19)
	public Date getApplyRemandTime() {
		return this.applyRemandTime;
	}

	public void setApplyRemandTime(Date applyRemandTime) {
		this.applyRemandTime = applyRemandTime;
	}

	@Column(name = "dictIdRegion", nullable = false, length = 36)
	public String getDictIdRegion() {
		return this.dictIdRegion;
	}

	public void setDictIdRegion(String dictIdRegion) {
		this.dictIdRegion = dictIdRegion;
	}

	@Column(name = "dictIdIsSecret", nullable = false, length = 36)
	public String getDictIdIsSecret() {
		return this.dictIdIsSecret;
	}

	public void setDictIdIsSecret(String dictIdIsSecret) {
		this.dictIdIsSecret = dictIdIsSecret;
	}

	@Column(name = "applyMemo", nullable = false, length = 400)
	public String getApplyMemo() {
		return this.applyMemo;
	}

	public void setApplyMemo(String applyMemo) {
		this.applyMemo = applyMemo;
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

	@Id
	@Column(name = "applyId", nullable = false, length = 36)
	public String getApplyId() {
		return this.applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	@Column(name = "dictIdCheckStatus", nullable = false, length = 36)
	public String getDictIdCheckStatus() {
		return this.dictIdCheckStatus;
	}

	public void setDictIdCheckStatus(String dictIdCheckStatus) {
		this.dictIdCheckStatus = dictIdCheckStatus;
	}

	@Column(name = "userIdCheck1", nullable = false, length = 36)
	public String getUserIdCheck1() {
		return this.userIdCheck1;
	}

	public void setUserIdCheck1(String userIdCheck1) {
		this.userIdCheck1 = userIdCheck1;
	}

	@Column(name = "timeCheck1", nullable = false, length = 19)
	public Date getTimeCheck1() {
		return this.timeCheck1;
	}

	public void setTimeCheck1(Date timeCheck1) {
		this.timeCheck1 = timeCheck1;
	}

	@Column(name = "applyCheck1", length = 100)
	public String getApplyCheck1() {
		return this.applyCheck1;
	}

	public void setApplyCheck1(String applyCheck1) {
		this.applyCheck1 = applyCheck1;
	}

	@Column(name = "userIdCheck2", nullable = false, length = 36)
	public String getUserIdCheck2() {
		return this.userIdCheck2;
	}

	public void setUserIdCheck2(String userIdCheck2) {
		this.userIdCheck2 = userIdCheck2;
	}

	@Column(name = "timeCheck2", nullable = false, length = 19)
	public Date getTimeCheck2() {
		return this.timeCheck2;
	}

	public void setTimeCheck2(Date timeCheck2) {
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
	public Date getTimeCheck3() {
		return this.timeCheck3;
	}

	public void setTimeCheck3(Date timeCheck3) {
		this.timeCheck3 = timeCheck3;
	}

	@Column(name = "applyCheck3", length = 100)
	public String getApplyCheck3() {
		return this.applyCheck3;
	}

	public void setApplyCheck3(String applyCheck3) {
		this.applyCheck3 = applyCheck3;
	}

	@Column(name = "carType", nullable = false, length = 50)
	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	@Column(name = "orgName", nullable = false, length = 200)
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "orgIdManager", nullable = false, length = 36)
	public String getOrgIdManager() {
		return orgIdManager;
	}

	public void setOrgIdManager(String orgIdManager) {
		this.orgIdManager = orgIdManager;
	}

	@Column(name = "isSecret", nullable = false, length = 50)
	public String getIsSecret() {
		return this.isSecret;
	}

	public void setIsSecret(String isSecret) {
		this.isSecret = isSecret;
	}

	@Column(name = "userNameCreate", nullable = false, length = 50)
	public String getUserNameCreate() {
		return this.userNameCreate;
	}

	public void setUserNameCreate(String userNameCreate) {
		this.userNameCreate = userNameCreate;
	}

	@Column(name = "linkManName", nullable = false, length = 50)
	public String getLinkManName() {
		return this.linkManName;
	}

	public void setLinkManName(String linkManName) {
		this.linkManName = linkManName;
	}

	@Column(name = "region", nullable = false, length = 50)
	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Column(name = "userNameCheck1", nullable = false, length = 50)
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

	@Column(name = "userNameCheck2", nullable = false, length = 50)
	public String getUserNameCheck2() {
		return this.userNameCheck2;
	}

	public void setUserNameCheck2(String userNameCheck2) {
		this.userNameCheck2 = userNameCheck2;
	}

	@Column(name = "checkStatus", nullable = false, length = 50)
	public String getCheckStatus() {
		return this.checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	@Column(name = "applyType", nullable = false, length = 45)
	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	@Column(name = "applyTypeName", nullable = false, length = 50)
	public String getApplyTypeName() {
		return applyTypeName;
	}

	public void setApplyTypeName(String applyTypeName) {
		this.applyTypeName = applyTypeName;
	}

	@Column(name = "dictIdApplySendStatus", nullable = false, length = 36)
	public String getDictIdApplySendStatus() {
		return dictIdApplySendStatus;
	}

	public void setDictIdApplySendStatus(String dictIdApplySendStatus) {
		this.dictIdApplySendStatus = dictIdApplySendStatus;
	}

	@Column(name = "sendStatus", nullable = false, length = 50)
	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	@Column(name = "preApplyId", length = 36)
	public String getPreApplyId() {
		return preApplyId;
	}

	public void setPreApplyId(String preApplyId) {
		this.preApplyId = preApplyId;
	}

	@Column(name = "newApplyId", length = 36)
	public String getNewApplyId() {
		return newApplyId;
	}

	public void setNewApplyId(String newApplyId) {
		this.newApplyId = newApplyId;
	}

	@Column(name = "orgIdCheck", length = 36)
	public String getOrgIdCheck() {
		return orgIdCheck;
	}

	public void setOrgIdCheck(String orgIdCheck) {
		this.orgIdCheck = orgIdCheck;
	}

	@Column(name = "useCarReson", nullable = false, length = 50)
	public String getUseCarReson() {
		return useCarReson;
	}

	public void setUseCarReson(String useCarReson) {
		this.useCarReson = useCarReson;
	}

}