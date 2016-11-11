package com.car.entity.export;



public class DeployBean implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2246745406389771767L;
	private String userType;
	private String collectType;
	private String collectSubType;
	private int collectValue; 
	private String amount;

      
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getCollectType() {
		return collectType;
	}
	public void setCollectType(String collectType) {
		this.collectType = collectType;
	}
	public String getCollectSubType() {
		return collectSubType;
	}
	public void setCollectSubType(String collectSubType) {
		this.collectSubType = collectSubType;
	}
	public int getCollectValue() {
		return collectValue;
	}
	public void setCollectValue(int collectValue) {
		this.collectValue = collectValue;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}

      
}
