package com.hangjia.bxj.dao.prize;

import java.util.List;
import java.util.Map;

import com.hangjia.bxj.model.prize.EggPrizeDO;
import com.hangjia.bxj.model.prize.EggPrizeInDO;
import com.hangjia.bxj.query.prize.EggPrizeInQuery;
import com.hangjia.bxj.query.prize.EggPrizeQuery;

public interface EggPrizeMapper {
	
	/**
	 * 批量新增砸蛋奖品
	 * @param eggPrizes
	 * @return
	 */
	int batchSaveEggPrize(List<EggPrizeDO> eggPrizes);
	
	/**
	 * 查询砸蛋奖品总数
	 * @param query
	 * @return
	 */
	int queryEggDataCount(EggPrizeQuery query);
	
	/**
	 * 查询砸蛋奖品列表
	 * @param query
	 * @return
	 */
	List<EggPrizeDO> queryEggDataPage(EggPrizeQuery query);
	
	/**
	 * 更新砸蛋奖品启用状态
	 * @param eggPrizeDO
	 * @return
	 */
	int updateEggPrizeEnable(EggPrizeDO eggPrizeDO);
	
	/**
	 * 查询是否存在100%概率的奖品
	 * @return
	 */
	int isExistHundredPercent();
	
	/**
	 * 更新砸蛋奖品数据
	 * @param eggPrizeDO
	 * @return
	 */
	int updatePrize(EggPrizeDO eggPrizeDO);
	
	/**
	 * 查询砸蛋奖品增量总数
	 * @param query
	 * @return
	 */
	int queryEggInDataCount(EggPrizeInQuery query);
	
	/**
	 * 查询砸蛋奖品增量列表
	 * @param query
	 * @return
	 */
	List<EggPrizeInDO> queryEggInDataPage(EggPrizeInQuery query);
	
	/**
	 * 删除砸蛋奖品增量
	 * @param query
	 * @return
	 */
	int deletePrizeIn(Map<String, Object> map);
	
	/**
	 * 查询所有砸蛋奖品
	 * @param query
	 * @return
	 */
	List<EggPrizeDO> queryAllEggPrizes(EggPrizeQuery query);
	
	/**
	 * 批量新增砸蛋奖品增量
	 * @param eggPrizes
	 * @return
	 */
	int batchSaveEggPrizeIn(List<EggPrizeInDO> eggPrizesIn);
}