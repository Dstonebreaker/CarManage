package com.car.entity.pgis;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * VObdCarGpsForobddb entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "v_obd_car_gps_forobddb")
public class VObdCarGpsForobddb implements java.io.Serializable {

	// Fields

	private String carId;
	private Double gpsLat;
	private Long gpsIndex;
	private Double gpsLon;
	private String ondNo;
	private Timestamp obdTime;

	// Constructors

	/** default constructor */
	public VObdCarGpsForobddb() {
	}

	/** minimal constructor */
	public VObdCarGpsForobddb(String carId, Long gpsIndex) {
		this.carId = carId;
		this.gpsIndex = gpsIndex;
	}

	/** full constructor */
	public VObdCarGpsForobddb(String carId, Double gpsLat, Long gpsIndex,
			Double gpsLon, String ondNo, Timestamp obdTime) {
		this.carId = carId;
		this.gpsLat = gpsLat;
		this.gpsIndex = gpsIndex;
		this.gpsLon = gpsLon;
		this.ondNo = ondNo;
		this.obdTime = obdTime;
	}

	// Property accessors
	@Id
	@Column(name = "carId", length = 36)
	public String getCarId() {
		return this.carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	@Column(name = "gpsLat", precision = 22, scale = 0)
	public Double getGpsLat() {
		return this.gpsLat;
	}

	public void setGpsLat(Double gpsLat) {
		this.gpsLat = gpsLat;
	}

	@Column(name = "gpsIndex", nullable = false)
	public Long getGpsIndex() {
		return this.gpsIndex;
	}

	public void setGpsIndex(Long gpsIndex) {
		this.gpsIndex = gpsIndex;
	}

	@Column(name = "gpsLon", precision = 22, scale = 0)
	public Double getGpsLon() {
		return this.gpsLon;
	}

	public void setGpsLon(Double gpsLon) {
		this.gpsLon = gpsLon;
	}

	@Column(name = "ondNo")
	public String getOndNo() {
		return this.ondNo;
	}

	public void setOndNo(String ondNo) {
		this.ondNo = ondNo;
	}

	@Column(name = "obdTime", length = 19)
	public Timestamp getObdTime() {
		return this.obdTime;
	}

	public void setObdTime(Timestamp obdTime) {
		this.obdTime = obdTime;
	}

}