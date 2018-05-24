package com.cbt.entity;

import java.io.Serializable;

public class LoginLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;     
	private String loginEmail;    //登录邮箱
	private String username;      //登录用户
	private String loginTime;     //登录时间
	private String loginFailTime; //登录失败时间
	private String loginIp;       //登录IP	
	private Integer loginStatus;   //登录状态(0:成功 1:失败)
	private String errorInfo;      //错误信息
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginEmail() {
		return loginEmail;
	}
	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLoginFailTime() {
		return loginFailTime;
	}
	public void setLoginFailTime(String loginFailTime) {
		this.loginFailTime = loginFailTime;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public Integer getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(Integer loginStatus) {
		this.loginStatus = loginStatus;
	}
	
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	@Override
	public String toString() {
		return "LoginLog [id=" + id + ", loginEmail=" + loginEmail
				+ ", username=" + username + ", loginTime=" + loginTime
				+ ", loginFailTime=" + loginFailTime + ", loginIp=" + loginIp
				+ ", loginStatus=" + loginStatus + ", errorInfo=" + errorInfo
				+ "]";
	}
	
	
	
	
}
