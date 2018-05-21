package com.znb.cms.util;
import java.io.ByteArrayOutputStream;  
import java.io.File;  
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.pdf.AcroFields;  
import com.itextpdf.text.pdf.BaseFont;  
import com.itextpdf.text.pdf.PdfReader;  
import com.itextpdf.text.pdf.PdfStamper;

/**
 * 
 * @author yx
 * PDF生成util
 */
public class PDFTempletUtil {
	  
	   
    private String templatePdfPath; //模板地址
    
    /**
     * 读取pdf文件
     * @param response
     * @throws Exception
     */
    public void readPdf(HttpServletResponse response) throws Exception{
    	PdfReader reader = new PdfReader(templatePdfPath);  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        PdfStamper ps = new PdfStamper(reader, bos);
        ps.close();  
        OutputStream fos = response.getOutputStream();  
        fos.write(bos.toByteArray());  
        fos.close();  
    }
    
    
    public String getTemplatePdfPath() {  
              return templatePdfPath;  
    }  

    public void setTemplatePdfPath(String templatePdfPath) {  
              this.templatePdfPath= templatePdfPath;  
    }  
}
