package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.SalesTicketOrderDetail;

public interface SalesTicketOrderDetailMapper {
    int deleteByPrimaryKey(Long fid);

    int insert(SalesTicketOrderDetail record);

    int insertSelective(SalesTicketOrderDetail record);

    SalesTicketOrderDetail selectByPrimaryKey(Long fid);

    int updateByPrimaryKeySelective(SalesTicketOrderDetail record);

    int updateByPrimaryKey(SalesTicketOrderDetail record);
}