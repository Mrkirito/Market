package com.hangjia.bxj.excel.xml;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Node;

import com.hangjia.bxj.excel.bundle.ResourceBundleCreateException;

public class XMLExcelExportResource {
    protected Map<String, Object> values = new HashMap<String, Object>();
    protected ExcelMultiBean excelBean = new ExcelMultiBean();

    public XMLExcelExportResource(Document doc) throws ResourceBundleCreateException {
        Node root = doc.selectSingleNode("/export-root");
        if(null != root) {
            String nodeList = (String)root.selectObject("string(@name)");
            if(StringUtils.isNotBlank(nodeList)) {
                this.excelBean.setName(nodeList);
            }
        }

        List nodeList1 = doc.selectNodes("/export-root/export-fields");
        if(null != nodeList1 && nodeList1.size() > 1) {
            this.excelBean.setMultiple(true);
            Iterator i$ = nodeList1.iterator();

            while(i$.hasNext()) {
                Node fieldsNode = (Node)i$.next();
                ExcelBean bean = new ExcelBean();
                String id = (String)fieldsNode.selectObject("string(@id)");
                if(StringUtils.isNotBlank(id)) {
                    bean.setId(id);
                }

                String name = (String)fieldsNode.selectObject("string(@name)");
                if(StringUtils.isNotBlank(name)) {
                    bean.setName(name);
                }

                this.initFieldsResource(fieldsNode, bean, true);
                this.excelBean.addExcelBean(bean);
            }

            if(null == this.excelBean.getBeanList() || this.excelBean.getBeanList().size() <= 1) {
                this.excelBean.setMultiple(false);
            }
        } else if(null != nodeList1 && nodeList1.size() == 1) {
            this.initFieldsResource((Node)nodeList1.get(0), this.excelBean, false);
        }

    }

    protected void initFieldsResource(Node fieldsNode, ExcelBean excelBean, boolean multiple) throws ResourceBundleCreateException {
        ExcelField field;
        for(Iterator i = fieldsNode.selectNodes("export-field").iterator(); i.hasNext(); excelBean.addField(field)) {
            Node fieldNode = (Node)i.next();
            field = this.initFieldResource(fieldNode);
            if(!multiple) {
                this.values.put(field.getName(), field);
            }
        }

    }

    protected ExcelField initFieldResource(Node fieldNode) throws ResourceBundleCreateException {
        String name = (String)fieldNode.selectObject("string(@name)");
        String title = (String)fieldNode.selectObject("string(@title)");
        String type = (String)fieldNode.selectObject("string(@type)");
        String width = (String)fieldNode.selectObject("string(@width)");
        ExcelField field = new ExcelField();
        if(StringUtils.isBlank(name)) {
            throw new ResourceBundleCreateException("name attribute must is not null", (Object[])null, (Throwable)null);
        } else {
            field.setName(name);
            if(StringUtils.isBlank(title)) {
                throw new ResourceBundleCreateException("title attribute must is not null", (Object[])null, (Throwable)null);
            } else {
                field.setTitle(title);
                if(StringUtils.isBlank(type)) {
                    type = "String";
                }

                field.setType(type);
                if(StringUtils.isNotBlank(width)) {
                    field.setWidth(Short.valueOf(width));
                }

                if(!ExcelExportResourceConstant.typeList.contains(type)) {
                    throw new ResourceBundleCreateException("type must in String|BigDecimal|Double|Integer|Date|select", (Object[])null, (Throwable)null);
                } else {
                    if("Date".equals(type)) {
                        String renderNode = (String)fieldNode.selectObject("string(@dateFormat)");
                        if(StringUtils.isBlank(renderNode)) {
                            field.setDateFormat("yyyy-MM-dd HH:mm:ss");
                        } else {
                            field.setDateFormat(renderNode);
                        }
                    } else if("select".equals(type)) {
                        Iterator renderNode1 = fieldNode.selectNodes("select/data").iterator();

                        while(renderNode1.hasNext()) {
                            Node renderClazz = (Node)renderNode1.next();
                            this.initSelectDataResource(field, renderClazz);
                        }
                    }

                    Node renderNode2 = fieldNode.selectSingleNode("render");
                    if(null != renderNode2) {
                        String renderClazz1 = (String)renderNode2.selectObject("string(@class)");
                        if(StringUtils.isNotBlank(renderClazz1)) {
                            field.setRenderClazz(renderClazz1);
                        }
                    }

                    return field;
                }
            }
        }
    }

    protected void initSelectDataResource(ExcelField field, Node fieldNode) throws ResourceBundleCreateException {
        String id = (String)fieldNode.selectObject("string(@id)");
        String text = (String)fieldNode.selectObject("string(@text)");
        if(StringUtils.isNotBlank(id)) {
            field.addSelectData(id, text);
        }

    }

    protected Object handleGetObject(String key) {
        return this.values.get(key);
    }

    public Enumeration<String> getKeys() {
        return null;
    }

    public ExcelMultiBean getExcelBean() {
        return this.excelBean;
    }
}
