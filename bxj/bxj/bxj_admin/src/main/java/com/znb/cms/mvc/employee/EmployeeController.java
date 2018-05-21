package com.znb.cms.mvc.employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.znb.cms.common.Result;
import com.znb.cms.model.mapper.Order;
import com.znb.cms.service.IEmployeeService;
import com.znb.cms.service.IOrderService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private IOrderService orderService;
	@Autowired
	private IEmployeeService employeeService;
	
	
	@RequestMapping("/list.json")
	@ResponseBody
	public Result suggestionList(Integer id) {
		Result result = new Result();
		Order order = orderService.selectByPrimaryKey(id);
		result.setModel(employeeService.selectOrderEmployee(order.getEmployeeIds()));
		return result;
	}
}
