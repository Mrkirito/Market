package com.hangjia.bxj.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public class FriendCircleIndexVo implements Serializable {
    private String dateTitle;
    private List<FriendCircleVo> list;

    public String getDateTitle() {
        return dateTitle;
    }

    public void setDateTitle(String dateTitle) {
        this.dateTitle = dateTitle;
    }

    public List<FriendCircleVo> getList() {
        return list;
    }

    public void setList(List<FriendCircleVo> list) {
        this.list = list;
    }
}
