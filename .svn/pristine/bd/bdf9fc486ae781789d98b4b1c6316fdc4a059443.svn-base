package com.cbt.entity;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuoteInfo implements Serializable {

	private String quoteTitle; // 询价标题 add by sang
	private Integer quotePurpose; // 询盘目的 add by sang (0:实际订单 1：暂时不急的询盘)

	private String customerId;    // 客户id
	private Integer orderId;      // 订单号
	private Integer customerType; // 客户类型( 老客户、新客户（大公司）、新客户（小公司）)
	private Integer confidentialityAgreement; // 是否签订保密协议（0：否 1：是 2:自选保密协议）
	private String filePath;      // 保密文件路径 add by sang
    private String confidentialityFileName;      //保密文件名称
	private String quoteEndDate;  // 报价截止日期
    private String deliveryDate;  // 交期时间     （原零件交期  统一为产品交期） 2017/11/22
    private String drawingPath;   // 图纸路径     （原零件图纸  统一为一个图纸（多个压缩上传）） 2017/11/22
    private String drawingName;   // 图纸名称
	
	private String mainProcess; // 主要工艺
	private Integer mainCategory; // 选择工艺方式 add by sang(0:让平台工程师选 1：自选)
	private Integer quoteWay; // 询盘方式（0：公开招标 1：邀请报价）
	private String quoteRemark; // 报价备注
	private String quoteFreightTerm; // 货运条款
	private String shippingAddress1; // 运输地址1
	private String shippingAddress2; // 运输地址2
	private String shippingAddress3; // 运输地址3
	private String inviteFactory; // 邀请工厂列表(逗号隔开）
	private Integer inviteStatus; // 是否邀请工厂报价（0：否 1：是）
	private String paymentTerm; // 付款条款
	private Integer quoteLocation; // 报价位置（0：不限 1：江浙沪 2：深圳、广东、福建）
	private String staffNumber; // 工厂人数
	private String qualification; // 资格认证
	private Integer orderStatus; // 订单类型（0：未发布 1：正常报价订单 2：报价已结束订单 3：报价已取消订单 4：已过期  5：授盘中 6：生产中  7：审核未通过）
	private String createTime; // 创建时间
	private String updateTime; // 更新时间
	private String publishDate; // 发布日期
	private Integer currentNumber; // 报价人数
	private String country; // 国家
	private String city;                   // 送货地区 // add by sang
	private String csgOrderId;             //csg项目号
	private String confirmFactoryId;       //最终授盘工厂ID       2017/11/23
	private String noPassReasons;          //审核未通过原因                   2017/11/28
	
	
	private String equipmentKeywords;  //设备关键词
	private String productKeywords;    //产品关键词
	
	private Integer followStatus;       //跟进状况  （自营）     2017/12/06   
	private Integer inquiryStatus;      //询盘状态  （自营）（1、投标中 2：投标结束 / 选标中 3 投标结束 / 客户跟进中 4投标结束 / 客户现有的供应商价格更便宜  5投标结束 / 客户不能给出合理理由）     2017/12/06  
	
	
	private Integer inspectionStatus;        //检测状态      2017/12/18     (0:不需要1:发起待确认 2:已确认待检测 3:已完成)
	private String inspectionPath;           //检测报告路径   2017/12/18 
	private String inspectionUploadTime;     //检测报告上传时间  
	
	private String quoteDetail;              //报价详情               2018/3/15
	
	private String orderFactoryName;         //下单工厂名            2018/4/26
	private String orderEndDate;             //工厂下单时间        2018/4/26
	private Double totalAmount;              //下单总价格            2018/4/26
	
	private Integer followStatusQuotation;   //内部报价同步的跟进状态    2018/4/27
	private String followDetail;             //跟进详情        2018/4/27
	private String followTime;               //跟进时间        2018/4/27
	
	//虚拟字段开始
	private String factoryName;         //工厂名
	private Integer hasNewItem;         //是否有新事项操作（主要用于后台显示）
    private String salesPhoto;          //销售照片
    private String quoterPhoto;         //报价员照片
    private String salesShortName;      //名称简写
    private String quoterShortName;      //名称简写
    private String assistantShortName;   //报价助理简写
    private String assistantPhoto;       //报价助理照片
//    private String quoterTel;          //报价员电话
//    private String salesTel;           //销售电话
    
	
	private String libraryFactoryId;          // 产品库工厂id   
	private List<QuoteProduct> products; // 询盘产品	
	private String inviteFactoryName;	
	private Integer isequipmentList;  // 0 :是列表里面的内容  1：自填数据 
	
	
	//自营添加销售、报价员、跟单销售、联系电话   2018/1/22
	private String quoter;            //报价员
	private String initialSales;      //初始销售
	private String followSales;       //跟单销售
	private String priceAssistant;    //报价助理
    private String contactTel;        //联系电话

	
	private static final long serialVersionUID = 1L;
	
	
	
	







	public String getFollowTime() {
		return followTime;
	}

	public void setFollowTime(String followTime) {
		this.followTime = followTime;
	}

	public Integer getFollowStatusQuotation() {
		return followStatusQuotation;
	}

	public void setFollowStatusQuotation(Integer followStatusQuotation) {
		this.followStatusQuotation = followStatusQuotation;
	}

	public String getFollowDetail() {
		return followDetail;
	}

	public void setFollowDetail(String followDetail) {
		this.followDetail = followDetail;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getOrderEndDate() {
		return orderEndDate;
	}

	public void setOrderEndDate(String orderEndDate) {
		this.orderEndDate = orderEndDate;
	}

	public String getOrderFactoryName() {
		return orderFactoryName;
	}

	public void setOrderFactoryName(String orderFactoryName) {
		this.orderFactoryName = orderFactoryName;
	}

	public String getQuoteDetail() {
		return quoteDetail;
	}

	public void setQuoteDetail(String quoteDetail) {
		this.quoteDetail = quoteDetail;
	}

	public String getAssistantShortName() {
		return assistantShortName;
	}

	public void setAssistantShortName(String assistantShortName) {
		this.assistantShortName = assistantShortName;
	}

	public String getAssistantPhoto() {
		return assistantPhoto;
	}

	public void setAssistantPhoto(String assistantPhoto) {
		this.assistantPhoto = assistantPhoto;
	}

	public String getPriceAssistant() {
		return priceAssistant;
	}

	public void setPriceAssistant(String priceAssistant) {
		this.priceAssistant = priceAssistant;
	}

	public String getSalesShortName() {
		return salesShortName;
	}

	public void setSalesShortName(String salesShortName) {
		this.salesShortName = salesShortName;
	}

	public String getQuoterShortName() {
		return quoterShortName;
	}

	public void setQuoterShortName(String quoterShortName) {
		this.quoterShortName = quoterShortName;
	}

	public String getSalesPhoto() {
		return salesPhoto;
	}

	public void setSalesPhoto(String salesPhoto) {
		this.salesPhoto = salesPhoto;
	}

	public String getQuoterPhoto() {
		return quoterPhoto;
	}

	public void setQuoterPhoto(String quoterPhoto) {
		this.quoterPhoto = quoterPhoto;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getQuoter() {
		return quoter;
	}

	public void setQuoter(String quoter) {
		this.quoter = quoter;
	}

	public String getInitialSales() {
		return initialSales;
	}

	public void setInitialSales(String initialSales) {
		this.initialSales = initialSales;
	}

	public String getFollowSales() {
		return followSales;
	}

	public void setFollowSales(String followSales) {
		this.followSales = followSales;
	}

	public Integer getInspectionStatus() {
		return inspectionStatus;
	}

	public void setInspectionStatus(Integer inspectionStatus) {
		this.inspectionStatus = inspectionStatus;
	}

	public String getInspectionPath() {
		return inspectionPath;
	}

	public void setInspectionPath(String inspectionPath) {
		this.inspectionPath = inspectionPath;
	}

	public String getInspectionUploadTime() {
		return inspectionUploadTime;
	}

	public void setInspectionUploadTime(String inspectionUploadTime) {
		this.inspectionUploadTime = inspectionUploadTime;
	}

	public Integer getHasNewItem() {
		return hasNewItem;
	}

	public void setHasNewItem(Integer hasNewItem) {
		this.hasNewItem = hasNewItem;
	}

	public Integer getFollowStatus() {
		return followStatus;
	}

	public void setFollowStatus(Integer followStatus) {
		this.followStatus = followStatus;
	}

	public Integer getInquiryStatus() {
		return inquiryStatus;
	}

	public void setInquiryStatus(Integer inquiryStatus) {
		this.inquiryStatus = inquiryStatus;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getNoPassReasons() {
		return noPassReasons;
	}

	public void setNoPassReasons(String noPassReasons) {
		this.noPassReasons = noPassReasons;
	}

	public String getConfirmFactoryId() {
		return confirmFactoryId;
	}

	public void setConfirmFactoryId(String confirmFactoryId) {
		this.confirmFactoryId = confirmFactoryId;
	}

	public String getDrawingPath() {
		return drawingPath;
	}

	public void setDrawingPath(String drawingPath) {
		this.drawingPath = drawingPath;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getEquipmentKeywords() {
		return equipmentKeywords;
	}

	public void setEquipmentKeywords(String equipmentKeywords) {
		this.equipmentKeywords = equipmentKeywords;
	}

	public String getProductKeywords() {
		return productKeywords;
	}

	public void setProductKeywords(String productKeywords) {
		this.productKeywords = productKeywords;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<QuoteProduct> getProducts() {
		return products;
	}

	public void setProducts(List<QuoteProduct> products) {
		this.products = products;
	}

	public Integer getCurrentNumber() {
		return currentNumber;
	}

	public void setCurrentNumber(Integer currentNumber) {
		this.currentNumber = currentNumber;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getCsgOrderId() {
		return csgOrderId;
	}

	public void setCsgOrderId(String csgOrderId) {
		this.csgOrderId = csgOrderId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId == null ? null : customerId.trim();
	}

	public Integer getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}

	public Integer getConfidentialityAgreement() {
		return confidentialityAgreement;
	}

	public void setConfidentialityAgreement(Integer confidentialityAgreement) {
		this.confidentialityAgreement = confidentialityAgreement;
	}

	public String getQuoteEndDate() {
		return quoteEndDate;
	}

	public void setQuoteEndDate(String quoteEndDate) {
		this.quoteEndDate = quoteEndDate;
	}

	public String getMainProcess() {
		return mainProcess;
	}

	public void setMainProcess(String mainProcess) {
		this.mainProcess = mainProcess == null ? null : mainProcess.trim();
	}

	public Integer getQuoteWay() {
		return quoteWay;
	}

	public void setQuoteWay(Integer quoteWay) {
		this.quoteWay = quoteWay;
	}

	public String getQuoteFreightTerm() {
		return quoteFreightTerm;
	}

	public void setQuoteFreightTerm(String quoteFreightTerm) {
		this.quoteFreightTerm = quoteFreightTerm == null ? null
				: quoteFreightTerm.trim();
	}

	public String getShippingAddress1() {
		return shippingAddress1;
	}

	public void setShippingAddress1(String shippingAddress1) {
		this.shippingAddress1 = shippingAddress1 == null ? null
				: shippingAddress1.trim();
	}

	public String getShippingAddress2() {
		return shippingAddress2;
	}

	public void setShippingAddress2(String shippingAddress2) {
		this.shippingAddress2 = shippingAddress2 == null ? null
				: shippingAddress2.trim();
	}

	public String getShippingAddress3() {
		return shippingAddress3;
	}

	public void setShippingAddress3(String shippingAddress3) {
		this.shippingAddress3 = shippingAddress3 == null ? null
				: shippingAddress3.trim();
	}

	public String getInviteFactory() {
		return inviteFactory;
	}

	public void setInviteFactory(String inviteFactory) {
		this.inviteFactory = inviteFactory == null ? null : inviteFactory
				.trim();
	}

	public Integer getInviteStatus() {
		return inviteStatus;
	}

	public void setInviteStatus(Integer inviteStatus) {
		this.inviteStatus = inviteStatus;
	}

	public String getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm == null ? null : paymentTerm.trim();
	}

	public Integer getQuoteLocation() {
		return quoteLocation;
	}

	public void setQuoteLocation(Integer quoteLocation) {
		this.quoteLocation = quoteLocation;
	}

	public String getStaffNumber() {
		return staffNumber;
	}

	public void setStaffNumber(String staffNumber) {
		this.staffNumber = staffNumber == null ? null : staffNumber.trim();
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification == null ? null : qualification
				.trim();
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getQuoteRemark() {
		return quoteRemark;
	}

	public void setQuoteRemark(String quoteRemark) {
		this.quoteRemark = quoteRemark == null ? null : quoteRemark.trim();
	}

	public String getQuoteTitle() {
		return quoteTitle;
	}

	public void setQuoteTitle(String quoteTitle) {
		this.quoteTitle = quoteTitle;
	}

	public Integer getQuotePurpose() {
		return quotePurpose;
	}

	public void setQuotePurpose(Integer quotePurpose) {
		this.quotePurpose = quotePurpose;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(Integer mainCategory) {
		this.mainCategory = mainCategory;
	}
        public String getConfidentialityFileName() {
		return confidentialityFileName;
	}

	public void setConfidentialityFileName(String confidentialityFileName) {
		this.confidentialityFileName = confidentialityFileName;
	}

	public String getDrawingName() {
		return drawingName;
	}

	public void setDrawingName(String drawingName) {
		this.drawingName = drawingName;
	}

	
	public String getInviteFactoryName() {
		return inviteFactoryName;
	}

	public void setInviteFactoryName(String inviteFactoryName) {
		this.inviteFactoryName = inviteFactoryName;
	}

	public Integer getIsequipmentList() {
		return isequipmentList;
	}

	public void setIsequipmentList(Integer isequipmentList) {
		this.isequipmentList = isequipmentList;
	}

	

	public String getLibraryFactoryId() {
		return libraryFactoryId;
	}

	public void setLibraryFactoryId(String libraryFactoryId) {
		this.libraryFactoryId = libraryFactoryId;
	}

	@Override
	public String toString() {
		return "QuoteInfo [quoteTitle=" + quoteTitle + ", quotePurpose="
				+ quotePurpose + ", customerId=" + customerId + ", orderId="
				+ orderId + ", customerType=" + customerType
				+ ", confidentialityAgreement=" + confidentialityAgreement
				+ ", filePath=" + filePath + ", confidentialityFileName="
				+ confidentialityFileName + ", quoteEndDate=" + quoteEndDate
				+ ", deliveryDate=" + deliveryDate + ", drawingPath="
				+ drawingPath + ", drawingName=" + drawingName
				+ ", mainProcess=" + mainProcess + ", mainCategory="
				+ mainCategory + ", quoteWay=" + quoteWay + ", quoteRemark="
				+ quoteRemark + ", quoteFreightTerm=" + quoteFreightTerm
				+ ", shippingAddress1=" + shippingAddress1
				+ ", shippingAddress2=" + shippingAddress2
				+ ", shippingAddress3=" + shippingAddress3 + ", inviteFactory="
				+ inviteFactory + ", inviteStatus=" + inviteStatus
				+ ", paymentTerm=" + paymentTerm + ", quoteLocation="
				+ quoteLocation + ", staffNumber=" + staffNumber
				+ ", qualification=" + qualification + ", orderStatus="
				+ orderStatus + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", publishDate=" + publishDate
				+ ", currentNumber=" + currentNumber + ", country=" + country
				+ ", city=" + city + ", csgOrderId=" + csgOrderId
				+ ", confirmFactoryId=" + confirmFactoryId + ", noPassReasons="
				+ noPassReasons + ", equipmentKeywords=" + equipmentKeywords
				+ ", productKeywords=" + productKeywords + ", followStatus="
				+ followStatus + ", inquiryStatus=" + inquiryStatus
				+ ", inspectionStatus=" + inspectionStatus
				+ ", inspectionPath=" + inspectionPath
				+ ", inspectionUploadTime=" + inspectionUploadTime
				+ ", quoteDetail=" + quoteDetail + ", orderFactoryName="
				+ orderFactoryName + ", orderEndDate=" + orderEndDate
				+ ", totalAmount=" + totalAmount + ", followStatusQuotation="
				+ followStatusQuotation + ", followDetail=" + followDetail
				+ ", factoryName=" + factoryName + ", hasNewItem=" + hasNewItem
				+ ", salesPhoto=" + salesPhoto + ", quoterPhoto=" + quoterPhoto
				+ ", salesShortName=" + salesShortName + ", quoterShortName="
				+ quoterShortName + ", assistantShortName="
				+ assistantShortName + ", assistantPhoto=" + assistantPhoto
				+ ", libraryFactoryId=" + libraryFactoryId + ", products="
				+ products + ", inviteFactoryName=" + inviteFactoryName
				+ ", isequipmentList=" + isequipmentList + ", quoter=" + quoter
				+ ", initialSales=" + initialSales + ", followSales="
				+ followSales + ", priceAssistant=" + priceAssistant
				+ ", contactTel=" + contactTel + "]";
	}

}