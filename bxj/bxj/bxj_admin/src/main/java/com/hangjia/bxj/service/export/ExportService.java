package com.hangjia.bxj.service.export;

import org.apache.poi.xssf.usermodel.XSSFCell;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/7/25.
 */
public interface ExportService {
    public void exportExcel(Object[][] o, OutputStream out,HttpServletResponse response);

    public void exportExcel(String[] headers, Object[][] o, OutputStream out,HttpServletResponse response);

    public void exportExcel(String[] headers, Object[][] o, OutputStream out, String pattern,HttpServletResponse response);

    public String getStringCellValue(XSSFCell cell);

    public void exportExcel(String title, String[] headers, Object[][] o, OutputStream out, String pattern, HttpServletResponse response);
}
