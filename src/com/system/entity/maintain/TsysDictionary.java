package com.system.entity.maintain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;


/**
 * TsysDictionary entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_sys_dictionary")
public class TsysDictionary implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7671795981976034885L;
	private String dictId;// id
	private String dictClasId;// 字典编号
	private String dictNo;// 编号
	private Integer dictIndex;// 排序
	private String dictName;// 名称
	private String dictPyCode;// 拼音码
	private String dictMemo;// 备注
	private String dictFlag;// 标识（有效/作废）
	private String userIdCreate;// 创建人名称
	private Timestamp timeCreate;// 创建时间
	private String userIdUpdate;// 修改人Id
	private Timestamp timeUpdate;// 修改时间
	// 所属字典分类
	private TsysDictionaryClass sysDictionaryClass;

	/** default constructor */
	public TsysDictionary() {
	}

	/** minimal constructor */

	// Property accessors
	@Id
	@Column(name = "dictId", columnDefinition = "nvarchar(50)", unique = true, nullable = false)
	public String getDictId() {

		if (!StringUtils.isBlank(this.dictId)) {
			return this.dictId;
		}
		return UUID.randomUUID().toString();
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	@Column(name = "dictClasId", columnDefinition = "nvarchar(50)", nullable = false)
	public String getDictClasId() {
		return this.dictClasId;
	}

	public void setDictClasId(String dictClasId) {
		this.dictClasId = dictClasId;
	}

	@Column(name = "dictNo", columnDefinition = "nvarchar(50)")
	public String getDictNo() {
		return this.dictNo;
	}

	public void setDictNo(String dictNo) {
		this.dictNo = dictNo;
	}

	@Column(name = "dictIndex", nullable = false)
	public Integer getDictIndex() {
		return this.dictIndex;
	}

	public void setDictIndex(Integer dictIndex) {
		this.dictIndex = dictIndex;
	}

	@Column(name = "dictName", columnDefinition = "nvarchar(50)", nullable = false)
	public String getDictName() {
		return this.dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	@Column(name = "dictPyCode", columnDefinition = "nvarchar(50)", nullable = false)
	public String getDictPyCode() {
		return this.dictPyCode;
	}

	public void setDictPyCode(String dictPyCode) {
		this.dictPyCode = dictPyCode;
	}

	@Column(name = "dictMemo", columnDefinition = "nvarchar(400)")
	public String getDictMemo() {
		return this.dictMemo;
	}

	public void setDictMemo(String dictMemo) {
		this.dictMemo = dictMemo;
	}

	@Column(name = "dictFlag", columnDefinition = "nvarchar(50)", nullable = false)
	public String getDictFlag() {
		return this.dictFlag;
	}

	public void setDictFlag(String dictFlag) {
		this.dictFlag = dictFlag;
	}

	@Column(name = "userIdUpdate", columnDefinition = "nvarchar(50)")
	public String getUserIdUpdate() {
		return this.userIdUpdate;
	}

	public void setUserIdUpdate(String userIdUpdate) {
		this.userIdUpdate = userIdUpdate;
	}

	public Timestamp getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(Timestamp timeCreate) {
		this.timeCreate = timeCreate;
	}

	public Timestamp getTimeUpdate() {
		return timeUpdate;
	}

	public void setTimeUpdate(Timestamp timeUpdate) {
		this.timeUpdate = timeUpdate;
	}

	@Column(name = "userIdCreate", columnDefinition = "nvarchar(50)", nullable = false)
	public String getUserIdCreate() {
		return userIdCreate;
	}

	public void setUserIdCreate(String userIdCreate) {
		this.userIdCreate = userIdCreate;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH, optional = true)
	@JoinColumn(name = "dictClasId", insertable = false, updatable = false)
	public TsysDictionaryClass getSysDictionaryClass() {
		return sysDictionaryClass;
	}

	public void setSysDictionaryClass(TsysDictionaryClass sysDictionaryClass) {
		this.sysDictionaryClass = sysDictionaryClass;
	}
}