package com.hangjia.bxj.service.prize;

import java.util.List;

import com.hangjia.bxj.model.prize.PrizeBonusLog;
import com.hangjia.bxj.query.prize.PrizeDetailQuery;

/**
 * 查询中奖详细 
 * @ClassName: 
 * @Description: 
 * @author: he-Yi
 * @date: 2016年8月4日 下午12:08:46
 */
public interface IPrizeDetailService {
	/**
	 * 查询砸蛋中奖总数
	 * @param query
	 * @return
	 */
	int queryPrizeLogCount(PrizeDetailQuery query);
	
	/**
	 * 查询砸蛋中奖列表
	 * @param query
	 * @return
	 */
	List<PrizeBonusLog> queryPrizeLogList(PrizeDetailQuery query);
	
	/** 
	 * 修改 中奖信息 状态为发货成功 
	 * @param prizelog
	 * @return
	 */
	int updatePrizeLogStatus(PrizeBonusLog prizelog);
}
