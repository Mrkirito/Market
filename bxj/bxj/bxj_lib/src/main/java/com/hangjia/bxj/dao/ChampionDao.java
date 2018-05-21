package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.ChampionExtract;

/**
 * 冠军论坛
 * @author K9999
 *
 */
public interface ChampionDao {

	/**
	 * 返回单个提取码记录。
	 * @param id 
	 * @return
	 */
	ChampionExtract get(String id);
	
}
