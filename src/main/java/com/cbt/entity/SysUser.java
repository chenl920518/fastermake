package com.cbt.entity;

import java.io.Serializable;
import java.util.Date;

public class SysUser implements Serializable {
    private Integer id;

    private String loginName;

    private String password;

    private Date createdate;
    
    
    private Integer permission; //权限
    private Integer backuserId; //后台用户id
    private String photo;       //销售照片
    

    private static final long serialVersionUID = 1L;

    
    
    
    public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getPermission() {
		return permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	public Integer getBackuserId() {
		return backuserId;
	}

	public void setBackuserId(Integer backuserId) {
		this.backuserId = backuserId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

	@Override
	public String toString() {
		return "SysUser [id=" + id + ", loginName=" + loginName + ", password="
				+ password + ", createdate=" + createdate + ", permission="
				+ permission + ", backuserId=" + backuserId + ", photo="
				+ photo + "]";
	}
    
    
}