package com.hangjia.bxj.vo;

/**
 * Created by Administrator on 2016/4/15.
 */
public class VideoAuthorityVo {
    private Integer userId;
    private int status;
    private int hasVouchers;
    private int giveVouchers;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getHasVouchers() {
        return hasVouchers;
    }

    public void setHasVouchers(int hasVouchers) {
        this.hasVouchers = hasVouchers;
    }

    public int getGiveVouchers() {
        return giveVouchers;
    }

    public void setGiveVouchers(int giveVouchers) {
        this.giveVouchers = giveVouchers;
    }
}
