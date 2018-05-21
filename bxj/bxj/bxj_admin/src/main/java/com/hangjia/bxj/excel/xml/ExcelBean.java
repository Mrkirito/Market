package com.hangjia.bxj.excel.xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExcelBean implements Serializable {
    private static final long serialVersionUID = -5587661759971107448L;
    private String id;
    private String name;
    private List<ExcelField> fieldList;

    public ExcelBean() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addField(ExcelField field) {
        if(null == this.fieldList) {
            this.fieldList = new ArrayList();
        }

        this.fieldList.add(field);
    }

    public List<ExcelField> getFieldList() {
        return this.fieldList;
    }

    public void setFieldList(List<ExcelField> fieldList) {
        this.fieldList = fieldList;
    }
}
