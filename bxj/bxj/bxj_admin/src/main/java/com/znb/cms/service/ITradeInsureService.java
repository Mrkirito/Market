package com.znb.cms.service;

import java.text.ParseException;
import java.util.List;

import com.core.cms.model.mapper.TCSource;
import org.springframework.web.multipart.MultipartFile;

import com.core.cms.common.AjaxResult;
import com.core.cms.model.dto.TradeInsureDto;
import com.core.cms.model.mapper.TradeInsure;

/**
 * 
* @author yuanxin
* @date 2017年6月7日上午10:13:25
* @version <b>1.0.0</b>
 */
public interface ITradeInsureService {
	int selectCount(TradeInsure tradeInsure);
	
	List<TradeInsure> getTradeInsureList(TradeInsure tradeInsure);
	
	TradeInsureDto getTradeInsureDtoByOrderId(String tradeId);
	
	int updateTradeInsure(TradeInsureDto tradeInsureDto) throws ParseException;
	
	
	int delTradeInsureByFid(Integer Fid);
	
	AjaxResult importSinglePolicy(MultipartFile file,Integer Fid) throws Exception;

	List<TCSource> queryTCSource();


	TradeInsureDto getTradeInsureDtoById(Integer tradeId);

}
