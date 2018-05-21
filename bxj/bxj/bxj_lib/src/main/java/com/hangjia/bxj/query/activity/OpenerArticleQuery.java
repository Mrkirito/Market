package com.hangjia.bxj.query.activity;

import com.hangjia.bxj.common.BaseCommonQuery;

import java.util.Date;

/**
 * 开门红文章 后台查询
 *
 * @ClassName:
 * @Description:
 * @author: bei.zhang
 * @date: 2016年11月23日 上午11:40:38
 */

public class OpenerArticleQuery extends BaseCommonQuery {

    private static final long serialVersionUID = -7468621596343710116L;

    private String title;

    private Long typeId;

    private Integer showStatus;

    private Date publishDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
}
