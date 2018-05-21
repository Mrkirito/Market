package com.hangjia.bxj.dao;

import java.util.List;

import com.hangjia.bxj.model.ChampionChannel;

public interface ChampionChannelMapper {
    int deleteByPrimaryKey(Long fid);

    int insert(ChampionChannel record);

    int insertSelective(ChampionChannel record);

    ChampionChannel selectByPrimaryKey(Long fid);

    int updateByPrimaryKeySelective(ChampionChannel record);

    int updateByPrimaryKey(ChampionChannel record);
    
    List<ChampionChannel> selectBySelective(ChampionChannel championChannel);

    /**
     * 根据父id查找所有子id
     * @param id
     * @return
     */
    List<Long> getIdsByParentId(Long id);

    /**
     * 根据父id查找所有子频道
     * @param id
     * @return
     */
    List<ChampionChannel> getChannelsByParentId(Long id);
}