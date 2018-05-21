package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.SalesTicketIntoLog;

public interface SalesTicketIntoLogMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(SalesTicketIntoLog record);

    int insertSelective(SalesTicketIntoLog record);

    SalesTicketIntoLog selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(SalesTicketIntoLog record);

    int updateByPrimaryKey(SalesTicketIntoLog record);
}