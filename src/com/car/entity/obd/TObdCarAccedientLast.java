package com.car.entity.obd;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TObdCarAccedientLast entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_obd_car_accedient_last")
public class TObdCarAccedientLast implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8933285543530812068L;
	private String obdNo;
	private String acceNo;
	private String carId;
	private Timestamp obdTime;
	private Double obdGpsLongitude;
	private Double obdGpsLatitude;
	private String sendId;
	private Integer messAccedient;

	// Constructors

	/** default constructor */
	public TObdCarAccedientLast() {
	}

	@Id
	@Column(name = "obdNo", nullable = false, length = 50)
	public String getObdNo() {
		return this.obdNo;
	}

	public void setObdNo(String obdNo) {
		this.obdNo = obdNo;
	}

	@Id
	@Column(name = "acceNo", nullable = false, length = 50)
	public String getAcceNo() {
		return this.acceNo;
	}

	public void setAcceNo(String acceNo) {
		this.acceNo = acceNo;
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

	@Column(name = "sendId", length = 36)
	public String getSendId() {
		return this.sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	@Column(name = "messAccedient")
	public Integer getMessAccedient() {
		return this.messAccedient;
	}

	public void setMessAccedient(Integer messAccedient) {
		this.messAccedient = messAccedient;
	}

}