package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.PlanBookVoice;

import java.util.List;

public interface PlanBookVoiceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlanBookVoice record);

    int insertSelective(PlanBookVoice record);

    PlanBookVoice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PlanBookVoice record);

    int updateByPrimaryKey(PlanBookVoice record);

    int selectBySelectiveCount(PlanBookVoice record);

    List<PlanBookVoice> selectBySelective(PlanBookVoice record);

    long selectMaxPrimaryKey();
}