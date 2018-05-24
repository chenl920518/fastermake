package com.cbt.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class FactoryInfo implements Serializable {
    private Integer id;

	private String factoryId;                  //工厂id
	private String factoryName;                //工厂名
	private String username;                   //联系人姓名
	private String pwd;                        //密码
	private String email;                      //邮箱
	private String tel;                        //电话
	private String country;                    //国家
	private String state;                      //省份
	private String city;                       //城市
	private String district;                   //市区
	private String detailsAddress;             //详细地址
	private String postcode;                   //邮编
	private String staffNumber;                //工厂人数
	private String website;                    //网址
	private String establishmentYear;          //创建年
	private String industryType;               //行业类型
	private String mainProcess;                //主要工艺
	private String factoryLicense;             //营业执照
	private String factoryLogo;                //公司logo
	private String factoryGate;                //公司厂门照片
    private String productionEnvironment;      //成产环境照片（多张）
	private Integer confirmEmail;              //邮箱确认(0：未确认 1：已确认)
	private String updateTime;                 //更新时间
	private String factoryRemark;              //公司介绍
	private String createTime;                 //录入时间
	private Integer isVip;                     //100 普通用户    101vip  105 封号工厂
	private Integer factoryType;               //工厂类型（0:既是供应商又是采购商   1：供应商  2：采购商）  
	private String factoryInfo;                //公司简介
	private String siteSize;                   //场地大小
	
	//添加工厂状态（polo  2017.11.27）
	private Integer factoryStatus;              //工厂状态（0：实地考察未通过  1：实地考察通过）
	private String systemRemark;               //系统评价
	private String inspectionNote;             //考察不通过备注
	private String inspectionDocumentPath;     //验厂文档路径
	
	//添加vip生成时间和过期时间
    private String vipCreateTime;              //vip生成时间
    private String vipExpiresTime;             //vip有效时间
    private String closeTime;                  //账户关闭时间
	
	private List<ProductLibrary> prList;       //产品列表 add by sang
	
	/**chenlun*/
	private String factoryAcreage;           //工厂面积
	private String factoryValue;             //工厂年产值
	private String type;                     //进出口产权
	private List<Equipment> equipmentList;   //工厂设备清单
    private List<Qualification> qualificationList; //资质认证
    
    private String cooperativeEnterprise;    //合作的企业
    private String[] cooperativeEnterpriseS;//合作企业组合
    private String technologicalAdvantage;//公司技术优势
    private String dominantMaterialModel;//优势材料型号
    private String dominantMaterialSize;//优势材料大小
    private String acceptCondition; //接受询盘条件(可接受询盘条件3种)
    private String acceptMoney;  //接受的价格
    private String aliWebsite; //1688网站
    
    private String position; //职位
    private String countryCode;//国家区号
    private String mobile;//手机
    private String fax; //传真
    private String headImage;//头像   
    private String[] mainProcessS;//主要工艺组合
    
    private String productionVideo; //生产视频
    
    private String specialLabel;//特别标签
    
    private FactoryEchart factoryEchart;
    
    private String factoryEquipmentDocument;     //工厂设备文档
    
    private Integer clickCounts;                 //企业档案浏览次数
    
    private String openid;                       //微信openid
   
    //虚拟字段
    private String qualificationNames;          //资格认证列表
    
    private static final long serialVersionUID = 1L;
    
    
    
    
   
    public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getQualificationNames() {
		return qualificationNames;
	}

	public void setQualificationNames(String qualificationNames) {
		this.qualificationNames = qualificationNames;
	}

	public Integer getClickCounts() {
		return clickCounts;
	}

	public void setClickCounts(Integer clickCounts) {
		this.clickCounts = clickCounts;
	}

	public String getFactoryEquipmentDocument() {
		return factoryEquipmentDocument;
	}

	public void setFactoryEquipmentDocument(String factoryEquipmentDocument) {
		this.factoryEquipmentDocument = factoryEquipmentDocument;
	}

	public String getInspectionDocumentPath() {
		return inspectionDocumentPath;
	}

	public void setInspectionDocumentPath(String inspectionDocumentPath) {
		this.inspectionDocumentPath = inspectionDocumentPath;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	public Integer getFactoryStatus() {
		return factoryStatus;
	}

	public void setFactoryStatus(Integer factoryStatus) {
		this.factoryStatus = factoryStatus;
	}

	public String getSystemRemark() {
		return systemRemark;
	}

	public void setSystemRemark(String systemRemark) {
		this.systemRemark = systemRemark;
	}

	public String getInspectionNote() {
		return inspectionNote;
	}

	public void setInspectionNote(String inspectionNote) {
		this.inspectionNote = inspectionNote;
	}

	public String getVipCreateTime() {
		return vipCreateTime;
	}

	public void setVipCreateTime(String vipCreateTime) {
		this.vipCreateTime = vipCreateTime;
	}

	public String getVipExpiresTime() {
		return vipExpiresTime;
	}

	public void setVipExpiresTime(String vipExpiresTime) {
		this.vipExpiresTime = vipExpiresTime;
	}

	public Integer getFactoryType() {
		return factoryType;
	}

	public void setFactoryType(Integer factoryType) {
		this.factoryType = factoryType;
	}

	public Integer getIsVip() {
		return isVip;
	}

	public void setIsVip(Integer isVip) {
		this.isVip = isVip;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId == null ? null : factoryId.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDetailsAddress() {
        return detailsAddress;
    }

    public void setDetailsAddress(String detailsAddress) {
        this.detailsAddress = detailsAddress == null ? null : detailsAddress.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber == null ? null : staffNumber.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public String getEstablishmentYear() {
        return establishmentYear;
    }

    public void setEstablishmentYear(String establishmentYear) {
        this.establishmentYear = establishmentYear == null ? null : establishmentYear.trim();
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType == null ? null : industryType.trim();
    }

    public String getMainProcess() {
        return mainProcess;
    }

    public void setMainProcess(String mainProcess) {
        this.mainProcess = mainProcess == null ? null : mainProcess.trim();
    }

    public String getFactoryLicense() {
        return factoryLicense;
    }

    public void setFactoryLicense(String factoryLicense) {
        this.factoryLicense = factoryLicense == null ? null : factoryLicense.trim();
    }

    public String getFactoryLogo() {
        return factoryLogo;
    }

    public void setFactoryLogo(String factoryLogo) {
        this.factoryLogo = factoryLogo == null ? null : factoryLogo.trim();
    }

    public String getFactoryGate() {
        return factoryGate;
    }

    public void setFactoryGate(String factoryGate) {
        this.factoryGate = factoryGate == null ? null : factoryGate.trim();
    }

    public String getProductionEnvironment() {
        return productionEnvironment;
    }

    public void setProductionEnvironment(String productionEnvironment) {
        this.productionEnvironment = productionEnvironment == null ? null : productionEnvironment.trim();
    }

    public Integer getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(Integer confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFactoryRemark() {
        return factoryRemark;
    }

    public void setFactoryRemark(String factoryRemark) {
        this.factoryRemark = factoryRemark == null ? null : factoryRemark.trim();
    }

	public String getFactoryAcreage() {
		return factoryAcreage;
	}

	public void setFactoryAcreage(String factoryAcreage) {
		this.factoryAcreage = factoryAcreage;
	}

	public String getFactoryValue() {
		return factoryValue;
	}

	public void setFactoryValue(String factoryValue) {
		this.factoryValue = factoryValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Equipment> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<Equipment> equipmentList) {
		this.equipmentList = equipmentList;
	}

	public List<Qualification> getQualificationList() {
		return qualificationList;
	}

	public void setQualificationList(List<Qualification> qualificationList) {
		this.qualificationList = qualificationList;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getCooperativeEnterprise() {
		return cooperativeEnterprise;
	}

	public void setCooperativeEnterprise(String cooperativeEnterprise) {
		this.cooperativeEnterprise = cooperativeEnterprise;
	}

	public String[] getMainProcessS() {
		return mainProcessS;
	}

	public void setMainProcessS(String[] mainProcessS) {
		this.mainProcessS = mainProcessS;
	}

	public String[] getCooperativeEnterpriseS() {
		return cooperativeEnterpriseS;
	}

	public void setCooperativeEnterpriseS(String[] cooperativeEnterpriseS) {
		this.cooperativeEnterpriseS = cooperativeEnterpriseS;
	}

	public String getTechnologicalAdvantage() {
		return technologicalAdvantage;
	}

	public void setTechnologicalAdvantage(String technologicalAdvantage) {
		this.technologicalAdvantage = technologicalAdvantage;
	}

	public String getDominantMaterialModel() {
		return dominantMaterialModel;
	}

	public void setDominantMaterialModel(String dominantMaterialModel) {
		this.dominantMaterialModel = dominantMaterialModel;
	}

	public String getDominantMaterialSize() {
		return dominantMaterialSize;
	}

	public void setDominantMaterialSize(String dominantMaterialSize) {
		this.dominantMaterialSize = dominantMaterialSize;
	}

	public String getAcceptCondition() {
		return acceptCondition;
	}

	public void setAcceptCondition(String acceptCondition) {
		this.acceptCondition = acceptCondition;
	}

	public String getAcceptMoney() {
		return acceptMoney;
	}

	public void setAcceptMoney(String acceptMoney) {
		this.acceptMoney = acceptMoney;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAliWebsite() {
		return aliWebsite;
	}

	public void setAliWebsite(String aliWebsite) {
		this.aliWebsite = aliWebsite;
	}

	public List<ProductLibrary> getPrList() {
		return prList;
	}

	public void setPrList(List<ProductLibrary> prList) {
		this.prList = prList;
	}

	public String getProductionVideo() {
		return productionVideo;
	}

	public void setProductionVideo(String productionVideo) {
		this.productionVideo = productionVideo;
	}
	
	

	public String getFactoryInfo() {
		return factoryInfo;
	}

	public void setFactoryInfo(String factoryInfo) {
		this.factoryInfo = factoryInfo;
	}

	public String getSiteSize() {
		return siteSize;
	}

	public void setSiteSize(String siteSize) {
		this.siteSize = siteSize;
	}

    
	
	public String getSpecialLabel() {
		return specialLabel;
	}

	public void setSpecialLabel(String specialLabel) {
		this.specialLabel = specialLabel;
	}
	
	

	public FactoryEchart getFactoryEchart() {
		return factoryEchart;
	}

	public void setFactoryEchart(FactoryEchart factoryEchart) {
		this.factoryEchart = factoryEchart;
	}

	@Override
	public String toString() {
		return "FactoryInfo [id=" + id + ", factoryId=" + factoryId
				+ ", factoryName=" + factoryName + ", username=" + username
				+ ", pwd=" + pwd + ", email=" + email + ", tel=" + tel
				+ ", country=" + country + ", state=" + state + ", city="
				+ city + ", district=" + district + ", detailsAddress="
				+ detailsAddress + ", postcode=" + postcode + ", staffNumber="
				+ staffNumber + ", website=" + website + ", establishmentYear="
				+ establishmentYear + ", industryType=" + industryType
				+ ", mainProcess=" + mainProcess + ", factoryLicense="
				+ factoryLicense + ", factoryLogo=" + factoryLogo
				+ ", factoryGate=" + factoryGate + ", productionEnvironment="
				+ productionEnvironment + ", confirmEmail=" + confirmEmail
				+ ", updateTime=" + updateTime + ", factoryRemark="
				+ factoryRemark + ", createTime=" + createTime + ", isVip="
				+ isVip + ", factoryType=" + factoryType + ", factoryInfo="
				+ factoryInfo + ", siteSize=" + siteSize + ", factoryStatus="
				+ factoryStatus + ", systemRemark=" + systemRemark
				+ ", inspectionNote=" + inspectionNote
				+ ", inspectionDocumentPath=" + inspectionDocumentPath
				+ ", vipCreateTime=" + vipCreateTime + ", vipExpiresTime="
				+ vipExpiresTime + ", closeTime=" + closeTime + ", prList="
				+ prList + ", factoryAcreage=" + factoryAcreage
				+ ", factoryValue=" + factoryValue + ", type=" + type
				+ ", equipmentList=" + equipmentList + ", qualificationList="
				+ qualificationList + ", cooperativeEnterprise="
				+ cooperativeEnterprise + ", cooperativeEnterpriseS="
				+ Arrays.toString(cooperativeEnterpriseS)
				+ ", technologicalAdvantage=" + technologicalAdvantage
				+ ", dominantMaterialModel=" + dominantMaterialModel
				+ ", dominantMaterialSize=" + dominantMaterialSize
				+ ", acceptCondition=" + acceptCondition + ", acceptMoney="
				+ acceptMoney + ", aliWebsite=" + aliWebsite + ", position="
				+ position + ", countryCode=" + countryCode + ", mobile="
				+ mobile + ", fax=" + fax + ", headImage=" + headImage
				+ ", mainProcessS=" + Arrays.toString(mainProcessS)
				+ ", productionVideo=" + productionVideo + ", specialLabel="
				+ specialLabel + ", factoryEchart=" + factoryEchart
				+ ", factoryEquipmentDocument=" + factoryEquipmentDocument
				+ ", clickCounts=" + clickCounts + ", openid=" + openid
				+ ", qualificationNames=" + qualificationNames + "]";
	}
	
	

}