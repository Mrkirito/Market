package com.hangjia.bxj.excel.xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.hangjia.bxj.excel.SelectOption;

public class ExcelField implements Serializable {
    private static final long serialVersionUID = 3272264444080842752L;
    private String name;
    private String title;
    private String type;
    private String dateFormat;
    private String renderClazz;
    private Short width;
    private List<SelectOption> selectDataList;

    public ExcelField() {
    }

    public Short getWidth() {
        return this.width;
    }

    public void setWidth(Short width) {
        this.width = width;
    }

    public String getRenderClazz() {
        return this.renderClazz;
    }

    public void setRenderClazz(String renderClazz) {
        this.renderClazz = renderClazz;
    }

    public void addSelectData(String id, String text) {
        if(null == this.selectDataList) {
            this.selectDataList = new ArrayList();
        }

        this.selectDataList.add(new SelectOption(id, text));
    }

    public List<SelectOption> getSelectDataList() {
        return this.selectDataList;
    }

    public void setSelectDataList(List<SelectOption> selectDataList) {
        this.selectDataList = selectDataList;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateFormat() {
        return this.dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
}
