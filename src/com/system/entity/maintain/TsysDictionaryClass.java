package com.system.entity.maintain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

/**
 * TsysDictionaryClass entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_sys_dictionary_class")
public class TsysDictionaryClass implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8063236473698599606L;
	private String dictClasId;// id
	private String dictClasNo;// 字典分类编号
	private String dictClasName;// 字典分类名称
	private Integer dictClasIsSelf;// 是否自用
	private String dictClasFlag;// 标识（有效/作废）

	// Constructors

	/** default constructor */
	public TsysDictionaryClass() {
	}

	

	// Property accessors
	@Id
	@Column(name = "dictClasId", columnDefinition = "nvarchar(50)", unique = true, nullable = false)
	public String getDictClasId() {

		if (!StringUtils.isBlank(this.dictClasId)) {
			return this.dictClasId;
		}
		return UUID.randomUUID().toString();
	}

	public void setDictClasId(String dictClasId) {
		this.dictClasId = dictClasId;
	}


	@Column(name = "dictClasNo", columnDefinition = "nvarchar(50)", nullable = false)
	public String getDictClasNo() {
		return this.dictClasNo;
	}

	public void setDictClasNo(String dictClasNo) {
		this.dictClasNo = dictClasNo;
	}

	@Column(name = "dictClasName", columnDefinition = "nvarchar(50)", nullable = false)
	public String getDictClasName() {
		return this.dictClasName;
	}

	public void setDictClasName(String dictClasName) {
		this.dictClasName = dictClasName;
	}

	@Column(name = "dictClasIsSelf", nullable = false)
	public Integer getDictClasIsSelf() {
		return this.dictClasIsSelf;
	}

	public void setDictClasIsSelf(Integer dictClasIsSelf) {
		this.dictClasIsSelf = dictClasIsSelf;
	}

	@Column(name = "dictClasFlag", columnDefinition = "nvarchar(50)", nullable = false)
	public String getDictClasFlag() {
		return this.dictClasFlag;
	}

	public void setDictClasFlag(String dictClasFlag) {
		this.dictClasFlag = dictClasFlag;
	}


}