package com.cbt.entity;

import java.io.Serializable;

public class QuoteBigProductsTab implements Serializable {
    private Integer id;                  

    private Integer bigProductId;               //产品表id
    
    private Integer productId;               //产品表id

    private Integer orderId;                 //询盘号

    private Integer quantity;                //数量

    private Double unitPrice;                //单价 

    private Double moldPrice;                //模具单价

    private String productRemark;            //产品备注
    
    
    //虚拟字段
	private String productName;                                       //产品名
	private String materials;                                         //材料
	private String process;                                           //基本工艺
	private Double weight;                                            //重量
	private String drawingPathCompress;                               //产品缩略图
	private String originalRemark;                                    //零件初始备注
	
    private static final long serialVersionUID = 1L;

    
    
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

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getDrawingPathCompress() {
		return drawingPathCompress;
	}

	public void setDrawingPathCompress(String drawingPathCompress) {
		this.drawingPathCompress = drawingPathCompress;
	}

	public String getOriginalRemark() {
		return originalRemark;
	}

	public void setOriginalRemark(String originalRemark) {
		this.originalRemark = originalRemark;
	}

	public Integer getBigProductId() {
		return bigProductId;
	}

	public void setBigProductId(Integer bigProductId) {
		this.bigProductId = bigProductId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getMoldPrice() {
        return moldPrice;
    }

    public void setMoldPrice(Double moldPrice) {
        this.moldPrice = moldPrice;
    }

    public String getProductRemark() {
        return productRemark;
    }

    public void setProductRemark(String productRemark) {
        this.productRemark = productRemark == null ? null : productRemark.trim();
    }

	@Override
	public String toString() {
		return "QuoteBigProductsTab [id=" + id + ", bigProductId="
				+ bigProductId + ", productId=" + productId + ", orderId="
				+ orderId + ", quantity=" + quantity + ", unitPrice="
				+ unitPrice + ", moldPrice=" + moldPrice + ", productRemark="
				+ productRemark + ", productName=" + productName
				+ ", materials=" + materials + ", process=" + process
				+ ", weight=" + weight + ", drawingPathCompress="
				+ drawingPathCompress + ", originalRemark=" + originalRemark
				+ "]";
	}
    
    
    
}