package com.car.entity.insurance;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;

/**
 * TCarInsurance entity. @author GJ
 */
@Entity
@Table(name = "t_car_insurance")
public class TCarInsurance implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7983856741790080651L;
	// Fields

	/**
	 * id（GUID）
	 */
	private String insuId;
	/**
	 * 保单号码
	 */
	private String insuNo;
	/**
	 * 车辆id
	 */
	private String carId;
	/**
	 * 车牌号
	 */
	private String carNo;
	/**
	 * 保险类型
	 */
	private String dictIdInsuranceType;
	/**
	 * 保险公司名称
	 */
	private String dictIdInsuranceCorp;
	/**
	 * 保险开始日期
	 */
	private Date insuStartDate;
	/**
	 * 保险到期日
	 */
	private Date insuOverDate;
	/**
	 * 保费金额
	 */
	private Double insuMoney;
	/**
	 * 标识
	 */
	private String dictIdFlag;
	/**
	 * 登记人id
	 */
	private String userIdCreate;
	/**
	 * 登记时间
	 */
	private Date timeCreate;
	/**
	 * 修改人id
	 */
	private String userIdUpdate;
	/**
	 * 修改时间
	 */
	private Date timeUpdate;

	// Constructors

	/** default constructor */
	public TCarInsurance() {
	}

	/** minimal constructor */
	public TCarInsurance(String insuId, String insuNo, String carId, String carNo, String dictIdInsuranceType, String dictIdInsuranceCorp,
			Date insuStartDate, Date insuOverDate, Double insuMoney, String dictIdFlag, String userIdCreate, Date timeCreate) {
		this.insuId = insuId;
		this.insuNo = insuNo;
		this.carId = carId;
		this.carNo = carNo;
		this.dictIdInsuranceType = dictIdInsuranceType;
		this.dictIdInsuranceCorp = dictIdInsuranceCorp;
		this.insuStartDate = insuStartDate;
		this.insuOverDate = insuOverDate;
		this.insuMoney = insuMoney;
		this.dictIdFlag = dictIdFlag;
		this.userIdCreate = userIdCreate;
		this.timeCreate = timeCreate;
	}

	/** full constructor */
	public TCarInsurance(String insuId, String insuNo, String carId, String carNo, String dictIdInsuranceType, String dictIdInsuranceCorp,
			Date insuStartDate, Date insuOverDate, Double insuMoney, String dictIdFlag, String userIdCreate, Date timeCreate,
			String userIdUpdate, Date timeUpdate) {
		this.insuId = insuId;
		this.insuNo = insuNo;
		this.carId = carId;
		this.carNo = carNo;
		this.dictIdInsuranceType = dictIdInsuranceType;
		this.dictIdInsuranceCorp = dictIdInsuranceCorp;
		this.insuStartDate = insuStartDate;
		this.insuOverDate = insuOverDate;
		this.insuMoney = insuMoney;
		this.dictIdFlag = dictIdFlag;
		this.userIdCreate = userIdCreate;
		this.timeCreate = timeCreate;
		this.userIdUpdate = userIdUpdate;
		this.timeUpdate = timeUpdate;
	}

	// Property accessors
	@Id
	@Column(name = "insuId", unique = true, nullable = false, length = 36)
	public String getInsuId() {
		if (!StringUtils.isBlank(this.insuId)) {
			return insuId;
		}
		insuId = UUID.randomUUID().toString();
		return this.insuId;
	}

	public void setInsuId(String insuId) {
		this.insuId = insuId;
	}

	@Column(name = "insuNo", nullable = false, length = 50)
	public String getInsuNo() {
		return this.insuNo;
	}

	public void setInsuNo(String insuNo) {
		this.insuNo = insuNo;
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

	@Column(name = "dictIdInsuranceType", nullable = false, length = 36)
	public String getDictIdInsuranceType() {
		return this.dictIdInsuranceType;
	}

	public void setDictIdInsuranceType(String dictIdInsuranceType) {
		this.dictIdInsuranceType = dictIdInsuranceType;
	}

	@Column(name = "dictIdInsuranceCorp", nullable = false, length = 36)
	public String getDictIdInsuranceCorp() {
		return this.dictIdInsuranceCorp;
	}

	public void setDictIdInsuranceCorp(String dictIdInsuranceCorp) {
		this.dictIdInsuranceCorp = dictIdInsuranceCorp;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "insuStartDate", nullable = false, length = 10)
	public Date getInsuStartDate() {
		return this.insuStartDate;
	}

	public void setInsuStartDate(Date insuStartDate) {
		this.insuStartDate = insuStartDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "insuOverDate", nullable = false, length = 10)
	public Date getInsuOverDate() {
		return this.insuOverDate;
	}

	public void setInsuOverDate(Date insuOverDate) {
		this.insuOverDate = insuOverDate;
	}

	@Column(name = "insuMoney", nullable = false, precision = 10)
	public Double getInsuMoney() {
		return this.insuMoney;
	}

	public void setInsuMoney(Double insuMoney) {
		this.insuMoney = insuMoney;
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