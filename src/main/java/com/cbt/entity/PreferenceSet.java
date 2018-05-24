package com.cbt.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 偏好设置
 * @author chenlun
 *
 */
public class PreferenceSet implements Serializable {
    private Integer id;

    private String factoryId;

    private String language; //语言

    private String currency; //货币

    private String awardDisc;//授盘

    private String invitedTender;//邀请报价

    private String news;//新消息

    private String updateRecord;//档案更新请求

    private String secrecyApplyYes;//保密协议申请通过

    private String updateSchedule;//更新生产进度

    private String receiveEvaluation;//收到评价

    private String secrecyApplyNo;//保密协议申请被拒绝

    private String inquiryWarn;//推荐询盘提醒
    
    private Date createTime;

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
        this.factoryId = factoryId == null ? null : factoryId.trim();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public String getAwardDisc() {
        return awardDisc;
    }

    public void setAwardDisc(String awardDisc) {
        this.awardDisc = awardDisc == null ? null : awardDisc.trim();
    }

    public String getInvitedTender() {
        return invitedTender;
    }

    public void setInvitedTender(String invitedTender) {
        this.invitedTender = invitedTender == null ? null : invitedTender.trim();
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news == null ? null : news.trim();
    }

    public String getUpdateRecord() {
        return updateRecord;
    }

    public void setUpdateRecord(String updateRecord) {
        this.updateRecord = updateRecord == null ? null : updateRecord.trim();
    }

    public String getSecrecyApplyYes() {
        return secrecyApplyYes;
    }

    public void setSecrecyApplyYes(String secrecyApplyYes) {
        this.secrecyApplyYes = secrecyApplyYes == null ? null : secrecyApplyYes.trim();
    }

    public String getUpdateSchedule() {
        return updateSchedule;
    }

    public void setUpdateSchedule(String updateSchedule) {
        this.updateSchedule = updateSchedule == null ? null : updateSchedule.trim();
    }

    public String getReceiveEvaluation() {
        return receiveEvaluation;
    }

    public void setReceiveEvaluation(String receiveEvaluation) {
        this.receiveEvaluation = receiveEvaluation == null ? null : receiveEvaluation.trim();
    }

    public String getSecrecyApplyNo() {
        return secrecyApplyNo;
    }

    public void setSecrecyApplyNo(String secrecyApplyNo) {
        this.secrecyApplyNo = secrecyApplyNo == null ? null : secrecyApplyNo.trim();
    }

    public String getInquiryWarn() {
        return inquiryWarn;
    }

    public void setInquiryWarn(String inquiryWarn) {
        this.inquiryWarn = inquiryWarn == null ? null : inquiryWarn.trim();
    }

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
}