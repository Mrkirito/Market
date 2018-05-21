package com.hangjia.bxj.excel.view;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.hangjia.bxj.model.SalesTicketBasic;
import com.hangjia.bxj.service.ticket.SalesTicketService;
import com.hangjia.bxj.vo.SalesTicketOrderAttendPeoplesVo2;
/**
 * excel 到处
 * @author Administrator
 *
 */
public class ViewExcelImport extends AbstractExcelView {
	private SalesTicketService service;
	public ViewExcelImport(SalesTicketService service) {
		this.service = service;
	}

	private String encodeFilename(String filename) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < filename.length(); i++) {
			char c = filename.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}
	
	@Override
	protected void buildExcelDocument(Map<String, Object> map, HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
		Integer basicId = Integer.parseInt(request.getParameter("fid"));
		SalesTicketBasic basic=service.getSalesTicketBasicByPrimaryKey(basicId);
		List<SalesTicketOrderAttendPeoplesVo2> list =service.getTicketOrderAttendPeoplesVos(basicId);
		int rowNum=0;
		HSSFSheet sheet=workbook.createSheet();
		sheet.setColumnWidth(1, 3000);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 4000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 4000);
		sheet.setColumnWidth(6, 4000);
		HSSFRow row=sheet.createRow(rowNum);
		HSSFCell cell=null;
		String[] column={"序号","报名人手机","支付方式","购买日期","参会人姓名","参会人手机","座位"};
		for (int i = 0; i < column.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(column[i]);
			cell.setCellStyle(getHSSFCellStyle(workbook));
		}
		for (int i = 0; i < list.size(); i++) {
			++rowNum;
			SalesTicketOrderAttendPeoplesVo2 vo=list.get(i);
			row = sheet.createRow(rowNum);
			cell = row.createCell(0);
			cell.setCellValue(i + 1);
			
			cell = row.createCell(1);
			cell.setCellValue(vo.getBuyPhone());
			
			cell = row.createCell(2);
			cell.setCellValue(vo.getBuyType());
			
			cell = row.createCell(3);
			cell.setCellValue( new SimpleDateFormat("yyyy-MM-dd").format(vo.getCreateTime()));
			
			cell = row.createCell(4);
			cell.setCellValue(vo.getUserName());

			cell = row.createCell(5);
			cell.setCellValue(vo.getUserPhone());
			
			cell = row.createCell(6);
			cell.setCellValue(formatSingleSit(vo));

		}
        response.reset();  
		response.setContentType("application/vnd.ms-excel;charset=gb2312");
		response.setHeader("Content-Disposition", "attachment;filename="+ encodeFilename(basic.getActivityName()+".xls"));
		OutputStream ouputStream = response.getOutputStream();
		workbook.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}
	private String formatSingleSit(SalesTicketOrderAttendPeoplesVo2 vo) {
		StringBuffer s = new StringBuffer();
		String f = vo.getFloor();
		if (StringUtils.isNotBlank(f)) {
			s.append(f).append("-");
		}
		String a = vo.getArea();
		if (StringUtils.isNotBlank(a)) {
			s.append(a).append("-");
		}
		String r = vo.getRows();
		if (StringUtils.isNotBlank(r)) {
			s.append(r).append("-");
		}
		String n = vo.getNumber();
		if (StringUtils.isNotBlank(n)) {
			s.append(n);
		}
		return s.toString();
	}
	private HSSFCellStyle getHSSFCellStyle(HSSFWorkbook workbook){
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 9);
		cellStyle.setFont(font);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);		
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		return cellStyle;
	}
}