package com.car.entity.car;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_car_model")
public class TCarModel implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5374861087435772137L;
	private String modelId;
	private String dictIdBrand;//车辆品牌
	private String dictIdCarSeries;//车系
	private String modelName;//车型
	private String dictIdProductionArea;//产地
	private String dictIdUseOilType;//燃油类型
	private String dictIdIsT;//是否带 T
	private Integer modelPeople;//载客量（人）
	private Double modelLoad;//载重量（吨）
	private Double modelAirDisplacement;//排气量（升）
	private String modelMemo;//备注
	private String dictIdFlag;//标识
	private String userIdCreate;//登记人id
	private Date timeCreate;//登记时间
	private String userIdUpdate;//修改人id
	private Date timeUpdate;//修改时间
	private Integer modelMaintenanceMonth;//保养周期
	
	
	@Id
	@Column(name="modelId",unique = true, nullable = false, length = 36)
	public String getModelId() {
		return modelId;
	}
	
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	@Column(name="dictIdBrand",nullable = false, length = 36)
	public String getDictIdBrand() {
		return dictIdBrand;
	}
	public void setDictIdBrand(String dictIdBrand) {
		this.dictIdBrand = dictIdBrand;
	}
	@Column(name="dictIdCarSeries", nullable = false, length = 36)
	public String getDictIdCarSeries() {
		return dictIdCarSeries;
	}
	public void setDictIdCarSeries(String dictIdCarSeries) {
		this.dictIdCarSeries = dictIdCarSeries;
	}
	@Column(name="modelName", nullable = false, length = 36)
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	@Column(name="dictIdProductionArea", nullable = false, length = 36)
	public String getDictIdProductionArea() {
		return dictIdProductionArea;
	}
	public void setDictIdProductionArea(String dictIdProductionArea) {
		this.dictIdProductionArea = dictIdProductionArea;
	}
	@Column(name="dictIdUseOilType",nullable = false, length = 36)
	public String getDictIdUseOilType() {
		return dictIdUseOilType;
	}
	public void setDictIdUseOilType(String dictIdUseOilType) {
		this.dictIdUseOilType = dictIdUseOilType;
	}
	@Column(name="dictIdIsT", nullable = false, length = 36)
	public String getDictIdIsT() {
		return dictIdIsT;
	}
	public void setDictIdIsT(String dictIdIsT) {
		this.dictIdIsT = dictIdIsT;
	}
	@Column(name="modelPeople",nullable = false, length = 11)
	public Integer getModelPeople() {
		return modelPeople;
	}
	public void setModelPeople(Integer modelPeople) {
		this.modelPeople = modelPeople;
	}
	@Column(name="modelLoad", nullable = false)
	public Double getModelLoad() {
		return modelLoad;
	}
	public void setModelLoad(Double modelLoad) {
		this.modelLoad = modelLoad;
	}
	@Column(name="modelAirDisplacement", nullable = false)
	public Double getModelAirDisplacement() {
		return modelAirDisplacement;
	}
	public void setModelAirDisplacement(Double modelAirDisplacement) {
		this.modelAirDisplacement = modelAirDisplacement;
	}
	@Column(name="modelMemo",length = 400)
	public String getModelMemo() {
		return modelMemo;
	}
	public void setModelMemo(String modelMemo) {
		this.modelMemo = modelMemo;
	}
	@Column(name="dictIdFlag", nullable = false,length = 36)
	public String getDictIdFlag() {
		return dictIdFlag;
	}
	public void setDictIdFlag(String dictIdFlag) {
		this.dictIdFlag = dictIdFlag;
	}
	@Column(name="userIdCreate", nullable = false,length = 36)
	public String getUserIdCreate() {
		return userIdCreate;
	}
	public void setUserIdCreate(String userIdCreate) {
		this.userIdCreate = userIdCreate;
	}
	@Column(name="timeCreate", nullable = false)
	public Date getTimeCreate() {
		return timeCreate;
	}
	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}
	@Column(name="userIdUpdate", nullable = true)
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
	@Column(name="modelMaintenanceMonth", nullable =false,length=11)
	public Integer getModelMaintenanceMonth() {
		return modelMaintenanceMonth;
	}

	public void setModelMaintenanceMonth(Integer modelMaintenanceMonth) {
		this.modelMaintenanceMonth = modelMaintenanceMonth;
	}
	
	
}
