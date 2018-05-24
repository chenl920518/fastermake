package com.cbt.entity;

import java.io.Serializable;

public class NoteMessage implements Serializable {
    /**
	 * @fieldName serialVersionUID
	 * @fieldType long
	 * @Description TODO
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String receiverId;  //接收者
    
    private String receiverName; //发送者名称
      
    private String sendId;      //发送者
    
    private String sendName; //发送者名称
    
    private Integer messageType; //消息类型
    
    private Integer isRead;      //0：未读 1：已读
    
    private String messageTitle; // 消息主题

    private String messageDetails; // 消息详情

    private String createTime;
    
    private String url;
    
    private Integer dialogueId;
    
    private FactoryInfo receiverInfo;

    private FactoryInfo sendInfo;
    
    
    private String fileName;
    
    private String filePath;
    
    //增加询盘号 记录消息来源询盘
    private Integer orderId;    //polo  2018/1/12
    

    public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId == null ? null : receiverId.trim();
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId == null ? null : sendId.trim();
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle == null ? null : messageTitle.trim();
    }

    public String getMessageDetails() {
        return messageDetails;
    }

    public void setMessageDetails(String messageDetails) {
        this.messageDetails = messageDetails == null ? null : messageDetails.trim();
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public Integer getDialogueId() {
		return dialogueId;
	}

	public void setDialogueId(Integer dialogueId) {
		this.dialogueId = dialogueId;
	}

	public FactoryInfo getReceiverInfo() {
		return receiverInfo;
	}

	public void setReceiverInfo(FactoryInfo receiverInfo) {
		this.receiverInfo = receiverInfo;
	}

	public FactoryInfo getSendInfo() {
		return sendInfo;
	}

	public void setSendInfo(FactoryInfo sendInfo) {
		this.sendInfo = sendInfo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "NoteMessage [id=" + id + ", receiverId=" + receiverId
				+ ", receiverName=" + receiverName + ", sendId=" + sendId
				+ ", sendName=" + sendName + ", messageType=" + messageType
				+ ", isRead=" + isRead + ", messageTitle=" + messageTitle
				+ ", messageDetails=" + messageDetails + ", createTime="
				+ createTime + ", url=" + url + ", dialogueId=" + dialogueId
				+ ", receiverInfo=" + receiverInfo + ", sendInfo=" + sendInfo
				+ ", fileName=" + fileName + ", filePath=" + filePath
				+ ", orderId=" + orderId + "]";
	}
    
	
	
    
    
}