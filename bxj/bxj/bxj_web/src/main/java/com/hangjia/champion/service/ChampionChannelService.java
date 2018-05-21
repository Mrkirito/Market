package com.hangjia.champion.service;

import java.util.List;

import com.hangjia.bxj.model.ChampionChannel;

public interface ChampionChannelService {

	/**
	 * @Title: queryShowChampionChannel
	 * @Description: 获取头部显示频道
	 * @return
	 */
	List<ChampionChannel> queryHeadChampionChannel();

}
