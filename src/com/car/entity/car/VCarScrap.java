package com.car.entity.car;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;


@Entity
@Table(name="v_car_scrap")
public class VCarScrap implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3723923200115159916L;
	private String scrapId;
    private String scrapNo;
    private String carId;
    private String scrapUsedYear;
    private String dictIdScrapResult;
    private String scrapUserId;
    private Date   scrapTime;
    private String userIdCreate;
    private Date   timeCreate;
    private String carNo;
    private String orgName;
    private String scrapName;
    private String dictName;
    private String orgIdManager;
    private String dictIdFlag;
    public VCarScrap() {
		super();
	}
	@Id
	@Column(name = "scrapId", unique = true, nullable = false, length = 36)  
	public String getScrapId() {
		if (!StringUtils.isBlank(this.scrapId)) {
			return this.scrapId;
		}
		return UUID.randomUUID().toString();
	}
	public void setScrapId(String scrapId) {
		this.scrapId = scrapId;
	}
	@Column(name = "scrapNo", nullable = false, length = 36)
	public String getScrapNo() {
		return scrapNo;
	}
	public void setScrapNo(String scrapNo) {
		this.scrapNo = scrapNo;
	}
	@Column(name = "carId", nullable = false, length = 36)
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	@Column(name = "scrapUsedYear", length = 36)
	public String getScrapUsedYear() {
		return scrapUsedYear;
	}
	public void setScrapUsedYear(String scrapUsedYear) {
		this.scrapUsedYear = scrapUsedYear;
	}
	@Column(name = "dictIdScrapResult", length = 36)
	public String getDictIdScrapResult() {
		return dictIdScrapResult;
	}
	public void setDictIdScrapResult(String dictIdScrapResult) {
		this.dictIdScrapResult = dictIdScrapResult;
	}
	@Column(name = "scrapUserId", length = 36)
	public String getScrapUserId() {
		return scrapUserId;
	}
	public void setScrapUserId(String scrapUserId) {
		this.scrapUserId = scrapUserId;
	}
	@Column(name = "scrapTime", length = 36)
	public Date getScrapTime() {
		return scrapTime;
	}
	public void setScrapTime(Date scrapTime) {
		this.scrapTime = scrapTime;
	}
	@Column(name = "userIdCreate", nullable = false, length = 36)
	public String getUserIdCreate() {
		return userIdCreate;
	}
	public void setUserIdCreate(String userIdCreate) {
		this.userIdCreate = userIdCreate;
	}
	@Column(name = "timeCreate", nullable = false, length = 36)
	public Date getTimeCreate() {
		return timeCreate;
	}
	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}
	@Column(name = "carNo", nullable = false, length = 36)
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	@Column(name = "orgName", nullable = false, length = 36)
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	@Column(name = "scrapName", nullable = false, length = 36)
	public String getScrapName() {
		return scrapName;
	}
	public void setScrapName(String scrapName) {
		this.scrapName = scrapName;
	}
	@Column(name = "dictName", nullable = false, length = 36)
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	@Column(name = "orgIdManager", nullable = false, length = 36)
	public String getOrgIdManager() {
		return orgIdManager;
	}
	public void setOrgIdManager(String orgIdManager) {
		this.orgIdManager = orgIdManager;
	}
	@Column(name = "dictIdFlag", nullable = false, length = 36)
	public String getDictIdFlag() {
		return dictIdFlag;
	}
	public void setDictIdFlag(String dictIdFlag) {
		this.dictIdFlag = dictIdFlag;
	}
}
