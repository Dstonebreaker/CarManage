package com.system.entity.maintain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "v_sys_log")
@DynamicInsert(true)
@DynamicUpdate(true)
public class VsysLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6223241975142749074L;
	private String logId; // 日志id
	private String logIp; // ip地址
	private String userId; // 操作用户id
	private String logOperateDescription; // 操作描述
	private String logOperateFunction; // 操作功能或者方法
	private Date logOperateTime; // 操作时间
	private String userName;//操作用户


	@Id
	@Column(name = "logId", unique = true, columnDefinition="nvarchar(36)", nullable = false)
	public String getLogId() {
		if (!StringUtils.isBlank(this.logId)) {
			return this.logId;
		}
		return UUID.randomUUID().toString();
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	@Column(name = "logIp", columnDefinition="nvarchar(36)")
	public String getLogIp() {
		return logIp;
	}

	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}

	@Column(name = "userId", columnDefinition="nvarchar(36)")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "logOperateDescription", columnDefinition="nvarchar(400)")
	public String getLogOperateDescription() {
		return logOperateDescription;
	}

	public void setLogOperateDescription(String logOperateDescription) {
		this.logOperateDescription = logOperateDescription;
	}

	@Column(name = "logOperateFunction", columnDefinition="nvarchar(64)")
	public String getLogOperateFunction() {
		return logOperateFunction;
	}

	public void setLogOperateFunction(String logOperateFunction) {
		this.logOperateFunction = logOperateFunction;
	}

	@Column(name = "logOperateTime", length = 23)
	public Date getLogOperateTime() {
		return logOperateTime;
	}

	public void setLogOperateTime(Date logOperateTime) {
		this.logOperateTime = logOperateTime;
	}
	@Column(name = "userName", columnDefinition="nvarchar(36)")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
