package com.car.entity.obd;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TObdCarSummary entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_obd_car_summary")
public class TObdCarSummary implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4908519679228670795L;
	private String id;
	private String carId;
	private String obdNo;
	private Timestamp obdTime;
	private Timestamp ignitionTime;
	private Double ignitionLongitude;
	private Double ignitionLatitude;
	private Timestamp flameoutTime;
	private Double flameoutLongitude;
	private Double flameoutLatitude;
	private Integer distance;
	private Integer fuelConsumption;
	private Integer maxSpeed;
	private Integer idleTime;
	private Integer idleFuelConsumption;
	private Integer overSpeedtime;
	private Integer overSpeedDistance;
	private Integer violentAccelerationCount;
	private Integer abruptDecelerationCount;
	private Integer extremeTurnCount;
	private Integer totleMileage;
	private Integer totalFuelConsumption;
	private Integer totalDrivingDuration;
	private String sendId;
	private String driverId;
	private Integer overSpeedFlag;
	private Integer tiredDrivingFlag;

	// Constructors

	/** default constructor */
	public TObdCarSummary() {
	}

	/** minimal constructor */
	public TObdCarSummary(String id) {
		this.id = id;
	}

	/** full constructor */
	public TObdCarSummary(String id, String carId, String obdNo, Timestamp obdTime, Timestamp ignitionTime, Double ignitionLongitude,
			Double ignitionLatitude, Timestamp flameoutTime, Double flameoutLongitude, Double flameoutLatitude, Integer distance,
			Integer fuelConsumption, Integer maxSpeed, Integer idleTime, Integer idleFuelConsumption, Integer overSpeedtime,
			Integer overSpeedDistance, Integer violentAccelerationCount, Integer abruptDecelerationCount, Integer extremeTurnCount,
			Integer totleMileage, Integer totalFuelConsumption, Integer totalDrivingDuration, String sendId, String driverId,
			Integer overSpeedFlag, Integer tiredDrivingFlag) {
		this.id = id;
		this.carId = carId;
		this.obdNo = obdNo;
		this.obdTime = obdTime;
		this.ignitionTime = ignitionTime;
		this.ignitionLongitude = ignitionLongitude;
		this.ignitionLatitude = ignitionLatitude;
		this.flameoutTime = flameoutTime;
		this.flameoutLongitude = flameoutLongitude;
		this.flameoutLatitude = flameoutLatitude;
		this.distance = distance;
		this.fuelConsumption = fuelConsumption;
		this.maxSpeed = maxSpeed;
		this.idleTime = idleTime;
		this.idleFuelConsumption = idleFuelConsumption;
		this.overSpeedtime = overSpeedtime;
		this.overSpeedDistance = overSpeedDistance;
		this.violentAccelerationCount = violentAccelerationCount;
		this.abruptDecelerationCount = abruptDecelerationCount;
		this.extremeTurnCount = extremeTurnCount;
		this.totleMileage = totleMileage;
		this.totalFuelConsumption = totalFuelConsumption;
		this.totalDrivingDuration = totalDrivingDuration;
		this.sendId = sendId;
		this.driverId = driverId;
		this.overSpeedFlag = overSpeedFlag;
		this.tiredDrivingFlag = tiredDrivingFlag;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "carId", length = 36)
	public String getCarId() {
		return this.carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	@Column(name = "obdNo", length = 50)
	public String getObdNo() {
		return this.obdNo;
	}

	public void setObdNo(String obdNo) {
		this.obdNo = obdNo;
	}

	@Column(name = "obdTime", length = 19)
	public Timestamp getObdTime() {
		return this.obdTime;
	}

	public void setObdTime(Timestamp obdTime) {
		this.obdTime = obdTime;
	}

	@Column(name = "ignitionTime", length = 19)
	public Timestamp getIgnitionTime() {
		return this.ignitionTime;
	}

	public void setIgnitionTime(Timestamp ignitionTime) {
		this.ignitionTime = ignitionTime;
	}

	@Column(name = "ignitionLongitude", precision = 10, scale = 6)
	public Double getIgnitionLongitude() {
		return this.ignitionLongitude;
	}

	public void setIgnitionLongitude(Double ignitionLongitude) {
		this.ignitionLongitude = ignitionLongitude;
	}

	@Column(name = "ignitionLatitude", precision = 10, scale = 6)
	public Double getIgnitionLatitude() {
		return this.ignitionLatitude;
	}

	public void setIgnitionLatitude(Double ignitionLatitude) {
		this.ignitionLatitude = ignitionLatitude;
	}

	@Column(name = "flameoutTime", length = 19)
	public Timestamp getFlameoutTime() {
		return this.flameoutTime;
	}

	public void setFlameoutTime(Timestamp flameoutTime) {
		this.flameoutTime = flameoutTime;
	}

	@Column(name = "flameoutLongitude", precision = 10, scale = 6)
	public Double getFlameoutLongitude() {
		return this.flameoutLongitude;
	}

	public void setFlameoutLongitude(Double flameoutLongitude) {
		this.flameoutLongitude = flameoutLongitude;
	}

	@Column(name = "flameoutLatitude", precision = 10, scale = 6)
	public Double getFlameoutLatitude() {
		return this.flameoutLatitude;
	}

	public void setFlameoutLatitude(Double flameoutLatitude) {
		this.flameoutLatitude = flameoutLatitude;
	}

	@Column(name = "distance")
	public Integer getDistance() {
		return this.distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	@Column(name = "fuelConsumption")
	public Integer getFuelConsumption() {
		return this.fuelConsumption;
	}

	public void setFuelConsumption(Integer fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}

	@Column(name = "maxSpeed")
	public Integer getMaxSpeed() {
		return this.maxSpeed;
	}

	public void setMaxSpeed(Integer maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	@Column(name = "idleTime")
	public Integer getIdleTime() {
		return this.idleTime;
	}

	public void setIdleTime(Integer idleTime) {
		this.idleTime = idleTime;
	}

	@Column(name = "idleFuelConsumption")
	public Integer getIdleFuelConsumption() {
		return this.idleFuelConsumption;
	}

	public void setIdleFuelConsumption(Integer idleFuelConsumption) {
		this.idleFuelConsumption = idleFuelConsumption;
	}

	@Column(name = "overSpeedtime")
	public Integer getOverSpeedtime() {
		return this.overSpeedtime;
	}

	public void setOverSpeedtime(Integer overSpeedtime) {
		this.overSpeedtime = overSpeedtime;
	}

	@Column(name = "overSpeedDistance")
	public Integer getOverSpeedDistance() {
		return this.overSpeedDistance;
	}

	public void setOverSpeedDistance(Integer overSpeedDistance) {
		this.overSpeedDistance = overSpeedDistance;
	}

	@Column(name = "violentAccelerationCount")
	public Integer getViolentAccelerationCount() {
		return this.violentAccelerationCount;
	}

	public void setViolentAccelerationCount(Integer violentAccelerationCount) {
		this.violentAccelerationCount = violentAccelerationCount;
	}

	@Column(name = "abruptDecelerationCount")
	public Integer getAbruptDecelerationCount() {
		return this.abruptDecelerationCount;
	}

	public void setAbruptDecelerationCount(Integer abruptDecelerationCount) {
		this.abruptDecelerationCount = abruptDecelerationCount;
	}

	@Column(name = "extremeTurnCount")
	public Integer getExtremeTurnCount() {
		return this.extremeTurnCount;
	}

	public void setExtremeTurnCount(Integer extremeTurnCount) {
		this.extremeTurnCount = extremeTurnCount;
	}

	@Column(name = "totleMileage")
	public Integer getTotleMileage() {
		return this.totleMileage;
	}

	public void setTotleMileage(Integer totleMileage) {
		this.totleMileage = totleMileage;
	}

	@Column(name = "totalFuelConsumption")
	public Integer getTotalFuelConsumption() {
		return this.totalFuelConsumption;
	}

	public void setTotalFuelConsumption(Integer totalFuelConsumption) {
		this.totalFuelConsumption = totalFuelConsumption;
	}

	@Column(name = "totalDrivingDuration")
	public Integer getTotalDrivingDuration() {
		return this.totalDrivingDuration;
	}

	public void setTotalDrivingDuration(Integer totalDrivingDuration) {
		this.totalDrivingDuration = totalDrivingDuration;
	}

	@Column(name = "sendId", length = 36)
	public String getSendId() {
		return this.sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	@Column(name = "driverId", length = 36)
	public String getDriverId() {
		return this.driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	@Column(name = "overSpeedFlag")
	public Integer getOverSpeedFlag() {
		return this.overSpeedFlag;
	}

	public void setOverSpeedFlag(Integer overSpeedFlag) {
		this.overSpeedFlag = overSpeedFlag;
	}

	@Column(name = "tiredDrivingFlag")
	public Integer getTiredDrivingFlag() {
		return this.tiredDrivingFlag;
	}

	public void setTiredDrivingFlag(Integer tiredDrivingFlag) {
		this.tiredDrivingFlag = tiredDrivingFlag;
	}

}