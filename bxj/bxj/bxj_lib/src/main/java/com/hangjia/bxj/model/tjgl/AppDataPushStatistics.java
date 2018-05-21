package com.hangjia.bxj.model.tjgl;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AppDataPushStatistics implements Serializable {
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dataTime;

    private String content;

    private String category;

    private String subCategory;

    private Long iosPushSuccessCount;

    private Long iosClickCount;

    private BigDecimal iosClickRate;

    private Long androidPushSuccessCount;

    private Long androidClickCount;

    private BigDecimal androidClickRate;

    private Long pushSuccessCount;

    private Long clickCount;

    private BigDecimal clickRate;

    private String createName;

    private Date createTime;

    private String updateName;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory == null ? null : subCategory.trim();
    }

    public Long getIosPushSuccessCount() {
        return iosPushSuccessCount;
    }

    public void setIosPushSuccessCount(Long iosPushSuccessCount) {
        this.iosPushSuccessCount = iosPushSuccessCount;
    }

    public Long getIosClickCount() {
        return iosClickCount;
    }

    public void setIosClickCount(Long iosClickCount) {
        this.iosClickCount = iosClickCount;
    }

    public BigDecimal getIosClickRate() {
        return iosClickRate;
    }

    public void setIosClickRate(BigDecimal iosClickRate) {
        this.iosClickRate = iosClickRate;
    }

    public Long getAndroidPushSuccessCount() {
        return androidPushSuccessCount;
    }

    public void setAndroidPushSuccessCount(Long androidPushSuccessCount) {
        this.androidPushSuccessCount = androidPushSuccessCount;
    }

    public Long getAndroidClickCount() {
        return androidClickCount;
    }

    public void setAndroidClickCount(Long androidClickCount) {
        this.androidClickCount = androidClickCount;
    }

    public BigDecimal getAndroidClickRate() {
        return androidClickRate;
    }

    public void setAndroidClickRate(BigDecimal androidClickRate) {
        this.androidClickRate = androidClickRate;
    }

    public Long getPushSuccessCount() {
        return pushSuccessCount;
    }

    public void setPushSuccessCount(Long pushSuccessCount) {
        this.pushSuccessCount = pushSuccessCount;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }

    public BigDecimal getClickRate() {
        return clickRate;
    }

    public void setClickRate(BigDecimal clickRate) {
        this.clickRate = clickRate;
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
        sb.append(", content=").append(content);
        sb.append(", category=").append(category);
        sb.append(", subCategory=").append(subCategory);
        sb.append(", iosPushSuccessCount=").append(iosPushSuccessCount);
        sb.append(", iosClickCount=").append(iosClickCount);
        sb.append(", iosClickRate=").append(iosClickRate);
        sb.append(", androidPushSuccessCount=").append(androidPushSuccessCount);
        sb.append(", androidClickCount=").append(androidClickCount);
        sb.append(", androidClickRate=").append(androidClickRate);
        sb.append(", pushSuccessCount=").append(pushSuccessCount);
        sb.append(", clickCount=").append(clickCount);
        sb.append(", clickRate=").append(clickRate);
        sb.append(", createName=").append(createName);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateName=").append(updateName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}