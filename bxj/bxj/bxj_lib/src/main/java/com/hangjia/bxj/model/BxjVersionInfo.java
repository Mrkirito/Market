package com.hangjia.bxj.model;

import java.io.Serializable;
import java.util.Date;

public class BxjVersionInfo extends BaseModel implements Serializable {
    private Long id;

    private String appType;

    private Integer versionNumber;

    private String versionCode;

    private String downloadUrl;

    private String tipsContext;

    private Integer updateMethod;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType == null ? null : appType.trim();
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode == null ? null : versionCode.trim();
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl == null ? null : downloadUrl.trim();
    }

    public String getTipsContext() {
        return tipsContext;
    }

    public void setTipsContext(String tipsContext) {
        this.tipsContext = tipsContext == null ? null : tipsContext.trim();
    }

    public Integer getUpdateMethod() {
        return updateMethod;
    }

    public void setUpdateMethod(Integer updateMethod) {
        this.updateMethod = updateMethod;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", appType=").append(appType);
        sb.append(", versionNumber=").append(versionNumber);
        sb.append(", versionCode=").append(versionCode);
        sb.append(", downloadUrl=").append(downloadUrl);
        sb.append(", tipsContext=").append(tipsContext);
        sb.append(", updateMethod=").append(updateMethod);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}