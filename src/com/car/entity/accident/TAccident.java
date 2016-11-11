package com.car.entity.accident;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.car.entity.car.TCar;
import com.car.entity.car.TCarSend;

/**
 * 事故类
 * 
 * @author Marlon
 * 
 */

@Entity
@Table(name = "t_car_accident")
public class TAccident implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2438642799898073300L;
	
	// 车牌号、产权单位、驾驶员、事故地点、事故日期、事故类型、事故责任、责任比例、保险理赔金额、私人理赔金额、派车单、事故处理状态。

	private String sendId; // 派车id varchar(36) YES

	private String acciId; // id（GUID） varchar(36) PRI NO
	private String carNo; // 车牌号 varchar(36) NO
	private String carId; // 车牌ID varchar(12) NO
	private String acciNo;// 事故单号
	private String ownerCompany; // 产权单位
	//private String stafId;//
	private String stafName; // 驾驶员
	private String acciAddress; // 事故地点 varchar(100)
	private Date acciTime; // 事故时间 datetime NO
	private String dictIdAcciType; // 事故类型 varchar(45)
	private String dictIdAcciState; // 事故状态
	private String dictIdAccidentRisk; // 事故责任 varchar(36) NO
	private Integer acciRiskProportion; // 责任比例 Integer(11) NO
	private double acciInsuranceMoney; // 保险理赔金额 decimal(10,2) NO
	private double acciSelfMoney; // 私人理赔金额 decimal(10,2) NO
	private String acciExplain; // 事故说明varchar(400) NO
	private Integer acciDeductMark; // 扣分 Integer 11 no
	private String dictIdFlag;
	private String userIdCreate; // 登记人id varchar(36) NO
	private Date timeCreate; // 登记时间 datetime NO
	private String userIdUpdate; // 修改人id varchar(36) YES
	private Date timeUpdate; // 修改时间 datetime YES

	private TCar car;
	//private TCarSend send;


	@Id
	@Column(name = "acciId", unique = true, nullable = false, columnDefinition = "nvarchar(50)")
	public String getAcciId() {
		return acciId;
	}

	public void setAcciId(String acciId) {
		this.acciId = acciId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "acciTime", nullable = false, length = 23)
	@OrderBy("DESC ")
	public Date getAcciTime() {
		return acciTime;
	}

	public void setAcciTime(Date acciTime) {
		this.acciTime = acciTime;
	}

	@Column(name = "carNo", columnDefinition = "nvarchar(50)", nullable = false)
	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	@Column(name = "acciExplain", columnDefinition = "nvarchar(400)", nullable = false)
	public String getAcciExplain() {
		return acciExplain;
	}

	public void setAcciExplain(String acciExplain) {
		this.acciExplain = acciExplain;
	}

	@Column(name = "carId", columnDefinition = "nvarchar(50)", nullable = false)
	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	@Column(name = "sendId", columnDefinition = "nvarchar(50)", nullable = true)
	public String getSendId() {
		return sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	@Column(name = "stafName", columnDefinition = "nvarchar(50)", nullable = false)
	public String getStafName() {
		return stafName;
	}

	public void setStafName(String stafName) {
		this.stafName = stafName;
	}

	@Column(name = "acciAddress", columnDefinition = "nvarchar(150)", nullable = false)
	public String getAcciAddress() {
		return acciAddress;
	}

	public void setAcciAddress(String acciAddress) {
		this.acciAddress = acciAddress;
	}

	@Column(name = "dictIdAcciType", columnDefinition = "nvarchar(50)", nullable = false)
	public String getDictIdAcciType() {
		return dictIdAcciType;
	}

	public void setDictIdAcciType(String dictIdAcciType) {
		this.dictIdAcciType = dictIdAcciType;
	}

	@Column(name = "dictIdAcciState", columnDefinition = "nvarchar(50)", nullable = false)
	public String getDictIdAcciState() {
		return dictIdAcciState;
	}

	public void setDictIdAcciState(String acciState) {
		this.dictIdAcciState = acciState;
	}

	@Column(name = "dictIdAccidentRisk", columnDefinition = "nvarchar(50)", nullable = false)
	public String getDictIdAccidentRisk() {
		return dictIdAccidentRisk;
	}

	public void setDictIdAccidentRisk(String dictIdAccidentRisk) {
		this.dictIdAccidentRisk = dictIdAccidentRisk;
	}

	@Column(name = "acciDeductMark", columnDefinition = "Integer(11)", nullable = false)
	public Integer getAcciDeductMark() {
		return acciDeductMark;
	}

	public void setAcciDeductMark(Integer acciDeductMark) {
		this.acciDeductMark = acciDeductMark;
	}

	@Column(name = "acciRiskProportion", columnDefinition = "Integer(11)", nullable = false)
	public Integer getAcciRiskProportion() {
		return acciRiskProportion;
	}

	public void setAcciRiskProportion(Integer acciRiskProportion) {
		this.acciRiskProportion = acciRiskProportion;
	}

	@Column(name = "acciInsuranceMoney", columnDefinition = "decimal(10,2)", nullable = false)
	public double getAcciInsuranceMoney() {
		return acciInsuranceMoney;
	}

	public void setAcciInsuranceMoney(double acciInsuranceMoney) {
		this.acciInsuranceMoney = acciInsuranceMoney;
	}

	@Column(name = "acciSelfMoney", columnDefinition = "decimal(10,2)", nullable = false)
	public double getAcciSelfMoney() {
		return acciSelfMoney;
	}

	public void setAcciSelfMoney(double acciSelfMoney) {
		this.acciSelfMoney = acciSelfMoney;
	}

	@Column(name = "userIdCreate", columnDefinition = "nvarchar(50)", nullable = false)
	public String getUserIdCreate() {
		return userIdCreate;
	}

	public void setUserIdCreate(String userIdCreate) {
		this.userIdCreate = userIdCreate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "timeCreate", length = 23)
	public Date getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}

	@Column(name = "userIdUpdate", columnDefinition = "nvarchar(50)", nullable = true)
	public String getUserIdUpdate() {
		return userIdUpdate;
	}

	public void setUserIdUpdate(String userIdUpdate) {
		this.userIdUpdate = userIdUpdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "timeUpdate", length = 23)
	public Date getTimeUpdate() {
		return timeUpdate;
	}

	public void setTimeUpdate(Date timeUpdate) {
		this.timeUpdate = timeUpdate;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "carId", updatable = false, insertable = false)
	public TCar getCar() {
		return car;
	}

	public void setCar(TCar car) {
		this.car = car;
	}
	
	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "sendId", updatable = false, insertable = false)
//	public TCarSend getSend() {
//		return send;
//	}
//	public void setSend(TCarSend send) {
//		this.send = send;
//	}
	
	

	@Override
	public String toString() {
		return "TAccident [sendId=" + sendId + ", acciId=" + acciId + ", carNo=" + carNo + ", carId=" + carId + ", ownerCompany="
				+ ownerCompany + ", stafName=" + stafName + ", acciAddress=" + acciAddress + ", acciTime=" + acciTime + ", dictIdAcciType="
				+ dictIdAcciType + ", dictIdAcciState=" + dictIdAcciState + ", dictIdAccidentRisk=" + dictIdAccidentRisk
				+ ", acciRiskProportion=" + acciRiskProportion + ", acciInsuranceMoney=" + acciInsuranceMoney + ", acciSelfMoney="
				+ acciSelfMoney + ", acciExplain=" + acciExplain + ", acciDeductMark=" + acciDeductMark + ", userIdCreate=" + userIdCreate
				+ ", timeCreate=" + timeCreate + ", userIdUpdate=" + userIdUpdate + ", timeUpdate=" + timeUpdate + ", car=" + car + "]";
	}

	@Column(name = "acciNo", columnDefinition = "varchar(50)", nullable = false)
	public String getAcciNo() {
		return acciNo;
	}

	public void setAcciNo(String acciNo) {
		this.acciNo = acciNo;
	}

	@Column(name = "dictIdFlag", columnDefinition = "varchar(50)", nullable = false)
	public String getDictIdFlag() {
		return dictIdFlag;
	}

	public void setDictIdFlag(String dictIdFlag) {
		this.dictIdFlag = dictIdFlag;
	}

	@Column(name = "ownerCompany", columnDefinition = "varchar(50)", nullable = false)
	public String getOwnerCompany() {
		return ownerCompany;
	}

	public void setOwnerCompany(String ownerCompany) {
		this.ownerCompany = ownerCompany;
	}
//	@Column(name = "stafId", columnDefinition = "varchar(50)", nullable = false)
//	public String getStafId() {
//		return stafId;
//	}
//
//	public void setStafId(String stafId) {
//		this.stafId = stafId;
//	}

}
