package com.znb.cms.excel;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.znb.cms.model.dto.EmployeeDto;
import com.znb.cms.model.dto.OrderDto;
import com.znb.cms.model.mapper.Employee;
import com.znb.cms.service.IEmployeeService;
import com.znb.cms.service.IOrderService;
import com.znb.cms.util.ExcelUtils;

public class OrderExoprt extends AbstractExcelView{
	
	private IEmployeeService employeeService;
	
	private IOrderService orderService;
	private Integer  id;
	
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
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		OrderDto  orderDto = new OrderDto();
		orderDto.setId(id);
		List<OrderDto> orderList = orderService.selectOrderByOrder(orderDto);
		List<Employee> employeeList = employeeService.selectOrderEmployee(orderList.get(0).getEmployeeIds());
		List<EmployeeDto> listEmployeeDto = employeeService.selectByOrderId(id);
		addOrderInfoSheet(orderList,workbook);
		addEmployeeInfoSheet(employeeList,workbook);
		addEmployeeSheet(listEmployeeDto,workbook);
		reduceEmployeeSheet(listEmployeeDto,workbook);
        response.reset();  
		response.setContentType("application/vnd.ms-excel;charset=gb2312");
		response.setHeader("Content-Disposition", "attachment;filename="+ ExcelUtils.encodeFilename("人员名单.xls"));
		OutputStream ouputStream = response.getOutputStream();
		workbook.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}
	
	/**
	 * 添加员工sheet页
	 * @param employeeList
	 * @param workbook
	 */
	private void addEmployeeInfoSheet(List<Employee> employeeList,HSSFWorkbook workbook){
		int rowNum=0;
		HSSFSheet sheet1=workbook.createSheet();
		workbook.setSheetName(1, "员工信息");
		HSSFCellStyle cellStyle1=ExcelUtils.getHSSFCellStyle(workbook);
		sheet1.setColumnWidth(1, 3000);
		sheet1.setColumnWidth(2, 4000);
		sheet1.setColumnWidth(3, 4000);
		sheet1.setColumnWidth(4, 4000);
		sheet1.setColumnWidth(5, 4000);
		sheet1.setColumnWidth(6, 4000);
		HSSFRow row1=sheet1.createRow(rowNum);
		HSSFCell cell1=null;
		String[] column1 = { "序号", "被保险人姓名", "证件类型", "证件号码", "性别", "出生日期", "职业类别" };
		for (int i = 0; i < column1.length; i++) {
			cell1 = row1.createCell(i);
			cell1.setCellValue(column1[i]);
			cell1.setCellStyle(cellStyle1);
		}
		for (int i = 0; i < employeeList.size(); i++) {
			++rowNum;
			Employee employee=employeeList.get(i);
			row1 = sheet1.createRow(rowNum);			
			cell1 = row1.createCell(0);
			cell1.setCellValue(employee.getId());			
			cell1 = row1.createCell(1);
			cell1.setCellValue(employee.getName());
			cell1 = row1.createCell(2);
			//修改  2017.07.19 证件类型问题
			if(employee.getCardTypeId().equals(0)){
				cell1.setCellValue("身份证");
			}else if(employee.getCardTypeId().equals(1)){
				cell1.setCellValue("护照");
			}else if(employee.getCardTypeId().equals(2)){
				cell1.setCellValue("军官证");
			}else if(employee.getCardTypeId().equals(3)){
				cell1.setCellValue("港澳通行证");
			}else{
				cell1.setCellValue("其他");
			}
			cell1 = row1.createCell(3);
			cell1.setCellValue(employee.getCardno());
			cell1 = row1.createCell(4);
			cell1.setCellValue(employee.getGender() == "0" ? "男":"女");
			cell1 = row1.createCell(5);
			cell1.setCellValue(employee.getBirthday());
			cell1 = row1.createCell(6);
			cell1.setCellValue(employee.getVocation()+"类");
		}
	}
	
	/**
	 * 添加保单信息页
	 * @param listEmployeeDto
	 * @param workbook
	 */
	private void addOrderInfoSheet(List<OrderDto> orderList,HSSFWorkbook workbook){
		int rowNum=0;
		HSSFSheet sheet=workbook.createSheet();
		workbook.setSheetName(0, "保险方案");
		HSSFCellStyle cellStyle=ExcelUtils.getHSSFCellStyle(workbook);
		sheet.setColumnWidth(1, 3000);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 4000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 4000);
		sheet.setColumnWidth(6, 4000);
		sheet.setColumnWidth(7, 4000);
		HSSFRow row=sheet.createRow(rowNum);
		HSSFCell cell=null;
		String[] column = {"保险名称", "投保人", "被保险人", "保单生效时间", "保费收取时长", "保费总价(元)", "保费优惠(元)","保费实付(元)" };
		for (int i = 0; i < column.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(column[i]);
			cell.setCellStyle(cellStyle);
		}
		row = sheet.createRow(1);			
		cell = row.createCell(0);
		cell.setCellValue(orderList.get(0).getInsuranceName());			
		cell = row.createCell(1);
		cell.setCellValue(orderList.get(0).getInsure().getCompanyName());
		cell = row.createCell(2);
		cell.setCellValue(orderList.get(0).getIsurant().getCompanyName());
		cell = row.createCell(3);
		cell.setCellValue(orderList.get(0).getBeginDate());
		cell = row.createCell(4);
		cell.setCellValue(orderList.get(0).getDuration().equalsIgnoreCase("1") ? "一月" :"一年");
		cell = row.createCell(5);
		cell.setCellValue(orderList.get(0).getPrice().doubleValue());
		cell = row.createCell(6);
		cell.setCellValue(0.0);
		cell = row.createCell(7);
		cell.setCellValue(orderList.get(0).getPrice().doubleValue());
	}
	
	/**
	 * 添加员工新增信息页
	 * @param listEmployeeDto
	 * @param workbook
	 */
	private void addEmployeeSheet(List<EmployeeDto> listEmployeeDto,HSSFWorkbook workbook){
		int rowNum=0;
		HSSFSheet sheet2=workbook.createSheet();
		workbook.setSheetName(2, "新增员工名单");
		HSSFCellStyle cellStyle2=ExcelUtils.getHSSFCellStyle(workbook);
		sheet2.setColumnWidth(1, 3000);
		sheet2.setColumnWidth(2, 4000);
		sheet2.setColumnWidth(3, 4000);
		sheet2.setColumnWidth(4, 4000);
		sheet2.setColumnWidth(5, 4000);
		sheet2.setColumnWidth(6, 4000);
		sheet2.setColumnWidth(7, 4000);
		HSSFRow row2=sheet2.createRow(rowNum);
		HSSFCell cell2=null;
		String[] column2 = {"姓名","证件类型", "证件号码", "性别", "出生日期", "职业类别","保费" };
		for (int i = 0; i < column2.length; i++) {
			cell2 = row2.createCell(i);
			cell2.setCellValue(column2[i]);
			cell2	.setCellStyle(cellStyle2);
		}
		if(null != listEmployeeDto && listEmployeeDto.size() > 0){
			for (int i = 0; i < listEmployeeDto.size(); i++) {
				EmployeeDto employeedto=listEmployeeDto.get(i);
				if(employeedto.getFlag().equals(1)){
					++rowNum;
					row2 = sheet2.createRow(rowNum);			
					cell2 = row2.createCell(0);
					cell2.setCellValue(employeedto.getName());
					cell2 = row2.createCell(1);
					if(employeedto.getCardTypeId().equals("0")){
						cell2.setCellValue("身份证");
					}else if(employeedto.getCardTypeId().equals("1")){
						cell2.setCellValue("护照");
					}else if(employeedto.getCardTypeId().equals("2")){
						cell2.setCellValue("军官证");
					}else if(employeedto.getCardTypeId().equals("3")){
						cell2.setCellValue("港澳通行证");
					}else{
						cell2.setCellValue("其他");
					}
					cell2 = row2.createCell(2);
					cell2.setCellValue(employeedto.getCardno());
					cell2 = row2.createCell(3);
					cell2.setCellValue(employeedto.getGender() == "0" ? "男":"女");
					cell2 = row2.createCell(4);
					cell2.setCellValue(employeedto.getBirthday());
					cell2 = row2.createCell(5);
					cell2.setCellValue(employeedto.getVocation()+"类");
					cell2 = row2.createCell(6);
					cell2.setCellValue(employeedto.getPrice().doubleValue());
				}
			}
		}
	}
	
	
	/**
	 * 添加员工裁剪信息页
	 * @param listEmployeeDto
	 * @param workbook
	 */
	private void reduceEmployeeSheet(List<EmployeeDto> listEmployeeDto,HSSFWorkbook workbook){
		int rowNum = 0;
		HSSFSheet sheet3=workbook.createSheet();
		workbook.setSheetName(3, "删除员工名单");
		HSSFCellStyle cellStyle3=ExcelUtils.getHSSFCellStyle(workbook);
		sheet3.setColumnWidth(1, 3000);
		sheet3.setColumnWidth(2, 4000);
		sheet3.setColumnWidth(3, 4000);
		sheet3.setColumnWidth(4, 4000);
		sheet3.setColumnWidth(5, 4000);
		sheet3.setColumnWidth(6, 4000);
		sheet3.setColumnWidth(7, 4000);
		HSSFRow row3=sheet3.createRow(rowNum);
		HSSFCell cell3=null;
		String[] column3 = {"姓名","证件类型", "证件号码", "性别", "出生日期", "职业类别","保费" };
		for (int i = 0; i < column3.length; i++) {
			cell3 = row3.createCell(i);
			cell3.setCellValue(column3[i]);
			cell3	.setCellStyle(cellStyle3);
		}
		if(null != listEmployeeDto && listEmployeeDto.size() > 0){
			for (int i = 0; i < listEmployeeDto.size(); i++) {
				EmployeeDto employeedto=listEmployeeDto.get(i);
				if(employeedto.getFlag().equals(0)){
					++rowNum;
					row3 = sheet3.createRow(rowNum);			
					cell3 = row3.createCell(0);
					cell3.setCellValue(employeedto.getName());
					cell3 = row3.createCell(1);
					if(employeedto.getCardTypeId().equals("0")){
						cell3.setCellValue("身份证");
					}else if(employeedto.getCardTypeId().equals("1")){
						cell3.setCellValue("护照");
					}else if(employeedto.getCardTypeId().equals("2")){
						cell3.setCellValue("军官证");
					}else if(employeedto.getCardTypeId().equals("3")){
						cell3.setCellValue("港澳通行证");
					}else{
						cell3.setCellValue("其他");
					}
					cell3 = row3.createCell(2);
					cell3.setCellValue(employeedto.getCardno());
					cell3 = row3.createCell(3);
					cell3.setCellValue(employeedto.getGender() == "0" ? "男":"女");
					cell3 = row3.createCell(4);
					cell3.setCellValue(employeedto.getBirthday());
					cell3 = row3.createCell(5);
					cell3.setCellValue(employeedto.getVocation()+"类");
					cell3 = row3.createCell(6);
					cell3.setCellValue(employeedto.getPrice().doubleValue());
				}
			}
		}
	}

}
