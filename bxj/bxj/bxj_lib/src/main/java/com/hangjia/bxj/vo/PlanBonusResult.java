package com.hangjia.bxj.vo;

import java.math.BigDecimal;
import java.util.List;

public class PlanBonusResult {
	private List<BigDecimal> grade3;
	private List<BigDecimal> high;
	private List<BigDecimal> low;
	private List<BigDecimal> middle;
	private String name;
	private String unit;
	
	public List<BigDecimal> getGrade3() {
		return grade3;
	}
	public void setGrade3(List<BigDecimal> grade3) {
		this.grade3 = grade3;
	}
	public List<BigDecimal> getHigh() {
		return high;
	}
	public void setHigh(List<BigDecimal> high) {
		this.high = high;
	}
	public List<BigDecimal> getLow() {
		return low;
	}
	public void setLow(List<BigDecimal> low) {
		this.low = low;
	}
	public List<BigDecimal> getMiddle() {
		return middle;
	}
	public void setMiddle(List<BigDecimal> middle) {
		this.middle = middle;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}	
}
