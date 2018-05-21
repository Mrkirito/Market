package com.znb.cms.excel;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.znb.cms.model.dto.OrderDto;
import com.znb.cms.model.mapper.Employee;
import com.znb.cms.service.IEmployeeService;
import com.znb.cms.service.IOrderService;
import com.znb.cms.util.ExcelUtils;

public class InsureExport extends AbstractExcelView {

	private IEmployeeService employeeService;
	
	private IOrderService orderService;
	private Integer  id;
	
	private String uploadPdfUrl;
	
	public String getUploadPdfUrl() {
		return uploadPdfUrl;
	}

	public void setUploadPdfUrl(String uploadPdfUrl) {
		this.uploadPdfUrl = uploadPdfUrl;
	}

	public IEmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public IOrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		OrderDto  orderDto = new OrderDto();
		orderDto.setId(id);
		List<OrderDto> orderList = orderService.selectOrderByOrder(orderDto);
		List<Employee> employeeList = employeeService.selectOrderEmployee(orderList.get(0).getEmployeeIds());
//		List<EmployeeDto> listEmployeeDto = employeeService.selectByOrderId(id);
		
		buildExcel(orderList, employeeList, workbook);
		
        response.reset();  
		response.setContentType("application/vnd.ms-excel;charset=gb2312");
		response.setHeader("Content-Disposition", "attachment;filename="+ ExcelUtils.encodeFilename("雇主责任险信息_"+orderList.get(0).getOrderNo()+".xls"));
		OutputStream ouputStream = response.getOutputStream();
		workbook.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
		
	}
	
	private void buildExcel(List<OrderDto> orderList, List<Employee> employeeList, HSSFWorkbook wb) {

//		SXSSFWorkbook wb = new SXSSFWorkbook(1000);//创建excel文档,内存中保留 1000 条数据，以免内存溢出  
		Font font = wb.createFont();//字体  
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);//加粗  
//        //表头样式  
//        CellStyle cellStylehead = wb.createCellStyle();
//        cellStylehead.setFont(font);//设置字体样式  
//        cellStylehead.setAlignment(CellStyle.ALIGN_CENTER);//水平对齐  
//        cellStylehead.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直对齐  
//        cellStylehead.setWrapText(true);//自动换行  
//        //设置边框  
//        cellStylehead.setBorderTop(CellStyle.BORDER_THIN);  
//        cellStylehead.setBorderRight(CellStyle.BORDER_THIN);  
//        cellStylehead.setBorderBottom(CellStyle.BORDER_THIN);  
//        cellStylehead.setBorderLeft(CellStyle.BORDER_THIN);  
        
        //表体样式  
        CellStyle cellStyleBody = wb.createCellStyle();//表体单元格样式  
        cellStyleBody.setAlignment(CellStyle.ALIGN_LEFT);//水平对齐  
        cellStyleBody.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直对齐  
        cellStyleBody.setWrapText(true);//自动换行  
        //设置边框  
        cellStyleBody.setBorderTop(CellStyle.BORDER_THIN);  
        cellStyleBody.setBorderRight(CellStyle.BORDER_THIN);  
        cellStyleBody.setBorderBottom(CellStyle.BORDER_THIN);  
        cellStyleBody.setBorderLeft(CellStyle.BORDER_THIN);  
//        cellStyleBody.setFont(font);
        
        //表体Title样式  
        CellStyle cellStyleTitle = wb.createCellStyle();//表体单元格样式  
        cellStyleTitle.setAlignment(CellStyle.ALIGN_CENTER);//水平对齐  
        cellStyleTitle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直对齐  
        cellStyleTitle.setWrapText(true);//自动换行  
        //设置背景色
        cellStyleTitle.setFillForegroundColor(HSSFColor.BLACK.index);
        cellStyleTitle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
        cellStyleTitle.setFillBackgroundColor(HSSFColor.BLACK.index); 
        
        Font fontTitle = wb.createFont();//字体  
//        fontTitle.setFontHeightInPoints((short)24);//字体大小
//        fontTitle.setFontName("楷体");
//        fontTitle.setBoldweight(Font.BOLDWEIGHT_BOLD);//粗体
        fontTitle.setColor(HSSFColor.WHITE.index);//白色字
        cellStyleTitle.setFont(fontTitle);
        
        //设置超链接
        Font fontLink = wb.createFont();//字体  
        fontLink.setBoldweight(Font.BOLDWEIGHT_BOLD);//加粗  
        fontLink.setUnderline(Font.U_SINGLE);  
        fontLink.setColor(IndexedColors.BLUE.getIndex());
        
        CellStyle cellStyleLink = wb.createCellStyle();//表体单元格样式  
        cellStyleLink.setFont(fontLink);
        
        Sheet sheet = wb.createSheet("sheet1");//创建一个sheet  
        sheet.setDefaultColumnWidth(15);//设置默认列宽  
        sheet.setColumnWidth(0, 30*256);//设置第一列的列宽  ，30个字符
        sheet.setColumnWidth(1, 40*256);//设置第二列的列宽  ，40个字符
        
        System.out.println("生成文件开始。。。");  
        long t1 = System.currentTimeMillis();  
        
        //写表体  
        Map<String, String> insureMap = new LinkedHashMap<String, String>();
        
        insureMap.put("title0", "雇主保障计划中级版-"+orderList.get(0).getAmount()+"万");
        insureMap.put("技术费等级 ", "中");
        insureMap.put("保单号 ", orderList.get(0).getPolicyNumber());
        insureMap.put("订单号", orderList.get(0).getOrderNo());
        insureMap.put("保单起始时间", orderList.get(0).getBeginDate() + " 00:00:00");
        insureMap.put("保单终止时间", orderList.get(0).getEndDate() + " 23:59:59");
        insureMap.put("保费", String.valueOf(orderList.get(0).getPrice()));
        insureMap.put("转账账户名", "");//????
        
        insureMap.put("title1", "投保人信息");
        
        insureMap.put("企业名称", orderList.get(0).getInsure().getCompanyName());
        insureMap.put("统一社会信用代码", orderList.get(0).getInsure().getInstitCode());
        String imglink = orderList.get(0).getInsure().getImgUrl();
        insureMap.put("营业执照", "下载链接");
        insureMap.put("发票类型", getReceiptType(orderList.get(0).getInsure().getReceiptType()));
        insureMap.put("税务登记号", orderList.get(0).getInsure().getTaxRegNumber());
        insureMap.put("税务登记地址", orderList.get(0).getInsure().getTaxRegAddress());
        insureMap.put("税务登记联系电话", orderList.get(0).getInsure().getTaxRegTel());
        insureMap.put("税务开户银行名称", orderList.get(0).getInsure().getTaxBankName());
        insureMap.put("税务开户银行账号", orderList.get(0).getInsure().getTaxBankAccount());
        insureMap.put("联系人", orderList.get(0).getInsure().getLinkman());
        insureMap.put("联系手机", orderList.get(0).getInsure().getTelephone());
        insureMap.put("所在省市", orderList.get(0).getInsure().getProvince()+orderList.get(0).getInsure().getCity());
        insureMap.put("办公地址", orderList.get(0).getInsure().getArea());
        
        insureMap.put("title2", "被保险人信息");
        
        insureMap.put("企业名称_", orderList.get(0).getIsurant().getCompanyName());
        insureMap.put("统一社会信用代码_", orderList.get(0).getIsurant().getInstitCode());
        
        String imglink2 = orderList.get(0).getIsurant().getImgUrl();
        insureMap.put("营业执照_", "下载链接2");
        insureMap.put("被保险人行业类别", "");//???
        
        insureMap.put("title3", "保障详情");
        
        int amount = orderList.get(0).getAmount();
        //保障计划
        insureMap.putAll(getBaozhangMap(amount));
        
//        System.out.println("------insureMap:" + insureMap);
        
//        String[] cols = "保单号 ,12234567".split(",");//切分sql列名  
//        int cellSize = cols.length;//列数  
        int count = 0;//记录行号  
        Iterator<String> it = insureMap.keySet().iterator();
        while(it.hasNext()) {
        	int rowNum = count;
        	Row row = sheet.createRow(rowNum);  
            row.setHeightInPoints(14);//设置行高  
            
            String value = it.next();
            if(value.startsWith("title")) {
            	Cell cell = row.createCell(0);  
            	cell.setCellValue(insureMap.get(value));//设置单元格的值  
//            	cell.setCellStyle(cellStyleBody);//设置样式  
            	
            	cell = row.createCell(1);  
            	cell.setCellValue("");//设置单元格的值  
//            	cell.setCellStyle(cellStyleBody);//设置样式  
            	
        		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 1));  
            	setMergedStyle(rowNum, rowNum, 0, 1, sheet, cellStyleTitle);
            	
            } else {
            	Cell cell = row.createCell(0);  
            	cell.setCellValue(value.replace("_", ""));//设置单元格的值  
            	cell.setCellStyle(cellStyleBody);//设置样式  

            	if("下载链接".equals(insureMap.get(value))) {
            		
            		if(StringUtils.isNotBlank(imglink)) {
            			CreationHelper createHelper = wb.getCreationHelper();  
                		Hyperlink link = createHelper.createHyperlink(Hyperlink.LINK_URL);  
                        link.setAddress(uploadPdfUrl + imglink);  
                        
                        cell = row.createCell(1);  
                    	cell.setCellValue(insureMap.get(value));//设置单元格的值  
                        cell.setHyperlink(link);  
                        cell.setCellStyle(cellStyleLink);  
            		} else {
            			cell = row.createCell(1);  
                    	cell.setCellValue("");//设置单元格的值  
            		}
            		
            	} else if("下载链接2".equals(insureMap.get(value))) {
            		
            		if(StringUtils.isNotBlank(imglink2)) {
            			CreationHelper createHelper = wb.getCreationHelper();  
                		Hyperlink link = createHelper.createHyperlink(Hyperlink.LINK_URL);  
                        link.setAddress(uploadPdfUrl + imglink2);  
                        
                        cell = row.createCell(1);  
                    	cell.setCellValue(insureMap.get(value).replace("2", ""));//设置单元格的值  
                        cell.setHyperlink(link);  
                        cell.setCellStyle(cellStyleLink);  
            		} else {
            			cell = row.createCell(1);  
                    	cell.setCellValue("");//设置单元格的值  
            		}
                    
            	} else {
            		cell = row.createCell(1);  
                	cell.setCellValue(insureMap.get(value));//设置单元格的值  
                	cell.setCellStyle(cellStyleBody);//设置样式  
            	}
            }
        	count++;
        }
        
        /**
         * 放入被保人员工名单
         */
    	Row row = sheet.createRow(count);  
        row.setHeightInPoints(14);//设置行高  
   
        Cell cell = row.createCell(0);  
    	cell.setCellValue("员工列表（共"+employeeList.size()+"）人");//设置单元格的值  
    	
    	cell = row.createCell(1);  
    	cell.setCellValue("");//设置单元格的值  
    	
        cell = row.createCell(2);  
    	cell.setCellValue("");//设置单元格的值  
    	
    	sheet.addMergedRegion(new CellRangeAddress(count, count, 0, 2));  
    	setMergedStyle(count, count, 0, 2, sheet, cellStyleTitle);
    	
    	count++;
    	
    	row = sheet.createRow(count);  
    	row.setHeightInPoints(14);//设置行高  
        
    	cell = row.createCell(0);  
    	cell.setCellValue("姓名");//设置单元格的值  
    	
    	cell = row.createCell(1);  
    	cell.setCellValue("身份证");//设置单元格的值  
    	
    	cell = row.createCell(2);  
    	cell.setCellValue("职业类别");//设置单元格的值  
    
    	count++;
        
        for (Employee employee : employeeList) {
        	int rowNum = count;
        	row = sheet.createRow(rowNum);  
            row.setHeightInPoints(14);//设置行高  
            
            cell = row.createCell(0);  
        	cell.setCellValue(employee.getName());//设置单元格的值  
        	
        	cell = row.createCell(1);  
        	cell.setCellValue(employee.getCardno());//设置单元格的值  
        	
        	cell = row.createCell(2);  
        	cell.setCellValue(employee.getVocation() + "类");//设置单元格的值  
        	
        	count++;
		}
        
        
//        long t11 = System.currentTimeMillis();  
//        System.out.println("生成Workbook共花费："+(t11-t1)+"毫秒");  
//        //写入文件  
//        FileOutputStream out;
//		try {
//			out = new FileOutputStream("D:/test/exp.xlsx");
//			wb.write(out);  
//	        out.close();  
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		
//        long t2 = System.currentTimeMillis();  
//        System.out.println("把Workbook写入文件共花费："+(t2-t11)+"毫秒");  
//        System.out.println("生成文件结束，共花费："+(t2-t1)+"毫秒");  
        
	}
	
	/**
	 * 获取发票类型
	 * Administrator
	 * 2017年6月30日
	 * String
	 */
	private String getReceiptType(String type) {
		
		String rece = "无需发票";
		if("1".equals(type)) {
			rece = "增值税普通发票（电子发票）";
		} else if("2".equals(type)) {
			rece = "增值税普通发票（纸质发票）";
		} else if("3".equals(type)) {
			rece = "增值税专用发票（纸质发票）";
		} 
		
		return rece;
	}
	
	/**
	 * 获取保障计划内容
	 * Administrator
	 * 2017年6月30日
	 * Map<String,String>
	 */
	private Map<String, String> getBaozhangMap(int amount) {
		
		Map<String, String> insureMap = new LinkedHashMap<String, String>();
        
		if(amount == 20) {
			insureMap.put("工伤或意外身故保障","20万");
	        insureMap.put("工伤或意外伤残保障","20万");
	        insureMap.put("工伤或意外医疗保障","2万");
	        insureMap.put("扩展特殊天气保障","含");
	        insureMap.put("扩展海外公干保障","含");
	        insureMap.put("扩展就餐时间保障","含");
//	        insureMap.put("扩展承保情景","含");
	        insureMap.put("扩展运动或娱乐活动保障","含");
	        insureMap.put("扩展上下班保障","含");
	        insureMap.put("扩展24小时人身意外伤害","含");
	        insureMap.put("误工津贴","60元/天");
	        insureMap.put("住院津贴","60元/天");
	        insureMap.put("法律费用","6万");
		} else if(amount == 10){
			insureMap.put("工伤或意外身故保障","10万");
	        insureMap.put("工伤或意外伤残保障","10万");
	        insureMap.put("工伤或意外医疗保障","1万");
	        insureMap.put("扩展特殊天气保障","含");
	        insureMap.put("扩展海外公干保障","含");
	        insureMap.put("扩展就餐时间保障","含");
//	        insureMap.put("扩展承保情景","含");
	        insureMap.put("扩展运动或娱乐活动保障","含");
	        insureMap.put("扩展上下班保障","含");
	        insureMap.put("扩展24小时人身意外伤害","含");
	        insureMap.put("误工津贴","60元/天");
	        insureMap.put("住院津贴","60元/天");
	        insureMap.put("法律费用","3万");
	        
		} else if(amount == 30) {
			insureMap.put("工伤或意外身故保障","30万");
	        insureMap.put("工伤或意外伤残保障","30万");
	        insureMap.put("工伤或意外医疗保障","3万");
	        insureMap.put("扩展特殊天气保障","含");
	        insureMap.put("扩展海外公干保障","含");
	        insureMap.put("扩展就餐时间保障","含");
//	        insureMap.put("扩展承保情景","含");
	        insureMap.put("扩展运动或娱乐活动保障","含");
	        insureMap.put("扩展上下班保障","含");
	        insureMap.put("扩展24小时人身意外伤害","含");
	        insureMap.put("误工津贴","120元/天");
	        insureMap.put("住院津贴","120元/天");
	        insureMap.put("法律费用","9万");
		} else if(amount == 50) {
			insureMap.put("工伤或意外身故保障","50万");
	        insureMap.put("工伤或意外伤残保障","50万");
	        insureMap.put("工伤或意外医疗保障","5万");
	        insureMap.put("扩展特殊天气保障","含");
	        insureMap.put("扩展海外公干保障","含");
	        insureMap.put("扩展就餐时间保障","含");
//	        insureMap.put("扩展承保情景","含");
	        insureMap.put("扩展运动或娱乐活动保障","含");
	        insureMap.put("扩展上下班保障","含");
	        insureMap.put("扩展24小时人身意外伤害","含");
	        insureMap.put("误工津贴","180元/天");
	        insureMap.put("住院津贴","180元/天");
	        insureMap.put("法律费用","15万");
		} else {
//			insureMap.put("工伤或意外身故保障","10万");
//	        insureMap.put("工伤或意外伤残保障","10万");
//	        insureMap.put("工伤或意外医疗保障","1万");
//	        insureMap.put("扩展特殊天气保障","含");
//	        insureMap.put("扩展海外公干保障","含");
//	        insureMap.put("扩展就餐时间保障","含");
////	        insureMap.put("扩展承保情景","");
//	        insureMap.put("扩展运动或娱乐活动保障","含");
//	        insureMap.put("扩展上下班保障","含");
//	        insureMap.put("扩展24小时人身意外伤害","含");
//	        insureMap.put("误工津贴","60元/天");
//	        insureMap.put("住院津贴","60元/天");
//	        insureMap.put("法律费用","3万");
		}
		
		
		return insureMap;
	}
	
	
	/** 
     * 设置合并单元格的样式 
     * @param i first row(0-based) 
     * @param j last row(0-based) 
     * @param k first column(0-based) 
     * @param l last column(0-based) 
     * @param sheet excel的sheet 
     * @param cellStylehead excel的样式 
     */  
    private static void setMergedStyle(int i, int j, int k, int l, Sheet sheet, CellStyle cellStylehead) {  
        for(int mm=i;mm<=j;mm++){  
            Row row = sheet.getRow(mm);  
            if(row==null){  
                row = sheet.createRow(mm);  
            }  
            for(int nn=k;nn<=l;nn++){  
                Cell cell = row.getCell(nn);  
                if(cell==null){  
                    cell = row.createCell(nn);  
                }  
                cell.setCellStyle(cellStylehead);  
            }  
        }  
    }  
    
	public static void main(String[] args) {
//		buildExcel();
	}
}
