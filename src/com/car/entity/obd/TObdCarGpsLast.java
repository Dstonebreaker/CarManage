package com.car.entity.obd;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TObdCarGpsLast entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_obd_car_gps_last")
public class TObdCarGpsLast implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3045166832569834528L;
	private String obdNo;
	private String carId;
	private String obdDataType;
	private Timestamp obdTime;
	private Double obdGpsLongitude;
	private Double obdGpsLatitude;
	private Integer messFence;
	private Integer isOutRegion;
	private String orgId;
	private String sendIdPrevious;
	private String sendIdLast;
	private Timestamp messTime;

	// Constructors

	/** default constructor */
	public TObdCarGpsLast() {
	}

	/** minimal constructor */
	public TObdCarGpsLast(String obdNo) {
		this.obdNo = obdNo;
	}

	/** full constructor */
	public TObdCarGpsLast(String obdNo, String carId, String obdDataType, Timestamp obdTime, Double obdGpsLongitude, Double obdGpsLatitude,
			Integer messFence, Integer isOutRegion, String orgId, String sendIdPrevious, String sendIdLast, Timestamp messTime) {
		this.obdNo = obdNo;
		this.carId = carId;
		this.obdDataType = obdDataType;
		this.obdTime = obdTime;
		this.obdGpsLongitude = obdGpsLongitude;
		this.obdGpsLatitude = obdGpsLatitude;
		this.messFence = messFence;
		this.isOutRegion = isOutRegion;
		this.orgId = orgId;
		this.sendIdPrevious = sendIdPrevious;
		this.sendIdLast = sendIdLast;
		this.messTime = messTime;
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

	@Column(name = "obdDataType", length = 50)
	public String getObdDataType() {
		return this.obdDataType;
	}

	public void setObdDataType(String obdDataType) {
		this.obdDataType = obdDataType;
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

	@Column(name = "messFence")
	public Integer getMessFence() {
		return this.messFence;
	}

	public void setMessFence(Integer messFence) {
		this.messFence = messFence;
	}

	@Column(name = "isOutRegion")
	public Integer getIsOutRegion() {
		return this.isOutRegion;
	}

	public void setIsOutRegion(Integer isOutRegion) {
		this.isOutRegion = isOutRegion;
	}

	@Column(name = "orgId", length = 36)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "sendIdPrevious", length = 36)
	public String getSendIdPrevious() {
		return this.sendIdPrevious;
	}

	public void setSendIdPrevious(String sendIdPrevious) {
		this.sendIdPrevious = sendIdPrevious;
	}

	@Column(name = "sendIdLast", length = 36)
	public String getSendIdLast() {
		return this.sendIdLast;
	}

	public void setSendIdLast(String sendIdLast) {
		this.sendIdLast = sendIdLast;
	}

	@Column(name = "messTime", length = 19)
	public Timestamp getMessTime() {
		return this.messTime;
	}

	public void setMessTime(Timestamp messTime) {
		this.messTime = messTime;
	}

}