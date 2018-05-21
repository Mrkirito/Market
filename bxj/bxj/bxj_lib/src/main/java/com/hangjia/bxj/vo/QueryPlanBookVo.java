package com.hangjia.bxj.vo;

import java.math.BigDecimal;

public class QueryPlanBookVo {
	private Integer sex = 1;
	private BigDecimal njbf;
	private String gsName;
	private Integer gs = 1;
	private Integer bxnx;
	private Integer jfnx;
	private Integer jflx;
	private String zxName;
	private Integer zxId;
	private String bzName;
	private String bzId;
	private Integer xz = 1;
	private Integer age = 24;
	private Integer flag = 0;// 1==修改,0==default
	private Long planBookId;
	private Integer source;// 1=制作计划书页面的匹配，0=总监方案页面的
	private Integer userId;
	public BigDecimal getNjbf() {
		return njbf;
	}
	public void setNjbf(BigDecimal njbf) {
		this.njbf = njbf;
	}
	public String getGsName() {
		return gsName;
	}
	public void setGsName(String gsName) {
		this.gsName = gsName;
	}
	public String getZxName() {
		return zxName;
	}
	public void setZxName(String zxName) {
		this.zxName = zxName;
	}
	public Integer getZxId() {
		return zxId;
	}
	public void setZxId(Integer zxId) {
		this.zxId = zxId;
	}
	public String getBzName() {
		return bzName;
	}
	public void setBzName(String bzName) {
		this.bzName = bzName;
	}
	public String getBzId() {
		return bzId;
	}
	public void setBzId(String bzId) {
		this.bzId = bzId;
	}
	public Integer getXz() {
		return xz;
	}
	public void setXz(Integer xz) {
		this.xz = xz;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Long getPlanBookId() {
		return planBookId;
	}
	public void setPlanBookId(Long planBookId) {
		this.planBookId = planBookId;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getGs() {
		return gs;
	}
	public void setGs(Integer gs) {
		this.gs = gs;
	}
	public Integer getBxnx() {
		return bxnx;
	}
	public void setBxnx(Integer bxnx) {
		this.bxnx = bxnx;
	}
	public Integer getJfnx() {
		return jfnx;
	}
	public void setJfnx(Integer jfnx) {
		this.jfnx = jfnx;
	}
	public Integer getJflx() {
		return jflx;
	}
	public void setJflx(Integer jflx) {
		this.jflx = jflx;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}	
}
