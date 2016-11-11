package com.system.entity.maintain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "v_sys_organization_checker")
public class VSysOrganizationChecker  implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6352173523504342326L;
	private String id;
	private String orgid;
	private String userId;
	private String userphone;
	
	
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Id
	@Column(name = "orgid",length = 36)
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	@Id
	@Column(name = "userId",length = 36)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Id
	@Column(name = "userphone",length = 36)
	public String getUserphone() {
		return userphone;
	}
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	
	
	

}
