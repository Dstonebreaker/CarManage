package com.car.entity.car;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TCarApply entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_car_apply")
public class TCarApply implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 597636235045391740L;
	private String applyId; // 主键（GUID）
	private String applyNo; // 申请编号（YYYYMMDD+三位流水号）
	private String dictIdCarType; // 车辆种类
	private String orgId; // 组织ID
	private String carId; // 固定用车id
	private String carPoliceUsed;
	private String userIdLinkman; // 申请人ID
	private String stafPhoneLinkman; // 申请人手机
	private Integer applyUsedPeople; // 乘车人数
	private String dictIdUseCarReson; // 用车理由
	private Date applyUsingTime; // 预计用车时间
	private Date applyRemandTime; // 预计还车时间
	private String dictIdRegion; // 用车区域
	/*
	 * private String userIdPilot; // 驾驶员ID private String stafPhonePilot; //
	 * 驾驶员手机 private String dictIdDrivingLlicenseKind; // 驾驶员准驾车型
	 */
	private String dictIdIsSecret; // 是否保密
	private String applyMemo; // 备注
	private String userIdCreate; // 登记人ID
	private Date timeCreate; // 登记时间
	private String dictIdCheckStatus; // 审批状态id
	private String userIdCheck1; // 科室审批人id
	private Date timeCheck1; // 科室审批时间
	private String applyCheck1; // 科室审批意见
	private String userIdCheck2; // 处审批人id
	private Date timeCheck2; // 处审批时间
	private String applyCheck2; // 处审批意见
	private String userIdCheck3; // 第三级审批人id（预留）
	private Date timeCheck3; // 第三级审批时间（预留）
	private String applyCheck3; // 第三级审批意见（预留）
	private String applyType; // 申请类型（有单/无单）
	private String preApplyId; // 重新申请的作废单据id（被继承的id，被拒绝的单据）
	private String newApplyId; // 重新申请的单据id（新的）

	// Constructors

	/** default constructor */
	public TCarApply() {
	}

	// Property accessors
	@Id
	@Column(name = "applyId", unique = true, nullable = false, length = 36)
	public String getApplyId() {
		return this.applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

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
	public void setDictIdUseCarReson(String dictIdUseCarReson) {
		this.dictIdUseCarReson = dictIdUseCarReson;
	}
	
	public void setApplyUsingTime(Date applyUsingTime) {
		this.applyUsingTime = applyUsingTime;
	}

	@Column(name = "applyUsingTime", nullable = false, length = 19)
	public Date getApplyUsingTime() {
		return this.applyUsingTime;
	}

	public String getDictIdUseCarReson() {
		return dictIdUseCarReson;
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

	/*
	 * @Column(name = "userIdPilot", length = 36) public String getUserIdPilot()
	 * { return this.userIdPilot; }
	 * 
	 * public void setUserIdPilot(String userIdPilot) { this.userIdPilot =
	 * userIdPilot; }
	 * 
	 * @Column(name = "stafPhonePilot", length = 15) public String
	 * getStafPhonePilot() { return this.stafPhonePilot; }
	 * 
	 * public void setStafPhonePilot(String stafPhonePilot) {
	 * this.stafPhonePilot = stafPhonePilot; }
	 * 
	 * @Column(name = "dictIdDrivingLlicenseKind", length = 36) public String
	 * getDictIdDrivingLlicenseKind() { return this.dictIdDrivingLlicenseKind; }
	 * 
	 * public void setDictIdDrivingLlicenseKind(String
	 * dictIdDrivingLlicenseKind) { this.dictIdDrivingLlicenseKind =
	 * dictIdDrivingLlicenseKind; }
	 */

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

	@Column(name = "dictIdCheckStatus", nullable = false, length = 36)
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

	@Column(name = "userIdCheck2", length = 36)
	public String getUserIdCheck2() {
		return this.userIdCheck2;
	}

	public void setUserIdCheck2(String userIdCheck2) {
		this.userIdCheck2 = userIdCheck2;
	}

	@Column(name = "timeCheck2", length = 19)
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

	@Column(name = "applyType", nullable = false, length = 45)
	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
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

}