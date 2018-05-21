package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.BxjVersionInfo;

import java.util.List;

public interface BxjVersionInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BxjVersionInfo record);

    int insertSelective(BxjVersionInfo record);

    BxjVersionInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BxjVersionInfo record);

    int updateByPrimaryKey(BxjVersionInfo record);

    List<BxjVersionInfo> selectBySelective(BxjVersionInfo record);
}