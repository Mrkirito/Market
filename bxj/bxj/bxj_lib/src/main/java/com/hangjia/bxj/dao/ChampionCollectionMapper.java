package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.ChampionCollection;

import java.util.List;
import java.util.Map;

public interface ChampionCollectionMapper {
    int deleteByPrimaryKey(Long fid);

    int insert(ChampionCollection record);

    int insertSelective(ChampionCollection record);

    ChampionCollection selectByPrimaryKey(Long fid);
    
    List<ChampionCollection> selectBySelective(ChampionCollection record);

    int updateByPrimaryKeySelective(ChampionCollection record);

    int updateByPrimaryKey(ChampionCollection record);

    int selectCountBy(Map<String, Object> map);

    int updateCollectionBy(Map<String, Object> map);

    int cancelList(List<Long> list);
    /**
     * 批量取消收藏  根据 视频id 和用户id 
     * @param map
     * @return
     */
    int cancelVedioList(Map<String, Object> map);
}