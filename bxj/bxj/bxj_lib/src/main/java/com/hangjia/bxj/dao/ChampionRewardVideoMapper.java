package com.hangjia.bxj.dao;

import java.util.List;

import com.hangjia.bxj.model.order.Reward;
import com.hangjia.bxj.query.order.RewardQuery;

public interface ChampionRewardVideoMapper {
	
    /**
     * 打赏列表分页
     * @param query
     * @return
     */
    List<Reward> queryRewardPageData(RewardQuery query);
    /**
     * 打赏列表总数
     * @param query
     * @return
     */
    int queryRewardPageDataCount(RewardQuery query);
}