package com.car.entity.message;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * MsgAlarm entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "msg_alarm")
public class MsgAlarm implements java.io.Serializable {

	// Fields

	private String recordId;
	private String plateno;
	private String platecolor;
	private Date pictime;
	private String picip;
	private String devicename;
	private String picpath;
	private Date createtime;
	private String manageResult;
	private String flag;
	private Integer ctrlState;
	private String ctrlBehavior;
	private String ctrlClassifier;
	private String caseLeader;
	private String caseLeaderTel;
	private String smstext;

	// Constructors

	/** default constructor */
	public MsgAlarm() {
	}

	/** minimal constructor */
	public MsgAlarm(String recordId) {
		this.recordId = recordId;
	}

	/** full constructor */
	public MsgAlarm(String recordId, String plateno, String platecolor,
			Date pictime, String picip, String devicename, String picpath,
			Date createtime, String manageResult, String flag,
			Integer ctrlState, String ctrlBehavior, String ctrlClassifier,
			String caseLeader, String caseLeaderTel, String smstext) {
		this.recordId = recordId;
		this.plateno = plateno;
		this.platecolor = platecolor;
		this.pictime = pictime;
		this.picip = picip;
		this.devicename = devicename;
		this.picpath = picpath;
		this.createtime = createtime;
		this.manageResult = manageResult;
		this.flag = flag;
		this.ctrlState = ctrlState;
		this.ctrlBehavior = ctrlBehavior;
		this.ctrlClassifier = ctrlClassifier;
		this.caseLeader = caseLeader;
		this.caseLeaderTel = caseLeaderTel;
		this.smstext = smstext;
	}

	// Property accessors
	@Id
	@Column(name = "RECORD_ID", unique = true, nullable = false, length = 32)
	public String getRecordId() {
		return this.recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	@Column(name = "PLATENO", length = 20)
	public String getPlateno() {
		return this.plateno;
	}

	public void setPlateno(String plateno) {
		this.plateno = plateno;
	}

	@Column(name = "PLATECOLOR", length = 32)
	public String getPlatecolor() {
		return this.platecolor;
	}

	public void setPlatecolor(String platecolor) {
		this.platecolor = platecolor;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PICTIME", length = 10)
	public Date getPictime() {
		return this.pictime;
	}

	public void setPictime(Date pictime) {
		this.pictime = pictime;
	}

	@Column(name = "PICIP", length = 20)
	public String getPicip() {
		return this.picip;
	}

	public void setPicip(String picip) {
		this.picip = picip;
	}

	@Column(name = "DEVICENAME", length = 64)
	public String getDevicename() {
		return this.devicename;
	}

	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}

	@Column(name = "PICPATH", length = 512)
	public String getPicpath() {
		return this.picpath;
	}

	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATETIME", length = 10)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "MANAGE_RESULT", length = 20)
	public String getManageResult() {
		return this.manageResult;
	}

	public void setManageResult(String manageResult) {
		this.manageResult = manageResult;
	}

	@Column(name = "FLAG", length = 256)
	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "CTRL_STATE")
	public Integer getCtrlState() {
		return this.ctrlState;
	}

	public void setCtrlState(Integer ctrlState) {
		this.ctrlState = ctrlState;
	}

	@Column(name = "CTRL_BEHAVIOR", length = 16)
	public String getCtrlBehavior() {
		return this.ctrlBehavior;
	}

	public void setCtrlBehavior(String ctrlBehavior) {
		this.ctrlBehavior = ctrlBehavior;
	}

	@Column(name = "CTRL_CLASSIFIER", length = 16)
	public String getCtrlClassifier() {
		return this.ctrlClassifier;
	}

	public void setCtrlClassifier(String ctrlClassifier) {
		this.ctrlClassifier = ctrlClassifier;
	}

	@Column(name = "CASE_LEADER", length = 64)
	public String getCaseLeader() {
		return this.caseLeader;
	}

	public void setCaseLeader(String caseLeader) {
		this.caseLeader = caseLeader;
	}

	@Column(name = "CASE_LEADER_TEL", length = 64)
	public String getCaseLeaderTel() {
		return this.caseLeaderTel;
	}

	public void setCaseLeaderTel(String caseLeaderTel) {
		this.caseLeaderTel = caseLeaderTel;
	}

	@Column(name = "SMSTEXT", length = 512)
	public String getSmstext() {
		return this.smstext;
	}

	public void setSmstext(String smstext) {
		this.smstext = smstext;
	}

}