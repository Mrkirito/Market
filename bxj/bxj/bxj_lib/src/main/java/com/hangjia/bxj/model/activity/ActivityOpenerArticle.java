package com.hangjia.bxj.model.activity;

import java.io.Serializable;
import java.util.Date;

public class ActivityOpenerArticle implements Serializable {
    private Long id;

    private Long typeId;

    private String title;

    private String subtitle;

    private String iconUrl;

    private Long productId;

    private String articleSource;

    private Date publishDate;

    private Integer browseCountReal;

    private Integer browseCountVirtual;

    private Integer shareCountReal;

    private Integer shareCountVirtual;

    private Integer makeCountReal;

    private Integer makeCountVirtual;

    private Integer showNewStatus;

    private Integer showHotStatus;

    private Integer showStatus;

    private Integer sort;

    private Date createTime;

    private Date updateTime;

    private String articleContent;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle == null ? null : subtitle.trim();
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl == null ? null : iconUrl.trim();
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getArticleSource() {
        return articleSource;
    }

    public void setArticleSource(String articleSource) {
        this.articleSource = articleSource == null ? null : articleSource.trim();
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getBrowseCountReal() {
        return browseCountReal;
    }

    public void setBrowseCountReal(Integer browseCountReal) {
        this.browseCountReal = browseCountReal;
    }

    public Integer getBrowseCountVirtual() {
        return browseCountVirtual;
    }

    public void setBrowseCountVirtual(Integer browseCountVirtual) {
        this.browseCountVirtual = browseCountVirtual;
    }

    public Integer getShareCountReal() {
        return shareCountReal;
    }

    public void setShareCountReal(Integer shareCountReal) {
        this.shareCountReal = shareCountReal;
    }

    public Integer getShareCountVirtual() {
        return shareCountVirtual;
    }

    public void setShareCountVirtual(Integer shareCountVirtual) {
        this.shareCountVirtual = shareCountVirtual;
    }

    public Integer getMakeCountReal() {
        return makeCountReal;
    }

    public void setMakeCountReal(Integer makeCountReal) {
        this.makeCountReal = makeCountReal;
    }

    public Integer getMakeCountVirtual() {
        return makeCountVirtual;
    }

    public void setMakeCountVirtual(Integer makeCountVirtual) {
        this.makeCountVirtual = makeCountVirtual;
    }

    public Integer getShowNewStatus() {
        return showNewStatus;
    }

    public void setShowNewStatus(Integer showNewStatus) {
        this.showNewStatus = showNewStatus;
    }

    public Integer getShowHotStatus() {
        return showHotStatus;
    }

    public void setShowHotStatus(Integer showHotStatus) {
        this.showHotStatus = showHotStatus;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", typeId=").append(typeId);
        sb.append(", title=").append(title);
        sb.append(", subtitle=").append(subtitle);
        sb.append(", iconUrl=").append(iconUrl);
        sb.append(", productId=").append(productId);
        sb.append(", articleSource=").append(articleSource);
        sb.append(", publishDate=").append(publishDate);
        sb.append(", browseCountReal=").append(browseCountReal);
        sb.append(", browseCountVirtual=").append(browseCountVirtual);
        sb.append(", shareCountReal=").append(shareCountReal);
        sb.append(", shareCountVirtual=").append(shareCountVirtual);
        sb.append(", makeCountReal=").append(makeCountReal);
        sb.append(", makeCountVirtual=").append(makeCountVirtual);
        sb.append(", showNewStatus=").append(showNewStatus);
        sb.append(", showHotStatus=").append(showHotStatus);
        sb.append(", showStatus=").append(showStatus);
        sb.append(", sort=").append(sort);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", articleContent=").append(articleContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}