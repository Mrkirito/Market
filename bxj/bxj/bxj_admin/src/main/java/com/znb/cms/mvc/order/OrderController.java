package com.znb.cms.mvc.order;

import com.ibaoxianjia.message.dto.MailMessageDto;
import com.ibaoxianjia.message.dto.SmsMessageDto;
import com.ibaoxianjia.message.service.MessageOpenAPI;
import com.znb.cms.common.AjaxResult;
import com.znb.cms.common.Result;
import com.znb.cms.excel.InsureExport;
import com.znb.cms.excel.OrderExoprt;
import com.znb.cms.model.dto.OrderDto;
import com.znb.cms.service.IEmployeeService;
import com.znb.cms.service.IInsureService;
import com.znb.cms.service.IIsurantService;
import com.znb.cms.service.IOrderService;
import com.znb.cms.util.ExcelUtils;
import com.znb.cms.util.PDFTempletUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private IOrderService orderService;

	@Autowired
	private MessageOpenAPI messageOpenAPI;
	
	@Autowired
	private IInsureService insureService;
	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IIsurantService isurantService;
	
	@Value("${upload_pdf_url}")
	private String uploadPdfUrl ;
	
	@Value("${platform_static_path}")
	private String platformStaticPath ;
	/**
	 * 
	* @author yuanxin
	* @date 2017年5月18日下午4:56:56
	* @version <b>1.0.0</b>
	* 订单首页
	 */
	@RequestMapping("index.jhtml")
	public ModelAndView index() {
		ModelAndView view=new ModelAndView("order/orderlist");
		return view;
	}


//	@RequestMapping("order_detail.page")
//	public ModelAndView orderDetail() {
//		ModelAndView view=new ModelAndView("order/order_detail");
//		return view;
//	}
	
	/**
	 * 
	* @author yuanxin
	* @date 2017年5月18日下午4:57:59
	* @version <b>1.0.0</b>
	* 查询符合条件订单
	* @return
	 */
	@RequestMapping("/list.json")
	@ResponseBody
	public Result suggestionList(OrderDto orderDto) {
		Result result = new Result();
		int count = orderService.selectCount(orderDto);
		if (count > 0) {
			result.setModel(orderService.selectOrderByOrder(orderDto));
		}
		orderDto.setTotalItem(count);
		result.setQuery(orderDto);
		return result;
	}
	
	
	/**
	 * 修改投保人公司信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/updateInsureMessage.json")
	@ResponseBody
	public Result updateInsureMessage(Integer id) {
		Result result = new Result();
		insureService.updateInsureMessage(id,"查询成功");
		result.setSuccess(true);
		result.setMsg("修改成功");
		return result;
	}
	
	
	/**
	* @author yuanxin
	* @date 2017年5月18日下午4:58:35
	* @version <b>1.0.0</b>
	* 删除订单
	* @return
	*/
	@RequestMapping("/del.json")
	@ResponseBody
	public Result del(Integer id) {
		Result result = new Result();
		orderService.delOrder(id);
		result.setSuccess(true);
		result.setMsg("作废成功");
		return result;
	}
	/**
	 * 读取pdf文件
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/downSinglePolicy.json")
	public String downSinglePolicy(HttpServletResponse response,String policyUrl){
		//设置返回类型
		response.reset();  
		response.setContentType("application/pdf;charset=gb2312");
		response.setHeader("Content-Disposition", "attachment;filename="+ ExcelUtils.encodeFilename("policy.pdf"));
		PDFTempletUtil pdfTT = new PDFTempletUtil();
    	pdfTT.setTemplatePdfPath(uploadPdfUrl+policyUrl); 
    	try {
			pdfTT.readPdf(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	* @author yuanxin
	* @date 2017年5月18日下午4:59:05
	* @version <b>1.0.0</b>
	* 下载订单
	* @return
	 */
	@RequestMapping("/downOrder.json")
	public Object downOrder(HttpServletResponse response,Integer id){
		OrderExoprt orderExoprt = new OrderExoprt();
		orderExoprt.setEmployeeService(employeeService);
		orderExoprt.setOrderService(orderService);
		orderExoprt.setId(id);
		return new ModelAndView(orderExoprt);
	}
	
	@RequestMapping("/downOrderModel.json")
	public Object downOrderModel(HttpServletResponse response,Integer id){
		InsureExport insureExport = new InsureExport();
		insureExport.setEmployeeService(employeeService);
		insureExport.setOrderService(orderService);
		insureExport.setId(id);
		insureExport.setUploadPdfUrl(platformStaticPath);
		return new ModelAndView(insureExport);
	}
	
	/**
	 * 
	* @author yuanxin
	* @date 2017年5月18日下午4:59:23
	* @version <b>1.0.0</b>
	* 更新被保人企查查验证信息
	* @return
	 */
	@RequestMapping("/updateIsurantMessage.json")
	@ResponseBody
	public Result updateIsurantMessage(Integer id) {
		Result result = new Result();
		isurantService.updateIsurantMessage(id, "查询成功");
		result.setSuccess(true);
		result.setMsg("修改成功");
		return result;
	}
	
	
	
	
	
	
//	@RequestMapping("/detail.json")
//	@ResponseBody
//	public Result detail(Integer id) {
//		Result result = new Result();
//		result.setModel(orderService.getOrder(id));
//		return result;
//	}
	
	
	
	/**
	* @author yuanxin
	* @date 2017年5月18日下午5:01:01
	* @version <b>1.0.0</b>
	* 更新订单信息
	* @return
	*/
	@RequestMapping("/updateOrder.json")
	@ResponseBody
	public Object updateOrder(OrderDto order) throws Exception {
			return orderService.updateOrder(order);
	}
	
	/**
	* @author yuanxin
	* @date 2017年5月18日下午5:01:14
	* @version <b>1.0.0</b>
	* 导入保单
	* @return
	*/
	@RequestMapping(value ="/importSinglePolicy.json",method=RequestMethod.POST)
	@ResponseBody
	public AjaxResult importSingleProduct(MultipartFile file,Integer id,HttpServletRequest request, HttpServletResponse response) {
		AjaxResult result=null;
		try {
			result=orderService.importSinglePolicy(file,id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value ="/sendSMS.jhtml")
	public ModelAndView sendSMS(){
		try {
			SmsMessageDto smsMessage = new SmsMessageDto();
		    smsMessage.addPhone("13072159526");
		    smsMessage.addPhone("13072159526");
		    smsMessage.setSysid("core_1001");
		    smsMessage.setSmsCode("BXJ_BATCH_SEND");
		    smsMessage.setIp("192.168.10.240");
		    Map<String, String> smsInfoMap = new HashMap<String, String>();
		    smsInfoMap.put("username", "帅哥");
		    smsInfoMap.put("content", "【保险家】TAIN测测短信功能");

		    smsMessage.setSmsInfoMap(smsInfoMap);
		    boolean result = messageOpenAPI.sendSms(smsMessage);
			System.out.println("Sending sms!! " + result);
			// 提现申请邮件通知
			MailMessageDto messageDto = new MailMessageDto();
			messageDto.setMailCode("CASH_APPLY_TMP");
			messageDto.setSysid("sysid");
			messageDto.addTo("huojiajie@baobaogroup.com");
			messageDto.setMailInfoMap(new HashMap<String, String>());
			result = messageOpenAPI.sendMail(messageDto);
			System.out.println("Sending mail!! " + result);
        } catch (Exception ex) {
        	System.out.println("提现通知邮件发送异常，异常原因：" + ex.toString());
        }
		ModelAndView view=new ModelAndView("order/orderlist");
		return view;
	}


	@RequestMapping(value ="/testSendMail.json")
	@ResponseBody
	public Object testSendMail(){
		boolean result=false;
		try {
			MailMessageDto messageDto = new MailMessageDto();
			messageDto.setMailCode("CASH_APPLY_TMP");
			messageDto.setSysid("10001");
			messageDto.addTo("420121738@qq.com");
			messageDto.setMailInfoMap(new HashMap<String, String>());
			result = messageOpenAPI.sendMail(messageDto);
		} catch (Exception ex) {
			logger.error("提现通知邮件发送异常，异常原因：",ex);
//			System.out.println("提现通知邮件发送异常，异常原因：" + ex.toString());
		}
		return result;
	}


	@RequestMapping(value ="/testSendSms.json")
	@ResponseBody
	public Object testSendSms(HttpServletRequest request){
		boolean result=false;
		try {
			SmsMessageDto smsMessage = new SmsMessageDto();
			smsMessage.addPhone("15921490998");
			smsMessage.setSysid("core_1001");
			smsMessage.setSmsCode("BXJ_BATCH_SEND");
			smsMessage.setIp(getIp2(request));
			Map<String, String> smsInfoMap = new HashMap<String, String>();
			smsInfoMap.put("username", "帅哥");
			smsInfoMap.put("content", "【保险家】TAIN测测短信功能");
			smsMessage.setSmsInfoMap(smsInfoMap);
			result = messageOpenAPI.sendSms(smsMessage);
			return result;
		} catch (Exception ex) {
			logger.error("短信发送异常，异常原因：",ex);
		}
		return result;
	}

	private static String getIp2(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
			//多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if(index != -1){
				return ip.substring(0,index);
			}else{
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
			return ip;
		}
		return request.getRemoteAddr();
	}
}
