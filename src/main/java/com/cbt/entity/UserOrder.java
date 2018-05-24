package com.cbt.entity;

import java.io.Serializable;

public class UserOrder implements Serializable {
    private Integer id;

    private Integer factoryUserId;      //工厂用户表Id

    private String cgsOrderId;          //凯融项目号

    private Integer orderId;            //快制造询盘号

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFactoryUserId() {
        return factoryUserId;
    }

    public void setFactoryUserId(Integer factoryUserId) {
        this.factoryUserId = factoryUserId;
    }

    public String getCgsOrderId() {
        return cgsOrderId;
    }

    public void setCgsOrderId(String cgsOrderId) {
        this.cgsOrderId = cgsOrderId == null ? null : cgsOrderId.trim();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

	@Override
	public String toString() {
		return "UserOrder [id=" + id + ", factoryUserId=" + factoryUserId
				+ ", cgsOrderId=" + cgsOrderId + ", orderId=" + orderId + "]";
	}
    
    
}