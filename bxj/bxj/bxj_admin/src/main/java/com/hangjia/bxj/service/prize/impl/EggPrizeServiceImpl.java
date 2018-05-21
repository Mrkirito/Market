package com.hangjia.bxj.service.prize.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hangjia.bxj.dao.prize.EggPrizeMapper;
import com.hangjia.bxj.model.prize.EggPrizeDO;
import com.hangjia.bxj.model.prize.EggPrizeInDO;
import com.hangjia.bxj.query.prize.EggPrizeInQuery;
import com.hangjia.bxj.query.prize.EggPrizeQuery;
import com.hangjia.bxj.service.prize.IEggPrizeService;

@Service
@Transactional(rollbackFor = Throwable.class)
public class EggPrizeServiceImpl implements IEggPrizeService {

	@Autowired
	private EggPrizeMapper eggPrizeMapper;
	
    @Value("${show_v3_path}")
    private String showPath;
    
	/**
	 * 批量新增砸蛋奖品
	 * @param eggPrizes
	 * @return
	 */
	@Override
	public Map<String, String> batchSaveEggPrize(List<EggPrizeDO> eggPrizes){
		Map<String, String> resMap = new HashMap<String, String>();
		int insert = eggPrizeMapper.batchSaveEggPrize(eggPrizes);
		if(insert != eggPrizes.size()){
			resMap.put("success", "false");
			resMap.put("msg", "新增砸蛋奖品失败");
		}
		return resMap;
	}

	/**
	 * 查询砸蛋奖品总数
	 * @param query
	 * @return
	 */
	@Override
	public int queryEggDataCount(EggPrizeQuery query) {
		return eggPrizeMapper.queryEggDataCount(query);
	}

	/**
	 * 查询砸蛋奖品列表
	 * @param query
	 * @return
	 */
	@Override
	public List<EggPrizeDO> queryEggDataPage(EggPrizeQuery query) {
		List<EggPrizeDO> list = new ArrayList<EggPrizeDO>();
		list = eggPrizeMapper.queryEggDataPage(query);
		if(null != list && list.size() > 0){
			for (EggPrizeDO eggPrizeDO : list) {
				if(StringUtils.isNotBlank(eggPrizeDO.getPrizeImg())){
					eggPrizeDO.setPrizeImg(showPath + eggPrizeDO.getPrizeImg());
				}
				if(StringUtils.isNotBlank(eggPrizeDO.getContextImg())){
					eggPrizeDO.setContextImg(showPath + eggPrizeDO.getContextImg());
				}
			}
		}
		return list;
	}

	/**
	 * 启用砸蛋奖品
	 * @param eggPrizeDO
	 * @return
	 */
	@Override
	public Map<String, String> upPrize(EggPrizeDO eggPrizeDO) {
		Map<String, String> resMap = new HashMap<String, String>();
		// 如果启用的不是100%概率的奖品
		if(eggPrizeDO.getProbability().intValue() != 100){
			// 查询是否存在100%概率的奖品
			int isExist = eggPrizeMapper.isExistHundredPercent();
			if(isExist < 1){
				resMap.put("success", "false");
				resMap.put("msg", "该操作后无100%概率的奖品，请先启用100%概率的奖品后再启用其他奖品。");
				return resMap;
			}
		}
		int update = eggPrizeMapper.updateEggPrizeEnable(eggPrizeDO);
		if(update != 1){
			resMap.put("success", "false");
			resMap.put("msg", "启用砸蛋奖品失败");
		}
		return resMap;
	}

	/**
	 * 禁用砸蛋奖品
	 * @param eggPrizeDO
	 * @return
	 */
	@Override
	public Map<String, String> downPrize(EggPrizeDO eggPrizeDO) {
		Map<String, String> resMap = new HashMap<String, String>();
		// 如果禁用的是100%概率的奖品
		if(eggPrizeDO.getProbability().intValue() == 100){
			// 查询是否存在100%概率的奖品
			int isExist = eggPrizeMapper.isExistHundredPercent();
			if(isExist <= 1){
				resMap.put("success", "false");
				resMap.put("msg", "该操作后无100%概率的奖品，请先启动至少1个100%概率的奖品。");
				return resMap;
			}
		}
		int update = eggPrizeMapper.updateEggPrizeEnable(eggPrizeDO);
		if(update != 1){
			resMap.put("success", "false");
			resMap.put("msg", "禁用砸蛋奖品失败");
		}
		return resMap;
	}

	/**
	 * 更新砸蛋奖品数据
	 * @param eggPrizeDO
	 * @return
	 */
	@Override
	public Map<String, String> updatePrize(EggPrizeDO eggPrizeDO) {
		Map<String, String> resMap = new HashMap<String, String>();
		// 查询是否存在100%概率的奖品
		if(eggPrizeDO.getOldProbability().intValue() == 100 && eggPrizeDO.getProbability().intValue() != 100){
			// 查询是否存在100%概率的奖品
			int isExist = eggPrizeMapper.isExistHundredPercent();
			if(isExist <= 1){
				resMap.put("success", "false");
				resMap.put("msg", "该操作后无100%概率的奖品，请先启动至少1个100%概率的奖品。");
				return resMap;
			}
		}
		int update = eggPrizeMapper.updatePrize(eggPrizeDO);
		if(update != 1){
			resMap.put("success", "false");
			resMap.put("msg", "该数据已经被更新，请刷新后重新修改。");
		}
		return resMap;
	}

	/**
	 * 查询砸蛋奖品增量总数
	 * @param query
	 * @return
	 */
	@Override
	public int queryEggInDataCount(EggPrizeInQuery query) {
		return eggPrizeMapper.queryEggInDataCount(query);
	}

	/**
	 * 查询砸蛋奖品增量列表
	 * @param query
	 * @return
	 */
	@Override
	public List<EggPrizeInDO> queryEggInDataPage(EggPrizeInQuery query) {
		return eggPrizeMapper.queryEggInDataPage(query);
	}

	/**
	 * 删除砸蛋奖品增量
	 * @param query
	 * @return
	 */
	@Override
	public int deletePrizeIn(Long id, Date updateTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("updateTime", updateTime);
		return eggPrizeMapper.deletePrizeIn(map);
	}
	
	/**
	 * 批量新增砸蛋奖品增量
	 * @param eggPrizes
	 * @return
	 */
	@Override
	public Map<String, String> batchSaveEggPrizeIn(List<EggPrizeInDO> eggPrizesIn){
		Map<String, String> resMap = new HashMap<String, String>();
		int insert = eggPrizeMapper.batchSaveEggPrizeIn(eggPrizesIn);
		if(insert != eggPrizesIn.size()){
			resMap.put("success", "false");
			resMap.put("msg", "新增砸蛋奖品失败");
		}
		return resMap;
	}
}
