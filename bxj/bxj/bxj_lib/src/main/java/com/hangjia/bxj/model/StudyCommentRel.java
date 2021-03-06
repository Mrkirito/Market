package com.hangjia.bxj.model;

import java.io.Serializable;
import java.util.Date;

public class StudyCommentRel implements Serializable {
    private Long id;

    private Long commentId;

    private Long userId;

    private Boolean isGood;

    private Date createAt;

    private Date cancelAt;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getIsGood() {
        return isGood;
    }

    public void setIsGood(Boolean isGood) {
        this.isGood = isGood;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getCancelAt() {
        return cancelAt;
    }

    public void setCancelAt(Date cancelAt) {
        this.cancelAt = cancelAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", commentId=").append(commentId);
        sb.append(", userId=").append(userId);
        sb.append(", isGood=").append(isGood);
        sb.append(", createAt=").append(createAt);
        sb.append(", cancelAt=").append(cancelAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}