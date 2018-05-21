package com.core.cms.common;

import org.apache.commons.lang3.StringUtils;

/** 
* @author  
* @date 2016年5月3日 下午2:30:15 
* @version 1.0 
*/
public class BaseCommonQuery extends BaseQuery {
	private static final long serialVersionUID = -2378821271437686322L;
	private String startTime;   //  开始时间
	private String endTime;		//  结束时间		
	public BaseCommonQuery() {
        setPageSize(10);
    }
    private Integer status;
    private String orderBy; // 查询条件字段名

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		if (StringUtils.isNotBlank(startTime)&&startTime.length()<=10) {
			startTime += " 00:00:00";
		}
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		if (StringUtils.isNotBlank(endTime)&&endTime.length()<=10) {
			endTime += " 23:59:59";
		}
		this.endTime = endTime;
	}
    
    
    
}
