package com.cbt.entity;

import java.io.Serializable;

public class FactoryEvaluateWithBLOBs extends FactoryEvaluate implements Serializable {
    private String evaluateInfo;

    private String picGroup;

    private static final long serialVersionUID = 1L;

    public String getEvaluateInfo() {
        return evaluateInfo;
    }

    public void setEvaluateInfo(String evaluateInfo) {
        this.evaluateInfo = evaluateInfo == null ? null : evaluateInfo.trim();
    }

    public String getPicGroup() {
        return picGroup;
    }

    public void setPicGroup(String picGroup) {
        this.picGroup = picGroup == null ? null : picGroup.trim();
    }
}