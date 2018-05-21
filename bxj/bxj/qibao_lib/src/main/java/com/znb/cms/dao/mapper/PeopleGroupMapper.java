package com.znb.cms.dao.mapper;

import java.util.List;

import com.znb.cms.model.mapper.PeopleGroup;

public interface PeopleGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PeopleGroup record);

    int insertSelective(PeopleGroup record);

    PeopleGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PeopleGroup record);

    int updateByPrimaryKey(PeopleGroup record);

    List<PeopleGroup> selectAll();
}