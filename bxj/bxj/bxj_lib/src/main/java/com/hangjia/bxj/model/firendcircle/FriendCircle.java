package com.hangjia.bxj.model.firendcircle;

import com.hangjia.bxj.model.BaseModel;

import java.util.Date;

public class FriendCircle extends BaseModel {
    private Long fid;

    private Long categoryId;

    private String company;

    private Long pfid;

    private Integer type;

    private Integer status;

    private Date publishTime;

    private String source;

    private String words;

    private String title;

    private Integer sort;

    private Integer shareCount;

    private Integer shareCountReal;

    private Integer weekShareCount;

    private Integer weekShareCountReal;

    private Integer likeCount;

    private String pic1;

    private String pic2;

    private String pic3;

    private String pic4;

    private String pic5;

    private String pic6;

    private String pic7;

    private String pic8;

    private String pic9;

    private Integer width;

    private Integer height;

    private Date createTime;

    private Long createUser;

    private Date updateTime;

    private Long udpateUser;

    private String content;

    private static final long serialVersionUID = 1L;

    public Long getPfid() {
        return pfid;
    }

    public void setPfid(Long pfid) {
        this.pfid = pfid;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words == null ? null : words.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public Integer getShareCountReal() {
        return shareCountReal;
    }

    public void setShareCountReal(Integer shareCountReal) {
        this.shareCountReal = shareCountReal;
    }

    public Integer getWeekShareCount() {
        return weekShareCount;
    }

    public void setWeekShareCount(Integer weekShareCount) {
        this.weekShareCount = weekShareCount;
    }

    public Integer getWeekShareCountReal() {
        return weekShareCountReal;
    }

    public void setWeekShareCountReal(Integer weekShareCountReal) {
        this.weekShareCountReal = weekShareCountReal;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1 == null ? null : pic1.trim();
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2 == null ? null : pic2.trim();
    }

    public String getPic3() {
        return pic3;
    }

    public void setPic3(String pic3) {
        this.pic3 = pic3 == null ? null : pic3.trim();
    }

    public String getPic4() {
        return pic4;
    }

    public void setPic4(String pic4) {
        this.pic4 = pic4 == null ? null : pic4.trim();
    }

    public String getPic5() {
        return pic5;
    }

    public void setPic5(String pic5) {
        this.pic5 = pic5 == null ? null : pic5.trim();
    }

    public String getPic6() {
        return pic6;
    }

    public void setPic6(String pic6) {
        this.pic6 = pic6 == null ? null : pic6.trim();
    }

    public String getPic7() {
        return pic7;
    }

    public void setPic7(String pic7) {
        this.pic7 = pic7 == null ? null : pic7.trim();
    }

    public String getPic8() {
        return pic8;
    }

    public void setPic8(String pic8) {
        this.pic8 = pic8 == null ? null : pic8.trim();
    }

    public String getPic9() {
        return pic9;
    }

    public void setPic9(String pic9) {
        this.pic9 = pic9 == null ? null : pic9.trim();
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
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

    public Long getUdpateUser() {
        return udpateUser;
    }

    public void setUdpateUser(Long udpateUser) {
        this.udpateUser = udpateUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fid=").append(fid);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", publishTime=").append(publishTime);
        sb.append(", source=").append(source);
        sb.append(", words=").append(words);
        sb.append(", title=").append(title);
        sb.append(", sort=").append(sort);
        sb.append(", shareCount=").append(shareCount);
        sb.append(", shareCountReal=").append(shareCountReal);
        sb.append(", weekShareCount=").append(weekShareCount);
        sb.append(", weekShareCountReal=").append(weekShareCountReal);
        sb.append(", likeCount=").append(likeCount);
        sb.append(", pic1=").append(pic1);
        sb.append(", pic2=").append(pic2);
        sb.append(", pic3=").append(pic3);
        sb.append(", pic4=").append(pic4);
        sb.append(", pic5=").append(pic5);
        sb.append(", pic6=").append(pic6);
        sb.append(", pic7=").append(pic7);
        sb.append(", pic8=").append(pic8);
        sb.append(", pic9=").append(pic9);
        sb.append(", width=").append(width);
        sb.append(", height=").append(height);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", udpateUser=").append(udpateUser);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**************************** extend begin here *******************************/
    private String simtitle;
    /**保存路径前缀**/
    private String filePath;
    private String categoryTitle;
    private String categoryWord;
    private String categoryPic;

    public String getSimtitle() {
        return simtitle;
    }

    public void setSimtitle(String simtitle) {
        this.simtitle = simtitle;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryWord() {
        return categoryWord;
    }

    public void setCategoryWord(String categoryWord) {
        this.categoryWord = categoryWord;
    }

    public String getCategoryPic() {
        return categoryPic;
    }

    public void setCategoryPic(String categoryPic) {
        this.categoryPic = categoryPic;
    }
}