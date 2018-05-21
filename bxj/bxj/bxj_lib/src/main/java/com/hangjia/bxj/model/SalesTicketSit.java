package com.hangjia.bxj.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SalesTicketSit implements Serializable{
	private static final long serialVersionUID = -5491783436337573922L;

	private Integer fid;

    private Integer basicId;

    private String sitName;

    private BigDecimal price;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getBasicId() {
        return basicId;
    }

    public void setBasicId(Integer basicId) {
        this.basicId = basicId;
    }

    public String getSitName() {
        return sitName;
    }

    public void setSitName(String sitName) {
        this.sitName = sitName == null ? null : sitName.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
}