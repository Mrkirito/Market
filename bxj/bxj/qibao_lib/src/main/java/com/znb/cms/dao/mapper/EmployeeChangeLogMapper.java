package com.znb.cms.dao.mapper;

import java.util.List;

import com.znb.cms.model.dto.EmployeeDto;
import com.znb.cms.model.mapper.EmployeeChangeLog;

public interface EmployeeChangeLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EmployeeChangeLog record);

    int insertSelective(EmployeeChangeLog record);

    EmployeeChangeLog selectByPrimaryKey(Integer id);
    
    List<EmployeeDto> selectByOrderId(Integer orderId);

    int updateByPrimaryKeySelective(EmployeeChangeLog record);

    int updateByPrimaryKey(EmployeeChangeLog record);
}