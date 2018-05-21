package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.BxjMyConf;

import java.util.List;

public interface BxjMyConfMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BxjMyConf record);

    int insertSelective(BxjMyConf record);

    BxjMyConf selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BxjMyConf record);

    int updateByPrimaryKey(BxjMyConf record);

    List<BxjMyConf> selectBySelective(BxjMyConf record);
}