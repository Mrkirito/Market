package com.hangjia.bxj.service.export.impl;

import com.baobao.framework.support.utility.StringUtils;
import com.hangjia.bxj.service.export.ExportService;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;


/**
 * 处理Excel表格导出
 * Created by kirito on 2017/7/25.
 */
@Service("ExportService")
public class ExportServiceImpl implements ExportService {
    @Override
    public void exportExcel(Object[][] o, OutputStream out, HttpServletResponse response) {
    }

    @Override
    public void exportExcel(String[] headers, Object[][] o, OutputStream out, HttpServletResponse response) {
        exportExcel("测试POI导出EXCEL文档", headers, o, out, "yyyy-MM-dd", response);
    }

    @Override
    public void exportExcel(String[] headers, Object[][] o, OutputStream out, String pattern, HttpServletResponse response) {
    }


    @Override
    public String getStringCellValue(XSSFCell cell) {
        String strCell = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case XSSFCell.CELL_TYPE_STRING:
                    strCell = cell.getStringCellValue();
                    break;
                case XSSFCell.CELL_TYPE_NUMERIC:
                    strCell = String.valueOf(cell.getNumericCellValue());
                    break;
                case XSSFCell.CELL_TYPE_BOOLEAN:
                    strCell = String.valueOf(cell.getBooleanCellValue());
                    break;
                case XSSFCell.CELL_TYPE_BLANK:
                    strCell = "";
                    break;
                default:
                    strCell = "";
                    break;
            }
            if (strCell.equals("") || strCell == null) {
                return "";
            }
            if (cell == null) {
                return "";
            }
        }
        return strCell;
    }

    @Override
    public void exportExcel(String title, String[] headers, Object[][] o, OutputStream out, String pattern, HttpServletResponse response) {
        // 声明一个工作薄
        @SuppressWarnings("resource")
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 生成一个表格
        XSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 设置表格默认行高为两个单位
        sheet.setDefaultRowHeight((short) 500);
        // 生成标题行样式样式
        XSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(new XSSFColor(Color.decode("#EDEDED")));

        style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);// 设置border
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);

        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 字体居中
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
        // 生成一个字体
        XSSFFont font = workbook.createFont();
        font.setColor(new XSSFColor(Color.DARK_GRAY));// 字体颜色黑色
        font.setFontHeightInPoints((short) 12);// 字体大小
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// 加粗
        // 把字体应用到当前的样式
        style.setFont(font);

        // 生成多个字体
        XSSFFont font2 = workbook.createFont();
        //字体颜色会跟随值变化
        font2.setColor(new XSSFColor(Color.DARK_GRAY));// 字体颜色黑色
        font2.setFontHeightInPoints((short) 12);// 字体大小

        XSSFFont font3 = workbook.createFont();
        //字体颜色会跟随值变化
        font3.setColor(new XSSFColor(Color.decode("#00B050")));// 字体颜色绿色
        font3.setFontHeightInPoints((short) 12);// 字体大小

        XSSFFont font4 = workbook.createFont();
        //字体颜色会跟随值变化
        font4.setColor(new XSSFColor(Color.decode("#FF0000")));// 字体颜色红色
        font4.setFontHeightInPoints((short) 12);// 字体大小

        // 生成左侧栏目样式
        XSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(new XSSFColor(Color.decode("#EDEDED")));// 背景色浅灰
        style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 居中
        style2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
        style2.setFont(font2);

        // 生成第一大行、第三大行、第五大行右侧栏目的样式
        XSSFCellStyle style31 = workbook.createCellStyle();
        style31.setFillForegroundColor(new XSSFColor(Color.decode("#D9E1F2")));// 背景色
        style31.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 居中
        style31.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
        style31.setFont(font2);

        XSSFCellStyle style32 = workbook.createCellStyle();
        style32.setFillForegroundColor(new XSSFColor(Color.decode("#D9E1F2")));// 背景色
        style32.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 居中
        style32.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
        style32.setFont(font3);

        XSSFCellStyle style33 = workbook.createCellStyle();
        style33.setFillForegroundColor(new XSSFColor(Color.decode("#D9E1F2")));// 背景色
        style33.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 居中
        style33.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
        style33.setFont(font4);


        // 生成第二大行、第四大行右侧栏目的样式
        XSSFCellStyle style41 = workbook.createCellStyle();
        style41.setFillForegroundColor(new XSSFColor(Color.decode("#FCE4D6")));// 背景色
        style41.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 居中
        style41.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
        style41.setFont(font2);

        XSSFCellStyle style42 = workbook.createCellStyle();
        style42.setFillForegroundColor(new XSSFColor(Color.decode("#FCE4D6")));// 背景色
        style42.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 居中
        style42.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
        style42.setFont(font3);

        XSSFCellStyle style43 = workbook.createCellStyle();
        style43.setFillForegroundColor(new XSSFColor(Color.decode("#FCE4D6")));// 背景色
        style43.setAlignment(XSSFCellStyle.ALIGN_CENTER);// 居中
        style43.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
        style43.setFont(font4);


        // 声明一个画图的顶级管理器
        XSSFDrawing draw = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        XSSFComment comment = draw.createCellComment(new XSSFClientAnchor(1, 1, 4, 4, (short) 0, 0, (short) 3, 3));
        // 设置注释内容
        comment.setString(new XSSFRichTextString("分母为零有两种情况：1、数据为空；2、未找到相应的数据导致分母为零"));
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
        comment.setAuthor("kirito");

        // 产生表格标题行
        XSSFRow subRow = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            XSSFCell cell = subRow.createCell(i);
            cell.setCellStyle(style);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        // 遍历集合数据，产生数据行
        for (int i = 0; i < 14; i++) {
            XSSFRow newRow = sheet.createRow(i + 1);
            for (int j = 0; j < 8; j++) {

                try {
                    String textValue = null;
                    // 判断值的类型后进行强制类型转换
                    Object value = o[i][j];
                    if (StringUtils.isBlank(value)) {//为空不合法
                        textValue = "分母为零";
                    } else {
                        if (String.valueOf(value).equals("0")) {//数值为零
                                textValue="0";
                        } else {
                            // 其它数据类型都当作字符串简单处理
                            textValue = value.toString();
                        }
                    }
                    if (textValue != null) {
                        XSSFRichTextString textString = new XSSFRichTextString(textValue);
                        if (i >= 0 && i <= 3 || i >= 8 && i <= 11 || i == 13) {
                            if (j < 2) {
                                XSSFCell cell = newRow.createCell(j);
                                cell.setCellStyle(style);
                                cell.setCellValue(textString);
                            } else {
                                XSSFCell cell = newRow.createCell(j);
                                boolean isNum = textValue.matches("[0-9]+");
                                if (isNum) {
                                    //是数字当作int处理
                                    cell.setCellStyle(style31);
                                    cell.setCellValue(Integer.parseInt(textValue));
                                } else {
                                    if (!textValue.contains("-")) {
                                        cell.setCellStyle(style33);
                                        cell.setCellValue(textString);
                                    } else {
                                        cell.setCellStyle(style32);
                                        cell.setCellValue(textString);
                                    }
                                }
                            }
                        } else {
                            if (j < 2) {
                                XSSFCell cell = newRow.createCell(j);
                                cell.setCellStyle(style);
                                cell.setCellValue(textString);
                            } else {
                                XSSFCell cell = newRow.createCell(j);
                                boolean isNum = textValue.matches("[0-9]+");
                                boolean isDouble=textValue.matches("^[0-9]*(\\.[0-9]*|[eE][+-][0-9]*)$");
                                if (isNum || isDouble) {
                                    //是数字当作int处理
                                    cell.setCellStyle(style41);
                                    cell.setCellValue(Integer.parseInt(textValue));
                                } else {
                                    if (!textValue.contains("-")) {
                                        cell.setCellStyle(style43);
                                        cell.setCellValue(textString);
                                    } else {
                                        cell.setCellStyle(style42);
                                        cell.setCellValue(textString);
                                    }
                                }
                            }
                        }
                    }
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } finally {
                    // 清理资源
                }
            }
        }
        sheet.addMergedRegion(new CellRangeAddress(1, 4, 0, 0));
        XSSFCell xc1 = sheet.getRow(1).getCell(0);
        xc1.setCellValue("pv/启动次数");

        sheet.addMergedRegion(new CellRangeAddress(5, 8, 0, 0));
        XSSFCell xc2 = sheet.getRow(5).getCell(0);
        xc2.setCellValue("uv/启动用户");

        sheet.addMergedRegion(new CellRangeAddress(9, 12, 0, 0));
        XSSFCell xc3 = sheet.getRow(9).getCell(0);
        xc3.setCellValue("新增用户");

        XSSFCell xc4 = sheet.getRow(13).getCell(2);
        Double cellValue = xc4.getNumericCellValue();
        sheet.addMergedRegion(new CellRangeAddress(13, 13, 2, 3));
        xc4.setCellValue(cellValue);

        XSSFCell xc5 = sheet.getRow(14).getCell(2);
        String cellValue2=getStringCellValue(xc5);
        sheet.addMergedRegion(new CellRangeAddress(14, 14, 2, 3));
        xc5.setCellValue(cellValue2);

        try {
            workbook.write(out);//将数据写到指定位置
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

