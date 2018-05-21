package com.hangjia.champion.service.impl;

import com.hangjia.bxj.dao.ChampionBannerMapper;
import com.hangjia.bxj.model.ChampionBanner;
import com.hangjia.champion.service.ChampionBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/12.
 */
@Service
public class ChampionBannerServiceImpl implements ChampionBannerService {
    @Autowired
    private ChampionBannerMapper dao;

    /**
     * 频道banner
     * @param channelId
     * @return
     */
    public List<ChampionBanner> getBannsersByChannel(Long channelId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("channelId", channelId);
        map.put("date", new Date());
        return dao.getBannsersByChannel(map);
    }
}
