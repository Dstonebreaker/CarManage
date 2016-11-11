package com.car.entity.obd;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TObdCarFaultCode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_obd_car_fault_code")
public class TObdCarFaultCode implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8742513098435794992L;
	private String faultCode;
	private String faultNameChinese;
	private Integer faultIsSend;
	private String userIdUpdate;
	private Timestamp timeUpdate;

	// Constructors

	/** default constructor */
	public TObdCarFaultCode() {
	}

	/** minimal constructor */
	public TObdCarFaultCode(String faultCode, Integer faultIsSend) {
		this.faultCode = faultCode;
		this.faultIsSend = faultIsSend;
	}

	/** full constructor */
	public TObdCarFaultCode(String faultCode, String faultNameChinese, Integer faultIsSend, String userIdUpdate, Timestamp timeUpdate) {
		this.faultCode = faultCode;
		this.faultNameChinese = faultNameChinese;
		this.faultIsSend = faultIsSend;
		this.userIdUpdate = userIdUpdate;
		this.timeUpdate = timeUpdate;
	}

	// Property accessors
	@Id
	@Column(name = "faultCode", unique = true, nullable = false, length = 50)
	public String getFaultCode() {
		return this.faultCode;
	}

	public void setFaultCode(String faultCode) {
		this.faultCode = faultCode;
	}

	@Column(name = "faultNameChinese", length = 50)
	public String getFaultNameChinese() {
		return this.faultNameChinese;
	}

	public void setFaultNameChinese(String faultNameChinese) {
		this.faultNameChinese = faultNameChinese;
	}

	@Column(name = "faultIsSend", nullable = false)
	public Integer getFaultIsSend() {
		return this.faultIsSend;
	}

	public void setFaultIsSend(Integer faultIsSend) {
		this.faultIsSend = faultIsSend;
	}

	@Column(name = "userIdUpdate", length = 36)
	public String getUserIdUpdate() {
		return this.userIdUpdate;
	}

	public void setUserIdUpdate(String userIdUpdate) {
		this.userIdUpdate = userIdUpdate;
	}

	@Column(name = "timeUpdate", length = 19)
	public Timestamp getTimeUpdate() {
		return this.timeUpdate;
	}

	public void setTimeUpdate(Timestamp timeUpdate) {
		this.timeUpdate = timeUpdate;
	}

}