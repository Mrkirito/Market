package com.hangjia.bxj.excel.util;

import com.hangjia.bxj.excel.SelectOption;
import com.hangjia.bxj.excel.bundle.ResourceBundleCreateException;
import com.hangjia.bxj.excel.render.ExcelRender;
import com.hangjia.bxj.excel.xml.ExcelBean;
import com.hangjia.bxj.excel.xml.ExcelField;
import com.hangjia.bxj.excel.xml.ExcelMultiBean;
import com.hangjia.bxj.excel.xml.XMLExcelExportResource;
import com.hangjia.bxj.mvc.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.generic.RenderTool;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

public class ExcelExportUtil {

	private static ExcelExportUtil excelUtil = new ExcelExportUtil();
	private transient Logger log;
	
	/**
	 * 获取ExcelBean
	 * @param excelModuleName
	 * @return
	 * @throws DocumentException
	 * @throws ResourceBundleCreateException
	 */
    public static ExcelMultiBean generateExcelBean(String excelModuleName) throws DocumentException, ResourceBundleCreateException {
    	SAXReader reader = new SAXReader();
    	Document doc = reader.read(excelModuleName);
        XMLExcelExportResource bundle = new XMLExcelExportResource(doc);
        return null != bundle && null != bundle.getExcelBean()?bundle.getExcelBean():null;
    }
	
    /**
     * 导出
     */
    public static void export(Context context, OutputStream outputStream, List<ExcelField> fieldList) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        if(null != fieldList && fieldList.size() > 0) {
            excelUtil.createHeader(wb, sheet, fieldList);
            excelUtil.createBody(wb, sheet, context, fieldList);
        }

        wb.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
    
    /**
     * 导出
     * @param context
     * @param outputStream
     * @param excelMultiBean
     * @throws IOException
     */
    public static void export(Context context, OutputStream outputStream, ExcelMultiBean excelMultiBean) throws IOException {
        if(!excelMultiBean.isMultiple()) {
            export((Context)context, (OutputStream)outputStream, (List)excelMultiBean.getFieldList());
        } else {
            HSSFWorkbook wb = new HSSFWorkbook();
            List beanList = excelMultiBean.getBeanList();
            if(null != beanList && beanList.size() > 0) {
                for(int i = 0; i < beanList.size(); ++i) {
                    ExcelBean excelBean = (ExcelBean)beanList.get(i);
                    String sheetName = "sheet" + (i + 1);
                    if(StringUtils.isNotBlank(excelBean.getName())) {
                        sheetName = excelBean.getName();
                    }

                    HSSFSheet sheet = wb.createSheet(sheetName);
                    String dataSubfix = "$!dataList_" + i + ".get(#{idx}).";
                    String key = "dataList_" + i;
                    List dataList = (List)context.get(key);
                    List fieldList = excelBean.getFieldList();
                    if(null != fieldList && fieldList.size() > 0) {
                        excelUtil.createHeader(wb, sheet, fieldList);
                        excelUtil.createBody(wb, sheet, context, dataList, dataSubfix, fieldList);
                    }
                }
            }

            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
        }

    }
    
    /**
     * 创建body
     * @param wb
     * @param sheet
     * @param context
     * @param fieldList
     */
    private void createBody(HSSFWorkbook wb, HSSFSheet sheet, Context context, List<ExcelField> fieldList) {
        String dataSubfix = "$!dataList.get(#{idx}).";
        List dataList = (List)context.get("dataList");
        this.createBody(wb, sheet, context, dataList, dataSubfix, fieldList);
    }
    
    /**
     * 创建头
     * @param wb
     * @param sheet
     * @param fieldList
     */
    private void createHeader(HSSFWorkbook wb, HSSFSheet sheet, List<ExcelField> fieldList) {
        HSSFCellStyle headerStyle = this.getHeaderStyle(wb);
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell;
        if(null != fieldList && fieldList.size() > 0) {
            for(int i = 0; i < fieldList.size(); ++i) {
                ExcelField field = fieldList.get(i);
                cell = row.createCell(i);
                cell.setCellStyle(headerStyle);
                cell.setCellValue(field.getTitle());
                short columnWidth = 4096;
                if("Date".equals(field.getType())) {
                    columnWidth = 4096;
                } else if("Integer".equals(field.getType())) {
                    columnWidth = 2048;
                } else if("Double".equals(field.getType())) {
                    columnWidth = 2048;
                } else if("BigDecimal".equals(field.getType())) {
                    columnWidth = 2048;
                }

                if(null != field.getWidth() && field.getWidth().shortValue() > 0) {
                    columnWidth = (short)(field.getWidth().shortValue() * 2048);
                }

                sheet.setColumnWidth(i, columnWidth);
            }
        }

    }
    
    /**
     * 头部样式
     * @param wb
     * @return
     */
    private HSSFCellStyle getHeaderStyle(HSSFWorkbook wb) {
        HSSFCellStyle headStyle = wb.createCellStyle();
        headStyle.setAlignment((short)2);
        headStyle.setBorderBottom((short)1);
        headStyle.setBottomBorderColor((short)8);
        headStyle.setBorderLeft((short)1);
        headStyle.setLeftBorderColor((short)8);
        headStyle.setBorderRight((short)1);
        headStyle.setRightBorderColor((short)8);
        headStyle.setBorderTop((short)1);
        headStyle.setTopBorderColor((short)8);
        headStyle.setAlignment((short)2);
        headStyle.setVerticalAlignment((short)1);
        headStyle.setLocked(true);
        headStyle.setFillForegroundColor((short)53);
        headStyle.setFillPattern((short)1);
        HSSFFont headFont = wb.createFont();
        headFont.setFontHeightInPoints((short)12);
        headFont.setFontName("Courier New");
        headFont.setBoldweight((short)700);
        headStyle.setFont(headFont);
        return headStyle;
    }
    
    /**
     * 创建body
     * @param wb
     * @param sheet
     * @param context
     * @param dataList
     * @param dataSubfix
     * @param fieldList
     */
    private void createBody(HSSFWorkbook wb, HSSFSheet sheet, Context context, List dataList, String dataSubfix, List<ExcelField> fieldList) {
        context.put("dateUtil", new DateUtils());
        HSSFCellStyle style = wb.createCellStyle();
        style.setBorderBottom((short)1);
        style.setBottomBorderColor((short)8);
        style.setBorderLeft((short)1);
        style.setLeftBorderColor((short)8);
        style.setBorderRight((short)1);
        style.setRightBorderColor((short)8);
        style.setBorderTop((short)1);
        style.setTopBorderColor((short)8);
        RenderTool renderTool = new RenderTool();
        if(null != dataList && dataList.size() > 0 && null != fieldList && fieldList.size() > 0) {
            for(int i = 0; i < dataList.size(); ++i) {
                Object data = dataList.get(i);
                HSSFRow row = sheet.createRow(i + 1);
                HSSFCell cell = null;

                for(int j = 0; j < fieldList.size(); ++j) {
                    try {
                        cell = row.createCell(j);
                        ExcelField e = (ExcelField)fieldList.get(j);
                        HSSFCellStyle cellStyle = this.getRowCellStyle(wb, e,style);
                        cell.setCellStyle(cellStyle);
                        String fieldName = e.getName();
                        if("excelIndex".equals(fieldName)) {
                            cell.setCellValue((double)(i + 1));
                        } else {
                            String name = (dataSubfix + fieldName).replace("#{idx}", i + "");
                            String res = renderTool.eval(context, name);
                            if(StringUtils.isNotBlank(e.getRenderClazz())) {
                                ExcelRender optionList = (ExcelRender)Class.forName(e.getRenderClazz()).newInstance();
                                if(null != optionList) {
                                    Object value = optionList.renderer(data, e.getName());
                                    if(null != value) {
                                        res = value.toString();
                                    }
                                }
                            }

                            if(StringUtils.isNotBlank(res)) {
                                if("Date".equals(e.getType())) {
                                    name = "$!dateUtil.format(" + (dataSubfix + e.getName()).replace("#{idx}", i + "") + ", \"" + e.getDateFormat() + "\")";
                                    res = renderTool.eval(context, name);
                                    if(StringUtils.isNotBlank(res)) {
                                        cell.setCellValue(res);
                                    }
                                } else if("Integer".equals(e.getType())) {
                                    cell.setCellValue((double)Integer.valueOf(res).intValue());
                                } else if("Double".equals(e.getType())) {
                                    cell.setCellValue(Double.valueOf(res).doubleValue());
                                } else if("BigDecimal".equals(e.getType())) {
                                    cell.setCellValue(Double.valueOf(res).doubleValue());
                                } else if("select".equals(e.getType())) {
                                    List var23 = e.getSelectDataList();
                                    String var24 = res;
                                    if(null != var23 && var23.size() > 0) {
                                        Iterator i$ = var23.iterator();

                                        while(i$.hasNext()) {
                                            SelectOption option = (SelectOption)i$.next();
                                            if(res.equals(option.getId())) {
                                                var24 = option.getText();
                                                break;
                                            }
                                        }
                                    }

                                    cell.setCellValue(var24);
                                } else {
                                    cell.setCellValue(res);
                                }
                            }
                        }
                    } catch (Exception var22) {
                        this.getLogger().error("export excel error!", var22);
                    }
                }
            }
        }
    }
    
    /**
     * 行样式
     * @param wb
     * @param field
     * @return
     */
    private HSSFCellStyle getRowCellStyle(HSSFWorkbook wb, ExcelField field,HSSFCellStyle cellStyle) {

        if("Date".equals(field.getType())) {
            cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(field.getDateFormat()));
        } else if("Integer".equals(field.getType())) {
            cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
        } else if("Double".equals(field.getType())) {
            cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
        } else if("BigDecimal".equals(field.getType())) {
            cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
        } else {
            cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("@"));
        }

        return cellStyle;
    }
    
    protected final Logger getLogger() {
        if(this.log == null) {
            this.log = LoggerFactory.getLogger(this.getClass());
        }

        return this.log;
    }
}
