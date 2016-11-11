package com.car.entity.car;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="t_car_transform")
public class TCarTransform  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5285807799857520404L;

	private String transformId;//ID
	private String transformNo;//车改信息编号
	private String carId;//车辆编号
	private String carNo;//车牌号
	private String transformBeforeOrgId;//车改前单位
	private String transformAfterOrgId;//车改后单位
	private String dictIdDisposalType;//处置方式
	private String dictIdHandleType;//处理方式
	private String dictIdFlag;//标识
	private String userIdCreate;//登记人id
	private Date timeCreate;//登记时间
	private String userIdUpdate;//修改人ID
	private Date timeUpdate;//修改时间
	
	@Id
	@Column(name = "transformId", unique = true, nullable = false, length = 36)
	public String getTransformId() {
		return transformId;
	}
	public void setTransformId(String transformId) {
		this.transformId = transformId;
	}
	@Column(name = "transformNo", nullable = false, length = 36)
	public String getTransformNo() {
		return transformNo;
	}
	public void setTransformNo(String transformNo) {
		this.transformNo = transformNo;
	}
	@Column(name = "carId", nullable = false, length = 36)
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	@Column(name = "carNo", nullable = false, length = 36)
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	@Column(name = "transformBeforeOrgId", nullable = false, length = 36)
	public String getTransformBeforeOrgId() {
		return transformBeforeOrgId;
	}
	public void setTransformBeforeOrgId(String transformBeforeOrgId) {
		this.transformBeforeOrgId = transformBeforeOrgId;
	}
	@Column(name = "transformAfterOrgId", nullable = false, length = 36)
	public String getTransformAfterOrgId() {
		return transformAfterOrgId;
	}
	public void setTransformAfterOrgId(String transformAfterOrgId) {
		this.transformAfterOrgId = transformAfterOrgId;
	}
	@Column(name = "dictIdDisposalType", nullable = false, length = 36)
	public String getDictIdDisposalType() {
		return dictIdDisposalType;
	}
	public void setDictIdDisposalType(String dictIdDisposalType) {
		this.dictIdDisposalType = dictIdDisposalType;
	}
	@Column(name = "dictIdHandleType", nullable = false, length = 36)
	public String getDictIdHandleType() {
		return dictIdHandleType;
	}
	public void setDictIdHandleType(String dictIdHandleType) {
		this.dictIdHandleType = dictIdHandleType;
	}
	@Column(name = "dictIdFlag", nullable = false, length = 36)
	public String getDictIdFlag() {
		return dictIdFlag;
	}
	public void setDictIdFlag(String dictIdFlag) {
		this.dictIdFlag = dictIdFlag;
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
	@Column(name = "userIdUpdate", length = 36)
	public String getUserIdUpdate() {
		return userIdUpdate;
	}
	public void setUserIdUpdate(String userIdUpdate) {
		this.userIdUpdate = userIdUpdate;
	}
	@Column(name = "timeUpdate",  length = 36)
	public Date getTimeUpdate() {
		return timeUpdate;
	}
	public void setTimeUpdate(Date timeUpdate) {
		this.timeUpdate = timeUpdate;
	}
	
	
	
	
}
