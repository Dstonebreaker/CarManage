package com.car.entity.car;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "v_obd_car_info_last")
public class VObdCarInfoLast implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4992762041164619186L;
	
	private String obdNo;//硬件编号
	private String carId;//carId
	private Date obdTime;//上报时间
	private Double obdOil;//油量
	private Integer obdMileage;//里程
	private Integer obdSpeed;//车速
	private Integer obdCoolant;//冷却液温度
	private Double obdAirDamper;//节气门开度 
	private Double obdEngine;//发动机负荷
	private Integer obdAirFlow;//进气流量
	private Integer obdAirTemperature;//进气温度
	private String obdAirPressure;//进气压力
	private Double obdBattery ;//电瓶电压
	private Integer obdAccident;//故障码个数
	private Integer obdUsedOil;//历史总油耗
	private Integer obdDriverDuration;//历史总驾驶时长
	private String  orgIdManager;//车辆管理单位id
	private String orgName;//单位名称
	private String carNo;//车牌号
	private String carIdentifyNo;//车架号
			
	
	@Id
	@Column(name = "obdNo", unique = true, nullable = false, length = 50)
	public String getObdNo() {
		return obdNo;
	}
	public void setObdNo(String obdNo) {
		this.obdNo = obdNo;
	}
	@Column(name = "carId", length = 36)
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	@Column(name = "obdTime", length = 20)
	public Date getObdTime() {
		return obdTime;
	}
	public void setObdTime(Date obdTime) {
		this.obdTime = obdTime;
	}
	@Column(name = "obdOil", length = 10)
	public Double getObdOil() {
		return obdOil;
	}
	public void setObdOil(Double obdOil) {
		this.obdOil = obdOil;
	}
	@Column(name = "obdMileage", length = 11)
	public Integer getObdMileage() {
		return obdMileage;
	}
	public void setObdMileage(Integer obdMileage) {
		this.obdMileage = obdMileage;
	}
	@Column(name = "obdSpeed", length = 11)
	public Integer getObdSpeed() {
		return obdSpeed;
	}
	public void setObdSpeed(Integer obdSpeed) {
		this.obdSpeed = obdSpeed;
	}
	@Column(name = "obdCoolant", length = 11)
	public Integer getObdCoolant() {
		return obdCoolant;
	}
	public void setObdCoolant(Integer obdCoolant) {
		this.obdCoolant = obdCoolant;
	}
	@Column(name = "obdAirDamper", length = 10)
	public Double getObdAirDamper() {
		return obdAirDamper;
	}
	public void setObdAirDamper(Double obdAirDamper) {
		this.obdAirDamper = obdAirDamper;
	}
	@Column(name = "obdEngine", length = 10)
	public Double getObdEngine() {
		return obdEngine;
	}
	public void setObdEngine(Double obdEngine) {
		this.obdEngine = obdEngine;
	}
	@Column(name = "obdAirFlow", length = 10)
	public Integer getObdAirFlow() {
		return obdAirFlow;
	}
	public void setObdAirFlow(Integer obdAirFlow) {
		this.obdAirFlow = obdAirFlow;
	}
	@Column(name = "obdAirTemperature", length = 11)
	public Integer getObdAirTemperature() {
		return obdAirTemperature;
	}
	public void setObdAirTemperature(Integer obdAirTemperature) {
		this.obdAirTemperature = obdAirTemperature;
	}
	@Column(name = "obdAirPressure", length = 11)
	public String getObdAirPressure() {
		return obdAirPressure;
	}
	public void setObdAirPressure(String obdAirPressure) {
		this.obdAirPressure = obdAirPressure;
	}
	@Column(name = "obdBattery", length = 10)
	public Double getObdBattery() {
		return obdBattery;
	}
	public void setObdBattery(Double obdBattery) {
		this.obdBattery = obdBattery;
	}
	@Column(name = "obdAccident", length = 11)
	public Integer getObdAccident() {
		return obdAccident;
	}
	public void setObdAccident(Integer obdAccident) {
		this.obdAccident = obdAccident;
	}
	@Column(name = "obdUsedOil", length = 11)
	public Integer getObdUsedOil() {
		return obdUsedOil;
	}
	public void setObdUsedOil(Integer obdUsedOil) {
		this.obdUsedOil = obdUsedOil;
	}
	@Column(name = "obdDriverDuration", length = 11)
	public Integer getObdDriverDuration() {
		return obdDriverDuration;
	}
	public void setObdDriverDuration(Integer obdDriverDuration) {
		this.obdDriverDuration = obdDriverDuration;
	}
	@Column(name = "orgIdManager", length = 36)
	public String getOrgIdManager() {
		return orgIdManager;
	}
	public void setOrgIdManager(String orgIdManager) {
		this.orgIdManager = orgIdManager;
	}
	@Column(name = "orgName", length = 200)
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	@Column(name = "carNo", length = 12)
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	@Column(name = "carIdentifyNo", length = 12)
	public String getCarIdentifyNo() {
		return carIdentifyNo;
	}
	public void setCarIdentifyNo(String carIdentifyNo) {
		this.carIdentifyNo = carIdentifyNo;
	}
	
	
	
	

}
