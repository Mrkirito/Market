package com.hangjia.bxj.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SalesTicketBasic implements Serializable{
	private static final long serialVersionUID = -241537476428640132L;

	private Integer fid;

    private String activityName;

    private String descripe;

    private String address;

    private Integer state;

    private Date beginTime;

    private Date endTime;

    private Date salesTime;

    private Date overTime;

    private String imgUrl;

    private Integer peoples;

    private Integer mimNum;

    private Integer maxNum;

    private Integer underway;

    private String note;

    private Date createTime;

    private Date updateTime;

    private Integer prensentNum;
    
    private String salesmanTel;
    
    private List<SalesTicketSit> salesTicketSits;
    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public String getDescripe() {
        return descripe;
    }

    public void setDescripe(String descripe) {
        this.descripe = descripe == null ? null : descripe.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getSalesTime() {
        return salesTime;
    }

    public void setSalesTime(Date salesTime) {
        this.salesTime = salesTime;
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public Integer getPeoples() {
        return peoples;
    }

    public void setPeoples(Integer peoples) {
        this.peoples = peoples;
    }

    public Integer getMimNum() {
        return mimNum;
    }

    public void setMimNum(Integer mimNum) {
        this.mimNum = mimNum;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public Integer getUnderway() {
        return underway;
    }

    public void setUnderway(Integer underway) {
        this.underway = underway;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
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

	public List<SalesTicketSit> getSalesTicketSits() {
		return salesTicketSits;
	}

	public void setSalesTicketSits(List<SalesTicketSit> salesTicketSits) {
		this.salesTicketSits = salesTicketSits;
	}

	public Integer getPrensentNum() {
		return prensentNum;
	}

	public void setPrensentNum(Integer prensentNum) {
		this.prensentNum = prensentNum;
	}

	public String getSalesmanTel() {
		return salesmanTel;
	}

	public void setSalesmanTel(String salesmanTel) {
		this.salesmanTel = salesmanTel;
	}
    
}