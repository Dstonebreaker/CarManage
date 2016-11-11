package com.car.entity.export;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_car_collect_deploy")
public class TCarCollectDeploy implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6576029748430291014L;
    private String collectId;
    private String orgName;
    private String collectType;
    private String collectSubType;
    private String collectSubType2;
    private int collectValue;
    private String userId;
    private Date  timeCreate;
    private String userType;
    private String amount;
    
    
    
	public TCarCollectDeploy() {
		super();
	}
	@Id
	@Column(name = "collectId", unique = true, nullable = false, columnDefinition = "nvarchar(36)")
	public String getCollectId() {
		return collectId;
	}
	public void setCollectId(String collectId) {
		this.collectId = collectId;
	}
	@Column(name = "orgName", columnDefinition = "nvarchar(200)")
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	@Column(name = "collectType", columnDefinition = "nvarchar(50)")
	public String getCollectType() {
		return collectType;
	}
	public void setCollectType(String collectType) {
		this.collectType = collectType;
	}
	@Column(name = "collectSubType", columnDefinition = "nvarchar(50)")
	public String getCollectSubType() {
		return collectSubType;
	}
	public void setCollectSubType(String collectSubType) {
		this.collectSubType = collectSubType;
	}
	@Column(name = "collectSubType2", columnDefinition = "nvarchar(50)")
	public String getCollectSubType2() {
		return collectSubType2;
	}
	public void setCollectSubType2(String collectSubType2) {
		this.collectSubType2 = collectSubType2;
	}
	@Column(name = "collectValue")
	public int getCollectValue() {
		return collectValue;
	}
	public void setCollectValue(int collectValue) {
		this.collectValue = collectValue;
	}
	@Column(name = "userId", columnDefinition = "nvarchar(36)")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "timeCreate",length=20)
	public Date getTimeCreate() {
		return timeCreate;
	}
	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}
	@Column(name = "userType",length=20)
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	@Column(name = "amount",length=20)
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	} 
	
	
}
