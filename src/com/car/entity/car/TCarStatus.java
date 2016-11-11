package com.car.entity.car;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TCarStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_car_status")
public class TCarStatus implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5111442246512979973L;
	private String carId; // id
	private String dictIdCarStatus; // 车辆状态
	private String simNo; // sim卡号
	private String keyNo;// 车辆钥匙号
	private String obdNo;// obd编号
	private String userIdUpdate; // 修改人id
	private Date timeUpdate; // 修改时间
	private Integer carMileage;
	private Integer carOil;

	// Constructors

	/** default constructor */
	public TCarStatus() {
	}

	/** full constructor */
	public TCarStatus(String carId, String dictIdCarStatus, String simNo, String userIdUpdate, Date timeUpdate) {
		this.carId = carId;
		this.dictIdCarStatus = dictIdCarStatus;
		this.simNo = simNo;
		this.userIdUpdate = userIdUpdate;
		this.timeUpdate = timeUpdate;
	}

	// Property accessors
	@Id
	@Column(name = "carId", unique = true, nullable = false, length = 36)
	public String getCarId() {
		return this.carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	@Column(name = "dictIdCarStatus", nullable = false, length = 36)
	public String getDictIdCarStatus() {
		return this.dictIdCarStatus;
	}

	public void setDictIdCarStatus(String dictIdCarStatus) {
		this.dictIdCarStatus = dictIdCarStatus;
	}

	@Column(name = "simNo", length = 15)
	public String getSimNo() {
		return this.simNo;
	}

	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}

	@Column(name = "userIdUpdate", nullable = false, length = 36)
	public String getUserIdUpdate() {
		return this.userIdUpdate;
	}

	public void setUserIdUpdate(String userIdUpdate) {
		this.userIdUpdate = userIdUpdate;
	}

	@Column(name = "timeUpdate", nullable = false, length = 19)
	public Date getTimeUpdate() {
		return this.timeUpdate;
	}

	public void setTimeUpdate(Date timeUpdate) {
		this.timeUpdate = timeUpdate;
	}

	@Column(name = "keyNo", length = 36)
	public String getKeyNo() {
		return keyNo;
	}

	public void setKeyNo(String keyNo) {
		this.keyNo = keyNo;
	}

	@Column(name = "obdNo", length = 36)
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

}