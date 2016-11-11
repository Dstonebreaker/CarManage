package com.system.entity.maintain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "t_sys_setting", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysSetting implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7901527406409339594L;
    private String settId;
    private String settName;
    private Integer settValue;
    private String settUnit;
    private String userIdUpdate;
    private Date timeUpdate;
    
	public SysSetting() {
		super();
	}
	public SysSetting(String settId, String settName, Integer settValue,
			String settUnit, String userIdUpdate, Date timeUpdate) {
		super();
		this.settId = settId;
		this.settName = settName;
		this.settValue = settValue;
		this.settUnit = settUnit;
		this.userIdUpdate = userIdUpdate;
		this.timeUpdate = timeUpdate;
	}
	@Id
	@Column(name = "settId", unique = true, nullable = false, length = 36)
	public String getSettId() {
		return settId;
	}
	public void setSettId(String settId) {
		this.settId = settId;
	}
	@Column(name = "settName",  nullable = false, length = 50)
	public String getSettName() {
		return settName;
	}
	public void setSettName(String settName) {
		this.settName = settName;
	}
	@Column(name = "settValue",  nullable = false, length = 50)
	public Integer getSettValue() {
		return settValue;
	}
	public void setSettValue(Integer settValue) {
		this.settValue = settValue;
	}
	@Column(name = "settUnit",  nullable = false, length = 50)
	public String getSettUnit() {
		return settUnit;
	}
	public void setSettUnit(String settUnit) {
		this.settUnit = settUnit;
	}
	@Column(name = "userIdUpdate",   length = 50)
	public String getUserIdUpdate() {
		return userIdUpdate;
	}
	public void setUserIdUpdate(String userIdUpdate) {
		this.userIdUpdate = userIdUpdate;
	}
	@Column(name = "timeUpdate",   length = 50)
	public Date getTimeUpdate() {
		return timeUpdate;
	}
	public void setTimeUpdate(Date timeUpdate) {
		this.timeUpdate = timeUpdate;
	}
	
	
    
}
