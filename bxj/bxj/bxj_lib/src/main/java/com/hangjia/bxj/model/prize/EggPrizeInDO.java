package com.hangjia.bxj.model.prize;

import com.hangjia.bxj.common.BaseCommonDO;

public class EggPrizeInDO extends BaseCommonDO{
	private Long id;
	
	private Long prizeId;
	
	private Integer incrementNum;
	
	private Double amountLimit;
	
	private Integer status;

	private String name;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(Long prizeId) {
		this.prizeId = prizeId;
	}

	public Integer getIncrementNum() {
		return incrementNum;
	}

	public void setIncrementNum(Integer incrementNum) {
		this.incrementNum = incrementNum;
	}

	public Double getAmountLimit() {
		return amountLimit;
	}

	public void setAmountLimit(Double amountLimit) {
		this.amountLimit = amountLimit;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}