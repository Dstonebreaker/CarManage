package com.car.entity.car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TCarApplySendStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_car_apply_send_status")
public class TCarApplySendStatus implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3769783882898091195L;
	private String id; // 主键id
	private String sendId; // 派车单id
	private String applyId; // 申请单id
	private String returnId; // 还车单id
	private String dictIdApplySendStatus; // 单据状态

	// Constructors

	/** default constructor */
	public TCarApplySendStatus() {
	}

	/** minimal constructor */
	public TCarApplySendStatus(String id, String dictIdApplySendStatus) {
		this.id = id;
		this.dictIdApplySendStatus = dictIdApplySendStatus;
	}

	/** full constructor */
	public TCarApplySendStatus(String id, String sendId, String applyId, String returnId, String dictIdApplySendStatus) {
		this.id = id;
		this.sendId = sendId;
		this.applyId = applyId;
		this.returnId = returnId;
		this.dictIdApplySendStatus = dictIdApplySendStatus;
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

	@Column(name = "sendId", length = 36)
	public String getSendId() {
		return this.sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	@Column(name = "applyId", length = 36)
	public String getApplyId() {
		return this.applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	@Column(name = "returnId", length = 36)
	public String getReturnId() {
		return this.returnId;
	}

	public void setReturnId(String returnId) {
		this.returnId = returnId;
	}

	@Column(name = "dictIdApplySendStatus", nullable = false, length = 36)
	public String getDictIdApplySendStatus() {
		return this.dictIdApplySendStatus;
	}

	public void setDictIdApplySendStatus(String dictIdApplySendStatus) {
		this.dictIdApplySendStatus = dictIdApplySendStatus;
	}

}