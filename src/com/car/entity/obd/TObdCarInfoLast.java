package com.car.entity.obd;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TObdCarInfoLast entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_obd_car_info_last")
public class TObdCarInfoLast implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2892669569826488068L;
	private String obdNo;
	private String carId;
	private Timestamp obdTime;
	private Double obdOil;
	private Integer obdMileage;
	private Integer obdSpeed;
	private Integer obdCoolant;
	private Double obdAirDamper;
	private Double obdEngine;
	private Double obdAirFlow;
	private Integer obdAirTemperature;
	private Integer obdAirPressure;
	private Double obdBattery;
	private Integer obdAccident;
	private Integer obdUsedOil;
	private Integer obdDriverDuration;

	// Constructors

	/** default constructor */
	public TObdCarInfoLast() {
	}

	/** minimal constructor */
	public TObdCarInfoLast(String obdNo, Timestamp obdTime) {
		this.obdNo = obdNo;
		this.obdTime = obdTime;
	}

	/** full constructor */
	public TObdCarInfoLast(String obdNo, String carId, Timestamp obdTime, Double obdOil, Integer obdMileage, Integer obdSpeed,
			Integer obdCoolant, Double obdAirDamper, Double obdEngine, Double obdAirFlow, Integer obdAirTemperature,
			Integer obdAirPressure, Double obdBattery, Integer obdAccident, Integer obdUsedOil, Integer obdDriverDuration) {
		this.obdNo = obdNo;
		this.carId = carId;
		this.obdTime = obdTime;
		this.obdOil = obdOil;
		this.obdMileage = obdMileage;
		this.obdSpeed = obdSpeed;
		this.obdCoolant = obdCoolant;
		this.obdAirDamper = obdAirDamper;
		this.obdEngine = obdEngine;
		this.obdAirFlow = obdAirFlow;
		this.obdAirTemperature = obdAirTemperature;
		this.obdAirPressure = obdAirPressure;
		this.obdBattery = obdBattery;
		this.obdAccident = obdAccident;
		this.obdUsedOil = obdUsedOil;
		this.obdDriverDuration = obdDriverDuration;
	}

	// Property accessors
	@Id
	@Column(name = "obdNo", unique = true, nullable = false, length = 50)
	public String getObdNo() {
		return this.obdNo;
	}

	public void setObdNo(String obdNo) {
		this.obdNo = obdNo;
	}

	@Column(name = "carId", length = 36)
	public String getCarId() {
		return this.carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	@Column(name = "obdTime", nullable = false, length = 19)
	public Timestamp getObdTime() {
		return this.obdTime;
	}

	public void setObdTime(Timestamp obdTime) {
		this.obdTime = obdTime;
	}

	@Column(name = "obdOil", precision = 10)
	public Double getObdOil() {
		return this.obdOil;
	}

	public void setObdOil(Double obdOil) {
		this.obdOil = obdOil;
	}

	@Column(name = "obdMileage")
	public Integer getObdMileage() {
		return this.obdMileage;
	}

	public void setObdMileage(Integer obdMileage) {
		this.obdMileage = obdMileage;
	}

	@Column(name = "obdSpeed")
	public Integer getObdSpeed() {
		return this.obdSpeed;
	}

	public void setObdSpeed(Integer obdSpeed) {
		this.obdSpeed = obdSpeed;
	}

	@Column(name = "obdCoolant")
	public Integer getObdCoolant() {
		return this.obdCoolant;
	}

	public void setObdCoolant(Integer obdCoolant) {
		this.obdCoolant = obdCoolant;
	}

	@Column(name = "obdAirDamper", precision = 10, scale = 1)
	public Double getObdAirDamper() {
		return this.obdAirDamper;
	}

	public void setObdAirDamper(Double obdAirDamper) {
		this.obdAirDamper = obdAirDamper;
	}

	@Column(name = "obdEngine", precision = 10, scale = 1)
	public Double getObdEngine() {
		return this.obdEngine;
	}

	public void setObdEngine(Double obdEngine) {
		this.obdEngine = obdEngine;
	}

	@Column(name = "obdAirFlow", precision = 10)
	public Double getObdAirFlow() {
		return this.obdAirFlow;
	}

	public void setObdAirFlow(Double obdAirFlow) {
		this.obdAirFlow = obdAirFlow;
	}

	@Column(name = "obdAirTemperature")
	public Integer getObdAirTemperature() {
		return this.obdAirTemperature;
	}

	public void setObdAirTemperature(Integer obdAirTemperature) {
		this.obdAirTemperature = obdAirTemperature;
	}

	@Column(name = "obdAirPressure")
	public Integer getObdAirPressure() {
		return this.obdAirPressure;
	}

	public void setObdAirPressure(Integer obdAirPressure) {
		this.obdAirPressure = obdAirPressure;
	}

	@Column(name = "obdBattery", precision = 10, scale = 1)
	public Double getObdBattery() {
		return this.obdBattery;
	}

	public void setObdBattery(Double obdBattery) {
		this.obdBattery = obdBattery;
	}

	@Column(name = "obdAccident")
	public Integer getObdAccident() {
		return this.obdAccident;
	}

	public void setObdAccident(Integer obdAccident) {
		this.obdAccident = obdAccident;
	}

	@Column(name = "obdUsedOil")
	public Integer getObdUsedOil() {
		return this.obdUsedOil;
	}

	public void setObdUsedOil(Integer obdUsedOil) {
		this.obdUsedOil = obdUsedOil;
	}

	@Column(name = "obdDriverDuration")
	public Integer getObdDriverDuration() {
		return this.obdDriverDuration;
	}

	public void setObdDriverDuration(Integer obdDriverDuration) {
		this.obdDriverDuration = obdDriverDuration;
	}

}