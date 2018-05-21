package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.BxjIpInfo;

import java.util.List;

public interface BxjIpInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BxjIpInfo record);

    int insertSelective(BxjIpInfo record);

    BxjIpInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BxjIpInfo record);

    int updateByPrimaryKey(BxjIpInfo record);

    List<BxjIpInfo> selectBySelective(BxjIpInfo record);
}