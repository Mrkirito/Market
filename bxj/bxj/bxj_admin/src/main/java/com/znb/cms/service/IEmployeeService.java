package com.znb.cms.service;

import java.util.List;

import com.znb.cms.model.dto.EmployeeDto;
import com.znb.cms.model.mapper.Employee;


/**
 * 
* @author yuanxin
* @date 2017年6月7日上午10:13:08
* @version <b>1.0.0</b>
 */
public interface IEmployeeService {
	List<Employee> selectOrderEmployee(String employeeIds);
	
	
	List<EmployeeDto> selectByOrderId(Integer orderId);
}
