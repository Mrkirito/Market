package com.hangjia.bxj.model.export;

/**
 * Created by Administrator on 2017/7/28.
 */
public class HjappData {
    private int id;
    private int hjapp_new;
    private int hjapp_start;
    private int times_num;
    private int hjapp_message;
    private double hjapp_sales_total;
    private String hjappPvLrr;
    private String hjappPvWeek;
    private String hjappPvMonth;
    private String hjappUvLrr;
    private String hjappUvWeek;
    private String hjappUvMonth;
    private String hjappTimesLrr;
    private String hjappTimesWeek;
    private String hjappTimesMonth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHjapp_new() {
        return hjapp_new;
    }

    public void setHjapp_new(int hjapp_new) {
        this.hjapp_new = hjapp_new;
    }

    public int getHjapp_start() {
        return hjapp_start;
    }

    public void setHjapp_start(int hjapp_start) {
        this.hjapp_start = hjapp_start;
    }

    public int getTimes_num() {
        return times_num;
    }

    public void setTimes_num(int times_num) {
        this.times_num = times_num;
    }

    public int getHjapp_message() {
        return hjapp_message;
    }

    public void setHjapp_message(int hjapp_message) {
        this.hjapp_message = hjapp_message;
    }

    public double getHjapp_sales_total() {
        return hjapp_sales_total;
    }

    public void setHjapp_sales_total(double hjapp_sales_total) {
        this.hjapp_sales_total = hjapp_sales_total;
    }

    public String getHjappPvLrr() {
        return hjappPvLrr;
    }

    public void setHjappPvLrr(String hjappPvLrr) {
        this.hjappPvLrr = hjappPvLrr;
    }

    public String getHjappPvWeek() {
        return hjappPvWeek;
    }

    public void setHjappPvWeek(String hjappPvWeek) {
        this.hjappPvWeek = hjappPvWeek;
    }

    public String getHjappPvMonth() {
        return hjappPvMonth;
    }

    public void setHjappPvMonth(String hjappPvMonth) {
        this.hjappPvMonth = hjappPvMonth;
    }

    public String getHjappUvLrr() {
        return hjappUvLrr;
    }

    public void setHjappUvLrr(String hjappUvLrr) {
        this.hjappUvLrr = hjappUvLrr;
    }

    public String getHjappUvWeek() {
        return hjappUvWeek;
    }

    public void setHjappUvWeek(String hjappUvWeek) {
        this.hjappUvWeek = hjappUvWeek;
    }

    public String getHjappUvMonth() {
        return hjappUvMonth;
    }

    public void setHjappUvMonth(String hjappUvMonth) {
        this.hjappUvMonth = hjappUvMonth;
    }

    public String getHjappTimesLrr() {
        return hjappTimesLrr;
    }

    public void setHjappTimesLrr(String hjappTimesLrr) {
        this.hjappTimesLrr = hjappTimesLrr;
    }

    public String getHjappTimesWeek() {
        return hjappTimesWeek;
    }

    public void setHjappTimesWeek(String hjappTimesWeek) {
        this.hjappTimesWeek = hjappTimesWeek;
    }

    public String getHjappTimesMonth() {
        return hjappTimesMonth;
    }

    public void setHjappTimesMonth(String hjappTimesMonth) {
        this.hjappTimesMonth = hjappTimesMonth;
    }

    @Override
    public String toString() {
        return "HjappData{" +
                "id=" + id +
                ", hjapp_new=" + hjapp_new +
                ", hjapp_start=" + hjapp_start +
                ", times_num=" + times_num +
                ", hjapp_message=" + hjapp_message +
                ", hjapp_sales_total=" + hjapp_sales_total +
                ", hjappPvLrr='" + hjappPvLrr + '\'' +
                ", hjappPvWeek='" + hjappPvWeek + '\'' +
                ", hjappPvMonth='" + hjappPvMonth + '\'' +
                ", hjappUvLrr='" + hjappUvLrr + '\'' +
                ", hjappUvWeek='" + hjappUvWeek + '\'' +
                ", hjappUvMonth='" + hjappUvMonth + '\'' +
                ", hjappTimesLrr='" + hjappTimesLrr + '\'' +
                ", hjappTimesWeek='" + hjappTimesWeek + '\'' +
                ", hjappTimesMonth='" + hjappTimesMonth + '\'' +
                '}';
    }
}
