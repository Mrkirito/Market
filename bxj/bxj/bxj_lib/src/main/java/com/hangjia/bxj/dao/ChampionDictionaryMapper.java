package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.ChampionDictionary;

import java.util.List;

public interface ChampionDictionaryMapper {
    int insert(ChampionDictionary record);

    int insertSelective(ChampionDictionary record);

    /**************************** extend begin here *******************************/
    /**
     * 分页查询 activity_dictionary
     * @param record
     * @return
     */
    List<ChampionDictionary> selectByPage(ChampionDictionary record);
}