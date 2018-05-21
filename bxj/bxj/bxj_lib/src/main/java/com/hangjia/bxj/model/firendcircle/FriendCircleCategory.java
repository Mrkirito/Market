package com.hangjia.bxj.model.firendcircle;

import java.io.Serializable;
import java.util.Date;

public class FriendCircleCategory implements Serializable {
    private Long fid;

    private String title;

    private String simtitle;

    private String words;

    private String pic;

    private Boolean isDisplay;

    private Long createUser;

    private Date createAt;

    private Long modifyUser;

    private Date modifyAt;
    
    /**
     * 路径前缀
     */
    private String filePath;

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

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words == null ? null : words.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Boolean getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Boolean isDisplay) {
        this.isDisplay = isDisplay;
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

    public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

    public String getSimtitle() {
        return simtitle;
    }

    public void setSimtitle(String simtitle) {
        this.simtitle = simtitle;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fid=").append(fid);
        sb.append(", title=").append(title);
        sb.append(", words=").append(words);
        sb.append(", pic=").append(pic);
        sb.append(", isDisplay=").append(isDisplay);
        sb.append(", createUser=").append(createUser);
        sb.append(", createAt=").append(createAt);
        sb.append(", modifyUser=").append(modifyUser);
        sb.append(", modifyAt=").append(modifyAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}