package com.hangjia.bxj.model.export;

/**
 * Created by Administrator on 2017/7/28.
 */
public class BbappData {
    private int id;
    private int bbapp_new;
    private int bbapp_start;
    private int times_num;
    private int bbapp_message;
    private double bbapp_sales_total;
    private String bbappPvLrr;
    private String bbappPvWeek;
    private String bbappPvMonth;
    private String bbappUvLrr;
    private String bbappUvWeek;
    private String bbappUvMonth;
    private String bbappTimesLrr;
    private String bbappTimesWeek;
    private String bbappTimesMonth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBbapp_new() {
        return bbapp_new;
    }

    public void setBbapp_new(int bbapp_new) {
        this.bbapp_new = bbapp_new;
    }

    public int getBbapp_start() {
        return bbapp_start;
    }

    public void setBbapp_start(int bbapp_start) {
        this.bbapp_start = bbapp_start;
    }

    public int getTimes_num() {
        return times_num;
    }

    public void setTimes_num(int times_num) {
        this.times_num = times_num;
    }

    public int getBbapp_message() {
        return bbapp_message;
    }

    public void setBbapp_message(int bbapp_message) {
        this.bbapp_message = bbapp_message;
    }

    public double getBbapp_sales_total() {
        return bbapp_sales_total;
    }

    public void setBbapp_sales_total(double bbapp_sales_total) {
        this.bbapp_sales_total = bbapp_sales_total;
    }

    public String getBbappPvLrr() {
        return bbappPvLrr;
    }

    public void setBbappPvLrr(String bbappPvLrr) {
        this.bbappPvLrr = bbappPvLrr;
    }

    public String getBbappPvWeek() {
        return bbappPvWeek;
    }

    public void setBbappPvWeek(String bbappPvWeek) {
        this.bbappPvWeek = bbappPvWeek;
    }

    public String getBbappPvMonth() {
        return bbappPvMonth;
    }

    public void setBbappPvMonth(String bbappPvMonth) {
        this.bbappPvMonth = bbappPvMonth;
    }

    public String getBbappUvLrr() {
        return bbappUvLrr;
    }

    public void setBbappUvLrr(String bbappUvLrr) {
        this.bbappUvLrr = bbappUvLrr;
    }

    public String getBbappUvWeek() {
        return bbappUvWeek;
    }

    public void setBbappUvWeek(String bbappUvWeek) {
        this.bbappUvWeek = bbappUvWeek;
    }

    public String getBbappUvMonth() {
        return bbappUvMonth;
    }

    public void setBbappUvMonth(String bbappUvMonth) {
        this.bbappUvMonth = bbappUvMonth;
    }

    public String getBbappTimesLrr() {
        return bbappTimesLrr;
    }

    public void setBbappTimesLrr(String bbappTimesLrr) {
        this.bbappTimesLrr = bbappTimesLrr;
    }

    public String getBbappTimesWeek() {
        return bbappTimesWeek;
    }

    public void setBbappTimesWeek(String bbappTimesWeek) {
        this.bbappTimesWeek = bbappTimesWeek;
    }

    public String getBbappTimesMonth() {
        return bbappTimesMonth;
    }

    public void setBbappTimesMonth(String bbappTimesMonth) {
        this.bbappTimesMonth = bbappTimesMonth;
    }

    @Override
    public String toString() {
        return "BxjappData{" +
                "id=" + id +
                ", bbapp_new=" + bbapp_new +
                ", bbapp_start=" + bbapp_start +
                ", times_num=" + times_num +
                ", bbapp_message=" + bbapp_message +
                ", bbapp_sales_total=" + bbapp_sales_total +
                ", bbappPvLrr='" + bbappPvLrr + '\'' +
                ", bbappPvWeek='" + bbappPvWeek + '\'' +
                ", bbappPvMonth='" + bbappPvMonth + '\'' +
                ", bbappUvLrr='" + bbappUvLrr + '\'' +
                ", bbappUvWeek='" + bbappUvWeek + '\'' +
                ", bbappUvMonth='" + bbappUvMonth + '\'' +
                ", bbappTimesLrr='" + bbappTimesLrr + '\'' +
                ", bbappTimesWeek='" + bbappTimesWeek + '\'' +
                ", bbappTimesMonth='" + bbappTimesMonth + '\'' +
                '}';
    }
}
