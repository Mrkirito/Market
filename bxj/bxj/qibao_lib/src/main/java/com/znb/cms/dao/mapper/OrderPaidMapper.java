package com.znb.cms.dao.mapper;

import java.util.List;

import com.znb.cms.model.mapper.OrderPaid;

public interface OrderPaidMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(OrderPaid record);

    int insertSelective(OrderPaid record);

    int deleteAll();

    List<OrderPaid> selectAll();

    OrderPaid selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderPaid record);

    int updateByPrimaryKey(OrderPaid record);
}