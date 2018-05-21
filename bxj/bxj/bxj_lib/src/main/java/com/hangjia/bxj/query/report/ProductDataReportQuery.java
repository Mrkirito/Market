package com.hangjia.bxj.query.report;

import com.hangjia.bxj.common.BaseCommonQuery;

import java.util.Date;

/**
 * @author 作者 : bei.zhang
 * @version 1.0
 * @date 2016年10月11日 下午2:32:53
 */
public class ProductDataReportQuery extends BaseCommonQuery {

    private Long id;
    private String queryMonth;
    private Date dataDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQueryMonth() {
        return queryMonth;
    }

    public void setQueryMonth(String queryMonth) {
        this.queryMonth = queryMonth;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }
}
