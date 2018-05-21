package com.hangjia.bxj.model.report;

import java.math.BigDecimal;
import java.util.Date;

import com.hangjia.bxj.common.BaseCommonDO;

public class UserDataReport extends BaseCommonDO {

    private Long id; // 主键

    private Integer newIosNum; // ios新增用户

    private Integer newAndroidNum; // 安卓新增用户

    private Integer activeIosNum; // ios启动用户

    private Integer activeAndroidNum; // 安卓启动用户

    private Integer newAddTarget;
    private Integer newStartTarget;
    private BigDecimal salesTotalTarget;

    private Integer newSum; // 累计用户数

    private Integer newSumDay;

    private Integer activeSumDay;

    private Integer newSumYesterday;

    private Integer activeSumYesterday;

    private Double newAvgMonth;

    private Double activeAvgMonth;

    private Date dataTime;

    private Integer timesIosNum;

    private Integer timesAndroidNum;

    private Double timesAvgYesterday;

    private Integer timesSumDay;

    private Integer message;
    private BigDecimal salesTotal;

    private BigDecimal salesProfits;

    private Integer bbwNew;
    private Integer bbwStart;
    private Integer bbwTimes;
    private Integer bbwMessage;
    private BigDecimal bbwSalesTotal;
    private Integer bbappNew;
    private Integer bbappStart;
    private Integer bbappTimes;
    private Integer bbappMessage;
    private BigDecimal bbappSalesTotal;
    private String bbwPvLrr;
    private Integer hjappNew;
    private Integer hjappStart;
    private Integer hjappTimes;
    private Integer hjappMessage;
    private BigDecimal hjappSalesTotal;

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    public Integer getBbwTimes() {
        return bbwTimes;
    }

    public void setBbwTimes(Integer bbwTimes) {
        this.bbwTimes = bbwTimes;
    }

    public Integer getBbwMessage() {
        return bbwMessage;
    }

    public void setBbwMessage(Integer bbwMessage) {
        this.bbwMessage = bbwMessage;
    }

    public BigDecimal getBbwSalesTotal() {
        return bbwSalesTotal;
    }

    public void setBbwSalesTotal(BigDecimal bbwSalesTotal) {
        this.bbwSalesTotal = bbwSalesTotal;
    }

    public Integer getBbappTimes() {
        return bbappTimes;
    }

    public void setBbappTimes(Integer bbappTimes) {
        this.bbappTimes = bbappTimes;
    }

    public Integer getBbappMessage() {
        return bbappMessage;
    }

    public void setBbappMessage(Integer bbappMessage) {
        this.bbappMessage = bbappMessage;
    }

    public BigDecimal getBbappSalesTotal() {
        return bbappSalesTotal;
    }

    public void setBbappSalesTotal(BigDecimal bbappSalesTotal) {
        this.bbappSalesTotal = bbappSalesTotal;
    }

    public String getBbwPvLrr() {
        return bbwPvLrr;
    }

    public void setBbwPvLrr(String bbwPvLrr) {
        this.bbwPvLrr = bbwPvLrr;
    }

    public Integer getHjappTimes() {
        return hjappTimes;
    }

    public void setHjappTimes(Integer hjappTimes) {
        this.hjappTimes = hjappTimes;
    }

    public Integer getHjappMessage() {
        return hjappMessage;
    }

    public void setHjappMessage(Integer hjappMessage) {
        this.hjappMessage = hjappMessage;
    }

    public BigDecimal getHjappSalesTotal() {
        return hjappSalesTotal;
    }

    public void setHjappSalesTotal(BigDecimal hjappSalesTotal) {
        this.hjappSalesTotal = hjappSalesTotal;
    }

    private Integer hjwPv;
    private Integer hjwUv;

    public Integer getHjwPv() {
        return hjwPv;
    }

    public void setHjwPv(Integer hjwPv) {
        this.hjwPv = hjwPv;
    }

    public Integer getHjwUv() {
        return hjwUv;
    }

    public void setHjwUv(Integer hjwUv) {
        this.hjwUv = hjwUv;
    }

    public Integer getNewAddTarget() {
        return newAddTarget;
    }

    public void setNewAddTarget(Integer newAddTarget) {
        this.newAddTarget = newAddTarget;
    }

    public Integer getNewStartTarget() {
        return newStartTarget;
    }

    public void setNewStartTarget(Integer newStartTarget) {
        this.newStartTarget = newStartTarget;
    }

    public BigDecimal getSalesTotalTarget() {
        return salesTotalTarget;
    }

    public void setSalesTotalTarget(BigDecimal salesTotalTarget) {
        this.salesTotalTarget = salesTotalTarget;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataTime() {
        return dataTime;
    }

    public BigDecimal getSalesProfits() {
        return salesProfits;
    }

    public void setSalesProfits(BigDecimal salesProfits) {
        this.salesProfits = salesProfits;
    }

    public Double getNewAvgMonth() {
        if (null == this.newAvgMonth) {
            return 0D;
        } else {
            BigDecimal b = new BigDecimal(this.newAvgMonth);
            return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
    }

    public void setNewAvgMonth(Double newAvgMonth) {
        this.newAvgMonth = newAvgMonth;
    }

    public Double getActiveAvgMonth() {
        if (null == this.activeAvgMonth) {
            return 0D;
        } else {
            BigDecimal b = new BigDecimal(this.activeAvgMonth);
            return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
    }

    public void setActiveAvgMonth(Double activeAvgMonth) {
        this.activeAvgMonth = activeAvgMonth;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    public Integer getNewSumYesterday() {
        return newSumYesterday;
    }

    public void setNewSumYesterday(Integer newSumYesterday) {
        this.newSumYesterday = newSumYesterday;
    }

    public Integer getActiveSumYesterday() {
        return activeSumYesterday;
    }

    public void setActiveSumYesterday(Integer activeSumYesterday) {
        this.activeSumYesterday = activeSumYesterday;
    }

    public Integer getNewIosNum() {
        return newIosNum;
    }

    public Integer getNewSumDay() {
        return newSumDay;
    }

    public void setNewSumDay(Integer newSumDay) {
        this.newSumDay = newSumDay;
    }

    public Integer getActiveSumDay() {
        return activeSumDay;
    }

    public void setActiveSumDay(Integer activeSumDay) {
        this.activeSumDay = activeSumDay;
    }

    public void setNewIosNum(Integer newIosNum) {
        this.newIosNum = newIosNum;
    }

    public Integer getNewAndroidNum() {
        return newAndroidNum;
    }

    public void setNewAndroidNum(Integer newAndroidNum) {
        this.newAndroidNum = newAndroidNum;
    }

    public Integer getActiveIosNum() {
        return activeIosNum;
    }

    public void setActiveIosNum(Integer activeIosNum) {
        this.activeIosNum = activeIosNum;
    }

    public Integer getActiveAndroidNum() {
        return activeAndroidNum;
    }

    public void setActiveAndroidNum(Integer activeAndroidNum) {
        this.activeAndroidNum = activeAndroidNum;
    }

    public Integer getNewSum() {
        return newSum;
    }

    public void setNewSum(Integer newSum) {
        this.newSum = newSum;
    }

    public Integer getTimesIosNum() {
        return timesIosNum;
    }

    public void setTimesIosNum(Integer timesIosNum) {
        this.timesIosNum = timesIosNum;
    }

    public Integer getTimesAndroidNum() {
        return timesAndroidNum;
    }

    public void setTimesAndroidNum(Integer timesAndroidNum) {
        this.timesAndroidNum = timesAndroidNum;
    }

    public Double getTimesAvgYesterday() {
        if (null == this.timesAvgYesterday) {
            return 0D;
        } else {
            BigDecimal b = new BigDecimal(this.timesAvgYesterday);
            return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
    }

    public Integer getBbwNew() {
        return bbwNew;
    }

    public void setBbwNew(Integer bbwNew) {
        this.bbwNew = bbwNew;
    }

    public Integer getBbwStart() {
        return bbwStart;
    }

    public void setBbwStart(Integer bbwStart) {
        this.bbwStart = bbwStart;
    }

    public Integer getBbappNew() {
        return bbappNew;
    }

    public void setBbappNew(Integer bbappNew) {
        this.bbappNew = bbappNew;
    }

    public Integer getBbappStart() {
        return bbappStart;
    }

    public void setBbappStart(Integer bbappStart) {
        this.bbappStart = bbappStart;
    }

    public Integer getHjappNew() {
        return hjappNew;
    }

    public void setHjappNew(Integer hjappNew) {
        this.hjappNew = hjappNew;
    }

    public Integer getHjappStart() {
        return hjappStart;
    }

    public void setHjappStart(Integer hjappStart) {
        this.hjappStart = hjappStart;
    }

    public void setTimesAvgYesterday(Double timesAvgYesterday) {
        this.timesAvgYesterday = timesAvgYesterday;
    }

    public Integer getTimesSumDay() {
        return timesSumDay;
    }

    public void setTimesSumDay(Integer timesSumDay) {
        this.timesSumDay = timesSumDay;
    }

    public BigDecimal getSalesTotal() {
        return salesTotal;
    }

    public void setSalesTotal(BigDecimal salesTotal) {
        this.salesTotal = salesTotal;
    }


}