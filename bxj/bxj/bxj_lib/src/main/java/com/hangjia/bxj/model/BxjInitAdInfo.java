package com.hangjia.bxj.model;

import java.io.Serializable;
import java.util.Date;

public class BxjInitAdInfo implements Serializable {
    private Long fid;

    private String imgUrl;

    private Integer action;

    private Integer status;

    private String title;

    private String openUrl;

    private String bxjPay;

    private Date createTime;

    private static final long serialVersionUID = 1L;


    private Integer defaultTheme;
    private Integer defaultBannerPlay;

    public String getBxjPay() {
        return bxjPay;
    }

    public void setBxjPay(String bxjPay) {
        this.bxjPay = bxjPay;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getOpenUrl() {
        return openUrl;
    }

    public void setOpenUrl(String openUrl) {
        this.openUrl = openUrl == null ? null : openUrl.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fid=").append(fid);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", action=").append(action);
        sb.append(", status=").append(status);
        sb.append(", title=").append(title);
        sb.append(", openUrl=").append(openUrl);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Integer getDefaultTheme() {
        return defaultTheme;
    }

    public void setDefaultTheme(Integer defaultTheme) {
        this.defaultTheme = defaultTheme;
    }

    public Integer getDefaultBannerPlay() {
        return defaultBannerPlay;
    }

    public void setDefaultBannerPlay(Integer defaultBannerPlay) {
        this.defaultBannerPlay = defaultBannerPlay;
    }
}