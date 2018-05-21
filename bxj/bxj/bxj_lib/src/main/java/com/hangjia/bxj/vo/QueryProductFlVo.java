package com.hangjia.bxj.vo;
public class QueryProductFlVo {
	private Integer pid;
	private Integer age;
	private Integer sex;
	private Integer bxnx;
	private Integer type;
	private Integer selectval;
	private String  pids;//批量PID
	private String  flxs;//费率类型
	private String  jlxs;//实际费率类型的值
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getBxnx() {
		return bxnx;
	}
	public void setBxnx(Integer bxnx) {
		this.bxnx = bxnx;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getSelectval() {
		return selectval;
	}
	public void setSelectval(Integer selectval) {
		this.selectval = selectval;
	}
	public String getPids() {
		return pids;
	}
	public void setPids(String pids) {
		this.pids = pids;
	}
	public String getFlxs() {
		return flxs;
	}
	public void setFlxs(String flxs) {
		this.flxs = flxs;
	}
	public String getJlxs() {
		return jlxs;
	}
	public void setJlxs(String jlxs) {
		this.jlxs = jlxs;
	}	
}
