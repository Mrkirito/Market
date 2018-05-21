package com.hangjia.bxj.model;

import java.io.Serializable;
import java.util.Date;

public class ChampionUserVocherLog implements Serializable {
    private Long fid;

    private Long userId;

    private Long vocherId;

    private Long videoId;

    private Integer count;

    private Integer type;

    private Date createAt;

    private static final long serialVersionUID = 1L;

    public ChampionUserVocherLog(Long fid, Long userId, Long vocherId, Long videoId, Integer count, Integer type, Date createAt) {
        this.fid = fid;
        this.userId = userId;
        this.vocherId = vocherId;
        this.videoId = videoId;
        this.count = count;
        this.type = type;
        this.createAt = createAt;
    }

    public ChampionUserVocherLog(Long userId, Long vocherId, Long videoId) {
        this.userId = userId;
        this.vocherId = vocherId;
        this.videoId = videoId;
    }

    public ChampionUserVocherLog() {
        super();
    }

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

    public Long getVocherId() {
        return vocherId;
    }

    public void setVocherId(Long vocherId) {
        this.vocherId = vocherId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ChampionUserVocherLog other = (ChampionUserVocherLog) that;
        return (this.getFid() == null ? other.getFid() == null : this.getFid().equals(other.getFid()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getVocherId() == null ? other.getVocherId() == null : this.getVocherId().equals(other.getVocherId()))
            && (this.getVideoId() == null ? other.getVideoId() == null : this.getVideoId().equals(other.getVideoId()))
            && (this.getCount() == null ? other.getCount() == null : this.getCount().equals(other.getCount()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getCreateAt() == null ? other.getCreateAt() == null : this.getCreateAt().equals(other.getCreateAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFid() == null) ? 0 : getFid().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getVocherId() == null) ? 0 : getVocherId().hashCode());
        result = prime * result + ((getVideoId() == null) ? 0 : getVideoId().hashCode());
        result = prime * result + ((getCount() == null) ? 0 : getCount().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getCreateAt() == null) ? 0 : getCreateAt().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fid=").append(fid);
        sb.append(", userId=").append(userId);
        sb.append(", vocherId=").append(vocherId);
        sb.append(", videoId=").append(videoId);
        sb.append(", count=").append(count);
        sb.append(", type=").append(type);
        sb.append(", createAt=").append(createAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}