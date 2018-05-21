package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.HjbProductCustom;

public interface HjbProductCustomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HjbProductCustom record);

    int insertSelective(HjbProductCustom record);

    HjbProductCustom selectByPrimaryKey(Integer id);

    /*B端客户登陆验证*/
    HjbProductCustom selectByLoginAccount(HjbProductCustom custom);

    int updateByPrimaryKeySelective(HjbProductCustom record);

    int updateByPrimaryKey(HjbProductCustom record);
}