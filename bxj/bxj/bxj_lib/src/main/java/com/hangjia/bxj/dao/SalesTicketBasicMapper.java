package com.hangjia.bxj.dao;

import java.util.List;
import com.hangjia.bxj.model.SalesTicketBasic;
public interface SalesTicketBasicMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(SalesTicketBasic record);

    int insertSelective(SalesTicketBasic record);

    SalesTicketBasic selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(SalesTicketBasic record);

    int updateByPrimaryKey(SalesTicketBasic record);
    
    List<SalesTicketBasic> querySalesTicketBasics();
    
    List<SalesTicketBasic> querySelectSalesTicketBasics();
}