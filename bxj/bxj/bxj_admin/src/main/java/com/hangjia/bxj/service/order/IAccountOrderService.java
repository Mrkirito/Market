package com.hangjia.bxj.service.order;

import java.util.List;

import com.hangjia.bxj.model.order.Reward;
import com.hangjia.bxj.query.order.RewardQuery;
import com.hangjia.bxj.ucenter.model.Cash;
import com.hangjia.bxj.ucenter.model.Profit;
import com.hangjia.bxj.ucenter.query.CashQuery;
import com.hangjia.bxj.ucenter.query.ProfitQuery;

/**
 * 用户账户相关
 * @author: yaoy
 * @date: 2016-06-29
 */
public interface IAccountOrderService {
	
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
	 * 查询打赏列表总数
	 * @param query
	 * @return
	 */
	int queryRewardDataCount(RewardQuery query);
	
	/**
	 * 查询打赏列表
	 * @param query
	 * @return
	 */
	List<Reward> queryRewardDataPage(RewardQuery query);
	
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
	 * 审核通过
	 * @param cash
	 * @return
	 */
	int passCash(Cash cash);
	
	/**
	 * 审核不通过
	 * @param cash
	 * @return
	 */
	int failCash(Cash cash);
}
