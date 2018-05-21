package com.hangjia.bxj.service.prize;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hangjia.bxj.model.prize.EggPrizeDO;
import com.hangjia.bxj.model.prize.EggPrizeInDO;
import com.hangjia.bxj.query.prize.EggPrizeInQuery;
import com.hangjia.bxj.query.prize.EggPrizeQuery;

/**
 * 砸蛋奖品相关
 * @author: yaoy
 * @date: 2016-06-29
 */
public interface IEggPrizeService {
	
	/**
	 * 批量新增砸蛋奖品
	 * @param eggPrizes
	 * @return
	 */
	Map<String, String> batchSaveEggPrize(List<EggPrizeDO> eggPrizes);
	
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
	 * 启用砸蛋奖品
	 * @param eggPrizeDO
	 * @return
	 */
	Map<String, String> upPrize(EggPrizeDO eggPrizeDO);
	
	/**
	 * 禁用砸蛋奖品
	 * @param eggPrizeDO
	 * @return
	 */
	Map<String, String> downPrize(EggPrizeDO eggPrizeDO);
	
	/**
	 * 更新砸蛋奖品数据
	 * @param eggPrizeDO
	 * @return
	 */
	Map<String, String> updatePrize(EggPrizeDO eggPrizeDO);
	
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
	int deletePrizeIn(Long id, Date updateTime);
	
	/**
	 * 批量新增砸蛋奖品增量
	 * @param eggPrizes
	 * @return
	 */
	Map<String, String> batchSaveEggPrizeIn(List<EggPrizeInDO> eggPrizesIn);
}
