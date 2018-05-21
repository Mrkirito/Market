package com.hangjia.bxj.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ShopGoods implements Serializable {
    private Long id;

    private String title;

    private String name;

    private String shortName;

    private String outHtmlUrl;

    private String unit;

    private BigDecimal weight;

    private Integer status;

    private Boolean isOnline;

    private Boolean isLimitPurchase;

    private String picture;

    private BigDecimal price1;

    private BigDecimal price2;

    private BigDecimal price3;

    private Integer maxBuyCount;

    private Integer saleCount;

    private Integer falseSaleCount;

    private Integer maxSaleCount;

    private String guideSharingWord;

    private String shareTitle;

    private String shareDesc;

    private String sharePic;

    private String shareUrl;

    private Date createTime;

    private Long createUser;

    private Date updateTime;

    private Long updateUesr;

    private Integer minBuyCount;

    private Integer hasMark;

    private String markText;

    private Integer sort;

    private Integer head;

    private String picture2;

    private String description;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public String getOutHtmlUrl() {
        return outHtmlUrl;
    }

    public void setOutHtmlUrl(String outHtmlUrl) {
        this.outHtmlUrl = outHtmlUrl == null ? null : outHtmlUrl.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Boolean isOnline) {
        this.isOnline = isOnline;
    }

    public Boolean getIsLimitPurchase() {
        return isLimitPurchase;
    }

    public void setIsLimitPurchase(Boolean isLimitPurchase) {
        this.isLimitPurchase = isLimitPurchase;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public BigDecimal getPrice1() {
        return price1;
    }

    public void setPrice1(BigDecimal price1) {
        this.price1 = price1;
    }

    public BigDecimal getPrice2() {
        return price2;
    }

    public void setPrice2(BigDecimal price2) {
        this.price2 = price2;
    }

    public BigDecimal getPrice3() {
        return price3;
    }

    public void setPrice3(BigDecimal price3) {
        this.price3 = price3;
    }

    public Integer getMaxBuyCount() {
        return maxBuyCount;
    }

    public void setMaxBuyCount(Integer maxBuyCount) {
        this.maxBuyCount = maxBuyCount;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Integer getFalseSaleCount() {
        return falseSaleCount;
    }

    public void setFalseSaleCount(Integer falseSaleCount) {
        this.falseSaleCount = falseSaleCount;
    }

    public Integer getMaxSaleCount() {
        return maxSaleCount;
    }

    public void setMaxSaleCount(Integer maxSaleCount) {
        this.maxSaleCount = maxSaleCount;
    }

    public String getGuideSharingWord() {
        return guideSharingWord;
    }

    public void setGuideSharingWord(String guideSharingWord) {
        this.guideSharingWord = guideSharingWord == null ? null : guideSharingWord.trim();
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle == null ? null : shareTitle.trim();
    }

    public String getShareDesc() {
        return shareDesc;
    }

    public void setShareDesc(String shareDesc) {
        this.shareDesc = shareDesc == null ? null : shareDesc.trim();
    }

    public String getSharePic() {
        return sharePic;
    }

    public void setSharePic(String sharePic) {
        this.sharePic = sharePic == null ? null : sharePic.trim();
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl == null ? null : shareUrl.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUesr() {
        return updateUesr;
    }

    public void setUpdateUesr(Long updateUesr) {
        this.updateUesr = updateUesr;
    }

    public Integer getMinBuyCount() {
        return minBuyCount;
    }

    public void setMinBuyCount(Integer minBuyCount) {
        this.minBuyCount = minBuyCount;
    }

    public Integer getHasMark() {
        return hasMark;
    }

    public void setHasMark(Integer hasMark) {
        this.hasMark = hasMark;
    }

    public String getMarkText() {
        return markText;
    }

    public void setMarkText(String markText) {
        this.markText = markText == null ? null : markText.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getHead() {
        return head;
    }

    public void setHead(Integer head) {
        this.head = head;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2 == null ? null : picture2.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", name=").append(name);
        sb.append(", shortName=").append(shortName);
        sb.append(", outHtmlUrl=").append(outHtmlUrl);
        sb.append(", unit=").append(unit);
        sb.append(", weight=").append(weight);
        sb.append(", status=").append(status);
        sb.append(", isOnline=").append(isOnline);
        sb.append(", isLimitPurchase=").append(isLimitPurchase);
        sb.append(", picture=").append(picture);
        sb.append(", price1=").append(price1);
        sb.append(", price2=").append(price2);
        sb.append(", price3=").append(price3);
        sb.append(", maxBuyCount=").append(maxBuyCount);
        sb.append(", saleCount=").append(saleCount);
        sb.append(", falseSaleCount=").append(falseSaleCount);
        sb.append(", maxSaleCount=").append(maxSaleCount);
        sb.append(", guideSharingWord=").append(guideSharingWord);
        sb.append(", shareTitle=").append(shareTitle);
        sb.append(", shareDesc=").append(shareDesc);
        sb.append(", sharePic=").append(sharePic);
        sb.append(", shareUrl=").append(shareUrl);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateUesr=").append(updateUesr);
        sb.append(", minBuyCount=").append(minBuyCount);
        sb.append(", hasMark=").append(hasMark);
        sb.append(", markText=").append(markText);
        sb.append(", sort=").append(sort);
        sb.append(", head=").append(head);
        sb.append(", picture2=").append(picture2);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**************************** extend begin here *******************************/
    /** 商品图片完整路径 **/
    private String picturePath;
    private String picture2Path;
    /** 分享图片完整路径 **/
    private String sharePicPath;

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String path) {
        this.picturePath = path + this.picture;
    }

    public String getSharePicPath() {
        return sharePicPath;
    }

    public void setSharePicPath(String path) {
        this.sharePicPath = path + this.sharePic;
    }

    public String getPicture2Path() {
        return picture2Path;
    }

    public void setPicture2Path(String path) {
        this.picture2Path = path + this.picture2;
    }
}