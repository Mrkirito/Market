package com.hangjia.bxj.model.right;

import com.hangjia.bxj.common.BaseCommonDO;

/**
 * @author yaoy
 * @since 2016-06-22
 */
public class FunctionDO extends BaseCommonDO {
    private Long id;
    private String funcCode;
    private String funcName;
    private String resourceCode;
    private String resourceName;
    private Integer sort;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getFuncCode() {
        return funcCode;
    }


    public void setFuncCode(String funcCode) {
        this.funcCode = funcCode;
    }


    public String getFuncName() {
        return funcName;
    }


    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }


    public String getResourceCode() {
        return resourceCode;
    }


    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }


    public String getResourceName() {
        return resourceName;
    }


    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }


    public Integer getSort() {
        return sort;
    }


    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
