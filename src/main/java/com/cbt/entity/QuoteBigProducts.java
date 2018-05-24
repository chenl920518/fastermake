package com.cbt.entity;

import java.io.Serializable;

public class QuoteBigProducts implements Serializable {
	               
    private Integer id;                 

    private String customerId;                    //客户id

    private Integer orderId;                      //询盘号

    private String paymentTerm;                   //付款条款

    private String purchaserRequirement;          //采购商需求
 
    private String purchaserRemark;               //采购商备注

    private String createTime;                    //创建时间

    private Integer isSupplierAccept;             //供应商是否接受（0：未接受 1：已接受  2：已拒绝 3 : 待付款确认 4：已到账 ）

    private String factoryId;                     //供应商id

    private String supplierRemark;                //供应商备注

    private String supplierReplyTime;             //供应商回复时间
    
    private Integer isActive;                     //是否有效
    
    private Double totalAmount;                   //总金额
    
    private String updateTime;                    //更新时间
    
    private String paymentNotConfirmTime;         //付款待确认时间
    private String paymentConfirmTime;            //付款确认时间


	//虚拟字段
    private String factoryName;                   //供应商名

    private static final long serialVersionUID = 1L;

    
    
    public String getPaymentConfirmTime() {
		return paymentConfirmTime;
	}

	public void setPaymentConfirmTime(String paymentConfirmTime) {
		this.paymentConfirmTime = paymentConfirmTime;
	}

	public String getPaymentNotConfirmTime() {
		return paymentNotConfirmTime;
	}

	public void setPaymentNotConfirmTime(String paymentNotConfirmTime) {
		this.paymentNotConfirmTime = paymentNotConfirmTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
    public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm == null ? null : paymentTerm.trim();
    }

    public String getPurchaserRequirement() {
        return purchaserRequirement;
    }

    public void setPurchaserRequirement(String purchaserRequirement) {
        this.purchaserRequirement = purchaserRequirement == null ? null : purchaserRequirement.trim();
    }

    public String getPurchaserRemark() {
        return purchaserRemark;
    }

    public void setPurchaserRemark(String purchaserRemark) {
        this.purchaserRemark = purchaserRemark == null ? null : purchaserRemark.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getIsSupplierAccept() {
        return isSupplierAccept;
    }

    public void setIsSupplierAccept(Integer isSupplierAccept) {
        this.isSupplierAccept = isSupplierAccept;
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId == null ? null : factoryId.trim();
    }

    public String getSupplierRemark() {
        return supplierRemark;
    }

    public void setSupplierRemark(String supplierRemark) {
        this.supplierRemark = supplierRemark == null ? null : supplierRemark.trim();
    }

    public String getSupplierReplyTime() {
        return supplierReplyTime;
    }

    public void setSupplierReplyTime(String supplierReplyTime) {
        this.supplierReplyTime = supplierReplyTime;
    }

	@Override
	public String toString() {
		return "QuoteBigProducts [id=" + id + ", customerId=" + customerId
				+ ", orderId=" + orderId + ", paymentTerm=" + paymentTerm
				+ ", purchaserRequirement=" + purchaserRequirement
				+ ", purchaserRemark=" + purchaserRemark + ", createTime="
				+ createTime + ", isSupplierAccept=" + isSupplierAccept
				+ ", factoryId=" + factoryId + ", supplierRemark="
				+ supplierRemark + ", supplierReplyTime=" + supplierReplyTime
				+ ", isActive=" + isActive + ", totalAmount=" + totalAmount
				+ ", updateTime=" + updateTime + ", paymentNotConfirmTime="
				+ paymentNotConfirmTime + ", paymentConfirmTime="
				+ paymentConfirmTime + ", factoryName=" + factoryName + "]";
	}
    
    
    
}