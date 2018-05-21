package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.ChampionModuleVideo;

public interface ChampionModuleVideoMapper {
    int deleteByPrimaryKey(Long fid);

    int insert(ChampionModuleVideo record);

    int insertSelective(ChampionModuleVideo record);

    ChampionModuleVideo selectByPrimaryKey(Long fid);

    int updateByPrimaryKeySelective(ChampionModuleVideo record);

    int updateByPrimaryKey(ChampionModuleVideo record);
}