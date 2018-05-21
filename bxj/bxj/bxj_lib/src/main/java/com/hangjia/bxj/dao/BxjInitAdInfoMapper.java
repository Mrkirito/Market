package com.hangjia.bxj.dao;

import java.util.List;

import com.hangjia.bxj.model.BxjInitAdInfo;
import com.hangjia.bxj.query.ad.BxjInitAdQuery;

public interface BxjInitAdInfoMapper {
    int deleteByPrimaryKey(Long fid);

    int insert(BxjInitAdInfo record);

    int insertSelective(BxjInitAdInfo record);

    BxjInitAdInfo selectByPrimaryKey(Long fid);

    int updateByPrimaryKeySelective(BxjInitAdInfo record);

    int updateByPrimaryKey(BxjInitAdInfo record);
    
    
    int selectCount(BxjInitAdQuery query);
    List<BxjInitAdInfo> selectList(BxjInitAdQuery query);
}