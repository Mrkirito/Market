package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.ChampionModule;

import java.util.List;
import java.util.Map;

public interface ChampionModuleMapper {
    int deleteByPrimaryKey(Long fid);

    int insert(ChampionModule record);

    int insertSelective(ChampionModule record);

    ChampionModule selectByPrimaryKey(Long fid);

    int updateByPrimaryKeySelective(ChampionModule record);

    int updateByPrimaryKey(ChampionModule record);

    List<ChampionModule> getListPage(Map<String, Object> map);

    int getAllModuleCount();

}