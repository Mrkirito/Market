package com.znb.cms.dao.mapper;

import java.util.List;

import com.znb.cms.model.dto.OrderDto;
import com.znb.cms.model.mapper.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);
    
    int delOrder(Integer id);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);
    
    List<OrderDto> selectOrderByOrder(OrderDto record);
    
    int selectCount(OrderDto record);

    int updateByPrimaryKeySelective(OrderDto record);

    int updateByPrimaryKey(Order record);
}