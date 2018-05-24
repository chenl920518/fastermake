package com.cbt.entity;

import java.io.Serializable;

public class QuoteWeeklyReport implements Serializable {
    private Integer id;                   

    private Integer orderId;                //询盘号

    private String uploadDate;              //上传日期

    private String photoPath;               //图片路径

    private String photoPathCompress;       //图片压缩路径

    private String remark;                  //备注

    private String documentPath;            //文档路径

    private Integer fileType;             //文件类型    0：图片  1:进度报告 2：材质证明  3：质量报告 

    private Integer isRead;                 //是否读取

    private String readTime;                //读取时间
    
    private String fileName;                //文件名

    private static final long serialVersionUID = 1L;

    
    
    public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath == null ? null : photoPath.trim();
    }

    public String getPhotoPathCompress() {
        return photoPathCompress;
    }

    public void setPhotoPathCompress(String photoPathCompress) {
        this.photoPathCompress = photoPathCompress == null ? null : photoPathCompress.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath == null ? null : documentPath.trim();
    }

    public Integer getFileType() {
        return fileType;
    }


    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public String getReadTime() {
        return readTime;
    }

    public void setReadTime(String readTime) {
        this.readTime = readTime;
    }

	@Override
	public String toString() {
		return "QuoteWeeklyReport [id=" + id + ", orderId=" + orderId
				+ ", uploadDate=" + uploadDate + ", photoPath=" + photoPath
				+ ", photoPathCompress=" + photoPathCompress + ", remark="
				+ remark + ", documentPath=" + documentPath + ", fileType="
				+ fileType + ", isRead=" + isRead + ", readTime=" + readTime
				+ ", fileName=" + fileName + "]";
	}
    
    
}