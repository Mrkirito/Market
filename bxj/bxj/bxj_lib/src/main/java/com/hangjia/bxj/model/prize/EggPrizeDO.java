package com.hangjia.bxj.model.prize;

import com.hangjia.bxj.common.BaseCommonDO;

public class EggPrizeDO extends BaseCommonDO{
	
	private Long id;
	
	private Integer usableNum;
	
	private Integer usedNum;
	
	private Integer maxCount;
	
	private String unit;
	
	private Integer isEnable;
	
	private Long version;
	
	private Integer type;
	
	private String name;
	
	private Double probability;
	
	private Integer totalNum;
	
	private Double amountLimit;
	
	private Integer level;
	
	private String prizeImg;
	
	private String contextImg;
	
	private Double amount;
	
	private Double oldProbability;
	
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
	
}