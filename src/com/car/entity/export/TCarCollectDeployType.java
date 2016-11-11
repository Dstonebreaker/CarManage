package com.car.entity.export;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_car_collect_deploy_type")
public class TCarCollectDeployType implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 205583188794108337L;
	    private String collectId;
	    private String collectType;
	    private String collectSubType;
	    private String collectValue;
	    private String userId;
	    private Date  timeCreate;
	    
	    @Id
		@Column(name = "collectId", unique = true, nullable = false, columnDefinition = "nvarchar(36)")
		public String getCollectId() {
			return collectId;
		}
		public void setCollectId(String collectId) {
			this.collectId = collectId;
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
		@Column(name = "collectValue", columnDefinition = "nvarchar(50)")
		public String getCollectValue() {
			return collectValue;
		}
		public void setCollectValue(String collectValue) {
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
   
}
