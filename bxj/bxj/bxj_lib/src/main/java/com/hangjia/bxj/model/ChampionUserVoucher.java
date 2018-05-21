package com.hangjia.bxj.model;

import java.util.Date;

public class ChampionUserVoucher {
    private Long fid;

    private Long userId;

    private Long voucherId;

    private Integer total;

    private Integer getAllcounts;

    private Integer useAllcounts;

    private Date createTime;

    private Date updateTime;

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getGetAllcounts() {
        return getAllcounts;
    }

    public void setGetAllcounts(Integer getAllcounts) {
        this.getAllcounts = getAllcounts;
    }

    public Integer getUseAllcounts() {
        return useAllcounts;
    }

    public void setUseAllcounts(Integer useAllcounts) {
        this.useAllcounts = useAllcounts;
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