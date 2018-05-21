package com.hangjia.bxj.dao;

import java.util.List;

import com.hangjia.bxj.model.ChampionTag;

public interface ChampionTagMapper {
    int deleteByPrimaryKey(Long fid);

    int insert(ChampionTag record);

    int insertSelective(ChampionTag record);

    ChampionTag selectByPrimaryKey(Long fid);

    int updateByPrimaryKeySelective(ChampionTag record);

    int updateByPrimaryKey(ChampionTag record);
    
    List<ChampionTag> selectBySelective(ChampionTag record);
}