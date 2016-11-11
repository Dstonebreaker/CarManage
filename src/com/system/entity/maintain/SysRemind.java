package com.system.entity.maintain;

public class SysRemind implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8033630976102121340L;
	
	private String  countExamine;
    private String  countInsurance;
    private String  countMaintenance;
	public String getCountExamine() {
		return countExamine;
	}
	public void setCountExamine(String countExamine) {
		this.countExamine = countExamine;
	}
	public String getCountInsurance() {
		return countInsurance;
	}
	public void setCountInsurance(String countInsurance) {
		this.countInsurance = countInsurance;
	}
	public String getCountMaintenance() {
		return countMaintenance;
	}
	public void setCountMaintenance(String countMaintenance) {
		this.countMaintenance = countMaintenance;
	}
    
    
}
