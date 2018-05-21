package com.core.cms.dao.mapper;

import com.core.cms.model.mapper.TCSource;

import java.util.List;

public interface TCSourceMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(TCSource record);

    int insertSelective(TCSource record);

    TCSource selectByPrimaryKey(Integer fid);

    int updateByPrimaryKeySelective(TCSource record);

    int updateByPrimaryKey(TCSource record);

    List<TCSource> queryTCSource();
}