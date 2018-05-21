package com.hangjia.bxj.vo;

import java.io.Serializable;

/**
 * 版本信息respDto
 * Created by Administrator on 2016/5/23.
 */
public class VersionInfoRespDto implements Serializable {

    private Long id;

    private String appType;

    private Integer versionNumber;

    private String versionCode;

    private String downloadUrl;

    private String tipsContext;

    private Integer updateMethod;

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
        this.appType = appType;
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
        this.versionCode = versionCode;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getTipsContext() {
        return tipsContext;
    }

    public void setTipsContext(String tipsContext) {
        this.tipsContext = tipsContext;
    }

    public Integer getUpdateMethod() {
        return updateMethod;
    }

    public void setUpdateMethod(Integer updateMethod) {
        this.updateMethod = updateMethod;
    }
}
