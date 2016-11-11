package com.system.entity.maintain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "t_sys_dictionary_relation", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysDictionaryRelation implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3120259726535243329L;
	
	private String  dictId;
	private String  dictClasId;
	private String  dictIdRelate;
	private String  dictClasIdRelate;
	public SysDictionaryRelation() {
		super();
	}
	public SysDictionaryRelation(String dictId, String dictClasId,
			String dictIdRelate, String dictClasIdRelate) {
		super();
		this.dictId = dictId;
		this.dictClasId = dictClasId;
		this.dictIdRelate = dictIdRelate;
		this.dictClasIdRelate = dictClasIdRelate;
	}
	@Id
	@Column(name = "dictId", unique = true, nullable = false, length = 36)
	public String getDictId() {
		return dictId;
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
	@Id
	@Column(name = "dictIdRelate", unique = true, nullable = false, length = 36)
	public String getDictIdRelate() {
		return dictIdRelate;
	}
	public void setDictIdRelate(String dictIdRelate) {
		this.dictIdRelate = dictIdRelate;
	}
	@Column(name = "dictClasIdRelate", nullable = false, length = 36)
	public String getDictClasIdRelate() {
		return dictClasIdRelate;
	}
	public void setDictClasIdRelate(String dictClasIdRelate) {
		this.dictClasIdRelate = dictClasIdRelate;
	}	


}
