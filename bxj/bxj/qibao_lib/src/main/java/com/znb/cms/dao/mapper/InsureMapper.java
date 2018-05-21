package com.znb.cms.dao.mapper;

import com.znb.cms.model.mapper.Insure;

public interface InsureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Insure record);

    int insertSelective(Insure record);

    Insure selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Insure record);

    int updateByPrimaryKey(Insure record);
}