package com.cbt.entity;

import java.io.Serializable;

public class BuyerInfo implements Serializable {
    private Integer id;

    private String buyerId;                                     //采购商ID
 
    private String factoryName;                                 //采购商ID
    
    private String username;                                    //用户名

    private String pwd;                                         //密码

    private String email;                                       //登录邮箱

    private String tel;                                         //电话

    private String cellphone;                                   //手机

    private String country;                                     //国家

    private String state;                                       //省份、州

    private Integer confirmEmail;                               //邮箱验证

    private String updateTime;                                    //更新时间

    private String createTime;                                    //创建时间

    private static final long serialVersionUID = 1L;

    
    
    
    
    public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId == null ? null : buyerId.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone == null ? null : cellphone.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Integer getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(Integer confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}