package com.car.entity.car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "v_car_dictionary_relation")
public class VCarDictionaryRelation implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7539804202383947881L;
	private String dictId;
	private String dictClasId;
	private String dictIdRelate;
	private String dictClasIdRelate;
	private String dictName;
	private String dictCarBrandFlag;
	private String dictCarSeriesFlag;

	@Id
	@Column(name = "dictId", unique = true, nullable = false, length = 36)
	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	@Column(name="dictClasId")
	public String getDictClasId() {
		return dictClasId;
	}

	public void setDictClasId(String dictClasId) {
		this.dictClasId = dictClasId;
	}
	@Id
	@Column(name="dictIdRelate")
	public String getDictIdRelate() {
		return dictIdRelate;
	}

	public void setDictIdRelate(String dictIdRelate) {
		this.dictIdRelate = dictIdRelate;
	}
	@Column(name="dictClasIdRelate")
	public String getDictClasIdRelate() {
		return dictClasIdRelate;
	}

	public void setDictClasIdRelate(String dictClasIdRelate) {
		this.dictClasIdRelate = dictClasIdRelate;
	}
	@Column(name="dictName")
	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	@Column(name="dictCarBrandFlag")
	public String getDictCarBrandFlag() {
		return dictCarBrandFlag;
	}

	public void setDictCarBrandFlag(String dictCarBrandFlag) {
		this.dictCarBrandFlag = dictCarBrandFlag;
	}
	@Column(name="dictCarSeriesFlag")
	public String getDictCarSeriesFlag() {
		return dictCarSeriesFlag;
	}

	public void setDictCarSeriesFlag(String dictCarSeriesFlag) {
		this.dictCarSeriesFlag = dictCarSeriesFlag;
	}
	
	

}
