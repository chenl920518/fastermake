package com.cbt.entity;

import java.io.Serializable;

public class BigBuyerComment implements Serializable {
    private Integer id;

    private String title;

    private String buyerId;

    private String products;             //逗号隔开

    private String requirement;

    private Integer isComment;           // 0:未点评  1：点赞

    private String releaseDate;          //发布时间

    private String closingDate;          //截止时间
    
    private String buyerInfo;

    private String comment;
    
    private FactoryInfo factoryInfo;
    
    private String special;

   

    public String getBuyerInfo() {
        return buyerInfo;
    }

    public void setBuyerInfo(String buyerInfo) {
        this.buyerInfo = buyerInfo == null ? null : buyerInfo.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId == null ? null : buyerId.trim();
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products == null ? null : products.trim();
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement == null ? null : requirement.trim();
    }

    public Integer getIsComment() {
        return isComment;
    }

    public void setIsComment(Integer isComment) {
        this.isComment = isComment;
    }

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(String closingDate) {
		this.closingDate = closingDate;
	}

	public FactoryInfo getFactoryInfo() {
		return factoryInfo;
	}

	public void setFactoryInfo(FactoryInfo factoryInfo) {
		this.factoryInfo = factoryInfo;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	
	
    
    
    
}