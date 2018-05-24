package com.cbt.entity;

import java.io.Serializable;
/**
 * 增加工厂用户表主要是区分工厂用户权限
 * @ClassName FactoryUser 
 * @Description
 * @author zj
 * @date 2018年2月7日 下午2:18:31
 */
public class FactoryUser implements Serializable {
    private Integer id;

    private String factoryId;          //工厂id

    private String email;              //邮箱

    private String pwd;                //密码

    private String username;           //用户名
    
    private Integer permission;         //权限
    
    private String updateTime;          //更新时间
    
    private String realName;            //用户实际名
    
    private String openid;              //微信openid

    private static final long serialVersionUID = 1L;

    
    
    public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getPermission() {
		return permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId == null ? null : factoryId.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

	@Override
	public String toString() {
		return "FactoryUser [id=" + id + ", factoryId=" + factoryId
				+ ", email=" + email + ", pwd=" + pwd + ", username="
				+ username + ", permission=" + permission + ", updateTime="
				+ updateTime + ", realName=" + realName + ", openid=" + openid
				+ "]";
	}
    
}