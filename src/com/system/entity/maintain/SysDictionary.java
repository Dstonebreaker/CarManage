package com.system.entity.maintain;

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
@Table(name = "t_sys_dictionary", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysDictionary implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5551502419519894257L;
    private String dictId;
    private String dictClasId;
    private String dictNo;
    private Integer dictIndex;
    private String dictName;
    private String dictPyCode;
    private String dictMemo;
    private String dictFlag;
    private String userIdCreate;
    private Date timeCreate;
    private String userIdUpdate;
    
	public SysDictionary() {
		super();
	}
	public SysDictionary(String dictId, String dictClasId, String dictNo,
			Integer dictIndex, String dictName, String dictPyCode,
			String dictMemo, String dictFlag, String userIdCreate,
			Date timeCreate, String userIdUpdate) {
		super();
		this.dictId = dictId;
		this.dictClasId = dictClasId;
		this.dictNo = dictNo;
		this.dictIndex = dictIndex;
		this.dictName = dictName;
		this.dictPyCode = dictPyCode;
		this.dictMemo = dictMemo;
		this.dictFlag = dictFlag;
		this.userIdCreate = userIdCreate;
		this.timeCreate = timeCreate;
		this.userIdUpdate = userIdUpdate;
	}
	@Id
	@Column(name = "dictId", unique = true, nullable = false, length = 36)
	public String getDictId() {
		if (!StringUtils.isBlank(this.dictId)) {
			return this.dictId;
		}
		return UUID.randomUUID().toString();
	}	
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	@Column(name = "dictClasId", nullable = false, length = 36)
	public String getDictClasId() {
		return dictClasId;
	}
	public void setDictClasId(String dictClasId) {
		this.dictClasId = dictClasId;
	}
	@Column(name = "dictNo", length = 50)
	public String getDictNo() {
		return dictNo;
	}
	public void setDictNo(String dictNo) {
		this.dictNo = dictNo;
	}
	@Column(name = "dictIndex", nullable = false,precision = 11)
	public Integer getDictIndex() {
		return dictIndex;
	}
	public void setDictIndex(Integer dictIndex) {
		this.dictIndex = dictIndex;
	}
	@Column(name = "dictName", nullable = false, length = 50)
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	@Column(name = "dictPyCode", nullable = false, length = 50)
	public String getDictPyCode() {
		return dictPyCode;
	}
	public void setDictPyCode(String dictPyCode) {
		this.dictPyCode = dictPyCode;
	}
	@Column(name = "dictMemo",  length = 100)
	public String getDictMemo() {
		return dictMemo;
	}
	public void setDictMemo(String dictMemo) {
		this.dictMemo = dictMemo;
	}
	@Column(name = "dictFlag", nullable = false, length = 50)
	public String getDictFlag() {
		return dictFlag;
	}
	public void setDictFlag(String dictFlag) {
		this.dictFlag = dictFlag;
	}
	@Column(name = "userIdCreate", nullable = false, length = 36)
	public String getUserIdCreate() {
		return userIdCreate;
	}
	public void setUserIdCreate(String userIdCreate) {
		this.userIdCreate = userIdCreate;
	}
	@Column(name = "timeCreate", nullable = false, length = 7)
	public Date getTimeCreate() {
		return timeCreate;
	}
	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}
	@Column(name = "userIdUpdate",  length = 36)
	public String getUserIdUpdate() {
		return userIdUpdate;
	}
	public void setUserIdUpdate(String userIdUpdate) {
		this.userIdUpdate = userIdUpdate;
	}
    
}
