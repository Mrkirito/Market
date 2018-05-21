package com.hangjia.bxj.dao;

import java.util.List;

import com.hangjia.bxj.model.ChampionTag;
import com.hangjia.bxj.model.ChampionVideoTag;

public interface ChampionVideoTagMapper {
    int deleteByPrimaryKey(Long fid);

    int insert(ChampionVideoTag record);

    int insertSelective(ChampionVideoTag record);

    ChampionVideoTag selectByPrimaryKey(Long fid);

    int updateByPrimaryKeySelective(ChampionVideoTag record);

    int updateByPrimaryKey(ChampionVideoTag record);
    
    List<ChampionTag> queryTagByVideoId(ChampionVideoTag record);
    
    int deleteByVideoId(Long videoId);
}