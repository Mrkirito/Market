package com.znb.cms.dao.mapper;

import com.znb.cms.model.mapper.Isurant;

public interface IsurantMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Isurant record);

    int insertSelective(Isurant record);

    Isurant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Isurant record);

    int updateByPrimaryKey(Isurant record);
}