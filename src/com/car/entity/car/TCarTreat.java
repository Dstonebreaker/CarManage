package com.car.entity.car;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="t_car_treat")
public class TCarTreat implements  java.io.Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 576607058995933019L;
	private String treatId;//ID
	private String 	treatNo ;//申请单编号
	private String treatUnit;//申请单位
	private String carId;//车辆id
	private String dictIdTreatMode;//处置方式
	private String dictIdTreatResult;//处置结果
	private String treatReason;//处置原因
	private String userIdCreate;//申请人
	private Date timeCreate;//申请日期
	private String treatUserId;//处置人
	private String treatTime;//处置时间
	private String treatVerifyUserId;//审批人
	private Date treatVerifyTime;//审批时间
	private String treatVerifyOpinion;//审批意见
	private String dictIdFlag;
	
	
	@Id
	@Column(name = "treatId", unique = true, nullable = false, length = 36)
	public String getTreatId() {
		return treatId;
	}
	public void setTreatId(String treatId) {
		this.treatId = treatId;
	}
	@Column(name = "treatNo",nullable = false,length = 36)
	public String getTreatNo() {
		return treatNo;
	}
	public void setTreatNo(String treatNo) {
		this.treatNo = treatNo;
	}
	@Column(name = "treatUnit",nullable = false,length = 20)
	public String getTreatUnit() {
		return treatUnit;
	}
	public void setTreatUnit(String treatUnit) {
		this.treatUnit = treatUnit;
	}
	@Column(name = "carId",nullable = false,length = 36)
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	@Column(name = "dictIdTreatMode",nullable = false,length = 36)
	public String getDictIdTreatMode() {
		return dictIdTreatMode;
	}
	public void setDictIdTreatMode(String dictIdTreatMode) {
		this.dictIdTreatMode = dictIdTreatMode;
	}
	@Column(name = "dictIdTreatResult",nullable = false,length = 36)
	public String getDictIdTreatResult() {
		return dictIdTreatResult;
	}
	public void setDictIdTreatResult(String dictIdTreatResult) {
		this.dictIdTreatResult = dictIdTreatResult;
	}
	@Column(name = "treatReason",length = 200)
	public String getTreatReason() {
		return treatReason;
	}
	public void setTreatReason(String treatReason) {
		this.treatReason = treatReason;
	}
	@Column(name = "userIdCreate",nullable = false,length = 11)
	public String getUserIdCreate() {
		return userIdCreate;
	}
	public void setUserIdCreate(String userIdCreate) {
		this.userIdCreate = userIdCreate;
	}
	@Column(name = "timeCreate",nullable = false,length = 19)
	public Date getTimeCreate() {
		return timeCreate;
	}
	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}
	@Column(name = "treatUserId",length = 36)
	public String getTreatUserId() {
		return treatUserId;
	}
	public void setTreatUserId(String treatUserId) {
		this.treatUserId = treatUserId;
	}
	@Column(name = "treatTime",length = 19)
	public String getTreatTime() {
		return treatTime;
	}
	public void setTreatTime(String treatTime) {
		this.treatTime = treatTime;
	}
	@Column(name = "treatVerifyUserId",length = 36)
	public String getTreatVerifyUserId() {
		return treatVerifyUserId;
	}
	public void setTreatVerifyUserId(String treatVerifyUserId) {
		this.treatVerifyUserId = treatVerifyUserId;
	}
	@Column(name = "treatVerifyTime",length = 19)
	public Date getTreatVerifyTime() {
		return treatVerifyTime;
	}
	public void setTreatVerifyTime(Date treatVerifyTime) {
		this.treatVerifyTime = treatVerifyTime;
	}
	@Column(name = "treatVerifyOpinion",length = 200)
	public String getTreatVerifyOpinion() {
		return treatVerifyOpinion;
	}
	public void setTreatVerifyOpinion(String treatVerifyOpinion) {
		this.treatVerifyOpinion = treatVerifyOpinion;
	}
	@Column(name = "dictIdFlag",length = 36)
	public String getDictIdFlag() {
		return dictIdFlag;
	}
	public void setDictIdFlag(String dictIdFlag) {
		this.dictIdFlag = dictIdFlag;
	}
	
	
	
}
