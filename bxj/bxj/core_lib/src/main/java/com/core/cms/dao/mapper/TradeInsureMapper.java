package com.core.cms.dao.mapper;

import java.util.List;

import com.core.cms.model.dto.TradeInsureDto;
import com.core.cms.model.mapper.PolicyMail;
import com.core.cms.model.mapper.TradeInsure;

public interface TradeInsureMapper {
	
	int selectCount(TradeInsure tradeInsure);
	
	List<TradeInsure> getTradeInsureList(TradeInsure tradeInsure);
	
    int deleteByPrimaryKey(Integer fid);

    int insert(TradeInsure record);

    int insertSelective(TradeInsure record);

    TradeInsure selectByPrimaryKey(Integer fid);
    
    TradeInsureDto getTradeInsureDto(String tradeId);

    int updateByPrimaryKeySelective(TradeInsure record);

    int updateByPrimaryKey(TradeInsure record);
    
    PolicyMail getIsurentMail(Integer fid);

    TradeInsureDto getTradeInsureDtoByFid(Integer tradeId);

}