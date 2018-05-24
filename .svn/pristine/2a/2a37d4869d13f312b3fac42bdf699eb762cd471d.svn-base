package com.cbt.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 资质认证
 * @author chenlun
 *
 */
public class Qualification implements Serializable {
    private Integer id;

    private String factoryId;

    private String qualificationName;
    
    private String fileUrl;

    private String validityTime;

    private Date createDate;

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
        this.factoryId = factoryId;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName == null ? null : qualificationName.trim();
    }
    
    public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getValidityTime() {
		return validityTime;
	}

	public void setValidityTime(String validityTime) {
		this.validityTime = validityTime;
	}

	public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	@Override
	public String toString() {
		return "Qualification [id=" + id + ", factoryId=" + factoryId + ", qualificationName=" + qualificationName
				+ ", fileUrl=" + fileUrl + ", validityTime=" + validityTime + ", createDate=" + createDate + "]";
	}
    
}