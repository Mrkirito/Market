package com.hangjia.bxj.common;
/** 
* @author  作者 : yaoy
* @date 2016年5月3日 下午2:30:15 
* @version 1.0 
*/
public class BaseCommonQuery extends BaseQuery {

	/** Comment for <code>serialVersionUID</code> */
	private static final long serialVersionUID = -1428491871227005119L;

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
}
