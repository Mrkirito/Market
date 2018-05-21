package cn.usst.market.po;

import java.util.Date;

public class Competition {
    private Integer id;

    private String name;

    private String license;

    private Integer currentQuarter;

    private Integer quarter;

    private Integer member;

    private Integer team;

    private Integer teacherId;

    private Date startTime;

    private Date endTime;

    private String status;

    private Integer isLock;

    private Float physicalRate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license == null ? null : license.trim();
    }

    public Integer getCurrentQuarter() {
        return currentQuarter;
    }

    public void setCurrentQuarter(Integer currentQuarter) {
        this.currentQuarter = currentQuarter;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }

    public Integer getMember() {
        return member;
    }

    public void setMember(Integer member) {
        this.member = member;
    }

    public Integer getTeam() {
        return team;
    }

    public void setTeam(Integer team) {
        this.team = team;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getIsLock() {
        return isLock;
    }

    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    public Float getPhysicalRate() {
        return physicalRate;
    }

    public void setPhysicalRate(Float physicalRate) {
        this.physicalRate = physicalRate;
    }
}