package com.core.cms.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.core.cms.common.BaseCommonQuery;
import com.core.cms.model.mapper.TradeAssured;
/**
 * 订单信息表
* @author yuanxin
* @date 2017年5月25日上午10:08:40
* @version <b>1.0.0</b>
 */
public class TradeInsureDto  extends BaseCommonQuery implements Serializable {
    private Integer fid;//
    
    private Integer orderId;//订单号

    private String tradeId;//交易单号

    private String thirdOrderId;//第三方订单号

    private Integer memberId;//会员id

    private Integer ftype;//订单类型1订单2团购3免费

    private Integer productId;//保险产品ID

    private String productName;//产品名称

    private Integer isVehicle;//是否为车险 0否1是

    private Integer isImport;//线上线下：0线上，1线下

    private Integer isFree;//免费领取

    private Integer statusId;//保单状态:0=已删除，1=待支付，2=处理中，4=已承保，5=已取消，6=已失效，9=已生效

    private String statusName;//主订单状态名称

    private String insuranceNum;//保单编号

    private Integer insureType;//保障类型，默认0年，1月，2日，3岁，4终身

    private Integer insureYears;//保障时效，101表示终身

    private Integer payYearsType;//交费年限类型，默认0年，1月，2日，3岁，4，终身9一次交清

    private Integer payYears;//交费年限

    private BigDecimal marketPrice;//单份保额

    private BigDecimal salePrice;//单份保费

    private BigDecimal marketAmount;//保额

    private BigDecimal saleAmount;//保费

    private Integer buyNumber;//购买数量

    private BigDecimal totalAmount;//产品总金额

    private Integer isSubjoin;//是否是附加险，默认0不是，1是

    private Integer referrerId;//推荐会员id

    private BigDecimal referrerRate;//推荐返点率

    private String shareId;//分享id

    private Integer sourceId;//渠道ID，订单来源

    private Date insureBeginDate;//保障开始日期

    private Date insureEndDate;//保障结束日期

    private Integer refundStatusId;//退款状态，默认0无退款，1退款中2已退款3已取消

    private String refundStatusName;//退款状态说明

    private Integer refundId;//退款ID

    private Integer productTariffId;//保险费率

    private String refuseReason;//拒绝原因

    private Integer isTeam;//是否团体险

    private Integer isClaim;//理赔标记

    private String claimId;//理赔单号

    private Integer claimStatusId;//理赔状态0未理赔1理赔中2理赔通过3理赔拒绝

    private String signImageUrl;//签名图片路径

    private Date signTime;//签名时间

    private String interfaceCode;//接口编号

    private Integer isAutoInsure;//自动投保1未处理2已处理

    private String insureFileUrl;//电子保单PDF路径

    private String policyNumber;//保单ID(大地撤单使用)/投保单号

    private String payOrderId;//支付订单号

    private Integer isDeduct;//返点标记0未返点1可返点2已返点3返点存在问题4已作废

    private BigDecimal deductRate1;//总返点率

    private BigDecimal deductRate2;//中介返点率

    private BigDecimal deductRate3;//代理返点率

    private BigDecimal deductAmount1;//总返点金额

    private BigDecimal deductAmount2;//中介返点金额

    private BigDecimal deductAmount3;//代理人返点金额

    private BigDecimal deductAmount4;//行家网返点金额

    private String deductMemo;//返点说明

    private Date createTime;//创建时间

    private Date payTime;//支付时间

    private Date modifiedTime;//修改时间

    private Integer guaranteeType;//保障类型

    private Integer payType;//缴费方式

    private String planText;//保障方案

    private String effectiveDate;//生效日期

    private String guaranteeTypeStr;//保障类型名称

    private String payTypeStr;//缴费方式名称

    private String recognizeeName;//被保人姓名

    private String payOrderNo;//支付平台订单号

    private Date payEndTime;//最后支付时间

    private Integer payWay;//支付方式：0=本地指定,1=中民对接，3=提交银行卡支付

    private Integer payState;//支付状态，1=待支付，2=已支付

    private Integer isRenew;//自动续保，默认0否，1续保
    
    private String createTimeTemp;	 //订单创建日期(字符串)
    private String insureBeginDateTemp;	 //订单开始日期(字符串)
    private String insureEndDateTemp;	 //订单结束日期(字符串)
    
    private TradeAssured tradeAssuredInsure;//投保人信息
    private TradeAssured tradeAssuredIsurent;//被保人信息

    private static final long serialVersionUID = 1L;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    public String getThirdOrderId() {
        return thirdOrderId;
    }

    public void setThirdOrderId(String thirdOrderId) {
        this.thirdOrderId = thirdOrderId == null ? null : thirdOrderId.trim();
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getFtype() {
        return ftype;
    }

    public void setFtype(Integer ftype) {
        this.ftype = ftype;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Integer getIsVehicle() {
        return isVehicle;
    }

    public void setIsVehicle(Integer isVehicle) {
        this.isVehicle = isVehicle;
    }

    public Integer getIsImport() {
        return isImport;
    }

    public void setIsImport(Integer isImport) {
        this.isImport = isImport;
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName == null ? null : statusName.trim();
    }

    public String getInsuranceNum() {
        return insuranceNum;
    }

    public void setInsuranceNum(String insuranceNum) {
        this.insuranceNum = insuranceNum == null ? null : insuranceNum.trim();
    }

    public Integer getInsureType() {
        return insureType;
    }

    public void setInsureType(Integer insureType) {
        this.insureType = insureType;
    }

    public Integer getInsureYears() {
        return insureYears;
    }

    public void setInsureYears(Integer insureYears) {
        this.insureYears = insureYears;
    }

    public Integer getPayYearsType() {
        return payYearsType;
    }

    public void setPayYearsType(Integer payYearsType) {
        this.payYearsType = payYearsType;
    }

    public Integer getPayYears() {
        return payYears;
    }

    public void setPayYears(Integer payYears) {
        this.payYears = payYears;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getMarketAmount() {
        return marketAmount;
    }

    public void setMarketAmount(BigDecimal marketAmount) {
        this.marketAmount = marketAmount;
    }

    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }

    public Integer getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getIsSubjoin() {
        return isSubjoin;
    }

    public void setIsSubjoin(Integer isSubjoin) {
        this.isSubjoin = isSubjoin;
    }

    public Integer getReferrerId() {
        return referrerId;
    }

    public void setReferrerId(Integer referrerId) {
        this.referrerId = referrerId;
    }

    public BigDecimal getReferrerRate() {
        return referrerRate;
    }

    public void setReferrerRate(BigDecimal referrerRate) {
        this.referrerRate = referrerRate;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId == null ? null : shareId.trim();
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Date getInsureBeginDate() throws ParseException {
    	if(StringUtils.isNotBlank(this.insureBeginDateTemp)){
			SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
			return dfs.parse(this.insureBeginDateTemp);
		}
        return insureBeginDate;
    }

    public void setInsureBeginDate(Date insureBeginDate) {
        this.insureBeginDate = insureBeginDate;
    }

    public Date getInsureEndDate() throws ParseException {
    	if(StringUtils.isNotBlank(this.insureEndDateTemp)){
			SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
			return dfs.parse(this.insureEndDateTemp);
		}
        return insureEndDate;
    }

    public void setInsureEndDate(Date insureEndDate) {
        this.insureEndDate = insureEndDate;
    }

    public Integer getRefundStatusId() {
        return refundStatusId;
    }

    public void setRefundStatusId(Integer refundStatusId) {
        this.refundStatusId = refundStatusId;
    }

    public String getRefundStatusName() {
        return refundStatusName;
    }

    public void setRefundStatusName(String refundStatusName) {
        this.refundStatusName = refundStatusName == null ? null : refundStatusName.trim();
    }

    public Integer getRefundId() {
        return refundId;
    }

    public void setRefundId(Integer refundId) {
        this.refundId = refundId;
    }

    public Integer getProductTariffId() {
        return productTariffId;
    }

    public void setProductTariffId(Integer productTariffId) {
        this.productTariffId = productTariffId;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason == null ? null : refuseReason.trim();
    }

    public Integer getIsTeam() {
        return isTeam;
    }

    public void setIsTeam(Integer isTeam) {
        this.isTeam = isTeam;
    }

    public Integer getIsClaim() {
        return isClaim;
    }

    public void setIsClaim(Integer isClaim) {
        this.isClaim = isClaim;
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId == null ? null : claimId.trim();
    }

    public Integer getClaimStatusId() {
        return claimStatusId;
    }

    public void setClaimStatusId(Integer claimStatusId) {
        this.claimStatusId = claimStatusId;
    }

    public String getSignImageUrl() {
        return signImageUrl;
    }

    public void setSignImageUrl(String signImageUrl) {
        this.signImageUrl = signImageUrl == null ? null : signImageUrl.trim();
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public String getInterfaceCode() {
        return interfaceCode;
    }

    public void setInterfaceCode(String interfaceCode) {
        this.interfaceCode = interfaceCode == null ? null : interfaceCode.trim();
    }

    public Integer getIsAutoInsure() {
        return isAutoInsure;
    }

    public void setIsAutoInsure(Integer isAutoInsure) {
        this.isAutoInsure = isAutoInsure;
    }

    public String getInsureFileUrl() {
        return insureFileUrl;
    }

    public void setInsureFileUrl(String insureFileUrl) {
        this.insureFileUrl = insureFileUrl == null ? null : insureFileUrl.trim();
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber == null ? null : policyNumber.trim();
    }

    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId == null ? null : payOrderId.trim();
    }

    public Integer getIsDeduct() {
        return isDeduct;
    }

    public void setIsDeduct(Integer isDeduct) {
        this.isDeduct = isDeduct;
    }

    public BigDecimal getDeductRate1() {
        return deductRate1;
    }

    public void setDeductRate1(BigDecimal deductRate1) {
        this.deductRate1 = deductRate1;
    }

    public BigDecimal getDeductRate2() {
        return deductRate2;
    }

    public void setDeductRate2(BigDecimal deductRate2) {
        this.deductRate2 = deductRate2;
    }

    public BigDecimal getDeductRate3() {
        return deductRate3;
    }

    public void setDeductRate3(BigDecimal deductRate3) {
        this.deductRate3 = deductRate3;
    }

    public BigDecimal getDeductAmount1() {
        return deductAmount1;
    }

    public void setDeductAmount1(BigDecimal deductAmount1) {
        this.deductAmount1 = deductAmount1;
    }

    public BigDecimal getDeductAmount2() {
        return deductAmount2;
    }

    public void setDeductAmount2(BigDecimal deductAmount2) {
        this.deductAmount2 = deductAmount2;
    }

    public BigDecimal getDeductAmount3() {
        return deductAmount3;
    }

    public void setDeductAmount3(BigDecimal deductAmount3) {
        this.deductAmount3 = deductAmount3;
    }

    public BigDecimal getDeductAmount4() {
        return deductAmount4;
    }

    public void setDeductAmount4(BigDecimal deductAmount4) {
        this.deductAmount4 = deductAmount4;
    }

    public String getDeductMemo() {
        return deductMemo;
    }

    public void setDeductMemo(String deductMemo) {
        this.deductMemo = deductMemo == null ? null : deductMemo.trim();
    }

    public Date getCreateTime() throws ParseException {
    	if(StringUtils.isNotBlank(this.createTimeTemp)){
			SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd");
			return dfs.parse(this.createTimeTemp);
		}
		return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getGuaranteeType() {
        return guaranteeType;
    }

    public void setGuaranteeType(Integer guaranteeType) {
        this.guaranteeType = guaranteeType;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPlanText() {
        return planText;
    }

    public void setPlanText(String planText) {
        this.planText = planText == null ? null : planText.trim();
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate == null ? null : effectiveDate.trim();
    }

    public String getGuaranteeTypeStr() {
        return guaranteeTypeStr;
    }

    public void setGuaranteeTypeStr(String guaranteeTypeStr) {
        this.guaranteeTypeStr = guaranteeTypeStr == null ? null : guaranteeTypeStr.trim();
    }

    public String getPayTypeStr() {
        return payTypeStr;
    }

    public void setPayTypeStr(String payTypeStr) {
        this.payTypeStr = payTypeStr == null ? null : payTypeStr.trim();
    }

    public String getRecognizeeName() {
        return recognizeeName;
    }

    public void setRecognizeeName(String recognizeeName) {
        this.recognizeeName = recognizeeName == "" ? null : recognizeeName.trim();
    }

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo == null ? null : payOrderNo.trim();
    }

    public Date getPayEndTime() {
        return payEndTime;
    }

    public void setPayEndTime(Date payEndTime) {
        this.payEndTime = payEndTime;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public Integer getPayState() {
        return payState;
    }

    public void setPayState(Integer payState) {
        this.payState = payState;
    }

    public Integer getIsRenew() {
        return isRenew;
    }

    public void setIsRenew(Integer isRenew) {
        this.isRenew = isRenew;
    }

	public String getCreateTimeTemp() {
		return createTimeTemp;
	}

	public void setCreateTimeTemp(String createTimeTemp) {
		this.createTimeTemp = createTimeTemp == "" ? null : createTimeTemp ;
	}

	public TradeAssured getTradeAssuredInsure() {
		return tradeAssuredInsure;
	}

	public void setTradeAssuredInsure(TradeAssured tradeAssuredInsure) {
		this.tradeAssuredInsure = tradeAssuredInsure;
	}

	public TradeAssured getTradeAssuredIsurent() {
		return tradeAssuredIsurent;
	}

	public void setTradeAssuredIsurent(TradeAssured tradeAssuredIsurent) {
		this.tradeAssuredIsurent = tradeAssuredIsurent;
	}

	public String getInsureBeginDateTemp() {
		return insureBeginDateTemp;
	}

	public void setInsureBeginDateTemp(String insureBeginDateTemp) {
		this.insureBeginDateTemp = insureBeginDateTemp;
	}

	public String getInsureEndDateTemp() {
		return insureEndDateTemp;
	}

	public void setInsureEndDateTemp(String insureEndDateTemp) {
		this.insureEndDateTemp = insureEndDateTemp;
	}
    
    
}