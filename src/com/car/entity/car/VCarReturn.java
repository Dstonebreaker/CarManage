package com.car.entity.car;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * VCarReturn entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "v_car_return")
public class VCarReturn implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7269574023325061803L;
	private String returnId;
	private Date returnTime;
	private Double returnMileage;
	private Double returnOil;
	private String returnHandlerPicture; // 还车人照片
	private String returnFile; // 还车文件
	private String returnMemo; // 还车备注
	private String sendId;
	private String dictIdFlag;
	private String userIdCreate;
	private Date timeCreate;
	private String userIdUpdate;
	private Date timeUpdate;
	private String userNameCreate;
	private String userNameUpdate;

	// Constructors

	/** default constructor */
	public VCarReturn() {
	}

	/** minimal constructor */
	public VCarReturn(String returnId, Date returnTime, Double returnMileage, Double returnOil, String sendId, String dictIdFlag,
			String userIdCreate, Date timeCreate, String userNameCreate, String userNameUpdate) {
		this.returnId = returnId;
		this.returnTime = returnTime;
		this.returnMileage = returnMileage;
		this.returnOil = returnOil;
		this.sendId = sendId;
		this.dictIdFlag = dictIdFlag;
		this.userIdCreate = userIdCreate;
		this.timeCreate = timeCreate;
		this.userNameCreate = userNameCreate;
		this.userNameUpdate = userNameUpdate;
	}

	/** full constructor */
	public VCarReturn(String returnId, Date returnTime, Double returnMileage, Double returnOil, String sendId, String dictIdFlag,
			String userIdCreate, Date timeCreate, String userIdUpdate, Date timeUpdate, String userNameCreate,
			String userNameUpdate) {
		this.returnId = returnId;
		this.returnTime = returnTime;
		this.returnMileage = returnMileage;
		this.returnOil = returnOil;
		this.sendId = sendId;
		this.dictIdFlag = dictIdFlag;
		this.userIdCreate = userIdCreate;
		this.timeCreate = timeCreate;
		this.userIdUpdate = userIdUpdate;
		this.timeUpdate = timeUpdate;
		this.userNameCreate = userNameCreate;
		this.userNameUpdate = userNameUpdate;
	}

	// Property accessors
	@Id
	@Column(name = "returnId", nullable = false, length = 36)
	public String getReturnId() {
		return this.returnId;
	}

	public void setReturnId(String returnId) {
		this.returnId = returnId;
	}

	@Column(name = "returnTime", nullable = false, length = 19)
	public Date getReturnTime() {
		return this.returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	@Column(name = "returnMileage", nullable = false, precision = 10)
	public Double getReturnMileage() {
		return this.returnMileage;
	}

	public void setReturnMileage(Double returnMileage) {
		this.returnMileage = returnMileage;
	}

	@Column(name = "returnOil", nullable = false, precision = 10)
	public Double getReturnOil() {
		return this.returnOil;
	}

	public void setReturnOil(Double returnOil) {
		this.returnOil = returnOil;
	}

	@Column(name = "returnHandlerPicture", length = 400)
	public String getReturnHandlerPicture() {
		return this.returnHandlerPicture;
	}

	public void setReturnHandlerPicture(String returnHandlerPicture) {
		this.returnHandlerPicture = returnHandlerPicture;
	}

	@Column(name = "returnFile", length = 400)
	public String getReturnFile() {
		return this.returnFile;
	}

	public void setReturnFile(String returnFile) {
		this.returnFile = returnFile;
	}

	@Column(name = "returnMemo", length = 400)
	public String getReturnMemo() {
		return this.returnMemo;
	}

	public void setReturnMemo(String returnMemo) {
		this.returnMemo = returnMemo;
	}

	@Column(name = "sendId", nullable = false, length = 36)
	public String getSendId() {
		return this.sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	@Column(name = "dictIdFlag", nullable = false, length = 36)
	public String getDictIdFlag() {
		return this.dictIdFlag;
	}

	public void setDictIdFlag(String dictIdFlag) {
		this.dictIdFlag = dictIdFlag;
	}

	@Column(name = "userIdCreate", nullable = false, length = 36)
	public String getUserIdCreate() {
		return this.userIdCreate;
	}

	public void setUserIdCreate(String userIdCreate) {
		this.userIdCreate = userIdCreate;
	}

	@Column(name = "timeCreate", nullable = false, length = 19)
	public Date getTimeCreate() {
		return this.timeCreate;
	}

	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}

	@Column(name = "userIdUpdate", length = 36)
	public String getUserIdUpdate() {
		return this.userIdUpdate;
	}

	public void setUserIdUpdate(String userIdUpdate) {
		this.userIdUpdate = userIdUpdate;
	}

	@Column(name = "timeUpdate", length = 19)
	public Date getTimeUpdate() {
		return this.timeUpdate;
	}

	public void setTimeUpdate(Date timeUpdate) {
		this.timeUpdate = timeUpdate;
	}

	@Column(name = "userNameCreate", nullable = false, length = 50)
	public String getUserNameCreate() {
		return this.userNameCreate;
	}

	public void setUserNameCreate(String userNameCreate) {
		this.userNameCreate = userNameCreate;
	}

	@Column(name = "userNameUpdate", nullable = false, length = 50)
	public String getUserNameUpdate() {
		return this.userNameUpdate;
	}

	public void setUserNameUpdate(String userNameUpdate) {
		this.userNameUpdate = userNameUpdate;
	}

}