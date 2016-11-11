package com.car.entity.pgis;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * VObdGpsLast entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "v_obd_gps_last")
public class VObdGpsLast implements java.io.Serializable {
	
	// Fields

	private String carId;
	private Timestamp obdTime;
	private Double obdGpsLongitude;
	private Double obdGpsLatitude;
	private Long isOutRegion;
	private String carNo;
	private String carIdentifyNo;
	private String carColor;
	private String carKind;
	private String specialCar;
	private String usingKind;
	private String modelName;
	private String dictIdCarStatus;
	private String carStatus;
	private String orgIdManager;
	private String orgNameManager;
	private String orgId;
	private String orgName;
	private String orgRealPath;
	private String pilotName;
	private String stafPhonePilot;
	private String dictIdIsSecret;

	// Constructors

	/** default constructor */
	public VObdGpsLast() {
	}

	/** minimal constructor */
	public VObdGpsLast(Long isOutRegion, String carNo, String carIdentifyNo,
			String dictIdCarStatus, String carStatus, String orgIdManager) {
		this.isOutRegion = isOutRegion;
		this.carNo = carNo;
		this.carIdentifyNo = carIdentifyNo;
		this.dictIdCarStatus = dictIdCarStatus;
		this.carStatus = carStatus;
		this.orgIdManager = orgIdManager;
	}

	/** full constructor */
	public VObdGpsLast(Timestamp obdTime, Double obdGpsLongitude,
			Double obdGpsLatitude, Long isOutRegion, String carNo,
			String carIdentifyNo, String carColor, String carKind,
			String specialCar, String usingKind, String modelName,
			String dictIdCarStatus, String carStatus, String orgIdManager,
			String orgNameManager, String orgId, String orgName,
			String orgRealPath, String pilotName, String stafPhonePilot,
			String dictIdIsSecret) {
		this.obdTime = obdTime;
		this.obdGpsLongitude = obdGpsLongitude;
		this.obdGpsLatitude = obdGpsLatitude;
		this.isOutRegion = isOutRegion;
		this.carNo = carNo;
		this.carIdentifyNo = carIdentifyNo;
		this.carColor = carColor;
		this.carKind = carKind;
		this.specialCar = specialCar;
		this.usingKind = usingKind;
		this.modelName = modelName;
		this.dictIdCarStatus = dictIdCarStatus;
		this.carStatus = carStatus;
		this.orgIdManager = orgIdManager;
		this.orgNameManager = orgNameManager;
		this.orgId = orgId;
		this.orgName = orgName;
		this.orgRealPath = orgRealPath;
		this.pilotName = pilotName;
		this.stafPhonePilot = stafPhonePilot;
		this.dictIdIsSecret = dictIdIsSecret;
	}

	// Property accessors
	@Id
	@Column(name = "carId", nullable = false, length = 36)
	public String getCarId() {
		return this.carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	@Column(name = "obdTime", length = 19)
	public Timestamp getObdTime() {
		return this.obdTime;
	}

	public void setObdTime(Timestamp obdTime) {
		this.obdTime = obdTime;
	}

	@Column(name = "obdGpsLongitude", precision = 10, scale = 6)
	public Double getObdGpsLongitude() {
		return this.obdGpsLongitude;
	}

	public void setObdGpsLongitude(Double obdGpsLongitude) {
		this.obdGpsLongitude = obdGpsLongitude;
	}

	@Column(name = "obdGpsLatitude", precision = 10, scale = 6)
	public Double getObdGpsLatitude() {
		return this.obdGpsLatitude;
	}

	public void setObdGpsLatitude(Double obdGpsLatitude) {
		this.obdGpsLatitude = obdGpsLatitude;
	}

	@Column(name = "isOutRegion", nullable = false)
	public Long getIsOutRegion() {
		return this.isOutRegion;
	}

	public void setIsOutRegion(Long isOutRegion) {
		this.isOutRegion = isOutRegion;
	}

	@Column(name = "carNo", nullable = false, length = 12)
	public String getCarNo() {
		return this.carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	@Column(name = "carIdentifyNo", nullable = false, length = 20)
	public String getCarIdentifyNo() {
		return this.carIdentifyNo;
	}

	public void setCarIdentifyNo(String carIdentifyNo) {
		this.carIdentifyNo = carIdentifyNo;
	}

	@Column(name = "carColor", length = 50)
	public String getCarColor() {
		return this.carColor;
	}

	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}

	@Column(name = "carKind", length = 50)
	public String getCarKind() {
		return this.carKind;
	}

	public void setCarKind(String carKind) {
		this.carKind = carKind;
	}

	@Column(name = "specialCar", length = 50)
	public String getSpecialCar() {
		return this.specialCar;
	}

	public void setSpecialCar(String specialCar) {
		this.specialCar = specialCar;
	}

	@Column(name = "usingKind", length = 50)
	public String getUsingKind() {
		return this.usingKind;
	}

	public void setUsingKind(String usingKind) {
		this.usingKind = usingKind;
	}

	@Column(name = "modelName", length = 50)
	public String getModelName() {
		return this.modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	@Column(name = "dictIdCarStatus", nullable = false, length = 36)
	public String getDictIdCarStatus() {
		return this.dictIdCarStatus;
	}

	public void setDictIdCarStatus(String dictIdCarStatus) {
		this.dictIdCarStatus = dictIdCarStatus;
	}

	@Column(name = "CarStatus", nullable = false, length = 50)
	public String getCarStatus() {
		return this.carStatus;
	}

	public void setCarStatus(String carStatus) {
		this.carStatus = carStatus;
	}

	@Column(name = "orgIdManager", nullable = false, length = 36)
	public String getOrgIdManager() {
		return this.orgIdManager;
	}

	public void setOrgIdManager(String orgIdManager) {
		this.orgIdManager = orgIdManager;
	}

	@Column(name = "orgNameManager", length = 200)
	public String getOrgNameManager() {
		return this.orgNameManager;
	}

	public void setOrgNameManager(String orgNameManager) {
		this.orgNameManager = orgNameManager;
	}

	@Column(name = "orgId", length = 36)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "orgName", length = 200)
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "orgRealPath", length = 4000)
	public String getOrgRealPath() {
		return this.orgRealPath;
	}

	public void setOrgRealPath(String orgRealPath) {
		this.orgRealPath = orgRealPath;
	}

	@Column(name = "pilotName", length = 50)
	public String getPilotName() {
		return this.pilotName;
	}

	public void setPilotName(String pilotName) {
		this.pilotName = pilotName;
	}

	@Column(name = "stafPhonePilot", length = 15)
	public String getStafPhonePilot() {
		return this.stafPhonePilot;
	}

	public void setStafPhonePilot(String stafPhonePilot) {
		this.stafPhonePilot = stafPhonePilot;
	}

	@Column(name = "dictIdIsSecret", length = 36)
	public String getDictIdIsSecret() {
		return this.dictIdIsSecret;
	}

	public void setDictIdIsSecret(String dictIdIsSecret) {
		this.dictIdIsSecret = dictIdIsSecret;
	}

}