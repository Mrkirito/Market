	package com.hangjia.bxj.vo;

import java.math.BigDecimal;
/**
 * 插入计划书
 * @author user
 *
 */
public class InsertPlanBookVo {
	private Integer sex;       //性别
	private Integer age;       //年龄 
	private Integer gs;		   //公司 
	private BigDecimal totalBf;//总保费
	private Integer zxId;      //主险产品ID
	private String bookName;   //主险产品Name  	
	private String author;     //收件人信息  
	private String proRels;	   //pid_jflx_jfnx/jflx_bf_be_father
	private BigDecimal njbf;   //预计年缴保费
	private Integer userId;	   //当前登陆的用户Id
	private Integer kchs;	   //当前计划书开场话术
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getProRels() {
		return proRels;
	}
	public void setProRels(String proRels) {
		this.proRels = proRels;
	}
	public BigDecimal getNjbf() {
		return njbf;
	}
	public void setNjbf(BigDecimal njbf) {
		this.njbf = njbf;
	}
	public Integer getGs() {
		return gs;
	}
	public void setGs(Integer gs) {
		this.gs = gs;
	}
	public BigDecimal getTotalBf() {
		return totalBf;
	}
	public void setTotalBf(BigDecimal totalBf) {
		this.totalBf = totalBf;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getZxId() {
		return zxId;
	}
	public void setZxId(Integer zxId) {
		this.zxId = zxId;
	}
	public Integer getKchs() {
		return kchs;
	}
	public void setKchs(Integer kchs) {
		this.kchs = kchs;
	}
}
