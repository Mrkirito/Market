package com.hangjia.bxj.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baobao.sso.client.WebUtils;
import com.baobao.sso.filter.AuthenType;
import com.baobao.sso.filter.Login;
import com.hangjia.bxj.BXJException;
import com.hangjia.bxj.model.Customer;
import com.hangjia.bxj.model.CustomersPlanBook;
import com.hangjia.bxj.model.FreeReceiveLog;
import com.hangjia.bxj.mvc.AjaxResult;
import com.hangjia.bxj.service.customer.CustomerDetails;
import com.hangjia.bxj.service.customer.MyCustomerService;



/**
 * 客户管理。
 * @author K9999
 *
 */
@Controller
@RequestMapping("customer")
public class CustomerManagerController {
	
	@Autowired
	private MyCustomerService myCustomerService;
	
	/**
	 * 【我的客户】列表，
	 * 也是【客户管理】的首页视图。
	 * 列出当前用户的所有【客户】，不分页。
	 * @return
	 */
	@Login(AuthenType.page)
	@RequestMapping(value="myCustomers.page", method=RequestMethod.GET)
	public Object myCustomers(HttpServletRequest request) {
		Integer currentUserId = WebUtils.getMemberId(request);
		
		List<Customer> list = myCustomerService.listMyCustomers(currentUserId);
		request.setAttribute("list", list);
		request.setAttribute("id", currentUserId);
		return "customer/address_list";
	}
	
	/**
	 * 客户资料视图。
	 * 显示客户的详细资料。
	 * 此方法只供显示当前用户自己的客户，如提供的客户ID不属于当前用户，视为【无结果】。
	 * @param customerId 客户ID，指定显示哪个用户的详细资料。
	 * @return
	 */
	@Login(AuthenType.page)
	@RequestMapping(value="getCustomerDetails.page", method=RequestMethod.GET)
	public Object getCustomerDetails(HttpServletRequest request, @RequestParam Long customerId) {
		
		Integer currentUserId = WebUtils.getMemberId(request);
		
		CustomerDetails details = myCustomerService.getMyCustomerDetailsPageData(currentUserId, customerId);
		
		assertCustomerNotNull(details);
		
		Customer customer = details.getCustomer();
		customer.setUserId(currentUserId);
		request.setAttribute("model", customer);
		
		List<CustomersPlanBook> planBooks = details.getPlanBooks();
		request.setAttribute("bookList", planBooks);
		
		List<FreeReceiveLog> freeList = details.getFreeReceiveds();
		request.setAttribute("freeList", freeList);
		
		
		return "customer/customer_infom";
	}
	
	/**
	 * 修改客户资料视图。
	 * 此方法只供操作当前用户自己的客户，如提供的客户ID不属于当前用户，视为【无结果】。
	 * @param customerId 客户ID，指定需要修改哪个客户。
	 * @return
	 */
	@Login(AuthenType.page)
	@RequestMapping(value="updateMyCustomer.page", method=RequestMethod.GET)
	public Object updateMyCustomer(HttpServletRequest request, @RequestParam Long customerId) {
		Integer currentUserId = WebUtils.getMemberId(request);
		Customer customer = myCustomerService.getMyCustomerDetails(currentUserId, customerId);
		
		assertCustomerNotNull(customer);
		
		request.setAttribute("model", customer);
		return "customer/editinfom";
	}
	
	/**
	 * 修改客户资料的功能方法。
	 * 此方法只供操作当前用户自己的客户，如提供的客户ID不属于当前用户，视为【无结果】。
	 * @param customer
	 * @return
	 */
	@RequestMapping(value="updateMyCustomer.do", method=RequestMethod.POST)
	@ResponseBody
	@Login(AuthenType.json)
	public Object updateMyCustomer(HttpServletRequest request, Customer customer, MultipartFile file, @RequestParam(defaultValue="false") boolean deleteImage) {
		Integer currentUserId = WebUtils.getMemberId(request);
		customer.setUserId(currentUserId);
		
		myCustomerService.updateMyCustomer(customer, file, deleteImage);
		
//		return "redirect:getCustomerDetails.page?customerId=" + customer.getId();
		// 由 ajax 进行跳转界面。
		return new AjaxResult.redirct("customer/getCustomerDetails.page?customerId=" + customer.getId());
	}
	
	/**
	 * 删除客户。
	 * 此方法只供操作当前用户自己的客户，如提供的客户ID不属于当前用户，视为【无结果】。
	 * @param custom
	 * @return
	 */
	@Login(AuthenType.page)
	@RequestMapping(value="deleteMyCustomer.do", method=RequestMethod.GET)
	public Object deleteMyCustomer(HttpServletRequest request, @RequestParam Long customerId) {
		Integer currentUserId = WebUtils.getMemberId(request);
		myCustomerService.deleteMyCustomer(currentUserId, customerId);
		return "redirect:myCustomers.page";
	}
	
	/**
	 * 删除我的客户的计划书。
	 * @param customerId
	 * @param relId
	 * @return
	 */
	@Login(AuthenType.page)
	@RequestMapping(value="deleteMyCustomerBook.do", method=RequestMethod.GET)
	public Object deleteMyCustomerBook(HttpServletRequest request, @RequestParam Long customerId, @RequestParam Integer relId) {
		Integer currentUserId = WebUtils.getMemberId(request);
		myCustomerService.deleteMyCustomerBook(currentUserId, relId);
		return "redirect:getCustomerDetails.page?customerId=" + customerId;
	}
	
	private void assertCustomerNotNull(Object customer) {
		if (customer == null) {
			throw new BXJException("没有找到该客户的记录");
		}
	}
	
}
