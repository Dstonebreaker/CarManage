package com.car.entity.message;

import java.util.List;

import javax.xml.bind.annotation.*;

/**
 * Created by Mrkin on 2016/10/25.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Dxcxjg {
	@XmlElement
	private List<Dxhfnr> dxhfnr;


	public List<Dxhfnr> getDxhfnr() {
		return dxhfnr;
	}

	public void setDxhfnr(List<Dxhfnr> dxhfnr) {
		this.dxhfnr = dxhfnr;
	}
}
