package com.hangjia.bxj.vo;

import java.io.Serializable;

/**
 * 保保网络科技-bxj
 * com.hangjia.bxj.vo
 * 作者-秦岭(Tain)
 * 说明：
 * 2016/10/20 11:43
 * 2016保保网络-版权所有
 */
public class FriendCircleApiDto implements Serializable {

    private Integer fid;

    private String title;

    private String imageUrl;

    private String pageUrl;

    private Integer type;

    private Integer sort;


    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
