package com.system.entity.maintain;

/**
 * sessionInfo模型，只要登录成功，就需要设置到session里面，便于系统使用
 * 
 * @author 陈晓亮
 * 
 */
public class SessionInfo implements java.io.Serializable {
	
	private static final long serialVersionUID = -5307186771355623749L;
	
	private SysUser user;
	private SysOrganization organization;
	private String cardImagePath;
	private String personImagePath;
	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}	

	public SysOrganization getOrganization() {
		return organization;
	}

	public void setOrganization(SysOrganization organization) {
		this.organization = organization;
	}

	@Override
	public String toString() {
		return user.getUserLogin();
	}

	public String getCardImagePath() {
		return cardImagePath;
	}

	public void setCardImagePath(String cardImagePath) {
		this.cardImagePath = cardImagePath;
	}

	public String getPersonImagePath() {
		return personImagePath;
	}

	public void setPersonImagePath(String personImagePath) {
		this.personImagePath = personImagePath;
	}
}