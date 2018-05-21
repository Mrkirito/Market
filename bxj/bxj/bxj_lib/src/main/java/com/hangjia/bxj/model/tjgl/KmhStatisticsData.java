package com.hangjia.bxj.model.tjgl;

import java.io.Serializable;
import java.util.Date;

public class KmhStatisticsData implements Serializable {
    private Long id;

    private Date dataTime;

    private Integer browseCount;

    private Integer browseCountUv;

    private Integer shareCount;

    private Integer shareCountUv;

    private String createName;

    private Date createTime;

    private String updateName;

    private Date updateTime;

    private static final long serialVersionUID = 1L;


    public Integer getBrowseCountUv() {
        return browseCountUv;
    }

    public void setBrowseCountUv(Integer browseCountUv) {
        this.browseCountUv = browseCountUv;
    }

    public Integer getShareCountUv() {
        return shareCountUv;
    }

    public void setShareCountUv(Integer shareCountUv) {
        this.shareCountUv = shareCountUv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    public Integer getBrowseCount() {
        return browseCount;
    }

    public void setBrowseCount(Integer browseCount) {
        this.browseCount = browseCount;
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName == null ? null : updateName.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", dataTime=").append(dataTime);
        sb.append(", browseCount=").append(browseCount);
        sb.append(", shareCount=").append(shareCount);
        sb.append(", createName=").append(createName);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateName=").append(updateName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}