package com.hangjia.bxj.model.prize;

import java.util.Date;

import com.hangjia.bxj.common.BaseCommonDO;

public class PrizeBonusLog extends BaseCommonDO{
	/** 奖品id **/
	private Long id;
	/** 可用奖品数 **/
	private Integer usableNum;
    /**已用奖品数**/
	private Integer usedNum;
	/**奖品总数**/
	private Integer totalNum;
	/** 奖品名称 **/
	private String name;
	
	private Integer maxCount;
	
	private String unit;
	
	private Integer isEnable;
	
	private Long version;
	
	private Integer type;
	
	private Double probability;
	
	private Double amountLimit;
	
	private Integer level;
	
	private String prizeImg;
	
	private String contextImg;
	/**奖品价值  RMB**/
	private Double amount;
	
	private Double oldProbability;
	
	/** 奖品active表中 收货用户手机号 **/
	private String takephone;
	/**  奖品active表中 收货用户名称 **/
	private String takeName;
	/**  奖品active表中 用户地区 **/
	private String area;
	/**  奖品active表中 用户收货详细地址 **/
	private String address;
	
	/** log表中 id **/
	private Long bonusLogId;
	/** log表中 奖品id **/
	private Long prizeId;
	/** log中用户奖品领取状态 1 未激活  2已激活 3发货成功**/
	private Integer logStatus;
	/** log中奖品类型  **/
	private Integer bonusType;
	/** log中 获奖时间  **/
	private Date logCreateTime;

	/**ip**/
	private String ip;
 
	private String userName;
 	
	
	/** 用户id**/
	private Long userID;
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getOldProbability() {
		return oldProbability;
	}

	public void setOldProbability(Double oldProbability) {
		this.oldProbability = oldProbability;
	}

	public Double getProbability() {
		return probability;
	}

	public void setProbability(Double probability) {
		this.probability = probability;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUsableNum() {
		return usableNum;
	}

	public void setUsableNum(Integer usableNum) {
		this.usableNum = usableNum;
	}

	public Integer getUsedNum() {
		return usedNum;
	}

	public void setUsedNum(Integer usedNum) {
		this.usedNum = usedNum;
	}

	public Integer getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Double getAmountLimit() {
		return amountLimit;
	}

	public void setAmountLimit(Double amountLimit) {
		this.amountLimit = amountLimit;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getPrizeImg() {
		return prizeImg;
	}

	public void setPrizeImg(String prizeImg) {
		this.prizeImg = prizeImg;
	}

	public String getContextImg() {
		return contextImg;
	}

	public void setContextImg(String contextImg) {
		this.contextImg = contextImg;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getBonusLogId() {
		return bonusLogId;
	}

	public void setBonusLogId(Long bonusLogId) {
		this.bonusLogId = bonusLogId;
	}

	public Long getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(Long prizeId) {
		this.prizeId = prizeId;
	}

	public Integer getLogStatus() {
		return logStatus;
	}

	public void setLogStatus(Integer logStatus) {
		this.logStatus = logStatus;
	}

	public Integer getBonusType() {
		return bonusType;
	}

	public void setBonusType(Integer bonusType) {
		this.bonusType = bonusType;
	}

	public Date getLogCreateTime() {
		return logCreateTime;
	}

	public void setLogCreateTime(Date logCreateTime) {
		this.logCreateTime = logCreateTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getTakeName() {
		return takeName;
	}

	public void setTakeName(String takeName) {
		this.takeName = takeName;
	}

	public String getTakephone() {
		return takephone;
	}

	public void setTakephone(String takephone) {
		this.takephone = takephone;
	}
	
}