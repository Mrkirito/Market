package com.znb.cms.service;

import java.util.List;

import com.znb.cms.model.mapper.Compensation;

public interface ISettlementService {
	
	/**
	 * 
	 * @param compensation
	 * @return 返回Compensation集合
	 */
	List<Compensation> getCompensationList(Compensation compensation);
	
	
	Compensation getCompensation(Integer id);
	
	/**
	 * 
	 * @param compensation
	 * @return 返回当前条件总行数
	 */
	int selectCount(Compensation compensation);
}
