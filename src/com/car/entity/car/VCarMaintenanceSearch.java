package com.car.entity.car;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name = "v_car_maintenance_search")
public class VCarMaintenanceSearch implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -587709852094823493L;
	private String mainId;//id
	private String carId;//车辆id
	private String carNo;//车辆No
	private Integer mainInMileage;//入厂里程
	private Integer mainMileage;//保养里程
	private Integer mainNextMileage;//下次保养里程
	private Date mainTime;//保养时间
	private Date mainNextTime;//下次保养时间
	private Double mainMoney;//保养金额
	private String mainCondition;//保养内容
	private String userIdHandler;//经办人
	private String dictIdMaintenanceFactory;//保养厂家
	private String userIdCreate;
	private Date timeCreate;
	private String userIdUpdate;
	private Date timeUpdate;
	private String dictIdFlag;
	private String orgIdManager;
	private String orgName;
	private Integer carMileage;
	private String userName;
	private String dictName;

	// Property accessors
	
	public VCarMaintenanceSearch(String mainId, String carId, String carNo,
			Integer mainInMileage, Integer mainMileage,
			Integer mainNextMileage, Date mainTime, Date mainNextTime,
			Double mainMoney, String mainCondition, String userIdHandler,
			String dictIdMaintenanceFactory, String userIdCreate,
			Date timeCreate, String userIdUpdate, Date timeUpdate,
			String dictIdFlag, String orgIdManager, String orgName,
			Integer carMileage, String userName, String dictName) {
		super();
		this.mainId = mainId;
		this.carId = carId;
		this.carNo = carNo;
		this.mainInMileage = mainInMileage;
		this.mainMileage = mainMileage;
		this.mainNextMileage = mainNextMileage;
		this.mainTime = mainTime;
		this.mainNextTime = mainNextTime;
		this.mainMoney = mainMoney;
		this.mainCondition = mainCondition;
		this.userIdHandler = userIdHandler;
		this.dictIdMaintenanceFactory = dictIdMaintenanceFactory;
		this.userIdCreate = userIdCreate;
		this.timeCreate = timeCreate;
		this.userIdUpdate = userIdUpdate;
		this.timeUpdate = timeUpdate;
		this.dictIdFlag = dictIdFlag;
		this.orgIdManager = orgIdManager;
		this.orgName = orgName;
		this.carMileage = carMileage;
		this.userName = userName;
		this.dictName = dictName;
		
	}
	
		public VCarMaintenanceSearch() {
		super();
	}

		@Id
		@Column(name = "mainId", unique = true, nullable = false, length = 36)
		public String getMainId() {
			if (!StringUtils.isBlank(this.mainId)) {
				return mainId;
			}
			mainId = UUID.randomUUID().toString();
			return this.mainId;
		}	
		
		public void setMainId(String mainId) {
			this.mainId = mainId;
		}

		@Column(name = "carId", nullable = false, length = 36)
		public String getCarId() {
			return this.carId;
		}

		public void setCarId(String carId) {
			this.carId = carId;
		}

		@Column(name = "carNo", nullable = false, length = 12)
		public String getCarNo() {
			return this.carNo;
		}

		public void setCarNo(String carNo) {
			this.carNo = carNo;
		}

		@Column(name = "mainInMileage", nullable = false, precision = 10)
		public Integer getMainInMileage() {
			return this.mainInMileage;
		}

		public void setMainInMileage(Integer mainInMileage) {
			this.mainInMileage = mainInMileage;
		}

		@Column(name = "mainMileage", nullable = false, precision = 10)
		public Integer getMainMileage() {
			return this.mainMileage;
		}

		public void setMainMileage(Integer mainMileage) {
			this.mainMileage = mainMileage;
		}

		@Column(name = "mainNextMileage", nullable = true, precision = 10)
		public Integer getMainNextMileage() {
			return this.mainNextMileage;
		}

		public void setMainNextMileage(Integer mainNextMileage) {
			this.mainNextMileage = mainNextMileage;
		}

		@Temporal(TemporalType.DATE)
		@Column(name = "mainTime", nullable = false, length = 10)
		public Date getMainTime() {
			return this.mainTime;
		}

		public void setMainTime(Date mainTime) {
			this.mainTime = mainTime;
		}

		@Temporal(TemporalType.DATE)
		@Column(name = "mainNextTime", nullable = false, length = 10)
		public Date getMainNextTime() {
			return this.mainNextTime;
		}

		public void setMainNextTime(Date mainNextTime) {
			this.mainNextTime = mainNextTime;
		}

		@Column(name = "mainMoney", nullable = false, precision = 10)
		public Double getMainMoney() {
			return this.mainMoney;
		}

		public void setMainMoney(Double mainMoney) {
			this.mainMoney = mainMoney;
		}

		@Column(name = "mainCondition", nullable = false, length = 400)
		public String getMainCondition() {
			return this.mainCondition;
		}

		public void setMainCondition(String mainCondition) {
			this.mainCondition = mainCondition;
		}

		@Column(name = "userIdHandler", nullable = false, length = 36)
		public String getUserIdHandler() {
			return this.userIdHandler;
		}

		public void setUserIdHandler(String userIdHandler) {
			this.userIdHandler = userIdHandler;
		}

		@Column(name = "dictIdMaintenanceFactory", nullable = false, length = 36)
		public String getDictIdMaintenanceFactory() {
			return this.dictIdMaintenanceFactory;
		}

		public void setDictIdMaintenanceFactory(String dictIdMaintenanceFactory) {
			this.dictIdMaintenanceFactory = dictIdMaintenanceFactory;
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
		@Column(name = "dictIdFlag", nullable = false, length = 36)
		public String getDictIdFlag() {
			return dictIdFlag;
		}

		public void setDictIdFlag(String dictIdFlag) {
			this.dictIdFlag = dictIdFlag;
		}
		@Column(name = "orgIdManager", nullable = false, length = 36)
		public String getOrgIdManager() {
			return orgIdManager;
		}

		public void setOrgIdManager(String orgIdManager) {
			this.orgIdManager = orgIdManager;
		}
		@Column(name = "orgName", nullable = false, length = 36)
		public String getOrgName() {
			return orgName;
		}

		public void setOrgName(String orgName) {
			this.orgName = orgName;
		}
		@Column(name = "carMileage", nullable = false, length = 10)
		public Integer getCarMileage() {
			return carMileage;
		}
		public void setCarMileage(Integer carMileage) {
			this.carMileage = carMileage;
		}
		@Column(name = "userName", nullable = false, length = 36)
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		@Column(name = "dictName", nullable = false, length = 36)
		public String getDictName() {
			return dictName;
		}
		public void setDictName(String dictName) {
			this.dictName = dictName;
		}
	
   
}
