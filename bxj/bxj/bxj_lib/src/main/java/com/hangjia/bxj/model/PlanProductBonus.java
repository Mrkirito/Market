package com.hangjia.bxj.model;

public class PlanProductBonus {
    private Long fid;

    private Integer pid;

    private Integer sex;

    private Integer age;

    private Integer jfnx;

    private Integer bxnx;

    private Integer lqnl;

    private Integer factor5;

    private String profit;

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

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

    public Integer getJfnx() {
        return jfnx;
    }

    public void setJfnx(Integer jfnx) {
        this.jfnx = jfnx;
    }

    public Integer getBxnx() {
        return bxnx;
    }

    public void setBxnx(Integer bxnx) {
        this.bxnx = bxnx;
    }

    public Integer getLqnl() {
        return lqnl;
    }

    public void setLqnl(Integer lqnl) {
        this.lqnl = lqnl;
    }

    public Integer getFactor5() {
        return factor5;
    }

    public void setFactor5(Integer factor5) {
        this.factor5 = factor5;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit == null ? null : profit.trim();
    }
}