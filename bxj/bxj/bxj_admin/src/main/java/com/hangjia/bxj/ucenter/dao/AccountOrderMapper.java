package com.hangjia.bxj.ucenter.dao;

import java.util.List;

import com.hangjia.bxj.query.order.RewardQuery;
import com.hangjia.bxj.ucenter.model.Cash;
import com.hangjia.bxj.ucenter.model.Profit;
import com.hangjia.bxj.ucenter.model.UcUser;
import com.hangjia.bxj.ucenter.query.CashQuery;
import com.hangjia.bxj.ucenter.query.ProfitQuery;

public interface AccountOrderMapper {
	
	/**
	 * 查询收益列表总数
	 * @param query
	 * @return
	 */
	int queryProfitDataCount(ProfitQuery query);
	
	/**
	 * 查询收益列表
	 * @param query
	 * @return
	 */
	List<Profit> queryProfitDataPage(ProfitQuery query);
	
	/**
	 * 根据用户id查询用户信息
	 * @param query
	 * @return
	 */
	List<UcUser> queryUserByUserId(List<Long> userIds);
	
	/**
	 * 查询所有符合信息的userId
	 * @param query
	 * @return
	 */
	List<Long> queryUserIds(RewardQuery query);
	
	/**
	 * 查询提现列表总数
	 * @param query
	 * @return
	 */
	int queryCashDataCount(CashQuery query);
	
	/**
	 * 查询提现列表
	 * @param query
	 * @return
	 */
	List<Cash> queryCashDataPage(CashQuery query);
	
	/**
	 * 更新提现审核状态
	 * @param cash
	 * @return
	 */
	int updateCashStatus(Cash cash);
}
