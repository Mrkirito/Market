package com.hangjia.bxj.model;

import java.math.BigDecimal;
/**
 * 入参参数
 * @author user
 *
 */
public class PlanBookVo {
	//总监方案  
	private Integer gender = 1;
	private BigDecimal njbf;
	private String gsName;
	private Integer gsId = 1;
	private String zxName;
	private Integer zxId;
	private String bzName;
	private String bzId;
	private Integer xz = 1;
	private Integer age = 24;
	private Integer flag = 0;//1==修改,0==default
	
	//查询出来的计划书主键
	private Long planBookId;//计划书主键
	private Integer pid;
	private Integer jfnx;
	private Integer jflx;
	private Integer bxnx;
	
	//做计划书 查询
	private String pname;// 产品名称
	private Integer pageStart = 0;// 分页 起始
	private Integer pageEnd = 10;// 分页 截至
	
	//生成计划书
	private Integer plan;//是否参考总监方案
	private String author;
	private String rels;
	private String sonRels;
	private Integer userId;
	private BigDecimal cpbe;
	
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
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
	public Integer getGsId() {
		return gsId;
	}
	public void setGsId(Integer gsId) {
		this.gsId = gsId;
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
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
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
	public Integer getBxnx() {
		return bxnx;
	}
	public void setBxnx(Integer bxnx) {
		this.bxnx = bxnx;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Integer getPageStart() {
		return pageStart;
	}
	public void setPageStart(Integer pageStart) {
		this.pageStart = pageStart;
	}
	public Integer getPageEnd() {
		return pageEnd;
	}
	public void setPageEnd(Integer pageEnd) {
		this.pageEnd = pageEnd;
	}
	public Integer getPlan() {
		return plan;
	}
	public void setPlan(Integer plan) {
		this.plan = plan;
	}
	public String getRels() {
		return rels;
	}
	public void setRels(String rels) {
		this.rels = rels;
	}
	public String getSonRels() {
		return sonRels;
	}
	public void setSonRels(String sonRels) {
		this.sonRels = sonRels;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public BigDecimal getCpbe() {
		return cpbe;
	}
	public void setCpbe(BigDecimal cpbe) {
		this.cpbe = cpbe;
	}
	
}
