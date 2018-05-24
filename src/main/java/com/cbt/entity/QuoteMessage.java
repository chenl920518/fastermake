package com.cbt.entity;

import java.io.Serializable;

public class QuoteMessage implements Serializable {
	
	
    private Integer id;

    private Integer orderId;                       //询盘号

    private String factoryId;                     //工厂ID

    private String filePath;                      //文件路径

    private String sendTime;                      //发送时间

    private Integer replyStatus;                  //采购商回复0   供应商回复  1
    
    private String messageDetails;                //消息内容
    
    private String replyFactoryId;                //回复工厂的工厂号
    
    private String userName;                      //回复人
    
    private String fileName;                      //原始文件名
    
    private String factoryLogo;                   //工厂logo
    
    private Integer factoryUserId;                //工厂联系人ID
    
    
    //虚拟字段
    private String realName;                     //工厂联系人名
    private String photo;                        //工厂联系人照片

    private static final long serialVersionUID = 1L;

    
    
    public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getFactoryUserId() {
		return factoryUserId;
	}

	public void setFactoryUserId(Integer factoryUserId) {
		this.factoryUserId = factoryUserId;
	}

	public String getFactoryLogo() {
		return factoryLogo;
	}

	public void setFactoryLogo(String factoryLogo) {
		this.factoryLogo = factoryLogo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getReplyFactoryId() {
		return replyFactoryId;
	}

	public void setReplyFactoryId(String replyFactoryId) {
		this.replyFactoryId = replyFactoryId;
	}

	public String getMessageDetails() {
		return messageDetails;
	}

	public void setMessageDetails(String messageDetails) {
		this.messageDetails = messageDetails;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId == null ? null : factoryId.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(Integer replyStatus) {
        this.replyStatus = replyStatus;
    }

	@Override
	public String toString() {
		return "QuoteMessage [id=" + id + ", orderId=" + orderId
				+ ", factoryId=" + factoryId + ", filePath=" + filePath
				+ ", sendTime=" + sendTime + ", replyStatus=" + replyStatus
				+ ", messageDetails=" + messageDetails + ", replyFactoryId="
				+ replyFactoryId + ", userName=" + userName + ", fileName="
				+ fileName + ", factoryLogo=" + factoryLogo
				+ ", factoryUserId=" + factoryUserId + ", realName=" + realName
				+ ", photo=" + photo + "]";
	}
    
    
}