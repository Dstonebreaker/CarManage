package com.car.entity.car;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name="t_car_model_maintenance")
public class TCarModelMaintenance implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2772668793096166528L;
	
	private String mmainId;
	private String modelId;
	private String mmainInfo;
	private Integer mmainMileage;
	private String dictIdFlag;
	private String userIdCreate;
	private Date  timeCreate;
	private String userIdUpdate;
	private Date  timeUpdate;
	
	public TCarModelMaintenance() {
		super();
	}

	
	public TCarModelMaintenance(String mmainId, String modelId,
			String mmainInfo, Integer mmainMileage, String dictIdFlag,
			String userIdCreate, Date timeCreate, String userIdUpdate,
			Date timeUpdate) {
		this.mmainId = mmainId;
		this.modelId = modelId;
		this.mmainInfo = mmainInfo;
		this.mmainMileage = mmainMileage;
		this.dictIdFlag = dictIdFlag;
		this.userIdCreate = userIdCreate;
		this.timeCreate = timeCreate;
		this.userIdUpdate = userIdUpdate;
		this.timeUpdate = timeUpdate;
	}


	@Id
	@Column(name="mmainId",unique = true, nullable = false, length = 36)
	public String getMmainId() {
		if (!StringUtils.isBlank(this.mmainId)) {
			return this.mmainId;
		}
		return UUID.randomUUID().toString();
	}
	public void setMmainId(String mmainId) {
		this.mmainId = mmainId;
	}
	@Column(name="modelId",nullable = false, length = 36)
	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	
	@Column(name="mmainMileage",nullable = false, length = 10)
	public Integer getMmainMileage() {
		return mmainMileage;
	}

	public void setMmainMileage(Integer mmainMileage) {
		this.mmainMileage = mmainMileage;
	}
	@Column(name="mmainInfo",nullable = false, length = 10)
	public String getMmainInfo() {
		return mmainInfo;
	}
	public void setMmainInfo(String mmainInfo) {
		this.mmainInfo = mmainInfo;
	}
	@Column(name="dictIdFlag",nullable = false, length = 10)
	public String getDictIdFlag() {
		return dictIdFlag;
	}
	public void setDictIdFlag(String dictIdFlag) {
		this.dictIdFlag = dictIdFlag;
	}
	@Column(name="userIdCreate",nullable = false, length = 36)
	public String getUserIdCreate() {
		return userIdCreate;
	}

	public void setUserIdCreate(String userIdCreate) {
		this.userIdCreate = userIdCreate;
	}
	@Column(name="timeCreate", nullable =true)
	public Date getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}
	@Column(name="userIdUpdate", length = 36)
	public String getUserIdUpdate() {
		return userIdUpdate;
	}

	public void setUserIdUpdate(String userIdUpdate) {
		this.userIdUpdate = userIdUpdate;
	}
	@Column(name="timeUpdate", nullable =true)
	public Date getTimeUpdate() {
		return timeUpdate;
	}

	public void setTimeUpdate(Date timeUpdate) {
		this.timeUpdate = timeUpdate;
	}
	  
	

}
