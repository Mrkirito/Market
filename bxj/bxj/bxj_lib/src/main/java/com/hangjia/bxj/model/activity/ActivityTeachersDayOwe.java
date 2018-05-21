package com.hangjia.bxj.model.activity;

import com.hangjia.bxj.common.BaseCommonDO;
import com.hangjia.bxj.common.BaseCommonQuery;

import java.io.Serializable;
import java.util.Date;

public class ActivityTeachersDayOwe extends BaseCommonDO implements Serializable {
    private Long id;

    private Long userId;

    private String phone;

    private String oweName;

    private String oweContent;

    private String signedName;

    private String signedPhoto;

    private Integer likeNum;

    private Integer oweType;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getOweName() {
        return oweName;
    }

    public void setOweName(String oweName) {
        this.oweName = oweName == null ? null : oweName.trim();
    }

    public String getOweContent() {
        return oweContent;
    }

    public void setOweContent(String oweContent) {
        this.oweContent = oweContent == null ? null : oweContent.trim();
    }

    public String getSignedName() {
        return signedName;
    }

    public void setSignedName(String signedName) {
        this.signedName = signedName == null ? null : signedName.trim();
    }

    public String getSignedPhoto() {
        return signedPhoto;
    }

    public void setSignedPhoto(String signedPhoto) {
        this.signedPhoto = signedPhoto == null ? null : signedPhoto.trim();
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getOweType() {
        return oweType;
    }

    public void setOweType(Integer oweType) {
        this.oweType = oweType;
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
        sb.append(", userId=").append(userId);
        sb.append(", phone=").append(phone);
        sb.append(", oweName=").append(oweName);
        sb.append(", oweContent=").append(oweContent);
        sb.append(", signedName=").append(signedName);
        sb.append(", signedPhoto=").append(signedPhoto);
        sb.append(", likeNum=").append(likeNum);
        sb.append(", oweType=").append(oweType);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}