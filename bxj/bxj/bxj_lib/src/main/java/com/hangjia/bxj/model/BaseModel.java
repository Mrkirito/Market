package com.hangjia.bxj.model;

import java.util.ArrayList;
import java.util.List;

import com.hangjia.bxj.vo.OrderData;

/**
 * @ClassName: BaseModel
 * @Description: model基类
 * @author: bei.zhang
 * @date: 2016年4月12日 上午10:12:19
 */
public abstract class BaseModel {

    private static final int PAGESIZE = 15;

    private Integer currPage; // 当前页
    private Integer pageSize; // 每页大小
    private Integer startIndex;
    private Integer endIndex;
    private List<OrderData> orderDatas; // 排序字段

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize <= 0) {
            this.pageSize = PAGESIZE;
        } else {
            this.pageSize = pageSize;
        }
    }

    public Integer getStartIndex() {
        if (currPage != null && pageSize != null && currPage > 0 && pageSize > 0) {
            return (currPage - 1) * pageSize;
        }
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getEndIndex() {
        if (currPage != null && pageSize != null && currPage > 0 && pageSize > 0) {
            return currPage * pageSize;
        }
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public List<OrderData> getOrderDatas() {
        return orderDatas;
    }

    public void setOrderDatas(List<OrderData> orderDatas) {
        this.orderDatas = orderDatas;
    }

    public void addOrderData(OrderData orderData) {
        if (orderDatas == null) {
            orderDatas = new ArrayList<OrderData>();
        }
        this.orderDatas.add(orderData);
    }

}
