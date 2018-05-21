package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.ChampionBanner;

import java.util.List;
import java.util.Map;

public interface ChampionBannerMapper {
    int deleteByPrimaryKey(Long fid);

    int insert(ChampionBanner record);

    int insertSelective(ChampionBanner record);

    ChampionBanner selectByPrimaryKey(Long fid);

    int updateByPrimaryKeySelective(ChampionBanner record);

    int updateByPrimaryKey(ChampionBanner record);

    List<ChampionBanner> getBannsersByChannel(Map<String, Object> map);
}