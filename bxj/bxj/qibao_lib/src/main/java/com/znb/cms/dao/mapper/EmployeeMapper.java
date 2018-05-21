package com.znb.cms.dao.mapper;

import java.util.List;
import java.util.Map;

import com.znb.cms.model.mapper.Employee;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);
    
    List<Employee> selectOrderEmployee(Map<String, Object> param);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
}