package com.hangjia.bxj.model.export;

/**
 * Created by Administrator on 2017/7/28.
 */
public class BbwData {
    private int id;
    private int bbw_new;
    private int bbw_start;
    private int times_num;
    private int bbw_message;
    private double bbw_sales_total;
    private String bbwPvLrr;
    private String bbwPvWeek;
    private String bbwPvMonth;
    private String bbwUvLrr;
    private String bbwUvWeek;
    private String bbwUvMonth;
    private String bbwTimesLrr;
    private String bbwTimesWeek;
    private String bbwTimesMonth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBbw_new() {
        return bbw_new;
    }

    public void setBbw_new(int bbw_new) {
        this.bbw_new = bbw_new;
    }

    public int getBbw_start() {
        return bbw_start;
    }

    public void setBbw_start(int bbw_start) {
        this.bbw_start = bbw_start;
    }

    public int getTimes_num() {
        return times_num;
    }

    public void setTimes_num(int times_num) {
        this.times_num = times_num;
    }

    public int getBbw_message() {
        return bbw_message;
    }

    public void setBbw_message(int bbw_message) {
        this.bbw_message = bbw_message;
    }

    public double getBbw_sales_total() {
        return bbw_sales_total;
    }

    public void setBbw_sales_total(double bbw_sales_total) {
        this.bbw_sales_total = bbw_sales_total;
    }

    public String getBbwPvLrr() {
        return bbwPvLrr;
    }

    public void setBbwPvLrr(String bbwPvLrr) {
        this.bbwPvLrr = bbwPvLrr;
    }

    public String getBbwPvWeek() {
        return bbwPvWeek;
    }

    public void setBbwPvWeek(String bbwPvWeek) {
        this.bbwPvWeek = bbwPvWeek;
    }

    public String getBbwPvMonth() {
        return bbwPvMonth;
    }

    public void setBbwPvMonth(String bbwPvMonth) {
        this.bbwPvMonth = bbwPvMonth;
    }

    public String getBbwUvLrr() {
        return bbwUvLrr;
    }

    public void setBbwUvLrr(String bbwUvLrr) {
        this.bbwUvLrr = bbwUvLrr;
    }

    public String getBbwUvWeek() {
        return bbwUvWeek;
    }

    public void setBbwUvWeek(String bbwUvWeek) {
        this.bbwUvWeek = bbwUvWeek;
    }

    public String getBbwUvMonth() {
        return bbwUvMonth;
    }

    public void setBbwUvMonth(String bbwUvMonth) {
        this.bbwUvMonth = bbwUvMonth;
    }

    public String getBbwTimesLrr() {
        return bbwTimesLrr;
    }

    public void setBbwTimesLrr(String bbwTimesLrr) {
        this.bbwTimesLrr = bbwTimesLrr;
    }

    public String getBbwTimesWeek() {
        return bbwTimesWeek;
    }

    public void setBbwTimesWeek(String bbwTimesWeek) {
        this.bbwTimesWeek = bbwTimesWeek;
    }

    public String getBbwTimesMonth() {
        return bbwTimesMonth;
    }

    public void setBbwTimesMonth(String bbwTimesMonth) {
        this.bbwTimesMonth = bbwTimesMonth;
    }

    @Override
    public String toString() {
        return "BbwData{" +
                "id=" + id +
                ", bbw_new=" + bbw_new +
                ", bbw_start=" + bbw_start +
                ", times_num=" + times_num +
                ", bbw_message=" + bbw_message +
                ", bbw_sales_total=" + bbw_sales_total +
                ", bbwPvLrr='" + bbwPvLrr + '\'' +
                ", bbwPvWeek='" + bbwPvWeek + '\'' +
                ", bbwPvMonth='" + bbwPvMonth + '\'' +
                ", bbwUvLrr='" + bbwUvLrr + '\'' +
                ", bbwUvWeek='" + bbwUvWeek + '\'' +
                ", bbwUvMonth='" + bbwUvMonth + '\'' +
                ", bbwTimesLrr='" + bbwTimesLrr + '\'' +
                ", bbwTimesWeek='" + bbwTimesWeek + '\'' +
                ", bbwTimesMonth='" + bbwTimesMonth + '\'' +
                '}';
    }
}
