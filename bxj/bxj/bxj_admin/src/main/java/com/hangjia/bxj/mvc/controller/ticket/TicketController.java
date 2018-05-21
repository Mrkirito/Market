package com.hangjia.bxj.mvc.controller.ticket;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hangjia.bxj.common.Result;
import com.hangjia.bxj.excel.view.ViewExcelImport;
import com.hangjia.bxj.model.LoginUser;
import com.hangjia.bxj.model.SalesTicketOrder;
import com.hangjia.bxj.model.SalesTicketOrderDetail;
import com.hangjia.bxj.model.SalesTicketSit;
import com.hangjia.bxj.mvc.common.AdminConstants;
import com.hangjia.bxj.query.ticket.SalesTicketDocumentQuery;
import com.hangjia.bxj.query.ticket.SalesTicketQuery;
import com.hangjia.bxj.service.ticket.SalesTicketService;
import com.hangjia.bxj.vo.SalesTicketOrderAttendPeoplesVo;

@Controller
@RequestMapping("/ticket")
public class TicketController {
	@Autowired
	private SalesTicketService service;
	
	@Autowired
	private HttpSession session;
	
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, true));
    }

	protected String getLoginUser() {
		String userCode = "";
		Object o = session.getAttribute(AdminConstants.LOGIN_USER);
		if (o != null) {
			LoginUser user = (LoginUser) o;
			userCode = user.getUserCode();
		}
		return userCode;
	}
	@RequestMapping("/index")
	public ModelAndView meets() {
		ModelAndView modelAndView = new ModelAndView("ticket/meet_index");
		modelAndView.addObject("surplusTickets", service.getMeetTicketCountList());
		modelAndView.addObject("salemans", service.getSaleMansList(getLoginUser()));
		modelAndView.addObject("now",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return modelAndView;
	}
	
	@RequestMapping("/index2")
	public ModelAndView meets2() {
		ModelAndView modelAndView = new ModelAndView("ticket/meet_index2");
		modelAndView.addObject("surplusTickets", service.getMeetTicketCountList());
		modelAndView.addObject("salemans", service.getSaleMansList(getLoginUser()));
		modelAndView.addObject("now",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return modelAndView;
	}

	@RequestMapping("/editParticipant")
	public ModelAndView editParticipant() {
		ModelAndView modelAndView = new ModelAndView("ticket/editParticipant");
		String userCode = getLoginUser();
		modelAndView.addObject("surplusTickets", service.getMeetTicketCountList());
		modelAndView.addObject("salemans", service.getSaleMansList(userCode));
		modelAndView.addObject("now",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return modelAndView;
	}
	
	@RequestMapping("/index3")
	public ModelAndView meets3(Long orderId) {
		ModelAndView modelAndView = new ModelAndView("ticket/meet_index3");
		modelAndView.addObject("surplusTickets", service.getMeetTicketCountList());
		modelAndView.addObject("salemans", service.getSaleMansList(getLoginUser()));
		modelAndView.addObject("orderId",orderId);
		modelAndView.addObject("now",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return modelAndView;
	}
	
	@RequestMapping("/getTicketCount")
	@ResponseBody
	public Result getTicketCount(SalesTicketQuery query){
		Result result = new Result();
		result.setModel(service.getSalesTicketTotal(query));
		return result;
	}
	
	
	@RequestMapping("/getSalesmanTickets")
	@ResponseBody
	public Result getSalesmanTickets(SalesTicketQuery query) {       
		Result result = new Result();
		int count=service.getSalesTicketDetailCount(query);
		if(count>0){
			result.setModel(service.getSalesTicketDetails(query));			
		}
		query.setTotalItem(count);
		result.setQuery(query);
		return result;
	}
	
	@RequestMapping("/getSalesmanTicketAttendPeoples")
	@ResponseBody
	public Result getSalesmanTicketAttendPeoples(SalesTicketQuery query) {       
		Result result = new Result();
		int count=service.queryAttendPeoplesCount(query);
		if(count>0){
			result.setModel(service.queryAttendPeoples(query));			
		}
		query.setTotalItem(count);
		result.setQuery(query);
		return result;
	}
	
	
	@RequestMapping("/getAttendPeopleByFid")
	@ResponseBody
	public Result getAttendPeopleByFid(Long fid) {       
		Result result = new Result();
		result.setModel(service.getAttendPeopleByFid(fid));
		return result;
	}
	@RequestMapping("/updateAttendPeoples")
	@ResponseBody
	public Object updateAttendPeoples(SalesTicketOrderAttendPeoplesVo vo) {       
		return service.updateAttendPeoples(vo);
	}	
	@RequestMapping("/sendAttendMsg")
	@ResponseBody
	public Object sendAttendMsg(Long fid){
		return service.sendAttendMsg(fid);
	}

	@RequestMapping("/index4")
	public ModelAndView meets4() {
		ModelAndView modelAndView = new ModelAndView("ticket/meet_index4");
		modelAndView.addObject("surplusTickets", service.querySelectSalesTicketBasics());
		modelAndView.addObject("salemans", service.getSaleMansList(getLoginUser()));
		modelAndView.addObject("now",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return modelAndView;
	}
	
	
	@RequestMapping("/index5")
	public ModelAndView meets5() {
		ModelAndView modelAndView = new ModelAndView("ticket/meet_index5");
		modelAndView.addObject("surplusTickets",service.querySelectSalesTicketBasics());
		modelAndView.addObject("salemans", service.getSaleMansList(getLoginUser()));
		modelAndView.addObject("now",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return modelAndView;
	}
	
	@RequestMapping("/getDocuments")
	@ResponseBody
	public Result getDocuments(SalesTicketDocumentQuery query) {
		Result result = new Result();
		int count=service.getLockSalesTicketDocumentsCount(query);
		if(count>0){
			result.setModel(service.getLockSalesTicketDocuments(query));
		}
		query.setTotalItem(count);
		result.setQuery(query);
		return result;
	}

	@RequestMapping("/lockDocuments")
	@ResponseBody
	public Map<String, Object> lockDocuments(SalesTicketDocumentQuery query){
		return service.lockDocuments(query);
	}


	@RequestMapping("/clearMeet")
	@ResponseBody
	public Result clearMeet(Long basicId) {
		return service.clearMeet(basicId);
	}
	
	@RequestMapping("/attendMeet.json")
	@ResponseBody
	public Result attendMeet(SalesTicketOrderAttendPeoplesVo vo) {
		return service.attendMeet(vo);
	}
	
	
	@RequestMapping("/updateAttendPeopleSit.json")
	@ResponseBody
	public Result updateAttendPeopleSit(Long fid,Long docId) {
		return service.updateAttendPeopleSit(fid,docId);
	}
	
	@RequestMapping("/getSits")
	@ResponseBody
	public List<SalesTicketSit> getSits(Integer fid) {
		return service.getSitsByBasicId(fid);
	}
	@RequestMapping("/excel")
	@ResponseBody
	public Object excel(MultipartFile file,SalesTicketOrder order) {
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			
			String name =file.getOriginalFilename().toUpperCase();
			Workbook workbook = null;
			if(name.endsWith(".XLS")){
				workbook=new HSSFWorkbook(file.getInputStream()); 
			}else if(name.endsWith(".XLSX")){
				workbook=new XSSFWorkbook(file.getInputStream());
			}else{
				map.put("success", false);
				map.put("status", "500");
				map.put("msg", "文件格式错误");
				return map;
			}
			Sheet sheet =workbook.getSheetAt(0);
			int m = sheet.getLastRowNum();
			if (m == 0) {
				map.put("success", false);
				map.put("status", "500");
				map.put("msg", "文件内容为空");
				return map;
			}
			List<SalesTicketOrderDetail> list = new ArrayList<SalesTicketOrderDetail>();
			Set<String> phones = new HashSet<String>();
			for (int j = 1; j < m + 1; j++) {
				Row n = sheet.getRow(j);
				if(n!=null){					
					String userName = getCellValue(n.getCell(0));
					String userPhone = getCellValue(n.getCell(1));
					String company = getCellValue(n.getCell(2));
					String bussinesHall = getCellValue(n.getCell(3));
					if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(userPhone)) {
						if(valdatePhone(userPhone)){
							SalesTicketOrderDetail detail = new SalesTicketOrderDetail();
							detail.setUserName(userName);
							detail.setUserPhone(userPhone);
							detail.setCompany(company);
							detail.setBusinessHall(bussinesHall);
							list.add(detail);
							phones.add(detail.getUserPhone());
						}else{
							map.put("success", false);
							map.put("status", "500");
							map.put("msg", "请检查EXCEL中手机号码是否正确");
							return map;
						}
					}
				}
			}
			int num=order.getActual()+order.getFree();
			if (list.size() != num) {
				map.put("success", false);
				map.put("status", "500");
				map.put("msg", "EXCEL中的参会人数与订单总人数不一致");
				return map;
			}
			if(phones.size()!=num){
				map.put("success", false);
				map.put("status", "500");
				map.put("msg", "EXCEL中的参会者手机号缺失");
				return map;
			}
			List<String> ph=new ArrayList<String>();
			ph.addAll(phones);
			order.setPhones(ph);
			int count = service.getPhoneCountByMeet(order);
			if (count > 1) {
				String t=service.getSystemHasPhones(order);
				map.put("success", false);
				map.put("status", "500");
				map.put("phones", t);
				map.put("msg", "EXCEL中有手机号在系统中重复报名");
				return map;
			}
			order.setNum(num);
			order.setAttendNum(num);
			order.setOrderDetails(list);
			return service.excelImport(order);
		} catch (Exception e) {
			
			map.put("success", false);
			map.put("msg", "导入失败...");
			map.put("status", "500");
			e.printStackTrace();
			return map;
		}
	}
	
	public void setFillColorBody(Workbook workbook,Row n) {
		if(workbook instanceof HSSFWorkbook){			
			HSSFCellStyle cellStyle = (HSSFCellStyle) workbook.createCellStyle();
			HSSFFont font = (HSSFFont) workbook.createFont();
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
			n.setRowStyle(cellStyle);
		}else if(workbook instanceof XSSFWorkbook){
			XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();
			XSSFFont font = (XSSFFont) workbook.createFont();
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
			n.setRowStyle(cellStyle);			
		}
	}
	public boolean valdatePhone(String mobiles) {
		Pattern p = Pattern.compile("^1[34578]\\d{9}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	public String getCellValue(Cell cell) {  
        String ret="";  
        if(cell!=null){        	
        	switch (cell.getCellType()) {  
        	case Cell.CELL_TYPE_BLANK:  
        		ret = "";  
        		break;  
        	case Cell.CELL_TYPE_BOOLEAN:  
        		ret = String.valueOf(cell.getBooleanCellValue());  
        		break;  
        	case Cell.CELL_TYPE_ERROR:  
        		ret = null;  
        		break;  
        	case Cell.CELL_TYPE_FORMULA:  
        		Workbook wb = cell.getSheet().getWorkbook();  
        		CreationHelper crateHelper = wb.getCreationHelper();  
        		FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();  
        		ret = getCellValue(evaluator.evaluateInCell(cell));  
        		break;  
        	case Cell.CELL_TYPE_NUMERIC:  
        		if (DateUtil.isCellDateFormatted(cell)) {   
        			Date theDate = cell.getDateCellValue();  
        			ret = new SimpleDateFormat("yyyy-MM-dd").format(theDate);  
        		} else {   
        			ret = NumberToTextConverter.toText(cell.getNumericCellValue());  
        		}  
        		break;  
        	case Cell.CELL_TYPE_STRING:  
        		ret = cell.getRichStringCellValue().getString();  
        		break;  
        	default:  
        		ret = "";  
        	}  
        }
        return ret.trim();  
    }  
	
	
	@RequestMapping(value ="/exportExcel", method = RequestMethod.GET)
	public Object exportExcel(HttpServletRequest request, HttpServletResponse response) {
		ViewExcelImport viewExcel = new ViewExcelImport(service);
		return new ModelAndView(viewExcel);
	}
}
