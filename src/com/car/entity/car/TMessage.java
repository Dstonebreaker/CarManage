package com.car.entity.car;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TMessage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_message")
public class TMessage implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5647623589900036374L;
	// Fields

	private String messId;
	private Integer messType;// 消息类型 1.发送；2.接收
	private String dictIdMessageKind;// 消息业务类型
	private String businessId;// 业务id
	private String messInfo;// 短信内容
	private String messPhones;// 发送手机号（以；分割）
	private Date messTime;// 保存时间
	private Date messDealTime;// 处理时间
	private Integer messStatus;// 处理状态 1.待处理；2.已处理
	private String messKey;//

	@Id
	@Column(name = "messId", unique = true, nullable = false, length = 36)
	public String getMessId() {
		return messId;
	}

	public void setMessId(String messId) {
		this.messId = messId;
	}

	@Column(name = "messType", nullable = false, length = 11)
	public Integer getMessType() {
		return messType;
	}

	public void setMessType(Integer messType) {
		this.messType = messType;
	}

	@Column(name = "dictIdMessageKind", length = 36)
	public String getDictIdMessageKind() {
		return dictIdMessageKind;
	}

	public void setDictIdMessageKind(String dictIdMessageKind) {
		this.dictIdMessageKind = dictIdMessageKind;
	}

	@Column(name = "businessId", length = 36)
	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	@Column(name = "messInfo", nullable = false, length = 400)
	public String getMessInfo() {
		return messInfo;
	}

	public void setMessInfo(String messInfo) {
		this.messInfo = messInfo;
	}

	@Column(name = "messPhones", nullable = false, length = 400)
	public String getMessPhones() {
		return messPhones;
	}

	public void setMessPhones(String messPhones) {
		this.messPhones = messPhones;
	}

	@Column(name = "messTime", nullable = false, length = 19)
	public Date getMessTime() {
		return messTime;
	}

	public void setMessTime(Date messTime) {
		this.messTime = messTime;
	}

	@Column(name = "messDealTime", length = 19)
	public Date getMessDealTime() {
		return messDealTime;
	}

	public void setMessDealTime(Date messDealTime) {
		this.messDealTime = messDealTime;
	}

	@Column(name = "messStatus", nullable = false, length = 11)
	public Integer getMessStatus() {
		return messStatus;
	}

	public void setMessStatus(Integer messStatus) {
		this.messStatus = messStatus;
	}

	@Column(name = "messKey", length = 36)
	public String getMessKey() {
		return messKey;
	}

	public void setMessKey(String messKey) {
		this.messKey = messKey;
	}

}