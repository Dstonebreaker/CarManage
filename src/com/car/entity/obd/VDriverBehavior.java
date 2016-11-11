package com.car.entity.obd;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * VDriverBehaviorId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="v_driver_behavior")
public class VDriverBehavior implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7239848863116209330L;
	private String behaviorId;
	private String obdTime;
	private BigDecimal  distance;
	private Integer violent;//急加速
	private Integer adrupt;//急减速
	private Integer turn;//急转弯
	private Integer overSpeed;//超速
	private Integer tired;//疲劳
	private String driverId;

	// Property accessors

	
	@Id
	public String getBehaviorId() {
		return behaviorId;
	}

	public void setBehaviorId(String behaviorId) {
		this.behaviorId = behaviorId;
	}
	
	@Column(name = "obdTime", length = 10)
	public String getObdTime() {
		return this.obdTime;
	}
	public void setObdTime(String obdTime) {
		this.obdTime = obdTime;
	}

	@Column(name = "distance", precision = 32, scale = 0)
	public BigDecimal getDistance() {
		return this.distance;
	}

	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}

	@Column(name = "violent")
	public Integer getViolent() {
		return this.violent;
	}

	public void setViolent(Integer violent) {
		this.violent = violent;
	}

	@Column(name = "adrupt")
	public Integer getAdrupt() {
		return this.adrupt;
	}

	public void setAdrupt(Integer adrupt) {
		this.adrupt = adrupt;
	}

	@Column(name = "turn")
	public Integer getTurn() {
		return this.turn;
	}

	public void setTurn(Integer turn) {
		this.turn = turn;
	}

	@Column(name = "overSpeed")
	public Integer getOverSpeed() {
		return this.overSpeed;
	}

	public void setOverSpeed(Integer overSpeed) {
		this.overSpeed = overSpeed;
	}

	@Column(name = "tired")
	public Integer getTired() {
		return this.tired;
	}

	public void setTired(Integer tired) {
		this.tired = tired;
	}

	@Column(name = "driverId", length = 36)
	public String getDriverId() {
		return this.driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

}