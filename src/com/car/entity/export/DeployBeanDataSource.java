package com.car.entity.export;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;



public class DeployBeanDataSource implements java.io.Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 5517136210734666214L;
	private JRBeanCollectionDataSource mainDs;
	private JRBeanCollectionDataSource subDs;
	
	
	
	public DeployBeanDataSource(JRBeanCollectionDataSource mainDs,JRBeanCollectionDataSource subDs) {
		super();
		this.mainDs = mainDs;
		this.subDs = subDs;
	}
	public JRBeanCollectionDataSource getmainDs() {
		return mainDs;
	}
	public void setmainDs(JRBeanCollectionDataSource mainDs) {
		this.mainDs = mainDs;
	}
	public JRBeanCollectionDataSource getSubDs() {
		return subDs;
	}
	public void setSubDs(JRBeanCollectionDataSource subDs) {
		this.subDs = subDs;
	}
	
	
}
