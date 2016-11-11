package com.car.entity.car;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TCarReturn entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_car_return")
public class TCarReturn implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 556591358938533493L;
	private String returnId;  // 主键
	private Date returnTime; //还车时间
	private Double returnMileage; // 还车里程
	private String returnHandlerPicture; // 还车人照片
	private String returnFile; // 还车文件
	private String returnMemo; // 还车备注
	private Double returnOil; // 还车油量
	private String sendId; // 派车单id
	private String dictIdFlag; // 标记
	private String userIdCreate; // 登记人
	private Date timeCreate; // 登记使时间
	private String userIdUpdate;
	private Date timeUpdate;

	// Constructors

	/** default constructor */
	public TCarReturn() {
	}

	/** minimal constructor */
	public TCarReturn(String returnId, Date returnTime, Double returnMileage, Double returnOil, String sendId, String dictIdFlag,
			String userIdCreate, Date timeCreate) {
		this.returnId = returnId;
		this.returnTime = returnTime;
		this.returnMileage = returnMileage;
		this.returnOil = returnOil;
		this.sendId = sendId;
		this.dictIdFlag = dictIdFlag;
		this.userIdCreate = userIdCreate;
		this.timeCreate = timeCreate;
	}

	/** full constructor */
	public TCarReturn(String returnId, Date returnTime, Double returnMileage, String returnHandlerPicture, String returnFile,
			String returnMemo, Double returnOil, String sendId, String dictIdFlag, String userIdCreate, Date timeCreate,
			String userIdUpdate, Date timeUpdate) {
		this.returnId = returnId;
		this.returnTime = returnTime;
		this.returnMileage = returnMileage;
		this.returnHandlerPicture = returnHandlerPicture;
		this.returnFile = returnFile;
		this.returnMemo = returnMemo;
		this.returnOil = returnOil;
		this.sendId = sendId;
		this.dictIdFlag = dictIdFlag;
		this.userIdCreate = userIdCreate;
		this.timeCreate = timeCreate;
		this.userIdUpdate = userIdUpdate;
		this.timeUpdate = timeUpdate;
	}

	// Property accessors
	@Id
	@Column(name = "returnId", unique = true, nullable = false, length = 36)
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

	@Column(name = "returnOil", nullable = false, precision = 10)
	public Double getReturnOil() {
		return this.returnOil;
	}

	public void setReturnOil(Double returnOil) {
		this.returnOil = returnOil;
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

}