package com.znb.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.znb.cms.dao.mapper.EmployeeChangeLogMapper;
import com.znb.cms.dao.mapper.EmployeeMapper;
import com.znb.cms.model.dto.EmployeeDto;
import com.znb.cms.model.mapper.Employee;
import com.znb.cms.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	private EmployeeChangeLogMapper employeeChangeLogMapper;

	@Override
	public List<Employee> selectOrderEmployee(String employeeIds) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("employeeIds", employeeIds.split(","));
		return employeeMapper.selectOrderEmployee(param);
	}

	@Override
	public List<EmployeeDto> selectByOrderId(Integer orderId) {
		return employeeChangeLogMapper.selectByOrderId(orderId);
	}
	
	

}
