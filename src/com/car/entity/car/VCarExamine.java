package com.car.entity.car;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "v_car_examine")
public class VCarExamine implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 929493048904687700L;
	private String examId;// id（GUID）
	private String carId;// 车辆id
	private String carNo;// 车牌号
	private Double examCycle;// 审核周期（年）
	private Date examValidStartTime;// 生效日期
	private Date examValidOverTime;// 终止日期
	private Double examMoney;// 金额
	private String userId;// 经办人
	private String examMemo;// 备注
	private String dictIdFlag;// 标识
	private String userIdCreate;// 登记人id
	private Date timeCreate;// 登记时间
	private String userIdUpdate;// 修改人id
	private Date timeUpdate; // 修改时间
	private String userName;//经办人
	private int day;//到审天数
	private String orgIdManager;//产权单位
	private String orgName;//产权单位名字
	private Date nextEaxmTime;
	private String carFlag;

	@Id
	@Column(name = "examId", unique = true, nullable = false, length = 36)
	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	@Column(name = "carId", nullable = false, length = 36)
	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	@Column(name = "carNo", nullable = false, length = 12)
	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	@Column(name = "examCycle", nullable = false, length = 36)
	public Double getExamCycle() {
		return examCycle;
	}

	public void setExamCycle(Double examCycle) {
		this.examCycle = examCycle;
	}

	@Column(name = "examValidStartTime", nullable = false, length = 36)
	public Date getExamValidStartTime() {
		return examValidStartTime;
	}

	public void setExamValidStartTime(Date examValidStartTime) {
		this.examValidStartTime = examValidStartTime;
	}

	@Column(name = "examValidOverTime", nullable = false, length = 36)
	public Date getExamValidOverTime() {
		return examValidOverTime;
	}

	public void setExamValidOverTime(Date examValidOverTime) {
		this.examValidOverTime = examValidOverTime;
	}

	@Column(name = "examMoney", nullable = false, length = 36)
	public Double getExamMoney() {
		return examMoney;
	}

	public void setExamMoney(Double examMoney) {
		this.examMoney = examMoney;
	}

	@Column(name = "userId", nullable = false, length = 36)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "examMemo", nullable = false, length = 400)
	public String getExamMemo() {
		return examMemo;
	}

	public void setExamMemo(String examMemo) {
		this.examMemo = examMemo;
	}

	@Column(name = "dictIdFlag", nullable = false, length = 36)
	public String getDictIdFlag() {
		return dictIdFlag;
	}

	public void setDictIdFlag(String dictIdFlag) {
		this.dictIdFlag = dictIdFlag;
	}

	@Column(name = "userIdCreate", nullable = false, length = 36)
	public String getUserIdCreate() {
		return userIdCreate;
	}

	public void setUserIdCreate(String userIdCreate) {
		this.userIdCreate = userIdCreate;
	}

	@Column(name = "timeCreate", nullable = false, length = 11)
	public Date getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}

	@Column(name = "userIdUpdate", length = 36)
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
	@Column(name = "userName", length = 36)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name = "day", length = 36)
	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
	@Column(name = "orgIdManager", length = 36)
	public String getOrgIdManager() {
		return orgIdManager;
	}

	public void setOrgIdManager(String orgIdManager) {
		this.orgIdManager = orgIdManager;
	}
	@Column(name = "orgName", length = 36)
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	@Transient
	public Date getNextEaxmTime() {
		return nextEaxmTime;
	}

	public void setNextEaxmTime(Date nextEaxmTime) {
		this.nextEaxmTime = nextEaxmTime;
	}
	@Column(name = "carFlag", length = 36)
	public String getCarFlag() {
		return carFlag;
	}

	public void setCarFlag(String carFlag) {
		this.carFlag = carFlag;
	}
	
	
	
}
