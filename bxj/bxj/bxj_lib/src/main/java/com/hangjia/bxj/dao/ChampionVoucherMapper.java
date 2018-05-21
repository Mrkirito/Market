package com.hangjia.bxj.dao;

import com.hangjia.bxj.model.ChampionVoucher;

public interface ChampionVoucherMapper {
    int deleteByPrimaryKey(Long fid);

    int insert(ChampionVoucher record);

    int insertSelective(ChampionVoucher record);

    ChampionVoucher selectByPrimaryKey(Long fid);

    int updateByPrimaryKeySelective(ChampionVoucher record);

    int updateByPrimaryKey(ChampionVoucher record);
}