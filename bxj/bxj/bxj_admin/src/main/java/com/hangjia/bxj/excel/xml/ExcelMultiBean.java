package com.hangjia.bxj.excel.xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ExcelMultiBean extends ExcelBean {
    private boolean multiple = false;
    private List<ExcelBean> beanList;

    public ExcelMultiBean() {
    }

    public void addExcelBean(ExcelBean excelBean) {
        if(null == this.beanList) {
            this.beanList = new ArrayList();
        }

        this.beanList.add(excelBean);
    }

    public List<ExcelBean> getBeanList() {
        return this.beanList;
    }

    public void setBeanList(List<ExcelBean> beanList) {
        this.beanList = beanList;
    }

    public boolean isMultiple() {
        return this.multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public ExcelMultiBean getExcelBeanById(String id) {
        if(!this.multiple) {
            return this;
        } else {
            ExcelMultiBean bean = new ExcelMultiBean();
            bean.setName(this.getName());
            if(StringUtils.isNotBlank(id) && null != this.beanList && this.beanList.size() > 0) {
                Iterator i$ = this.beanList.iterator();

                while(i$.hasNext()) {
                    ExcelBean excelBean = (ExcelBean)i$.next();
                    if(id.equals(excelBean.getId())) {
                        bean.addExcelBean(excelBean);
                    }
                }
            }

            if(null != bean.getBeanList()) {
                if(bean.getBeanList().size() > 1) {
                    bean.setMultiple(true);
                } else if(bean.getBeanList().size() == 1) {
                    bean.setFieldList(((ExcelBean)bean.getBeanList().get(0)).getFieldList());
                    bean.setName(((ExcelBean)bean.getBeanList().get(0)).getName());
                }
            }

            return bean;
        }
    }
}
