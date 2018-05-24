package com.cbt.entity;

import java.io.Serializable;

public class Progress implements Serializable {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
      private long pBytesRead = 0L;        //到目前为止读取文件的比特数 
	  private long pContentLength = 0L;   //文件总大小 
	  private long pItems;                //目前正在读取第几个文件 
	  private float percentage;           //百分比
	 
	  
	  
	  public float getPercentage() {
		return percentage;
	}
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
	public long getpBytesRead() {  
	    return pBytesRead; 
	  } 
	  public void setpBytesRead(long pBytesRead) { 
	    this.pBytesRead = pBytesRead; 
	  } 
	  public long getpContentLength() { 
	    return pContentLength; 
	  } 
	  public void setpContentLength(long pContentLength) { 
	    this.pContentLength = pContentLength; 
	  } 
	  public long getpItems() { 
	    return pItems; 
	  } 
	  public void setpItems(long pItems) { 
	    this.pItems = pItems; 
	  } 
	  @Override
	public String toString() {
		return "Progress [pBytesRead=" + pBytesRead + ", pContentLength="
				+ pContentLength + ", pItems=" + pItems + ", percentage="
				+ percentage + "]";
	}  
	    
}
