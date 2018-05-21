package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.SalesTicketDocument;

public interface SalesTicketDocumentMapper {
    int deleteByPrimaryKey(Long fid);

    int insert(SalesTicketDocument record);

    int insertSelective(SalesTicketDocument record);

    SalesTicketDocument selectByPrimaryKey(Long fid);

    int updateByPrimaryKeySelective(SalesTicketDocument record);

    int updateByPrimaryKey(SalesTicketDocument record);
}