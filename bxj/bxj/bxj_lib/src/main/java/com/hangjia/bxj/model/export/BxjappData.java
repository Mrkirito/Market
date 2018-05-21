package com.hangjia.bxj.model.export;

/**
 * Created by Administrator on 2017/7/28.
 */
public class BxjappData {
    private int id;
    private int new_num;
    private int active_num;
    private int times_num;
    private int bxjapp_message;
    private double bxjapp_sales_total;
    private String bxjappPvLrr;
    private String bxjappPvWeek;
    private String bxjappPvMonth;
    private String bxjappUvLrr;
    private String bxjappUvWeek;
    private String bxjappUvMonth;
    private String bxjappTimesLrr;
    private String bxjappTimesWeek;
    private String bxjappTimesMonth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNew_num() {
        return new_num;
    }

    public void setNew_num(int new_num) {
        this.new_num = new_num;
    }

    public int getActive_num() {
        return active_num;
    }

    public void setActive_num(int active_num) {
        this.active_num = active_num;
    }

    public int getTimes_num() {
        return times_num;
    }

    public void setTimes_num(int times_num) {
        this.times_num = times_num;
    }

    public int getBxjapp_message() {
        return bxjapp_message;
    }

    public void setBxjapp_message(int bxjapp_message) {
        this.bxjapp_message = bxjapp_message;
    }

    public double getBxjapp_sales_total() {
        return bxjapp_sales_total;
    }

    public void setBxjapp_sales_total(double bxjapp_sales_total) {
        this.bxjapp_sales_total = bxjapp_sales_total;
    }

    public String getBxjappPvLrr() {
        return bxjappPvLrr;
    }

    public void setBxjappPvLrr(String bxjappPvLrr) {
        this.bxjappPvLrr = bxjappPvLrr;
    }

    public String getBxjappPvWeek() {
        return bxjappPvWeek;
    }

    public void setBxjappPvWeek(String bxjappPvWeek) {
        this.bxjappPvWeek = bxjappPvWeek;
    }

    public String getBxjappPvMonth() {
        return bxjappPvMonth;
    }

    public void setBxjappPvMonth(String bxjappPvMonth) {
        this.bxjappPvMonth = bxjappPvMonth;
    }

    public String getBxjappUvLrr() {
        return bxjappUvLrr;
    }

    public void setBxjappUvLrr(String bxjappUvLrr) {
        this.bxjappUvLrr = bxjappUvLrr;
    }

    public String getBxjappUvWeek() {
        return bxjappUvWeek;
    }

    public void setBxjappUvWeek(String bxjappUvWeek) {
        this.bxjappUvWeek = bxjappUvWeek;
    }

    public String getBxjappUvMonth() {
        return bxjappUvMonth;
    }

    public void setBxjappUvMonth(String bxjappUvMonth) {
        this.bxjappUvMonth = bxjappUvMonth;
    }

    public String getBxjappTimesLrr() {
        return bxjappTimesLrr;
    }

    public void setBxjappTimesLrr(String bxjappTimesLrr) {
        this.bxjappTimesLrr = bxjappTimesLrr;
    }

    public String getBxjappTimesWeek() {
        return bxjappTimesWeek;
    }

    public void setBxjappTimesWeek(String bxjappTimesWeek) {
        this.bxjappTimesWeek = bxjappTimesWeek;
    }

    public String getBxjappTimesMonth() {
        return bxjappTimesMonth;
    }

    public void setBxjappTimesMonth(String bxjappTimesMonth) {
        this.bxjappTimesMonth = bxjappTimesMonth;
    }

    @Override
    public String toString() {
        return "BxjappData{" +
                "id=" + id +
                ", new_num=" + new_num +
                ", active_num=" + active_num +
                ", times_num=" + times_num +
                ", bxjapp_message=" + bxjapp_message +
                ", bxjapp_sales_total=" + bxjapp_sales_total +
                ", bxjappPvLrr='" + bxjappPvLrr + '\'' +
                ", bxjappPvWeek='" + bxjappPvWeek + '\'' +
                ", bxjappPvMonth='" + bxjappPvMonth + '\'' +
                ", bxjappUvLrr='" + bxjappUvLrr + '\'' +
                ", bxjappUvWeek='" + bxjappUvWeek + '\'' +
                ", bxjappUvMonth='" + bxjappUvMonth + '\'' +
                ", bxjappTimesLrr='" + bxjappTimesLrr + '\'' +
                ", bxjappTimesWeek='" + bxjappTimesWeek + '\'' +
                ", bxjappTimesMonth='" + bxjappTimesMonth + '\'' +
                '}';
    }
}
