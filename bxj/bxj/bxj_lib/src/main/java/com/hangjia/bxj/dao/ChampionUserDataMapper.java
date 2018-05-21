package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.ChampionUserData;

public interface ChampionUserDataMapper {
    int deleteByPrimaryKey(Long fid);

    int insert(ChampionUserData record);

    int insertSelective(ChampionUserData record);

    ChampionUserData selectByPrimaryKey(Long fid);

    int updateByPrimaryKeySelective(ChampionUserData record);

    int updateByPrimaryKey(ChampionUserData record);
    
    ChampionUserData selectByUserId(Long userId);
}