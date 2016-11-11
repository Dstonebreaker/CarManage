package com.car.entity.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement
@XmlType(name = "dxhfnr", propOrder = { "LSH", "DHHM", "DXNR","HFSJ","TFH" })
public class Dxhfnr {
	 private String LSH; //--请求查询流水号
	 private String DHHM;//--电话号码
	 private String DXNR;//--回复短信内容
	 private String HFSJ;// --回复时间
	 private String TFH;//--特服号
	public String getLSH() {
		return LSH;
	}
	public void setLSH(String lSH) {
		LSH = lSH;
	}
	public String getDHHM() {
		return DHHM;
	}
	public void setDHHM(String dHHM) {
		DHHM = dHHM;
	}
	public String getDXNR() {
		return DXNR;
	}
	public void setDXNR(String dXNR) {
		DXNR = dXNR;
	}
	public String getHFSJ() {
		return HFSJ;
	}
	public void setHFSJ(String hFSJ) {
		HFSJ = hFSJ;
	}
	public String getTFH() {
		return TFH;
	}
	public void setTFH(String tFH) {
		TFH = tFH;
	}
	 
}
