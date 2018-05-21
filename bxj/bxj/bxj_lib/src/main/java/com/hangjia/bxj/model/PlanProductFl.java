package com.hangjia.bxj.model;

import java.math.BigDecimal;
import java.util.Date;

public class PlanProductFl {
    private Integer fid;

    private Integer pid;

    private Integer age;

    private Integer sex;

    private Integer type;

    private Integer jfnx;

    private Integer jflx;

    private Integer bxnx;
    private Integer lqnl;
    private String factor1;

    private String factor2;

    private String factor3;

    private String factor4;

    private Integer factor5;

    private Integer factor6;

    private Integer factor7;

    private Integer factor8;

    private BigDecimal bf;

    private Date ctime;

    private Long ver;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getFactor1() {
        return factor1;
    }

    public void setFactor1(String factor1) {
        this.factor1 = factor1 == null ? null : factor1.trim();
    }

    public String getFactor2() {
        return factor2;
    }

    public void setFactor2(String factor2) {
        this.factor2 = factor2 == null ? null : factor2.trim();
    }

    public String getFactor3() {
        return factor3;
    }

    public void setFactor3(String factor3) {
        this.factor3 = factor3 == null ? null : factor3.trim();
    }

    public String getFactor4() {
        return factor4;
    }

    public void setFactor4(String factor4) {
        this.factor4 = factor4 == null ? null : factor4.trim();
    }

    public Integer getFactor5() {
        return factor5;
    }

    public void setFactor5(Integer factor5) {
        this.factor5 = factor5;
    }

    public Integer getFactor6() {
        return factor6;
    }

    public void setFactor6(Integer factor6) {
        this.factor6 = factor6;
    }

    public Integer getFactor7() {
        return factor7;
    }

    public void setFactor7(Integer factor7) {
        this.factor7 = factor7;
    }

    public Integer getFactor8() {
        return factor8;
    }

    public void setFactor8(Integer factor8) {
        this.factor8 = factor8;
    }

    public BigDecimal getBf() {
        return bf;
    }

    public void setBf(BigDecimal bf) {
        this.bf = bf;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Long getVer() {
        return ver;
    }

    public void setVer(Long ver) {
        this.ver = ver;
    }

	public Integer getLqnl() {
		return lqnl;
	}

	public void setLqnl(Integer lqnl) {
		this.lqnl = lqnl;
	}   
    
}