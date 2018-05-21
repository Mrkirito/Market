package com.hangjia.bxj.query.study;

import com.hangjia.bxj.common.BaseCommonQuery;

/**
 * Created by xiongfangyong on 2016/10/8.
 */
public class Study3CommentQuery extends BaseCommonQuery {
    private String articleTitle;
    private String content;

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
