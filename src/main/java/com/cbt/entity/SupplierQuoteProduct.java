package com.cbt.entity;

import java.io.Serializable;

public class SupplierQuoteProduct implements Serializable {
    private Integer id;

    private Integer supplierQuoteId;       //工厂报价表ID

    private Integer orderId;

    private Double quoteUnitPrice1;         //报价产品价格1

    private Double quoteUnitPrice2;         //报价产品价格2

    private Double quoteUnitPrice3;         //报价产品价格3

    private Double quoteMoldPrice1;         //报价模具费1

    private Double quoteMoldPrice2;         //报价模具费2

    private Double quoteMoldPrice3;         //报价模具费3
    
    private Integer quoteProductId;         //报价产品ID
    
    private String productRemark;           //报价产品备注
    
    
    //虚拟字段
    private String createTime;             //报价时间
    private String productName;            //报价产品名
    private String materials;              //报价材质
    private String quantityList;           //报价数量
    private String attachmentPath;         //报价附件
    private Integer priceType;             //价格类型
    
    
    

    public Integer getPriceType() {
		return priceType;
	}

	public void setPriceType(Integer priceType) {
		this.priceType = priceType;
	}

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	public String getProductRemark() {
		return productRemark;
	}

	public void setProductRemark(String productRemark) {
		this.productRemark = productRemark;
	}

	public String getQuantityList() {
		return quantityList;
	}

	public void setQuantityList(String quantityList) {
		this.quantityList = quantityList;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMaterials() {
		return materials;
	}

	public void setMaterials(String materials) {
		this.materials = materials;
	}

	public Integer getQuoteProductId() {
		return quoteProductId;
	}

	public void setQuoteProductId(Integer quoteProductId) {
		this.quoteProductId = quoteProductId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSupplierQuoteId() {
        return supplierQuoteId;
    }

    public void setSupplierQuoteId(Integer supplierQuoteId) {
        this.supplierQuoteId = supplierQuoteId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Double getQuoteUnitPrice1() {
        return quoteUnitPrice1;
    }

    public void setQuoteUnitPrice1(Double quoteUnitPrice1) {
        this.quoteUnitPrice1 = quoteUnitPrice1;
    }

    public Double getQuoteUnitPrice2() {
        return quoteUnitPrice2;
    }

    public void setQuoteUnitPrice2(Double quoteUnitPrice2) {
        this.quoteUnitPrice2 = quoteUnitPrice2;
    }

    public Double getQuoteUnitPrice3() {
        return quoteUnitPrice3;
    }

    public void setQuoteUnitPrice3(Double quoteUnitPrice3) {
        this.quoteUnitPrice3 = quoteUnitPrice3;
    }

    public Double getQuoteMoldPrice1() {
        return quoteMoldPrice1;
    }

    public void setQuoteMoldPrice1(Double quoteMoldPrice1) {
        this.quoteMoldPrice1 = quoteMoldPrice1;
    }

    public Double getQuoteMoldPrice2() {
        return quoteMoldPrice2;
    }

    public void setQuoteMoldPrice2(Double quoteMoldPrice2) {
        this.quoteMoldPrice2 = quoteMoldPrice2;
    }

    public Double getQuoteMoldPrice3() {
        return quoteMoldPrice3;
    }

    public void setQuoteMoldPrice3(Double quoteMoldPrice3) {
        this.quoteMoldPrice3 = quoteMoldPrice3;
    }

	@Override
	public String toString() {
		return "SupplierQuoteProduct [id=" + id + ", supplierQuoteId="
				+ supplierQuoteId + ", orderId=" + orderId
				+ ", quoteUnitPrice1=" + quoteUnitPrice1 + ", quoteUnitPrice2="
				+ quoteUnitPrice2 + ", quoteUnitPrice3=" + quoteUnitPrice3
				+ ", quoteMoldPrice1=" + quoteMoldPrice1 + ", quoteMoldPrice2="
				+ quoteMoldPrice2 + ", quoteMoldPrice3=" + quoteMoldPrice3
				+ ", quoteProductId=" + quoteProductId + ", productRemark="
				+ productRemark + ", createTime=" + createTime
				+ ", productName=" + productName + ", materials=" + materials
				+ ", quantityList=" + quantityList + ", attachmentPath="
				+ attachmentPath + ", priceType=" + priceType + "]";
	}
    
    
    
}