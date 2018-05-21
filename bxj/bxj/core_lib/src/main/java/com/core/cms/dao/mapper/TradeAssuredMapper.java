package com.core.cms.dao.mapper;

import java.util.List;

import com.core.cms.model.mapper.TradeAssured;
import com.core.cms.model.mapper.TradeInsure;

public interface TradeAssuredMapper {
    int deleteByPrimaryKey(Long fid);

    int insert(TradeAssured record);

    int insertSelective(TradeAssured record);

    TradeAssured selectByPrimaryKey(Long fid);

    int updateByPrimaryKeySelective(TradeAssured record);

    int updateByPrimaryKey(TradeAssured record);
    
    int selectCount(TradeInsure tradeInsure);
	
	List<TradeInsure> getTradeInsureList(TradeInsure tradeInsure);
}