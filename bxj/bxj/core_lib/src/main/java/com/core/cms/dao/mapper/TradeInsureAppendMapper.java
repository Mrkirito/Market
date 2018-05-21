package com.core.cms.dao.mapper;

import com.core.cms.model.mapper.TradeInsureAppend;

public interface TradeInsureAppendMapper {
    int deleteByPrimaryKey(Long fid);

    int insert(TradeInsureAppend record);

    int insertSelective(TradeInsureAppend record);

    TradeInsureAppend selectByPrimaryKey(Long fid);

    int updateByPrimaryKeySelective(TradeInsureAppend record);

    int updateByPrimaryKey(TradeInsureAppend record);
}