package com.hangjia.bxj.dao.prize;

import java.util.List;

import com.hangjia.bxj.model.prize.PrizeBonusLog;
import com.hangjia.bxj.query.prize.PrizeDetailQuery;

/**
 * 中奖 详细 
 * @ClassName: 
 * @Description: 
 * @author: he-Yi
 * @date: 2016年8月4日 下午12:04:00
 */
public interface PrizeBonusLogMapper {
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
	 * 修改 中奖信息状态为发货
	 * @param query
	 * @return
	 */
    int updatePrizeLogStatus(PrizeBonusLog query);
}