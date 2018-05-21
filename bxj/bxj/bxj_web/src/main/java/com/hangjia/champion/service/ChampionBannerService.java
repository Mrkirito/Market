package com.hangjia.champion.service;

import com.hangjia.bxj.model.ChampionBanner;

import java.util.List;

/**
 * Created by Administrator on 2016/4/12.
 */
public interface ChampionBannerService {
    List<ChampionBanner> getBannsersByChannel(Long channelId);
}
