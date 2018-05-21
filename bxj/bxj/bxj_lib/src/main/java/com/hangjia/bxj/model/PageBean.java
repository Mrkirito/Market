package com.hangjia.bxj.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/4/6.
 */
public class PageBean  implements Serializable {
    public static int defaultSize = 1;
    public static int defaultIndex = 1;
    private int size;
    private int index;
    private int count;
    private int totalPage;
    private Object list;
    private String action;

    public PageBean() {}

    public PageBean(int size, int index, int count, Object list) {
        this.size = size;
        this.index = index;
        this.count = count;
        this.list = list;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
