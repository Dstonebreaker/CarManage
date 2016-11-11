package com.system.entity.maintain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;





@Entity
@Table(name = "t_sys_dictionary_class", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysDictionaryClass implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 481233148300484334L;
     private String dictClasId;
     private String dictClasNo;
     private String dictClasName;
     private Integer dictClasIsSelf;
     private String dictClasFlag;
     
	public SysDictionaryClass() {
		super();
	}
	public SysDictionaryClass(String dictClasId, String dictClasNo,
			String dictClasName, Integer dictClasIsSelf, String dictClasFlag) {
		super();
		this.dictClasId = dictClasId;
		this.dictClasNo = dictClasNo;
		this.dictClasName = dictClasName;
		this.dictClasIsSelf = dictClasIsSelf;
		this.dictClasFlag = dictClasFlag;
	}
	@Id
	@Column(name = "dictClasId", unique = true, nullable = false, length = 36)
	public String getDictClasId() {
		if (!StringUtils.isBlank(this.dictClasId)) {
			return this.dictClasId;
		}
		return UUID.randomUUID().toString();

	}
	public void setDictClasId(String dictClasId) {
		this.dictClasId = dictClasId;
	}
	@Column(name = "dictClasNo",  nullable = false, length = 50)
	public String getDictClasNo() {
		return dictClasNo;
	}
	public void setDictClasNo(String dictClasNo) {
		this.dictClasNo = dictClasNo;
	}
	@Column(name = "dictClasName",  nullable = false, length = 50)
	public String getDictClasName() {
		return dictClasName;
	}
	public void setDictClasName(String dictClasName) {
		this.dictClasName = dictClasName;
	}
	@Column(name = "dictIndex", nullable = false,precision = 11)
	public Integer dictClasIsSelf() {
		return dictClasIsSelf;
	}
	public void setDictClasIsSelf(Integer dictClasIsSelf) {
		this.dictClasIsSelf = dictClasIsSelf;
	}
	@Column(name = "dictClasFlag",  nullable = false, length = 50)
	public String getDictClasFlag() {
		return dictClasFlag;
	}
	public void setDictClasFlag(String dictClasFlag) {
		this.dictClasFlag = dictClasFlag;
	}
     
     
     
}
