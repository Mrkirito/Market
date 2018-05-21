package com.hangjia.bxj.model;

import java.util.Date;

public class SalesTicketDocument {
    private Long fid;

    private Integer basicId;

    private Integer sitId;

    private Integer state;

    private String floor;

    private String area;

    private String rows;

    private String number;

    private Integer ticketNo;

    private Date salesTime;

    private Date lockTime;

    private Date createTime;

    private Date updateTime;

    private Integer version;
    
    private String activityName;
    
    private Integer stateBak;
    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public Integer getBasicId() {
        return basicId;
    }

    public void setBasicId(Integer basicId) {
        this.basicId = basicId;
    }

    public Integer getSitId() {
        return sitId;
    }

    public void setSitId(Integer sitId) {
        this.sitId = sitId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor == null ? null : floor.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows == null ? null : rows.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Integer getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(Integer ticketNo) {
        this.ticketNo = ticketNo;
    }

    public Date getSalesTime() {
        return salesTime;
    }

    public void setSalesTime(Date salesTime) {
        this.salesTime = salesTime;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Integer getStateBak() {
		return stateBak;
	}

	public void setStateBak(Integer stateBak) {
		this.stateBak = stateBak;
	}
     
}