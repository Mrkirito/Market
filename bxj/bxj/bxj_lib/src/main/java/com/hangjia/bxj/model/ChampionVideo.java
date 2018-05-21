package com.hangjia.bxj.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

public class ChampionVideo extends BaseModel implements Serializable {
    private Long fid;

    private String title;

    private Long channelId;

    private Long lecturerId;

    private String lecturerName;

    private String pageUrl;

    private Integer videoSize;

    private String videoUrl;

    private String coverImageUrl;

    private Integer auditStatus;

    private Boolean isRecommend;

    private Boolean isUnicast;

    private Integer browseNum;

    private Boolean playType;

    private String durationTime;

    private Date uploadTime;

    private Date onlineTime;

    private Integer onlineStatus;

    private Date offlineTime;

    private Integer playCount;

    private Boolean isFalseCount;

    private Integer falseCount;

    private Integer shareCount;

    private Integer collectionCount;

    private Long voucherId;

    private Integer voucherCount;

    private BigDecimal money;

    private Integer watchTime;

    private String description;

    private Integer videoType;

    private String qiniuId;

    private Integer status;

    private Integer sort;

    private Long createUser;

    private Date createAt;

    private Long modifyUser;

    private Date modifyAt;

    private Long auditUser;

    private Date auditAt;

    private static final long serialVersionUID = 1L;

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Long getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Long lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName == null ? null : lecturerName.trim();
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl == null ? null : pageUrl.trim();
    }

    public Integer getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(Integer videoSize) {
        this.videoSize = videoSize;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? null : videoUrl.trim();
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl == null ? null : coverImageUrl.trim();
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Boolean getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Boolean isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Boolean getIsUnicast() {
        return isUnicast;
    }

    public void setIsUnicast(Boolean isUnicast) {
        this.isUnicast = isUnicast;
    }

    public Integer getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    public Boolean getPlayType() {
        return playType;
    }

    public void setPlayType(Boolean playType) {
        this.playType = playType;
    }

    public String getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(String durationTime) {
        this.durationTime = durationTime == null ? null : durationTime.trim();
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Integer getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Integer onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public Date getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Date offlineTime) {
        this.offlineTime = offlineTime;
    }

    public Integer getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }

    public Boolean getIsFalseCount() {
        return isFalseCount;
    }

    public void setIsFalseCount(Boolean isFalseCount) {
        this.isFalseCount = isFalseCount;
    }

    public Integer getFalseCount() {
        return falseCount;
    }

    public void setFalseCount(Integer falseCount) {
        this.falseCount = falseCount;
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public Integer getCollectionCount() {
        return collectionCount;
    }

    public void setCollectionCount(Integer collectionCount) {
        this.collectionCount = collectionCount;
    }

    public Long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }

    public Integer getVoucherCount() {
        return voucherCount;
    }

    public void setVoucherCount(Integer voucherCount) {
        this.voucherCount = voucherCount;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getWatchTime() {
        return watchTime;
    }

    public void setWatchTime(Integer watchTime) {
        this.watchTime = watchTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getVideoType() {
        return videoType;
    }

    public void setVideoType(Integer videoType) {
        this.videoType = videoType;
    }

    public String getQiniuId() {
        return qiniuId;
    }

    public void setQiniuId(String qiniuId) {
        this.qiniuId = qiniuId == null ? null : qiniuId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Long getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(Long modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Date getModifyAt() {
        return modifyAt;
    }

    public void setModifyAt(Date modifyAt) {
        this.modifyAt = modifyAt;
    }

    public Long getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(Long auditUser) {
        this.auditUser = auditUser;
    }

    public Date getAuditAt() {
        return auditAt;
    }

    public void setAuditAt(Date auditAt) {
        this.auditAt = auditAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fid=").append(fid);
        sb.append(", title=").append(title);
        sb.append(", channelId=").append(channelId);
        sb.append(", lecturerId=").append(lecturerId);
        sb.append(", lecturerName=").append(lecturerName);
        sb.append(", pageUrl=").append(pageUrl);
        sb.append(", videoSize=").append(videoSize);
        sb.append(", videoUrl=").append(videoUrl);
        sb.append(", coverImageUrl=").append(coverImageUrl);
        sb.append(", auditStatus=").append(auditStatus);
        sb.append(", isRecommend=").append(isRecommend);
        sb.append(", isUnicast=").append(isUnicast);
        sb.append(", browseNum=").append(browseNum);
        sb.append(", playType=").append(playType);
        sb.append(", durationTime=").append(durationTime);
        sb.append(", uploadTime=").append(uploadTime);
        sb.append(", onlineTime=").append(onlineTime);
        sb.append(", onlineStatus=").append(onlineStatus);
        sb.append(", offlineTime=").append(offlineTime);
        sb.append(", playCount=").append(playCount);
        sb.append(", isFalseCount=").append(isFalseCount);
        sb.append(", falseCount=").append(falseCount);
        sb.append(", shareCount=").append(shareCount);
        sb.append(", collectionCount=").append(collectionCount);
        sb.append(", voucherId=").append(voucherId);
        sb.append(", voucherCount=").append(voucherCount);
        sb.append(", money=").append(money);
        sb.append(", watchTime=").append(watchTime);
        sb.append(", description=").append(description);
        sb.append(", videoType=").append(videoType);
        sb.append(", qiniuId=").append(qiniuId);
        sb.append(", status=").append(status);
        sb.append(", sort=").append(sort);
        sb.append(", createUser=").append(createUser);
        sb.append(", createAt=").append(createAt);
        sb.append(", modifyUser=").append(modifyUser);
        sb.append(", modifyAt=").append(modifyAt);
        sb.append(", auditUser=").append(auditUser);
        sb.append(", auditAt=").append(auditAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    private String channelTitle;

    private String coverFileUrl; // 封面图片实际地址

    private String channelName; // 分类名称

    private String videoFileUrl; // 音/视频实际地址

    private List<ChampionTag> tagList; // 所有标签分类

    private String tagName; // 所有标签名称

    private BigDecimal size;

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getCoverFileUrl() {
        return coverFileUrl;
    }

    public void setCoverFileUrl(String coverFileUrl) {
        this.coverFileUrl = coverFileUrl;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getVideoFileUrl() {
        return videoFileUrl;
    }

    public void setVideoFileUrl(String videoFileUrl) {
        this.videoFileUrl = videoFileUrl;
    }

    public List<ChampionTag> getTagList() {
        return tagList;
    }

    public void setTagList(List<ChampionTag> tagList) {
        this.tagList = tagList;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize() {
        this.size = new BigDecimal(this.videoSize).divide(new BigDecimal(1024*1024), 2, RoundingMode.HALF_UP);
    }

    public ChampionVideo  getChampionVideo(String bxjPath, String qiniuPath, String staticPath) {
        this.setPageUrl(bxjPath + "/" + this.getPageUrl());
        if(null!=this.getVideoUrl()) {
            /** 音频 **/
            if (this.getVideoUrl().contains(".mp3")) this.setVideoUrl(staticPath + "/" + this.getVideoUrl());
            else this.setVideoUrl(qiniuPath + "/" + this.getVideoUrl());
        }
        //this.setVideoUrl(qiniuPath + "/" + this.getVideoUrl());
        this.setCoverImageUrl(staticPath + "/" + this.getCoverImageUrl());
        return this;
    }
}