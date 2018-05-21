/*
* Copyright (c) 2016 www.baobao18.com. All Rights Reserved.
*/
package com.hangjia.bxj.query.competingproductsstatistics;

import com.hangjia.bxj.common.BaseCommonQuery;

import java.util.Date;

/**
 * Created by Caigp
 * Date: 2016/11/10 11:08
 */
public class CompetingProductsStatisticsDataQuery extends BaseCommonQuery {

    private Date startTime;
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}