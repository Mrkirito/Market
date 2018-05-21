package com.hangjia.bxj.dao;

import java.util.List;

import com.hangjia.bxj.model.FreeInsurance;
import com.hangjia.bxj.model.FreeReceiveLog;

public interface FreeReceiveLogDao {

	/**
	 * 保存赠险领取记录
	 * @param freeReceiveLog
	 * @return
	 */
	void save(FreeReceiveLog freeReceiveLog);
	
	/**
	 * 查询我的客户的赠险领取记录
	 * @param userId
	 * @param customerId
	 * @return
	 */
	List<FreeReceiveLog> listCustomersReceiveLog(Integer userId, Long customerId);

	/**
	 * 返回所有的免费险产品。
	 * @return
	 */
	List<FreeInsurance> listFreeInsurances();

	/**
	 * 使用ID获得单个免费险产品
	 * @param id
	 * @return
	 */
	FreeInsurance getFreeInsurance(String id);
	
}
