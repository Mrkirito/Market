package com.znb.cms.model.mapper;

import java.util.Date;

import com.znb.cms.common.BaseCommonQuery;

public class AppStatistics  extends BaseCommonQuery{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1035991850068208031L;

	private String date;

    private Long col30;

    private Long col14;

    private Date createTime;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public Long getCol30() {
        return col30;
    }

    public void setCol30(Long col30) {
        this.col30 = col30;
    }

    public Long getCol14() {
        return col14;
    }

    public void setCol14(Long col14) {
        this.col14 = col14;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}