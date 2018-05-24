package com.cbt.entity;

import java.io.Serializable;
import java.util.Date;

public class CollectTab implements Serializable {
    @Override
	public String toString() {
		return "CollectTab [id=" + id + ", factoryId=" + factoryId
				+ ", orderId=" + orderId + ", collectDate=" + collectDate + "]";
	}

	private Integer id;

    private String factoryId;

    private Integer orderId;

    private Date collectDate;

    private static final long serialVersionUID = 1L;

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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getCollectDate() {
        return collectDate;
    }

    public void setCollectDate(Date collectDate) {
        this.collectDate = collectDate;
    }
}