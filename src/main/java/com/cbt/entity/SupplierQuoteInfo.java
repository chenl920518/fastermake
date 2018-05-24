package com.cbt.entity;

import java.io.Serializable;
import java.util.Date;

public class SupplierQuoteInfo implements Serializable {
    private Integer id;

    private String factoryId;

    private Integer orderId;                 //询盘号

    private String paymentTerm;             //支付条款

    private String quoteRemark;              //报价备注

    private String attachmentPath;          //附件路径

    private String quoteReasons;            //选择的优势

    private Date quoteDate;                 //报价日期

    private Integer quoteStatus;            //报价状态  （0：草稿 1:已报价 2:已授盘 3:生产中  4:已撤销 5:已完成  6:已过期 7:被拒绝）

    private Date createTime;                //创建时间
    
    private Integer validityDays;           //有效期
    
    private String paymentRemark;           //付款备注
//    private List<SupplierQuoteProduct> list;  //报价产品
    
    private String csgOrderId;              //CSG orderId
       
    private Integer isInspectedFactory;     //是否验厂
    
    private Integer isInspectedProduct;      //是否验货
    
    private Integer isProcess;               //是否进行大货
    
    private Integer isActive;                //是否有效（判断该订单是否更新报价）（每个订单只有一个有效  触发器update_supplier_quote_info）
    
    private Double totalAmount;              //报价数量最多的总金额
    
    private String refuseReasons;            //拒绝理由
    
    private String operationTime;            //操作时间
    
    private String attachmentName;           //原始附件名
    
    private Integer priceType;               //价格类型（1、含增值税到最近港口价格 2、含增值税出厂价格）   2018/04/20
    
    //虚拟字段
    private String factoryName;             //工厂名
    private String state;                   //工厂所在地区
    private String staffNumber;             //工厂人数
    private String qualificationNames;      //工厂资格认证
    private String establishmentYear;       //创建年
    private String qualificationNameList;   //资格认证
    private static final long serialVersionUID = 1L;

    
    
    
    public Integer getPriceType() {
		return priceType;
	}

	public void setPriceType(Integer priceType) {
		this.priceType = priceType;
	}

	public String getQualificationNameList() {
		return qualificationNameList;
	}

	public void setQualificationNameList(String qualificationNameList) {
		this.qualificationNameList = qualificationNameList;
	}

	public String getEstablishmentYear() {
		return establishmentYear;
	}

	public void setEstablishmentYear(String establishmentYear) {
		this.establishmentYear = establishmentYear;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}

	public String getRefuseReasons() {
		return refuseReasons;
	}

	public void setRefuseReasons(String refuseReasons) {
		this.refuseReasons = refuseReasons;
	}

	public String getQualificationNames() {
		return qualificationNames;
	}

	public void setQualificationNames(String qualificationNames) {
		this.qualificationNames = qualificationNames;
	}

	public String getStaffNumber() {
		return staffNumber;
	}

	public void setStaffNumber(String staffNumber) {
		this.staffNumber = staffNumber;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getIsInspectedFactory() {
		return isInspectedFactory;
	}

	public void setIsInspectedFactory(Integer isInspectedFactory) {
		this.isInspectedFactory = isInspectedFactory;
	}

	public Integer getIsInspectedProduct() {
		return isInspectedProduct;
	}

	public void setIsInspectedProduct(Integer isInspectedProduct) {
		this.isInspectedProduct = isInspectedProduct;
	}

	public Integer getIsProcess() {
		return isProcess;
	}

	public void setIsProcess(Integer isProcess) {
		this.isProcess = isProcess;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getCsgOrderId() {
		return csgOrderId;
	}

	public void setCsgOrderId(String csgOrderId) {
		this.csgOrderId = csgOrderId;
	}

	public String getPaymentRemark() {
		return paymentRemark;
	}

	public void setPaymentRemark(String paymentRemark) {
		this.paymentRemark = paymentRemark;
	}

	public Integer getValidityDays() {
		return validityDays;
	}

	public void setValidityDays(Integer validityDays) {
		this.validityDays = validityDays;
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

    public String getQuoteRemark() {
        return quoteRemark;
    }

    public void setQuoteRemark(String quoteRemark) {
        this.quoteRemark = quoteRemark == null ? null : quoteRemark.trim();
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath == null ? null : attachmentPath.trim();
    }

    public String getQuoteReasons() {
        return quoteReasons;
    }

    public void setQuoteReasons(String quoteReasons) {
        this.quoteReasons = quoteReasons == null ? null : quoteReasons.trim();
    }

    public Date getQuoteDate() {
        return quoteDate;
    }

    public void setQuoteDate(Date quoteDate) {
        this.quoteDate = quoteDate;
    }

    public Integer getQuoteStatus() {
        return quoteStatus;
    }

    public void setQuoteStatus(Integer quoteStatus) {
        this.quoteStatus = quoteStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "SupplierQuoteInfo [id=" + id + ", factoryId=" + factoryId
				+ ", orderId=" + orderId + ", paymentTerm=" + paymentTerm
				+ ", quoteRemark=" + quoteRemark + ", attachmentPath="
				+ attachmentPath + ", quoteReasons=" + quoteReasons
				+ ", quoteDate=" + quoteDate + ", quoteStatus=" + quoteStatus
				+ ", createTime=" + createTime + ", validityDays="
				+ validityDays + ", paymentRemark=" + paymentRemark
				+ ", csgOrderId=" + csgOrderId + ", isInspectedFactory="
				+ isInspectedFactory + ", isInspectedProduct="
				+ isInspectedProduct + ", isProcess=" + isProcess
				+ ", isActive=" + isActive + ", totalAmount=" + totalAmount
				+ ", refuseReasons=" + refuseReasons + ", operationTime="
				+ operationTime + ", attachmentName=" + attachmentName
				+ ", priceType=" + priceType + ", factoryName=" + factoryName
				+ ", state=" + state + ", staffNumber=" + staffNumber
				+ ", qualificationNames=" + qualificationNames
				+ ", establishmentYear=" + establishmentYear
				+ ", qualificationNameList=" + qualificationNameList + "]";
	}
    
    
}