package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.ChampionPlayVideoLog;

public interface ChampionPlayVideoLogMapper {
    int deleteByPrimaryKey(Long fid);

    int insert(ChampionPlayVideoLog record);

    int insertSelective(ChampionPlayVideoLog record);

    ChampionPlayVideoLog selectByPrimaryKey(Long fid);

    int updateByPrimaryKeySelective(ChampionPlayVideoLog record);

    int updateByPrimaryKey(ChampionPlayVideoLog record);
}